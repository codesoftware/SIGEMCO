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
public class ProductoRecetaDao {

    /**
     * Funcion con la cual busco los productos de una receta
     *
     * @param rece_rece
     * @return
     */
    public String buscaProductosReceta(String rece_rece) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT repr_repr       , repr_rece       , repr_dska       , to_char(repr_promedio,'9,999,999,999.00') repr_promedio   , repr_estado     , repr_fec_ingreso, repr_tius       , repr_cantidad, ");
        sql.append(" dska_cod, dska_nom_prod ");
        sql.append("FROM in_trepr, in_tdska ");
        sql.append("WHERE repr_rece = ");
        sql.append(rece_rece);
        sql.append(" AND dska_dska = repr_dska ");
        sql.append(" order by repr_fec_ingreso ");
        return sql.toString();
    }
}
