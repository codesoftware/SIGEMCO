/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.facturacion.dao.DetFactServicoDao;
import co.com.sigemco.alfa.facturacion.dto.DetFactServicoDto;
import java.sql.ResultSet;

/**
 *
 * @author nicolas
 */
public class DetFactServicioLogica {
    
    public DetFactServicoDto simulaFacturacionServicio(String numDias, String idHabitacion){
        DetFactServicoDao objDao = new DetFactServicoDao();
        DetFactServicoDto objDto = null;
        try(EnvioFunction function = new EnvioFunction()) {
            objDao.setNumDias(numDias);
            objDao.setDsha_dsha(idHabitacion);
            ResultSet rs = function.enviarSelect(objDao.simulaFacturacionServicio());
            if(rs.next()){
                if(objDto == null){
                    objDto = new DetFactServicoDto();
                }
                objDto.setNum_hab(rs.getString("dsha_num_hab"));
                objDto.setIvaUnidad(rs.getString("vlr_Iva"));
                objDto.setIvaTotal(rs.getString("ivaTotal"));
                objDto.setPrecioUnidad(rs.getString("prha_precio"));
                objDto.setPrecioTotal(rs.getString("vlrTotal"));  
                objDto.setTotalPagar(rs.getString("totalPagar"));
                objDto.setNumDias(numDias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDto;
    }
    
    
}
