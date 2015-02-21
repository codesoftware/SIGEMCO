/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import co.com.sigemco.alfa.inventario.logica.ProductoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones de los productos en el sistema
 *
 * @author Nicolas
 */
public class ProductoAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private List<ProductoDto> listProductos;
    private ProductoDto producto;
    private String perActualizar;   //Indica si el usuario que realiza determinada accion tiene permiso de actualizar el producto
    private String perParamPrecio;  //Indica si el usuario que realiza determinada accion tiene permiso de parametrizar el precio
    private String accion;

    public String consultaGenXFiltros() {
        ProductoLogica logica = null;
        try {
            logica = new ProductoLogica();
            listProductos = logica.buscaProductosXFiltro(producto);
            if (listProductos == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                int acceso = usuario.getPermisos().indexOf(".InPr5.");
                if (acceso >= 0) {
                    perActualizar = "S";
                } else {
                    perActualizar = "N";
                }
                acceso = usuario.getPermisos().indexOf(".InPr6.");
                if (acceso >= 0) {
                    perParamPrecio = "S";
                } else {
                    perParamPrecio = "N";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de validar los cmpos de cada uno de las acciones que
     * realice el usuario
     */
    public void validate() {
        if (accion.equalsIgnoreCase("consultaGen")) {
        }
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

    public List<ProductoDto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<ProductoDto> listProductos) {
        this.listProductos = listProductos;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    public String getPerActualizar() {
        return perActualizar;
    }

    public void setPerActualizar(String perActualizar) {
        this.perActualizar = perActualizar;
    }

    public String getPerParamPrecio() {
        return perParamPrecio;
    }

    public void setPerParamPrecio(String perParamPrecio) {
        this.perParamPrecio = perParamPrecio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
