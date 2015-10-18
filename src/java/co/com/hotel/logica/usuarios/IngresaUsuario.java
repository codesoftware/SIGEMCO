/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.usuarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.email.EnviarCorreoIns;
import co.com.hotel.logica.general.RandomPassword;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.email.SendMail;

/**
 *
 * @author nicolas
 */
public class IngresaUsuario {

    public String ingresarUsuario(Usuario usuario, String fechaNac) {
        RandomPassword contra = new RandomPassword();
        EnviarCorreoIns enviar = new EnviarCorreoIns();
        String stringContra = contra.generarContrasena();
        boolean rtaBd = nuevoUsuarioDB(usuario, stringContra, fechaNac);

        if (rtaBd == true) {
            enviar.logginCorreo();
            String asunto = "Nuevo usuario";
            String msg = "Bienvenido al Sistema de Contabilidad "
                    + "con estos datos podra ingresar a la aplicación:"
                    + "\n\n USUARIO: " + usuario.getUsuario()
                    + "\n\n CONTRASEÑA: " + stringContra
                    + "\n\n Si esta solicitud no fue realizada por usted por favor omita este mensaje";

            SendMail sm = new SendMail(msg, usuario.getCorreo(), asunto);
            if (sm.send()) {
                return "Correo enviado";
            } else {
                return stringContra;
            }

        } else {
            return "Error";
        }

    }

    public boolean nuevoUsuarioDB(Usuario usuario, String contra, String fechaNac) {
        EnvioFunction function = new EnvioFunction();
        function.adicionarNombre("US_FINSERTA_USUA");
        String param = "";
        param = function.adicionarParametro(usuario.getNombre().replace(",", " "));
        param = function.adicionarParametro(usuario.getApellido().replace(",", " "));
        param = function.adicionarParametro(usuario.getCedula().replace(",", " "));
        param = function.adicionarParametro(usuario.getCorreo().replace(",", " "));
        param = function.addicionarParametroDate(fechaNac.replace(",", " "));
        param = function.adicionarParametro(usuario.getUsuario().replace(",", " "));
        param = function.adicionarParametro(contra.replace(",", " "));
        param = function.adicionarParametro(usuario.getIdPerfil());
        param = function.adicionarNumeric(usuario.getSede());
        try{
        param = function.adicionarParametro(usuario.getCampo1().replace(",", " "));
        param = function.adicionarParametro(usuario.getCampo2().replace(",", " "));
        param = function.adicionarParametro(usuario.getCampo3().replace(",", " "));
        param = function.adicionarParametro(usuario.getCampo4().replace(",", " "));
        }catch(Exception e){
            System.out.println("Usuario sin campos");
        }
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
                System.out.println("El usuario se inserto correctamente en la base de datos");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
