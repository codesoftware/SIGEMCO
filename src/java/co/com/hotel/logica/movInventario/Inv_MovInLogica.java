/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.movInventario;

import co.com.hotel.dto.MovInventario;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.validacion.ValidaCampos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SOFIA
 */
public class Inv_MovInLogica {

    /**
     * Function la cual se encarga de obtener los identificadores y
     * descripciones de los movimientos de inventario
     *
     * @return Map con los ids de los movimientos y la descripcion
     */
    public Map<String, String> obtineMovimientosInv() {
        Map<String, String> inv = null;
        EnvioFunction function = new EnvioFunction();
        int cont = 0;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "SELECT mvin_mvin id, mvin_descr descripcion\n";
            sql += "FROM in_tmvin";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    inv = new HashMap<String, String>();
                    cont++;
                }
                inv.put(rs.getString("id"), rs.getString("descripcion"));
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.obtineMovimientosInv " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return inv;
    }

    /**
     * Funcion encargada de insertar un movimiento de inventario a la base de
     * datos
     *
     * @param objTo Objeto el cual contiene los datos del movimiento de
     * inventario
     * @return Ok si la operacion es exitosa
     */
    public String insertaMovInv(MovInventario objTo) {
        String rta = "Error ";
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        try {
            if (objTo.getMvin_venta().equalsIgnoreCase("S")) {
                String valida = actualizarMovInventarioVenta(objTo);
                if (!valida.equalsIgnoreCase("Ok")) {
                    return valida;
                }
            }
            sql += "insert into IN_TMVIN (MVIN_DESCR, MVIN_NATU, MVIN_USIM, MVIN_VENTA, MVIN_INICIAL , MVIN_REVFACT)\n";
            sql += "values('" + objTo.getMvin_descr() + "', '" + objTo.getMvin_natu() + "','" + objTo.getMvin_usim() + "','" + objTo.getMvin_venta() + "','" + objTo.getMvin_inicial() + "','" + objTo.getMvin_revfact() + "')\n";
            boolean valida = function.enviarUpdate(sql);
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error Inv_MovInLogica.insertaMovInv al ejecutar el insert en la base de datos ";
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.insertaMovInv " + e);
            rta = "Error Inv_MovInLogica.insertaMovInv " + e;
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }

    /**
     * Funcion encargada de actualizar la tabla in_tmvin columna mvin_venta como
     * 'N' ya que solo debe haber una sola fila con el campo en 'S'
     *
     * @return Ok si la operacion se ejecuto correctamente
     */
    public String actualizarMovInventarioVenta(MovInventario objTo) {
        String rta = "Error ";
        EnvioFunction function = new EnvioFunction();
        String update = "";
        int contador = 0;
        try {
            update += "UPDATE in_tmvin\n";
            if (objTo.getMvin_venta().equalsIgnoreCase("S")) {
                if (contador == 0) {
                    update += "SET mvin_venta = 'N'\n";
                    contador++;
                } else {
                    update += ", mvin_venta = 'N'\n";
                }
            }
            if (objTo.getMvin_inicial().equalsIgnoreCase("S")) {
                if (contador == 0) {
                    update += "SET mvin_inicial = 'N'\n";
                    contador++;
                } else {
                    update += ", mvin_inicial = 'N'\n";
                }
            }
            if (objTo.getMvin_revfact().equalsIgnoreCase("S")) {
                if (contador == 0) {
                    update += "SET mvin_revfact = 'N'\n";
                    contador++;
                } else {
                    update += ", mvin_revfact = 'N'\n";
                }
            }
            boolean valida = true;
            if (contador > 0) {
                valida = function.enviarUpdate(update);
            }
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error Inv_MovInLogica.actualizarMovInventarioVenta al ejecutar el update en la base de datos";
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.actualizarMovInventarioVenta " + e);
            rta = "Error Inv_MovInLogica.actualizarMovInventarioVenta " + e;
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }

    /**
     * Funcion encargada de consultar la tabla in_tmvin y filtrar esta consulta
     * por medio de los mismos campos
     *
     * @param filtros Objeto con los datos de in_tmvin con los cuales se
     * filtrara la consulta
     * @return retorna una lista con los resultados de la consulta
     */
    public List<MovInventario> consultaGeneralMovimientosInventarioXFiltro(MovInventario filtros) {
        List<MovInventario> lista = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        try {
            sql += "SELECT MVIN_MVIN   ,\n";
            sql += "       MVIN_DESCR  ,\n";
            sql += "       CASE WHEN MVIN_NATU = 'E' THEN 'EGRESO' ELSE 'INGRESO' END MVIN_NATU  ,\n";
            sql += "       CASE WHEN MVIN_USIM = 'C' THEN 'CLIENTE' WHEN MVIN_USIM = 'P' THEN 'PROVEEDOR' ELSE 'NINGUNO' END MVIN_USIM ,\n";
            sql += "       CASE WHEN MVIN_VENTA = 'S' THEN 'SI' ELSE 'NO' END MVIN_VENTA,  \n";
            sql += "       CASE WHEN MVIN_INICIAL = 'S' THEN 'SI' ELSE 'NO' END MVIN_INICIAL,  \n";
            sql += "       CASE WHEN MVIN_REVFACT = 'S' THEN 'SI' ELSE 'NO' END MVIN_REVFACT  \n";
            sql += "FROM IN_TMVIN       \n";
            sql += "WHERE 1 = 1 \n";
            if (filtros.getMvin_mvin() != null) {
                sql += "AND MVIN_MVIN = " + filtros.getMvin_mvin().trim() + "\n";
            } else {
                sql += this.armarWhereConsultafiltros(filtros);
            }
            sql += " order by MVIN_NATU ";
            System.out.println("Sql: " + sql);
            ResultSet rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (lista == null) {
                    lista = new ArrayList<MovInventario>();
                }
                MovInventario aux = new MovInventario();
                aux.setMvin_mvin(rs.getString("MVIN_MVIN"));
                aux.setMvin_descr(rs.getString("MVIN_DESCR"));
                aux.setMvin_natu(rs.getString("MVIN_NATU"));
                aux.setMvin_usim(rs.getString("MVIN_USIM"));
                aux.setMvin_venta(rs.getString("MVIN_VENTA"));
                aux.setMvin_inicial(rs.getString("MVIN_INICIAL"));
                aux.setMvin_revfact(rs.getString("MVIN_REVFACT"));
                lista.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.consultaGeneralMovimientosInventarioXFiltro " + e);
            lista = null;
        } finally {
            function.cerrarConexion();
        }
        return lista;
    }

    /**
     * Funcion la cual se encarga de armar el where para la consulta por filtros
     * de movimientos de inventario
     *
     * @param filtros
     * @return String con el where requerido
     */
    public String armarWhereConsultafiltros(MovInventario filtros) {
        String where = "";
        ValidaCampos valida = null;
        try {
            valida = new ValidaCampos();
            if (valida.validaNulo(filtros.getMvin_descr())) {
                where += "AND UPPER(MVIN_DESCR) LIKE UPPER('%" + filtros.getMvin_descr() + "%')\n";
            }
            if (!filtros.getMvin_natu().trim().equalsIgnoreCase("-1")) {
                where += "AND UPPER(MVIN_NATU) LIKE UPPER('%" + filtros.getMvin_natu().trim() + "%')\n";
            }
            if (!filtros.getMvin_usim().trim().equalsIgnoreCase("-1")) {
                where += "AND UPPER(MVIN_USIM) LIKE UPPER('%" + filtros.getMvin_usim().trim() + "%')\n";
            }
            if (!filtros.getMvin_venta().trim().equalsIgnoreCase("-1")) {
                where += "AND UPPER(MVIN_VENTA) LIKE UPPER('%" + filtros.getMvin_venta().trim() + "%')\n";
            }
            if (!filtros.getMvin_revfact().trim().equalsIgnoreCase("-1")) {
                where += "AND UPPER(MVIN_REVFACT) LIKE UPPER('%" + filtros.getMvin_revfact().trim() + "%')\n";
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.armarWhereConsultafiltros " + e);
        }
        return where;
    }

    /**
     * Funcion el cual consulta un solo movimiento de inventario por medio de
     * filtros
     *
     * @param filtro
     * @return
     */
    public MovInventario consultaUpdMovimientoInventario(MovInventario filtro) {
        MovInventario rta = null;
        try {
            List<MovInventario> aux = this.consultaGeneralMovimientosInventarioXFiltro(filtro);
            if (aux != null) {
                rta = aux.get(0);
                if (rta.getMvin_natu().trim().equalsIgnoreCase("EGRESO")) {
                    rta.setMvin_natu("E");
                } else {
                    rta.setMvin_natu("I");
                }

                if (rta.getMvin_usim().trim().equalsIgnoreCase("CLIENTE")) {
                    rta.setMvin_usim("C");
                } else if (rta.getMvin_usim().trim().equalsIgnoreCase("PROVEEDOR")) {
                    rta.setMvin_usim("P");
                } else {
                    rta.setMvin_usim("N");
                }

                if (rta.getMvin_venta().trim().equalsIgnoreCase("SI")) {
                    rta.setMvin_venta("S");
                } else {
                    rta.setMvin_venta("N");
                }

                if (rta.getMvin_inicial().trim().equalsIgnoreCase("SI")) {
                    rta.setMvin_inicial("S");
                } else {
                    rta.setMvin_inicial("N");
                }

                if (rta.getMvin_revfact().trim().equalsIgnoreCase("SI")) {
                    rta.setMvin_revfact("S");
                } else {
                    rta.setMvin_revfact("N");
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.consultaUpdMovimientoInventario " + e);
        }
        return rta;
    }

    /**
     * Funcion encargada de ejecutar el query de actualizacin de Movimiento de
     * inventarios
     *
     * @param objTo
     * @return
     */
    public String actualizarMovimientoInv(MovInventario objTo) {
        String sql = "";
        String rta = "Ok";
        EnvioFunction function = new EnvioFunction();
        try {
            String valida = this.actualizarMovInventarioVenta(objTo);
            if (valida.trim().equalsIgnoreCase("Ok")) {
                sql += "UPDATE in_tmvin  \n";
                sql += "SET MVIN_DESCR = '" + objTo.getMvin_descr() + "'    \n";
                sql += ",MVIN_NATU      = '" + objTo.getMvin_natu() + "'    \n";
                sql += ",MVIN_USIM      = '" + objTo.getMvin_usim() + "'    \n";
                sql += ",MVIN_VENTA     = '" + objTo.getMvin_venta() + "'   \n";
                sql += ",MVIN_INICIAL   = '" + objTo.getMvin_inicial() + "' \n";
                sql += ",MVIN_REVFACT   = '" + objTo.getMvin_revfact() + "' \n";
                sql += "WHERE mvin_mvin =" + objTo.getMvin_mvin() + " \n";
                boolean validaUpd = function.enviarUpdate(sql);
                if (!validaUpd) {
                    rta = "Error al actualizar en la base de datos";
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_MovInLogica.actualizarMovimientoInv " + e);
            rta = "Error Inv_MovInLogica.actualizarMovimientoInv " + e;
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    /**
     * Obtiene la naturaleza del movimiento de inventario por el id
     *
     * @param mvin_mvin
     * @return
     */
    public String consultaNaturalezaMvinXId(String mvin_mvin) {
        String sql = "";
        String naturaleza = null;
        ResultSet rs = null;
        try (EnvioFunction function = new EnvioFunction()) {
            sql = "select case\n";
            sql += "when mvin_natu = 'I' then 'Ingreso'\n";
            sql += "else 'Egreso'\n";
            sql += "end naturaleza\n";
            sql += "from in_tmvin\n";
            sql += "where mvin_mvin =" + mvin_mvin;
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                naturaleza = rs.getString("naturaleza");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturaleza;
    }

}
