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
public class CuentaDao {

    private String cuen_cuen;
    private String cuen_grup;
    private String cuen_estado;
    private String cuen_nombre;
    private String cuen_codigo;
    private String cuen_descripcion;
    private String cuen_naturaleza;

    public String getCuen_naturaleza() {
        return cuen_naturaleza;
    }

    public void setCuen_naturaleza(String cuen_naturaleza) {
        this.cuen_naturaleza = cuen_naturaleza;
    }
    
    public String getCuen_cuen() {
        return cuen_cuen;
    }

    public void setCuen_cuen(String cuen_cuen) {
        this.cuen_cuen = cuen_cuen;
    }

    public String getCuen_grup() {
        return cuen_grup;
    }

    public void setCuen_grup(String cuen_grup) {
        this.cuen_grup = cuen_grup;
    }

    public String getCuen_estado() {
        return cuen_estado;
    }

    public void setCuen_estado(String cuen_estado) {
        this.cuen_estado = cuen_estado;
    }

    public String getCuen_nombre() {
        return cuen_nombre;
    }

    public void setCuen_nombre(String cuen_nombre) {
        this.cuen_nombre = cuen_nombre;
    }

    public String getCuen_codigo() {
        return cuen_codigo;
    }

    public void setCuen_codigo(String cuen_codigo) {
        this.cuen_codigo = cuen_codigo;
    }

    public String getCuen_descripcion() {
        return cuen_descripcion;
    }

    public void setCuen_descripcion(String cuen_descripcion) {
        this.cuen_descripcion = cuen_descripcion;
    }

    /**
     * Funcion encargada de crear Query para obtener las cuentas de un grupo
     *
     * @param grup_grup
     * @return
     */
    public String cuentasXIdGrupo(String grup_grup) {
        String sql = "";
        sql += "SELECT cuen_cuen, cuen_grup, cuen_estado, cuen_nombre, cuen_codigo,\n";
        sql += "       cuen_descripcion                                            \n";
        sql += "  FROM co_tcuen                                                    \n";
        sql += " WHERE cuen_grup = " + grup_grup;
        return sql;
    }
/**
 * Funcion encargada de crear el Query para obtener la cuenta por llave cuenta_cuanta
 * @return 
 */
    public String cuentasXId() {
        String sql = "";
        sql += "SELECT cuen_cuen, cuen_grup, cuen_estado, cuen_nombre, cuen_codigo,\n";
        sql += "       cuen_descripcion                                            \n";
        sql += "  FROM co_tcuen                                                    \n";
        sql += " WHERE cuen_cuen = " + this.getCuen_cuen();
        return sql;

    }
}
