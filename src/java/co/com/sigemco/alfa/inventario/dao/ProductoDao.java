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

    /**
     * Funcion encargada de retornar el select con los filtros solicitados por
     * el usuario
     *
     * @return String consulta solicitada
     */
    public String selectConFiltros() {
        String select = "";
        select += "SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, \n";
        select += "       dska_porc_iva, dska_marca, dska_estado, dska_fec_ingreso, dska_cate \n";
        select += "  FROM in_tdska                                                            \n";
        select += " WHERE 1 = 1 \n";
        return select;
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

}
