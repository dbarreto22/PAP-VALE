package edu.tecnopotify.datatypes;

import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.ListaReproduccion;
import edu.tecnopotify.entidades.Temas;
import java.util.ArrayList;
import java.util.List;

public class dataFavoritos {

    private Cliente cliente;
    private List<Temas> listTemas;
    private List<Album> listAlbum;
    private List<ListaReproduccion> listRep;

    public dataFavoritos() {
    }

    public dataFavoritos(Cliente cliente, List<Temas> listTemas, List<Album> listAlbum, List<ListaReproduccion> listRep) {
        this.cliente = cliente;
        this.listTemas = listTemas;
        this.listAlbum = listAlbum;
        this.listRep = listRep;
    }

    public dataFavoritos(Cliente cliente) {
        this.cliente = cliente;
        this.listTemas = new ArrayList<Temas>();
        this.listAlbum = new ArrayList<Album>();
        this.listRep = new ArrayList<ListaReproduccion>();
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

}
