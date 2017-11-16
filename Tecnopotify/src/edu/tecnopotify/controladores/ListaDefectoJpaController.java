/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.ListaDefecto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author diego-lucia
 */
public class ListaDefectoJpaController implements Serializable {

    public ListaDefectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaDefecto listaDefecto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaDefecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaDefecto(listaDefecto.getNombre()) != null) {
                throw new PreexistingEntityException("ListaDefecto " + listaDefecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaDefecto listaDefecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaDefecto = em.merge(listaDefecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaDefecto.getNombre();
                if (findListaDefecto(id) == null) {
                    throw new NonexistentEntityException("The listaDefecto with id " + id + " no longer exists.");
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
            ListaDefecto listaDefecto;
            try {
                listaDefecto = em.getReference(ListaDefecto.class, id);
                listaDefecto.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaDefecto with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaDefecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaDefecto> findListaDefectoEntities() {
        return findListaDefectoEntities(true, -1, -1);
    }

    public List<ListaDefecto> findListaDefectoEntities(int maxResults, int firstResult) {
        return findListaDefectoEntities(false, maxResults, firstResult);
    }

    private List<ListaDefecto> findListaDefectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaDefecto.class));
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

    public ListaDefecto findListaDefecto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaDefecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaDefectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaDefecto> rt = cq.from(ListaDefecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
