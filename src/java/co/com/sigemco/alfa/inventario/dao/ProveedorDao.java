/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author john
 */
public class ProveedorDao {

    private String prov_prov;
    private String prov_nombre;
    private String prov_nit;
    private String prov_razon_social;
    private String prov_representante;
    private String prov_telefono;
    private String prov_direccion;
    private String prov_celular;
    private String prov_estado;

    /**
     * Funcion que crea el query para la inserción del proovedor
     *
     * @return
     */
    public String insertaProveedor() {
        String select = "";
        select = "INSERT into in_tprov (prov_nombre,  prov_nit,prov_razon_social,prov_representante,prov_telefono,prov_direccion,prov_celular)"
                .concat("values ('" + this.prov_nombre + "','" + this.prov_nit + "','" + this.prov_razon_social + "','" + this.prov_representante + "','" + this.prov_telefono + "','" + this.prov_direccion + "','" + this.prov_celular + "')");
        return select;
    }

    public String consultaFilros(String filtros) {
        String select = "SELECT prov_prov, prov_nombre,  prov_nit,prov_razon_social,prov_representante,prov_telefono,prov_direccion,prov_celular,prov_estado from in_tprov  WHERE " + filtros;
        System.out.println("Filtros" + select);
        return select;
    }

    public String actualizaProovedor() {
        String select = "UPDATE in_tprov set prov_nombre = '" + this.prov_nombre + "',prov_nit = '" + this.prov_nit + "',prov_razon_social = '" + this.prov_razon_social + "', "
                + "prov_representante = '" + this.prov_representante + "',prov_telefono = '" + this.prov_telefono + "',prov_direccion = '" + this.prov_direccion + "',"
                + " prov_celular = '" + this.prov_celular + "',prov_estado = '" + this.prov_estado + "' WHERE prov_prov = " + this.prov_prov + "";
        System.out.println("Update " + select);
        return select;
    }

    public String consultaEspecificaXId() {
        String select = "";
        select += "select prov_prov, prov_nombre,  prov_nit,prov_razon_social,prov_representante,prov_telefono,prov_direccion,prov_celular,prov_estado \n";
        select += "  FROM in_tprov                                                 \n";
        select += " WHERE prov_prov = " + this.prov_prov + " \n";
        System.out.println("select" + select);
        return select;
    }

    public String getProv_prov() {
        return prov_prov;
    }

    public void setProv_prov(String prov_prov) {
        this.prov_prov = prov_prov;
    }

    public String getProv_nombre() {
        return prov_nombre;
    }

    public void setProv_nombre(String prov_nombre) {
        this.prov_nombre = prov_nombre;
    }

    public String getProv_nit() {
        return prov_nit;
    }

    public void setProv_nit(String prov_nit) {
        this.prov_nit = prov_nit;
    }

    public String getProv_razon_social() {
        return prov_razon_social;
    }

    public void setProv_razon_social(String prov_razon_social) {
        this.prov_razon_social = prov_razon_social;
    }

    public String getProv_representante() {
        return prov_representante;
    }

    public void setProv_representante(String prov_representante) {
        this.prov_representante = prov_representante;
    }

    public String getProv_telefono() {
        return prov_telefono;
    }

    public void setProv_telefono(String prov_telefono) {
        this.prov_telefono = prov_telefono;
    }

    public String getProv_direccion() {
        return prov_direccion;
    }

    public void setProv_direccion(String prov_direccion) {
        this.prov_direccion = prov_direccion;
    }

    public String getProv_celular() {
        return prov_celular;
    }

    public void setProv_celular(String prov_celular) {
        this.prov_celular = prov_celular;
    }

    public String getProv_estado() {
        return prov_estado;
    }

    public void setProv_estado(String prov_estado) {
        this.prov_estado = prov_estado;
    }

}
