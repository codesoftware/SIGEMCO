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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class MarcaLogica {
    
    public String insertaMarca(MarcaDto objDto){
        MarcaDao objDao = null;
        try (EnvioFunction funcion = new EnvioFunction()){
            objDao = poblarDAO(objDto);
            if(funcion.enviarUpdate(objDao.insertaMarca())){
                return "MARCA INSERTADA CORRECTAMENTE";
            }else{
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

            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (contador == 0) {
                    rta = new HashMap<String, String>();
                    contador++;
                }
                rta.put(rs.getString("marca_marca"), rs.getString("marca_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
     public MarcaDao poblarDAO(MarcaDto objDTO) {
         MarcaDao objDao = new MarcaDao();
         objDao.setMarca_descr(objDTO.getMarca_descr());
         objDao.setMarca_marca(objDTO.getMarca_marca());
         objDao.setMarca_estado(objDTO.getMarca_estado());
         objDao.setMarca_nombre(objDTO.getMarca_nombre());
         return objDao;
         
     }

}
