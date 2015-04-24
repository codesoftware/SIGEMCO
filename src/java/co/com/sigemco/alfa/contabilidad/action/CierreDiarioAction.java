/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.logica.CierreDiarioLogica;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author Personal
 */
public class CierreDiarioAction extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private String sede;
    private String fecha;
    private String CierreDiarioDao;

    @SkipValidation
    public String execute() {
        return SUCCESS;
    }

    public String insertaProducto() {
        CierreDiarioLogica logica = new CierreDiarioLogica();
        try {
            String rta = logica.insertaCierreDiario(usuario.getUsuario(), sede, fecha);
            if (rta.equalsIgnoreCase("OK")) {
                addActionMessage("Cierre realizado correctamente");
            } else {
                addActionError("Lamentablemente el cierre no pudo ser realizado por el siguiente error \n" + rta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al ingresar el producto");
        }
        return SUCCESS;
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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCierreDiarioDao() {
        return CierreDiarioDao;
    }

    public void setCierreDiarioDao(String CierreDiarioDao) {
        this.CierreDiarioDao = CierreDiarioDao;
    }

}
