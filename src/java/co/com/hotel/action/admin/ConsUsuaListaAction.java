/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.usuarios.ConsultaUsuarios;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Clase utilizada para las consultas de Usuarios en el sistema
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConsUsuaListaAction extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private String cedula;
    private String nombre;
    private String usuaConsulta;
    private String usuaConsultaAvn;
    // Array el cual contendra los usuarios encontrados
    private ArrayList<Usuario> resultUsuarios;
    private String banMostrarTabla = "false";

    @SkipValidation
    public String execute() {
        this.banMostrarTabla = "false";
        return SUCCESS;
    }

    @SkipValidation
    public String consultaXUsua() {
        ValidaCampos valida = new ValidaCampos();
        boolean val = true;
        
        if( (usuaConsulta.equalsIgnoreCase("") || usuaConsulta == null)){
            addActionError("El campo usuario es obligatorio");
            this.banMostrarTabla = "false";
        }else{
            val = valida.validaEspacio(usuaConsulta);
            if(val == false){
                addActionError("El campo usuario no debe tener espacios");
                this.banMostrarTabla = "false";
            }else{
                this.resultUsuarios = new ArrayList<Usuario>();
                ConsultaUsuarios consulta = new ConsultaUsuarios();                
                boolean validacion = consulta .buscaUsuariosXUsua(usuaConsulta);
                if(validacion){
                    this.resultUsuarios = consulta.getUsuaResult();
                    this.banMostrarTabla = "true";
                }else{
                    addActionError("Error al realizar la consulta");
                    this.banMostrarTabla = "false";
                }
            }
        }
        valida = null;
        return SUCCESS;
    }

    public String consultaUsuaAvn() {
        System.out.println("1111 Cedula:" + cedula + " Nombre:" + nombre + " Usuario" + usuaConsulta + " Usua avnz:" + usuaConsultaAvn);
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        boolean val = true;
        if ((usuaConsultaAvn.equalsIgnoreCase("") || usuaConsultaAvn == null) && (nombre.equalsIgnoreCase("") || nombre == null) && (cedula.equalsIgnoreCase("") || cedula == null)) {
            addActionError("Todos los campos  de la busqueda no pueden estar vacios");
            this.banMostrarTabla = "false";
        }
        val = valida.validaEspacio(usuaConsultaAvn);
        if (val == false){
            addActionError("El usuario no debe contener espacios");
            this.banMostrarTabla = "false";
        }
        valida = null;
    }

    public String getUsuaConsulta() {
        return usuaConsulta;
    }

    public void setUsuaConsulta(String usuaConsulta) {
        this.usuaConsulta = usuaConsulta;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getUsuaConsultaAvn() {
        return usuaConsultaAvn;
    }

    public void setUsuaConsultaAvn(String usuaConsultaAvn) {
        this.usuaConsultaAvn = usuaConsultaAvn;
    }

    public ArrayList<Usuario> getResultUsuarios() {
        return resultUsuarios;
    }

    public void setResultUsuarios(ArrayList<Usuario> resultUsuarios) {
        this.resultUsuarios = resultUsuarios;
    }

    public String getBanMostrarTabla() {
        return banMostrarTabla;
    }

    public void setBanMostrarTabla(String banMostrarTabla) {
        this.banMostrarTabla = banMostrarTabla;
    }
}
