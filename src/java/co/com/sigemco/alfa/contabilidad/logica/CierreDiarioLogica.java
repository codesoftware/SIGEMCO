/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dto.CierreDiarioDto;

/**
 *
 * @author Personal
 */
public class CierreDiarioLogica {

    /**
     * Funcion que permite insertar el cierre diario
     *
     * @param usuario
     * @param sede
     * @param fecha
     * @return
     */
    public String insertaCierreDiario(String usuario, String sede, String fecha) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("IN_FINSERT_CIERRE_DIARIO");
            function.addicionarParametroDate(fecha);
            function.adicionarNumeric(sede);
            function.adicionarNumeric(usuario);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    rta = function.getRespuesta();

                }
            }

        } catch (Exception e) {
            rta = "Error al insertar Cierre";
            e.printStackTrace();
        }
        return rta;

    }
    

}
