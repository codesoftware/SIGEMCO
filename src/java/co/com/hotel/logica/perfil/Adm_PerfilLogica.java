/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.perfil;

import co.com.hotel.dto.Perfil;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SOFIA
 */
public class Adm_PerfilLogica {

    public boolean ingresaPerfil(Perfil perfil) {
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("US_FINSERT_PERFIL");
            function.adicionarParametro(perfil.getNombre());
            function.adicionarParametro(perfil.getDescripcion());
            function.adicionarParametro(perfil.getEstado());
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return false;
    }

    public ArrayList<String> buscaPerfilesXFiltro(String filtro) {
        ArrayList<String> respuesta = new ArrayList<>();
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        try {
            String sql = "select perf_nomb\n";
            sql += "from us_tperf\n";
            sql += "where upper(perf_nomb) like upper('%" + filtro + "%')";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                String nomAux = rs.getString("perf_nomb");
                respuesta.add(nomAux);
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.buscaPerfilesXFiltro : " + e);
        } finally {
            function.cerrarConexion();
        }
        return respuesta;
    }

    public Perfil buscaPerfil(String nom, String desc, String estado) {
        Perfil perfil = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        try {
            String sql = creaSqlPerfil(nom, desc, estado);
            rs = function.enviarSelect(sql);
            function.cerrarConexion();
            if (rs.next()) {
                perfil = new Perfil();
                perfil.setNombre(rs.getString("nombre"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setEstado(rs.getString("estado"));
                perfil.setId(rs.getString("id"));
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.buscaPerfil : " + e);
        }

        return perfil;
    }

    public String creaSqlPerfil(String nombre, String desc, String estado) {
        String sql = "select perf_nomb nombre, perf_desc descripcion , perf_estado estado, perf_perf id\n";
        sql += "from us_tperf\n";
        if (nombre == null || nombre.equalsIgnoreCase("") || nombre.isEmpty() || nombre == "null") {
            sql += "where perf_nomb = perf_nomb\n";
        } else {
            sql += "where perf_nomb like '%" + nombre + "%'\n";
        }

        if (desc == null || desc.equalsIgnoreCase("") || desc.isEmpty()) {
            sql += "and perf_desc = perf_desc\n";
        } else {
            sql += "and perf_desc like '%" + desc + "%'\n";
        }

        if (estado == null || estado.equalsIgnoreCase("") || estado.isEmpty()) {
            sql += "and perf_estado = perf_estado\n";
        } else {
            sql += "and perf_estado like '%" + estado + "%'\n";
        }

        return sql;
    }

    public boolean actulizarPerfil(Perfil perfil) {
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("US_FACTUALIZA_PERFIL");
            function.adicionarParametro(perfil.getNombre());
            function.adicionarParametro(perfil.getDescripcion());
            function.adicionarParametro(perfil.getEstado());
            function.adicionarNumeric(perfil.getId());
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.cerrarConexion();
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    System.out.println("Este es la respuesta de la funcion: " + rtaPg);
                    return true;
                }

            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.actulizarPerfil");
            return false;
        }
        return false;
    }

    public ArrayList<Perfil> consultaGeneralPerfil(String filtro) throws SQLException {
        ArrayList<Perfil> resultado = null;
        String sql = "select perf_perf id, perf_nomb nombre, perf_desc descri, perf_estado estado\n";
        sql += "from us_tperf\n";
        sql += "where upper(perf_estado) = upper(";
        if (filtro.equalsIgnoreCase("A")) {
            sql += "'A')";
        } else if (filtro.equalsIgnoreCase("I")) {
            sql += "'I')";
        } else if (filtro.equalsIgnoreCase("-1")) {
            sql += "perf_estado)";
        }
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        rs = function.enviarSelect(sql);
        function.cerrarConexion();
        if (rs != null) {
            resultado = new ArrayList<Perfil>();
            while (rs.next()) {
                Perfil auxPerfil = new Perfil();
                auxPerfil.setEstado(rs.getString("estado"));
                auxPerfil.setDescripcion(rs.getString("descri"));
                auxPerfil.setNombre(rs.getString("nombre"));
                auxPerfil.setId(rs.getString("id"));
                resultado.add(auxPerfil);
            }
        }
        return resultado;
    }

    public ArrayList<String> obtieneNomPerfil() {
        ArrayList<String> result = null;
        String sql = "select perf_nomb nombre\n";
        sql += "from us_tperf";
        EnvioFunction function = new EnvioFunction();
        try {

            ResultSet rs = null;
            rs = function.enviarSelect(sql);

            if (rs != null) {
                result = new ArrayList<String>();
                while (rs.next()) {
                    result.add(rs.getString("nombre"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.obtieneNomPerfil");
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return result;
    }

    public String obtieneIdPerfil(String nombrePerfil) {
        String result = null;
        {
            String sql = "select perf_perf id\n";
            sql += "from us_tperf\n";
            sql += "where upper(perf_nomb) = upper('" + nombrePerfil + "')";
            EnvioFunction function = new EnvioFunction();
            try {

                ResultSet rs = null;
                rs = function.enviarSelect(sql);

                if (rs != null) {
                    while (rs.next()) {
                        result = rs.getString("id");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error Adm_PerfilLogica.obtieneIdPerfil");
            } finally {
                function.cerrarConexion();
                function = null;
            }
            return result;
        }
    }

    public Perfil buscaPerfilXId(String idPerfil) {
        Perfil perfil = null;
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        int contador = 0;
        try {
            sql = "SELECT  perf_perf idPerfil, perf_nomb nombre, perf_desc descripcion, perf_permisos permisos, perf_estado estado\n";
            sql += "FROM us_tperf\n";
            sql += "WHERE perf_perf = '" + idPerfil + "'";

            ResultSet rs = null;
            rs = function.enviarSelect(sql);            
            if(rs.next()){
                if(contador == 0){
                    perfil = new Perfil();
                    contador++;
                }
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setEstado(rs.getString("estado"));
                perfil.setId(rs.getString("idPerfil"));
                perfil.setNombre(rs.getString("nombre"));
                perfil.setPermisos(rs.getString("permisos"));
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.buscaPerfilXId " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return perfil;
    }
    
    public String actualizaPermisos(String id, String permisos){
        String rta = "Ok";
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        try {
            sql = "Update us_tperf\n";
            sql += "set perf_permisos = '" + permisos + "'\n";
            sql += "WHERE perf_perf = "+id.trim()+"";
            function.enviarSelect(sql);
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.actualizaPermisos " + e);
            rta = "Error";
        }finally{
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }
    
    public Map<String, String> obitnePerfilIdNombre(){
        Map<String, String> map = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        int contador = 0;
        ResultSet rs = null;
        try {
            sql = "SELECT perf_perf id_perfil, perf_nomb nombre_perf\n";
            sql += "FROM us_tperf";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if(contador == 0){
                    map = new HashMap<>();
                    contador++;
                }
                String auxId= rs.getString("id_perfil");
                String auxNom = rs.getString("nombre_perf");
                map.put(auxId,auxNom);
            }
        } catch (Exception e) {
            System.out.println("Error Adm_PerfilLogica.obitnePerfilIdNombre " + e);
        }finally{
            function.cerrarConexion();
            function = null;
            rs = null;
        }
        return map;
    }
}
