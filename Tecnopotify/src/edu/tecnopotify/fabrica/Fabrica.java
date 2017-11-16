/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.fabrica;

import edu.tecnopotify.interfaces.Controlador;
import edu.tecnopotify.interfaces.IControlador;

/**
 *
 * @author Carlox
 */
public class Fabrica {
    private static Fabrica Instancia;
    private Fabrica() {
    }
    
    public static Fabrica getInstance() {
        if(Instancia==null)
        {
            Instancia=new Fabrica();
        }
        return Instancia;
    }
    
    public IControlador getInstancia()
    {
        IControlador in = new Controlador();
        return in;
    }
}
