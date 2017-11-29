/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Artista;
import edu.tecnopotify.entidades.Cliente;
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

    public void editImagen(Artista cli) throws PreexistingEntityException, PreexistingEntityException, PreexistingEntityException, PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(cli);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("seguidor " + cli.getNombre() + cli.getNombre() + " no se pudo agregar hijo.", e);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void agregarAlbum(Artista padre, Album hijo) throws PreexistingEntityException {
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
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
