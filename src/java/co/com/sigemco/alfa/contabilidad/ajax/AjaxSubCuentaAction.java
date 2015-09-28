/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto;
import co.com.sigemco.alfa.contabilidad.logica.SubCuentaLogica;
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
public class AjaxSubCuentaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private String sbcu_sbcu;
    private String sbcu_nombre;
    private String sbcu_descripcion;
    private String sbcu_naturaleza;
    private Usuario usuario;
    private Map session;

    public void verificaEdicionSubcuenta() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            SubCuentaLogica logica = new SubCuentaLogica();
            String rta = logica.evaluaEdicionSubcuenta(sbcu_sbcu);
            Map<String, Object> respuesta = new HashMap<>();
            if (rta == null) {
                respuesta.put("respuesta", "Error al realizar la consulta");
            } else {
                if ("No".equalsIgnoreCase(rta)) {
                    respuesta.put("respuesta", "Error imposible realizar actulizacion de esta subcuenta ya que tiene movientos contables asociados");
                } else {
                    respuesta.put("respuesta", "Ok");
                    SubCuentaDto obj = logica.buscaSubcuentaXId(sbcu_sbcu);
                    respuesta.put("objeto", obj);
                }
            }
            String objJson = gson.toJson(respuesta);
            out.println(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizaSubCuenta(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            Map<String, Object> respuesta = new HashMap<>();
            String objJson = "";
            objJson = gson.toJson(respuesta);
            SubCuentaLogica logica = new SubCuentaLogica();
            SubCuentaDto objDto = new SubCuentaDto();
            objDto.setSbcu_sbcu(sbcu_sbcu);
            objDto.setSbcu_nombre(sbcu_nombre);
            objDto.setSbcu_descripcion(sbcu_descripcion);
            objDto.setSbcu_naturaleza(sbcu_naturaleza);
            String valida = logica.actualizaSubCuenta(objDto);
            if("Ok".equalsIgnoreCase(valida)){
                respuesta.put("respuesta", "Ok");
            }else{
                respuesta.put("resuesta", "Error");
            }
            out.println(objJson);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public String getSbcu_sbcu() {
        return sbcu_sbcu;
    }

    public void setSbcu_sbcu(String sbcu_sbcu) {
        this.sbcu_sbcu = sbcu_sbcu;
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

    public String getSbcu_nombre() {
        return sbcu_nombre;
    }

    public void setSbcu_nombre(String sbcu_nombre) {
        this.sbcu_nombre = sbcu_nombre;
    }

    public String getSbcu_descripcion() {
        return sbcu_descripcion;
    }

    public void setSbcu_descripcion(String sbcu_descripcion) {
        this.sbcu_descripcion = sbcu_descripcion;
    }

    public String getSbcu_naturaleza() {
        return sbcu_naturaleza;
    }

    public void setSbcu_naturaleza(String sbcu_naturaleza) {
        this.sbcu_naturaleza = sbcu_naturaleza;
    }

}
