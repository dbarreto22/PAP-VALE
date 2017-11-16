package edu.tecnopotify.datatypes;


import edu.tecnopotify.entidades.*;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlSeeAlso({edu.tecnopotify.datatypes.dataListaDefecto.class,edu.tecnopotify.datatypes.dataListaParticular.class})
public class dataListaReproduccion{
    
    private String nombre;
    private String imagen;
    private List<Temas> listaTemas;

    
    public dataListaReproduccion(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    //    this.listaTemas = listaTemas;
    }
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Temas> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Temas> listaTemas) {
        this.listaTemas = listaTemas;
    }



    
    
}
