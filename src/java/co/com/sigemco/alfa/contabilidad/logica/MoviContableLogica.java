/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dto.MoviContableDao;
import co.com.sigemco.alfa.contabilidad.dto.MoviContableDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class MoviContableLogica {

    /**
     * Funcion encargada de realizar la logica para obtener la informacion
     * necesaria para un asiento contable
     *
     * @param mvco_trans String id de la transaccion
     * @return
     */
    public List<MoviContableDto> generaAsientoContable(String mvco_trans) {
        List<MoviContableDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()){
            MoviContableDao objDao = new MoviContableDao();            
            objDao.setMvco_trans(mvco_trans);
            ResultSet rs= function.enviarSelect(objDao.generaAsientoContable());
            while(rs.next()){
                if(rta==null){
                    rta = new ArrayList<MoviContableDto>();
                }
                MoviContableDto aux = new MoviContableDto();
                aux.setSbcu_nombre(rs.getString("sbcu_nombre"));
                aux.setSbcu_codigo(rs.getString("sbcu_codigo"));
                aux.setDebitos(rs.getString("debitos"));
                aux.setCreditos(rs.getString("creditos"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }
}
