/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.validacion;

/**
 * Clase la cual se encargara de validar formatos de los campos para ingresar un
 * nuevo usuario
 *
 * @author nicolas
 * @version 1.0.0
 */
public class ValidaCampos {

    /**
     * Valida si el formato el cual se le envia solo tiene caracteres
     * alfabeticos
     *
     * @param String cadena caracteres que se evaluaran
     * @return true(tiene solo letras) false(contiene algún caracter numerico )
     */
    public boolean validaLetras(String cadena) {
        boolean rta = true;
        for (int i = 0; i < cadena.length(); i++) {
            char aux1 = cadena.charAt(i);
            boolean aux2 = Character.isLetter(aux1);
            if (aux2 == false) {
                if (aux1 != ' ') {
                    return false;
                }
            }
        }
        return rta;
    }

    /**
     * Valida si la cadena la cual es enviada tiene un formato especifico de
     * correo
     *
     * @param String correo: cadena el cual contiene el correo
     * @return true si el campo tiene el formato deseado
     */
    public boolean validaCorreo(String correo) {
        String[] correoList = correo.split("@");
        boolean aux1 = validaEspacio(correo);
        if (aux1 == true) {
            if (correoList.length == 2) {
                String aux3 = correoList[1];
                int busca = aux3.indexOf(".");
                if (busca > -1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Valida si la cadena tiene espacios en medio
     *
     * @param String cadena: cadena de caracteres que se desea evaluar
     * @return retorna true si la cadena no contiene espacios
     */
    public boolean validaEspacio(String cadena) {
        boolean rta = true;
        for (int i = 0; i < cadena.length(); i++) {
            char aux1 = cadena.charAt(i);
            if (aux1 == ' ') {
                return false;
            }
        }
        return rta;
    }

    /**
     * Valida si la cadena que se envia es numerico
     *
     * @param String number: cadena de caracteres que se desea evaluar
     * @return true: si la cadena es un numero
     */
    public boolean validaNumerico(String number) {
        try {
            double cedula;
            cedula = Double.parseDouble(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validaFloat(String number) {
        try {
            float number2;
            number2 = Float.parseFloat(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Evalua si el campo es nulo o vacio retorna true si no es nulo y false si
     * es nulo
     *
     * @param campo
     * @return
     */
    public boolean validaNulo(String campo) {
        try {
            if (campo.isEmpty() || campo == null || campo.trim().equalsIgnoreCase("")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean validaDuplicados(String valor1, String valor2) {
        return valor1.equals(valor2);
    }

    public boolean validaCantidadCaracteres(String valor, int valMax, int valMin) {
        int tam = valor.length();
        if (valMax >= tam) {
            if (valMin <= tam) {
                return true;
            }
        }
        return false;
    }

}
