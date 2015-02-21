/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.logica.servicio;

import co.com.hotel.dto.Habitacion;
import co.com.hotel.dto.PrecioHabitacion;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class Adm_ServPrecioLogica {
    
    public String insertarPrecioHabitacion(Habitacion obj, String tius_tius){
        String rta = "Ok";
        EnvioFunction function = new EnvioFunction();        
        String sql = "";
        try {
            sql = "INSERT INTO in_tprha(prha_dsha, prha_precio, prha_tius_crea, prha_tius_update)\n";
            sql += "VALUES ( "+obj.getIdHabitacion()+", "+obj.getPrecio()+", "+tius_tius+", "+tius_tius+" )";
            function.enviarUpdate(sql);
        }catch (Exception e) {
            System.out.println("Error Adm_ServPrecioLogica.insertarPrecioHabitacion " + e);
            e.printStackTrace();
            rta = "Error" + e;
        }finally{
            function.cerrarConexion();
        }
        return rta;
    }
    
    public List<PrecioHabitacion> buscaHistorialPrecios(String dsha_dsha){
        List<PrecioHabitacion> r = null;
        EnvioFunction function = new EnvioFunction();        
        String sql = "";
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "select prha_prha,prha_precio,to_char(prha_fecha,'dd/mm/yyyy') fecha ,prha_tius_crea,\n";
            sql += "case when prha_estado = 'A' THEN 'ACTIVO' else 'INACTIVO' END prha_estado \n";
            sql += "from in_tprha\n";
            sql += "where prha_dsha = "+dsha_dsha + "\n";
            sql += "order by prha_prha desc";
            rs = function.enviarSelect(sql);
            while(rs.next()){
                if(cont == 0){
                    r = new ArrayList<PrecioHabitacion>();
                    cont = 1;
                }
                PrecioHabitacion aux = new PrecioHabitacion();
                aux.setPrha_prha(rs.getString("prha_prha"));
                aux.setPrha_dsha(dsha_dsha);
                aux.setPrha_precio(rs.getBigDecimal("prha_precio"));
                aux.setPrha_fecha(rs.getString("fecha"));
                aux.setPrha_tius_crea(rs.getString("prha_tius_crea"));
                aux.setPrha_estado(rs.getString("prha_estado"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServPrecioLogica.buscaHistorialPrecios " + e);
            e.printStackTrace();
        }finally{
            function.cerrarConexion();
        }
        return r;
    }    
}
