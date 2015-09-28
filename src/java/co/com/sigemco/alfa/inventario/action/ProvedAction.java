/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.ProvedDto;
import co.com.sigemco.alfa.inventario.logica.ProveedorLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author john
 */
public class ProvedAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private ProvedDto proved;
    private ArrayList<ProvedDto> resultProved = null;
    private Map<String, String> estadoMap;

    public void validate() {
        estadoMap = new HashMap<String, String>();
        this.estadoMap.put("A", "Activo");
        this.estadoMap.put("I", "Inactivo");
    }
    /**
     * Metodo que invoca a la logica para insertar un proveedor
     * @return 
     */
        public String insertar() {
        ProveedorLogica res = null;
        try {
            res = new ProveedorLogica();
            String resultado = res.insertaProovedor(proved);
            if("Ok".equalsIgnoreCase(resultado)){
                addActionMessage("EL PROVEEDOR FUE INSERTADO CORRECTAMENTE");
                proved = null;
            }else{
                addActionError("ERROR AL INSERTAR EL PROVEEDOR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
        /**
         * Metodo que invoca la logica de la consulta de proveedores
         * @return 
         */
        
        public String consultaProveedores() {
        ProveedorLogica res = null;
        try {
            res = new ProveedorLogica();
            resultProved= res.consultaProveedores(proved);
            if (resultProved.size() <= 0) {
                addActionMessage("LA CONSULTA NO ARROJO NINGUN RESULTADO.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;

    }
    
    /**
     * Metodo que invoca la logica para consultar un proveedor especifico
     * @return 
     */
    public String consultaProveedorEspecifico(){
        ProveedorLogica res= null;
        try {
            res= new ProveedorLogica();
            proved =res.traeProovedorEspecifico(proved);
           
        } catch (Exception e) {
             addActionMessage("ERROR EN LA CONSULTA.");
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
        public String actualizaProovedor(){
        String resultado="";
        ProveedorLogica res= null;
        try {
            res= new ProveedorLogica();
             resultado=res.actualizaProveedorEspecifico(proved);
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

    public ProvedDto getProved() {
        return proved;
    }

    public void setProved(ProvedDto proved) {
        this.proved = proved;
    }

    public ArrayList<ProvedDto> getResultProved() {
        return resultProved;
    }

    public void setResultProved(ArrayList<ProvedDto> resultProved) {
        this.resultProved = resultProved;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }
    
    

}
