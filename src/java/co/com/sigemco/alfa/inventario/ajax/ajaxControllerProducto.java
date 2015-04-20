/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.logica.ConteoProdLogica;
import co.com.sigemco.alfa.inventario.logica.ProductoLogica;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class ajaxControllerProducto extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String dska_codigo;
    private String cantidad;
    private String dska_dska;
    private String copr_copr;

    /**
     * Funcion encargada de realizar la accion de consulta de productos por
     * codigo
     */
    public void consultaProductoPorCodigo() {
        ProductoLogica objLogica = null;
        try {
            objLogica = new ProductoLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            Object objeto = objLogica.buscaProductoXCodigo(dska_codigo);
            Map<String, Object> mapa = new HashMap<String, Object>();
            if (objeto != null) {
                mapa.put("respuesta", "Ok");
                mapa.put("objeto", objeto);
            } else {
                mapa.put("respuesta", "Error");
            }
            Gson gson = new Gson();
            String objJson = gson.toJson(mapa);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada de realizar la accion de para actualizar el conteo de
     * productos
     */
    public void actualizaConteoProducto() {
        Map<String, Object> mapa = new HashMap<String, Object>();
        ConteoProdLogica conteo = null;
        try {
            conteo = new ConteoProdLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            mapa.put("respuesta", "Ok");
            Gson gson = new Gson();
            String objJson = gson.toJson(mapa);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
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

    public String getDska_codigo() {
        return dska_codigo;
    }

    public void setDska_codigo(String dska_codigo) {
        this.dska_codigo = dska_codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String getCopr_copr() {
        return copr_copr;
    }

    public void setCopr_copr(String copr_copr) {
        this.copr_copr = copr_copr;
    }
}
