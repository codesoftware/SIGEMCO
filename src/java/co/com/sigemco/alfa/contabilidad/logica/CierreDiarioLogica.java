/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.CierreDiarioDao;
import co.com.sigemco.alfa.contabilidad.dto.CierreDiarioDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
            function.adicionarNumeric(sede);
            function.adicionarNumeric(usuario);
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
            //properties.put("fact_fact", Integer.parseInt(fact_fact));
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

}
