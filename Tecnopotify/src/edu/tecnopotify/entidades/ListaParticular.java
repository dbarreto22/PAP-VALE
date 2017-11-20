package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataListaReproduccion;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaParticular extends ListaReproduccion implements Serializable{

    private boolean esPrivada;
    
    @ManyToOne
    @XmlTransient
    private Cliente cliente;

    public ListaParticular(boolean esPrivada, Cliente cliente, 
            dataListaReproduccion listaRep) {
        super(listaRep);
        this.esPrivada = esPrivada;
        this.cliente = cliente;
    }

    public ListaParticular() {
        super();
    }

    public boolean isEsPrivada() {
        return esPrivada;
    }

    public void setEsPrivada(boolean esPrivada) {
        this.esPrivada = esPrivada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.cliente != null ? this.cliente.hashCode() : 0);
        return hash;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaParticular other = (ListaParticular) obj;
        if (this.cliente != other.cliente && (this.cliente == null || !this.cliente.equals(other.cliente))) {
            return false;
        }
        return true;
    }
    
    
  
}