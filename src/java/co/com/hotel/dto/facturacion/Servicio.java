/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.dto.facturacion;

/**
 *
 * @author nicolas
 */
public class Servicio {
    
    private Reservacion reservacion;
    //Identificador primario de la tabla de detalles de servicio
    private String dtsv_dtsv;
    //Fecha en la cual se realizo el detalle de facturacion
    private String dtsv_fecha;
    //Valor el cual debe pagar el cliente
    private String dtsv_valor_venta;
    //Valor neto del servicio
    private String dtsv_valor_sv;
    //Valor del iva
    private String dtsv_valor_iva;
    //Identificador del la reservacion
    private String dtsv_rvha;
    private String numHabitacion;
    private String diasReserv;
    //precio de la habitacion por noche
    private String precioHabitacion;

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public String getDtsv_dtsv() {
        return dtsv_dtsv;
    }

    public void setDtsv_dtsv(String dtsv_dtsv) {
        this.dtsv_dtsv = dtsv_dtsv;
    }

    public String getDtsv_fecha() {
        return dtsv_fecha;
    }

    public void setDtsv_fecha(String dtsv_fecha) {
        this.dtsv_fecha = dtsv_fecha;
    }

    public String getDtsv_valor_venta() {
        return dtsv_valor_venta;
    }

    public void setDtsv_valor_venta(String dtsv_valor_venta) {
        this.dtsv_valor_venta = dtsv_valor_venta;
    }

    public String getDtsv_valor_sv() {
        return dtsv_valor_sv;
    }

    public void setDtsv_valor_sv(String dtsv_valor_sv) {
        this.dtsv_valor_sv = dtsv_valor_sv;
    }

    public String getDtsv_rvha() {
        return dtsv_rvha;
    }

    public void setDtsv_rvha(String dtsv_rvha) {
        this.dtsv_rvha = dtsv_rvha;
    }

    public String getDtsv_valor_iva() {
        return dtsv_valor_iva;
    }

    public void setDtsv_valor_iva(String dtsv_valor_iva) {
        this.dtsv_valor_iva = dtsv_valor_iva;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getDiasReserv() {
        return diasReserv;
    }

    public void setDiasReserv(String diasReserv) {
        this.diasReserv = diasReserv;
    }

    public String getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(String precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }
}
