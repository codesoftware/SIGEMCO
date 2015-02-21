/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.dto.facturacion;

import java.util.List;

/**
 *
 * @author nicolas
 */
public class Factura {
    
    private String fact_fact;
    private String fact_fec_ini;
    private String fact_estado;
    private String usuarioFacturo;
    private String fact_vlr_iva;
    private String fact_vlr_total;
    private String fact_vlr_total_iva;
    private List<Servicio> detalleServicio; 
    private List<Producto> detalleProducto;
    private Cliente cliente;
    private String valorIva;
    private String valorServicio;
    private String valorTotal;
    
    private String valorIvaPro;
    private String valorProductos;
    private String valorTotalProd;

    public String getFact_fact() {
        return fact_fact;
    }

    public void setFact_fact(String fact_fact) {
        this.fact_fact = fact_fact;
    }

    public List<Servicio> getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(List<Servicio> detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFact_fec_ini() {
        return fact_fec_ini;
    }

    public void setFact_fec_ini(String fact_fec_ini) {
        this.fact_fec_ini = fact_fec_ini;
    }      

    public String getFact_estado() {
        return fact_estado;
    }

    public void setFact_estado(String fact_estado) {
        this.fact_estado = fact_estado;
    }

    public String getUsuarioFacturo() {
        return usuarioFacturo;
    }

    public void setUsuarioFacturo(String usuarioFacturo) {
        this.usuarioFacturo = usuarioFacturo;
    }

    public String getValorIva() {
        return valorIva;
    }

    public void setValorIva(String valorIva) {
        this.valorIva = valorIva;
    }

    public String getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(String valorServicio) {
        this.valorServicio = valorServicio;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Producto> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<Producto> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public String getValorIvaPro() {
        return valorIvaPro;
    }

    public void setValorIvaPro(String valorIvaPro) {
        this.valorIvaPro = valorIvaPro;
    }

    public String getValorProductos() {
        return valorProductos;
    }

    public void setValorProductos(String valorProductos) {
        this.valorProductos = valorProductos;
    }

    public String getValorTotalProd() {
        return valorTotalProd;
    }

    public void setValorTotalProd(String valorTotalProd) {
        this.valorTotalProd = valorTotalProd;
    }

    public String getFact_vlr_iva() {
        return fact_vlr_iva;
    }

    public void setFact_vlr_iva(String fact_vlr_iva) {
        this.fact_vlr_iva = fact_vlr_iva;
    }

    public String getFact_vlr_total() {
        return fact_vlr_total;
    }

    public void setFact_vlr_total(String fact_vlr_total) {
        this.fact_vlr_total = fact_vlr_total;
    }

    public String getFact_vlr_total_iva() {
        return fact_vlr_total_iva;
    }

    public void setFact_vlr_total_iva(String fact_vlr_total_iva) {
        this.fact_vlr_total_iva = fact_vlr_total_iva;
    }
}
