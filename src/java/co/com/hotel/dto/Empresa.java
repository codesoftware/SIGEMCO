/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.dto;

/**
 *
 * @author nicolas
 */
public class Empresa {

    private String nombre;
    private String nit;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String iva;
    private String ivaVentas;
    private String diasVen;//Dias de vencimiento para las notificaciones
    private String comisionPrepago;
    private String comisionPostpago;
    private String comisionReposicion;
    private String subcuentaBancos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getDiasVen() {
        return diasVen;
    }

    public void setDiasVen(String diasVen) {
        this.diasVen = diasVen;
    }

    public String getComisionPrepago() {
        return comisionPrepago;
    }

    public void setComisionPrepago(String comisionPrepago) {
        this.comisionPrepago = comisionPrepago;
    }

    public String getComisionPostpago() {
        return comisionPostpago;
    }

    public void setComisionPostpago(String comisionPostpago) {
        this.comisionPostpago = comisionPostpago;
    }

    public String getComisionReposicion() {
        return comisionReposicion;
    }

    public void setComisionReposicion(String comisionReposicion) {
        this.comisionReposicion = comisionReposicion;
    }

    public String getSubcuentaBancos() {
        return subcuentaBancos;
    }

    public void setSubcuentaBancos(String subcuentaBancos) {
        this.subcuentaBancos = subcuentaBancos;
    }

    public String getIvaVentas() {
        return ivaVentas;
    }

    public void setIvaVentas(String ivaVentas) {
        this.ivaVentas = ivaVentas;
    }

}
