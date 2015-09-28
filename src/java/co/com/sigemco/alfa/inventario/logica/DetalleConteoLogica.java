/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.DetalleConteoDao;
import co.com.sigemco.alfa.inventario.dto.DetalleConteoDto;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class DetalleConteoLogica {

    /**
     * Funcion encargada de realizar la logica para obtener un detalle de conteo
     *
     * @param copr_copr
     * @param ecop_dska
     * @return
     */
    public DetalleConteoDto consultaDetalleConteo(String copr_copr, String ecop_dska) {
        DetalleConteoDto rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            DetalleConteoDao objDao = new DetalleConteoDao();
            objDao.setEcop_copr(copr_copr);
            objDao.setEcop_dska(ecop_dska);
            ResultSet rs = function.enviarSelect(objDao.consultaDetalleProductoXConteo());
            while (rs.next()) {
                if (rta == null) {
                    rta = new DetalleConteoDto();
                }
                rta.setEcop_ecop(rs.getString("ecop_ecop"));
                rta.setEcop_copr(rs.getString("ecop_copr"));
                rta.setEcop_dska(rs.getString("ecop_dska"));
                rta.setEcop_valor(rs.getString("ecop_valor"));
                rta.setEcop_existencias(rs.getString("ecop_existencias"));
                rta.setEcop_diferencia(rs.getString("ecop_diferencia"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
