/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.facturacion;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.facturacion.Factura;
import co.com.hotel.dto.facturacion.Producto;
import co.com.hotel.dto.facturacion.Servicio;
import co.com.hotel.logica.facturacion.Fac_FacturacionLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author nicolas
 */
public class Fact_GestionFacturaAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private Factura factura;
    private String estado;
    private List resultados;
    //Variables en las cuales concateno todos los identificadores de detalles que contiene la facdtura
    private String listDetServ;
    private String listDetProd;

    public String buscaFacturas() {
        Fac_FacturacionLogica logica = null;
        try {
            logica = new Fac_FacturacionLogica();
            resultados = logica.buscaFacturasXFiltro(factura.getCliente().getCedula(), factura.getFact_fact());
            if (resultados == null) {
                addActionMessage("No se encontro ninguna factura asociada a estos filtros");
                estado = "inicial";
            } else {
                estado = "consultaFacturas";
            }
        } catch (Exception e) {
            System.out.println("Error Fact_GestionFacturaAction.buscaFacturas " + e);
            e.printStackTrace();
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    @SkipValidation
    public String buscaFacturaEspecifica() {
        Fac_FacturacionLogica logica = null;
        try {
            logica = new Fac_FacturacionLogica();
            factura = logica.recuperaFacturaXId(factura.getFact_fact());
            if (factura == null) {
                clearErrorsAndMessages();
                addActionMessage("No existe esta factura");
            } else {
                this.listDetServ = "";
                if (factura.getDetalleServicio() != null) {
                    for (Servicio detalle : factura.getDetalleServicio()) {
                        this.listDetServ += "." + detalle.getDtsv_dtsv() + ".";
                    }
                }
                this.listDetProd = "";
                if (factura.getDetalleProducto() != null) {
                    for (Producto detalle : factura.getDetalleProducto()) {
                        this.listDetProd += "." + detalle.getDtpr_dtpr() + ".";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Fact_GestionFacturaAction.buscaFacturaEspecifica " + e);
            e.printStackTrace();
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valCampos = new ValidaCampos();
        System.out.println("paso por aqui");
        if (accion.equalsIgnoreCase("busquedaInical")) {
            if (!valCampos.validaNulo(factura.getCliente().getCedula().trim())
                    && !valCampos.validaNulo(factura.getFact_fact().trim())) {
                addActionError("Los dos campos de busqueda no pueden estar vacios");
                estado = "inicial";
            }
        } else if (accion.equalsIgnoreCase("busquedaEspecifica")) {
            System.out.println("Entro a la validacion");
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List getResultados() {
        return resultados;
    }

    public void setResultados(List resultados) {
        this.resultados = resultados;
    }

    public String getListDetServ() {
        return listDetServ;
    }

    public void setListDetServ(String listDetServ) {
        this.listDetServ = listDetServ;
    }

    public String getListDetProd() {
        return listDetProd;
    }

    public void setListDetProd(String listDetProd) {
        this.listDetProd = listDetProd;
    }
}
