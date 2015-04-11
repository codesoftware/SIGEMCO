/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dto;

/**
 *
 * @author Nicolas
 */
public class MoviContableDao {

    private String mvco_mvco;
    private String mvco_trans;
    private String mvco_sbcu;
    private String mvco_naturaleza;
    private String mvco_tido;
    private String mvco_valor;
    private String mvco_lladetalle;
    private String mvco_id_llave;
    private String mvco_tercero;
    private String mvco_tipo;
    private String clas_clas;
    private String grup_grup;
    private String cuen_cuen;

    public String getMvco_mvco() {
        return mvco_mvco;
    }

    public void setMvco_mvco(String mvco_mvco) {
        this.mvco_mvco = mvco_mvco;
    }

    public String getMvco_trans() {
        return mvco_trans;
    }

    public void setMvco_trans(String mvco_trans) {
        this.mvco_trans = mvco_trans;
    }

    public String getMvco_sbcu() {
        return mvco_sbcu;
    }

    public void setMvco_sbcu(String mvco_sbcu) {
        this.mvco_sbcu = mvco_sbcu;
    }

    public String getMvco_naturaleza() {
        return mvco_naturaleza;
    }

    public void setMvco_naturaleza(String mvco_naturaleza) {
        this.mvco_naturaleza = mvco_naturaleza;
    }

    public String getMvco_tido() {
        return mvco_tido;
    }

    public void setMvco_tido(String mvco_tido) {
        this.mvco_tido = mvco_tido;
    }

    public String getMvco_valor() {
        return mvco_valor;
    }

    public void setMvco_valor(String mvco_valor) {
        this.mvco_valor = mvco_valor;
    }

    public String getMvco_lladetalle() {
        return mvco_lladetalle;
    }

    public void setMvco_lladetalle(String mvco_lladetalle) {
        this.mvco_lladetalle = mvco_lladetalle;
    }

    public String getMvco_id_llave() {
        return mvco_id_llave;
    }

    public void setMvco_id_llave(String mvco_id_llave) {
        this.mvco_id_llave = mvco_id_llave;
    }

    public String getMvco_tercero() {
        return mvco_tercero;
    }

    public void setMvco_tercero(String mvco_tercero) {
        this.mvco_tercero = mvco_tercero;
    }

    public String getMvco_tipo() {
        return mvco_tipo;
    }

    public String getClas_clas() {
        return clas_clas;
    }

    public void setClas_clas(String clas_clas) {
        this.clas_clas = clas_clas;
    }

    public String getGrup_grup() {
        return grup_grup;
    }

    public void setGrup_grup(String grup_grup) {
        this.grup_grup = grup_grup;
    }

    public String getCuen_cuen() {
        return cuen_cuen;
    }

    public void setCuen_cuen(String cuen_cuen) {
        this.cuen_cuen = cuen_cuen;
    }
    

    public void setMvco_tipo(String mvco_tipo) {
        this.mvco_tipo = mvco_tipo;
    }

    /**
     * Funcion encargada de realizar el query para obtene el asiento contable de
     * una transaccion
     *
     * @return
     */
    public String generaAsientoContable() {
        String sql = "";
        sql += "SELECT sbcu_nombre,sbcu_codigo,to_char(mvco_valor,'LFM9,999,999,999.0') debitos, 'N/A' creditos   \n";
        sql += "  FROM co_tmvco,co_tsbcu                                                                       \n";
        sql += " WHERE mvco_trans = " + this.getMvco_trans() + "                                                   \n";
        sql += "   AND mvco_naturaleza = 'D'                                                                   \n";
        sql += "   AND mvco_sbcu = sbcu_sbcu                                                                   \n";
        sql += " UNION all                                                                                     \n";
        sql += "SELECT sbcu_nombre,sbcu_codigo, 'N/A' debitos,  to_char(mvco_valor,'LFM9,999,999,999.0') creditos\n";
        sql += "  FROM co_tmvco,co_tsbcu                                                                       \n";
        sql += " WHERE mvco_trans = " + this.getMvco_trans() + "                                                   \n";
        sql += "   AND mvco_naturaleza = 'C'                                                                   \n";
        sql += "   AND mvco_sbcu = sbcu_sbcu                                                                   \n";
        return sql;
    }

    public String consultaFilros(String filtros) {
        String select = "SELECT mvco_mvco, mvco_trans, mvco_sbcu, mvco_naturaleza, mvco_tido, \n"
                + "mvco_valor, mvco_lladetalle, mvco_id_llave, mvco_tercero, mvco_tipo from co_tmvco,co_tsbcu   WHERE " + filtros;
        System.out.println("Filtros" + select);
        return select;
    }
    
}
