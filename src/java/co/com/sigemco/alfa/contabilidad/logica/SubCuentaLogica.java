/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.SubCuentaDao;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.dto.CuentaDto;
import co.com.sigemco.alfa.contabilidad.dto.GrupoDto;
import co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Funcion encargada de realizar la logica para Sub Cuentas
 *
 * @author Daniel
 */
public class SubCuentaLogica {

    /**
     * Funcion encargada de realizar la logica para obtener la lista de Sub
     * Cuentas por medio del id de la cuenta
     *
     * @author Daniel
     */
    public List<SubCuentaDto> obtieneSubCuentaXCuenta(String cuen_cuen) {
        List<SubCuentaDto> rta = null;
        SubCuentaDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();) {
            objDao = new SubCuentaDao();
            ResultSet rs = function.enviarSelect(objDao.subCuentasXIdCuenta(cuen_cuen));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<SubCuentaDto>();
                }
                SubCuentaDto objDto = new SubCuentaDto();
                objDto.setSbcu_clas(rs.getString("sbcu_clas"));
                objDto.setSbcu_codigo(rs.getString("sbcu_codigo"));
                objDto.setSbcu_cuen(rs.getString("sbcu_cuen"));
                objDto.setSbcu_descripcion(rs.getString("sbcu_descripcion"));
                objDto.setSbcu_estado(rs.getString("sbcu_estado"));
                objDto.setSbcu_grup(rs.getString("sbcu_grup"));
                objDto.setSbcu_nombre(rs.getString("sbcu_nombre"));
                objDto.setSbcu_sbcu(rs.getString("sbcu_sbcu"));
                rta.add(objDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para Sub Cuentas
     *
     * @author Daniel
     */
    public String insertSubCuenta(SubCuentaDto objDto, ClaseDto clasDto, CuentaDto cuenDto, GrupoDto grupoDto) {
        String rta = "OK";
        SubCuentaDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();) {
            objDao = new SubCuentaDao();

            objDto.setSbcu_clas(clasDto.getClas_clas());
            objDto.setSbcu_grup(grupoDto.getGrup_grup());
            objDto.setSbcu_cuen(cuenDto.getCuen_cuen());

            objDao = poblarDao(objDto);
            System.out.println("Query: " + objDao.insertSubCuenta());
            boolean rs = function.enviarUpdate(objDao.insertSubCuenta());
            if (!rs) {
                rta = "Error ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de poblar del dao de SubCuentas con el Dto de
     * SubCuentas
     *
     * @param objDto
     * @return
     */
    public SubCuentaDao poblarDao(SubCuentaDto objDto) {
        SubCuentaDao objDao = new SubCuentaDao();
        try {            
            objDao.setSbcu_clas(objDto.getSbcu_clas());
            objDao.setSbcu_codigo(objDto.getSbcu_codigo());
            objDao.setSbcu_cuen(objDto.getSbcu_cuen());
            objDao.setSbcu_descripcion(objDto.getSbcu_descripcion());
            objDao.setSbcu_estado(objDto.getSbcu_estado());
            objDao.setSbcu_grup(objDto.getSbcu_grup());
            objDao.setSbcu_nombre(objDto.getSbcu_nombre());
            objDao.setSbcu_sbcu(objDto.getSbcu_sbcu());
            objDao.setSbcu_naturaleza(objDto.getSbcu_naturaleza());
        } catch (Exception e) {
            e.printStackTrace();
            objDao = null;
        }
        return objDao;
    }
}
