/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.tecnopotify.entidades.Genero;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class AlbumJpaController implements Serializable {

    public AlbumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Album album) throws PreexistingEntityException, Exception {
        if (album.getListGenero() == null) {
            album.setListGenero(new ArrayList<Genero>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Genero> attachedListGenero = new ArrayList<Genero>();
            for (Genero listGeneroGeneroToAttach : album.getListGenero()) {
                listGeneroGeneroToAttach = em.getReference(listGeneroGeneroToAttach.getClass(), listGeneroGeneroToAttach.getNombre());
                attachedListGenero.add(listGeneroGeneroToAttach);
            }
            album.setListGenero(attachedListGenero);
            em.persist(album);
            for (Genero listGeneroGenero : album.getListGenero()) {
                listGeneroGenero.getListAlbum().add(album);
                listGeneroGenero = em.merge(listGeneroGenero);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlbum(album.getNombre()) != null) {
                throw new PreexistingEntityException("Album " + album + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Album album) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album persistentAlbum = em.find(Album.class, album.getNombre());
            List<Genero> listGeneroOld = persistentAlbum.getListGenero();
            List<Genero> listGeneroNew = album.getListGenero();
            List<Genero> attachedListGeneroNew = new ArrayList<Genero>();
            for (Genero listGeneroNewGeneroToAttach : listGeneroNew) {
                listGeneroNewGeneroToAttach = em.getReference(listGeneroNewGeneroToAttach.getClass(), listGeneroNewGeneroToAttach.getNombre());
                attachedListGeneroNew.add(listGeneroNewGeneroToAttach);
            }
            listGeneroNew = attachedListGeneroNew;
            album.setListGenero(listGeneroNew);
            album = em.merge(album);
            for (Genero listGeneroOldGenero : listGeneroOld) {
                if (!listGeneroNew.contains(listGeneroOldGenero)) {
                    listGeneroOldGenero.getListAlbum().remove(album);
                    listGeneroOldGenero = em.merge(listGeneroOldGenero);
                }
            }
            for (Genero listGeneroNewGenero : listGeneroNew) {
                if (!listGeneroOld.contains(listGeneroNewGenero)) {
                    listGeneroNewGenero.getListAlbum().add(album);
                    listGeneroNewGenero = em.merge(listGeneroNewGenero);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = album.getNombre();
                if (findAlbum(id) == null) {
                    throw new NonexistentEntityException("The album with id " + id + " no longer exists.");
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
            Album album;
            try {
                album = em.getReference(Album.class, id);
                album.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The album with id " + id + " no longer exists.", enfe);
            }
            List<Genero> listGenero = album.getListGenero();
            for (Genero listGeneroGenero : listGenero) {
                listGeneroGenero.getListAlbum().remove(album);
                listGeneroGenero = em.merge(listGeneroGenero);
            }
            em.remove(album);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Album> findAlbumEntities() {
        return findAlbumEntities(true, -1, -1);
    }

    public List<Album> findAlbumEntities(int maxResults, int firstResult) {
        return findAlbumEntities(false, maxResults, firstResult);
    }

    private List<Album> findAlbumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Album.class));
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

    public Album findAlbum(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlbumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Album> rt = cq.from(Album.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
