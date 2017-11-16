package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataUsuario;
import edu.tecnopotify.datatypes.dataFecha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

public class Usuario implements Serializable {

    @Id
    private String nickname;
    private String nombre;
    private String apellido;
    private String mail;
    private dataFecha f_nac;
    private String imagen;
    private String contrasenia;
    
    @ManyToMany
    private List<Usuario> lstSeguidos;

    public Usuario(dataUsuario usuario) {
        this.nickname = usuario.getNickname();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.mail = usuario.getMail();
        this.f_nac = usuario.getF_nac();
        this.imagen = usuario.getImagen();
        this.contrasenia = usuario.getContrasenia();
        this.lstSeguidos = new ArrayList<Usuario>();
    }

    public Usuario() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public dataFecha getF_nac() {
        return f_nac;
    }

    public void setF_nac(dataFecha f_nac) {
        this.f_nac = f_nac;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Usuario> getLstSeguidos() {
        return lstSeguidos;
    }

    public void setLstSeguidos(List<Usuario> lstSeguidos) {
        this.lstSeguidos = lstSeguidos;
    }

    public void addToSeguidos(Usuario usr) {
        this.lstSeguidos.add(usr);
    }

    public void removeFromSeguidos(Usuario usr) {
        this.lstSeguidos.remove(usr);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.nickname != null ? this.nickname.hashCode() : 0);
        hash = 59 * hash + (this.contrasenia != null ? this.contrasenia.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if ((this.nickname == null) ? (other.nickname != null) : !this.nickname.equals(other.nickname)) {
            return false;
        }
        if ((this.contrasenia == null) ? (other.contrasenia != null) : !this.contrasenia.equals(other.contrasenia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Nickname=" + nickname
                + "\n" + ", Nombre Completo=" + nombre + " "
                + apellido + "\n" + " Mail=" + mail + "\n"
                + " Fecha Nacimiento=" + f_nac.getDia() + "/" + f_nac.getMes() + "/" + f_nac.getAnio()
                + '}';
    }

}
