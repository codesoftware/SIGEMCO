/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author nicolas
 */
public class ConteoProdDao {

    private String copr_copr;
    private String copr_estado;
    private String copr_tius;
    private String copr_fecha;
    private String copr_sede;
    private String copr_fec_ini;
    private String copr_fec_fin;
    private String copr_desc;

    public String getCopr_copr() {
        return copr_copr;
    }

    public void setCopr_copr(String copr_copr) {
        this.copr_copr = copr_copr;
    }

    public String getCopr_estado() {
        return copr_estado;
    }

    public void setCopr_estado(String copr_estado) {
        this.copr_estado = copr_estado;
    }

    public String getCopr_tius() {
        return copr_tius;
    }

    public void setCopr_tius(String copr_tius) {
        this.copr_tius = copr_tius;
    }

    public String getCopr_fecha() {
        return copr_fecha;
    }

    public void setCopr_fecha(String copr_fecha) {
        this.copr_fecha = copr_fecha;
    }

    public String getCopr_sede() {
        return copr_sede;
    }

    public void setCopr_sede(String copr_sede) {
        this.copr_sede = copr_sede;
    }

    public String getCopr_fec_ini() {
        return copr_fec_ini;
    }

    public void setCopr_fec_ini(String copr_fec_ini) {
        this.copr_fec_ini = copr_fec_ini;
    }

    public String getCopr_fec_fin() {
        return copr_fec_fin;
    }

    public void setCopr_fec_fin(String copr_fec_fin) {
        this.copr_fec_fin = copr_fec_fin;
    }

    public String getCopr_desc() {
        return copr_desc;
    }

    public void setCopr_desc(String copr_desc) {
        this.copr_desc = copr_desc;
    }

    /**
     * Funcion encargada de realizar el query para realizar una insercion de un
     * conteo nuevo de inventario
     *
     * @return String Sentencia sql
     */
    public String insertaConteo() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO in_tcopr(                                   ");
        sql.append("            copr_copr, copr_tius, copr_sede, copr_desc) ");
        sql.append("    VALUES ((SELECT coalesce(max(copr_copr), 0) + 1  FROM in_tcopr)," + this.getCopr_tius() + ", " + this.getCopr_sede() + ", '" + this.getCopr_desc() + "')");
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query para la consulta general de
     * conteos
     *
     * @return
     */
    public String consultaGeneral() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT copr_copr, copr_estado, copr_tius, copr_fecha, copr_sede, copr_fec_ini, ");
        sql.append("       copr_fec_fin, copr_desc ");
        sql.append(" FROM in_tcopr");
        return sql.toString();
    }

}