/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.CategoriaDao;
import co.com.sigemco.alfa.inventario.dto.CategoriaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar la logica que implique la tabla in_tcate
 *
 * @author nicolas
 * @version 1.0
 */
public class CategoriaLogica {
    /**
     * Funcion la cual retorna una Array de objetos Categoria
     * @return List de Categorias
     */
    public List<CategoriaDto> obtieneCategoriasActivas() {
        List<CategoriaDto> rta = null;
        EnvioFunction function = new EnvioFunction();
        CategoriaDao objDao = null;
        try {
            objDao = new CategoriaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralActivos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<CategoriaDto>();
                }
                CategoriaDto aux = new CategoriaDto();
                aux.setCate_cate(rs.getString("cate_cate"));
                aux.setCate_desc(rs.getString("cate_desc"));
                aux.setCate_estado(rs.getString("cate_estado"));
                aux.setCate_runic(rs.getString("cate_runic"));
                aux.setCate_feven(rs.getString("cate_feven"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }
    
    /**
     * Funcion la cual retorna una categoria en especifico la cual obtine con el id de la categoria
     * @param cate_cate String llave primaria de la tabla in_tcate
     * @return Objeto con la categoria
     */
    public CategoriaDto obtieneCategoriasXId(String cate_cate) {
        CategoriaDto aux = null;
        EnvioFunction function = new EnvioFunction();
        CategoriaDao objDao = null;
        try {
            objDao = new CategoriaDao();
            objDao.setCate_cate(cate_cate);
            ResultSet rs = function.enviarSelect(objDao.consultaEspecificaXId());
            while (rs.next()) {                
                aux = new CategoriaDto();
                aux.setCate_cate(rs.getString("cate_cate"));
                aux.setCate_desc(rs.getString("cate_desc"));
                aux.setCate_estado(rs.getString("cate_estado"));
                aux.setCate_runic(rs.getString("cate_runic"));
                aux.setCate_feven(rs.getString("cate_feven"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            function.cerrarConexion();
        }
        return aux;
    }
    
}
