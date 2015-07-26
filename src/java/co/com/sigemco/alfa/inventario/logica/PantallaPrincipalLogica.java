/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.PantallaPrincipalDao;
import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class PantallaPrincipalLogica {

    /**
     * Funcion encargada de realizar la logica para guardar un producto en la
     * tabla de lista
     *
     * @param codigo
     * @param tipo
     * @param posicion
     * @return
     */
    public String guardaProducto(String codigo, String tipo, int posicion) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            PantallaPrincipalDto objDto = new PantallaPrincipalDto();
            objDto.setPpfa_codigo(codigo);
            objDto.setPpfa_posicion("" + posicion);
            String sql = "";
            PantallaPrincipalDao objdao = new PantallaPrincipalDao();
            if ("producto".equalsIgnoreCase(tipo)) {
                sql = objdao.insertaProducto(objDto);
            } else {
                sql = objdao.insertaReceta(objDto);
            }
            boolean valida = function.enviarUpdate(sql);
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error al insertar el registro";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de realzar la logica para buscar los productos
     *
     * @return
     */
    public ArrayList<PantallaPrincipalDto> buscaProductos() {
        ArrayList<PantallaPrincipalDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            PantallaPrincipalDao objDao = new PantallaPrincipalDao();
            ResultSet rs = function.enviarSelect(objDao.buscaProductos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                PantallaPrincipalDto aux = new PantallaPrincipalDto();
                aux.setPpfa_ppfa(rs.getString("ppfa_ppfa"));
                aux.setPpfa_codigo(rs.getString("ppfa_codigo"));
                aux.setPpfa_tipo(rs.getString("ppfa_tipo"));
                aux.setPpfa_nombre(rs.getString("ppfa_nombre"));
                aux.setPpfa_posicion(rs.getString("ppfa_posicion"));
                aux.setPpga_ruta_img(rs.getString("ppga_ruta_img"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realzar la logica para buscar los productos
     *
     * @return
     */
    public ArrayList<PantallaPrincipalDto> buscaRecetas() {
        ArrayList<PantallaPrincipalDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            PantallaPrincipalDao objDao = new PantallaPrincipalDao();
            ResultSet rs = function.enviarSelect(objDao.buscaRecetas());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                PantallaPrincipalDto aux = new PantallaPrincipalDto();
                aux.setPpfa_ppfa(rs.getString("ppfa_ppfa"));
                aux.setPpfa_codigo(rs.getString("ppfa_codigo"));
                aux.setPpfa_tipo(rs.getString("ppfa_tipo"));
                aux.setPpfa_nombre(rs.getString("ppfa_nombre"));
                aux.setPpfa_posicion(rs.getString("ppfa_posicion"));
                aux.setPpga_ruta_img(rs.getString("ppga_ruta_img"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de eliminar un item de la tabla de los productos que se
     * visualizaran en la pantalla principal
     *
     * @param ppfa_ppfa
     * @return
     */
    public String eliminaItem(String ppfa_ppfa) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            PantallaPrincipalDao objdao = new PantallaPrincipalDao();
            boolean valida = function.enviarUpdate(objdao.eliminaItem(ppfa_ppfa));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error al eliminar el item";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

}
