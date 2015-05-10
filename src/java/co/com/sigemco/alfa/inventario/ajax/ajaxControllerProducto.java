/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.facturacion.dto.DetFactServicoDto;
import co.com.sigemco.alfa.facturacion.logica.DetFactServicioLogica;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
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
    private String dsha_dsha;

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
        ConteoProdLogica conteo = null;
        try {
            conteo = new ConteoProdLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = "";
            objJson = conteo.actualizaValorProdConteoJson(copr_copr, dska_dska, cantidad);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada de realizar
     */
    public void cierraConteo() {
        ConteoProdLogica logica = null;
        try {
            logica = new ConteoProdLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = "";
            objJson = logica.cierraConteo(copr_copr);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada de realizar la acion de iniciar el conteo de
     * inventarios actualizando la fecha de inicio y el estado del conteo
     */
    public void actualizaInicioConteo() {
        ConteoProdLogica logica = null;
        try {
            logica = new ConteoProdLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.actualizaInicioConteo(copr_copr);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada
     */
    public void adicionaProductoFactura() {
        ProductoLogica objLogica = null;
        try {
            objLogica = new ProductoLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            ProductoDto objDto = new ProductoDto();
            objDto.setCantidad(cantidad);
            objDto.setDska_dska(dska_dska);
            objDto = objLogica.obtieneCalculosProductoFactura(objDto);
            Map<String, Object> rta = new HashMap<String, Object>();
            if (objDto == null) {
                rta.put("respuesta", "Error");
            } else {
                rta.put("respuesta", "Ok");
                rta.put("objeto", objDto);
            }
            Gson gson = new Gson();
            String objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void simulaDetalleServicioFactura() {
        DetFactServicioLogica objLogica = null;
        try {
            objLogica = new DetFactServicioLogica();
            DetFactServicoDto obj = objLogica.simulaFacturacionServicio(cantidad, dsha_dsha);
            Map rta = new HashMap<String, Object>();
            if (obj == null) {
                rta.put("respuesta", "Error al simular el pago por la reserva");
            } else {
                rta.put("respuesta", "Ok");
                rta.put("Objeto", obj);
            }
            Gson gson = new Gson();
            String objJson = "";
            objJson = gson.toJson(rta);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
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

    public String getDsha_dsha() {
        return dsha_dsha;
    }

    public void setDsha_dsha(String dsha_dsha) {
        this.dsha_dsha = dsha_dsha;
    }

}
