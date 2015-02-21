/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.logica.ClaseLogica;
import co.com.sigemco.alfa.contabilidad.logica.GrupoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de las acciones de las clases del PUC
 *
 * @author Nicolas
 */
public class ClaseAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private ClaseDto clase;

    /**
     * Action encargado de realizar la busqueda de los grupos que pertenecen a
     * una clase
     *
     * @return
     */
    public String buscaGruposPorClase() {
        try {
            ClaseLogica logicaClase = new ClaseLogica();
            clase = logicaClase.obtieneClaseXId(clase);
            if (clase == null) {
                addActionError("Clase inexiste por favor verifique el id ");
            }else{
                GrupoLogica logicaGrupo = new GrupoLogica();
                clase.setGrupo(logicaGrupo.consultaGeneralActivo(clase.getClas_clas()));
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

    public ClaseDto getClase() {
        return clase;
    }

    public void setClase(ClaseDto clase) {
        this.clase = clase;
    }
}
