/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.validacion;

import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * Clase encargada
 *
 * @author nicolas
 */
public class ValidaDuplicadodosProd {

    public boolean verificaCodigo(String codigo) {
        try {
            codigo = codigo.replaceAll(" ", "");
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FVERIFICA_COD_PROD");
            function.adicionarParametro(codigo);
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
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    if (rtaPg.equalsIgnoreCase("N")) {
                        function = null;
                        return true;
                    } else {
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
