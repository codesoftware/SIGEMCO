/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.validacion;

import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * Clase encargada de consultar en la base de datos si la futura informacion que
 * va hacia la base de datos ya es existente
 *
 * @author nicolas
 * @version 1.0.0
 */
public class ValidaDuplicados {
    
    public boolean verificaCedula(String cedula){
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FVERIFICA_CEDULA");
            function.adicionarParametro(cedula);
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
                    // Aqui verifico si el update fue realizado
                    String rtaPg = function.getRespuesta();
                    if(rtaPg.equalsIgnoreCase("UNICA")){
                        function = null;
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;        
    }
    
    public boolean verificaCorreo(String correo){
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FVERIFICA_CORREO");
            function.adicionarParametro(correo);
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
                    // Aqui verifico si el update fue realizado
                    String rtaPg = function.getRespuesta();
                    if(rtaPg.equalsIgnoreCase("UNICA")){
                        function = null;
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;        
    }
    
    public boolean verificaUsuario(String usuario){
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FVERIFICA_USUARIO");
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
                    // Aqui verifico si el update fue realizado
                    String rtaPg = function.getRespuesta();
                    if(rtaPg.equalsIgnoreCase("UNICA")){
                        function = null;
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;        
    }

}
