/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.NonexistentEntityException;
import edu.tecnopotify.entidades.RegistroUsuarios;
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
 * @author root
 */
public class RegistroUsuariosJpaController implements Serializable {

    public RegistroUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroUsuarios registroUsuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroUsuarios registroUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroUsuarios = em.merge(registroUsuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = registroUsuarios.getId();
                if (findRegistroUsuarios(id) == null) {
                    throw new NonexistentEntityException("The registroUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroUsuarios registroUsuarios;
            try {
                registroUsuarios = em.getReference(RegistroUsuarios.class, id);
                registroUsuarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroUsuarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroUsuarios> findRegistroUsuariosEntities() {
        return findRegistroUsuariosEntities(true, -1, -1);
    }

    public List<RegistroUsuarios> findRegistroUsuariosEntities(int maxResults, int firstResult) {
        return findRegistroUsuariosEntities(false, maxResults, firstResult);
    }

    private List<RegistroUsuarios> findRegistroUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroUsuarios.class));
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

    public RegistroUsuarios findRegistroUsuarios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroUsuarios> rt = cq.from(RegistroUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
