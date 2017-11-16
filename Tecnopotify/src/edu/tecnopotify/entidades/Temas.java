package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataTemas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Temas implements Serializable {
    
//    private static final long serialVersionUID = 1L;
    
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String nombre;

    private String duracion;
 
    private int posicion;

    private String archivo;
    

    
    @XmlTransient
    @ManyToMany
    public Map<String, ListaReproduccion> listaR = new HashMap<String,ListaReproduccion>();
    
    
    @XmlTransient
    @ManyToMany
    private List<Favoritos> listFavoritos = new  ArrayList<Favoritos>();
    
    @XmlTransient
    public Map<String, ListaReproduccion> getListaR() {
        return listaR;
    }

    public void setListaR(Map<String, ListaReproduccion> listaR) {
        this.listaR = listaR;
    }
    
    public Temas() {
    }

    public Temas(dataTemas tema) {
        this.nombre = tema.getNombre();
        this.duracion = tema.getDuracion();
        this.posicion = tema.getPosicion();
        this.archivo = tema.getArchivo();
      
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
/*
    public Long getSerialVersioUID() {
        return serialVersionUID;
    }
    */

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
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
        final Temas other = (Temas) obj;
        if (this.posicion != other.posicion) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.duracion == null) ? (other.duracion != null) : !this.duracion.equals(other.duracion)) {
            return false;
        }
        if ((this.archivo == null) ? (other.archivo != null) : !this.archivo.equals(other.archivo)) {
            return false;
        }
        if (this.listaR != other.listaR && (this.listaR == null || !this.listaR.equals(other.listaR))) {
            return false;
        }
        if (this.listFavoritos != other.listFavoritos && (this.listFavoritos == null || !this.listFavoritos.equals(other.listFavoritos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Temas{" + "nombre=" + nombre + ", duracion=" + duracion + ", posicion=" + posicion + ", archivo=" + archivo + '}';
    }
    
    
    
    
    
}
