/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.dao;

/**
 * Clase en la cual se realizaran los Querys para la entidad Factura
 *
 * @author ACER
 */
public class FacturaDao {

    /**
     * Funcion encargada de realizar el Query para obtener los datos de una
     * factura
     *
     * @param fact_fact Integer identificador unico de la tabla in_tfact
     * @return
     */
    public String buscaFacturaXId(Integer fact_fact) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT fact_fact         , fact_tius         , fact_fec_ini      , fact_fec_cierre   , fact_clien        , fact_vlr_total    , fact_vlr_iva      , fact_tipo_pago    , fact_id_voucher   , fact_cometarios   , fact_estado       , fact_naturaleza   , fact_devolucion   , fact_original     , fact_vlr_dcto     , fact_vlr_efectivo , fact_vlr_tarjeta  , fact_cierre       , fact_sede");
        sql.append(" FROM fa_tfact");
        sql.append(" WHERE fact_fact =");
        sql.append(fact_fact);
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query para obtener el id de transaccion
     * del movimiento contable generado en la facturacion
     *
     * @param fact_fact
     * @return
     */
    public String buscaMoviContableFact(Integer fact_fact) {
        StringBuilder sql = new StringBuilder();
        sql.append("select mvco_trans ");
        sql.append("from co_tmvco ");
        sql.append("where mvco_lladetalle = 'fact' ");
        sql.append("and mvco_id_llave = 1 "); 
        return sql.toString();
    }

}
