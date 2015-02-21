/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.usuarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase la cual se encargara de buscar usuarios en la base de datos dependiendo
 * de los filtros los cuales se le enviaran
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConsultaUsuarios {

    private ArrayList<Usuario> usuaResult = null;
    private String trajoDatos;
    private List<String> perfiles;

    /**
     * Funcion encargada de buscar y los usuarios que tengan coincidencias en el
     * usuario que se envia como parametro
     *
     * @param String usuario con el cual se buscara en base de datos
     * @return
     */
    public boolean buscaUsuariosXUsua(String usuario) {
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FCONSULTA_USUARIOS");
            function.adicionarParametro(usuario);
            function.adicionarNull();
            function.adicionarNull();
            function.adicionarNull();
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarStringVector();
            function.cerrarConexion();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String usuarios[] = function.getRespuestaVector();
                    if (usuarios.length == 0 || usuarios == null) {
                        return false;
                    } else {
                        boolean valida = cargarUsuarios(usuarios);
                        if (valida) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public ArrayList<Usuario> getUsuaResult() {
        return usuaResult;
    }

    public void setUsuaResult(ArrayList<Usuario> usuaResult) {
        this.usuaResult = usuaResult;
    }

    /**
     * Funcion encargada en armar el array de usuarios basado en un Array de
     * Strings
     *
     * @return boolean (true) si el objeto fue armado con exito
     */
    public boolean cargarUsuarios(String[] cadenas) {
        try {
            int tam = cadenas.length;
            this.usuaResult = new ArrayList<Usuario>();
            for (int i = 0; i < tam; i++) {
                Usuario usua = construlleUsuario(cadenas[i]);
                this.usuaResult.add(usua);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion que se encarga de armar un objeto usuario en base a un String la
     * estructura del String es la siguiente
     *
     * @param cadena
     * @return
     */
    public Usuario construlleUsuario(String cadena) {
        Usuario usua = new Usuario();
        String[] atributos = cadena.split(",");
        usua.setCedula(atributos[0]);
        usua.setNombre(atributos[1]);
        usua.setApellido(atributos[2]);
        usua.setCorreo(atributos[3]);
        usua.setTelefono(atributos[4]);
        usua.setTipoUsuario(atributos[5]);
        usua.setUsuario(atributos[6]);
        usua.setTipoUsuario(atributos[7]);
        usua.setFechaNacimiento(atributos[8]);
        usua.setEstado(atributos[9]);
        usua.setIdPerfil(atributos[10]);
        usua.setIdTius(atributos[11]);
        usua.setSede(atributos[12]);
        return usua;
    }

    public Usuario buscaUsuarioXFiltros(Usuario usuario) {
        Usuario usuarioRta = null;
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("US_FCONSULTA_USUARIOS");
            if (this.verificaNulo(usuario.getUsuario())) {
                function.adicionarParametro(usuario.getUsuario());
            } else {
                function.adicionarNull();
            }
            if (this.verificaNulo(usuario.getNombre())) {
                function.adicionarParametro(usuario.getNombre());
            } else {
                function.adicionarNull();
            }
            if (this.verificaNulo(usuario.getCedula())) {
                function.adicionarParametro(usuario.getCedula());
            } else {
                function.adicionarNull();
            }
            if (this.verificaNulo(usuario.getApellido())) {
                function.adicionarParametro(usuario.getApellido());
            } else {
                function.adicionarNull();
            }
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarStringVector();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String usuarios[] = function.getRespuestaVector();
                    if (usuarios.length != 0) {
                        boolean valida = cargarUsuarios(usuarios);
                        if (valida) {
                            trajoDatos = "S";
                            return usuaResult.get(0);
                        } else {
                            trajoDatos = "N";
                            return usuario;
                        }
                    } else {
                        trajoDatos = "N";
                        return usuario;
                    }

                }
            }
        } catch (Exception e) {

        } finally {
            function.cerrarConexion();
        }
        trajoDatos = "N";
        return usuarioRta;
    }

    public boolean verificaNulo(String campo) {
        if (campo.isEmpty() || campo == null || campo.trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public String getTrajoDatos() {
        return trajoDatos;
    }

    public void setTrajoDatos(String trajoDatos) {
        this.trajoDatos = trajoDatos;
    }
    
}
