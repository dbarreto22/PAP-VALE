/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.datatypes.dataFavoritos;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.Favoritos;
import edu.tecnopotify.entidades.ListaReproduccion;
import edu.tecnopotify.entidades.Temas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Carlox
 */
public class extJpaCliente extends ClienteJpaController {

    public extJpaCliente(EntityManagerFactory emf) {
        super(emf);
    }

    public void agregarTemaFav(Temas objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        /* Query query=em.createQuery("Insert into favoritos_temas values "
        + ""+oCliente.getFav().getId()+","+objeto.getNombre()+"");*/
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // query.executeUpdate();
            //Creo un objeto favorito y le asigno el del cliente
            if (oCliente.getFav() == null) { //Si el cliente no tiene favoritos
                Favoritos oFavorito = new Favoritos();
                oFavorito.setCliente(oCliente);
                oCliente.setFav(oFavorito);
                oCliente.getFav().getListTemas().add(objeto);//Agrego el tema a la lista de favs           
                em.merge(oCliente);//Y le hago merge
                em.merge(oCliente.getFav());
                 em.getTransaction().commit();
 
            }else{
                oCliente.getFav().getListTemas().add(objeto);//Agrego el tema a la lista de favs           
                em.merge(oCliente);//Y le hago merge
                Favoritos f = oCliente.getFav();
                em.merge(f);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void agregarAlbumFav(Album objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if (oCliente.getFav() == null) {
                oFavorito = new Favoritos();
                oFavorito.setCliente(oCliente);
                oCliente.setFav(oFavorito);
            }
            oCliente.getFav().getListAlbum().add(objeto);
            em.merge(oCliente);//Y le hago merge
            em.merge(oCliente.getFav());;
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void agregarListaFav(ListaReproduccion objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if (oFavorito == null) {
                oFavorito = new Favoritos();
                oFavorito.setCliente(oCliente);
            }
            List<ListaReproduccion> attachedFavoritos = oFavorito.getListRep();
            attachedFavoritos.add(objeto);
            oFavorito.setListRep(attachedFavoritos);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);//Y le hago merge
            em.merge(oCliente.getFav());
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void quitarListaFav(ListaReproduccion objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if (oFavorito != null) {
                List<ListaReproduccion> attachedFavoritos = oFavorito.getListRep();
                attachedFavoritos.remove(objeto);
                oFavorito.setListRep(attachedFavoritos);
                oCliente.setFav(oFavorito);
                em.merge(oCliente);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void quitarTemaFav(Temas objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if (oFavorito != null) {
                List<Temas> attachedFavoritos = oFavorito.getListTemas();
                attachedFavoritos.remove(objeto);
                oFavorito.setListTemas(attachedFavoritos);
                oCliente.setFav(oFavorito);
                em.merge(oCliente);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void quitarAlbumFav(Album objeto, Cliente oCliente) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if (oFavorito != null) {
                List<Album> attachedFavoritos = oFavorito.getListAlbum();
                attachedFavoritos.remove(objeto);
                oFavorito.setListAlbum(attachedFavoritos);
                oCliente.setFav(oFavorito);
                em.merge(oCliente);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
