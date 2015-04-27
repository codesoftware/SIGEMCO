/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.logica.CierreDiarioLogica;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author Personal
 */
public class CierreDiarioAction extends ActionSupport implements UsuarioHabilitado, SessionAware, ServletRequestAware {

    private Usuario usuario;
    private Map session;
    private String sede;
    private String fecha;
    private String CierreDiarioDao;
    private String accion;
    private Map<String, String> sedes;
    private HttpServletRequest request;

    @SkipValidation
    public String execute() {
        return SUCCESS;
    }

    public String insertaCierre() {
        CierreDiarioLogica logica = new CierreDiarioLogica();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        System.out.println("ip" + ip);
        System.out.println("host" +host);
        try {
            String rta = logica.insertaCierreDiario(usuario.getIdTius(), sede, fecha);
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

    public void validate() {
        if ("cierreDiario".equalsIgnoreCase(accion)) {
            Adm_SedeLogica sedeLogica = null;
            sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();

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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
