/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.reporte;

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
 * @author ACER
 */
public class ProductoReporte extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String nombreJasper;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;
    private String tipoReporte;

    /**
     * Funcion encargada de generar el reporte de productos por medio de un
     * excel
     *
     * @return
     */
    public String generaReporteProducto() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
            File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/productos.xls"));
            Rep_ReporteLogica logica = new Rep_ReporteLogica();
            String rta = logica.generaRepProductos(reporte.getPath(), reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "productos.xls";
            } else {
                addActionError("Error al generar el reporte \n" + rta);
            }
            String path = reporte.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de generar el reporte de productos por medio de un
     * excel
     *
     * @return
     */
    public String reportBasicosInv() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            File reporte = null;
            File reporteDestino = null;
            if ("existGene".equalsIgnoreCase(this.tipoReporte)) {
                this.nombreJasper = "ExsistenciasProductosReport.jasper";
                reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
                reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/productos_exis.xls"));
            } else if ("receProd".equalsIgnoreCase(this.tipoReporte)) {
                this.nombreJasper = "ProductosRecetasReport.jasper";
                reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
                reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/recetaProducto.xls"));
            }
            Rep_ReporteLogica logica = new Rep_ReporteLogica();
            String rta = logica.generaReporteBasico(reporte.getPath(), reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                if ("existGene".equalsIgnoreCase(this.tipoReporte)) {
                    this.contentName = "productos_exis.xls";
                } else if ("receProd".equalsIgnoreCase(this.tipoReporte)) {
                    this.contentName = "productos_receta.xls";
                }
            } else {
                addActionError("Error al generar el reporte \n" + rta);
            }
            String path = reporte.getPath();
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

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

}
