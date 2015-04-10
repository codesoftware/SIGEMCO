/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.ajax;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.logica.CuentaLogica;
import co.com.sigemco.alfa.contabilidad.logica.GrupoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones ajax de la aplicacion
 *
 * @author Personal
 */
public class AjaxControllerCombos extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private static final long serialVersionUID = 1L;
    private String grup_clas;
    private String grup_nombre;
    private String grup_grup;
    private Usuario usuario;
    private Map session;

    /**
     * Funcion encargada de Buscar todos los grupos que perntenzcan a una clase
     */
    public void consultaComboGrupo() {
        GrupoLogica logica = new GrupoLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.consultaGrupoXClase(grup_clas);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion encargada de realizar la accion de buscar las cuentas que
     * pertenscan a un grupo
     */
    public void consultaComboCuenta() {
        CuentaLogica logica = null;
        try {
            logica = new CuentaLogica();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.obtienecuentasXGrupoJson(grup_grup);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGrup_clas() {
        return grup_clas;
    }

    public void setGrup_clas(String grup_clas) {
        this.grup_clas = grup_clas;
    }

    public String getGrup_nombre() {
        return grup_nombre;
    }

    public void setGrup_nombre(String grup_nombre) {
        this.grup_nombre = grup_nombre;
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

    public String getGrup_grup() {
        return grup_grup;
    }

    public void setGrup_grup(String grup_grup) {
        this.grup_grup = grup_grup;
    }
}
