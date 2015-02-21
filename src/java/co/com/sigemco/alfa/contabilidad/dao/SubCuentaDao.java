/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dao;

/**
 *
 * @author SISCOMPUTO
 */
public class SubCuentaDao {

    private String sbcu_sbcu;
    private String sbcu_cuen;
    private String sbcu_clas;
    private String sbcu_grup;
    private String sbcu_estado;
    private String sbcu_nombre;
    private String sbcu_codigo;
    private String sbcu_descripcion;
    private String sbcu_naturaleza;

    public String getSbcu_naturaleza() {
        return sbcu_naturaleza;
    }

    public void setSbcu_naturaleza(String sbcu_naturaleza) {
        this.sbcu_naturaleza = sbcu_naturaleza;
    }

    public String getSbcu_sbcu() {
        return sbcu_sbcu;
    }

    public void setSbcu_sbcu(String sbcu_sbcu) {
        this.sbcu_sbcu = sbcu_sbcu;
    }

    public String getSbcu_cuen() {
        return sbcu_cuen;
    }

    public void setSbcu_cuen(String sbcu_cuen) {
        this.sbcu_cuen = sbcu_cuen;
    }

    public String getSbcu_clas() {
        return sbcu_clas;
    }

    public void setSbcu_clas(String sbcu_clas) {
        this.sbcu_clas = sbcu_clas;
    }

    public String getSbcu_grup() {
        return sbcu_grup;
    }

    public void setSbcu_grup(String sbcu_grup) {
        this.sbcu_grup = sbcu_grup;
    }

    public String getSbcu_estado() {
        return sbcu_estado;
    }

    public void setSbcu_estado(String sbcu_estado) {
        this.sbcu_estado = sbcu_estado;
    }

    public String getSbcu_nombre() {
        return sbcu_nombre;
    }

    public void setSbcu_nombre(String sbcu_nombre) {
        this.sbcu_nombre = sbcu_nombre;
    }

    public String getSbcu_codigo() {
        return sbcu_codigo;
    }

    public void setSbcu_codigo(String sbcu_codigo) {
        this.sbcu_codigo = sbcu_codigo;
    }

    public String getSbcu_descripcion() {
        return sbcu_descripcion;
    }

    public void setSbcu_descripcion(String sbcu_descripcion) {
        this.sbcu_descripcion = sbcu_descripcion;
    }

    /**
     * Query encargado de seleccioanr todas las Subcuentas que pertenecen a una
     * Cuenta
     *
     * @param cuen_cuen
     * @return
     */
    public String subCuentasXIdCuenta(String cuen_cuen) {
        String sql = "";
        sql += "SELECT sbcu_sbcu, sbcu_cuen, sbcu_clas, sbcu_grup, sbcu_estado, sbcu_nombre,\n";
        sql += "sbcu_codigo, sbcu_descripcion                                               \n";
        sql += "FROM co_tsbcu;                                                              \n";
        sql += " WHERE sbcu_cuen = " + cuen_cuen;
        return sql;
    }

    /**
     * Query encargado de insertar una nueva SubCuenta en la tabla con_tsbcu no
     * s envia ni el id(sbcu_sbcU)ni el codigo(sbcu_codigo) ya que se generan
     * desde el triger.
     *
     * @return
     */
    public String insertSubCuenta() {
        String sql = "";
        sql += "INSERT INTO co_tsbcu(";
        sql += "sbcu_cuen, sbcu_clas, sbcu_grup, sbcu_estado, sbcu_nombre,";
        sql += " sbcu_descripcion,sbcu_naturaleza,sbcu_codigo)";
        sql += "VALUES (" + this.getSbcu_cuen() + "," + this.getSbcu_clas() + ", " + this.getSbcu_grup() + ", UPPER('A'), '" + this.getSbcu_nombre() + "',";
        sql += "'" + this.getSbcu_descripcion() + "',UPPER('" + this.getSbcu_naturaleza() + "'),'" + this.getSbcu_codigo() + "');";
        return sql;
    }
}
