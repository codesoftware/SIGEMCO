/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase la se encargara de hacer las acciones de un solo usuario
 * @author nicolas
 */
public class ConsUsuaAction extends ActionSupport implements UsuarioHabilitado, SessionAware{
    
    private Usuario usuario;
    private Map session;
    
    public String execute(){
        return SUCCESS;
    }

    public Map getSession() {
        return session;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }   
}
