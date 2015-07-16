/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

import co.com.sigemco.alfa.inventario.dto.RecetaDto;

/**
 *
 * @author nicolas
 */
public class RecetaDao {

    /**
     * Funcion encargada de realizar el Query para insertar una receta
     *
     * @param objDto
     * @return
     */
    public String insertareceta(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO in_trece(");
        sql.append("rece_rece, rece_codigo, rece_nombre, rece_desc, rece_iva,rece_promedio) ");
        sql.append("values ((select coalesce(max(rece_rece), 0) + 1 from in_trece),");
        sql.append("(select '3-'|| coalesce(max(rece_rece), 0) + 1 from in_trece),");
        sql.append("upper('");
        sql.append(objDto.getRece_nombre());
        sql.append("'),upper('");
        sql.append(objDto.getRece_desc());
        sql.append("'),");
        sql.append("(SELECT cast(para_valor as numeric) FROM em_tpara WHERE para_clave = 'IVAPRVENTA'),");
        sql.append("0)");
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query la consulta general de Recetas con
     * filtros
     *
     * @param objDto
     * @return
     */
    public String consultaGeneralRecetasXFiltros(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT rece_rece, rece_codigo, rece_nombre, rece_desc, rece_iva, rece_estado, ");
        sql.append("rece_fec_ingreso, rece_promedio ");
        sql.append("FROM in_trece ");
        sql.append("WHERE 1=1");
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query para la actualizacion de una
     * Receta por medio de su Id
     *
     * @param objDto
     * @return
     */
    public String actualizaRecetaXId(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE in_trece ");
        sql.append("   SET rece_nombre='");
        sql.append(objDto.getRece_nombre());
        sql.append("',");
        sql.append("rece_desc='");
        sql.append(objDto.getRece_desc());
        sql.append("',");
        sql.append("rece_estado='");
        sql.append(objDto.getRece_estado());
        sql.append("' WHERE rece_rece = ");
        sql.append(objDto.getRece_rece());
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query la consulta especifica de una
     * receta por su id
     *
     * @param objDto
     * @return
     */
    public String consultaGeneralRecetasXId(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT rece_rece, rece_codigo, rece_nombre, rece_desc, rece_iva, rece_estado, ");
        sql.append("rece_fec_ingreso, rece_promedio ");
        sql.append("FROM in_trece ");
        sql.append("WHERE rece_rece=");
        sql.append(objDto.getRece_rece());
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query la consulta de una receta por su
     * codigo
     *
     * @param objDto
     * @return
     */
    public String consultaGeneralRecetasXCodigo(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT rece_rece, rece_codigo, rece_nombre, rece_desc, rece_iva, rece_estado, ");
        sql.append("to_char(rece_fec_ingreso, 'dd/mm/yyyy') rece_fec_ingreso , rece_promedio ");
        sql.append("FROM in_trece ");
        sql.append("WHERE rece_codigo = '");
        sql.append(objDto.getRece_codigo());
        sql.append("'");
        return sql.toString();
    }

    /**
     * Funcion con la cual parametrizo el precio de una receta
     *
     * @param objDto
     * @param tius_tius
     * @return
     */
    public String insertPrecioReceta(RecetaDto objDto, String tius_tius) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO in_tprre(");
        sql.append("prre_rece, prre_precio, prre_tius_crea, prre_tius_update, ");
        sql.append("prre_sede) ");
        sql.append("VALUES (");
        sql.append(objDto.getRece_rece());
        sql.append(",");
        sql.append(objDto.getRece_precio());
        sql.append(",");
        sql.append(tius_tius);
        sql.append(",");
        sql.append(tius_tius);
        sql.append(",");
        sql.append(objDto.getRece_sede());
        sql.append(")");
        return sql.toString();
    }

    /**
     * Actualiza todas las recetas a inactivo por sede
     *
     * @param objDto
     * @return
     */
    public String actualizaPreciosInactivosXSede(RecetaDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE in_tprre ");
        sql.append("SET prre_estado = 'I' ");
        sql.append("WHERE prre_rece = ");
        sql.append(objDto.getRece_rece());
        sql.append(" AND prre_sede = ");
        sql.append(objDto.getRece_sede());
        return sql.toString();
    }

    /**
     * .Funcion encargada de realizar el Query que obtiene el historico de
     * precios de recetas por sede
     *
     * @return
     */
    public String buscaPreciosPorSede(String sede, String rece) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT prre_prre, prre_sede, prre_estado, prre_precio, prre_fecha ");
        sql.append("FROM in_tprre ");
        sql.append("WHERE prre_rece = ");
        sql.append(rece);
        sql.append(" AND prre_sede = ");
        sql.append(sede);
        sql.append(" order by prre_prre desc ");
        return sql.toString();
    }

}
