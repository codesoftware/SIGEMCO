/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.general.CambioContrasena;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author nicolas
 */
public class InicioAdmin extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Map session;
    private Usuario usuario;
    private String contra;
    private String contra2;

    @SkipValidation
    public String execute() {
        if (usuario != null) {
            return SUCCESS;
        }
        return ERROR;
    }

    public String cambioContra() {
        CambioContrasena cambio = new CambioContrasena();
        System.out.println("este es el usuario: " + usuario.getUsuario());
        if (cambio.cambiarContra(usuario.getUsuario(), this.contra)) {
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if (!valida.validaDuplicados(contra, contra2)) {
            addActionError("Las contraseñas no coinciden");
        }
        if (!valida.validaCantidadCaracteres(contra, 16, 8)) {
            addActionError("El numero de caracteres debe estar entre 8 y 16");
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = session;
    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getContra2() {
        return contra2;
    }

    public void setContra2(String contra2) {
        this.contra2 = contra2;
    }
}
