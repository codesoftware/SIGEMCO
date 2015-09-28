/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.action.inventarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Habitacion;
import co.com.hotel.dto.PrecioHabitacion;
import co.com.hotel.logica.servicio.Adm_ServPrecioLogica;
import co.com.hotel.logica.servicio.Adm_ServicioLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class Inv_PrecioSvAccion extends ActionSupport implements SessionAware,UsuarioHabilitado{
    
    private Usuario usuario;
    private Map session;
    private Habitacion habitacion;
    private List<PrecioHabitacion> hisPrecio;
    
    public String consultaServicio(){
        Adm_ServicioLogica obj = null;
        try {
            obj = new Adm_ServicioLogica();
            habitacion = obj.buscaHabitacionXNumHab(habitacion.getNumHabi());
            if(habitacion == null){
                addActionError("No Existe ninguna Habitación con este Numero");
            }else{
                Adm_ServPrecioLogica precioHis = new Adm_ServPrecioLogica();
                hisPrecio = precioHis.buscaHistorialPrecios(habitacion.getIdHabitacion());                
            }
        } catch (Exception e) {
            System.out.println("Error Inv_PrecioSvAccion.consultaServicio " + e);
        }finally{
            obj = null;
        }
        return SUCCESS;
    }
    
    public String paramPrecioSv(){
        Adm_ServPrecioLogica logica = null;
        try {
            logica = new Adm_ServPrecioLogica();
            String rta = logica.insertarPrecioHabitacion(habitacion, usuario.getIdTius());
            if(rta.equalsIgnoreCase("Ok")){
                addActionMessage("Precio Parametrizado correctamente Habitacion No: " + habitacion.getNumHabi() + " Precio: "  + habitacion.getPrecio());
                habitacion = null;                
            }else{
                addActionError("Error al parametrizar el precio");
            }            
        } catch (Exception e) {
            System.out.println("Error Inv_PrecioSvAccion.paramPrecioSv " + e);
            e.printStackTrace();
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public List<PrecioHabitacion> getHisPrecio() {
        return hisPrecio;
    }

    public void setHisPrecio(List<PrecioHabitacion> hisPrecio) {
        this.hisPrecio = hisPrecio;
    }
}
