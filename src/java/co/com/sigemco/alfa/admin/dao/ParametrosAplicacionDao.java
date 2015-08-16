/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.admin.dao;

/**
 * Clase con la cual obtengo los Querys de parametrizacion de la aplicacion
 *
 * @author Nicolas
 */
public class ParametrosAplicacionDao {

    /**
     * Funcion con la que busco el parametro del inicio del sistema
     *
     * @return
     */
    public String buscaParametrizacionInicio() {
        StringBuilder sql = new StringBuilder();
        sql.append("select PARA_ESTADO ");
        sql.append("from ad_tpara ");
        sql.append("where para_nombre = 'INICIO_PROD' ");
        sql.append("LIMIT 1");
        return sql.toString();
    }

    /**
     * Funcion con la cual buscamos los productos que estan proximos a terminar
     * sus existencias
     *
     * @return
     */
    public String buscaProductosAlerta() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dska_cod, dska_nom_prod, cepr_existencia ");
        sql.append("FROM in_tcepr, in_tdska ");
        sql.append("WHERE dska_dska = cepr_dska ");
        sql.append("AND cepr_existencia < 5");
        return sql.toString();
    }

}
