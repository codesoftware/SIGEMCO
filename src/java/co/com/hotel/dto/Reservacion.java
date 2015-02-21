/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.dto;

/**
 *
 * @author nicolas
 */
public class Reservacion {
    
    private String idReserva;
    private String idDsha; //Identificador de la habitacion reservada
    private String idClien; //Identificador del cliente el cual reservo la habitacion
    private String nomClien; //Nombre del cliente el cual realizo la reservación
    private String fechaReser; // Fecha en la cual se hizo la reserva
    private String fechaIni; // Fecha en la cual se iniciara la reserva
    private String fechaFin; // Fecha en la cual se realizara la reserva
    private String fechaVenci; // Fecha en la cual se vencera la reserva
    private String numDias; // numero de dias de la reservacion
    private String confirma; //Identifica si la reservacion ya fue confirmada
    private String estado; // Estado en el cual se encuentra la reserva
    private String idTius; // Identificador del usuario que realizo la reservacion
    private String nomTius; // Nombre del usuario que realizo la reservación

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdDsha() {
        return idDsha;
    }

    public void setIdDsha(String idDsha) {
        this.idDsha = idDsha;
    }

    public String getIdClien() {
        return idClien;
    }

    public void setIdClien(String idClien) {
        this.idClien = idClien;
    }

    public String getNomClien() {
        return nomClien;
    }

    public void setNomClien(String nomClien) {
        this.nomClien = nomClien;
    }

    public String getFechaReser() {
        return fechaReser;
    }

    public void setFechaReser(String fechaReser) {
        this.fechaReser = fechaReser;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNumDias() {
        return numDias;
    }

    public void setNumDias(String numDias) {
        this.numDias = numDias;
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdTius() {
        return idTius;
    }

    public void setIdTius(String idTius) {
        this.idTius = idTius;
    }

    public String getNomTius() {
        return nomTius;
    }

    public void setNomTius(String nomTius) {
        this.nomTius = nomTius;
    }

    public String getFechaVenci() {
        return fechaVenci;
    }

    public void setFechaVenci(String fechaVenci) {
        this.fechaVenci = fechaVenci;
    }
}
