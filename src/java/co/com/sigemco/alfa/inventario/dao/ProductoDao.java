/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

/**
 *
 * @author Nicolas
 */
public class ProductoDao {

    private String dska_dska;
    private String dska_refe;
    private String dska_cod;
    private String dska_nom_prod;
    private String dska_desc;
    private String dska_iva;
    private String dska_porc_iva;
    private String dska_marca;
    private String dska_estado;
    private String dska_fec_ingreso;
    private String dska_cate;
    private String cantExis;
    private String cantidad;

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String getDska_refe() {
        return dska_refe;
    }

    public void setDska_refe(String dska_refe) {
        this.dska_refe = dska_refe;
    }

    public String getDska_cod() {
        return dska_cod;
    }

    public void setDska_cod(String dska_cod) {
        this.dska_cod = dska_cod;
    }

    public String getDska_nom_prod() {
        return dska_nom_prod;
    }

    public void setDska_nom_prod(String dska_nom_prod) {
        this.dska_nom_prod = dska_nom_prod;
    }

    public String getDska_desc() {
        return dska_desc;
    }

    public void setDska_desc(String dska_desc) {
        this.dska_desc = dska_desc;
    }

    public String getDska_iva() {
        return dska_iva;
    }

    public void setDska_iva(String dska_iva) {
        this.dska_iva = dska_iva;
    }

    public String getDska_porc_iva() {
        return dska_porc_iva;
    }

    public void setDska_porc_iva(String dska_porc_iva) {
        this.dska_porc_iva = dska_porc_iva;
    }

    public String getDska_marca() {
        return dska_marca;
    }

    public void setDska_marca(String dska_marca) {
        this.dska_marca = dska_marca;
    }

    public String getDska_estado() {
        return dska_estado;
    }

    public void setDska_estado(String dska_estado) {
        this.dska_estado = dska_estado;
    }

    public String getDska_fec_ingreso() {
        return dska_fec_ingreso;
    }

    public void setDska_fec_ingreso(String dska_fec_ingreso) {
        this.dska_fec_ingreso = dska_fec_ingreso;
    }

    public String getDska_cate() {
        return dska_cate;
    }

    public void setDska_cate(String dska_cate) {
        this.dska_cate = dska_cate;
    }

    public String getCantExis() {
        return cantExis;
    }

