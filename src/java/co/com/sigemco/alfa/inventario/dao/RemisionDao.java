/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

import co.com.hotel.utilidades.ManejoString;
import co.com.hotel.validacion.ValidaCampos;

/**
 *
 * @author nicolas
 */
public class RemisionDao {

    private String rmce_rmce;
    private String rmce_refe;
    private String rmce_imei;
    private String rmce_iccid;
    private String rmce_valor;
    private String rmce_comision;
    private String rmce_tppl;
    private String rmce_fcve;
    private String rmce_fcsl;
    private String rmce_fcen;
    private String rmce_tius_ent;
    private String rmce_tius_sal;
    private String rmce_codigo;
    private String rmce_sede;
    private String rmce_estado;
    private String rmce_pagado;
    private String rmce_comdev;
    private String valorBeteween;

    public String getRmce_rmce() {
        return rmce_rmce;
    }

    public void setRmce_rmce(String rmce_rmce) {
        this.rmce_rmce = rmce_rmce;
    }

    public String getRmce_imei() {
        return rmce_imei;
    }

    public void setRmce_imei(String rmce_imei) {
        this.rmce_imei = rmce_imei;
    }

    public String getRmce_iccid() {
        return rmce_iccid;
    }

    public void setRmce_iccid(String rmce_iccid) {
        this.rmce_iccid = rmce_iccid;
    }

    public String getRmce_valor() {
        return ManejoString.eliminaMascaraMoneda(rmce_valor);
    }

    public void setRmce_valor(String rmce_valor) {
        this.rmce_valor = rmce_valor;
    }

    public String getRmce_comision() {
        return rmce_comision;
    }

    public void setRmce_comision(String rmce_comision) {
        this.rmce_comision = rmce_comision;
    }

    public String getRmce_tppl() {
        return rmce_tppl;
    }

    public void setRmce_tppl(String rmce_tppl) {
        this.rmce_tppl = rmce_tppl;
    }

    public String getRmce_fcve() {
        return "to_date('" + rmce_fcve + "','mm/dd/yyyy')";
    }

    public void setRmce_fcve(String rmce_fcve) {
        this.rmce_fcve = rmce_fcve;
    }

    public String getRmce_fcsl() {
        return rmce_fcsl;
    }

    public void setRmce_fcsl(String rmce_fcsl) {
        this.rmce_fcsl = rmce_fcsl;
    }

    public String getRmce_fcen() {
        return rmce_fcen;
    }

    public void setRmce_fcen(String rmce_fcen) {
        this.rmce_fcen = rmce_fcen;
    }

    public String getRmce_tius_ent() {
        return rmce_tius_ent;
    }

    public void setRmce_tius_ent(String rmce_tius_ent) {
        this.rmce_tius_ent = rmce_tius_ent;
    }

    public String getRmce_tius_sal() {
        return rmce_tius_sal;
    }

    public void setRmce_tius_sal(String rmce_tius_sal) {
        this.rmce_tius_sal = rmce_tius_sal;
    }

    public String getRmce_codigo() {
        return rmce_codigo;
    }

    public void setRmce_codigo(String rmce_codigo) {
        this.rmce_codigo = rmce_codigo;
    }

    public String getRmce_sede() {
        return rmce_sede;
    }

    public void setRmce_sede(String rmce_sede) {
        this.rmce_sede = rmce_sede;
    }

    public String getRmce_refe() {
        return rmce_refe;
    }

    public void setRmce_refe(String rmce_refe) {
        this.rmce_refe = rmce_refe;
    }

    public String getRmce_estado() {
        return rmce_estado;
    }

    public void setRmce_estado(String rmce_estado) {
        this.rmce_estado = rmce_estado;
    }

    public String getRmce_pagado() {
        return rmce_pagado;
    }

    public void setRmce_pagado(String rmce_pagado) {
        this.rmce_pagado = rmce_pagado;
    }

    public String getRmce_comdev() {
        return rmce_comdev;
    }

    public void setRmce_comdev(String rmce_comdev) {
        this.rmce_comdev = rmce_comdev;
    }

    public String getValorBeteween() {
        return ManejoString.eliminaMascaraMoneda(valorBeteween);
    }

    public void setValorBeteween(String valorBeteween) {
        this.valorBeteween = valorBeteween;
    }

    /**
     * Funcion la cual retorna el query pra realizar la insercion a la base de
     * datos de remisiones de celulares
     *
     * @return
     */
    public String insert() {
        String insert = "";
        insert += "INSERT INTO in_trmce(";
        insert += "            rmce_refe, rmce_imei, rmce_iccid, rmce_valor, rmce_comision,    ";
        insert += "            rmce_tppl, rmce_fcve, rmce_tius_ent, rmce_codigo, rmce_sede  )values(";
        insert += "'" + this.getRmce_refe() + "'";
        insert += ",'" + this.getRmce_imei() + "'";
        insert += ",'" + this.getRmce_iccid() + "'";
        insert += ",'" + this.getRmce_valor() + "'";
        insert += ",'" + this.getRmce_comision() + "'";
        insert += ",'" + this.getRmce_tppl() + "'";
        insert += "," + this.getRmce_fcve() + "";
        insert += ",'" + this.getRmce_tius_ent() + "'";
        insert += ",(select '2-' || coalesce(count(1), 0) +1 id from in_trmce) ";
        insert += ",'" + this.getRmce_sede() + "')";
        return insert;
    }

