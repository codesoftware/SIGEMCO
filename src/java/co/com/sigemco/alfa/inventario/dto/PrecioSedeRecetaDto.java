/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dto;

import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class PrecioSedeRecetaDto {

    private String rece_rece;
    private String sede_sede;
    private String sede_nombre;
    private ArrayList<PrecioRecetaDto> precios;

    public String getRece_rece() {
        return rece_rece;
    }

    public void setRece_rece(String rece_rece) {
        this.rece_rece = rece_rece;
    }

    public String getSede_sede() {
        return sede_sede;
    }

    public void setSede_sede(String sede_sede) {
        this.sede_sede = sede_sede;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public ArrayList<PrecioRecetaDto> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<PrecioRecetaDto> precios) {
        this.precios = precios;
    }

}
