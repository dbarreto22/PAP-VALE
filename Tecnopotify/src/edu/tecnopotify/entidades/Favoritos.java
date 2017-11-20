/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Favoritos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @XmlTransient
    @OneToOne
    private Cliente cliente;
    @ManyToMany
    private List<Temas> listTemas;
    @ManyToMany
    private List<Album> listAlbum;
    @ManyToMany
    private List<ListaReproduccion> listRep;

    public Favoritos() {
        this.listTemas = new ArrayList<Temas>();
        this.listAlbum = new ArrayList<Album>();
        this.listRep = new ArrayList<ListaReproduccion>();
    }

    public Favoritos(Cliente cliente) {
        this.cliente = cliente;
        this.listTemas = new ArrayList<Temas>();
        this.listAlbum = new ArrayList<Album>();
        this.listRep = new ArrayList<ListaReproduccion>();
    }

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Temas> getListTemas() {
        return listTemas;
    }

    public void setListTemas(List<Temas> listTemas) {
        this.listTemas = listTemas;
    }

    public List<Album> getListAlbum() {
        return listAlbum;
    }

    public void setListAlbum(List<Album> listAlbum) {
        this.listAlbum = listAlbum;
    }

    public List<ListaReproduccion> getListRep() {
        return listRep;
    }

    public void setListRep(List<ListaReproduccion> listRep) {
        this.listRep = listRep;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Favoritos other = (Favoritos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.cliente != other.cliente && (this.cliente == null || !this.cliente.equals(other.cliente))) {
            return false;
        }
        if (this.listTemas != other.listTemas && (this.listTemas == null || !this.listTemas.equals(other.listTemas))) {
            return false;
        }
        if (this.listAlbum != other.listAlbum && (this.listAlbum == null || !this.listAlbum.equals(other.listAlbum))) {
            return false;
        }
        if (this.listRep != other.listRep && (this.listRep == null || !this.listRep.equals(other.listRep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.tecnopotify.entidades.Favoritos[ id=" + id + " ]";
    }

}
