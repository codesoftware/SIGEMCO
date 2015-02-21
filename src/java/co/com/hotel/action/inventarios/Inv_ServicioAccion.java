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
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Inv_ServicioAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private String subAccion;
    private Map<String, String> yesNo;
    private Habitacion habitacion;
    private String bandera;

    public String updateServicio() {
        Adm_ServicioLogica logica = new Adm_ServicioLogica();
        clearErrorsAndMessages();
        if(subAccion.equalsIgnoreCase("consulta")){
            habitacion = logica.buscaHabitacionXFiltros(habitacion);
            if(habitacion == null){
                addActionError("La consulta no arrojo ningun resultado");
                bandera = "N";
            }else{
                bandera = "S";
            }            
        }else if(subAccion.equalsIgnoreCase("actualizar")){
            String rta =logica.actualizaServicio(habitacion);
            if(rta.equalsIgnoreCase("Ok")){
                addActionMessage("Registro actualizado correctamente");
                habitacion.reset();
            }else{
                addActionError("Error al actualizar el registro");
            }
        }
        return SUCCESS;
    }

    public void validate() {
        if (accion.equalsIgnoreCase("updServicio")) {
            this.yesNo = new HashMap<String, String>();
            this.yesNo.put("S", "Si");
            this.yesNo.put("N", "No");
            if(subAccion.equalsIgnoreCase("actualizar")){
                ValidaCampos valida = new ValidaCampos();
                if(!valida.validaNulo(habitacion.getIdHabitacion())){
                    addActionError("El campo oculto Id ha sido eliminado por favor consulte de nuevo");
                }
            }
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getSubAccion() {
        return subAccion;
    }

    public void setSubAccion(String subAccion) {
        this.subAccion = subAccion;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }
}
