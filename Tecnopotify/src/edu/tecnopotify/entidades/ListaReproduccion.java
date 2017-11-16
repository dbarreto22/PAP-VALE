package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataListaReproduccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaReproduccion implements Serializable{
    /*
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @Id 
    private String nombre;
   
    private String imagen;
    
    @ManyToMany
    private List<Temas> listaTemas ;
    
    @ManyToMany
    @XmlTransient
    private List<Favoritos> listFavoritos = new ArrayList<Favoritos>();

    
    
    public ListaReproduccion(dataListaReproduccion listaRep) {
        this.nombre = listaRep.getNombre();
        this.imagen = listaRep.getImagen();
        this.listaTemas = new ArrayList<Temas>();
    }

    public ListaReproduccion() {
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }   
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Temas> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Temas> listaTemas) {
        this.listaTemas = listaTemas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
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
        final ListaReproduccion other = (ListaReproduccion) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if (this.listaTemas != other.listaTemas && (this.listaTemas == null || !this.listaTemas.equals(other.listaTemas))) {
            return false;
        }
        if (this.listFavoritos != other.listFavoritos && (this.listFavoritos == null || !this.listFavoritos.equals(other.listFavoritos))) {
            return false;
        }
        return true;
    }


    
    public dataListaReproduccion toDataType()
    {
        dataListaReproduccion retorno=new dataListaReproduccion(this.nombre, this.imagen);
        return retorno;
    }
}
