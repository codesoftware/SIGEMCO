/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Sede;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author Personal
 */
public class Adm_SedeAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private Sede sede;
    private ArrayList<Sede> resultSede = null;
    private Map<String, String> estadoMap;

    public String insertarSede() {
        Adm_SedeLogica ingSede = null;
        try {
            ingSede = new Adm_SedeLogica();
            String resultado = ingSede.insertarSede(sede, usuario.getIdTius());
            if (resultado.equalsIgnoreCase("OK")) {
                addActionMessage("Sede correctamente instalada");
                sede = null;
            } else {
                addActionMessage("Error al Insertar la sede");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {
        if (accion.equalsIgnoreCase("insertar")) {

        }
    }

    @SkipValidation
    public String consultar() {
        System.out.println("Entra Sede" + sede.getSede_estado());
        try {

            if (sede.getSede_estado().equals("A") || sede.getSede_estado().equals("I") || sede.getSede_estado().equals("-1")) {
                Adm_SedeLogica perfilObj = new Adm_SedeLogica();
                resultSede = perfilObj.consultaGeneralSede(sede.getSede_estado());
                if (resultSede == null || resultSede.isEmpty()) {
                    addActionError("La consulta no ha arrojado ningun resultado");
                }
            } else {
                System.out.println("entro por aqui");
                addActionError("EL FILTRO INGRESADO NO ES PERMITIDO..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String actualizaSede() {
        this.estadoMap = new HashMap<String, String>();
        this.estadoMap.put("A", "Activo");
        this.estadoMap.put("I", "Inactivo");
        Adm_SedeLogica logica = null;
        try {
            logica = new Adm_SedeLogica();
            String rta = logica.actualizaSede(this.sede);
            if (!rta.equalsIgnoreCase("Ok")) {

                addActionError("Error al actualizar una sede.");
            } else {
                addActionMessage("Sede actualizada correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String consultaSedeEspecifico() {
        this.estadoMap = new HashMap<String, String>();
        this.estadoMap.put("A", "Activo");
        this.estadoMap.put("I", "Inactivo");
        Adm_SedeLogica logica = null;
        try {
            logica = new Adm_SedeLogica();
            sede = logica.consultarSedeEspecifico(this.sede.getSede_sede());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;

    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public ArrayList<Sede> getResultSede() {
        return resultSede;
    }

    public void setResultSede(ArrayList<Sede> resultSede) {
        this.resultSede = resultSede;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

}
