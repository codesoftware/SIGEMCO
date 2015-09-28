/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;
import co.com.sigemco.alfa.inventario.logica.PantallaPrincipalLogica;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class PantallaPrincipalAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private PantallaPrincipalDto pantalla;
    private ArrayList<PantallaPrincipalDto> productosPantalla;
    private ArrayList<PantallaPrincipalDto> recetasPantalla;
    private String tipoProducto;
    private String posicion;
    private String accion;

    /**
     * Funcion encargada de realizar la adicion de productos o recetas a la
     * pantalla principal de facturacion
     *
     * @return
     */
    public String adicionaPantallaPrincipal() {
        try {
            PantallaPrincipalLogica objLogica = new PantallaPrincipalLogica();
            String ruta = objLogica.guardaImagen(pantalla, tipoProducto);
            String valida = objLogica.guardaProducto(pantalla.getPpfa_codigo(), tipoProducto, Integer.parseInt(posicion),ruta);
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("Producto adicionado correctamente");
                productosPantalla = objLogica.buscaProductos();
                recetasPantalla = objLogica.buscaRecetas();
            } else {
                productosPantalla = objLogica.buscaProductos();
                recetasPantalla = objLogica.buscaRecetas();
                addActionError(valida);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error " + e);
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if ("addPantallaPrincipal".equalsIgnoreCase(accion)) {
            if (!valida.validaNulo(pantalla.getPpfa_codigo())) {
                addActionError("El codigo no puede ser nulo");
            } else if (!valida.validaNulo(posicion)) {
                addActionError("La posicion no puede ser nulo");
            }

        }
    }

    public PantallaPrincipalDto getPantalla() {
        return pantalla;
    }

    public void setPantalla(PantallaPrincipalDto pantalla) {
        this.pantalla = pantalla;
    }

    public ArrayList<PantallaPrincipalDto> getProductosPantalla() {
        return productosPantalla;
    }

    public void setProductosPantalla(ArrayList<PantallaPrincipalDto> productosPantalla) {
        this.productosPantalla = productosPantalla;
    }

    public ArrayList<PantallaPrincipalDto> getRecetasPantalla() {
        return recetasPantalla;
    }

    public void setRecetasPantalla(ArrayList<PantallaPrincipalDto> recetasPantalla) {
        this.recetasPantalla = recetasPantalla;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

}
