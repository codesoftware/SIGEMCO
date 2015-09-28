/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.utilidades;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Personal
 */
public class ManejoLocateCO {

    NumberFormat currency;
    Locale coLocale = new Locale("es", "CO");
    Locale currentLocale = coLocale;

    /**
     * Metodo que me convierte un valor double (en este caso String por que asi
     * esta en la BD) y retorna un string con el formato de moneda colombiana.
     *
     * @param valor
     * @return
     */
    public String doubleTOMoney(String valor) {
        try {
            if (valor.equalsIgnoreCase("N/A")) {
                return valor;
            } else {
                Double d = Double.parseDouble(valor);
                currency = NumberFormat.getCurrencyInstance(currentLocale);
                return currency.format(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
