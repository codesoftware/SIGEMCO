/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.general;

import co.com.hotel.datos.session.RetornoLoginDto;
import co.com.hotel.persistencia.general.EnvioFunction;

/**
 * Clase que se encargara de confirmar los datos de los usuarios.
 *
 * @author nicolas
 */
public class ConfirmaDatos {

    /**
     * Funcion la cual se encargar de confirmar los datos de un usuario por
     * medio de un Usuario y una Contraseña
     *
     * @param usuario String usuario asignado al usuario para el ingreso al
     * sistema
     * @param contra String contraseña de acceso al sistema
     * @return Boolean True(Cuando el usuario tiene acceso al sistema) *
     */
    public RetornoLoginDto confimaUsuario(String usuario, String contra) {
        RetornoLoginDto retorno = new RetornoLoginDto();
        
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FAUTENTICAR_USUA");
            function.adicionarParametro(usuario);
            function.adicionarParametro(contra);
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
                    // aqui verifico si es true or false
                    String[] rtaPg = function.getRespuesta().split(",");
                    if(rtaPg[1].equalsIgnoreCase("Acceso_aprobado")){
                        retorno.setAcceso(true);
                        if(rtaPg[0].equalsIgnoreCase("UPD")){
                            //indica que si debe redireccionar al usuario a la pagina para hacer el cambio de contraseña
                            retorno.setUpdate("SI");
                        }else{
                            retorno.setUpdate("NO");
                        }
                        function = null;
                    }else{
                        function = null;
                    }
                    
                }
            } else {
                function = null;                
            }

        } catch (Exception e) {            
        }
        return retorno;
    }
    
    public void registraUltimoIngreso(String tius){
        EnvioFunction function = new EnvioFunction();
        try {
            String sql= "update us_ttius\n";
            sql += "set tius_ultimo_ingreso = now()\n";
            sql += "where tius_tius = '"+tius+"'";
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error ConfirmaDatos.registraUltimoIngreso " + e);
        }finally{
            function.cerrarConexion();
        }
    }

}
