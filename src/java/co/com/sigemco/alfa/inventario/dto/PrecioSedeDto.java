/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dto;

import java.util.List;

/**
 *
 * @author Nicolas
 */
public class PrecioSedeDto {

    private String sede_sede;
    private String sede_nombre;
    private String dska_dska;
    private List<PrecioProductoDto> precios;

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

    public List<PrecioProductoDto> getPrecios() {
        return precios;
    }

    public void setPrecios(List<PrecioProductoDto> precios) {
        this.precios = precios;
    }

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

}
