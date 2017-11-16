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
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.ListaParticular;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class ListaParticularJpaController implements Serializable {

    public ListaParticularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaParticular listaParticular) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = listaParticular.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getNickname());
                listaParticular.setCliente(cliente);
            }
            em.persist(listaParticular);
            if (cliente != null) {
                cliente.getListasReprParticular().add(listaParticular);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaParticular(listaParticular.getNombre()) != null) {
                throw new PreexistingEntityException("ListaParticular " + listaParticular + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaParticular listaParticular) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaParticular persistentListaParticular = em.find(ListaParticular.class, listaParticular.getNombre());
            Cliente clienteOld = persistentListaParticular.getCliente();
            Cliente clienteNew = listaParticular.getCliente();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getNickname());
                listaParticular.setCliente(clienteNew);
            }
            listaParticular = em.merge(listaParticular);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListasReprParticular().remove(listaParticular);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListasReprParticular().add(listaParticular);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaParticular.getNombre();
                if (findListaParticular(id) == null) {
                    throw new NonexistentEntityException("The listaParticular with id " + id + " no longer exists.");
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
            ListaParticular listaParticular;
            try {
                listaParticular = em.getReference(ListaParticular.class, id);
                listaParticular.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaParticular with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = listaParticular.getCliente();
            if (cliente != null) {
                cliente.getListasReprParticular().remove(listaParticular);
                cliente = em.merge(cliente);
            }
            em.remove(listaParticular);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaParticular> findListaParticularEntities() {
        return findListaParticularEntities(true, -1, -1);
    }

    public List<ListaParticular> findListaParticularEntities(int maxResults, int firstResult) {
        return findListaParticularEntities(false, maxResults, firstResult);
    }

    private List<ListaParticular> findListaParticularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaParticular.class));
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

    public ListaParticular findListaParticular(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaParticular.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaParticularCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaParticular> rt = cq.from(ListaParticular.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
