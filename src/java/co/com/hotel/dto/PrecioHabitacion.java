/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.dto;

import java.math.BigDecimal;

/**
 *
 * @author nicolas
 */
public class PrecioHabitacion {
    
    private String prha_prha;
    private String prha_dsha;
    private BigDecimal prha_precio;
    private String prha_tius_crea;
    private String prha_tius_update;
    private String prha_estado;
    private String prha_fecha;

    public String getPrha_prha() {
        return prha_prha;
    }

    public void setPrha_prha(String prha_prha) {
        this.prha_prha = prha_prha;
    }

    public String getPrha_dsha() {
        return prha_dsha;
    }

    public void setPrha_dsha(String prha_dsha) {
        this.prha_dsha = prha_dsha;
    }

    public BigDecimal getPrha_precio() {
        return prha_precio;
    }

    public void setPrha_precio(BigDecimal prha_precio) {
        this.prha_precio = prha_precio;
    }

    public String getPrha_tius_crea() {
        return prha_tius_crea;
    }

    public void setPrha_tius_crea(String prha_tius_crea) {
        this.prha_tius_crea = prha_tius_crea;
    }

    public String getPrha_tius_update() {
        return prha_tius_update;
    }

    public void setPrha_tius_update(String prha_tius_update) {
        this.prha_tius_update = prha_tius_update;
    }

    public String getPrha_estado() {
        return prha_estado;
    }

    public void setPrha_estado(String prha_estado) {
        this.prha_estado = prha_estado;
    }

    public String getPrha_fecha() {
        return prha_fecha;
    }

    public void setPrha_fecha(String prha_fecha) {
        this.prha_fecha = prha_fecha;
    }
}