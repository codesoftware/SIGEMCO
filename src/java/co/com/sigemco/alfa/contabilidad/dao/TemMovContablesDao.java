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
public class TemMovContablesDao {

    private String tem_mvco_trans;
    private String tem_mvco_sbcu;
    private String tem_mvco_valor;
    private String tem_mvco_naturaleza;

    public String getTem_mvco_trans() {
        return tem_mvco_trans;
    }

    public void setTem_mvco_trans(String tem_mvco_trans) {
        this.tem_mvco_trans = tem_mvco_trans;
    }

    public String getTem_mvco_sbcu() {
        return tem_mvco_sbcu;
    }

    public void setTem_mvco_sbcu(String tem_mvco_sbcu) {
        this.tem_mvco_sbcu = tem_mvco_sbcu;
    }

    public String getTem_mvco_valor() {
        return tem_mvco_valor;
    }

    public void setTem_mvco_valor(String tem_mvco_valor) {
        this.tem_mvco_valor = tem_mvco_valor;
    }

    public String getTem_mvco_naturaleza() {
        return tem_mvco_naturaleza;
    }

    public void setTem_mvco_naturaleza(String tem_mvco_naturaleza) {
        this.tem_mvco_naturaleza = tem_mvco_naturaleza;
    }

    /**
     * Funcion encargada de realizar el query necesario para insertar en la base
     * de datos los datos de la tabla temporal de movimientos contables
     *
     * @return
     */
    public String insert() {
        String sql = "";
        sql += "INSERT INTO co_ttem_mvco( tem_mvco_trans, tem_mvco_sbcu,  \n";
        sql += "                          tem_mvco_valor, tem_mvco_naturaleza) \n";
        sql += "    VALUES (\n";
        sql += "'" + this.getTem_mvco_trans() + "'";
        sql += ",'" + this.getTem_mvco_sbcu() + "'";
        sql += ",'" + this.getTem_mvco_valor() + "'";
        sql += ",'" + this.getTem_mvco_naturaleza() + "')";
        return sql;
    }

    /**
     * Funcion encargada de realiar el Query necesaria para obtener el valor de
     * la secuencia de la tabla temporal
     *
     * @return
     */
    public String obtieneSecuenciaTem() {
        String sql = "";
        sql = "SELECT nextval('co_temp_movi_contables') secuencia ";
        return sql;
    }

    /**
     * Funcion encargada de realizar el query para eliminar datos de la tabla
     * temporal basada en el id de la transaccion
     *
     * @return
     */
    public String eliminaTemporalXTran() {
        String sql = "delete from co_ttem_mvco";
        sql += "where tem_mvco_trans = "+ this.getTem_mvco_trans();
        return sql;
    }
}
