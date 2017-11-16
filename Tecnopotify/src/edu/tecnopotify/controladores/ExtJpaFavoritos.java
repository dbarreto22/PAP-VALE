/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.Favoritos;
import edu.tecnopotify.entidades.ListaReproduccion;
import edu.tecnopotify.entidades.Temas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Carlox
 */
public class ExtJpaFavoritos extends FavoritosJpaController {
    
    public ExtJpaFavoritos(EntityManagerFactory emf) {
        super(emf);
    }
    
    public void agregarTemaFav(Temas objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //Creo un objeto favorito y le asigno el del cliente
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito==null) //Si el cliente no tiene favoritos
            {
               oFavorito=new Favoritos();
               oFavorito.setCliente(oCliente);
               em.merge(oFavorito);
            }else{
            List<Temas> attachedFavoritos = oFavorito.getListTemas();
            attachedFavoritos.add(objeto);//Agrego el tema a la lista de favs
            oFavorito.setListTemas(attachedFavoritos);
            oFavorito.setCliente(oCliente);
            oCliente.setFav(oFavorito);//Guardo los favs en el cliente
            em.merge(oFavorito);
            em.merge(oCliente);//Y le hago merge
            em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
        public void agregarAlbumFav(Album objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito==null)
            {
               oFavorito=new Favoritos();
               oFavorito.setCliente(oCliente);
            }
            List<Album> attachedFavoritos = oFavorito.getListAlbum();
            attachedFavoritos.add(objeto);
            oFavorito.setListAlbum(attachedFavoritos);
            oFavorito.setCliente(oCliente);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
        
    public void agregarListaFav(ListaReproduccion objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito==null)
            {
               oFavorito=new Favoritos();
               oFavorito.setCliente(oCliente);
            }
            List<ListaReproduccion> attachedFavoritos = oFavorito.getListRep();
            attachedFavoritos.add(objeto);
            oFavorito.setListRep(attachedFavoritos);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Cliente " + oCliente.toString() + " da error no se cual.", e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void quitarListaFav(ListaReproduccion objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito!=null)
            {
            List<ListaReproduccion> attachedFavoritos = oFavorito.getListRep();
            attachedFavoritos.remove(objeto);
            oFavorito.setListRep(attachedFavoritos);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);
            em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void quitarTemaFav(Temas objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito!=null)
            {
            List<Temas> attachedFavoritos = oFavorito.getListTemas();
            attachedFavoritos.remove(objeto);
            oFavorito.setListTemas(attachedFavoritos);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);
            em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void quitarAlbumFav(Album objeto, Cliente oCliente) throws PreexistingEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favoritos oFavorito = oCliente.getFav();
            if(oFavorito!=null)
            {
            List<Album> attachedFavoritos = oFavorito.getListAlbum();
            attachedFavoritos.remove(objeto);
            oFavorito.setListAlbum(attachedFavoritos);
            oCliente.setFav(oFavorito);
            em.merge(oCliente);
            em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new PreexistingEntityException("No hay un objeto con ese nombre en" + oCliente.toString(), e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