    public void setCantExis(String cantExis) {
        this.cantExis = cantExis;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Funcion encargada de retornar el select con los filtros solicitados por
     * el usuario
     *
     * @return String consulta solicitada
     */
    public String selectConFiltros() {
        String select = "";
        select += "SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, \n";
        select += "       dska_porc_iva, dska_estado, dska_fec_ingreso, refe_desc, \n";
        select += "       coalesce((select marca_nombre from in_tmarca where marca_marca = dska_marca), 'SIN MARCA') dska_marca,  \n";
        select += "        cate_desc as dska_cate ";
        select += "  FROM in_tdska, in_trefe, in_tcate                                                  \n";
        select += " WHERE refe_refe = dska_refe \n";
        select += "   AND cate_cate = dska_cate \n";
        select += this.armaWhereXFiltros();
        //System.out.println("Este es el sql: \n" + select);
        return select;
    }

    public String armaWhereXFiltros() {
        String where = "";
        if (!"-1".equalsIgnoreCase(dska_refe)) {
            where += " AND dska_refe = " + this.getDska_refe();
        }
        if (!"-1".equalsIgnoreCase(dska_cate)) {
            where += " AND dska_cate = " + this.getDska_cate();
        }
        if (!"-1".equalsIgnoreCase(dska_marca)) {
            where += " AND dska_marca = " + this.getDska_marca();
        }
        if (!"".equalsIgnoreCase(dska_cod)) {
            where += " AND dska_cod like '%" + this.getDska_cod() + "%'";
        }
        return where;
    }

    /**
     * Funcion encargada de realizar el query para saber cuantas cantidades
     * totales de este producto hay en toda la tienda
     *
     * @return
     */
    public String selectCantidadesExistentes() {
        String sql = "";
        sql = "select kapr_cant_saldo\n";
        sql += "from in_tkapr t1\n";
        sql += "where t1.kapr_dska = " + this.dska_dska + "\n";
        sql += "and t1.kapr_cons_pro = (select max(kapr_cons_pro) numMax from in_tkapr t2 where t2.kapr_dska = t1.kapr_dska)";
        return sql;
    }

    /**
     * Funcion encargada de realizar el query para obtener el valor promedio de
     * cada producto
     *
     * @return String Query
     */
    public String encontrarValorPromedioXProd() {
        String select = "SELECT kapr_cost_saldo_uni costo "
                .concat("  FROM in_tkapr k1 ")
                .concat(" WHERE k1.kapr_dska = ").concat(this.getDska_dska())
                .concat("   AND k1.kapr_kapr = (SELECT max(k2.kapr_kapr) FROM in_tkapr k2 WHERE k2.kapr_dska = k1.kapr_dska ) ");
        return select;
    }

    /**
     * Funcion encargada realizar el query para saber todos los ingresos del
     * producto realizados por sede
     *
     * @return
     */
    public String ingresoProdSede(String sede) {
        String sql = "";
        sql += "SELECT sum(kapr_cant_mvto) ingresos  \n";
        sql += "  FROM in_tmvin, in_tkapr   \n";
        sql += " WHERE mvin_natu = 'I'      \n";
        sql += "   AND mvin_mvin = kapr_mvin\n";
        sql += "   AND kapr_sede = " + sede + " \n";
        sql += "   AND kapr_dska = " + this.getDska_dska();
        return sql;
    }

    /**
     * Funcion encargada realizar el query para saber todos los egresos del
     * producto por sede
     *
     * @param sede
     * @return
     */
    public String egresosProdSede(String sede) {
        String sql = "";
        sql += "SELECT sum(kapr_cant_mvto) egresos  \n";
        sql += "  FROM in_tmvin, in_tkapr   \n";
        sql += " WHERE mvin_natu = 'E'      \n";
        sql += "   AND mvin_mvin = kapr_mvin\n";
        sql += "   AND kapr_sede = " + sede + " \n";
        sql += "   AND kapr_dska = " + this.getDska_dska();
        return sql;
    }

    /**
     * Funcion encargada de realizar el query para buscar un producto por su
     * codigo
     *
     * @return
     */
    public String buscaProductoXCodigo() {
        String select = "";
        select += "SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, \n";
        select += "       dska_porc_iva, dska_marca, dska_estado, dska_fec_ingreso, dska_cate,\n";
        select += "       dska_sbcu, refe_desc, marca_nombre                                  \n";
        select += "  FROM in_tdska, in_trefe, in_tmarca                                       \n";
        select += " WHERE dska_cod = '" + this.getDska_cod() + "' \n";
        select += "   AND dska_refe = refe_refe \n";
        select += "   AND marca_marca = dska_marca \n";
        return select;
    }

    /**
     * Funcion encargada de realizar el query para obtener el valor promedio de
     * cada producto pero con la mascara de moneda
     *
     * @return String Query
     */
    public String encontrarValorPromedioXProdMascaraMon() {
        String select = "SELECT to_char(kapr_cost_saldo_uni,'9999999999.0') costo "
                .concat("  FROM in_tkapr k1 ")
                .concat(" WHERE k1.kapr_dska = ").concat(this.getDska_dska())
                .concat("   AND k1.kapr_kapr = (SELECT max(k2.kapr_kapr) FROM in_tkapr k2 WHERE k2.kapr_dska = k1.kapr_dska ) ");
        return select;
    }

    /**
     * Funcion encargda de realizar el Query de los calculos necesarios para
     * facturar un producto
     *
     * @return
     */
    public String calculosFactura() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dska_dska,cantidad, codigo,nombre, to_char(precio,'9,999,999,999,999.00') precio,         \n");
        sql.append("       to_char(ivauni,'9,999,999,999,999.00') ivaUni,                                            \n");
        sql.append("       to_char(vlrTotal,'9,999,999,999,999.00') vlrTotal,                                        \n");
        sql.append("       to_char(ivaTotal,'9,999,999,999,999.00') ivaTotal,                                        \n");
        sql.append("       to_char((vlrTotal+ivaTotal),'9,999,999,999,999.00') totalPagar,                           \n");
        sql.append("       cast((vlrTotal+ivaTotal) as int) totalPagarSinFil,                                           \n");
        sql.append("       cast( ivaTotal as int ) totalIvaSinFil,                                                      \n");
        sql.append("       cast( vlrTotal as int) vlrPagarSinFil                                                        \n");
        sql.append("  FROM (SELECT  dska_dska,cantidad, codigo, nombre,                                                 \n");
        sql.append("                precio, ((precio*iva)/100) ivaUni,                                                  \n");
        sql.append("                (precio *cantidad) vlrTotal, (((precio*iva)/100)*cantidad) ivaTotal                 \n");
        sql.append("        FROM (SELECT dska_dska,dska_cod codigo, dska_nom_prod nombre,                               \n");
        sql.append("                     prpr_precio precio, ");
        sql.append(this.getCantidad());
        sql.append("cantidad,");
        sql.append("                     cast((select para_valor from em_tpara where para_clave = 'IVAPR')as int) iva   \n");
        sql.append("                FROM in_tdska, in_tprpr                                                             \n");
        sql.append("               WHERE dska_dska = ");
        sql.append(this.getDska_dska());
        sql.append("                 AND prpr_dska = dska_dska                                                          \n");
        sql.append("                 AND prpr_estado = 'A'                                                              \n");
        sql.append("                 AND prpr_sede = 1                                                                  \n");
        sql.append("              ) param                                                                               \n");
        sql.append("       ) tablaFinal                                                                                 \n");
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el query para buscar productos que tengan
     * la misma referencia, marca y categoria
     *
     * @return String SQL requerido
     */
    public String buscaProductosSimilares() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dska_cod, dska_desc, marca_nombre,refe_desc,cate_desc ");
        sql.append("FROM in_tdska, in_tmarca, in_trefe, in_tcate ");
        sql.append("WHERE dska_refe = ");
        sql.append(this.getDska_refe());
        sql.append(" AND dska_marca = ");
        sql.append(this.getDska_marca());
        sql.append(" AND dska_cate = ");
        sql.append(this.getDska_cate());
        sql.append(" AND dska_marca = marca_marca ");
        sql.append(" AND dska_refe = refe_refe ");
        sql.append(" AND cate_cate = dska_cate ");

        return sql.toString();
    }

    /**
     * Funcion encargada buscar el numero de productos registrados en un conteo
     * especifico
     *
     * @param copr_copr
     * @param dska_cod
     * @return
     */
    public String buscaProductoConteo(String copr_copr, String dska_cod) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dska_cod, ecop_valor, dska_nom_prod ");
        sql.append("FROM in_tecop, in_tdska ");
        sql.append("where dska_cod = '");
        sql.append(dska_cod.trim());
        sql.append("' ");
        sql.append("and ecop_dska = dska_dska ");
        sql.append("and ecop_copr = ");
        sql.append(copr_copr);
        return sql.toString();
    }

}
