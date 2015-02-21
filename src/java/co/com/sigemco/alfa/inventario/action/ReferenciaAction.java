/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.ReferenciaDTO;
import co.com.sigemco.alfa.inventario.logica.ReferenciaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ReferenciaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private ReferenciaDTO referencia;
    private ArrayList<ReferenciaDTO> resultReferencia = null;
    private Map<String, String> estadoMap;
    private Map<String, String> camara;
    private Map<String, String> pantalla;
    private Map<String, String> memoria;

    public void validate() {
        estadoMap = new HashMap<String, String>();
        this.estadoMap.put("A", "Activo");
        this.estadoMap.put("I", "Inactivo");

        this.camara = new HashMap<String, String>();
        this.camara.put("8", "MENOS DE 8");
        this.camara.put("13", "ENTRE 8 Y 13");
        this.camara.put("14", "MAS DE 13 ");

        this.pantalla = new HashMap<String, String>();
        this.pantalla.put("4", "MENOS DE 4");
        this.pantalla.put("5", "MAS DE 4");

        this.memoria = new HashMap<String, String>();
        this.memoria.put("16", "MENOS DE 16");
        this.memoria.put("32", "ENTRE 16 Y 32");
        this.memoria.put("33", "MAS DE 33");
    }

    public ArrayList<ReferenciaDTO> getResultReferencia() {
        return resultReferencia;
    }

    public void setResultReferencia(ArrayList<ReferenciaDTO> resultReferencia) {
        this.resultReferencia = resultReferencia;
    }

    public String insertar() {
        ReferenciaLogica res = null;
        try {
            res = new ReferenciaLogica();
            String resultado = res.insertarReferencia(referencia);
            addActionMessage(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String consultaReferencias() {
        ReferenciaLogica res = null;
        try {
            res = new ReferenciaLogica();
            resultReferencia = res.consultaReferencias(referencia);
            if (resultReferencia.size() <= 0) {
                addActionMessage("LA CONSULTA NO ARROJO NINGUN RESULTADO.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String consultaReferenciaEspecifica(){
        ReferenciaLogica res= null;
        try {
            res= new ReferenciaLogica();
            referencia =res.traeReferenciaEspecifica(referencia);
           
        } catch (Exception e) {
             addActionMessage("ERROR EN LA CONSULTA.");
            e.printStackTrace();
        }
        return SUCCESS;
    }
    public String actualizaReferencia(){
        String resultado="";
        ReferenciaLogica res= null;
        try {
            res= new ReferenciaLogica();
             resultado=res.actualizaReferenciaEspecifica(referencia);
             addActionMessage(resultado);
        } catch (Exception e) {
             addActionMessage("ERROR EN LA ACTUALIZACIÓN.");
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

    public ReferenciaDTO getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaDTO referencia) {
        this.referencia = referencia;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public Map<String, String> getCamara() {
        return camara;
    }

    public void setCamara(Map<String, String> camara) {
        this.camara = camara;
    }

    public Map<String, String> getPantalla() {
        return pantalla;
    }

    public void setPantalla(Map<String, String> pantalla) {
        this.pantalla = pantalla;
    }


    public Map<String, String> getMemoria() {
        return memoria;
    }

    public void setMemoria(Map<String, String> memoria) {
        this.memoria = memoria;
    }

}
