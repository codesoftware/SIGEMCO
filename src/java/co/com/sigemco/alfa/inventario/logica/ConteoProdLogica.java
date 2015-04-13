/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.ConteoProdDao;
import co.com.sigemco.alfa.inventario.dto.ConteoProdDto;

/**
 *
 * @author nicolas
 */
public class ConteoProdLogica {

    /**
     * Funcion encargada de realizar la logica para la insercion del registro de
     * un conteo de inventario nuevo
     *
     * @param objDto
     * @return
     */
    public String insertaCreacionConteo(ConteoProdDto objDto) {
        String rta = "";
        ConteoProdDao objDao = null;
        try(EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            boolean valida = function.enviarUpdate(objDao.insertaConteo());            
            if(valida){
                rta = "Ok";
            }else{
                rta = "Error";                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error";
        }
        return rta;
    }

    /**
     * Funcion encargada de poblar un objeto Dao apartir de un objeto Dto
     *
     * @param objDto
     * @return
     */
    public ConteoProdDao poblarDao(ConteoProdDto objDto) {
        ConteoProdDao objDao = new ConteoProdDao();
        try {
            objDao.setCopr_copr(objDto.getCopr_copr());
            objDao.setCopr_estado(objDto.getCopr_estado());
            objDao.setCopr_tius(objDto.getCopr_tius());
            objDao.setCopr_fecha(objDto.getCopr_fecha());
            objDao.setCopr_sede(objDto.getCopr_sede());
            objDao.setCopr_fec_ini(objDto.getCopr_fec_ini());
            objDao.setCopr_fec_fin(objDto.getCopr_fec_fin());
            objDao.setCopr_desc(objDto.getCopr_desc());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }

}
