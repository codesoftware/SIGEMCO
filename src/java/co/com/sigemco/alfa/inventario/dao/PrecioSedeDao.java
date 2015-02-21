/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author Nicolas
 */
public class PrecioSedeDao {

    private String sede_sede;
    private String sede_nombre;
    private String dska_dska;

    public String getSede_sede() {
        return sede_sede;
    }

    public void setSede_sede(String sede_sede) {
        this.sede_sede = sede_sede;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String obtienePreciosProductoPorSedeConFormatos() {
        String sql = "";
        sql += "SELECT prpr_prpr, prpr_dska, to_char(prpr_precio,'LFM9,999,999.00') as prpr_precio , prpr_tius_crea, prpr_tius_update, \n ";
        sql += "       prpr_estado, to_char(prpr_fecha,'dd/mm/yyyy') as prpr_fecha, prpr_sede                                   \n ";
        sql += "  FROM in_tprpr                                                             \n ";
        sql += " WHERE prpr_sede = " + this.getSede_sede() + "\n";
        sql += "  AND prpr_dska = " + this.getDska_dska()  + "\n";
        sql += "  order by prpr_prpr desc \n";
        return sql;
    }
    /**
     * Consul
     * @return 
     */
    public String obtienePreciosProductoPorSedeSinFormatos() {
        String sql = "";
        sql += "SELECT prpr_prpr, prpr_dska, to_char(prpr_precio,'LFM9,999,999.00') as prpr_precio , prpr_tius_crea, prpr_tius_update, \n ";
        sql += "       prpr_estado, prpr_fecha, prpr_sede                                   \n ";
        sql += "  FROM in_tprpr                                                             \n ";
        sql += " WHERE prpr_sede = " + this.getSede_sede() + "\n";
        sql += "  AND prpr_dska = " + this.getDska_dska()  + "\n";
        sql += "  order by prpr_prpr desc \n";
        return sql;
    }

}
