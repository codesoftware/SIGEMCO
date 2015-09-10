/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.facturacion.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.facturacion.dao.FacturaDao;
import co.com.sigemco.alfa.facturacion.dto.FacturaDto;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class FacturaLogica {

    /**
     * Funcion encargada de realizar la busqueda de una factura por medio de su
     * id
     *
     * @param fact_fact
     * @return
     */
    public FacturaDto buscaFacturaXId(Integer fact_fact) {
        FacturaDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            FacturaDao objDao = new FacturaDao();
            ResultSet rs = function.enviarSelect(objDao.buscaFacturaXId(fact_fact));
            if (rs.next()) {
                if (objDto == null) {
                    objDto = new FacturaDto();
                }
                objDto.setFactFact(rs.getString("fact_fact"));
                objDto.setFactTius(rs.getString("fact_tius"));
                objDto.setFactFecini(rs.getString("fact_fec_ini"));
                objDto.setFactFeccierre(rs.getString("fact_fec_cierre"));
                objDto.setFactClien(rs.getString("fact_clien"));
                objDto.setFactVlrtotal(rs.getString("fact_vlr_total"));
                objDto.setFactVlriva(rs.getString("fact_vlr_iva"));
                objDto.setFactTipopago(rs.getString("fact_tipo_pago"));
                objDto.setFactIdvoucher(rs.getString("fact_id_voucher"));
                objDto.setFactCometarios(rs.getString("fact_cometarios"));
                objDto.setFactEstado(rs.getString("fact_estado"));
                objDto.setFactNaturaleza(rs.getString("fact_naturaleza"));
                objDto.setFactDevolucion(rs.getString("fact_devolucion"));
                objDto.setFactOriginal(rs.getString("fact_original"));
                objDto.setFactVlrdcto(rs.getString("fact_vlr_dcto"));
                objDto.setFactVlrefectivo(rs.getString("fact_vlr_efectivo"));
                objDto.setFactVlrtarjeta(rs.getString("fact_vlr_tarjeta"));
                objDto.setFactCierre(rs.getString("fact_cierre"));
                objDto.setFactSede(rs.getString("fact_sede"));
            }
            if (objDto != null) {
                objDto.setMvco_trans(this.buscaMvcoTrasnFact(fact_fact).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDto;
    }

    /**
     * Funcion con la cual se realiza la logica para buscar el id de transaccion
     * que se genero al realizar la facturacion
     *
     * @param fact_fact
     * @return
     */
    public Integer buscaMvcoTrasnFact(Integer fact_fact) {
        Integer mvco_trans = null;
        try (EnvioFunction function = new EnvioFunction()) {
            FacturaDao objDao = new FacturaDao();
            ResultSet rs = function.enviarSelect(objDao.buscaMoviContableFact(fact_fact));
            if (rs.next()) {
                if (mvco_trans == null) {
                    mvco_trans = new Integer(0);
                }
                mvco_trans = rs.getInt("mvco_trans");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvco_trans;
    }

}
