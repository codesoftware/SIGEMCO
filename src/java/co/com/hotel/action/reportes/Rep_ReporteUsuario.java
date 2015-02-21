/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.reportes;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.reportes.Rep_ReporteLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class Rep_ReporteUsuario extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String nombreJasper;
    private Usuario usuarioRep;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;

    public String generarReporteUsuario() {
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/reporteUsuarios.pdf"));
        try{
            String path = reporte.getPath();
            Rep_ReporteLogica logica = new Rep_ReporteLogica();
            String rta = logica.generarReporteUsuarios(usuarioRep, path, reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "reporteUsuarios.pdf";
            } else {
                addActionError("Error al generar el reporte \n" + rta);
            }
        } catch (Exception e) {
            System.out.println("Error Rep_ReporteUsuario.generarReporteUsuario " + e);
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

    public String getNombreJasper() {
        return nombreJasper;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    public Usuario getUsuarioRep() {
        return usuarioRep;
    }

    public void setUsuarioRep(Usuario usuarioRep) {
        this.usuarioRep = usuarioRep;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
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
