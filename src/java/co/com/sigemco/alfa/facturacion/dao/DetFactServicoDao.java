/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.dao;

/**
 *
 * @author Nicolas
 */
public class DetFactServicoDao {

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

    /**
     * Funcion encargada de realizar un query calculando los datos que pagaria
     * una persona par una habitacion en determinada cantidad de dias
     *
     * @return
     */
    public String simulaFacturacionServicio() {
        String sql = "";
        sql += "SELECT dsha_num_hab";
        sql += " , dsha_num_max_pers";
        sql += " , prha_precio";
        sql += " , prha_precio* " + this.getNumDias() + " vlrTotal";
        sql += " , ((dsha_iva*prha_precio)/100) vlr_Iva";
        sql += " , ((dsha_iva*prha_precio)/100) * " + this.getNumDias() + " ivaTotal";
        sql += " , (((dsha_iva*prha_precio)/100)*" + this.getNumDias() + ") + (prha_precio* " + this.getNumDias() + ")  totalPagar\n";
        sql += " FROM in_tdsha, in_tprha\n";
        sql += "WHERE prha_dsha = dsha_dsha\n";
        sql += " AND prha_estado = 'A'\n";
        return sql;
    }

}
