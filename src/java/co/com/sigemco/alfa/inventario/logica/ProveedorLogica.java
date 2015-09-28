/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.ProveedorDao;
import co.com.sigemco.alfa.inventario.dto.ProvedDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author john
 */
public class ProveedorLogica {

    public String insertaProovedor(ProvedDto objDto) {
        ProveedorDao objDao = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDao = poblarDAO(objDto);
            if (funcion.enviarUpdate(objDao.insertaProveedor())) {
                return "Ok";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR INSERTANDO PROVEEDOR";
        }
    }
    
     public Map<String, String> obtieneProovedores() {
        Map<String, String> rta = null;
        try {
            int contador = 0;
            EnvioFunction function = new EnvioFunction();
            String sql = "";
            sql += " SELECT prov_prov, prov_nombre,  prov_nit,prov_razon_social,prov_representante,prov_telefono,prov_direccion,prov_celular";
            sql += "  FROM in_tprov             ";
            sql += " WHERE prov_estado = 'A'    ";
            sql += "  ORDER BY prov_nombre ";

            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (contador == 0) {
                    rta = new LinkedHashMap<String, String>();
                    contador++;
                }
                rta.put(rs.getString("prov_prov"), rs.getString("prov_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public ArrayList<ProvedDto> consultaProveedores(ProvedDto objDTO) {
        ResultSet rs = null;
        ProvedDto aux = null;
        ArrayList<ProvedDto> result = null;
        ProveedorDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDAO(objDTO);
            String filtros = traeFiltros(objDTO);
            rs = function.enviarSelect(objDao.consultaFilros(filtros));
            result = new ArrayList<>();
            while (rs.next()) {
                aux = new ProvedDto();
                aux.setProv_celular(rs.getString("prov_celular"));
                aux.setProv_direccion(rs.getString("prov_direccion"));
                aux.setProv_estado(rs.getString("prov_estado"));
                aux.setProv_nit(rs.getString("prov_nit"));
                aux.setProv_nombre(rs.getString("prov_nombre"));
                aux.setProv_razon_social(rs.getString("prov_razon_social"));
                aux.setProv_representante(rs.getString("prov_representante"));
                aux.setProv_telefono(rs.getString("prov_telefono"));
                aux.setProv_prov(rs.getString("prov_prov"));
                result.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
    
     public String actualizaProveedorEspecifico(ProvedDto objDTO) {

        ProveedorDao objDAO = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDAO = new ProveedorDao();
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.actualizaProovedor())) {
                return "PROVEEDOR ACTUALIZADO CORRECTAMENTE";
            } else {
                return "ERROR AL REALIZAR ACTUALIZACIÓN";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR ACTUALIZANDO PROOVEDOR";
        }
     }
     
     
      public ProvedDto traeProovedorEspecifico(ProvedDto objDTO) {
        ProvedDto result = null;
        ProveedorDao objDAO = null;
        ResultSet rs = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            objDAO = new ProveedorDao();
            result = new ProvedDto();
            objDAO = poblarDAO(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaEspecificaXId());
            while (rs.next()) {
                result.setProv_celular(rs.getString("prov_celular"));
                result.setProv_direccion(rs.getString("prov_direccion"));
                result.setProv_estado(rs.getString("prov_estado"));
                result.setProv_nit(rs.getString("prov_nit"));
                result.setProv_nombre(rs.getString("prov_nombre"));
                result.setProv_razon_social(rs.getString("prov_razon_social"));
                result.setProv_representante(rs.getString("prov_representante"));
                result.setProv_telefono(rs.getString("prov_telefono"));
                result.setProv_prov(rs.getString("prov_prov"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ProveedorDao poblarDAO(ProvedDto objDTO) {
        ProveedorDao objDao = new ProveedorDao();
        objDao.setProv_celular(objDTO.getProv_celular());
        objDao.setProv_direccion(objDTO.getProv_direccion());
        objDao.setProv_estado(objDTO.getProv_estado());
        objDao.setProv_nit(objDTO.getProv_nit());
        objDao.setProv_nombre(objDTO.getProv_nombre());
        objDao.setProv_razon_social(objDTO.getProv_razon_social());
        objDao.setProv_representante(objDTO.getProv_representante());
        objDao.setProv_telefono(objDTO.getProv_telefono());
        objDao.setProv_prov(objDTO.getProv_prov());
        return objDao;

    }

    public String traeFiltros(ProvedDto objDTO) {
        String respuesta = "1=1";
        try {
            if (!objDTO.getProv_estado().equalsIgnoreCase("-1")) {
                respuesta += " AND prov_estado='" + objDTO.getProv_estado() + "'";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

}
