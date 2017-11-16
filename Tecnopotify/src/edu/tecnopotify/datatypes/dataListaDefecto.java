package edu.tecnopotify.datatypes;

import edu.tecnopotify.entidades.*;



public class dataListaDefecto extends ListaReproduccion{
    
    private String genero;

    public dataListaDefecto(String genero, dataListaReproduccion listaRep) {
        super(listaRep);
        this.genero = genero;
    }
    /*
    public dataListaDefecto(String nombre, String imagen, Genero genero) {
        super(nombre, imagen);
    }

    public dataListaDefecto(Genero genero, String nombre, String imagen) {
        super(nombre, imagen);
        this.genero = genero;
    }*/

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
