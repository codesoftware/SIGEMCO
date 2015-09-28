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
public class ClaseDao {

    private String clas_clas;
    private String clas_estado;
    private String clas_nombre;
    private String clas_codigo;
    private String clas_descripcion;
    private String clas_naturaleza;

    public String getClas_naturaleza() {
        return clas_naturaleza;
    }

    public void setClas_naturaleza(String clas_naturaleza) {
        this.clas_naturaleza = clas_naturaleza;
    }
    
    public String getClas_clas() {
        return clas_clas;
    }

    public void setClas_clas(String clas_clas) {
        this.clas_clas = clas_clas;
    }

    public String getClas_estado() {
        return clas_estado;
    }

    public void setClas_estado(String clas_estado) {
        this.clas_estado = clas_estado;
    }

    public String getClas_nombre() {
        return clas_nombre;
    }

    public void setClas_nombre(String clas_nombre) {
        this.clas_nombre = clas_nombre;
    }

    public String getClas_codigo() {
        return clas_codigo;
    }

    public void setClas_codigo(String clas_codigo) {
        this.clas_codigo = clas_codigo;
    }

    public String getClas_descripcion() {
        return clas_descripcion;
    }

    public void setClas_descripcion(String clas_descripcion) {
        this.clas_descripcion = clas_descripcion;
    }

    /**
     * Funcion encargada de generar el query para realizar la consulta general
     * de clases
     *
     * @return
     */
    public String cosultaGeneralActivos() {
        String sql = "";
        sql += "SELECT clas_clas, clas_estado, clas_nombre, clas_codigo, clas_descripcion\n";
        sql += "  FROM co_tclas                                                         \n";
        sql += " WHERE clas_estado = 'A'";
        return sql;
    }
    
    public String consultaEspeciXId(){
        String sql = "";
        sql += "SELECT clas_clas, clas_estado, clas_nombre, clas_codigo, clas_descripcion\n";
        sql += "  FROM co_tclas                                                         \n";
        sql += "  WHERE clas_clas = " + this.getClas_clas();
        return sql;
    }
}
