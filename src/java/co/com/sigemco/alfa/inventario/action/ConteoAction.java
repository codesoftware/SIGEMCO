/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.ConteoProdDto;
import co.com.sigemco.alfa.inventario.logica.ConteoProdLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class ConteoAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private Map<String, String> sedes;
    private ConteoProdDto conteo;
    
    public String insrtaNuevoConteo(){
        ConteoProdLogica logica = null;
        try {
            logica = new ConteoProdLogica();
            conteo.setCopr_tius(usuario.getIdTius());
            String rta = logica.insertaCreacionConteo(conteo);
            if("Ok".equalsIgnoreCase(rta)){
                addActionMessage("El inventario se registro correctamente");
                conteo = null;
            }else{
                addActionError("Error al ingresar el Conteo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    /**
     * Funcion encargada de realizar las validaciones mas basicas antes de llegar a la funcion accionador pertinente
     */
    public void validate() {
        if ("insConteo".equalsIgnoreCase(accion)) {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
        }
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

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public ConteoProdDto getConteo() {
        return conteo;
    }

    public void setConteo(ConteoProdDto conteo) {
        this.conteo = conteo;
    }
}
