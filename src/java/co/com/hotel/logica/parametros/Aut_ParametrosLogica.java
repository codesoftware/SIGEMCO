/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.hotel.logica.parametros;

import co.com.hotel.datos.session.Parametros;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;

/**
 *
 * @author SOFIA
 */
public class Aut_ParametrosLogica {
    
    public Parametros calculaFechas(Parametros parametros){
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "select to_char(to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy')+1, 'dd/mm/yyyy') manana\n";
            sql += ", to_char(to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy')-1, 'dd/mm/yyyy') ayer\n";
            sql += ", to_char(to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy')-30, 'dd/mm/yyyy') haceUnMes\n";
            sql += ", to_char(now(),'dd/mm/yyyy') hoy";
            sql += ", (SELECT to_char(date_trunc('MONTH', now()),'dd/mm/yyyy') ) primerDiaMes";
            sql += ", (select to_char(to_date((select (select extract(day from (extract(year from now()) || '-' ||\n" +
                        "extract(month from now()) + 1  || '-01')::date - '1 day'::interval))) || '/' || (SELECT extract('month' from now()))||'/'||(SELECT extract('year' from now()) ), 'dd/mm/yyyy'), 'dd/mm/yyyy')) ultimoDiaMes";
            
            rs = function.enviarSelect(sql);
            if(rs.next()){
                if(parametros == null){
                    parametros = new Parametros();
                }
                parametros.setFechaHoy(rs.getString("hoy"));
                parametros.setFechaAyer(rs.getString("ayer"));
                parametros.setFechaManana(rs.getString("manana"));
                parametros.setFechaUnMesAtras(rs.getString("haceUnMes"));
                parametros.setPrimerDiaMes(rs.getString("primerDiaMes"));
                parametros.setUltimoDiaMes(rs.getString("ultimoDiaMes"));
            }
        } catch (Exception e) {
            System.out.println("Error Aut_ParametrosLogica.calculaFechas " + e);
            return parametros;
        }finally{
            function.cerrarConexion();
            function = null;
        }
        return parametros;
    }
    
}
