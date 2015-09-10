/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.reportes;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * @author nicolas
 */
public class Rep_ReporteLogica {

    public String generarFactura(String fact_fact, String ruta, String rutaDestino) {
        String rta = "Ok";
        EnvioFunction function = new EnvioFunction();
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("fact_fact", Integer.parseInt(fact_fact));
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            JasperExportManager.exportReportToPdfFile(print, rutaDestino);
        } catch (Exception e) {
            System.out.println("Error Rep_ReporteLogica.generarFactura " + e);
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

    public String generarReporteUsuarios(Usuario usua, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("PERFIL", usua.getIdPerfil());
            properties.put("ESTADO", usua.getEstado());
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
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generarStikerProd(String dska_dska, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("dska_dska", dska_dska.trim());
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
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generarStikerCelular(String rmce_rmce, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("rmce_rmce", rmce_rmce.trim());
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
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generarReporteConteo(String copr_copr, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("copr_copr", copr_copr.trim());
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
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generarReporteMovimientosContables(String fechaIni, String fechaFin, String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            String print = null;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("fechaIni", fechaIni);
            properties.put("fechaFin", fechaFin);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            print = JasperFillManager.fillReportToFile(ubicacionReporte,
                    properties, conn);
            //JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            //JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
                    print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                    rutaDestino);
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

    public String generarConsolidadoProdInventario(String ruta, String rutaDestino, String fechaIni, String fechaFin) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            String print = null;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("fechaInicial", fechaIni);
            properties.put("fechaFinal", fechaFin);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            print = JasperFillManager.fillReportToFile(ubicacionReporte,
                    properties, conn);
            //JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            //JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
                    print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                    rutaDestino);
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

    /**
     * Funcion la cual genera el reporte para crear el pdf del promedio
     * ponderado
     *
     * @param ruta
     * @param rutaDestino
     * @param fechaIni
     * @param fechaFin
     * @param dska
     * @return
     */
    public String generarPromPondXProd(String ruta, String rutaDestino, String fechaIni, String fechaFin, String dska) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("fechaInicial", fechaIni);
            properties.put("fechaFinal", fechaFin);
            properties.put("dska", dska);
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
                Logger.getLogger(Rep_ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rta;
    }

    public String generaRepProductos(String ruta, String rutaDestino) {
        String rta = "Ok";
        Connection conn = null;
        try {
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            String print = null;
            Map<String, Object> properties = new HashMap<String, Object>();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            print = JasperFillManager.fillReportToFile(ubicacionReporte,
                    properties, conn);
            //JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            //JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
                    print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                    rutaDestino);
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

}
