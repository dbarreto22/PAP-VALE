/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Genero;
import edu.tecnopotify.entidades.Temas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Carlox
 */
public class ExtJpaAlbum extends AlbumJpaController{
    
    public ExtJpaAlbum(EntityManagerFactory emf) {
        super(emf);
    }
    
    public void agregarTema(Album padre, Temas tema) throws PreexistingEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Temas> attachedAlbums = padre.getListTemas();
            attachedAlbums.add(tema);
            padre.setListTemas(attachedAlbums);
            em.merge(padre);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Genero " + padre + " no se pudo agregar hijo.", e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }    
}
