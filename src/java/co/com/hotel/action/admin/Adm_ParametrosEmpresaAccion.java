/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Empresa;
import co.com.hotel.logica.empresa.Emp_EmpresaLogica;
import co.com.hotel.utilidades.ManejoString;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class Adm_ParametrosEmpresaAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Empresa empresa;

    /**
     * Accion encargada de insertar la informacion general de la empresa
     *
     * @return
     */
    public String modificarParametros() {
        Emp_EmpresaLogica logica = null;
        try {
            logica = new Emp_EmpresaLogica();
            String rta = logica.ingresarParametrosPrincempresa(empresa);
            if (rta.equalsIgnoreCase("Ok")) {
                addActionMessage("Parametros ingresados Correctamente ");
            } else {
                addActionError(rta);
            }
        } catch (Exception e) {
            System.err.println("Error Adm_ParametrosEmpresaAccion.modificarParametros " + e);
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de modificar los parametros generales como el iva y los
     * dias de vencimiento para los equipos celulares
     *
     * @return
     */
    public String modificaParametrosGeneralesEmp() {
        Emp_EmpresaLogica logica = null;
        try {
            String comisionAux = ManejoString.eliminaMascaraMoneda(empresa.getComision());
            empresa.setComision(comisionAux);
            logica = new Emp_EmpresaLogica();
            String rta = logica.ingresarParametrosGeneEmpresa(empresa);
            if (rta.equalsIgnoreCase("Ok")) {
                addActionMessage("Parametros ingresados Correctamente ");
            } else {
                addActionError(rta);
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
