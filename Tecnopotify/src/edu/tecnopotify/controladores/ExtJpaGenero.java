/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Genero;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Carlox
 */
public class ExtJpaGenero extends GeneroJpaController{
    
    public ExtJpaGenero(EntityManagerFactory emf) {
        super(emf);
    }
    
    public void agregarHijo(Genero padre, Genero hijo) throws PreexistingEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Genero> attachedGenerosHijos = padre.getListHijos();
            attachedGenerosHijos.add(hijo);
            padre.setListHijos(attachedGenerosHijos);
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
