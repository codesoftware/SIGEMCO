/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.utilidades;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nicolas
 */
public class ManejoString {

    /**
     * Funcion encargada de eliminar todos los caracteres especificos de un
     * string
     *
     * @param String cadena a la cual se le desean eliminar los caracteres
     * @param String caracter el cual se quiere eliminar de la cadena
     * @return
     */
    public String eliminaCaracter(String cadena, char caracter) {
        int tamCadena = cadena.length();
        char[] nuevaCadena = new char[tamCadena];
        int j = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char aux1 = cadena.charAt(i);
            if (aux1 != caracter) {
                nuevaCadena[j] = aux1;
                j++;
            }
        }
        String cadenaEnvio = convertirString(nuevaCadena);
        return cadenaEnvio;
    }

    /**
     * Funcion encargada de convertir un array de char a un String
     *
     * @param char [] cadena la cual se va a convertir a String
     * @return cadena String
     */
    public String convertirString(char[] cadena) {
        String nuevaCadena = "";
        for (int i = 0; i < cadena.length; i++) {
            nuevaCadena = nuevaCadena + cadena[i];
        }
        return nuevaCadena.trim();
    }

    /**
     * Funcion la cual convierte una fecha la cual este fromateada en otro
     * formato de fechas a la deseada el formato deseado es dd/mm/yyyy
     *
     * @param fecha fecha la cual se desea formatear
     * @param formato es el formato en el cual es la fecha la cual esta enviando
     * @param separador es el caracter con el cual se separan las fechas
     * @return
     */
    public String convertirFormatoFechas(String fecha, String formato, String separador) {
        String fechaFinal = "";
        String[] vecFecha = fecha.split(separador);
        String[] vecFormato = formato.split(separador);
        Map<String, String> mapFecha = new HashMap<String, String>();
        for (int i = 0; i < vecFecha.length; i++) {
            mapFecha.put(vecFormato[i], vecFecha[i]);
        }
        fechaFinal = mapFecha.get("dd");
        fechaFinal += "/" + mapFecha.get("mm");
        fechaFinal += "/" + mapFecha.get("yyyy");
        return fechaFinal;
    }
    
    /**
     * Funcion para quitar puntos en una cadena
     * @param cadena que se va a reemplazar
     * @return Cadena sin puntos
     */

    public static String eliminaMascaraMoneda(String cadena) {
        String resultado = "";
        try {
            resultado = cadena.replaceAll("\\.", "");
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;

    }

}
