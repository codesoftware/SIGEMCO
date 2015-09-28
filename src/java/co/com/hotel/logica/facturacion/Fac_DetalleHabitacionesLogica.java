/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.facturacion;

import co.com.hotel.dto.Habitacion;
import co.com.hotel.dto.Reservacion;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class Fac_DetalleHabitacionesLogica {

    public ArrayList<Reservacion> buscaReservasHabitacionXid(String dsha) {
        ArrayList<Reservacion> r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            String sql = "SELECT rvha_rvha, rvha_dsha, rvha_clien, rvha_fecha, rvha_fecha_ini, \n";
            sql += "rvha_fecha_fin, rvha_num_dias, rvha_fecha_venci, rvha_confirmada, \n";
            sql += "rvha_estado, rvha_tius,\n";
            sql += " pers_apellido || ' ' ||pers_nombre nombreUsua,\n";
            sql += "(select pers_apellido || ' ' ||pers_nombre nombreUsua from us_tclien, us_tpers where clien_clien = rv.rvha_clien and pers_pers = clien_pers) nombreCliente\n";
            sql += "FROM in_trvha rv, us_ttius, us_tpers\n";
            sql += "where rvha_dsha = " + dsha + "\n";
            sql += "order by rvha_fecha_ini asc";
            rs = function.enviarSelect(sql);
            while (rs.next()){
                if (cont == 0) {
                    r = new ArrayList<Reservacion>();
                    cont++;
                }
                Reservacion aux = new Reservacion();
                aux.setIdReserva(rs.getString("rvha_rvha"));
                aux.setIdDsha(rs.getString("rvha_dsha"));
                aux.setIdClien(rs.getString("rvha_clien"));
                aux.setNomClien(rs.getString("nombreCliente"));
                aux.setFechaReser(rs.getString("rvha_fecha"));
                aux.setFechaIni(rs.getString("rvha_fecha_fin"));
                aux.setFechaFin(rs.getString("rvha_fecha_fin"));
                aux.setNumDias(rs.getString("rvha_num_dias"));
                aux.setConfirma(rs.getString("rvha_confirmada"));
                aux.setEstado(rs.getString("rvha_estado"));
                aux.setIdTius(rs.getString("rvha_tius"));
                aux.setNomTius(rs.getString("nombreUsua"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_DetalleHabitacionesLogica.buscaReservasHabitacionXid " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }
    //Para pruebas unitarias de la clase
//    public static void main(String[] args) {
//        Fac_DetalleHabitacionesLogica obj = new Fac_DetalleHabitacionesLogica();
//        ArrayList<Reservacion> r = obj.buscaReservasHabitacionXid("1");
//        System.out.println("funciono");
//    }

}
