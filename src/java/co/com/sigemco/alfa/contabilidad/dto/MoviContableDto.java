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
public class MoviContableDto {

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
    //Variables necesarias para crear los asientos contables
    private String debitos;
    private String creditos;
    private String sbcu_nombre;
    private String sbcu_codigo;

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

    public void setMvco_tipo(String mvco_tipo) {
        this.mvco_tipo = mvco_tipo;
    }

    public String getDebitos() {
        return debitos;
    }

    public void setDebitos(String debitos) {
        this.debitos = debitos;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getSbcu_nombre() {
        return sbcu_nombre;
    }

    public void setSbcu_nombre(String sbcu_nombre) {
        this.sbcu_nombre = sbcu_nombre;
    }

    public String getSbcu_codigo() {
        return sbcu_codigo;
    }

    public void setSbcu_codigo(String sbcu_codigo) {
        this.sbcu_codigo = sbcu_codigo;
    }
}
