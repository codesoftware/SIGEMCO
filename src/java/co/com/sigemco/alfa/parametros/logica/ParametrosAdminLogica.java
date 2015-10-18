/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.parametros.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.parametros.dao.ParametrosAdminDao;
import co.com.sigemco.alfa.parametros.dto.ParametrosAdminDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author root
 */
public class ParametrosAdminLogica {

    /**
     * Funcion que consulta una lista de parametros de acuerdo con el estado
     * @return 
     */
    public Map<String, String> consultaParametrosAdm() {
        Map<String, String>resultado = new HashMap<>();
        ParametrosAdminDao objDao = new ParametrosAdminDao();
        ResultSet rs = null;
        try (EnvioFunction function = new EnvioFunction()) {
            rs = function.enviarSelect(objDao.consultaParametrosEstado("A"));
            while(rs.next()){
                resultado.put(rs.getString("parsadm_nombre"),rs.getString("parsadm_valor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
