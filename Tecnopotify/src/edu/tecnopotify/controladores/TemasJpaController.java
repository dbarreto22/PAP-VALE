/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Temas;
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
public class TemasJpaController implements Serializable {

    public TemasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Temas temas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemas(temas.getNombre()) != null) {
                throw new PreexistingEntityException("Temas " + temas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Temas temas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temas = em.merge(temas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = temas.getNombre();
                if (findTemas(id) == null) {
                    throw new NonexistentEntityException("The temas with id " + id + " no longer exists.");
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
            Temas temas;
            try {
                temas = em.getReference(Temas.class, id);
                temas.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temas with id " + id + " no longer exists.", enfe);
            }
            em.remove(temas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Temas> findTemasEntities() {
        return findTemasEntities(true, -1, -1);
    }

    public List<Temas> findTemasEntities(int maxResults, int firstResult) {
        return findTemasEntities(false, maxResults, firstResult);
    }

    private List<Temas> findTemasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temas.class));
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

    public Temas findTemas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temas> rt = cq.from(Temas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
