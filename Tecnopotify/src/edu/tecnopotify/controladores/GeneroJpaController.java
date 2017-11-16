/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.tecnopotify.entidades.Album;
import java.util.ArrayList;
import java.util.List;
import edu.tecnopotify.entidades.Genero;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) throws PreexistingEntityException, Exception {
        if (genero.getListAlbum() == null) {
            genero.setListAlbum(new ArrayList<Album>());
        }
        if (genero.getListHijos() == null) {
            genero.setListHijos(new ArrayList<Genero>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Album> attachedListAlbum = new ArrayList<Album>();
            for (Album listAlbumAlbumToAttach : genero.getListAlbum()) {
                listAlbumAlbumToAttach = em.getReference(listAlbumAlbumToAttach.getClass(), listAlbumAlbumToAttach.getNombre());
                attachedListAlbum.add(listAlbumAlbumToAttach);
            }
            genero.setListAlbum(attachedListAlbum);
            List<Genero> attachedListHijos = new ArrayList<Genero>();
            for (Genero listHijosGeneroToAttach : genero.getListHijos()) {
                listHijosGeneroToAttach = em.getReference(listHijosGeneroToAttach.getClass(), listHijosGeneroToAttach.getNombre());
                attachedListHijos.add(listHijosGeneroToAttach);
            }
            genero.setListHijos(attachedListHijos);
            em.persist(genero);
            for (Album listAlbumAlbum : genero.getListAlbum()) {
                listAlbumAlbum.getListGenero().add(genero);
                listAlbumAlbum = em.merge(listAlbumAlbum);
            }
            for (Genero listHijosGenero : genero.getListHijos()) {
                listHijosGenero.getListHijos().add(genero);
                listHijosGenero = em.merge(listHijosGenero);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenero(genero.getNombre()) != null) {
                throw new PreexistingEntityException("Genero " + genero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getNombre());
            List<Album> listAlbumOld = persistentGenero.getListAlbum();
            List<Album> listAlbumNew = genero.getListAlbum();
            List<Genero> listHijosOld = persistentGenero.getListHijos();
            List<Genero> listHijosNew = genero.getListHijos();
            List<Album> attachedListAlbumNew = new ArrayList<Album>();
            for (Album listAlbumNewAlbumToAttach : listAlbumNew) {
                listAlbumNewAlbumToAttach = em.getReference(listAlbumNewAlbumToAttach.getClass(), listAlbumNewAlbumToAttach.getNombre());
                attachedListAlbumNew.add(listAlbumNewAlbumToAttach);
            }
            listAlbumNew = attachedListAlbumNew;
            genero.setListAlbum(listAlbumNew);
            List<Genero> attachedListHijosNew = new ArrayList<Genero>();
            for (Genero listHijosNewGeneroToAttach : listHijosNew) {
                listHijosNewGeneroToAttach = em.getReference(listHijosNewGeneroToAttach.getClass(), listHijosNewGeneroToAttach.getNombre());
                attachedListHijosNew.add(listHijosNewGeneroToAttach);
            }
            listHijosNew = attachedListHijosNew;
            genero.setListHijos(listHijosNew);
            genero = em.merge(genero);
            for (Album listAlbumOldAlbum : listAlbumOld) {
                if (!listAlbumNew.contains(listAlbumOldAlbum)) {
                    listAlbumOldAlbum.getListGenero().remove(genero);
                    listAlbumOldAlbum = em.merge(listAlbumOldAlbum);
                }
            }
            for (Album listAlbumNewAlbum : listAlbumNew) {
                if (!listAlbumOld.contains(listAlbumNewAlbum)) {
                    listAlbumNewAlbum.getListGenero().add(genero);
                    listAlbumNewAlbum = em.merge(listAlbumNewAlbum);
                }
            }
            for (Genero listHijosOldGenero : listHijosOld) {
                if (!listHijosNew.contains(listHijosOldGenero)) {
                    listHijosOldGenero.getListHijos().remove(genero);
                    listHijosOldGenero = em.merge(listHijosOldGenero);
                }
            }
            for (Genero listHijosNewGenero : listHijosNew) {
                if (!listHijosOld.contains(listHijosNewGenero)) {
                    listHijosNewGenero.getListHijos().add(genero);
                    listHijosNewGenero = em.merge(listHijosNewGenero);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genero.getNombre();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<Album> listAlbum = genero.getListAlbum();
            for (Album listAlbumAlbum : listAlbum) {
                listAlbumAlbum.getListGenero().remove(genero);
                listAlbumAlbum = em.merge(listAlbumAlbum);
            }
            List<Genero> listHijos = genero.getListHijos();
            for (Genero listHijosGenero : listHijos) {
                listHijosGenero.getListHijos().remove(genero);
                listHijosGenero = em.merge(listHijosGenero);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
