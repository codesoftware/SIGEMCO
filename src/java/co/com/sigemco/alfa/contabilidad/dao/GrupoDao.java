/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dao;

/**
 *
 * @author Nicolas
 */
public class GrupoDao {

    private String grup_grup;
    private String grup_clas;
    private String grup_estado;
    private String grup_nombre;
    private String grup_codigo;
    private String grup_descripcion;
    private String clas_naturaleza;

    public String getClas_naturaleza() {
        return clas_naturaleza;
    }

    public void setClas_naturaleza(String clas_naturaleza) {
        this.clas_naturaleza = clas_naturaleza;
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

    /**
     * Funcion encargada de generar el Query para obtener todos los grupos de
     * una clase
     *
     * @param clas_clas
     * @return
     */
    public String obtenerGruposXClas_Clas(String clas_clas) {
        String select = "";
        select += "SELECT grup_grup, grup_clas, grup_estado, grup_nombre, grup_codigo, \n";
        select += "       grup_descripcion                                             \n";
        select += "  FROM co_tgrup                                                     \n";
        select += " WHERE grup_estado = 'A' \n";
        select += " AND grup_clas = " + clas_clas;
        return select;
    }

    /**
     * Funcion encargada de realizar el query de un grupo basandonos en su id
     *
     * @return
     */
    public String obtenerGruposXId() {
        String select = "";
        select += "SELECT grup_grup, grup_clas, grup_estado, grup_nombre, grup_codigo, \n";
        select += "       grup_descripcion                                             \n";
        select += "  FROM co_tgrup                                                     \n";
        select += " WHERE grup_estado = 'A' \n";
        select += " AND grup_grup = " + this.getGrup_grup();
        return select;
    }

}
