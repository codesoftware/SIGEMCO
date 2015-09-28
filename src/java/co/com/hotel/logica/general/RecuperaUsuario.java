/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.general;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * *
 * Clase con la cual recupero los datos personales del usuario
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class RecuperaUsuario {    

    public Usuario recuperaDatosUsuario(String usuaSistema) {
        Usuario usuario= new Usuario();
        try {            
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FRECUPERAR_DATOS");
            function.adicionarParametro(usuaSistema);
            String rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            function.cerrarConexion();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String rtaPg = function.getRespuesta();
                    usuario = this.obtenerObjeto(rtaPg);
                }                
            }            
        } catch (Exception e) {
            usuario = null;
        }

        return usuario;
    }
    
    public Usuario obtenerObjeto(String rta) {
        Usuario usuario= new Usuario();
        String[] vector;
        try {
            vector = rta.split(",");
            for (int i = 0; i < vector.length; i++) {
                String aux = vector[i];
                if (i == 0) {
                    usuario.setCedula(aux);
                } else if (i == 1) {
                    usuario.setNombre(aux);
                } else if (i == 2) {
                    usuario.setApellido(aux);
                } else if (i == 3) {
                    usuario.setCorreo(aux);
                } else if (i == 4) {
                    usuario.setTelefono(aux);
                } else if (i == 5){
                    usuario.setTipoUsuario(aux);                    
                } else if (i == 6){
                    usuario.setNomPerfil(aux);
                } else if (i == 7){
                    usuario.setPermisos(aux);
                } else if (i == 8){
                    usuario.setIdTius(aux);
                }else if (i == 9){
                    usuario.setUltimoIngreso(aux);

                }
            }
        } catch (Exception e) {
           usuario = null;
        }
        return usuario;
    }
}
