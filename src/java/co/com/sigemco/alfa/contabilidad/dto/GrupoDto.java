/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dto;

import java.util.List;

/**
 *
 * @author Nicolas
 */
public class GrupoDto {

    private String grup_grup;
    private String grup_clas;
    private String grup_estado;
    private String grup_nombre;
    private String grup_codigo;
    private String grup_descripcion;
    private List<CuentaDto> cuenta;
    private String grup_naturaleza;

    public String getGrup_naturaleza() {
        return grup_naturaleza;
    }

    public void setGrup_naturaleza(String grup_naturaleza) {
        this.grup_naturaleza = grup_naturaleza;
    }

    public String getGrup_grup() {
        return grup_grup;
    }

    public void setGrup_grup(String grup_grup) {
        this.grup_grup = grup_grup;
    }

    public String getGrup_clas() {
        return grup_clas;
    }

    public void setGrup_clas(String grup_clas) {
        this.grup_clas = grup_clas;
    }

    public String getGrup_estado() {
        return grup_estado;
    }

    public void setGrup_estado(String grup_estado) {
        this.grup_estado = grup_estado;
    }

    public String getGrup_nombre() {
        return grup_nombre;
    }

    public void setGrup_nombre(String grup_nombre) {
        this.grup_nombre = grup_nombre;
    }

    public String getGrup_codigo() {
        return grup_codigo;
    }

    public void setGrup_codigo(String grup_codigo) {
        this.grup_codigo = grup_codigo;
    }

    public String getGrup_descripcion() {
        return grup_descripcion;
    }

    public void setGrup_descripcion(String grup_descripcion) {
        this.grup_descripcion = grup_descripcion;
    }

    public List<CuentaDto> getCuenta() {
        return cuenta;
    }

    public void setCuenta(List<CuentaDto> cuenta) {
        this.cuenta = cuenta;
    }
}
