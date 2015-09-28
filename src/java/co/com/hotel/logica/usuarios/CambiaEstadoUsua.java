/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.usuarios;

import co.com.hotel.persistencia.general.EnvioFunction;

/**
 *
 * @author nicolas
 */
public class CambiaEstadoUsua {

    private String msnRta;

    public boolean cambiaEstado(String usuario) {
        EnvioFunction function = new EnvioFunction();
        function.adicionarNombre("us_fcambia_estado_usuario");
        function.adicionarParametro(usuario);
        String rta = "";
        rta = function.llamarFunction(function.getSql());
        function.recuperarString();
        function.cerrarConexion();
        String[] rtaVector = rta.split("-");
        int tam = rtaVector.length;
        if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso.
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                String confirma = function.getRespuesta();
                if(confirma.equalsIgnoreCase("ok")){
                    msnRta = "Proceso realizado correctamente";
                    return true;
                }else{
                    msnRta = "Error al cambiar el usuario de estado";
                }                
                
            }
        }
        return false;
    }
}
