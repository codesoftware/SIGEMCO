/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.dto;

import co.com.hotel.utilidades.ManejoString;

/**
 *
 * @author nicolas
 */
public class Producto {

    private String id;
    private String referencia;
    private String codigo;
    private String nombre;
    private String descripcion = "";
    private String iva;
    private String porcIva;
    private String marca = "";
    private String cantidad;
    private String costo;
    private String precio;
    private String sede;
    private String categoria;
    private String fechaVencimiento;
    private String registroUnico;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPorcIva() {
        return porcIva;
    }

    public void setPorcIva(String porcIva) {
        this.porcIva = porcIva;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrecio() {
        return ManejoString.eliminaMascaraMoneda(precio);
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getRegistroUnico() {
        return registroUnico;
    }

    public void setRegistroUnico(String registroUnico) {
        this.registroUnico = registroUnico;
    }

}
