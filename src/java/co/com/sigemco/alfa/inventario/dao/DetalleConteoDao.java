/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author ACER
 */
public class DetalleConteoDao {

    private String ecop_ecop;
    private String ecop_copr;
    private String ecop_dska;
    private String ecop_valor;
    private String ecop_existencias;
    private String ecop_diferencia;

    public String getEcop_ecop() {
        return ecop_ecop;
    }

    public void setEcop_ecop(String ecop_ecop) {
        this.ecop_ecop = ecop_ecop;
    }

    public String getEcop_copr() {
        return ecop_copr;
    }

    public void setEcop_copr(String ecop_copr) {
        this.ecop_copr = ecop_copr;
    }

    public String getEcop_dska() {
        return ecop_dska;
    }

    public void setEcop_dska(String ecop_dska) {
        this.ecop_dska = ecop_dska;
    }

    public String getEcop_valor() {
        return ecop_valor;
    }

    public void setEcop_valor(String ecop_valor) {
        this.ecop_valor = ecop_valor;
    }

    public String getEcop_existencias() {
        return ecop_existencias;
    }

    public void setEcop_existencias(String ecop_existencias) {
        this.ecop_existencias = ecop_existencias;
    }

    public String getEcop_diferencia() {
        return ecop_diferencia;
    }

    public void setEcop_diferencia(String ecop_diferencia) {
        this.ecop_diferencia = ecop_diferencia;
    }

    /**
     * Funcion encargada de realizar el Query para obtener los datos de un
     * producto de un conteo teniendo como referencia el inventario y la llave
     * primaria del producto
     *
     * @return
     */
    public String consultaDetalleProductoXConteo() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ecop_ecop, ecop_copr, ecop_dska, ecop_valor, ecop_existencias, ");
        sql.append("       ecop_diferencia                                                ");
        sql.append("  FROM in_tecop                                                       ");
        sql.append(" WHERE ecop_copr = " + this.getEcop_copr());
        sql.append("   AND ecop_dska = " + this.getEcop_dska());
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar la actualizacion de productos por conteo
     *
     * @return
     */
    public String actulizaProdXConteo() {
        StringBuilder sql = new StringBuilder();
        sql.append("update in_tecop                    ");
        sql.append("set ecop_valor = ecop_valor + ("+this.getEcop_valor()+") ");
        sql.append("where ecop_copr = " + this.getEcop_copr() + " ");
        sql.append("and ecop_dska = " + this.getEcop_dska() + " ");
        return sql.toString();
    }

}
