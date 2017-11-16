package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataListaReproduccion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ListaDefecto extends ListaReproduccion implements Serializable{
    
    private Genero genero;

    public ListaDefecto() {
        super();
    }
     public ListaDefecto(dataListaReproduccion listaRep) {
        super(listaRep);
    }

    public ListaDefecto(Genero genero, dataListaReproduccion listaRep) {
        super(listaRep);
        this.genero = genero;
    }
    
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.genero != null ? this.genero.hashCode() : 0);
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
        final ListaDefecto other = (ListaDefecto) obj;
        if (this.genero != other.genero && (this.genero == null || !this.genero.equals(other.genero))) {
            return false;
        }
        return true;
    }
    
    
}
