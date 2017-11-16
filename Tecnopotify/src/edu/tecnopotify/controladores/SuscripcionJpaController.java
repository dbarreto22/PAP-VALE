/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.Suscripcion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class SuscripcionJpaController implements Serializable {

    public SuscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suscripcion suscripcion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente suscripto = suscripcion.getSuscripto();
            if (suscripto != null) {
                suscripto = em.getReference(suscripto.getClass(), suscripto.getNickname());
                suscripcion.setSuscripto(suscripto);
            }
            em.persist(suscripcion);
            if (suscripto != null) {
                Suscripcion oldSuscripcionOfSuscripto = suscripto.getSuscripcion();
                if (oldSuscripcionOfSuscripto != null) {
                    oldSuscripcionOfSuscripto.setSuscripto(null);
                    oldSuscripcionOfSuscripto = em.merge(oldSuscripcionOfSuscripto);
                }
                suscripto.setSuscripcion(suscripcion);
                suscripto = em.merge(suscripto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suscripcion suscripcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suscripcion persistentSuscripcion = em.find(Suscripcion.class, suscripcion.getId());
            Cliente suscriptoOld = persistentSuscripcion.getSuscripto();
            Cliente suscriptoNew = suscripcion.getSuscripto();
            if (suscriptoNew != null) {
                suscriptoNew = em.getReference(suscriptoNew.getClass(), suscriptoNew.getNickname());
                suscripcion.setSuscripto(suscriptoNew);
            }
            suscripcion = em.merge(suscripcion);
            if (suscriptoOld != null && !suscriptoOld.equals(suscriptoNew)) {
                suscriptoOld.setSuscripcion(null);
                suscriptoOld = em.merge(suscriptoOld);
            }
            if (suscriptoNew != null && !suscriptoNew.equals(suscriptoOld)) {
                Suscripcion oldSuscripcionOfSuscripto = suscriptoNew.getSuscripcion();
                if (oldSuscripcionOfSuscripto != null) {
                    oldSuscripcionOfSuscripto.setSuscripto(null);
                    oldSuscripcionOfSuscripto = em.merge(oldSuscripcionOfSuscripto);
                }
                suscriptoNew.setSuscripcion(suscripcion);
                suscriptoNew = em.merge(suscriptoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = suscripcion.getId();
                if (findSuscripcion(id) == null) {
                    throw new NonexistentEntityException("The suscripcion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suscripcion suscripcion;
            try {
                suscripcion = em.getReference(Suscripcion.class, id);
                suscripcion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suscripcion with id " + id + " no longer exists.", enfe);
            }
            Cliente suscripto = suscripcion.getSuscripto();
            if (suscripto != null) {
                suscripto.setSuscripcion(null);
                suscripto = em.merge(suscripto);
            }
            em.remove(suscripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suscripcion> findSuscripcionEntities() {
        return findSuscripcionEntities(true, -1, -1);
    }

    public List<Suscripcion> findSuscripcionEntities(int maxResults, int firstResult) {
        return findSuscripcionEntities(false, maxResults, firstResult);
    }

    private List<Suscripcion> findSuscripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suscripcion.class));
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

    public Suscripcion findSuscripcion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suscripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuscripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suscripcion> rt = cq.from(Suscripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
