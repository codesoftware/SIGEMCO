/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import co.com.sigemco.alfa.inventario.logica.RecetaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class RecetasAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private RecetaDto receta;
    private List recetas;
    private String permisoAct;

    /**
     * Funcion encargada de realizar la accion de insertar una receta en el
     * sistema
     *
     * @return
     */
    public String insertarReceta() {
        RecetaLogica logica = new RecetaLogica();
        try {
            String valida = logica.insertaReceta(receta);
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("La receta " + receta.getRece_nombre().toUpperCase() + " fue insertada correctamente");
                receta = null;
            } else {
                addActionError(valida);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de buscar las recetas teniendo en
     * cuenta filtros
     *
     * @return
     */
    public String consultaRecetasXFiltro() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            this.recetas = objLogica.consultaGeneralXFiltros(receta);
            if (recetas == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                if(usuario.getPermisos().indexOf(".InRec3.")>0){
                    permisoAct = "S";
                }else{
                    permisoAct = "N";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String consultaRecetasXId(){
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if ("insertReceta".equalsIgnoreCase(accion)) {
            if (!valida.validaNulo(receta.getRece_nombre())) {
                addActionError("El campo nombre no puede ser nulo");
            } else if (!valida.validaNulo(receta.getRece_desc())) {
                addActionError("El campo descripcion no puede ser nulo");
            }
        }else if("consultaActuliza".equalsIgnoreCase(accion)){
            if(!valida.validaNulo(receta.getRece_rece())){
                addActionError("El Id de la receta no puede ser nulo");
            }
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

    public RecetaDto getReceta() {
        return receta;
    }

    public void setReceta(RecetaDto receta) {
        this.receta = receta;
    }

    public List getRecetas() {
        return recetas;
    }

    public void setRecetas(List recetas) {
        this.recetas = recetas;
    }

    public String getPermisoAct() {
        return permisoAct;
    }

    public void setPermisoAct(String permisoAct) {
        this.permisoAct = permisoAct;
    }
}
