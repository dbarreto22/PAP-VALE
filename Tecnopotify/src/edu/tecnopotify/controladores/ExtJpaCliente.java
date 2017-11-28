/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author root
 */
public class ExtJpaCliente extends ClienteJpaController {
    
    public ExtJpaCliente(EntityManagerFactory emf) {
        super(emf);
    }
    
    public void editImagen(Cliente cli) throws PreexistingEntityException{
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
    
    
}
