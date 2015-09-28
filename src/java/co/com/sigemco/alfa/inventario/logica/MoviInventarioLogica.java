/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.MoviInventarioDao;
import co.com.sigemco.alfa.inventario.dto.MoviInventarioDto;
import java.sql.ResultSet;

/**
 *
 * @author Nicolas
 */
public class MoviInventarioLogica {

    /**
     * Funcion encargada de realizar la logica para obtener el movimiento de
     * invetario parametrizado para realizar compras de productos
     *
     * @return
     */
    public MoviInventarioDto buscaMoviInventarioCompra() {
        MoviInventarioDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            MoviInventarioDao objDao = new MoviInventarioDao();
            ResultSet rs = function.enviarSelect(objDao.obtineMoviInvCompra());
            if (rs.next()) {
                if (objDto == null) {
                    objDto = new MoviInventarioDto();
                }
                objDto.setMvin_mvin(rs.getString("mvin_mvin"));
                objDto.setMvin_descr(rs.getString("mvin_descr"));
                objDto.setMvin_natu(rs.getString("mvin_natu"));
                objDto.setMvin_usim(rs.getString("mvin_usim"));
                objDto.setMvin_venta(rs.getString("mvin_venta"));
                objDto.setMvin_inicial(rs.getString("mvin_inicial"));
                objDto.setMvin_revfact(rs.getString("mvin_revfact"));
                objDto.setMvin_compra(rs.getString("mvin_compra"));
                objDto.setMvin_perdida(rs.getString("mvin_perdida"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDto;
    }
}
