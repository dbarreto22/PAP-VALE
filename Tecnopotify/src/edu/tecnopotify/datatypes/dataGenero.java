package edu.tecnopotify.datatypes;


import edu.tecnopotify.entidades.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class dataGenero{

    private String nombre;
    private String padre;
    @XmlTransient
    public List listasReprGenero;
    @XmlTransient
    private List<Genero> generosHijos;
    
    public dataGenero(String nombre, String Padre) {
        this.nombre = nombre;
        this.padre = Padre;
    }

    public dataGenero() {
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

    public List<ListaDefecto> getListasReprGenero() {
        return listasReprGenero;
    }

    public void setListasReprGenero(List<ListaDefecto> listasReprGenero) {
        this.listasReprGenero = listasReprGenero;
    }

    public List<Genero> getGenerosHijos() {
        return generosHijos;
    }

    public void setGenerosHijos(List<Genero> generosHijos) {
        this.generosHijos = generosHijos;
    }  
}
