/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.action.reportes;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.facturacion.Factura;
import co.com.hotel.logica.reportes.Rep_ReporteLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class Rep_ReporteAction extends ActionSupport implements SessionAware,UsuarioHabilitado{
    private Usuario usuario;
    private Map session;    
    private String nombreJasper;
    private Factura factura;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;
    
    public String generarFactura(){
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/"+nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/FACTURA/factura_"+factura.getFact_fact()+".pdf"));
        try {
            String path =  reporte.getPath();
            Rep_ReporteLogica logica = new Rep_ReporteLogica();
            String rta = logica.generarFactura(factura.getFact_fact(), path,reporteDestino.getPath());            
            if(rta.equalsIgnoreCase("Ok")){
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "factura_"+factura.getFact_fact()+".pdf";
            }else{
                addActionError("Error al generar factura " + factura.getFact_fact() +" \n" + rta);
            }
        }catch (Exception e){
            System.out.println("Error Rep_ReporteAction.generarFactura " + e);
        }
        return SUCCESS;
    }

    public Usuario getUsuario() {
        return  usuario;
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

    public String getNombreJasper() {
        return nombreJasper;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }    
}
