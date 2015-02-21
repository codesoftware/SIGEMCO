/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.action.reportes;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.DetalleInventarioProd;
import co.com.hotel.dto.Producto;
import co.com.hotel.logica.productos.Inv_ProductoLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Rep_ReportesAcciones extends ActionSupport implements SessionAware, UsuarioHabilitado {
    
    private Usuario usuario;
    private Map session; 
    private Producto producto;
    private ArrayList<DetalleInventarioProd> detalleInv;
    private String fechaFin;
    private String fechaIni;
            
    
    public String execute(){
        Inv_ProductoLogica logica= new Inv_ProductoLogica();
        detalleInv = logica.buscaDetalleInventario(producto.getId(), fechaIni, fechaFin);
        producto = logica.buscaProductoXId(producto.getId());
        return SUCCESS;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ArrayList<DetalleInventarioProd> getDetalleInv() {
        return detalleInv;
    }

    public void setDetalleInv(ArrayList<DetalleInventarioProd> detalleInv) {
        this.detalleInv = detalleInv;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }
}
