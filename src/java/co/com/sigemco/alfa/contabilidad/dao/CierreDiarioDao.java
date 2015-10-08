/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dao;

/**
 *
 * @author Personal
 */
public class CierreDiarioDao {

    private int cier_cier;
    private String cier_fech;
    private int cier_usua;
    private String cier_vlri;
    private String cier_vlrt;
    private String cier_vlrc;
    private int cier_sede;
    private int cier_estado;

    public int getCier_cier() {
        return cier_cier;
    }

    public void setCier_cier(int cier_cier) {
        this.cier_cier = cier_cier;
    }

    public String getCier_fech() {
        return cier_fech;
    }

    public void setCier_fech(String cier_fech) {
        this.cier_fech = cier_fech;
    }

    public int getCier_usua() {
        return cier_usua;
    }

    public void setCier_usua(int cier_usua) {
        this.cier_usua = cier_usua;
    }

    public String getCier_vlri() {
        return cier_vlri;
    }

    public void setCier_vlri(String cier_vlri) {
        this.cier_vlri = cier_vlri;
    }

    public String getCier_vlrt() {
        return cier_vlrt;
    }

    public void setCier_vlrt(String cier_vlrt) {
        this.cier_vlrt = cier_vlrt;
    }

    public String getCier_vlrc() {
        return cier_vlrc;
    }

    public void setCier_vlrc(String cier_vlrc) {
        this.cier_vlrc = cier_vlrc;
    }

    public int getCier_sede() {
        return cier_sede;
    }

    public void setCier_sede(int cier_sede) {
        this.cier_sede = cier_sede;
    }

    public int getCier_estado() {
        return cier_estado;
    }

    public void setCier_estado(int cier_estado) {
        this.cier_estado = cier_estado;
    }

    public String consultaFiltros(String filtros) {
        String select = "";
        select += "SELECT cier_cier, cier_fech, cier_sede\n";
        select += "  FROM ad_tcier   where   " + filtros;
        //System.out.println(select);
        return select;
    }

}
