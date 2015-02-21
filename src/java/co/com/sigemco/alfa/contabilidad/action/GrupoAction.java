/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.dto.GrupoDto;
import co.com.sigemco.alfa.contabilidad.logica.ClaseLogica;
import co.com.sigemco.alfa.contabilidad.logica.CuentaLogica;
import co.com.sigemco.alfa.contabilidad.logica.GrupoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nicolas
 */
public class GrupoAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private ClaseDto clase;
    private GrupoDto grupo;

    /**
     * Funcion encargada de realizar la accion de buscar las cuentas teniendo
     * como base un grupo
     *
     * @return
     */
    public String buscarCuentasXGrupo() {
        ClaseLogica logicaClase = null;
        try {
            logicaClase = new ClaseLogica();
            clase = logicaClase.obtieneClaseXId(clase);
            if (clase == null) {
                addActionError("Error al realizar la consulta");
            } else {
                GrupoLogica logicaGrupo = new GrupoLogica();
                grupo = logicaGrupo.obtieneGrupoXId(grupo.getGrup_grup());
                if (grupo == null) {
                    addActionError("Error al realizar la consulta por grupo");
                } else {
                    CuentaLogica logicaCuenta = new CuentaLogica();
                    grupo.setCuenta(logicaCuenta.obtienecuentasXGrupo(grupo.getGrup_grup()));
                }
            }
        } catch (Exception e) {
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

    public GrupoDto getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoDto grupo) {
        this.grupo = grupo;
    }

    public ClaseDto getClase() {
        return clase;
    }

    public void setClase(ClaseDto clase) {
        this.clase = clase;
    }

}
