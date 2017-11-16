package edu.tecnopotify.entidades;

import edu.tecnopotify.controladores.SuscripcionJpaController;
import edu.tecnopotify.datatypes.dataCliente;
import edu.tecnopotify.datatypes.dataUsuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente extends Usuario implements Serializable {

    @OneToMany
//    @XmlTransient
    private List<ListaParticular> listasReprParticular;
    /* @OneToMany
    public List<Usuario> seguidos;*/

    @OneToOne
    @XmlTransient
    private Favoritos fav;

    @OneToOne
    @XmlTransient
    private Suscripcion suscripcion;

    public Cliente(dataUsuario usuario) {
        super(usuario);
        this.fav = null;
        this.suscripcion = null;
    }

    public Cliente() {
        super();
    }

    public void setSuscripcion(Suscripcion susc) {
        this.suscripcion = susc;
    }

    public Suscripcion getSuscripcion() {
        return this.suscripcion;
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
    public Favoritos getFav() {
        return fav;
    }

    public void setFav(Favoritos fav) {
        this.fav = fav;
    }

}
