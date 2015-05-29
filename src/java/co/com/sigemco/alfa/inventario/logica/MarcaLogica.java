/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.MarcaDao;
import co.com.sigemco.alfa.inventario.dto.MarcaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class MarcaLogica {

    public String insertaMarca(MarcaDto objDto) {
        MarcaDao objDao = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDao = poblarDAO(objDto);
            if (funcion.enviarUpdate(objDao.insertaMarca())) {
                return "MARCA INSERTADA CORRECTAMENTE";
            } else {
                return "NO SE PUDO INSERTAR LA MARCA";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR INSERTANDO MARCA";
        }

    }

    public Map<String, String> obtieneMarcas() {
        Map<String, String> rta = null;
        try {
            int contador = 0;
            EnvioFunction function = new EnvioFunction();
            String sql = "";
            sql += "SELECT marca_marca, marca_nombre ";
            sql += "  FROM in_tmarca             ";
            sql += " WHERE marca_estado = 'A'    ";
            sql += "  ORDER BY marca_nombre ";

            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (contador == 0) {
                    rta = new LinkedHashMap<String, String>();
                    contador++;
                }
                rta.put(rs.getString("marca_marca"), rs.getString("marca_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public ArrayList<MarcaDto> consultaMarcas(MarcaDto objDTO) {
        ResultSet rs = null;
        MarcaDto aux = null;
        ArrayList<MarcaDto> result = null;
        MarcaDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDAO(objDTO);
            String filtros = traeFiltros(objDTO);
            rs = function.enviarSelect(objDao.consultaFilros(filtros));
            result = new ArrayList<>();
            while (rs.next()) {
                aux = new MarcaDto();
                aux.setMarca_descr(rs.getString("marca_descr"));
                aux.setMarca_estado(rs.getString("marca_estado"));
                aux.setMarca_marca(rs.getString("marca_marca"));
                aux.setMarca_nombre(rs.getString("marca_nombre"));
                result.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
    
     public String actualizaMarcaEspecifica(MarcaDto objDTO) {

        MarcaDao objDAO = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDAO = new MarcaDao();
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.actualizaMarca())) {
                return "REFERENCIA ACTUALIZADA CORRECTAMENTE";
            } else {
                return "ERROR AL REALIZAR ACTUALIZACIÓN";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR ACTUALIZANDO REFERENCIA";
        }
     }
     
     
      public MarcaDto traeMarcaEspecifica(MarcaDto objDTO) {
        MarcaDto result = null;
        MarcaDao objDAO = null;
        ResultSet rs = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDAO = new MarcaDao();
            result = new MarcaDto();
            objDAO = poblarDAO(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaEspecificaXId());
            while (rs.next()) {
                result.setMarca_descr(rs.getString("marca_descr"));
                result.setMarca_estado(rs.getString("marca_estado"));
                result.setMarca_marca(rs.getString("marca_marca"));
                result.setMarca_nombre(rs.getString("marca_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public MarcaDao poblarDAO(MarcaDto objDTO) {
        MarcaDao objDao = new MarcaDao();
        objDao.setMarca_descr(objDTO.getMarca_descr());
        objDao.setMarca_marca(objDTO.getMarca_marca());
        objDao.setMarca_estado(objDTO.getMarca_estado());
        objDao.setMarca_nombre(objDTO.getMarca_nombre());
        return objDao;

    }

    public String traeFiltros(MarcaDto objDTO) {
        String respuesta = "1=1";
        try {
            if (!objDTO.getMarca_estado().equalsIgnoreCase("-1")) {
                respuesta += " AND marca_estado='" + objDTO.getMarca_estado() + "'";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

}
