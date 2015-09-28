/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.usuarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SOFIA
 */
public class Adm_UsuarioLogica {

    public ArrayList obtieneEstadoUsuario() {
        ArrayList<String> result = null;
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        try {
            sql = "select distinct tius_estado estado\n";
            sql += "from us_ttius";
            ResultSet rs = null;
            rs = function.enviarSelect(sql);

            if (rs != null) {
                result = new ArrayList<String>();
                while (rs.next()) {
                    result.add(rs.getString("estado"));
                }
            }

        } catch (Exception e) {
            System.out.println("Error Adm_UsuarioLogica.obtieneEstadoUsuario " + e);
            result = null;
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return result;
    }

    public ArrayList<Usuario> consultaGeneral(String estado, String perfil,String sedes) {
        ArrayList<Usuario> result = null;
        String sql = "";
        sql = "select (apellido || ' ' ||nombre) nomCompleto, cedula, usuario, correo, perfil_usuario perfil, nombreSede \n";
        sql += "from us_vusuarios\n";
        sql += "where 1=1\n";
        if (!estado.equalsIgnoreCase("-1")) {
            sql += " and estado_usuario = '" + estado + "'";
        }
        if (!perfil.equalsIgnoreCase("-1")) {
            sql += " and perfil_usuario = '" + perfil + "'";
        }
        if (!sedes.equalsIgnoreCase("-1")){
            sql += " and sede = '" + sedes + "'";
        }
        EnvioFunction function = new EnvioFunction();
        try {
            ResultSet rs = null;
            rs = function.enviarSelect(sql);
            if (rs != null) {
                result = new ArrayList<Usuario>();
            }
            while (rs.next()) {
                Usuario usuAux = new Usuario();
                String nombre = rs.getString("nomCompleto");
                String cedula = rs.getString("cedula");
                String usuario = rs.getString("usuario");
                String correo = rs.getString("correo");
                String perfilA = rs.getString("perfil");
                String nombreSede = rs.getString("nombreSede");
                usuAux.setNombre(nombre);
                usuAux.setCedula(cedula);
                usuAux.setUsuario(usuario);
                usuAux.setCorreo(correo);
                usuAux.setNomPerfil(perfilA);
                usuAux.setSede(nombreSede);
                result.add(usuAux);
            }
        } catch (Exception e) {
            System.out.println("Error Adm_UsuarioLogica.consultaGeneral" + e);
        } finally {
            function.cerrarConexion();
        }

        return result;
    }

    public boolean actualizaUsuario(Usuario usuario) {
        boolean rtaBool = false;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        try {
            function.adicionarNombre("US_FACTUALIZA_USUARIO");
            function.adicionarParametro(usuario.getNombre());
            function.adicionarParametro(usuario.getApellido());
            function.adicionarParametro(usuario.getCedula());
            function.adicionarParametro(usuario.getCorreo());
            function.addicionarParametroDate(usuario.getFechaNacimiento());
            function.adicionarNumeric(usuario.getIdPerfil());
            function.adicionarParametro(usuario.getEstado());
            function.adicionarParametro(usuario.getUsuario());
            function.adicionarNumeric(usuario.getSede());
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String rtaPostg = function.getRespuesta();
                    if (rtaPostg.equalsIgnoreCase("Ok")) {
                        rtaBool = true;
                    } else {
                        rtaBool = false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Adm_UsuarioLogica.actualizaUsuario " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rtaBool;
    }
}