    /**
     * Funcion la cual se encarga de generar el sql para la consulta general por
     * filtros
     *
     * @return
     */
    public String consultaGeneralXFiltros() {
        String select = "";
        select += "SELECT rmce_rmce, rmce_refe, rmce_imei, rmce_iccid, to_char(rmce_valor,'LFM9,999,999.99') rmce_valor , rmce_comision,  \n";
        select += "       rmce_tppl, rmce_fcve, rmce_fcsl, rmce_fcen, rmce_tius_ent, rmce_tius_sal,\n";
        select += "       rmce_codigo, rmce_sede, rmce_estado, rmce_pagado, rmce_comdev            \n";
        select += "  FROM in_trmce                                                                \n";
        select += armaWhereObjDao();
        return select;
    }

    public String consultaEspecifica() {
        String select = "SELECT rmce_rmce, rmce_refe, rmce_imei, rmce_iccid, rmce_valor rmce_valor , rmce_comision, rmce_tppl, rmce_fcve, rmce_fcsl, rmce_fcen, rmce_tius_ent, rmce_tius_sal,rmce_codigo, rmce_sede, rmce_estado, rmce_pagado, rmce_comdev FROM in_trmce where rmce_rmce='"
                .concat(this.getRmce_rmce())
                .concat("'");
        System.out.println("select especifico" + select);
        return select;
    }

    public String actualizaEspecifica() {
        String select = "UPDATE in_trmce SET  "
                .concat("rmce_refe=").concat(this.rmce_refe)
                .concat(",rmce_imei='").concat(this.rmce_imei)
                .concat("',rmce_iccid='").concat(this.rmce_iccid)
                .concat("',rmce_valor='").concat(this.rmce_valor)
                .concat("',rmce_comision='").concat(this.rmce_comision)
                .concat("',rmce_fcve='").concat(this.rmce_fcve)
                .concat("',rmce_sede=").concat(this.rmce_sede)
                .concat(" WHERE rmce_rmce=".concat(this.rmce_rmce));
        ;

        System.out.println("update especifico" + select);
        return select;
    }
    public String devuelveCelular() {
        String select = "UPDATE in_trmce SET  "
                .concat("rmce_comdev='").concat(this.rmce_comdev)
                .concat("',rmce_estado='D'")
                .concat(" WHERE rmce_rmce=".concat(this.rmce_rmce));
        ;

        System.out.println("update especifico" + select);
        return select;
    }

    public String armaWhereObjDao() {
        String rta = "where 1= 1\n";
        ValidaCampos valida = new ValidaCampos();
        try {
            if (valida.validaNulo(this.rmce_refe) && !this.getRmce_refe().equalsIgnoreCase("-1")) {
                rta += "and rmce_refe = " + this.getRmce_refe() + "\n";
            }
            if (valida.validaNulo(this.getRmce_tppl()) && !this.getRmce_tppl().equalsIgnoreCase("-1")) {
                rta += "and rmce_tppl = '" + this.getRmce_tppl() + "'\n";
            }
            if (valida.validaNulo(this.getRmce_sede()) && !this.getRmce_sede().equalsIgnoreCase("-1")) {
                rta += "and rmce_sede = " + this.getRmce_sede() + "\n";
            }
            if (valida.validaNulo(this.getRmce_pagado()) && !this.getRmce_pagado().equalsIgnoreCase("-1")) {
                rta += "and rmce_pagado = '" + this.getRmce_pagado() + "'\n";
            }
            if (valida.validaNulo(this.getRmce_estado()) && !this.getRmce_estado().equalsIgnoreCase("-1")) {
                rta += "and rmce_estado = '" + this.getRmce_estado() + "'\n";
            }
            if (valida.validaNulo(this.getRmce_valor()) && !this.getRmce_valor().equalsIgnoreCase("0")) {
                if (valida.validaNulo(this.valorBeteween) && !this.getValorBeteween().equalsIgnoreCase("0")) {
                    rta += "and rmce_valor BETWEEN " + this.getRmce_valor() + " and " + this.getValorBeteween() + "\n";
                } else {
                    rta += "and rmce_valor <= " + this.getRmce_valor() + "\n";
                }
            } else if (valida.validaNulo(this.getValorBeteween()) && !this.getValorBeteween().equalsIgnoreCase("-1")) {
                rta += "and rmce_valor >= " + this.getValorBeteween() + "\n";
            }
            if (valida.validaNulo(this.getRmce_iccid()) && !this.getRmce_iccid().equalsIgnoreCase("")) {
                rta += "and upper(rmce_iccid) like upper('%" + this.getRmce_iccid() + "%') \n";
            }
            if (valida.validaNulo(this.getRmce_imei()) && !this.getRmce_imei().equalsIgnoreCase("")) {
                rta += "and upper(rmce_imei) like upper('%" + this.getRmce_imei().trim() + "%') \n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rta;
    }

    /**
     * Funcion la cual se encarga de crear el sql para obtener los equipos
     * celulares vencidos
     *
     * @return
     */
    public String remisionesProximasAVencer() {
        String sql = "";
        sql += "SELECT rmce_rmce , to_char(rmce_fcve,'dd/mm/yyyy') rmce_fcve , refe_desc, sede_nombre, rmce_imei, rmce_iccid\n";
        sql += "  FROM in_trmce, in_trefe, em_tsede               \n";
        sql += " WHERE rmce_fcve <= (select current_date + (select cast(para_valor as int) dias from em_tpara where para_clave = 'DIASVEN'))\n";
        sql += "   AND rmce_estado = 'E'    \n";
        sql += "   AND rmce_refe = refe_refe\n";
        sql += "   AND rmce_sede = sede_sede\n";
        return sql;
    }

}
