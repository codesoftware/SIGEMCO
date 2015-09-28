/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Producto;
import co.com.hotel.logica.productos.IngresaProductoNuevo;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.hotel.validacion.ValidaDuplicadodosProd;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author nicolas
 */
public class InProductosAction extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private Producto producto;
    private List<String> gravamen;

    @SkipValidation
    public String execute() {
        return SUCCESS;
    }

    public String ingresoProductoNuevo() {
        IngresaProductoNuevo ingreso = null;
        try {
            ingreso = new IngresaProductoNuevo();
            String rta = ingreso.IngresaProducto(producto, usuario.getUsuario());
            if (rta.equalsIgnoreCase("Ok")) {
                limpiarProducto();
                addActionMessage("Producto ingresado correctamente");
            } else {
                addActionError("Lamentablemente el producto no pudo ser ingresado po el siguiente error \n" + rta);
            }
        } catch (Exception e) {
            addActionError("Error al ingresar el producto");
        }
        return SUCCESS;
    }

    public void limpiarProducto() {
        producto.setReferencia("");
        producto.setCodigo("");
        producto.setNombre("");
        producto.setDescripcion("");
        producto.setIva("");
        producto.setPorcIva("");
        producto.setMarca("");
        producto.setCantidad("");
        producto.setCosto("");
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        ValidaDuplicadodosProd duplicados = new ValidaDuplicadodosProd();
        boolean rtaValida = false;
        rtaValida = valida.validaLetras(producto.getNombre());
        if (rtaValida == false) {
            addFieldError("producto.nombre", "El campo solo puede contener letras");
        }
        rtaValida = valida.validaLetras(producto.getDescripcion());
        if (rtaValida == false) {
            addFieldError("producto.descripcion", "El campo solo puede contener letras");
        }
        rtaValida = valida.validaFloat("" + producto.getPorcIva());
        if (rtaValida == false) {
            addFieldError("producto.porcIva", "El campo debe ser numerico o los decimales deben ser con .");
        }
        rtaValida = valida.validaFloat(producto.getCosto());
        if (rtaValida == false) {
            addFieldError("producto.costo", "El campo debe ser numerico o los decimales deben ser con .");
        }
        rtaValida = valida.validaNumerico(producto.getCantidad());
        if (rtaValida == false) {
            addFieldError("producto.cantidad", "El campo debe ser numerico");
        }
        System.out.println("valor del select: " + producto.getIva());
        if (producto.getIva().equalsIgnoreCase("-1")) {
            addFieldError("producto.iva", "Seleccione una de las opciones");
        }
        boolean dup = duplicados.verificaCodigo(producto.getCodigo());
        if (dup == false) {
            addFieldError("producto.codigo", "El codigo ingresado ya esta referenciado a otro producto");
        }        
        valida = null;
    }

    public Map getSession() {
        return session;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.gravamen = new ArrayList<String>();
        this.gravamen.add("SI_grava_Iva");
        this.gravamen.add("No_grava_Iva");
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<String> getGravamen() {
        return gravamen;
    }

    public void setGravamen(List<String> gravamen) {
        this.gravamen = gravamen;
    }
}
