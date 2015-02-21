/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.dto.CuentaDto;
import co.com.sigemco.alfa.contabilidad.dto.GrupoDto;
import co.com.sigemco.alfa.contabilidad.logica.ClaseLogica;
import co.com.sigemco.alfa.contabilidad.logica.CuentaLogica;
import co.com.sigemco.alfa.contabilidad.logica.GrupoLogica;
import co.com.sigemco.alfa.contabilidad.logica.SubCuentaLogica;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de las acciones de las cuentas del PUC
 *
 * @author Daniel
 */
public class CuentaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private CuentaDto cuenta;
    private GrupoDto grupo;
    private ClaseDto clase;

    /**
     * Action encargado de realizar la busqueda de las sub cuentas  que pertenecen a
     * una cuenta
     *
     * @return
     */
    public String buscarSubCuentaXCuenta() {
        ClaseLogica claseLogica = null;
        try {
            claseLogica = new ClaseLogica();
            clase = claseLogica.obtieneClaseXId(clase);
            if (clase == null) {
                addActionError("Error al realizar la consulta");
            } else {
                GrupoLogica grupoLogica = new GrupoLogica();
                grupo = grupoLogica.obtieneGrupoXId(grupo.getGrup_grup());
                if (grupo == null) {
                    addActionError("Error al realizar la consulta");
                } else {
                    CuentaLogica cuentaLogica = new CuentaLogica();
                    cuenta = cuentaLogica.obtieneCuentaXId(cuenta.getCuen_cuen());
                    if (cuenta == null) {
                        addActionError("Error al realizar la consulta");
                    } else {
                        SubCuentaLogica subCuentaLogica = new SubCuentaLogica();
                        cuenta.setSubCuenta(subCuentaLogica.obtieneSubCuentaXCuenta(cuenta.getCuen_cuen()));
                    }
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

    public CuentaDto getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDto cuenta) {
        this.cuenta = cuenta;
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
