/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.dto.Sede;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.PrecioSedeDao;
import co.com.sigemco.alfa.inventario.dto.PrecioProductoDto;
import co.com.sigemco.alfa.inventario.dto.PrecioSedeDto;
import groovy.util.MapEntry;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Nicolas
 */
public class PreciosLogica {

    /**
     * Funcion encargada de buscar todos los precios que han sido parametrizados
     * para un producto
     *
     * @param dska_dska llave primaria de la tabla de descripcion de productos
     * @return
     */
    public List<PrecioSedeDto> buscaPreciosSedes(String dska_dska) {
        List<PrecioSedeDto> rta = null;
        try {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            Map<String, String> sedes = sedeLogica.obtieneSedes();
            for (Entry<String, String> e : sedes.entrySet()) {
                if (rta == null) {
                    rta = new ArrayList<PrecioSedeDto>();
                }
                PrecioSedeDto aux = new PrecioSedeDto();
                aux.setSede_sede(e.getKey());
                aux.setSede_nombre(e.getValue());
                aux.setPrecios(this.buscaPrecioSede(e.getKey(), dska_dska));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para buscar el histrorial de
     * precios de una sede en especifico
     *
     * @param sede_sede String identificador primario para la tabla de sedes
     * @return
     */
    public List<PrecioProductoDto> buscaPrecioSede(String sede_sede, String dska_dska) {
        List<PrecioProductoDto> rta = null;
        PrecioSedeDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new PrecioSedeDao();
            objDao.setSede_sede(sede_sede);
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.obtienePreciosProductoPorSedeConFormatos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<PrecioProductoDto>();
                }
                PrecioProductoDto aux = new PrecioProductoDto();
                aux.setPrpr_prpr(rs.getString("prpr_prpr"));
                aux.setPrpr_dska(rs.getString("prpr_dska"));
                aux.setPrpr_precio(rs.getString("prpr_precio"));
                aux.setPrpr_tius_crea(rs.getString("prpr_tius_crea"));
                aux.setPrpr_tius_update(rs.getString("prpr_tius_update"));
                aux.setPrpr_estado(rs.getString("prpr_estado"));
                aux.setPrpr_fecha(rs.getString("prpr_fecha"));
                aux.setPrpr_sede(rs.getString("prpr_sede"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
