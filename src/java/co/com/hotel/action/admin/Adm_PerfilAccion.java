/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Perfil;
import co.com.hotel.logica.perfil.Adm_PerfilLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author SOFIA
 */
public class Adm_PerfilAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Perfil perfil;
    private String valorSelect;
    private String inserto;
    private ArrayList<Perfil> resultPerfil = null;

    public String execute() {
        Adm_PerfilLogica perfil = new Adm_PerfilLogica();
        try {
            if( inserto==null || inserto.isEmpty()){
                inserto = "N";
            }
            if (inserto.equalsIgnoreCase("N")) {
                boolean rta = perfil.ingresaPerfil(this.perfil);
                if (rta = true) {
                    addActionMessage("PERFIL INSERTADO CORRECTAMENTE");
                    this.inserto = "S";
                } else {
                    addActionError("ERROR AL INSERTAR EL PERFIL");
                    this.inserto = "N";
                }
            }else{
                addActionError("EL REGISTRO YA FUE INSERTADO");
                this.inserto = "S";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String actualizar(){
        Adm_PerfilLogica perfil = new Adm_PerfilLogica();
        try {
            boolean rta = perfil.actulizarPerfil(this.perfil);
            if(rta == true){
                addActionMessage("PERFIL MODIFICADO CORRECTAMENTE");                
                limpiaPerfil();
            }else{
                addActionError("ERROR AL ACTUALIZAR EL PERFIL");
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilAccion.actualizar: " + e);
        }                
        return SUCCESS;
    }
    
    @SkipValidation
    public String consultar() throws SQLException{
        if(perfil.getEstado().equals("A") || perfil.getEstado().equals("I") || perfil.getEstado().equals("-1")){
            Adm_PerfilLogica perfilObj = new Adm_PerfilLogica();
            resultPerfil = perfilObj.consultaGeneralPerfil(perfil.getEstado());
            if(resultPerfil == null || resultPerfil.isEmpty()){
                addActionError("La consulta no ha arrojado ningun resultado");
            }
        }else{
            addActionError("EL FILTRO INGRESADO NO ES PERMITIDO..");
        }            
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        boolean aux = valida.validaNulo(perfil.getNombre());
        if (aux == false) {
            addActionError("EL CAMPO NOMBRE NO PUEDE ESTAR VACIO");
        }
        aux = valida.validaNulo(perfil.getDescripcion());
        if (aux == false) {
            addActionError("EL CAMPO DESCRIPCION NO PUEDE ESTAR VACIO");
        }

        if (perfil.getEstado().equalsIgnoreCase("-1")) {
            addActionError("DEBE SELECCIONAR UNA OPCION EN EL ESTADO");
        } else {
            valorSelect = perfil.getEstado();
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public String isInserto() {
        return inserto;
    }

    public void setInserto(String inserto) {
        this.inserto = inserto;
    }

    public ArrayList<Perfil> getResultPerfil() {
        return resultPerfil;
    }

    public void setResultPerfil(ArrayList<Perfil> resultPerfil) {
        this.resultPerfil = resultPerfil;
    }    
    
    public void limpiaPerfil(){
        perfil.setDescripcion("");
        perfil.setEstado("");
        perfil.setNombre("");
        valorSelect = ""; 
    }

}
