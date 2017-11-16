/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.controladores;

import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.Temas;
import edu.tecnopotify.entidades.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author diego-lucia
 */
public class ExtUsuario extends UsuarioJpaController {

    public ExtUsuario(EntityManagerFactory emf) {
        super(emf);
    }

    public void agregarSeguidor(Usuario usr, Cliente cli) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedseguidos = cli.getLstSeguidos();
            attachedseguidos.add(usr);
            cli.setLstSeguidos(attachedseguidos);
            em.merge(cli);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("seguidor " + usr.getNombre() + cli.getNombre() + " no se pudo agregar hijo.", e);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void quitarSeguidor(Usuario usr, Cliente cli) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedseguidos = cli.getLstSeguidos();
            attachedseguidos.remove(usr);
            cli.setLstSeguidos(attachedseguidos);
            em.merge(cli);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PreexistingEntityException("seguidor " + usr.getNombre() + cli.getNombre() + " no se pudo agregar hijo.", e);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Usuario buscarUsrMail(String mail) throws PreexistingEntityException, SQLException {
        EntityManager em = null;
        em = getEntityManager();
        Usuario usr = null;
        ResultSet resultado;
        // String id = "SELECT NICKNAME FROM PUBLIC.USUARIO "  + "where mail = "+"' mail'"+",";
        try {
            Query q = em.createNamedQuery("usuario.mail");
            q.setParameter("mail", mail);
            usr = (Usuario) q.getSingleResult();
        } finally {
            em.close();
        }
        return usr;
    }


}
