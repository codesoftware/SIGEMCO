/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Sede;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dao.CierreDiarioDao;
import co.com.sigemco.alfa.contabilidad.logica.CierreDiarioLogica;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author Personal
 */
public class CierreDiarioAction extends ActionSupport implements UsuarioHabilitado, SessionAware, ServletRequestAware {

    private Usuario usuario;
    private Map session;
    private String sede;
    private String fecha;
    private String CierreDiarioDao;
    private String accion;
    private Map<String, String> sedes;
    private HttpServletRequest request;
    private String nombreJasper;
    private CierreDiarioDao cierreDiario;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;

    @SkipValidation
    public String execute() {
        return SUCCESS;
    }

    public String insertaCierre() {
        CierreDiarioLogica logica = new CierreDiarioLogica();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        System.out.println("ip" + ip);
        System.out.println("host" + host);
        try {
            String rta = logica.insertaCierreDiario(usuario.getIdTius(), sede, fecha);
            if (rta.equalsIgnoreCase("OK")) {
                addActionMessage("Cierre realizado correctamente");
            } else {
                addActionError("Lamentablemente el cierre no pudo ser realizado por el siguiente error \n" + rta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al ingresar el producto");
        }
        return SUCCESS;
    }

    /**
     * metodo que hace la insercion de cierres automatico
     *
     * @return
     */
    public String insertaCierreTarea() {
        ArrayList<Sede> lista = new ArrayList<Sede>();
        Adm_SedeLogica logicaSede = new Adm_SedeLogica();
        CierreDiarioLogica logica = new CierreDiarioLogica();
        try {
            java.util.Date fecha = new Date();
            System.out.println(fecha);
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(fecha.getTime());
            cal.add(Calendar.DATE, -1);
            Date d = new Date();
            d =cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaf = sdf.format(d);
            lista = logicaSede.consultaGeneralSede("A");
            for (Sede lista1 : lista) {
                logica.insertaCierreDiario("1", lista1.getSede_sede(), fechaf);
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error al ingresar el producto");
        }
        return SUCCESS;
    }

    public void validate() {
        if ("cierreDiario".equalsIgnoreCase(accion)) {
            Adm_SedeLogica sedeLogica = null;
            sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();

        }
    }

    /**
     * Función que permite generar el reporte del cierre diario
     *
     * @return
     */
    public String generarReporte() {
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/reporteCierreDiario.pdf"));
        try {
            String path = reporte.getPath();
            CierreDiarioLogica logica = new CierreDiarioLogica();
            String rta = logica.generarReporteCierre(cierreDiario, path, reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "reporteCierreDiario.pdf";
            } else {
                addActionError("Error al generar el reporte \n" + rta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;

    }
    
    /**
     * Función que permite generar el reporte del cierre diario en excel
     *
     * @return
     */
    public String generarReporteExcel() {
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/ACCIONES/REPORTES/FUENTES/" + nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/reporteCierreDiario.xls"));
        try {
            String path = reporte.getPath();
            CierreDiarioLogica logica = new CierreDiarioLogica();
            String rta = logica.generarReporteCierreExcel(cierreDiario, path, reporteDestino.getPath());
            if (rta.equalsIgnoreCase("Ok")) {
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "reporteCierreDiario.xls";
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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCierreDiarioDao() {
        return CierreDiarioDao;
    }

    public void setCierreDiarioDao(String CierreDiarioDao) {
        this.CierreDiarioDao = CierreDiarioDao;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getNombreJasper() {
        return nombreJasper;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    public CierreDiarioDao getCierreDiario() {
        return cierreDiario;
    }

    public void setCierreDiario(CierreDiarioDao cierreDiario) {
        this.cierreDiario = cierreDiario;
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
