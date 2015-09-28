/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.inventarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Habitacion;
import co.com.hotel.logica.servicio.Adm_ServicioLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Inv_ServAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Habitacion habitacion;
    private ArrayList<Habitacion> rtaHabitacion;
    private Map<String, String> yesNo;
    private String accion;
    private String bandera;

    /**
     * Accion que recupera las habitaciones registradas por medio de filtros pre
     * establecidos
     *
     * @return
     */
    public String consGeneralServ() {
        Adm_ServicioLogica logica = new Adm_ServicioLogica();
        rtaHabitacion = logica.buscaHabitacionesXFiltros(habitacion);
        if(rtaHabitacion == null || rtaHabitacion.isEmpty()){
            addActionError("LA CONSULTA NO ARROJO NINGUN RESULTADO");
        }else{
            if(usuario.getPermisos().indexOf(".InSr4.")>=0){
                bandera = "S";
            }else{
                bandera = "N";
            }
        }
        return SUCCESS;
    }

    public void validate() {
        this.yesNo = new HashMap<String, String>();
        this.yesNo.put("S", "Si");
        this.yesNo.put("N", "No");
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public ArrayList<Habitacion> getRtaHabitacion() {
        return rtaHabitacion;
    }

    public void setRtaHabitacion(ArrayList<Habitacion> rtaHabitacion) {
        this.rtaHabitacion = rtaHabitacion;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }
}
