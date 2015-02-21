/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Habitacion;
import co.com.hotel.logica.servicio.Adm_ServicioLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.hotel.validacion.ValidaDuplicadosServ;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Adm_ServicioAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Habitacion habitacion;
    private Map<String, String> yesNo;
    
    /**
     * Funcion encargada de insertar los servicios 
     * @return 
     */
    public String insertHabitacion(){
        Adm_ServicioLogica logica = null;
        try {
            logica = new Adm_ServicioLogica();
            boolean rta = logica.insertaServicio(habitacion);
            if(rta){
                habitacion = null;
                addActionMessage("El servicio ha sido insertado correctamente");
            }else{
                addActionError("Error al insertar el servicio");
            }            
        } catch (Exception e) {
            return ERROR;
        }finally{
            logica = null;
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        boolean rtaValida = false;
        rtaValida = valida.validaNulo(habitacion.getNumHabi().trim());
        if (rtaValida == false) {
            addActionError("El campo numero de habitacion no puede ser nulo");
        }
        rtaValida = valida.validaNumerico(habitacion.getNumHabi());
        if (rtaValida == false) {
            addActionError("El campo numero de habitacion solo pueden ser numeros");
        }   
        rtaValida = valida.validaNumerico(habitacion.getIva());
        if (rtaValida == false) {
            addActionError("El campo iva de habitacion solo pueden ser numeros");
        }   
        if (habitacion.getNumMaxPers() == 0) {
            addActionError("El campo Num. Maximo de Personas no puede ser cero");
        } 
        ValidaDuplicadosServ dupli = new ValidaDuplicadosServ();
        if(!dupli.validaNumeroHabitacion(habitacion.getNumHabi())){
            addActionError("El numero de habitacion ya se encuentra registrado intente con otro");
        }
        valida = null;
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
        this.yesNo = new HashMap<String, String>();
        this.yesNo.put("S", "Si");
        this.yesNo.put("N", "No");
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
}
