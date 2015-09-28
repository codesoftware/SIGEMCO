/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author Personal
 */
public class MarcaDao {

    private String marca_marca;
    private String marca_nombre;
    private String marca_descr;
    private String marca_estado;

    public String insertaMarca() {
        String select = "";
        select = "INSERT into in_tmarca (marca_nombre,  marca_descr)"
                .concat("values ('" + this.marca_nombre + "','" + this.marca_descr + "')");
        System.out.println("prueba" + select);
        return select;
    }

    public String consultaFilros(String filtros) {
        String select = "SELECT marca_marca, marca_nombre, marca_descr, marca_estado from in_tmarca  WHERE " + filtros;
        System.out.println("Filtros" + select);
        return select;
    }

    public String actualizaMarca() {
        String select = "UPDATE in_tmarca set marca_descr = '" + this.marca_descr + "',marca_estado = '" + this.marca_estado + "',marca_nombre = '" + this.marca_nombre + "' WHERE marca_marca = " + this.marca_marca + "";
        System.out.println("Update " + select);
        return select;
    }

    public String consultaEspecificaXId() {
        String select = "";
        select += "SELECT marca_marca, marca_descr, marca_estado, marca_nombre \n";
        select += "  FROM in_tmarca                                                 \n";
        select += " WHERE marca_marca = " + this.marca_marca + " \n";
        return select;
    }

    public String getMarca_marca() {
        return marca_marca;
    }

    public void setMarca_marca(String marca_marca) {
        this.marca_marca = marca_marca;
    }

    public String getMarca_nombre() {
        return marca_nombre;
    }

    public void setMarca_nombre(String marca_nombre) {
        this.marca_nombre = marca_nombre;
    }

    public String getMarca_descr() {
        return marca_descr;
    }

    public void setMarca_descr(String marca_descr) {
        this.marca_descr = marca_descr;
    }

    public String getMarca_estado() {
        return marca_estado;
    }

    public void setMarca_estado(String marca_estado) {
        this.marca_estado = marca_estado;
    }

}
