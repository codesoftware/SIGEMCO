/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.facturacion.dto.FacturaDto;
import co.com.sigemco.alfa.facturacion.logica.FacturaLogica;
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
 * @author ACER
 */
public class AjaxCancelaFacturaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Integer fact_fact;

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

    /**
     * Funcion la cual realiza la accion de buscar toda la informacion de una
     * factura
     */
    public void buscaFacturaXId() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            FacturaLogica objLogica = new FacturaLogica();
            FacturaDto objDto = objLogica.buscaFacturaXId(fact_fact);
            if (objDto != null) {
                rta.put("respuesta", "Ok");
                rta.put("objeto", objDto);
            } else {
                rta.put("respuesta", "Error");
            }
            objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion la cual se encarga de realizar la logica para cancelar una
     * factura
     */
    public void cancelaFactura() {
        Map<String, Object> rta = new HashMap<>();
        String objJson;
        Gson gson = new Gson();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            FacturaLogica objLogica = new FacturaLogica();
            Map<String, Object> rtaFun = objLogica.cancelaFactura(fact_fact);
            if (rtaFun != null ){
                rta.put("respuesta", "Factura cancelada correctamente");
            }else{
                rta.put("respuesta", "Error al cancelar la factura");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getFact_fact() {
        return fact_fact;
    }

    public void setFact_fact(Integer fact_fact) {
        this.fact_fact = fact_fact;
    }

}
