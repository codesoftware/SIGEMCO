/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.admin.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.admin.dao.ParametrosAplicacionDao;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase con la cual creo la logica para la administracion de la aplicacion
 *
 * @author Nicolas
 */
public class AdministracionLogica {

    /**
     * Funcion con la cual obtengo la parametrizacion del inicio de la pantalla
     *
     * @return
     */
    public String obtieneParametrizacionInicio() {
        String rta = "";
        ParametrosAplicacionDao objDao = new ParametrosAplicacionDao();
        try (EnvioFunction function = new EnvioFunction()) {
            ResultSet rs = function.enviarSelect(objDao.buscaParametrizacionInicio());
            if (rs.next()) {
                rta = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rta;
    }

    /**
     * Funcion encargada de buscar los productos que estan apunto de acabar sus
     * existencias en el sistema
     *
     * @return
     */
    public List<ProductoDto> buscaProdProximosTerminar() {
        List<ProductoDto> lista = null;
        try (EnvioFunction function = new EnvioFunction()){
            ParametrosAplicacionDao objDao = new ParametrosAplicacionDao();
            ResultSet rs = function.enviarSelect(objDao.buscaProductosAlerta());
            while(rs.next()){
                if(lista == null){
                    lista = new ArrayList<>();
                }
                ProductoDto aux = new ProductoDto();
                aux.setDska_cod(rs.getString("dska_cod"));
                aux.setDska_nom_prod(rs.getString("dska_nom_prod"));
                aux.setCantidad(rs.getString("cepr_existencia"));
                lista.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
