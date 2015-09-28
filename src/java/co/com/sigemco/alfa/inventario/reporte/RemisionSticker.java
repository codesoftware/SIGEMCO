/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.reporte;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.reportes.Rep_ReporteLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.inventario.dto.RemisionDto;
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
 * @author Nicolas
 */
public class RemisionSticker extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private RemisionDto remision;
    private String nombreJasper;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;

    /**
     * Funcion encargada de Generar la accion de los stikers de las
     * Remisiones(Equipos Celulares)
     *
     * @return
     */
    public String generarStiker() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
            File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/codigo_" + remision.getRmce_rmce()+ ".pdf"));
            String path = reporte.getPath();
            Rep_ReporteLogica logica = new Rep_ReporteLogica();
            String rta = logica.generarStikerCelular(remision.getRmce_rmce(), path, reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "stikerCelular_" + remision.getRmce_rmce()+ ".pdf";
            } else {
                addActionError("Error al generar el reporte \n" + rta);
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

    public RemisionDto getRemision() {
        return remision;
    }

    public void setRemision(RemisionDto remision) {
        this.remision = remision;
    }

    public String getNombreJasper() {
        return nombreJasper;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
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
