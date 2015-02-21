/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.GrupoDao;
import co.com.sigemco.alfa.contabilidad.dto.GrupoDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar la logica encargada realacionada con los grupos
 * del puc
 *
 * @author Nicolas
 */
public class GrupoLogica {

    /**
     * Funcion encargada de realizar la logica para obtener todos los grupos
     * teniendo como referencia una clase
     *
     * @param clas_clas
     * @return
     */
    public List<GrupoDto> consultaGeneralActivo(String clas_clas) {
        List<GrupoDto> rta = null;
        GrupoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new GrupoDao();
            ResultSet rs = function.enviarSelect(objDao.obtenerGruposXClas_Clas(clas_clas));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<GrupoDto>();
                }
                GrupoDto aux = new GrupoDto();
                aux.setGrup_grup(rs.getString("grup_grup"));
                aux.setGrup_clas(rs.getString("grup_clas"));
                aux.setGrup_estado(rs.getString("grup_estado"));
                aux.setGrup_nombre(rs.getString("grup_nombre"));
                aux.setGrup_codigo(rs.getString("grup_codigo"));
                aux.setGrup_descripcion(rs.getString("grup_descripcion"));
                rta.add(aux);
                aux = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de mapear un objeto dto a un Objeto Dao
     *
     * @param objDto
     * @return
     */
    public GrupoDao poblarDao(GrupoDto objDto) {
        GrupoDao objDao = null;
        objDao.setGrup_grup(objDto.getGrup_grup());
        objDao.setGrup_clas(objDto.getGrup_clas());
        objDao.setGrup_estado(objDto.getGrup_estado());
        objDao.setGrup_nombre(objDto.getGrup_nombre());
        objDao.setGrup_codigo(objDto.getGrup_codigo());
        objDao.setGrup_descripcion(objDto.getGrup_descripcion());
        return objDao;
    }

    /**
     * Funcion encargada de realizar la logica para obtener un grupo basandonos
     * en el id de ese grupo
     *
     * @param grup_grup
     * @return
     */
    public GrupoDto obtieneGrupoXId(String grup_grup) {
        GrupoDao objDao = null;
        GrupoDto aux = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new GrupoDao();
            objDao.setGrup_grup(grup_grup);
            ResultSet rs = function.enviarSelect(objDao.obtenerGruposXId());
            while (rs.next()) {
                aux = new GrupoDto();
                aux.setGrup_grup(rs.getString("grup_grup"));
                aux.setGrup_clas(rs.getString("grup_clas"));
                aux.setGrup_estado(rs.getString("grup_estado"));
                aux.setGrup_nombre(rs.getString("grup_nombre"));
                aux.setGrup_codigo(rs.getString("grup_codigo"));
                aux.setGrup_descripcion(rs.getString("grup_descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

}
