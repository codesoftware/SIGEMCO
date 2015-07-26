/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dao;

import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;

/**
 *
 * @author nicolas
 */
public class PantallaPrincipalDao {

    /**
     * Funcion la cual inserta un producto para su posterior visualizacion en la
     * pantalla principal de facturacion
     *
     * @param objDto
     * @return
     */
    public String insertaProducto(PantallaPrincipalDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into in_tppfa(PPFA_CODIGO  , PPFA_TIPO    , PPFA_NOMBRE  , PPFA_POSICION, PPGA_RUTA_IMG,PPFA_EXTENSION) ");
        sql.append("values('");
        sql.append(objDto.getPpfa_codigo());
        sql.append("',");
        sql.append("'P',");
        sql.append("(select dska_nom_prod from in_tdska where dska_cod = '");
        sql.append(objDto.getPpfa_codigo());
        sql.append("'),");
        sql.append(objDto.getPpfa_posicion());
        sql.append(",'");
        sql.append(objDto.getPpfa_ruta_img());
        sql.append("','");
        sql.append(objDto.getPpfa_extencion());
        sql.append("')");
        return sql.toString();
    }

    /**
     * Funcion la cual inserta un producto para su posterior visualizacion en la
     * pantalla principal de facturacion
     *
     * @param objDto
     * @return
     */
    public String insertaReceta(PantallaPrincipalDto objDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into in_tppfa(PPFA_CODIGO  , PPFA_TIPO    , PPFA_NOMBRE  , PPFA_POSICION, PPGA_RUTA_IMG,PPFA_EXTENSION) ");
        sql.append("values('");
        sql.append(objDto.getPpfa_codigo());
        sql.append("',");
        sql.append("'R',");
        sql.append("(select rece_nombre from in_trece where rece_codigo = '");
        sql.append(objDto.getPpfa_codigo());
        sql.append("'),");
        sql.append(objDto.getPpfa_posicion());
        sql.append(",'");
        sql.append(objDto.getPpfa_ruta_img());
        sql.append("','");
        sql.append(objDto.getPpfa_extencion());
        sql.append("')");
        return sql.toString();
    }

    public String buscaProductos() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ppfa_ppfa, ppfa_codigo  , ppfa_tipo    , ppfa_nombre  , ppfa_posicion, ppga_ruta_img ");
        sql.append("FROM in_tppfa                                                                    ");
        sql.append("WHERE ppfa_tipo = 'P'                                                            ");
        sql.append("ORDER BY ppfa_posicion                                                           ");
        return sql.toString();
    }

    public String buscaRecetas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ppfa_ppfa, ppfa_codigo  , ppfa_tipo    , ppfa_nombre  , ppfa_posicion, ppga_ruta_img ");
        sql.append("FROM in_tppfa                                                                    ");
        sql.append("WHERE ppfa_tipo = 'R'                                                            ");
        sql.append("ORDER BY ppfa_posicion                                                           ");
        return sql.toString();
    }

    public String eliminaItem(String ppfa_ppfa) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM in_tppfa ");
        sql.append("WHERE ppfa_ppfa = ");
        sql.append(ppfa_ppfa);
        return sql.toString();
    }

}
