/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.productos;

import co.com.hotel.dto.AddProdExistentes;
import co.com.hotel.dto.DetalleInventarioProd;
import co.com.hotel.dto.PrecioProducto;
import co.com.hotel.dto.Producto;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.validacion.ValidaCampos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SOFIA
 */
public class Inv_ProductoLogica {

    public String ingresaProductoExistente(AddProdExistentes obj, String usuario) {
        EnvioFunction function = new EnvioFunction();
        String rtaFun = "Error";
        try {
            function.adicionarNombre("IN_FINSERTA_PROD_KARDEX");
            function.adicionarNumeric(obj.getIdProd());
            function.adicionarNumeric(obj.getMovInv());
            function.adicionarNumeric(usuario);
            function.adicionarNumeric(obj.getNoProductos());
            function.adicionarNumeric(obj.getCosto());
            function.adicionarNumeric(obj.getSede());
            String rta = "";
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
            System.out.println("Error Inv_ProductoLogica.ingresaProductoExistente " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rtaFun;
    }

    public ArrayList<Producto> buscaProductosXFiltro(Producto obj) {
        ArrayList<Producto> r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            String sql = "select dska_refe referencia , dska_cod codigo , dska_nom_prod nombre, dska_desc descripcion, dska_marca marca, dska_dska id,"
                    + "dska_iva iva, dska_porc_iva porcIva\n";
            sql += ", to_char(prpr_precio,'LFM9,999,999.99') prpr_precio\n ";
            sql += "from in_tdska, in_tprpr\n";
            sql += "where upper(dska_cod) like upper('%" + obj.getCodigo().trim() + "%')\n";
            sql += "and upper(dska_refe) like upper('%" + obj.getReferencia().trim() + "%')\n";
            sql += "and upper(dska_nom_prod) like upper('%" + obj.getNombre().trim() + "%')\n";
            sql += "and upper(dska_desc) like upper('%" +obj.getDescripcion().trim() + "%')\n";
            //sql += "and upper(dska_iva) like upper('%" + obj.getIva().trim() + "%')\n";
            sql += "and prpr_dska = dska_dska\n";
            sql += "and prpr_estado = 'A'\n";
            if(obj.getPorcIva() != null && !obj.getPorcIva().equalsIgnoreCase("")){
                sql += "and dska_porc_iva =" + obj.getPorcIva().trim() + "\n";
            }
            sql += "and upper(dska_marca) like upper('%" + obj.getMarca().trim() + "%')\n";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<Producto>();
                    cont++;
                }
                Producto prod = new Producto();
                prod.setReferencia(rs.getString("referencia"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setMarca(rs.getString("marca"));
                prod.setId(rs.getString("id"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setIva(rs.getString("iva"));
                prod.setPorcIva(rs.getString("porcIva"));
                String aux = buscaCanProdExistenXId(prod.getId());
                prod.setCantidad(aux);
                prod.setCosto(rs.getString("prpr_precio"));
                r.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.buscaProductosXFiltro " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }
    
    public Producto buscaProductosXCodigo(String codigo,String id) {
        Producto r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            String sql = "select dska_refe referencia , dska_cod codigo , dska_nom_prod nombre, dska_desc descripcion, dska_marca marca, dska_dska id,"
                    + "dska_iva iva, dska_porc_iva porcIva\n";
            sql += ", to_char(prpr_precio,'LFM9,999,999.99') prpr_precio\n ";
            sql += "from in_tdska LEFT JOIN in_tprpr\n";
            sql += "on dska_dska = prpr_dska\n";
            sql += "where upper(dska_cod) like upper('%" + codigo.trim() + "%')\n";                    
            if(id !=null && !id.equalsIgnoreCase("")){
                sql += " and dska_dska = " + id + "\n";
            }
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new Producto();
                    cont++;
                }
                Producto prod = new Producto();
                prod.setReferencia(rs.getString("referencia"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setMarca(rs.getString("marca"));
                prod.setId(rs.getString("id"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setIva(rs.getString("iva"));
                prod.setPorcIva(rs.getString("porcIva"));
                String aux = buscaCanProdExistenXId(prod.getId());
                prod.setCantidad(aux);
                prod.setCosto(rs.getString("prpr_precio"));
                r = prod;
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.buscaProductosXFiltro " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    /**
     * Funcion la cual busca en la base de datos cual es la cantidad de
     * productos existentes del producto por el cual se quiere buscar
     *
     * @param id en la base de datos es la llave dska_dska el cual es la llave
     * de cada producto
     * @return
     */
    public String buscaCanProdExistenXId(String id) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        try {
            String sql = "select kapr_cant_saldo\n";
            sql += "from in_tkapr t1\n";
            sql += "where t1.kapr_dska = " + id + "\n";
            sql += "and t1.kapr_cons_pro = (select max(kapr_cons_pro) numMax from in_tkapr t2 where t2.kapr_dska = t1.kapr_dska)";
            rs = function.enviarSelect(sql);
            if (rs.next()) {
                String aux = rs.getString("kapr_cant_saldo");
                return aux;
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.buscaCanProdExistenXId " + e);
            return null;
        } finally {
            function.cerrarConexion();
            function = null;

        }
        return rta;
    }

    public ArrayList<DetalleInventarioProd> buscaDetalleInventario(String id, String fechaIni, String fechaFin) {
        ArrayList<DetalleInventarioProd> r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        String sql = "";
        try {
            sql += "select kapr_cons_pro consec,                           \n";
            sql += "    to_char(kapr_fecha, 'dd/mm/yyyy') fecha,           \n";
            sql += "    mvin_descr mvInv,                                  \n";
            sql += "    case when mvin_natu = 'I' then  kapr_cant_mvto     \n";
            sql += "        end cantMvtoEn,                                \n";
            sql += "    case when mvin_natu = 'I' then  kapr_cost_mvto_uni \n";
            sql += "        end costMvtoUniEn,                             \n";
            sql += "    case when mvin_natu = 'I' then  kapr_cost_mvto_tot \n";
            sql += "        end costMvtTotEn,                              \n";
            sql += "    case when mvin_natu = 'E' then  kapr_cant_mvto     \n";
            sql += "        end cantMvtoSa,                                \n";
            sql += "    case when mvin_natu = 'E' then  kapr_cost_mvto_uni \n";
            sql += "        end costMvtoUniSa,                             \n";
            sql += "    case when mvin_natu = 'E' then  kapr_cost_mvto_tot \n";
            sql += "        end costMvtTotSa,                              \n";
            sql += "    kapr_cant_saldo cantSaldo,                         \n";
            sql += "    kapr_cost_saldo_uni,                               \n";
            sql += "    kapr_cost_saldo_tot,                                \n";
            sql += "    sede_nombre  sede                              \n";
            sql += "from in_tkapr, in_tmvin, em_tsede                      \n";
            sql += "where kapr_dska = "+id+"                               \n";
            sql += "and kapr_mvin = mvin_mvin                              \n";
            sql += "and kapr_fecha between '"+fechaIni+"' and '"+fechaFin+"' \n";
            sql += "and kapr_sede = sede_sede    ";
            sql += "order by kapr_cons_pro asc                             \n";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<DetalleInventarioProd>();
                    cont++;
                }
                DetalleInventarioProd det = new DetalleInventarioProd();
                det.setConsecutivo(rs.getString("consec"));
                det.setFecha(rs.getString("fecha"));
                det.setMvInv(rs.getString("mvInv"));
                det.setCantEnt(rs.getString("cantMvtoEn"));
                det.setCostoUnidaddEnt(rs.getString("costMvtoUniEn"));
                det.setCostoTotalEnt(rs.getString("costMvtTotEn"));
                det.setCantSalidas(rs.getString("cantMvtoSa"));
                det.setCostoUnidadSal(rs.getString("costMvtoUniSa"));
                det.setCostoTotalSal(rs.getString("costMvtTotSa"));
                det.setCantTotal(rs.getString("cantSaldo"));
                det.setCostoUnidadTot(rs.getString("kapr_cost_saldo_uni"));
                det.setCostoTotalTot(rs.getString("kapr_cost_saldo_tot"));
                det.setSede(rs.getString("sede"));
                r.add(det);
            }
        } catch (Exception e) {

        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }
    
    public Producto buscaProductoXId(String id){
        Producto producto = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            String sql = "select dska_refe referencia , dska_cod codigo , dska_nom_prod nombre, "
                    + "dska_desc descripcion, dska_marca marca, dska_dska id,dska_porc_iva ivaPorc, dska_iva iva \n";
            sql += "from in_tdska\n";
            sql += "where dska_dska = " + id + "\n";
            rs = function.enviarSelect(sql);
            if(rs.next()){
                if(cont == 0){
                    producto = new Producto();
                    cont++;
                }
                producto.setReferencia(rs.getString("referencia"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setId(rs.getString("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPorcIva(rs.getString("ivaPorc"));
                producto.setIva(rs.getString("iva"));
                
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.buscaProductoXId " + e );
        }finally{
            function.cerrarConexion();
            function = null;
        }
        return producto;
    }    
    
    public String actualizaProducto(Producto obj){
        String rta = null;
        EnvioFunction function = new EnvioFunction();
        try {
            if(obj.getId() == null || obj.getId().trim().equalsIgnoreCase("")){
                return null;
            }else{
                String sql = this.armaUpdProducto(obj);
                if(sql != null){
                    function.enviarUpdate(sql);
                    rta = "Ok";
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.actualizaProducto " + e);
        }finally{
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }
    
    
    public String armaUpdProducto(Producto obj){
        String update = "Update in_tdska\n";
        update += "set dska_cod = dska_cod\n";
        int cont = 0;
        ValidaCampos valida = new ValidaCampos();
        if(valida.validaNulo(obj.getNombre())){
            update += ", dska_nom_prod = '" + obj.getNombre()  +"'\n";
            cont++;
        }
        if(valida.validaNulo(obj.getReferencia())){
            update += ", dska_refe = '" + obj.getReferencia() +"'\n";
            cont++;
        }if(valida.validaNulo(obj.getDescripcion())){
            update += ", dska_desc = '"+obj.getDescripcion()+"'\n";
            cont++;
        }if(valida.validaNulo(obj.getPorcIva())){
            update += ", dska_porc_iva = "+obj.getPorcIva()+ "\n";
            cont++;
        }if(valida.validaNulo(obj.getIva())){
            update += ", dska_iva = '" + obj.getIva() + "'\n";
            cont++;
        }if(valida.validaNulo(obj.getMarca())){
            update += ", dska_marca = '"+ obj.getMarca() + "'\n";
            cont++;
        }
        if(cont>0){
            update += "WHERE dska_dska = "+obj.getId();
        }else{
            return null;
        }
        return update;
    }
    
    public String adicionProductosFactura(String fact_fact, String dska_dska, String cant,String usuario){        
        String rta ="";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_REGISTRA_VENTA_PROD");
            function.adicionarNumeric(dska_dska);
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric(cant);
            function.adicionarNumeric(usuario);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    rta = function.getRespuesta();
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.adicionProductosFactura " + e);
        }finally{
            function.cerrarConexion();
        }
        return rta;
    }
    
    public List buscaHistorialPreciosProd(String dska_dska){
        List<PrecioProducto> r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            String sql = "SELECT prpr_prpr, prpr_dska, to_char(prpr_precio,'LFM9,999,999.00') prpr_precio , prpr_tius_crea, prpr_tius_update, prpr_fecha,\n";
            sql += "(CASE WHEN prpr_estado = 'A' THEN 'Activo' WHEN prpr_estado = 'I' THEN 'Inactivo' END ) prpr_estado\n";
            sql += "FROM in_tprpr\n";
            sql += "WHERE prpr_dska = "+ dska_dska;
            sql += " ORDER BY prpr_fecha desc,prpr_prpr desc";
            rs = function.enviarSelect(sql);
            while(rs.next()){
                if(cont==0){
                    r = new ArrayList<PrecioProducto>();
                    cont++;
                }
                PrecioProducto aux = new PrecioProducto();
                aux.setPrpr_prpr(rs.getString("prpr_prpr"));
                aux.setPrpr_dska(rs.getString("prpr_dska"));
                aux.setPrpr_fecha(rs.getString("prpr_fecha"));
                aux.setPrpr_estado(rs.getString("prpr_estado"));
                aux.setPrpr_precio(rs.getString("prpr_precio"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.buscaHistorialPreciosProd " + e);
        }finally{
            function.cerrarConexion();
        }
        return r;        
    }
    
    public String  parametrizaPrecioPr(String dska_dska, String tius_tius, String precio,String sede){
        String rta = "Ok";
        EnvioFunction function = new EnvioFunction();
        try {
            String sql = "INSERT INTO in_tprpr(";
            sql += "prpr_dska, prpr_precio, prpr_tius_crea, prpr_tius_update, prpr_sede )";
            sql += "values(";
            sql += dska_dska + "," + precio + "," + tius_tius + "," + tius_tius + "," + sede;
            sql += ")";
            function.enviarUpdate(sql);            
        } catch (Exception e) {
            System.out.println("Error Inv_ProductoLogica.parametrizaPrecioPr " + e);
        }finally{
            function.cerrarConexion();
        }
        return rta;
    }
    
}
