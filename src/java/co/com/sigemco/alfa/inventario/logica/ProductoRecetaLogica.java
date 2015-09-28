/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.ProductoRecetaDao;
import co.com.sigemco.alfa.inventario.dto.ProductoRecetaDto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class ProductoRecetaLogica {

    /**
     * Funcion la cual obtiene una lista de productos para una receta en
     * especifico
     *
     * @param rece_rece
     * @return
     */
    public ArrayList<ProductoRecetaDto> obtienePrdoctosReceta(String rece_rece) {
        ArrayList<ProductoRecetaDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            ProductoRecetaDao objDao = new ProductoRecetaDao();
            ResultSet rs = function.enviarSelect(objDao.buscaProductosReceta(rece_rece));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                ProductoRecetaDto aux = new ProductoRecetaDto();
                aux.setRepr_repr(rs.getString("repr_repr"));
                aux.setRepr_rece(rs.getString("repr_rece"));
                aux.setRepr_dska(rs.getString("repr_dska"));
                aux.setRepr_promedio(rs.getString("repr_promedio"));
                aux.setRepr_estado(rs.getString("repr_estado"));
                aux.setRepr_fec_ingreso(rs.getString("repr_fec_ingreso"));
                aux.setRepr_tius(rs.getString("repr_tius"));
                aux.setRepr_cantidad(rs.getString("repr_cantidad"));
                aux.setDska_cod(rs.getString("dska_cod"));
                aux.setDska_nombre(rs.getString("dska_nom_prod"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
