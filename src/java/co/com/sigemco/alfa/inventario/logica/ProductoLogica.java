/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.dto.AddProdExistentes;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.utilidades.ManejoLocateCO;
import co.com.sigemco.alfa.inventario.dao.ProductoDao;
import co.com.sigemco.alfa.inventario.dto.DetalleConteoDto;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import com.google.gson.Gson;
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
                aux.setReferenciaNombre(rs.getString("refe_desc"));
                aux.setDska_prov(rs.getString("dska_prov"));
                String cant = this.buscaCanProdExistenXId(aux.getDska_dska());
                aux.setCantExis(cant);
                String promPon = this.obtieneValorPonderadoProducto(objDto.getDska_dska());
                aux.setPromPonderado(promPon);
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
            objDao.setCantidad(objDto.getCantidad());
            objDao.setDska_prov(objDto.getDska_prov());
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
            total = ingresos - egresos;
            rta = "" + total;
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

    /**
     * Funcion encargada de realizar la busqueda de un producto por medio de su
     * codigo
     *
     * @param codigo
     * @return
     */
    public ProductoDto buscaProductoXCodigo(String codigo) {
        ProductoDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            ProductoDao objDao = new ProductoDao();
            objDao.setDska_cod(codigo);
            ResultSet rs = function.enviarSelect(objDao.buscaProductoXCodigo());
            if (rs.next()) {
                if (objDto == null) {
                    objDto = new ProductoDto();
                }
                objDto.setDska_dska(rs.getString("dska_dska"));
                objDto.setDska_refe(rs.getString("dska_refe"));
                objDto.setDska_cod(rs.getString("dska_cod"));
                objDto.setDska_nom_prod(rs.getString("dska_nom_prod"));
                objDto.setDska_desc(rs.getString("dska_desc"));
                objDto.setDska_iva(rs.getString("dska_iva"));
                objDto.setDska_porc_iva(rs.getString("dska_porc_iva"));
                objDto.setDska_marca(rs.getString("marca_nombre"));
                objDto.setDska_estado(rs.getString("dska_estado"));
                objDto.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                objDto.setDska_cate(rs.getString("dska_cate"));
                objDto.setReferenciaNombre(rs.getString("refe_desc"));
                String cant = this.buscaCanProdExistenXId(objDto.getDska_dska());
                objDto.setCantExis(cant);
                String promPon = this.obtieneValorPonderadoProductoMascara(objDto.getDska_dska());
                objDto.setPromPonderado(promPon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDto;
    }

    /**
     * Funcion encargada de obtener el promedio ponderado pero con mascara de
     * moneda
     *
     * @param dska_dska
     * @return
     */
    public String obtieneValorPonderadoProductoMascara(String dska_dska) {
        ManejoLocateCO loc = new ManejoLocateCO();
        String valor = null;
        ProductoDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.encontrarValorPromedioXProdMascaraMon());
            while (rs.next()) {
                valor = loc.doubleTOMoney(rs.getString("costo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }

    /**
     * Funcion encargada de realizar la logica para adicionar un movimiento de
     * inventario a el inventario
     *
     * @return
     */
    public String adicionaProdInventario(ProductoDto producto, AddProdExistentes movimiento, String tius_tius) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("IN_ADICIONA_PROD_EXIS");
            function.adicionarNumeric(producto.getDska_dska());
            function.adicionarNumeric(movimiento.getNoProductos());
            function.adicionarNumeric(movimiento.getCosto());
            function.adicionarNumeric(movimiento.getSede());
            function.adicionarNumeric(tius_tius);
            function.adicionarNumeric(producto.getTransMvcon());
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return rtaPg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public String cambioSedeProd(String dska_dska, String sedeOrigen, String sedeDestino, String tius_tius, String cantidad) {
        String rta = "Error";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("IN_CAMBIOSEDE_PROD");
            function.adicionarNumeric(sedeDestino);
            function.adicionarNumeric(sedeOrigen);
            function.adicionarNumeric(cantidad);
            function.adicionarNumeric(dska_dska);
            function.adicionarNumeric(tius_tius);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return rtaPg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta += " " + e;
        }
        return rta;
    }

    /**
     * Funcion la cual esta encargada de realizar la logica para corregir el
     * ingreso de productos al sistema
     *
     * @param dska_dska String Identificador unico de la tabla de productos
     * @param sede_sede String Identificador unico de la tabla de Sedes
     * @param cantidad String cantidad de productos a corregir
     * @param tius_tius String Identificador del usuario autenticado para
     * realizar el movimiento
     * @return
     */
    public String corrigeIngrersoProd(String dska_dska, String sede_sede, String cantidad, String tius_tius) {
        String rta = "Error";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("IN_CORRIGEING_PROD");
            function.adicionarNumeric(sede_sede);
            function.adicionarNumeric(dska_dska);
            function.adicionarNumeric(cantidad);
            function.adicionarNumeric(tius_tius);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return rtaPg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public ProductoDto obtieneCalculosProductoFactura(ProductoDto objDto) {
        ProductoDao objDao = null;
        ProductoDto rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            System.out.println("Este es el sql \n" + objDao.calculosFactura());
            ResultSet rs = function.enviarSelect(objDao.calculosFactura());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ProductoDto();
                }
                rta.setDska_dska(rs.getString("dska_dska"));
                rta.setCantidad(rs.getString("cantidad"));
                rta.setDska_cod(rs.getString("codigo"));
                rta.setDska_nom_prod(rs.getString("nombre"));
                rta.setValorProdUni(rs.getString("precio"));
                rta.setValorIvaUni(rs.getString("ivauni"));
                rta.setValorProdTotal(rs.getString("vlrtotal"));
                rta.setValorTotalVenta(rs.getString("totalpagar"));
                rta.setValorIvaTotal(rs.getString("ivaTotal"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargade de realizar la logica para buscar los productos
     * similares
     *
     * @param objDto
     * @return
     */
    public List buscaProductosSimilares(ProductoDto objDto) {
        List<ProductoDto> lista = null;
        try (EnvioFunction function = new EnvioFunction()) {
            ProductoDao objDao = new ProductoDao();
            objDao.setDska_cate(objDto.getDska_cate());
            objDao.setDska_marca(objDto.getDska_marca());
            objDao.setDska_refe(objDto.getDska_refe());
            ResultSet rs = function.enviarSelect(objDao.buscaProductosSimilares());
            while (rs.next()) {
                if (lista == null) {
                    lista = new ArrayList<ProductoDto>();
                }
                ProductoDto aux = new ProductoDto();
                aux.setDska_cod(rs.getString("dska_cod"));
                aux.setDska_desc(rs.getString("dska_desc"));
                aux.setDska_marca(rs.getString("marca_nombre"));
                aux.setDska_refe(rs.getString("refe_desc"));
                aux.setDska_cate(rs.getString("cate_desc"));
                lista.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Funcion encargada de realizar la busqueda de un producto dentro de un
     * conteo por medio de su codigo
     *
     * @param dska_cod
     * @param copr_copr
     * @return
     */
    public DetalleConteoDto buscaProductoConteoXCodigo(String dska_cod, String copr_copr) {
        DetalleConteoDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            ProductoDao objDao = new ProductoDao();
            ResultSet rs = function.enviarSelect(objDao.buscaProductoConteo(copr_copr, dska_cod));
            if (rs.next()) {
                if (objDto == null) {
                    objDto = new DetalleConteoDto();
                }
                objDto.setNombreProducto(rs.getString("dska_nom_prod"));
                objDto.setEcop_valor(rs.getString("ecop_valor"));
                objDto.setCodigoProducto(rs.getString("dska_cod"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDto;
    }

}
