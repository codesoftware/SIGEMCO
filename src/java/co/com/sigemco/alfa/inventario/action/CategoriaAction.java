/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.CategoriaDto;
import co.com.sigemco.alfa.inventario.logica.CategoriaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Personal
 */
public class CategoriaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private Map<String, String> estadoMap;
    private Map<String, String> runico;
    private ArrayList<CategoriaDto> resultCategoria = null;
    private CategoriaDto categoria;

    public void validate() {
        
        this.runico = new HashMap<String, String>();
        this.runico.put("A", "Activo");
        this.runico.put("I", "Inactivo");
        this.estadoMap = new HashMap<String, String>();
        this.estadoMap.put("A", "Activo");
        this.estadoMap.put("I", "Inactivo");
        
//        if(accion.equalsIgnoreCase("insertar")){
//            
//        }

    }

    public String consultaCategorias() {
        CategoriaLogica res = null;
        try {
            res = new CategoriaLogica();
            resultCategoria = res.consultaCategorias(categoria);
            if (resultCategoria.size() <= 0) {
                addActionMessage("LA CONSULTA NO ARROJO NINGUN RESULTADO.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String consultaCategoriaEspecifica() {
        CategoriaLogica res = null;
        try {
            res = new CategoriaLogica();
            categoria = res.obtieneCategoriasXId(categoria.getCate_cate());
        } catch (Exception e) {
            addActionMessage("ERROR EN LA CONSULTA.");
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String actualizaCategoria() {
        String resultado = "";
        CategoriaLogica res = null;
        try {
            res = new CategoriaLogica();
            resultado = res.actualizaCategoria(categoria);
            addActionMessage(resultado);
        } catch (Exception e) {
            addActionMessage("ERROR EN LA ACTUALIZACIÓN.");
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
        public String insertar() {
        CategoriaLogica res = null;
        try {
            res = new CategoriaLogica();
            String resultado = res.insertarCategoria(categoria);
            addActionMessage(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public Map<String, String> getRunico() {
        return runico;
    }

    public void setRunico(Map<String, String> runico) {
        this.runico = runico;
    }

    public ArrayList<CategoriaDto> getResultCategoria() {
        return resultCategoria;
    }

    public void setResultCategoria(ArrayList<CategoriaDto> resultCategoria) {
        this.resultCategoria = resultCategoria;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
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

}
