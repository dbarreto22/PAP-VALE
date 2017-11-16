/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Artista;
import edu.tecnopotify.entidades.Genero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego-lucia
 */
public class ExtJpaSrtista extends ArtistaJpaController {
    
    public ExtJpaSrtista(EntityManagerFactory emf) {
        super(emf);
    }
    
        public void agregarAlbum(Artista padre, Album hijo) throws PreexistingEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Album> attachedGenerosHijos = padre.getListAlbum();
            attachedGenerosHijos.add(hijo);
            padre.setListAlbum(attachedGenerosHijos);
            em.merge(padre);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("Artista " + padre + " da error no se cual.", e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
