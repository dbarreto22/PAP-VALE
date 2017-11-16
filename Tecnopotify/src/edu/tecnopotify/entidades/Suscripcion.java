/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.entidades;

import edu.tecnopotify.datatypes.dataFecha;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author menan
 * El Sistema permite al Cliente contratar una suscripción a la plataforma que habilitará a la
utilización de diversos servicios como ser la descarga de archivos de música de los temas que
integran los álbumes y listas de reproducción ofrecidas. Mediante el pago de una cuota
(semanal, mensual o anual) el Cliente accederá a diversos servicios, pudiendo optar por no
renovar la suscripción en el momento de su vencimiento.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Suscripcion implements Serializable {

    public enum estado {
        PENDIENTE, VENCIDA, VIGENTE, CANCELADA
    }  
    
    public enum pago {
        SUSCRIPCION_PENDIENTE, SEMANAL, MENSUAL, ANUAL
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public dataFecha f_vence;    
    public estado status;
    public pago cuota;
    
    @OneToOne
    Cliente suscripto;
    
    public Suscripcion() {
        status = estado.PENDIENTE;
        cuota = pago.SUSCRIPCION_PENDIENTE;
        f_vence = new dataFecha(31,12,3333);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSuscripto(Cliente suscripto) {
        this.suscripto = suscripto;
    }

    public Long getId() {
        return id;
    }

    public Cliente getSuscripto() {
        return suscripto;
    }

    public estado getStatus() {
        return status;
    }

    public dataFecha getF_vence() {
        return f_vence;
    }

    public void setF_vence(dataFecha f_vence) {
        this.f_vence = f_vence;
    }

    public void setStatus(String est) {
        if (est.equals("PENDIENTE")){
            status = estado.PENDIENTE;
        } else if (est.equals("VENCIDA")){
            status = estado.VENCIDA;
        } else if (est.equals("VIGENTE")){
            status = estado.VIGENTE;
        } else if (est.equals("CANCELADA")){
            status = estado.CANCELADA;
        }
    }

    public pago getCuota() {
        return cuota;
    }

    public void setCuota(pago cuota) {
        this.cuota = cuota;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.f_vence != null ? this.f_vence.hashCode() : 0);
        hash = 73 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 73 * hash + (this.cuota != null ? this.cuota.hashCode() : 0);
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
        final Suscripcion other = (Suscripcion) obj;
        if (this.f_vence != other.f_vence && (this.f_vence == null || !this.f_vence.equals(other.f_vence))) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.cuota != other.cuota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Suscripcion{" + "vencimiento=" + f_vence + ", status=" + status + ", cuota=" + cuota + '}';
    }


    
}
