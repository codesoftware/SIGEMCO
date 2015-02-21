/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.ClaseDao;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase encargada de realizar la logica de las clases del puc
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClaseLogica {

    /**
     * Funcion encargada de realizar la logica para obtener una lista con las
     * clases que se encuentran parametrizadas en el sistema
     *
     * @return
     */
    public List<ClaseDto> consultaGeneralActivo() {
        List<ClaseDto> rta = null;
        ClaseDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new ClaseDao();
            ResultSet rs = function.enviarSelect(objDao.cosultaGeneralActivos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<ClaseDto>();
                }
                ClaseDto aux = new ClaseDto();
                aux.setClas_clas(rs.getString("clas_clas"));
                aux.setClas_estado(rs.getString("clas_estado"));
                aux.setClas_nombre(rs.getString("clas_nombre"));
                aux.setClas_codigo(rs.getString("clas_codigo"));
                aux.setClas_descripcion(rs.getString("clas_descripcion"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener una clase por su Id
     *
     * @param objDto
     * @return
     */
    public ClaseDto obtieneClaseXId(ClaseDto objDto) {
        ClaseDto rta = null;
        ClaseDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.consultaEspeciXId());
            if (rs.next()) {
                rta = new ClaseDto();
                rta.setClas_clas(rs.getString("clas_clas"));
                rta.setClas_estado(rs.getString("clas_estado"));
                rta.setClas_nombre(rs.getString("clas_nombre"));
                rta.setClas_codigo(rs.getString("clas_codigo"));
                rta.setClas_descripcion(rs.getString("clas_descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de mapear un objeto Dto en un objeto Dao
     *
     * @param objDto
     * @return
     */
    public ClaseDao poblarDao(ClaseDto objDto) {
        ClaseDao objDao = null;
        try {
            objDao = new ClaseDao();
            objDao.setClas_clas(objDto.getClas_clas());
            objDao.setClas_estado(objDto.getClas_estado());
            objDao.setClas_nombre(objDto.getClas_nombre());
            objDao.setClas_codigo(objDto.getClas_codigo());
            objDao.setClas_descripcion(objDto.getClas_descripcion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }
}
