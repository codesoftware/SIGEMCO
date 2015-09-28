/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Sede;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.ManejoString;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.inventario.dto.RemisionDto;
import co.com.sigemco.alfa.inventario.logica.ReferenciaLogica;
import co.com.sigemco.alfa.inventario.logica.RemisionLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones y redireccionar a las paginas de las
 * remisiones del sistema
 *
 * @author nicolas
 */
public class RemisionAction extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private Usuario usuario;
    private Map session;
    private RemisionDto remision;
    private String accion;
    private Map<String, String> tipoPlan;
    private Map<String, String> sedes;
    private Map<String, String> referencias;
    private Map<String, String> estadoEqCeluar;
    private Map<String, String> yesNo;
    private List<RemisionDto> listRemisiones;
    private String muestraIccid;
    private String muestraImei;

    /**
     * Funcion encargada de realizar la accion de insertar una remision
     *
     * @return
     */
    public String insertarRemision() {
        RemisionLogica logica = null;
        try {
            logica = new RemisionLogica();
            this.remision.setRmce_tius_ent(usuario.getIdTius());
            String rta = logica.insertaRemision(remision);
            if (rta.equalsIgnoreCase("Ok")) {
                addActionMessage("Celular ingresado correctamente");
                String auxComision = remision.getRmce_comision();
                remision = null;
                remision = new RemisionDto();
                remision.setRmce_comision(auxComision);
            } else {
                addActionError("Error al ingresar el Equipo Celular \n" + rta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la consulta general por filtros de las
     * remisiones
     *
     * @return
     */
    public String consultaGeneralRem() {
        RemisionLogica logica = null;
        try {
            logica = new RemisionLogica();
            this.listRemisiones = logica.consultaGeneralXFiltros(remision);
            if (listRemisiones == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                if (remision.getRmce_iccid() != null && !remision.getRmce_iccid().equalsIgnoreCase("")) {
                    muestraIccid = "S";
                } else {
                    muestraIccid = "N";
                }
                if (remision.getRmce_imei() != null && !remision.getRmce_imei().equalsIgnoreCase("")) {
                    muestraImei = "S";
                } else {
                    muestraImei = "N";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String actualizaEspecificoCelular() {
        RemisionLogica logica = null;
        try {
            logica = new RemisionLogica();
            addActionMessage(logica.actualizaCelularEspecifico(remision));
        } catch (Exception e) {
            addActionError("Error Actualizando Celular");
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de actualizar los celulares
     *
     * @return
     */
    public String consultaEspecificaCelular() {
        RemisionLogica logica = null;
        try {
            logica = new RemisionLogica();
            this.remision = logica.consultaCelularEspecifico(remision);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public void validate() {
        Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
        try {
            this.sedes = sedeLogica.obtieneSedes();
            this.tipoPlan = new HashMap<String, String>();
            tipoPlan.put("pr", "PREPAGO");
            tipoPlan.put("ps", "POSTPAGO");
            ReferenciaLogica refeLogica = new ReferenciaLogica();
            this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
            this.estadoEqCeluar = new HashMap<String, String>();
            this.estadoEqCeluar.put("E", "STAND");
            this.estadoEqCeluar.put("V", "VENDIDO");
            this.estadoEqCeluar.put("D", "DEVUELTO");
            this.yesNo = new HashMap<String, String>();
            this.yesNo.put("S", "Si");
            this.yesNo.put("N", "No");
            ValidaCampos valida = new ValidaCampos();
            if (accion.equalsIgnoreCase("insertar")) {
                String auxComision = ManejoString.eliminaMascaraMoneda(remision.getRmce_comision());
                remision.setRmce_comision(auxComision);
                remision.setFiltros("N");
                if (remision.getRmce_refe().equalsIgnoreCase("-1")) {
                    addActionError("Debe seleccionar la referencia del Equipo Celular");
                } else if (!valida.validaNulo(remision.getRmce_imei())) {
                    addActionError("El campo Imei no puede ser nulo");
                } else if (!valida.validaNulo(remision.getRmce_iccid())) {
                    addActionError("El campo iccid no puede ser nulo");
                } else if (!valida.validaNulo(remision.getRmce_valor())) {
                    addActionError("El campo Valor de Venta no puede ser nulo");
                } else if (!valida.validaNumerico(remision.getRmce_valor())) {
                    addActionError("El campo Valor de venta debe ser numerico");
                } else if (!valida.validaNulo(remision.getRmce_comision())) {
                    addActionError("El campo comision no puede ser nulo");
                } else if (!valida.validaNumerico(remision.getRmce_comision())) {
                    addActionError("El campo comision debe ser numerico");
                } else if (remision.getRmce_tppl().equalsIgnoreCase("-1")) {
                    addActionError("Seleccione el tipo de plan del equipo celular");
                } else if (!valida.validaNulo(remision.getRmce_fcve())) {
                    addActionError("El campo fecha de Vencimiento no puede ser nulo");
                } else if (remision.getRmce_sede().equalsIgnoreCase("-1")) {
                    addActionError("Debe seleccionar la sede en cual estara el equipo celular");
                }
            } else if (accion.equalsIgnoreCase("consultaGen")) {
                //Aqui hacemos la logica para la consulta general de remisiones
                remision.setFiltros("N");
            }else if(accion.equalsIgnoreCase("updRemisionIndv")){
                remision.setFiltros("N");
            }
            valida = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public RemisionDto getRemision() {
        return remision;
    }

    public void setRemision(RemisionDto remision) {
        this.remision = remision;
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

    public Map<String, String> getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(Map<String, String> tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public Map<String, String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Map<String, String> referencias) {
        this.referencias = referencias;
    }

    public Map<String, String> getEstadoEqCeluar() {
        return estadoEqCeluar;
    }

    public void setEstadoEqCeluar(Map<String, String> estadoEqCeluar) {
        this.estadoEqCeluar = estadoEqCeluar;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public List<RemisionDto> getListRemisiones() {
        return listRemisiones;
    }

    public void setListRemisiones(List<RemisionDto> listRemisiones) {
        this.listRemisiones = listRemisiones;
    }

    public String getMuestraIccid() {
        return muestraIccid;
    }

    public void setMuestraIccid(String muestraIccid) {
        this.muestraIccid = muestraIccid;
    }

    public String getMuestraImei() {
        return muestraImei;
    }

    public void setMuestraImei(String muestraImei) {
        this.muestraImei = muestraImei;
    }
}
