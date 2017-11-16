package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataGenero;
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
import javax.persistence.OneToMany;

@Entity
public class Genero implements Serializable{
    
    //private static final long serialVersionUID = 1L;
    @Id
    private String nombre;

    private String padre;

    
    @OneToMany
    private Map<String, ListaDefecto> listasReprGenero = new HashMap<String, ListaDefecto>();
 
    @OneToMany
    private List<Genero> listHijos=null;
  
    @ManyToMany
    private List<Album> listAlbum;
    
    
    public Genero(dataGenero genero) {
        this.nombre = genero.getNombre();
        this.padre = genero.getPadre();
        this.listHijos=new ArrayList<Genero>();
        this.listAlbum=new ArrayList<Album>();
    }

    public Genero() {
        
    }

    public Map<String, ListaDefecto> getListasReprGenero() {
        return listasReprGenero;
    }

    public void setListasReprGenero(Map<String, ListaDefecto> listasReprGenero) {
        this.listasReprGenero = listasReprGenero;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }
    
    public List<Album> getListAlbum() {
        return listAlbum;
    }

    public void setListAlbum(List<Album> aux) {
        this.listAlbum = aux;
    }
    public List<Genero> getListHijos() {
        return listHijos;
    }

    public void setListHijos(List<Genero> listHijos) {
        this.listHijos = listHijos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 37 * hash + (this.padre != null ? this.padre.hashCode() : 0);
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
        final Genero other = (Genero) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.padre == null) ? (other.padre != null) : !this.padre.equals(other.padre)) {
            return false;
        }
        return true;
    }

    public dataGenero toData(){
        dataGenero retorno = null;
        retorno.setNombre(this.getNombre());
        retorno.setPadre(this.getPadre());
        retorno.setListasReprGenero((List<ListaDefecto>) this.getListasReprGenero());
        retorno.setGenerosHijos(this.getListHijos());
        retorno.setGenerosHijos(this.getListHijos());
        return retorno;
    }    
    
}
