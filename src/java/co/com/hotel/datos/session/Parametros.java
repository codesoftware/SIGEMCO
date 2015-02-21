/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.datos.session;

/**
 *
 * @author SOFIA
 */
public class Parametros {
    private String fechaHoy;
    private String fechaManana;
    private String fechaAyer;
    private String fechaUnMesAtras;
    private String RutaSitio;

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getFechaManana() {
        return fechaManana;
    }

    public void setFechaManana(String fechaManana) {
        this.fechaManana = fechaManana;
    }

    public String getFechaAyer() {
        return fechaAyer;
    }

    public void setFechaAyer(String fechaAyer) {
        this.fechaAyer = fechaAyer;
    }

    public String getFechaUnMesAtras() {
        return fechaUnMesAtras;
    }

    public void setFechaUnMesAtras(String fechaUnMesAtras) {
        this.fechaUnMesAtras = fechaUnMesAtras;
    }

    public String getRutaSitio() {
        return RutaSitio;
    }

    public void setRutaSitio(String RutaSitio) {
        this.RutaSitio = RutaSitio;
    }
}
