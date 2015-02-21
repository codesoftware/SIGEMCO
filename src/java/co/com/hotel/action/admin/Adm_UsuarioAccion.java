/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.perfil.Adm_PerfilLogica;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.logica.usuarios.Adm_UsuarioLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author SOFIA
 */
public class Adm_UsuarioAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String estado;
    private String perfil;
    private String sedesFiltro;
    private Usuario usuaNuevo;
    private String modifica;

    private List<String> perfiles;
    private List<String> estadoUsuario;
    private String aliasUsuarioNuevo;
    private ArrayList<Usuario> resultConsGeneral = null;
    private Map<String, String> perfilesMap;
    private Map<String, String> estadoMap;
    private Map<String, String> sedes;

    public String execute() {
        Adm_UsuarioLogica usuaObj = null;
        Adm_SedeLogica sedeLogica = null;
        try {
            sedeLogica = new Adm_SedeLogica();
            usuaObj = new Adm_UsuarioLogica();
            this.sedes = sedeLogica.obtieneSedes();
            resultConsGeneral = usuaObj.consultaGeneral(estado, perfil, sedesFiltro);
            clearErrorsAndMessages();
            if (resultConsGeneral == null || resultConsGeneral.isEmpty()) {
                addActionError("NO SE ENCONTRARON RESULTADOS CON ESTE CRITERIO DE BUSQUEDA");
            }

        } catch (Exception e) {
        } finally {
            usuaObj = null;
        }
        return SUCCESS;
    }

    @SkipValidation
    public String updateUsuario() {
        clearErrorsAndMessages();
        Adm_PerfilLogica periflObj = null;
        Adm_UsuarioLogica usuarioObj = null;
        Adm_SedeLogica sedeLogica = null;
        //usuaNuevo.setUsuario(aliasUsuarioNuevo);
        try {
            periflObj = new Adm_PerfilLogica();
            this.perfilesMap = periflObj.obitnePerfilIdNombre();
            this.estadoMap = new HashMap<>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            sedeLogica = new Adm_SedeLogica();
            sedes = sedeLogica.obtieneSedes();
            if (validateUpdate()) {
                usuarioObj = new Adm_UsuarioLogica();
                boolean rtaAct = usuarioObj.actualizaUsuario(usuaNuevo);
                if (rtaAct) {
                    addActionMessage("El usuario fue actualizado correctamente");
                } else {
                    addActionError("Error al actualizar el usuario");
                }
                modifica = "N";
            } else {
                modifica = "S";
            }
        } catch (Exception e) {
            System.out.println("Error Adm_UsuarioAccion.updateUsuario " + e);
        } finally {
            periflObj = null;
            usuarioObj = null;
        }
        return SUCCESS;
    }

    public boolean validateUpdate() {
        ValidaCampos valida = new ValidaCampos();
        boolean rta = true;
        if (!valida.validaNulo(usuaNuevo.getNombre())) {
            addActionError("El NOMBRE  no puede ser nulo");
            rta = false;
        }
        if (!valida.validaNulo(usuaNuevo.getApellido())) {
            addActionError("El APELLIDO no puede ser nulo");
            rta = false;
        }
        if (!valida.validaNulo(usuaNuevo.getCedula())) {
            addActionError("La Cedula no puede ser nula");
        }
        return rta;
    }

    public void validate() {
        if (perfiles == null) {
            perfiles = new ArrayList<String>();
        }
        if (estadoUsuario == null) {
            estadoUsuario = new ArrayList<String>();
        }
        Adm_PerfilLogica periflObj = new Adm_PerfilLogica();
        Adm_UsuarioLogica usuarioObj = new Adm_UsuarioLogica();
        ArrayList<String> aux2 = usuarioObj.obtieneEstadoUsuario();
        ArrayList<String> aux = periflObj.obtieneNomPerfil();
        setPerfiles(aux);
        setEstadoUsuario(aux2);
        periflObj = null;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
    }

    public List<String> getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(List<String> estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public ArrayList<Usuario> getResultConsGeneral() {
        return resultConsGeneral;
    }

    public void setResultConsGeneral(ArrayList<Usuario> resultConsGeneral) {
        this.resultConsGeneral = resultConsGeneral;
    }

    public Usuario getUsuaNuevo() {
        return usuaNuevo;
    }

    public void setUsuaNuevo(Usuario usuaNuevo) {
        this.usuaNuevo = usuaNuevo;
    }

    public String getAliasUsuarioNuevo() {
        return aliasUsuarioNuevo;
    }

    public void setAliasUsuarioNuevo(String aliasUsuarioNuevo) {
        this.aliasUsuarioNuevo = aliasUsuarioNuevo;
    }

    public String getModifica() {
        return modifica;
    }

    public void setModifica(String modifica) {
        this.modifica = modifica;
    }

    public Map<String, String> getPerfilesMap() {
        return perfilesMap;
    }

    public void setPerfilesMap(Map<String, String> perfilesMap) {
        this.perfilesMap = perfilesMap;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public String getSedesFiltro() {
        return sedesFiltro;
    }

    public void setSedesFiltro(String sedesFiltro) {
        this.sedesFiltro = sedesFiltro;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }
}
