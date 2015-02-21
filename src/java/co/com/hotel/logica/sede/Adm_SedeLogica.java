/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.sede;

import co.com.hotel.dto.Sede;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class Adm_SedeLogica {

    /**
     * Funcion encargada de insertar una sede en la base de datos
     *
     * @param objTO objeto con la informacion de la sede
     * @param UsuarioLogeado usuario el cual inserto la sede
     * @return
     */
    public String insertarSede(Sede objTO, String UsuarioLogeado) {
        EnvioFunction function = new EnvioFunction();
        String resultado = "OK";
        try {
            String sql = "INSERT INTO EM_TSEDE (SEDE_NOMBRE,SEDE_DIRECCION,SEDE_TELEFONO,SEDE_TIUS) VALUES "
                    + "('" + objTO.getSede_nombre() + "','" + objTO.getSede_direccion() + "','" + objTO.getSede_telefono() + "','" + UsuarioLogeado + "')";
            boolean valida = function.enviarUpdate(sql);
            if (!valida) {
                resultado = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = "Error";
        } finally {
            function.cerrarConexion();
        }

        return resultado;
    }

    /**
     * Funcion encargada de recuperar en un arraylist todas la sedes
     * parametrizadas basandose en una serie de filtros proporcionados por el
     * usuario
     *
     * @param filtro
     * @return
     * @throws SQLException
     */
    public ArrayList<Sede> consultaGeneralSede(String filtro) throws SQLException {
        ArrayList<Sede> resultado = null;
        String sql = "select sede_sede id, sede_nombre nombre, sede_direccion direccion, sede_estado estado, sede_telefono telefono\n";
        sql += "from em_tsede\n";
        sql += "where upper(sede_estado) = upper(";
        if (filtro.equalsIgnoreCase("A")) {
            sql += "'A')";
        } else if (filtro.equalsIgnoreCase("I")) {
            sql += "'I')";
        } else if (filtro.equalsIgnoreCase("-1")) {
            sql += "sede_estado)";
        }
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        rs = function.enviarSelect(sql);
        function.cerrarConexion();
        if (rs != null) {
            resultado = new ArrayList<Sede>();
            while (rs.next()) {
                Sede auxPerfil = new Sede();
                auxPerfil.setSede_estado(rs.getString("estado"));
                auxPerfil.setSede_direccion(rs.getString("direccion"));
                auxPerfil.setSede_nombre(rs.getString("nombre"));
                auxPerfil.setSede_sede(rs.getString("id"));
                auxPerfil.setSede_telefono(rs.getString("telefono"));
                resultado.add(auxPerfil);
            }
        }
        return resultado;
    }

    /**
     * Funcion la cual obtiene el nombre de las sedes y sus Id's
     *
     * @return Mapa con las sedes
     */
    public Map<String, String> obtieneSedes() {
        EnvioFunction function = new EnvioFunction();
        Map<String, String> rta = null;
        int contador = 0;
        String sql = "";
        try {
            sql += "select sede_sede, sede_nombre \n";
            sql += "from em_tsede                 \n";
            sql += "where sede_estado = 'A'       \n";
            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (contador == 0) {
                    rta = new HashMap<String, String>();
                    contador++;
                }
                rta.put(rs.getString("sede_sede"), rs.getString("sede_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    /**
     * Funcion encargada de actualizar una sede basandose en el id de la sede
     *
     * @param objTo
     * @return
     */
    public String actualizaSede(Sede objTo) {
        String sql = "";
        String rta = "Ok";

        try (EnvioFunction function = new EnvioFunction();) {
            sql = sql.concat("UPDATE em_tsede SET sede_nombre = '"
                    .concat(objTo.getSede_nombre()).concat("',")
                    .concat("sede_direccion = '").concat(objTo.getSede_direccion()).concat("',")
                    .concat("sede_telefono = '").concat(objTo.getSede_telefono()).concat("',")
                    .concat("sede_estado= '").concat(objTo.getSede_estado()).concat("'")
                    .concat(" WHERE sede_sede = ").concat(objTo.getSede_sede())
            );
            boolean validaUpd = function.enviarUpdate(sql);
            if (!validaUpd) {
                rta = "Error al actualizar en la base de datos";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de recuperar una sede en especifico basandose en el
     * identificador de la tabla de sedes
     *
     * @param id
     * @return
     */
    public Sede consultarSedeEspecifico(String id) {
        Sede sede = null;
        String sql = "";
        try (EnvioFunction function = new EnvioFunction();) {
            sql = "SELECT sede_sede, sede_nombre, sede_direccion, sede_telefono, sede_fecin,sede_tius, sede_estado FROM em_tsede WHERE sede_sede = '" + id + "' ";
            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                sede = new Sede();
                sede.setSede_sede(rs.getString("sede_sede"));
                sede.setSede_nombre(rs.getString("sede_nombre"));
                sede.setSede_direccion(rs.getString("sede_direccion"));
                sede.setSede_telefono(rs.getString("sede_telefono"));
                sede.setSede_fecin(rs.getString("sede_fecin"));
                sede.setSede_tius(rs.getString("sede_tius"));
                sede.setSede_estado(rs.getString("sede_estado"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sede;
    }

}
