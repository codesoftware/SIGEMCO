/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

import co.com.hotel.validacion.ValidaCampos;

/**
 *
 * @author Personal
 */
public class ReferenciaDao {

    private String refe_refe;
    private String refe_desc;
    private String refe_estado;
    private String refe_came;
    private String refe_memori;
    private String refe_pantalla;
    private String refe_nombre;

    public String getRefe_pantalla() {
        return refe_pantalla;
    }

    public void setRefe_pantalla(String refe_pantalla) {
        this.refe_pantalla = refe_pantalla;
    }

    public String getRefe_refe() {
        return refe_refe;
    }

    public void setRefe_refe(String refe_refe) {
        this.refe_refe = refe_refe;
    }

    public String getRefe_desc() {
        return refe_desc;
    }

    public void setRefe_desc(String refe_desc) {
        this.refe_desc = refe_desc;
    }

    public String getRefe_estado() {
        return refe_estado;
    }

    public void setRefe_estado(String refe_estado) {
        this.refe_estado = refe_estado;
    }

    public String getRefe_came() {
        return refe_came;
    }

    public void setRefe_came(String refe_came) {
        this.refe_came = refe_came;
    }

    public String getRefe_memori() {
        return refe_memori;
    }

    public void setRefe_memori(String refe_memori) {
        this.refe_memori = refe_memori;
    }

    public String getRefe_nombre() {
        return refe_nombre;
    }

    public void setRefe_nombre(String refe_nombre) {
        this.refe_nombre = refe_nombre;
    }

    /**
     * Funcion la cual devuelve el query para obtener todas las categorias
     * parametrizadas en el sitema
     *
     * @return
     */
    public String consultaGeneralActivos() {
        String select = "";
        select += "SELECT refe_refe, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla\n";
        select += "  FROM in_trefe                                                 \n";
        select += " WHERE refe_estado = 'A' \n";
        select += " ORDER BY refe_desc ";
        //System.out.println("Sql \n" + select);
        return select;
    }

    /**
     * Funcion la cual devuelve el query para obtener todas las categorias
     * parametrizadas en el sitema
     *
     * @param filtros
     * @return
     */
    public String consultaFilros(String filtros) {
        String select = "SELECT refe_refe,refe_nombre, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla  from in_trefe  WHERE " + filtros;
        System.out.println("Filtros" + select);
        return select;
    }

    /**
     * Funcion la cual genera el query para obtener una categoria en especifico
     * dado el id de la categoria
     *
     * @return
     */
    public String consultaEspecificaXId() {
        String select = "";
        select += "SELECT refe_refe,refe_nombre, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla \n";
        select += "  FROM in_trefe                                                 \n";
        select += " WHERE refe_refe = " + this.refe_refe + " \n";
        return select;
    }

    /*PFuncion que inserta la referencia para la categoría del celular
     *
     * @return
     
     **/
    public String insertaReferencia() {
        StringBuilder insert = new StringBuilder();
        StringBuilder values = new StringBuilder();
        insert.append("INSERT into in_trefe (refe_desc,  refe_nombre");
        values.append(" VALUES ('");
        values.append(this.refe_desc);
        values.append("','");
        values.append(this.refe_nombre);
        values.append("'");
        ValidaCampos valida = new ValidaCampos();
        if (valida.validaNulo(this.refe_came)) {
            insert.append(",refe_came");
            values.append(",'");
            values.append(this.refe_came);
            values.append("'");
        }
        if (valida.validaNulo(this.refe_memori)) {
            insert.append(",refe_memori");
            values.append(",'");
            values.append(this.refe_memori);
            values.append("'");
        }
        if (valida.validaNulo(this.refe_pantalla)) {
            insert.append(",refe_pantalla");
            values.append(",'");
            values.append(this.refe_pantalla);
            values.append("'");
        }
        insert.append(")");
        values.append(")");
        return insert.toString() + " " + values.toString();

    }

    /**
     * Funcion con la cual creo el query para la actualizacion de las
     * referencias
     *
     * @return
     */
    public String actualizaReferencia() {
        StringBuilder update = new StringBuilder();
        update.append("UPDATE in_trefe set refe_desc = '");
        update.append(this.refe_desc);
        update.append("',refe_estado = '");
        update.append(this.refe_estado);
        update.append("',refe_came = '");
        update.append(this.refe_came);
        update.append("',refe_memori = '");
        update.append(this.refe_memori);
        update.append("',refe_pantalla = '");
        update.append(this.refe_pantalla);
        update.append("',refe_nombre = '");
        update.append(this.refe_nombre);
        update.append("' WHERE refe_refe = ");
        update.append(this.refe_refe);
        return update.toString();
    }

}
