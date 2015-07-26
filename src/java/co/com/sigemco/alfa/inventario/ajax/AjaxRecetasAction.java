/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.ProductoRecetaDto;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import co.com.sigemco.alfa.inventario.logica.ProductoRecetaLogica;
import co.com.sigemco.alfa.inventario.logica.RecetaLogica;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class AjaxRecetasAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String rece_rece;
    private String rece_dska;
    private String rece_codigo;

    /**
     * Funcion encargada de realizar la accion de adicionar un producto a una
     * receta
     */
    public void adicionaProductoReceta() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            RecetaLogica objLogica = new RecetaLogica();
            int numProd = objLogica.validaProductoReceta(rece_rece, rece_dska);
            String valida = "";
            if (numProd == 0) {
                valida = objLogica.insertaProductoReceta(rece_rece, rece_dska, usuario.getIdTius());
            } else {
                valida = objLogica.adicionaCantidadProductoReceta(rece_rece, rece_dska);
            }
            if ("Ok".equalsIgnoreCase(valida)) {
                rta.put("respuesta", "Ok");
            } else {
                rta.put("respuesta", valida);
            }
            objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion la cual realiza la accion de buscar los productos de una receta
     */
    public void buscaProductosReceta() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            ProductoRecetaLogica objLogica = new ProductoRecetaLogica();
            ArrayList<ProductoRecetaDto> lista = objLogica.obtienePrdoctosReceta(rece_rece);
            if (lista != null) {
                rta.put("respuesta", "Ok");
                rta.put("objeto", lista);
            } else {
                rta.put("respuesta", "Error al buscar la lista de productos o la receta no tiene productos aún");
            }

            objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada de realizar la accion de eliminar un producto de una
     * receta
     */
    public void eliminaProductoReceta() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            RecetaLogica objLogica = new RecetaLogica();
            int numProd = objLogica.obtieneCantidadProductoReceta(rece_rece, rece_dska);
            String valida = "";
            if (numProd == 1) {
                valida = objLogica.eliminaProdReceta(rece_rece, rece_dska);
            } else {
                valida = objLogica.reduceProdReceta(rece_rece, rece_dska);
            }
            if ("Ok".equalsIgnoreCase(valida)) {
                rta.put("respuesta", "Ok");
            } else {
                rta.put("respuesta", valida);
            }

            objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcion encargada de realizar la accion de eliminar un producto de una
     * receta
     */
    public void buscaRecetaXCodigo() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            RecetaLogica objLogica = new RecetaLogica();
            RecetaDto objDto = new RecetaDto();
            objDto.setRece_codigo(rece_codigo);
            RecetaDto objRta = objLogica.consultaGeneralXCodigo(objDto);
            if (objRta != null) {
                rta.put("respuesta", "Ok");
                rta.put("objeto", objRta);
            } else {
                rta.put("respuesta", "La receta que busca no existe");
            }
            objJson = gson.toJson(rta);
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

    public String getRece_rece() {
        return rece_rece;
    }

    public void setRece_rece(String rece_rece) {
        this.rece_rece = rece_rece;
    }

    public String getRece_dska() {
        return rece_dska;
    }

    public void setRece_dska(String rece_dska) {
        this.rece_dska = rece_dska;
    }

    public String getRece_codigo() {
        return rece_codigo;
    }

    public void setRece_codigo(String rece_codigo) {
        this.rece_codigo = rece_codigo;
    }

}
