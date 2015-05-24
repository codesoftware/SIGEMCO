/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.validaciones;

/**
 *
 * @author Personal
 */
public class ValidacionesString {
    
    public String quitacomas(String valor){
        String rta="";
        try {
            rta = valor.replaceAll(",", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    public String ponePunto(String valor){
        String rta = "";
        try {
           rta= valor.substring(0, valor.length()-2).concat(".").concat(valor.substring(valor.length()-2,valor.length()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
            
           
    
}
