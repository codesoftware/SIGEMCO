/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.validacion;

import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;

/**
 *
 * @author SOFIA
 */
public class ValidaDuplicadosServ {
    
    public boolean validaNumeroHabitacion(String valor){
        EnvioFunction function = new EnvioFunction();
        boolean rta = false;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "select count(*) repet\n";
            sql += "from in_tdsha\n";
            sql += "where dsha_num_hab = "+valor;
            rs = function.enviarSelect(sql);
            if(rs.next()){
                int cont = rs.getInt("repet");
                if(cont== 0){
                    rta =  true;
                }
            }
                    
        } catch (Exception e) {
            System.out.println("Error ValidaDuplicadosServ.validaNumeroHabitacion "+ e);
            return false;            
        }finally{
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }
    
}
