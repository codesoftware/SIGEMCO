/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.MovInventario;
import co.com.hotel.logica.movInventario.Inv_MovInLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class InMoviInAccion extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private String accion;
    private MovInventario movimiento;
    //Mapas necesarios para los movimientos de inventario
    private Map<String, String> naturalezaMvIn;
    private Map<String, String> usuarioImplicado;
    private Map<String, String> yesNo;
    //Objeto respuesta de la consulta General
    private List<MovInventario> listMov;
    private String modifica;

    public String execute() {
        return SUCCESS;
    }

    /**
     * Funcion encarga de la accion de insertar un movimiento de inventario
     *
     * @return String el cual confirma la navegacion
     */
    public String insertarMovimientoInventario() {
        Inv_MovInLogica logica = null;
        try {
            logica = new Inv_MovInLogica();
            String valida = logica.insertaMovInv(movimiento);
            if (valida.equalsIgnoreCase("Ok")) {
                addActionMessage("Movimiento de inventario insertado correctamente ");
                movimiento = null;
            } else {
                addActionError(valida);
            }
        } catch (Exception e) {
            System.out.println("Error InMoviInAccion.insertarMovimientoInventario " + e);
        }
        return SUCCESS;
    }

    /**
     * Funcion con la cual se realiza la consulta general con filtros
     *
     * @return
     */
    public String consultaGeneralXFiltros() {
        Inv_MovInLogica logica = new Inv_MovInLogica();
        try {
            listMov = logica.consultaGeneralMovimientosInventarioXFiltro(movimiento);
            if (listMov == null) {
                addActionError("La consulta no arrojo ningun resultado");
            }
        } catch (Exception e) {
            System.out.println("Error InMoviInAccion.consultaGeneralXFiltros " + e);
        }
        return SUCCESS;
    }

    /**
     * Funcion que se encarga de actualizar los movimientos de inventario
     * @return
     */
    public String consultaActualizaMovimientoInv() {
        Inv_MovInLogica logica = new Inv_MovInLogica();
        try {
            movimiento = logica.consultaUpdMovimientoInventario(movimiento);
            if (movimiento == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                modifica = "S";
            }
        } catch (Exception e) {
            System.out.println("Error InMoviInAccion.actualizarMovimientoInv " + e);
        }
        return SUCCESS;
    }

    /**
     * Actualiza los datos de movimientos de inventario
     *
     * @return
     */
    public String ActualizaMovimientoInv() {
        Inv_MovInLogica logica = null;
        try {
            logica = new Inv_MovInLogica();
            String rta = logica.actualizarMovimientoInv(this.movimiento);
            if (!rta.equalsIgnoreCase("Ok")) {
                addActionError("Error al actualizar un movimiento de inventario");
            } else {
                addActionMessage("Movimiento de inventario actualizado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de Validar las aciones que requiera validacionde datos
     *
     */
    public void validate() {
        naturalezaMvIn = new HashMap<String, String>();
        this.naturalezaMvIn.put("I", "Ingreso");
        this.naturalezaMvIn.put("E", "Egreso");
        this.usuarioImplicado = new HashMap<String, String>();
        this.usuarioImplicado.put("C", "Cliente");
        this.usuarioImplicado.put("P", "Proveedor");
        this.usuarioImplicado.put("N", "Ninguno");
        this.yesNo = new HashMap<String, String>();
        this.yesNo.put("S", "Si");
        this.yesNo.put("N", "No");
        ValidaCampos valida = new ValidaCampos();
        if (accion.equalsIgnoreCase("insertar")) {
            if (!valida.validaNulo(movimiento.getMvin_descr().trim())) {
                addActionError("El campo Descripcion no puede ser nulo");
            } else if (movimiento.getMvin_natu().equalsIgnoreCase("-1")) {
                addActionError("Debe escojer una naturaleza el campo es obligatorio");
            } else if (movimiento.getMvin_usim().equalsIgnoreCase("-1")) {
                addActionError("Debe escojer un usuario el campo es obligatorio");
            } else if (movimiento.getMvin_venta().equalsIgnoreCase("-1")) {
                addActionError("Debe escojer si implica o no implica una venta este movimiento");
            }
        }
    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Map getSession() {
        return session;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public MovInventario getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovInventario movimiento) {
        this.movimiento = movimiento;
    }

    public Map<String, String> getNaturalezaMvIn() {
        return naturalezaMvIn;
    }

    public void setNaturalezaMvIn(Map<String, String> naturalezaMvIn) {
        this.naturalezaMvIn = naturalezaMvIn;
    }

    public Map<String, String> getUsuarioImplicado() {
        return usuarioImplicado;
    }

    public void setUsuarioImplicado(Map<String, String> usuarioImplicado) {
        this.usuarioImplicado = usuarioImplicado;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public List<MovInventario> getListMov() {
        return listMov;
    }

    public void setListMov(List<MovInventario> listMov) {
        this.listMov = listMov;
    }

    public String getModifica() {
        return modifica;
    }

    public void setModifica(String modifica) {
        this.modifica = modifica;
    }
}
