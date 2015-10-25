/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.admin.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.empresa.Emp_EmpresaLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.admin.logica.AdministracionLogica;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones ajax de la administracion del
 * software
 *
 * @author Nicolas
 * @version 1.0
 */
public class AjaxAdministracionAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;

    /**
     * Metodo con el cual busco la parametrizacion del inicio del sistema
     */
    public void buscaParametroInicio() {
        AdministracionLogica objLogica = new AdministracionLogica();
        Map<String,String> respuesta;
        respuesta = new HashMap<>();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String objJson;
            String rta = objLogica.obtieneParametrizacionInicio();
            if(rta != null){
                respuesta.put("respuesta", "Ok");
                respuesta.put("para", rta);
            }else{
                respuesta.put("respuesta", "Error al obtener la parametrizacion");
            }
            objJson = gson.toJson(respuesta);
            out.println(objJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Funcion con la cual se buscan los productos que se 
     */
    public void buscaProductosInicio() {
        AdministracionLogica objLogica = new AdministracionLogica();
        Map<String,Object> respuesta;
        respuesta = new HashMap<>();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String objJson;
            List<ProductoDto> rta = objLogica.buscaProdProximosTerminar();
            if(rta != null){
                respuesta.put("respuesta", "Ok");
                respuesta.put("para", rta);
            }else{
                respuesta.put("respuesta", "Error obtener los productos");
            }
            objJson = gson.toJson(respuesta);
            out.println(objJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Funcion con la cual obtengo el iva de las compras
     */
    public void buscaIvaCompras() {
        Emp_EmpresaLogica objLogica = new Emp_EmpresaLogica();
        Map<String,Object> respuesta;
        respuesta = new HashMap<>();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String objJson;
            String rta = objLogica.obtieneValorIvaCompras();
            if(rta != null){
                respuesta.put("respuesta", "Ok");
                respuesta.put("ivacompras", rta);
            }else{
                respuesta.put("respuesta", "Error obtener los productos");
            }
            objJson = gson.toJson(respuesta);
            out.println(objJson);
        } catch (IOException e) {
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

}
