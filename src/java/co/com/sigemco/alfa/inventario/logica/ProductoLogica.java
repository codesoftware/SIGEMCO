/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.ProductoDao;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de manipular la logica para los productos del sistema
 *
 * @author Nicolas
 */
public class ProductoLogica {

    /**
     * Funcion encargada de realizar la busqueda de los productos por filtros
     * proporcionados por el usuario
     *
     * @param objDto
     * @return
     */
    public List<ProductoDto> buscaProductosXFiltro(ProductoDto objDto) {
        ProductoDao objDao = null;
        List<ProductoDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = this.poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.selectConFiltros());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<ProductoDto>();
                }
                ProductoDto aux = new ProductoDto();
                aux.setDska_dska(rs.getString("dska_dska"));
                aux.setDska_refe(rs.getString("dska_refe"));
                aux.setDska_cod(rs.getString("dska_cod"));
                aux.setDska_nom_prod(rs.getString("dska_nom_prod"));
                aux.setDska_desc(rs.getString("dska_desc"));
                aux.setDska_iva(rs.getString("dska_iva"));
                aux.setDska_porc_iva(rs.getString("dska_porc_iva"));
                aux.setDska_marca(rs.getString("dska_marca"));
                aux.setDska_estado(rs.getString("dska_estado"));
                aux.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                aux.setDska_cate(rs.getString("dska_cate"));
                String cant = this.buscaCanProdExistenXId(aux.getDska_dska());
                aux.setCantExis(cant);
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public ProductoDao poblarDao(ProductoDto objDto) {
        ProductoDao objDao = null;
        try {
            objDao = new ProductoDao();
            objDao.setDska_dska(objDto.getDska_dska());
            objDao.setDska_refe(objDto.getDska_refe());
            objDao.setDska_cod(objDto.getDska_cod());
            objDao.setDska_nom_prod(objDto.getDska_nom_prod());
            objDao.setDska_desc(objDto.getDska_desc());
            objDao.setDska_iva(objDto.getDska_iva());
            objDao.setDska_porc_iva(objDto.getDska_porc_iva());
            objDao.setDska_marca(objDto.getDska_marca());
            objDao.setDska_estado(objDto.getDska_estado());
            objDao.setDska_fec_ingreso(objDto.getDska_fec_ingreso());
            objDao.setDska_cate(objDto.getDska_cate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }

    /**
     * Funcion la cual busca en la base de datos cual es la cantidad de
     * productos existentes del producto por el cual se quiere buscar
     *
     * @param id en la base de datos es la llave dska_dska el cual es la llave
     * de cada producto
     * @return
     */
    public String buscaCanProdExistenXId(String dska_dska) {
        String rta = "";
        ProductoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.selectCantidadesExistentes());
            if (rs.next()) {
                String aux = rs.getString("kapr_cant_saldo");
                return aux;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rta;
    }

    /**
     * Funcion la cual obtiene el ultimo valor del promedio ponderado del
     * producto
     *
     * @param dska_dska
     * @return
     */
    public String obtieneValorPonderadoProducto(String dska_dska) {
        String valor = null;
        ProductoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.encontrarValorPromedioXProd());
            while (rs.next()) {
                valor = rs.getString("costo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }

    /**
     * Funcion encarda de realizar la logica para obtener el valor de las
     * existencias de un producto por sede
     *
     * @param producto Objeto con la informacion necesaria para encontrar el
     * producto
     * @param sede_sede Sede de la cual desea saber las existencias
     * @return
     */
    public String obtenerExistenciasPorSede(ProductoDto producto, String sede_sede) {
        String rta = null;
        try {
            int ingresos = 0;
            int egresos = 0;
            int total = 0;
            ingresos = obtieneIngresosProdXSede(producto, sede_sede);
            egresos = obtieneEgresosProdXSede(producto, sede_sede);
            total = ingresos- egresos;
            rta = ""+total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de obtener el numero de ingresos de determinado
     * producto por sede
     *
     * @param dska_dska
     * @return
     */
    public int obtieneIngresosProdXSede(ProductoDto objDto, String sede_sede) {
        int rta = 0;
        ProductoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.ingresoProdSede(sede_sede));
            if (rs.next()) {
                rta = rs.getInt("ingresos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = 0;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener el numero de salidas
     * o egresos de un producto en especifico por sede
     *
     * @param objDto
     * @param sede_sede
     * @return
     */
    public int obtieneEgresosProdXSede(ProductoDto objDto, String sede_sede) {
        int rta = 0;
        ProductoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.egresosProdSede(sede_sede));
            if (rs.next()) {
                rta = rs.getInt("egresos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = 0;
        }
        return rta;
    }

}
