package edu.tecnopotify.datatypes;

import edu.tecnopotify.entidades.*;
import java.io.Serializable;



public class dataListaParticular extends dataListaReproduccion {
    
 
    private boolean esPrivada;
    private String nickCliente;

    public dataListaParticular(boolean esPrivada, String nickCliente, 
            dataListaReproduccion listaRep) {
        super(listaRep.getNombre(),listaRep.getImagen());
        this.esPrivada = esPrivada;
        this.nickCliente = nickCliente;
    }
/*
    public dataListaParticular(boolean esPrivada, Cliente cliente, 
            String nombre, String imagen) {
        super(nombre, imagen);
        this.esPrivada = esPrivada;
        this.cliente = cliente;
    }

    public dataListaParticular(String nombre, String imagen) {
        super(nombre, imagen);
        esPrivada = false;
    }

    public dataListaParticular(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    public boolean isEsPrivada() {
        return esPrivada;
    }

    public void setEsPrivada(boolean esPrivada) {
        this.esPrivada = esPrivada;
    }

    public String getCliente() {
        return nickCliente;
    }

    public void setCliente(String nickCliente) {
        this.nickCliente = nickCliente;
    }

    

    
    
}