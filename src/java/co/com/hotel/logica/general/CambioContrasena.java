/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.general;

import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * Clase encargada de cambiar la contraseña de un usuario
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class CambioContrasena {

    /**
     * Funcion encargada de hacer la logica del cambio de contraseña basado en
     * el usuario
     *
     * @param usuario Usuario al cual se le quiere cambiar la contraseña
     * @param contra Nueva contraseña principal que tendra el usuario
     * @return true(Cuando el cambio de contraseña fue exitoso ) false (Cuando
     * no se realizo el cambi de contraseña)
     */
    public boolean cambiarContra(String usuario, String contra) {
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FCAMBIO_CLAVE");
            function.adicionarParametro(usuario);
            function.adicionarParametro(contra);
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
                    // Aqui verifico si el update fue realizado
                    String rtaPg = function.getRespuesta();
                    if(rtaPg.equalsIgnoreCase("Ok")){
                        function = null;
                        return true;
                    }
                }
            }
            
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
