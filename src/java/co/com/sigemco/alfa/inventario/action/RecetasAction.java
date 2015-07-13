/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import co.com.sigemco.alfa.inventario.logica.RecetaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
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
    private Map<String, String> estadoMap;
    private Map<String, String> sedes;

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
                if (usuario.getPermisos().indexOf(".InRec3.") > 0) {
                    permisoAct = "S";
                } else {
                    permisoAct = "N";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de consultar la receta por su Id
     *
     * @return
     */
    public String consultaRecetasXId() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            receta = objLogica.consultaRecetaXId(receta);
            if (receta == null) {
                addActionError("No se encontro ninguna receta con ese id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de actualizar una receta
     *
     * @return
     */
    public String actualizaReceta() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            String valida = objLogica.actualizaReceta(receta);
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("La receta se ha actualizado correctamente");
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
     * Funcion encargada de realizar la accion de buscar una receta por su
     * codigo
     *
     * @return
     */
    public String buscaRecetaXCod() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
            receta = objLogica.consultaGeneralXCodigo(receta);
            if (receta == null) {
                addActionError("No existe ninguna receta con ese codigo");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        } else if ("consultaActuliza".equalsIgnoreCase(accion)) {
            this.estadoMap = new HashMap<String, String>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            if (!valida.validaNulo(receta.getRece_rece())) {
                addActionError("El Id de la receta no puede ser nulo");
            }
        } else if ("actualizaReceta".equalsIgnoreCase(accion)) {
            this.estadoMap = new HashMap<String, String>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            if (!valida.validaNulo(receta.getRece_nombre())) {
                addActionError("El campo nombre no puede ser nulo");
            } else if (!valida.validaNulo(receta.getRece_desc())) {
                addActionError("El campo descripcion no puede ser nulo");
            } else if ("-1".equalsIgnoreCase(receta.getRece_rece())) {
                addActionError("Por Favor seleccione un estado para la receta");
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

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

}
