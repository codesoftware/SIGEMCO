/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author Nicolas
 */
public class MoviInventarioDao {

    private String mvin_mvin;
    private String mvin_descr;
    private String mvin_natu;
    private String mvin_usim;
    private String mvin_venta;
    private String mvin_inicial;
    private String mvin_revfact;
    private String mvin_compra;
    private String mvin_perdida;

    public String getMvin_mvin() {
        return mvin_mvin;
    }

    public void setMvin_mvin(String mvin_mvin) {
        this.mvin_mvin = mvin_mvin;
    }

    public String getMvin_descr() {
        return mvin_descr;
    }

    public void setMvin_descr(String mvin_descr) {
        this.mvin_descr = mvin_descr;
    }

    public String getMvin_natu() {
        return mvin_natu;
    }

    public void setMvin_natu(String mvin_natu) {
        this.mvin_natu = mvin_natu;
    }

    public String getMvin_usim() {
        return mvin_usim;
    }

    public void setMvin_usim(String mvin_usim) {
        this.mvin_usim = mvin_usim;
    }

    public String getMvin_venta() {
        return mvin_venta;
    }

    public void setMvin_venta(String mvin_venta) {
        this.mvin_venta = mvin_venta;
    }

    public String getMvin_inicial() {
        return mvin_inicial;
    }

    public void setMvin_inicial(String mvin_inicial) {
        this.mvin_inicial = mvin_inicial;
    }

    public String getMvin_revfact() {
        return mvin_revfact;
    }

    public void setMvin_revfact(String mvin_revfact) {
        this.mvin_revfact = mvin_revfact;
    }

    public String getMvin_compra() {
        return mvin_compra;
    }

    public void setMvin_compra(String mvin_compra) {
        this.mvin_compra = mvin_compra;
    }

    public String getMvin_perdida() {
        return mvin_perdida;
    }

    public void setMvin_perdida(String mvin_perdida) {
        this.mvin_perdida = mvin_perdida;
    }

    public String obtineMoviInvCompra() {
        String sql = "";
        sql += "SELECT mvin_mvin, mvin_descr, mvin_natu, mvin_usim, mvin_venta, mvin_inicial,\n";
        sql += "       mvin_revfact, mvin_compra, mvin_perdida                               \n";
        sql += "  FROM in_tmvin                                                              \n";
        sql += " WHERE mvin_compra = 'S' ";
        return sql;
    }

}
