/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.permisos.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.permisos.dao.AdPermisosDao;
import java.sql.ResultSet;

/**
 *
 * @author john
 */
public class AdPermisosLogica {
    
    public String consultaPermiso(String clave){
        String rta="V";
        AdPermisosDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();){
            objDao = new AdPermisosDao();
            ResultSet rs = function.enviarSelect(objDao.buscaClave(clave));
            while(rs.next()){
               rta= rs.getString("PARA_ESTADO");
            }
        } catch (Exception e) {
            
        }
        return rta;
    }
    
}
