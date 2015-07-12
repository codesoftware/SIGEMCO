/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.RecetaDao;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class RecetaLogica {

    /**
     * Funcion encargada de realizar la inserccion de una receta a la base de
     * datos
     *
     * @param objDto
     * @return
     */
    public String insertaReceta(RecetaDto objDto) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.insertareceta(objDto));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error al insertar la receta";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica de consulta de las recetas
     * teniendo en cuenta los filtros dados por el usuario
     *
     * @param objDto
     * @return
     */
    public List consultaGeneralXFiltros(RecetaDto objDto) {
        List<RecetaDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXFiltros(objDto));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                RecetaDto aux = new RecetaDto();
                aux.setRece_rece(rs.getString("Rece_rece"));
                aux.setRece_codigo(rs.getString("Rece_codigo"));
                aux.setRece_nombre(rs.getString("Rece_nombre"));
                aux.setRece_desc(rs.getString("Rece_desc"));
                aux.setRece_iva(rs.getString("Rece_iva"));
                aux.setRece_estado(rs.getString("Rece_estado"));
                aux.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                aux.setRece_promedio(rs.getString("Rece_promedio"));
                rta.add(aux);

            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la logica para actualizar las recetas del
     * sistema
     *
     * @param objDto
     * @return
     */
    public String actualizaReceta(RecetaDto objDto) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.actualizaRecetaXId(objDto));
            if(!valida){
                rta = "Error al ejecutar la receta";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que se encarga de realizar la logica para la busqueda de una
     * receta por su id
     *
     * @param objDto
     * @return
     */
    public RecetaDto consultaRecetaXId(RecetaDto objDto) {
        RecetaDto rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXId(objDto));
            if (rs.next()) {
                if (rta == null) {
                    rta = new RecetaDto();
                }
                rta.setRece_rece(rs.getString("Rece_rece"));
                rta.setRece_codigo(rs.getString("Rece_codigo"));
                rta.setRece_nombre(rs.getString("Rece_nombre"));
                rta.setRece_desc(rs.getString("Rece_desc"));
                rta.setRece_iva(rs.getString("Rece_iva"));
                rta.setRece_estado(rs.getString("Rece_estado"));
                rta.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                rta.setRece_promedio(rs.getString("Rece_promedio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
