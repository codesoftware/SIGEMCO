/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.permisos.dao;

/**
 *
 * @author john
 */
public class AdPermisosDao {

    public String buscaClave(String clave) {
        String select = "SELECT  PARA_ESTADO FROM AD_TPARA WHERE PARA_NOMBRE  = '"+clave+"'" ;
        System.out.println("Update " + select);
        return select;
    }
}
