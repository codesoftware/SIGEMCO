/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.parametros.dao;

/**
 *
 * @author root
 */
public class ParametrosAdminDao {
    /**
     * Consulta los parametros por estado
     * @param estado
     * @return 
     */
    public String consultaParametrosEstado(String estado){
        String select ="SELECT parsadm_parsadm, parsadm_nombre, parsadm_estado, parsadm_valor  FROM ad_tparsadm where parsadm_estado = '"+estado+"'";
        
        return select;
    }
    
}
