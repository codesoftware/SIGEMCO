/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.general;

import co.com.hotel.email.EnviarCorreoIns;
import co.com.hotel.action.general.*;
import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * Clase la cual hace la logica de recuperar la contraseña del usuario
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class RecuperaContraLogica {

    private String usuario;

    /**
     * Función la cual genera la clave y envia el correo con los datos del
     * usuario para que pueda ingresar de nuevo a la aplicación
     *
     * @param correo correo del usuario al cual se le enviara el correo
     * @return boolean indica si el envio de los datos fue exitoso o no
     */
    public boolean recuperaContra(String correo) {
        boolean rta = true;
        RandomPassword contra = new RandomPassword();
        EnviarCorreoIns enviar = new EnviarCorreoIns();
        try {
            if (confirmaDatos(correo)) {
                String stringContra = contra.generarContrasena();
                enviar.logginCorreo();
                enviar.setAsunto("Cambio Contraseña Shaoloom");
                enviar.setMensaje("Su solicitud de  cambio de contraseña a sido procesada "
                        + "con estos datos podra ingresar a la aplicación:"
                        + "\n\n USUARIO: " + this.usuario
                        + "\n\n CONTRASEÑA: " + stringContra
                        + "\n\n Si esta solicitud no fue realizada por usted por favor omita este mensaje"
                );
                boolean confirma = cambioContra(this.usuario, stringContra);
                //confirma = true; corregir
                if (confirma) {
                    enviar.envioCorreoIns(correo);
                } else {
                    return false;
                }

            } else {
                return false;
            }
            return rta;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Función la cual confirma datos y me permite saber si se deben enviar los
     * datos
     *
     * @param String correo: E-mail del usuario el cual desea recordar su
     * contraseña
     * @return Boolean el cual me dice si envia el correo de cambio de
     * contraseña o no
     */
    public boolean confirmaDatos(String correo) {
        // aqui va si el sistema debe o no enviar los datos al correo el cual se ingreso...
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("US_FRECUPERAR_USUA");
            function.adicionarParametro(correo);
            String rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    if (!function.getRespuesta().isEmpty() || function.getRespuesta() != null) {
                        this.usuario = function.getRespuesta();
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error RecuperaContraLogica.confirmaDatos " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return false;
    }

    /**
     * Función la cual realizara un update sobre la base de datos en el registro
     * de contraseña auxiliar para que el usuario pueda tener acceso al sistema
     *
     * @param usuario String Usuario al cual se el cambiara la contraseña
     * auxiliar
     * @param contra String contraseña auxiliar la cual se va ha cambiar
     * @return
     */
    public boolean cambioContra(String usuario, String contra) {
        boolean rta = false;
        EnvioFunction function = new EnvioFunction();
        function.adicionarNombre("US_FINSERT_CONTRA_AUX");
        String param = "";
        param = function.adicionarParametro(usuario);
        param = function.adicionarParametro(contra);
        String rtaPg = function.llamarFunction(function.getSql());
        function.recuperarString();
        function.cerrarConexion();
        String[] rtaVector = rtaPg.split("-");
        // Este mensaje lo envia la funcion que envia la funcion de java que
        // confirma que el llamado de a la funcion fue exitiso
        if (rtaVector[1].equalsIgnoreCase("Ok")) {
            //Esta es la respuesta que retorna postgres
            String rtaFunc = function.getRespuesta();
            if (rtaFunc.equalsIgnoreCase("OK")) {
                System.out.println("El usuario actualizo correctamente su contraseña");
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
