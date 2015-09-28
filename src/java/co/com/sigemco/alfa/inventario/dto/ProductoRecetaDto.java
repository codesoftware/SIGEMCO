/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dto;

/**
 *
 * @author nicolas
 */
public class ProductoRecetaDto {

    private String repr_repr;
    private String repr_rece;
    private String repr_dska;
    private String repr_promedio;
    private String repr_estado;
    private String repr_fec_ingreso;
    private String repr_tius;
    private String repr_cantidad;
    //Datos del producto como tal
    private String dska_cod;
    private String dska_nombre;

    public String getRepr_repr() {
        return repr_repr;
    }

    public void setRepr_repr(String repr_repr) {
        this.repr_repr = repr_repr;
    }

    public String getRepr_rece() {
        return repr_rece;
    }

    public void setRepr_rece(String repr_rece) {
        this.repr_rece = repr_rece;
    }

    public String getRepr_dska() {
        return repr_dska;
    }

    public void setRepr_dska(String repr_dska) {
        this.repr_dska = repr_dska;
    }

    public String getRepr_promedio() {
        return repr_promedio;
    }

    public void setRepr_promedio(String repr_promedio) {
        this.repr_promedio = repr_promedio;
    }

    public String getRepr_estado() {
        return repr_estado;
    }

    public void setRepr_estado(String repr_estado) {
        this.repr_estado = repr_estado;
    }

    public String getRepr_fec_ingreso() {
        return repr_fec_ingreso;
    }

    public void setRepr_fec_ingreso(String repr_fec_ingreso) {
        this.repr_fec_ingreso = repr_fec_ingreso;
    }

    public String getRepr_tius() {
        return repr_tius;
    }

    public void setRepr_tius(String repr_tius) {
        this.repr_tius = repr_tius;
    }

    public String getRepr_cantidad() {
        return repr_cantidad;
    }

    public void setRepr_cantidad(String repr_cantidad) {
        this.repr_cantidad = repr_cantidad;
    }

    public String getDska_cod() {
        return dska_cod;
    }

    public void setDska_cod(String dska_cod) {
        this.dska_cod = dska_cod;
    }

    public String getDska_nombre() {
        return dska_nombre;
    }

    public void setDska_nombre(String dska_nombre) {
        this.dska_nombre = dska_nombre;
    }

}
