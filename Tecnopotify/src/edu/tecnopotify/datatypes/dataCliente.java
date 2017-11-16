package edu.tecnopotify.datatypes;

import edu.tecnopotify.entidades.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dataCliente extends dataUsuario {

    public List<ListaParticular> listasReprParticular;
    /* public List<Usuario> seguidos;*/

    public dataCliente(String nickname, String nombre, String apellido, String mail, dataFecha f_nac, String imagen,String contrasenia,
            List<Usuario> lseguidores, List<Usuario> lseguidos) {
        super(nickname, nombre, apellido, mail, f_nac, imagen,contrasenia, lseguidos);
    }
    
    public dataCliente(String nickname, String nombre, String apellido, String mail, dataFecha f_nac,String contrasenia, String imagen) {
        super(nickname, nombre, apellido, mail, f_nac, contrasenia,imagen);
    }

    public dataCliente() {
        super();
    }

    public List<ListaParticular> getLista() {
        return listasReprParticular;
    }

    public List<ListaParticular> getListasReprParticular() {
        return listasReprParticular;
    }

    public void setListasReprParticular(List<ListaParticular> listasReprParticular) {
        this.listasReprParticular = listasReprParticular;
    }

    /*    public List<Usuario> getSeguidos() {
    return seguidos;
    }
    
    public void setSeguidos(List<Usuario> seguidos) {
    this.seguidos = seguidos;
    }*/
}
