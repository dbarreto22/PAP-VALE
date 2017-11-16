
package edu.tecnopotify.datatypes;

import java.io.Serializable;


public class dataFecha implements Serializable{
    
    private int dia;
    private int mes;
    private int anio;

    public dataFecha() {
    }

    public dataFecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return dia + "/" +
                mes + "/" +
                anio;           
    }

   

    
       
}