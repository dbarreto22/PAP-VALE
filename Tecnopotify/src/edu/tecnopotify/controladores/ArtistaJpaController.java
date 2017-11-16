/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Artista;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.tecnopotify.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class ArtistaJpaController implements Serializable {

    public ArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artista artista) throws PreexistingEntityException, Exception {
        if (artista.getLstSeguidos() == null) {
            artista.setLstSeguidos(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedLstSeguidos = new ArrayList<Usuario>();
            for (Usuario lstSeguidosUsuarioToAttach : artista.getLstSeguidos()) {
                lstSeguidosUsuarioToAttach = em.getReference(lstSeguidosUsuarioToAttach.getClass(), lstSeguidosUsuarioToAttach.getNickname());
                attachedLstSeguidos.add(lstSeguidosUsuarioToAttach);
            }
            artista.setLstSeguidos(attachedLstSeguidos);
            em.persist(artista);
            for (Usuario lstSeguidosUsuario : artista.getLstSeguidos()) {
                lstSeguidosUsuario.getLstSeguidos().add(artista);
                lstSeguidosUsuario = em.merge(lstSeguidosUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtista(artista.getNickname()) != null) {
                throw new PreexistingEntityException("Artista " + artista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artista artista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista persistentArtista = em.find(Artista.class, artista.getNickname());
            List<Usuario> lstSeguidosOld = persistentArtista.getLstSeguidos();
            List<Usuario> lstSeguidosNew = artista.getLstSeguidos();
            List<Usuario> attachedLstSeguidosNew = new ArrayList<Usuario>();
            for (Usuario lstSeguidosNewUsuarioToAttach : lstSeguidosNew) {
                lstSeguidosNewUsuarioToAttach = em.getReference(lstSeguidosNewUsuarioToAttach.getClass(), lstSeguidosNewUsuarioToAttach.getNickname());
                attachedLstSeguidosNew.add(lstSeguidosNewUsuarioToAttach);
            }
            lstSeguidosNew = attachedLstSeguidosNew;
            artista.setLstSeguidos(lstSeguidosNew);
            artista = em.merge(artista);
            for (Usuario lstSeguidosOldUsuario : lstSeguidosOld) {
                if (!lstSeguidosNew.contains(lstSeguidosOldUsuario)) {
                    lstSeguidosOldUsuario.getLstSeguidos().remove(artista);
                    lstSeguidosOldUsuario = em.merge(lstSeguidosOldUsuario);
                }
            }
            for (Usuario lstSeguidosNewUsuario : lstSeguidosNew) {
                if (!lstSeguidosOld.contains(lstSeguidosNewUsuario)) {
                    lstSeguidosNewUsuario.getLstSeguidos().add(artista);
                    lstSeguidosNewUsuario = em.merge(lstSeguidosNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = artista.getNickname();
                if (findArtista(id) == null) {
                    throw new NonexistentEntityException("The artista with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista artista;
            try {
                artista = em.getReference(Artista.class, id);
                artista.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artista with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> lstSeguidos = artista.getLstSeguidos();
            for (Usuario lstSeguidosUsuario : lstSeguidos) {
                lstSeguidosUsuario.getLstSeguidos().remove(artista);
                lstSeguidosUsuario = em.merge(lstSeguidosUsuario);
            }
            em.remove(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artista> findArtistaEntities() {
        return findArtistaEntities(true, -1, -1);
    }

    public List<Artista> findArtistaEntities(int maxResults, int firstResult) {
        return findArtistaEntities(false, maxResults, firstResult);
    }

    private List<Artista> findArtistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artista.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Artista findArtista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artista.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artista> rt = cq.from(Artista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
