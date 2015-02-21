/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Perfil;
import co.com.hotel.logica.perfil.Adm_PerfilLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Adm_PermisosAccion extends ActionSupport implements SessionAware, UsuarioHabilitado{
    
    private Usuario usuario;
    private Map session;
    private String perfil;
    private Perfil objPerfil;
    
    public String actualizaPerfil(){
        Adm_PerfilLogica logica= null;
        try {
            logica = new Adm_PerfilLogica();
            objPerfil = logica.buscaPerfilXId(perfil);
            if(objPerfil == null){
                addActionError("Perfil no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PermisosAccion.actualizaPerfil " + e);
        }finally{
            logica = null;
        }
        return SUCCESS;
    }
    
    public String actualizaPermisoPerfil(){
        Adm_PerfilLogica logica = null;        
        try {
            logica = new Adm_PerfilLogica();
            String rta = logica.actualizaPermisos(objPerfil.getId(), objPerfil.getPermisos());
            if(rta.equalsIgnoreCase("Ok")){
                addActionMessage("Perfil "+ objPerfil.getNombre() + " actualizado correctamente");
            }else{
                addActionError("Error al actualizar el perfil");
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PermisosAccion.actualizaPermisoPerfil " + e);
        }finally{
            logica = null;
        }
        return SUCCESS;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
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
    
    
    
}