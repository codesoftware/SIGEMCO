/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.dto.MoviContableDto;
import co.com.sigemco.alfa.contabilidad.logica.ClaseLogica;
import co.com.sigemco.alfa.contabilidad.logica.MoviContableLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Personal
 */
public class MovimientoContableAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private ArrayList<MoviContableDto> resultMoviContable = null;
    private MoviContableDto moviContable;
    private Map<String, String> clasePUC;
    private Map<String, String> grupoPUC;
    private Map<String, String> cuentaPUC;

    public void validate() {
        ClaseLogica cl = new ClaseLogica();
        List<ClaseDto> rta = new ArrayList<ClaseDto>();
        rta = cl.consultaGeneralActivo();
        clasePUC = new HashMap<String, String>();
        for (int i = 0; i < rta.size(); i++) {
            this.clasePUC.put(rta.get(i).getClas_clas(), rta.get(i).getClas_nombre());
        }

        this.grupoPUC = new HashMap<String, String>();
        this.cuentaPUC = new HashMap<String, String>();

    }

    public String consultaMovContable() {
        MoviContableLogica res = null;
        try {
            res = new MoviContableLogica();
            resultMoviContable = res.consultaMoviContable(moviContable);
            if (resultMoviContable.size() <= 0) {
                addActionMessage("LA CONSULTA NO ARROJO NINGUN RESULTADO.");
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public ArrayList<MoviContableDto> getResultMoviContable() {
        return resultMoviContable;
    }

    public void setResultMoviContable(ArrayList<MoviContableDto> resultMoviContable) {
        this.resultMoviContable = resultMoviContable;
    }

    public MoviContableDto getMoviContable() {
        return moviContable;
    }

    public void setMoviContable(MoviContableDto moviContable) {
        this.moviContable = moviContable;
    }

    public Map<String, String> getClasePUC() {
        return clasePUC;
    }

    public void setClasePUC(Map<String, String> clasePUC) {
        this.clasePUC = clasePUC;
    }

    public Map<String, String> getGrupoPUC() {
        return grupoPUC;
    }

    public void setGrupoPUC(Map<String, String> grupoPUC) {
        this.grupoPUC = grupoPUC;
    }

    public Map<String, String> getCuentaPUC() {
        return cuentaPUC;
    }

    public void setCuentaPUC(Map<String, String> cuentaPUC) {
        this.cuentaPUC = cuentaPUC;
    }

}
