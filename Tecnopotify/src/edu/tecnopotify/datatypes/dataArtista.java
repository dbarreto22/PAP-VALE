package edu.tecnopotify.datatypes;



import edu.tecnopotify.entidades.*;
import java.util.Collection;
import java.util.List;
;


public class dataArtista extends dataUsuario{
    
  
    private String biografia;
    private String link;
    private List<Album> listAlbum;


    
    public dataArtista(String biografia, String link, List<Album> listAlbum, String nickname,
            String nombre, String apellido, String mail, dataFecha f_nac, String imagen,String contrasenia,List<Usuario> lseguidores, List<Usuario> lseguidos ) {
        super(nickname, nombre, apellido, mail, f_nac, imagen,contrasenia,lseguidos);
        this.biografia = biografia;
        this.link = link;
        this.listAlbum = listAlbum;
    }

    public dataArtista(String biografia, String link,String nickname,
            String nombre, String apellido, String mail, dataFecha f_nac,String contrasenia, String imagen) {
        super(nickname, nombre, apellido, mail, f_nac, imagen,contrasenia);
        this.biografia = biografia;
        this.link = link;
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

    public List<Album> getListDiscos() {
        return listAlbum;
    }

    public void setListDiscos(List<Album> listAlbum) {
        this.listAlbum = listAlbum;
    }
    
    
    
}
