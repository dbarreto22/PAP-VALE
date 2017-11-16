package edu.tecnopotify.datatypes;

import edu.tecnopotify.entidades.*;
import java.util.ArrayList;
import java.util.List;

public class dataAlbum {

    private String nombre;
    private int anioCreado;
    private String imagenAlbum;
    private List<Temas> listTemas;
    private List<Genero> listGeneros;
    private List<dataGenero> listdataGeneros;

    public dataAlbum(String nombre, int anioCreado, String imagenAlbum) {
        this.nombre = nombre;
        this.anioCreado = anioCreado;
        this.imagenAlbum = imagenAlbum;
        this.listGeneros = new ArrayList<Genero>();                 

    }

    public dataAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioCreado() {
        return anioCreado;
    }

    public void setAnioCreado(int anioCreado) {
        this.anioCreado = anioCreado;
    }

    public String getImagenAlbum() {
        return imagenAlbum;
    }

    public void setImagenAlbum(String imagenAlbum) {
        this.imagenAlbum = imagenAlbum;
    }

    public List<Temas> getListTemas() {
        return listTemas;
    }

    public void setListTemas(List<Temas> listTemas) {
        this.listTemas = listTemas;
    }

    public List<Genero> getListGeneros() {
        return listGeneros;
    }

    public void setListGeneros(List<Genero> listGeneros) {
        this.listGeneros = listGeneros;
    }

    public List<dataGenero> getListdataGeneros() {
        return listdataGeneros;
    }

    public void setListdataGeneros(List<dataGenero> listdataGeneros) {
        this.listdataGeneros = listdataGeneros;
    }
    
    
    
    
}
