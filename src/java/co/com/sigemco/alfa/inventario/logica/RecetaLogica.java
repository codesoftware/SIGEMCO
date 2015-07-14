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
            if (!valida) {
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

    /**
     * Funcion encargada de realizar la logica de consulta de las recetas
     * teniendo en cuenta el codigo de la receta
     *
     * @param objDto
     * @return
     */
    public RecetaDto consultaGeneralXCodigo(RecetaDto objDto) {
        RecetaDto aux = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXCodigo(objDto));
            while (rs.next()) {
                if (aux == null) {
                    aux = new RecetaDto();
                }
                aux.setRece_rece(rs.getString("Rece_rece"));
                aux.setRece_codigo(rs.getString("Rece_codigo"));
                aux.setRece_nombre(rs.getString("Rece_nombre"));
                aux.setRece_desc(rs.getString("Rece_desc"));
                aux.setRece_iva(rs.getString("Rece_iva"));
                aux.setRece_estado(rs.getString("Rece_estado"));
                aux.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                aux.setRece_promedio(rs.getString("Rece_promedio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            aux = null;
        }
        return aux;
    }

    /**
     * Funcion con la cual actualiza los precios de las recetas
     *
     * @return
     */
    public String actualizaPrecioReceta(RecetaDto objDto, String tius_tius) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            String valida = this.inactivaRecetasXSede(objDto);
            if("Ok".equalsIgnoreCase(valida)){
                boolean respuesta = function.enviarUpdate(objDao.insertPrecioReceta(objDto, tius_tius));
                if(!respuesta){
                    rta = "Error al insertar el precio nuevo de la receta ";
                }
            }else{
                rta = valida;
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual inactivo todos los precios de una receta por sede
     *
     * @param objDto
     * @return
     */
    public String inactivaRecetasXSede(RecetaDto objDto) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.actualizaPreciosInactivosXSede(objDto));
            if (!valida) {
                rta = "Error al inactivar los precios";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
