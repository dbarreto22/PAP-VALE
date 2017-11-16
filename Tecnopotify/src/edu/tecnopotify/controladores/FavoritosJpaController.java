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
import edu.tecnopotify.entidades.Favoritos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class FavoritosJpaController implements Serializable {

    public FavoritosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Favoritos favoritos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = favoritos.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getNickname());
                favoritos.setCliente(cliente);
            }
            em.persist(favoritos);
            if (cliente != null) {
                Favoritos oldFavOfCliente = cliente.getFav();
                if (oldFavOfCliente != null) {
                    oldFavOfCliente.setCliente(null);
                    oldFavOfCliente = em.merge(oldFavOfCliente);
                }
                cliente.setFav(favoritos);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Favoritos favoritos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos persistentFavoritos = em.find(Favoritos.class, favoritos.getId());
            Cliente clienteOld = persistentFavoritos.getCliente();
            Cliente clienteNew = favoritos.getCliente();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getNickname());
                favoritos.setCliente(clienteNew);
            }
            favoritos = em.merge(favoritos);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setFav(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                Favoritos oldFavOfCliente = clienteNew.getFav();
                if (oldFavOfCliente != null) {
                    oldFavOfCliente.setCliente(null);
                    oldFavOfCliente = em.merge(oldFavOfCliente);
                }
                clienteNew.setFav(favoritos);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = favoritos.getId();
                if (findFavoritos(id) == null) {
                    throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.");
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
            Favoritos favoritos;
            try {
                favoritos = em.getReference(Favoritos.class, id);
                favoritos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = favoritos.getCliente();
            if (cliente != null) {
                cliente.setFav(null);
                cliente = em.merge(cliente);
            }
            em.remove(favoritos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Favoritos> findFavoritosEntities() {
        return findFavoritosEntities(true, -1, -1);
    }

    public List<Favoritos> findFavoritosEntities(int maxResults, int firstResult) {
        return findFavoritosEntities(false, maxResults, firstResult);
    }

    private List<Favoritos> findFavoritosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Favoritos.class));
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

    public Favoritos findFavoritos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Favoritos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFavoritosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Favoritos> rt = cq.from(Favoritos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
