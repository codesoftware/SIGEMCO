/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.facturacion;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Habitacion;
import co.com.hotel.dto.Reservacion;
import co.com.hotel.dto.facturacion.Factura;
import co.com.hotel.logica.facturacion.Fac_DetalleHabitacionesLogica;
import co.com.hotel.logica.facturacion.Fac_FacturacionLogica;
import co.com.hotel.logica.servicio.Adm_ServicioLogica;
import co.com.hotel.utilidades.ManejoString;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author nicolas
 */
public class Fact_DetalleHabitacionesAction extends ActionSupport implements SessionAware, UsuarioHabilitado{

    private Usuario usuario;
    private Map session;
    private String accion;
    private Habitacion habitacion;
    private ArrayList<Reservacion> reservaciones;
    private String habitacionesReservadas;
    private String numDias;
    private String fechaInicial;
    private Factura factura;

    public String DetalleReservasHabitacion() {
        Adm_ServicioLogica logicaServ = new Adm_ServicioLogica();
        Fac_DetalleHabitacionesLogica logicaReserv = new Fac_DetalleHabitacionesLogica();
        try {
            habitacion = logicaServ.buscaHabitacionXid(habitacion.getIdHabitacion());
            reservaciones = logicaReserv.buscaReservasHabitacionXid(habitacion.getIdHabitacion());
            if (reservaciones == null) {
                addActionError("Esta habitacion no tiene ninguna reservacion futura");
            }
        } catch (Exception e) {
            System.out.println("Error Fact_DetalleHabitacionesAction.DetalleReservasHabitacion " + e);
        } finally {
            logicaServ = null;
        }
        return SUCCESS;
    }
    
    public String ReservarHabitaciones() {
        Fac_FacturacionLogica fact = new Fac_FacturacionLogica();
        ManejoString obj = new ManejoString();
        try {
            String[] habitaciones = habitacionesReservadas.split("|");
            habitaciones = armarVector(habitaciones);
            fechaInicial = obj.convertirFormatoFechas(fechaInicial, "mm/dd/yyyy", "/");
            Map rtaFact = fact.realizarFacturacion(habitaciones, numDias, fechaInicial, null, usuario.getIdTius());
            String mensaje = "";
            String aux1 = (String) rtaFact.get("RtaFact");
            if(aux1.equalsIgnoreCase("Factura realizada Exitosamente\n")){
                // Aqui debo recuperar la factura que acabo de insertar
                String idFact = (String) rtaFact.get("fact_fact");
                //System.out.println("Este es el id de la factura: " + idFact);
                factura = fact.recuperaFacturaXId(idFact);
            }
            mensaje = (String) rtaFact.get("RtaFact");
            String aux = (String) rtaFact.get("ErrorDetalles");
            if(!aux.trim().equalsIgnoreCase(""))
                addActionError(aux);
            addActionMessage(mensaje);
        } catch (Exception e) {
            System.out.println("Error Fact_DetalleHabitacionesAction.ReservarHabitaciones " + e);
        }
        return SUCCESS;
    }

    public String[] armarVector(String[] vector) {
        String[] vectorRta;
        int cont = 0;
        int j = 0;
        for (int i = 0; i < vector.length; i++) {
            if (!vector[i].trim().equalsIgnoreCase("|")) {
                cont++;
            }
        }
        vectorRta = new String[cont];
        for (int i = 0; i < vector.length; i++) {
            if (!vector[i].trim().equalsIgnoreCase("|")) {
                vectorRta[j] = vector[i];
                j++;
            }
        }
        return vectorRta;
    }
    
    @SkipValidation
    public String AprobacionFactura(){
        try {
            ValidaCampos valida = new ValidaCampos();
            if(valida.validaNulo(factura.getFact_fact())){
                Fac_FacturacionLogica obj = new Fac_FacturacionLogica();
                String rta = obj.apruebaFactura(factura.getFact_fact());
                if(rta.equalsIgnoreCase("Ok")){
                    addActionMessage("Factura Aprobada Correctamente");
                    factura = obj.recuperaFacturaXId(factura.getFact_fact());
                }else{
                    addActionError("Error al aprobar facturación");
                }
            }else{
                addActionError("El identificador de la factura no puede ser nula");
            }
        } catch (Exception e) {
            System.out.println("Error Fact_DetalleHabitacionesAction.AprobacionFactura " + e);
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if (accion.equalsIgnoreCase("detalleReservaHabitacion")) {
            if (!valida.validaNulo(habitacion.getIdHabitacion())) {
                addActionError("EL id de la habitación no puede ser nulo");
            }
            if (!valida.validaNumerico(habitacion.getIdHabitacion())) {
                addActionError("EL id de la habitación debe ser un caracter numerico");
            }
        } else if (accion.equalsIgnoreCase("reservar")) {
            if (!valida.validaNulo(null)) {
                addActionError("Error al recuperar la cedula del cliente por favor cree de nuevo la factura");
            }
            if (!valida.validaNulo(habitacionesReservadas)) {
                addActionError("No ha reservado ninguna Habitacion");
            }
            if (!valida.validaNulo(numDias)) {
                addActionError("No puede ser nulo el numero de dias");
            }
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public ArrayList<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(ArrayList<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public String getHabitacionesReservadas() {
        return habitacionesReservadas;
    }

    public void setHabitacionesReservadas(String habitacionesReservadas) {
        this.habitacionesReservadas = habitacionesReservadas;
    }

    public String getNumDias() {
        return numDias;
    }

    public void setNumDias(String numDias) {
        this.numDias = numDias;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
