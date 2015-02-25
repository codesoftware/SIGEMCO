/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.TemMovContablesDao;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

/**
 * Clase encargada de realizar la logica implicada para los movimientos
 * contables
 *
 * @author Nicolas
 */
public class MoviContablesLogica {

    /**
     * Funcion encargada de realizar la logica de insertar en la tabla temporal
     * CO_TTEM_MVCO para luego procesar los datos y insertarlos en la tabla de
     * movimientos contables
     *
     * @param lista Tiene los datos insertados por el usuario
     * @return Si la operacion fue exitosa retornara el numero de la transaccion
     */
    public String insertaSbcuTablaTempo(List<String> lista) {
        String idTransac = "";
        try(EnvioFunction function = new EnvioFunction()) {
            Iterator<String> it = lista.iterator();
            idTransac = obtineSecuenciaTemMvCo();
            if (idTransac != null) {
                while (it.hasNext()) {
                    String linea = it.next();
                    String[] aux = linea.split("&");
                    String validaCreaSbcu = aux[2];
                    if ( !validaCreaSbcu.equalsIgnoreCase("S")) {
                        TemMovContablesDao objDao = new TemMovContablesDao();
                        if (aux.length == 4) {
                            objDao.setTem_mvco_trans(idTransac);
                            objDao.setTem_mvco_sbcu(aux[0]);
                            objDao.setTem_mvco_valor(aux[1]);
                            objDao.setTem_mvco_naturaleza(aux[3]);
                            function.enviarUpdate(objDao.insert());                            
                        } else {                            
                            return "Error el numero de datos no es concordante por favor verifique e intente de nuevo ";
                        }
                    }else{
                        it.remove();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            idTransac = "Error MoviContablesLogica.insertaSbcuTablaTempo " + e;
        }
        return idTransac;
    }

    /**
     * Funcion encargada de obtener el valor de la secuencia utilizada para la
     * temporal de movimientos contables
     *
     * @return
     */
    public String obtineSecuenciaTemMvCo() {
        String sec = "";
        try (EnvioFunction function = new EnvioFunction()) {
            TemMovContablesDao objDao = new TemMovContablesDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneSecuenciaTem());
            if (rs.next()) {
                sec = rs.getString("secuencia");
            } else {
                sec = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sec = null;
        }
        return sec;
    }

}
