/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.facturacion.dao.ClienteDto;
import co.com.sigemco.alfa.facturacion.logica.ClienteLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones relacionadas con los clientes
 *
 * @author Nicolas
 */
public class ClienteAction extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private ClienteDto cliente;
    private String accion;
    private String existeCliente;

    /**
     * Funcion encargada de realizar la accion de consultar un cliente
     *
     * @return
     */
    public String consultaClienteIniFactura() {
        ClienteLogica logicaCliente = null;
        try {
            logicaCliente = new ClienteLogica();
            String cedulaAux = cliente.getClien_cedula();
            cliente = logicaCliente.obtenerclienteXCeduala(cliente);
            if (cliente == null) {
                addActionError("Cliente inexistente en el sistema. Por favor ingrese los siguientes datos basicos del cliente.");
                cliente = new ClienteDto();
                cliente.setClien_cedula(cedulaAux);
                existeCliente = "N";
                return "inexistente";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de insertar un cliente
     *
     * @return
     */
    public String insertarCliente(){
        ClienteLogica logicaCliente = null;
        try {
            logicaCliente = new ClienteLogica();
            String rta = logicaCliente.insertaCliente(cliente);
            if(!rta.equalsIgnoreCase("Ok")){
                addActionError("Error al insertar el Cliente intente de nuevo");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de validar los campos antes de llegar a los
     * accionadores
     */
    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        try {
            if (accion.equalsIgnoreCase("consultaCliente")) {
                if (!valida.validaNulo(cliente.getClien_cedula())) {
                    addActionError("El campo cedula no puede ser nulo");
                } else if (!valida.validaNumerico(cliente.getClien_cedula())) {
                    addActionError("El campo cedula debe ser numerico");
                }

            }
            if(accion.equalsIgnoreCase("insertarCliente")){
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al realizar las validadaciones de datos" + e);
        }
        valida = null;
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

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public String getExisteCliente() {
        return existeCliente;
    }

    public void setExisteCliente(String existeCliente) {
        this.existeCliente = existeCliente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
