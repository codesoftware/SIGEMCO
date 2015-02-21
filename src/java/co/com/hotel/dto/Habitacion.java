/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.dto;

import java.math.BigDecimal;

/**
 *
 * @author SOFIA
 */
public class Habitacion {

    private String idHabitacion;
    private String numHabi = "";
    private String fechaIngreso;
    private int numMaxPers;
    private int numMinPers;
    private String bano; //servicio de baño
    private String television;
    private String cable;
    private int numCamas;
    private String camaAux; //Cama Auxiliar
    private boolean reservo;
    private String estado;
    private BigDecimal precio;
    private String iva;

    public String getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumHabi() {
        return numHabi;
    }

    public void setNumHabi(String numHabi) {
        this.numHabi = numHabi;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getNumMaxPers() {
        return numMaxPers;
    }

    public void setNumMaxPers(int numMaxPers) {
        this.numMaxPers = numMaxPers;
    }

    public int getNumMinPers() {
        return numMinPers;
    }

    public void setNumMinPers(int numMinPers) {
        this.numMinPers = numMinPers;
    }

    public String getBano() {
        return bano;
    }

    public void setBano(String bano) {
        this.bano = bano;
    }

    public String getTelevision() {
        return television;
    }

    public void setTelevision(String television) {
        this.television = television;
    }

    public String getCable() {
        return cable;
    }

    public void setCable(String cable) {
        this.cable = cable;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public String getCamaAux() {
        return camaAux;
    }

    public void setCamaAux(String camaAux) {
        this.camaAux = camaAux;
    }

    public boolean isReservo() {
        return reservo;
    }

    public void setReservo(boolean reservo) {
        this.reservo = reservo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void reset() {
        idHabitacion = "";
        numHabi = "";
        fechaIngreso = "";
        numMaxPers = 0;
        numMinPers = 0;
        bano = "";
        television = "";
        cable = "";
        numCamas = 0;
        camaAux = "";
        iva = "";
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    
}
