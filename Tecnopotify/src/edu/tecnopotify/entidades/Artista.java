package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataUsuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Artista extends Usuario implements Serializable {

    private String biografia;
    private String link;
    private Boolean status;
    @OneToMany
    private List<Album> listAlbum;

    public Artista(String biografia, String link,
            dataUsuario usuario) {
        super(usuario);
        this.biografia = biografia;
        this.link = link;
        this.status = true;

    }

    public Artista(String biografia, String link,
            Usuario usuario) {
        super(usuario);
        this.biografia = biografia;
        this.link = link;
        this.status = true;
    }

    public Artista() {
        super();
    }

    public void setListAlbum(List<Album> listAlbum) {
        this.listAlbum = listAlbum;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Album> getListAlbum() {
        return listAlbum;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
