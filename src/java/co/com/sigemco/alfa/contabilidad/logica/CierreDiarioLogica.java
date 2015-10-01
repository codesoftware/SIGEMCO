/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.logica.reportes.Rep_ReporteLogica;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.CierreDiarioDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Personal
 */
public class CierreDiarioLogica {

    /**
     * Funcion que permite insertar el cierre diario
     *
     * @param usuario
     * @param sede
     * @param fecha
     * @return
     */
    public String insertaCierreDiario(String usuario, String sede, String fecha) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("IN_FINSERT_CIERRE_DIARIO");
            function.addicionarParametroDate(fecha);
            function.adicionarNumeric(usuario);
            function.adicionarNumeric(sede);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    rta = function.getRespuesta();

                }
            }

        } catch (Exception e) {
            rta = "Error al insertar Cierre";
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que genera el pdf para el cierre
     *
     * @param cierr
     * @param ruta
     * @param rutaDestino
     * @return
     */
    public String generarReporteCierre(CierreDiarioDao cierr, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("FECHA", cierr.getCier_fech());
            properties.put("SEDE", cierr.getCier_sede());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            JasperExportManager.exportReportToPdfFile(print, rutaDestino);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rta;
    }

    /**
     * metodo que genera el pdf para el cierre en excel
     *
     * @param cierr
     * @param ruta
     * @param rutaDestino
     * @return
     */
    public String generarReporteCierreExcel(CierreDiarioDao cierr, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            String print = null;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("FECHA", cierr.getCier_fech());
            properties.put("SEDE", cierr.getCier_sede());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            print = JasperFillManager.fillReportToFile(ubicacionReporte,
                    properties, conn);
            //JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            //JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaDestino);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generarReporteCierreDetalladoExcel(CierreDiarioDao cierr, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            String print = null;

            Map<String, Object> properties = new HashMap<String, Object>();
            int cierreId = obtieneCodigoCierre(cierr);
            properties.put("CIERRE", ""+cierreId);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            try {
                print = JasperFillManager.fillReportToFile(ubicacionReporte,
                        properties, conn);
            } catch (Error e) {
                e.printStackTrace();
            }

            //JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            //JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaDestino);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public Connection generarConexion() {
        Connection con = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("co.com.sigemco.alfa.archivos.BASECONFIG");
            String host = rb.getString("HOST").trim();
            String user = rb.getString("USER").trim();
            String pass = rb.getString("PASSWORD").trim();
            String db = rb.getString("DATABASE").trim();
            String port = rb.getString("PUERTO").trim();
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error al realizar la conexion...");
            e.printStackTrace();
        }
        return con;
    }

    public int obtieneCodigoCierre(CierreDiarioDao cierr) {
        int cod_cierre = 0;

        try (EnvioFunction function = new EnvioFunction();) {
            CierreDiarioDao objDao = new CierreDiarioDao();
            ResultSet rs = function.enviarSelect(objDao.consultaFiltros("cier_sede = " + cierr.getCier_sede() + " and cier_fech = ('" + cierr.getCier_fech() + "')"));
            while (rs.next()) {
                cod_cierre = rs.getInt(1);
            }
            System.out.println("CIERRE" + cod_cierre);
            System.out.println("CIERREF" + cierr.getCier_fech());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cod_cierre;
    }

}
