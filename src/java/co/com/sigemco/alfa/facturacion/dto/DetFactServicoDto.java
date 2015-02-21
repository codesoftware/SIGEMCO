/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.dto;

/**
 *
 * @author Nicolas
 */
public class DetFactServicoDto {

    private String num_hab;
    private String num_max_pers;
    private String precioUnidad;
    private String precioTotal;
    private String ivaUnidad;
    private String ivaTotal;
    private String numPersonas;
    private String numDias;

    public String getNum_hab() {
        return num_hab;
    }

    public void setNum_hab(String num_hab) {
        this.num_hab = num_hab;
    }

    public String getNum_max_pers() {
        return num_max_pers;
    }

    public void setNum_max_pers(String num_max_pers) {
        this.num_max_pers = num_max_pers;
    }

    public String getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(String precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getIvaUnidad() {
        return ivaUnidad;
    }

    public void setIvaUnidad(String ivaUnidad) {
        this.ivaUnidad = ivaUnidad;
    }

    public String getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(String ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public String getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(String numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getNumDias() {
        return numDias;
    }

    public void setNumDias(String numDias) {
        this.numDias = numDias;
    }

}
