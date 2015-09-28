/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.dto.facturacion;

/**
 *
 * @author nicolas
 */
public class Reservacion {
    
    private String rvha_rvha;
    //Datos de la habitacion
    private String rvha_dsha;
    private String numHabitacion;
    //Fecha en la cual se hizo la reservacion
    private String rvha_fecha;
    //Fechas de la reservacion
    private String rvha_fecha_ini;
    private String rvha_fecha_fin;
    private String rvha_fecha_venci;
    private String rvha_confirmada;
    private String rvha_estado;
    private String rvha_tius;

    public String getRvha_rvha() {
        return rvha_rvha;
    }

    public void setRvha_rvha(String rvha_rvha) {
        this.rvha_rvha = rvha_rvha;
    }

    public String getRvha_dsha() {
        return rvha_dsha;
    }

    public void setRvha_dsha(String rvha_dsha) {
        this.rvha_dsha = rvha_dsha;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getRvha_fecha() {
        return rvha_fecha;
    }

    public void setRvha_fecha(String rvha_fecha) {
        this.rvha_fecha = rvha_fecha;
    }

    public String getRvha_fecha_ini() {
        return rvha_fecha_ini;
    }

    public void setRvha_fecha_ini(String rvha_fecha_ini) {
        this.rvha_fecha_ini = rvha_fecha_ini;
    }

    public String getRvha_fecha_fin() {
        return rvha_fecha_fin;
    }

    public void setRvha_fecha_fin(String rvha_fecha_fin) {
        this.rvha_fecha_fin = rvha_fecha_fin;
    }

    public String getRvha_fecha_venci() {
        return rvha_fecha_venci;
    }

    public void setRvha_fecha_venci(String rvha_fecha_venci) {
        this.rvha_fecha_venci = rvha_fecha_venci;
    }

    public String getRvha_confirmada() {
        return rvha_confirmada;
    }

    public void setRvha_confirmada(String rvha_confirmada) {
        this.rvha_confirmada = rvha_confirmada;
    }

    public String getRvha_estado() {
        return rvha_estado;
    }

    public void setRvha_estado(String rvha_estado) {
        this.rvha_estado = rvha_estado;
    }

    public String getRvha_tius() {
        return rvha_tius;
    }

    public void setRvha_tius(String rvha_tius) {
        this.rvha_tius = rvha_tius;
    }
}
