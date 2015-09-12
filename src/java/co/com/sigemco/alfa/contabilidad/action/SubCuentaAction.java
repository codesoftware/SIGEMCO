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
import co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto;
import co.com.sigemco.alfa.contabilidad.logica.SubCuentaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Funcion encargada de las acciones de Subcuenta
 *
 * @author SISCOMPUTO
 */
public class SubCuentaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map Session;
    private CuentaDto cuenta;
    private GrupoDto grupo;
    private ClaseDto clase;
    private Map<String, String> naturaleza;
    private SubCuentaDto subCuenta;
    private String accion;

    /**
     * Funcion encargada de realizar la accion de insertar una subcuenta
     *
     * @return
     */
    public String insertSubCuenta() {
        try {
            SubCuentaLogica subCuentaLogica = new SubCuentaLogica();
            String rta = subCuentaLogica.insertSubCuenta(subCuenta, clase, cuenta, grupo);
            if (rta.equalsIgnoreCase("ok")) {
                cuenta.setSubCuenta(subCuentaLogica.obtieneSubCuentaXCuenta(cuenta.getCuen_cuen()));
                addActionMessage("SubCuenta insertada correctamente");
            } else {
                addActionError("Error al insertar SubCuenta");
            }
            subCuenta = new SubCuentaDto();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de redireccionar la insercion de
     * la subcuenta
     *
     * @return
     */
    public String guadaDatos() {
        this.naturaleza = new HashMap<String, String>();
        try {
            naturaleza.put("D", "DEBITO");
            naturaleza.put("C", "CREDITO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public void validate(){
        if("insetarSubCuenta".equalsIgnoreCase(accion)){
            if(subCuenta.getSbcu_codigo().length()>2){
                addActionError("Error de validacion el codigo no puede tener mas de dos caracteres");
                this.guadaDatos();
            }
            if("-1".equalsIgnoreCase(subCuenta.getSbcu_naturaleza())){
                addActionError("Por favor seleccione una naturaleza para la subcuenta");
                this.guadaDatos();
            }
            SubCuentaLogica logicaSbcu = new SubCuentaLogica();
            int conteo = logicaSbcu.cuentaSubcuentasCuenta(cuenta.getCuen_cuen());
            if(conteo>=99){
                addActionError("El numero de Subcuentas no puede exceder de 99 por favor reestructure las subcuentas para esta cuenta");
                this.guadaDatos();
            }
            logicaSbcu = null;
            
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Map getSession() {
        return Session;
    }

    public void setSession(Map Session) {
        this.Session = Session;
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

    public Map<String, String> getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(Map<String, String> naturaleza) {
        this.naturaleza = naturaleza;
    }

    public SubCuentaDto getSubCuenta() {
        return subCuenta;
    }

    public void setSubCuenta(SubCuentaDto subCuenta) {
        this.subCuenta = subCuenta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
