/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.MarcaDto;
import co.com.sigemco.alfa.inventario.logica.MarcaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Personal
 */
public class MarcaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private MarcaDto marca;
    private ArrayList<MarcaDto> resultMarca = null;
    private Map<String, String> estadoMap;

    public String insertar(){
        MarcaLogica res = null;
        try {
            res = new MarcaLogica();
            String resultado = res.insertaMarca(marca);
            addActionMessage(resultado);
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

    public MarcaDto getMarca() {
        return marca;
    }

    public void setMarca(MarcaDto marca) {
        this.marca = marca;
    }

    public ArrayList<MarcaDto> getResultMarca() {
        return resultMarca;
    }

    public void setResultMarca(ArrayList<MarcaDto> resultMarca) {
        this.resultMarca = resultMarca;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }
    
}
