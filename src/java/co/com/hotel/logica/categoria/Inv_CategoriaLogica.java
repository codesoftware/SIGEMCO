/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.categoria;

import co.com.hotel.dto.inventarios.Categoria;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de manipular la logica con la base de datos de las categorias
 * de los productos
 *
 * @author nicolas
 * @version 1.0
 */
public class Inv_CategoriaLogica {

    /**
     * Funcion encargada de recuperar los id's y las descripciones de las
     * categorias parametrizadas en el sistema
     *
     * @return Mapa con los datos requeridos
     */
    public Map<String,String> obtieneCategorias() {
        Map<String,String> rta = null;
        int contador = 0;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        try {
            sql += "SELECT cate_cate, cate_desc\n";
            sql += "  FROM in_tcate            \n";
            sql += " WHERE cate_estado = 'A'   \n";
            
            ResultSet rs = function.enviarSelect(sql);
            while(rs.next()){
                if(contador == 0){
                    rta = new HashMap<String, String>();
                    contador++;
                }
                rta.put(rs.getString("cate_cate"), rs.getString("cate_desc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

}
