/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.servicio;

import co.com.hotel.dto.Habitacion;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.validacion.ValidaCampos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SOFIA
 */
public class Adm_ServicioLogica {

    public boolean insertaServicio(Habitacion habitacion) {
        boolean rta = false;
        String rtaString = "";
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("IN_FINSERT_SERVICIO");
            function.adicionarNumeric(habitacion.getNumHabi());
            function.adicionarNumeric("" + habitacion.getNumMaxPers());
            function.adicionarNumeric("" + habitacion.getNumMinPers());
            function.adicionarNumeric("" + habitacion.getIva());
            function.adicionarParametro(habitacion.getBano());
            function.adicionarParametro(habitacion.getTelevision());
            function.adicionarParametro(habitacion.getCable());
            function.adicionarNumeric("" + habitacion.getNumCamas());
            function.adicionarParametro(habitacion.getCamaAux());
            rtaString = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaString.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String rtaAux = function.getRespuesta();
                    if (rtaAux.equalsIgnoreCase("Ok")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.insertaServicio " + e);
            rta = false;
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    public ArrayList<Habitacion> buscaHabitacionesXFiltros(Habitacion obj) {
        ArrayList<Habitacion> r = null;
        String rtaString = "";
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            sql += "SELECT dsha_dsha      \n";
            sql += "    ,dsha_num_hab     \n";
            sql += "    ,dsha_num_max_pers\n";
            sql += "    ,dsha_num_min_pers\n";
            sql += "    , dsha_bano       \n";
            sql += "    , dsha_televison  \n";
            sql += "    , dsha_cable      \n";
            sql += "    , dsha_num_camas  \n";
            sql += "    , dsha_cama_aux   \n";
            sql += "    , dsha_iva        \n";
            sql += "  FROM in_tdsha       \n";
            sql += "  where 1 = 1         \n";
            sql += armaWhereConsGeneral(obj);
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<Habitacion>();
                    cont++;
                }
                Habitacion aux = new Habitacion();
                aux.setIdHabitacion(rs.getString("dsha_dsha"));
                aux.setNumHabi(rs.getString("dsha_num_hab"));
                aux.setNumMaxPers(rs.getInt("dsha_num_max_pers"));
                aux.setNumMinPers(rs.getInt("dsha_num_min_pers"));
                aux.setBano(rs.getString("dsha_bano"));
                aux.setTelevision(rs.getString("dsha_televison"));
                aux.setCable(rs.getString("dsha_cable"));
                aux.setNumCamas(rs.getInt("dsha_num_camas"));
                aux.setCamaAux(rs.getString("dsha_cama_aux"));
                aux.setIva(rs.getString("dsha_iva"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.buscaHabitacionesXFiltros " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    public Habitacion buscaHabitacionXFiltros(Habitacion obj) {
        Habitacion r = null;
        String rtaString = "";
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        try {
            sql += "SELECT dsha_dsha      \n";
            sql += "    ,dsha_num_hab     \n";
            sql += "    ,dsha_num_max_pers\n";
            sql += "    ,dsha_num_min_pers\n";
            sql += "    , dsha_bano       \n";
            sql += "    , dsha_televison  \n";
            sql += "    , dsha_cable      \n";
            sql += "    , dsha_num_camas  \n";
            sql += "    , dsha_cama_aux   \n";
            sql += "    , trunc(dsha_iva) dsha_iva \n";
            sql += "  FROM in_tdsha       \n";
            sql += "  where 1 = 1         \n";
            sql += armaWhereConsGeneral(obj);
            sql += " limit 1\n";
            rs = function.enviarSelect(sql);
            if (rs.next()) {
                r = new Habitacion();
                r.setIdHabitacion(rs.getString("dsha_dsha"));
                r.setNumHabi(rs.getString("dsha_num_hab"));
                r.setNumMaxPers(rs.getInt("dsha_num_max_pers"));
                r.setNumMinPers(rs.getInt("dsha_num_min_pers"));
                r.setBano(rs.getString("dsha_bano"));
                r.setTelevision(rs.getString("dsha_televison"));
                r.setCable(rs.getString("dsha_cable"));
                r.setNumCamas(rs.getInt("dsha_num_camas"));
                r.setCamaAux(rs.getString("dsha_cama_aux"));
                r.setIva(rs.getString("dsha_iva"));
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.buscaHabitacionXFiltros " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    public String armaWhereConsGeneral(Habitacion obj) {
        String where = "\n";
        if (obj.getBano() != null) {
            if (obj.getBano().equalsIgnoreCase("S") || obj.getBano().equalsIgnoreCase("N")) {
                where += "and dsha_bano = '" + obj.getBano() + "'\n";
            }
        }
        if (obj.getCable() != null) {
            if (obj.getCable().equalsIgnoreCase("S") || obj.getCable().equalsIgnoreCase("N")) {
                where += "and dsha_cable = '" + obj.getCable() + "'\n";
            }
        }
        if (obj.getCamaAux() != null) {
            if (obj.getCamaAux().equalsIgnoreCase("S") || obj.getCamaAux().equalsIgnoreCase("N")) {
                where += "and dsha_cama_aux = '" + obj.getCamaAux() + "'\n";
            }
        }
        if (obj.getTelevision() != null) {
            if (obj.getTelevision().equalsIgnoreCase("S") || obj.getTelevision().equalsIgnoreCase("N")) {
                where += "and dsha_televison = '" + obj.getTelevision() + "'\n";
            }
        }
        if (obj.getNumCamas() > 0) {
            where += "and dsha_num_camas = " + obj.getNumCamas() + "\n";
        }
        if (obj.getNumHabi() != null || !obj.getNumHabi().equalsIgnoreCase("")) {
            where += "and cast(dsha_num_hab as varchar) like '%" + obj.getNumHabi().trim() + "%'\n";
        }
        return where;
    }

    public String actualizaServicio(Habitacion hab) {
        String rta = null;
        EnvioFunction function = new EnvioFunction();
        try {
            String sql = "";
            sql = armaUpdServicio(hab);
            if (sql != null) {
                function.enviarSelect(sql);
                return "Ok";
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.actualizaServicio " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }

    public String armaUpdServicio(Habitacion obj) {
        String update = "Update in_tdsha \n";
        update += "set dsha_num_hab = dsha_num_hab\n";
        int cont = 0;
        ValidaCampos valida = new ValidaCampos();
        if (valida.validaNulo("" + obj.getNumMaxPers())) {
            update += ", dsha_num_max_pers = " + obj.getNumMaxPers() + "\n";
            cont++;
        }
        if (valida.validaNulo("" + obj.getNumMinPers())) {
            update += ", dsha_num_min_pers = " + obj.getNumMinPers() + "\n";
            cont++;
        }
        if (valida.validaNulo(obj.getBano())) {
            update += ", dsha_bano = '" + obj.getBano() + "'\n";
            cont++;
        }
        if (valida.validaNulo(obj.getTelevision())) {
            update += ", dsha_televison = '" + obj.getTelevision() + "'\n";
            cont++;
        }
        if (valida.validaNulo(obj.getCable())) {
            update += ", dsha_cable = '" + obj.getCable() + "'\n";
            cont++;
        }
        if (valida.validaNulo(obj.getCamaAux())) {
            update += ", dsha_cama_aux = '" + obj.getCamaAux() + "'\n";
            cont++;
        }
        if (valida.validaNulo("" + obj.getNumCamas())) {
            update += ", dsha_num_camas = '" + obj.getNumCamas() + "'\n";
            cont++;
        }
        if (valida.validaNulo("" + obj.getIva())) {
            update += ", dsha_iva = '" + obj.getIva() + "'\n";
            cont++;
        }

        if (cont > 0) {
            update += "WHERE dsha_dsha = " + obj.getIdHabitacion();
        } else {
            return null;
        }
        return update;
    }

    public Habitacion buscaHabitacionXid(String dsha) {
        Habitacion r = null;
        String rtaString = "";
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dsha_dsha, dsha_num_hab, dsha_fec_ingreso, dsha_num_max_pers, \n";
            sql += "dsha_num_min_pers, dsha_bano, dsha_televison, dsha_cable, dsha_num_camas, \n";
            sql += "dsha_cama_aux, dsha_estado\n";
            sql += "FROM in_tdsha\n";
            sql += "where dsha_dsha = " + dsha;
            rs = function.enviarSelect(sql);
            if (rs.next()) {
                r = new Habitacion();
                r.setIdHabitacion(rs.getString("dsha_dsha"));
                r.setNumHabi(rs.getString("dsha_num_hab"));
                r.setFechaIngreso(rs.getString("dsha_fec_ingreso"));
                r.setNumMaxPers(rs.getInt("dsha_num_max_pers"));
                r.setNumMinPers(rs.getInt("dsha_num_min_pers"));
                r.setBano(rs.getString("dsha_bano"));
                r.setTelevision(rs.getString("dsha_televison"));
                r.setCable(rs.getString("dsha_cable"));
                r.setNumCamas(rs.getInt("dsha_num_camas"));
                r.setCamaAux(rs.getString("dsha_cama_aux"));
                r.setEstado(rs.getString("dsha_estado"));
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.buscaHabitacionXid " + e);
        } finally {
            function.cerrarConexion();
        }
        return r;
    }

    public Habitacion buscaHabitacionXNumHab(String numHab) {
        Habitacion r = null;
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dsha_dsha, dsha_num_hab, dsha_fec_ingreso, dsha_num_max_pers, \n";
            sql += "dsha_num_min_pers, dsha_bano, dsha_televison, dsha_cable, dsha_num_camas, \n";
            sql += "dsha_cama_aux, dsha_estado\n";
            sql += "FROM in_tdsha\n";
            sql += "where dsha_num_hab = " + numHab;
            rs = function.enviarSelect(sql);
            if (rs.next()) {
                r = new Habitacion();
                r.setIdHabitacion(rs.getString("dsha_dsha"));
                r.setNumHabi(rs.getString("dsha_num_hab"));
                r.setFechaIngreso(rs.getString("dsha_fec_ingreso"));
                r.setNumMaxPers(rs.getInt("dsha_num_max_pers"));
                r.setNumMinPers(rs.getInt("dsha_num_min_pers"));
                r.setBano(rs.getString("dsha_bano"));
                r.setTelevision(rs.getString("dsha_televison"));
                r.setCable(rs.getString("dsha_cable"));
                r.setNumCamas(rs.getInt("dsha_num_camas"));
                r.setCamaAux(rs.getString("dsha_cama_aux"));
                r.setEstado(rs.getString("dsha_estado"));
            }
        } catch (Exception e) {
            System.out.println("Error Adm_ServicioLogica.buscaHabitacionXNumHab " + e);
        } finally {
            function.cerrarConexion();
        }
        return r;
    }

// Pruebas de la clase
//    public static void main(String[] args) {
//        Adm_ServicioLogica obj= new Adm_ServicioLogica();
//        Habitacion hab = obj.buscaHabitacionXid("1");
//        System.out.println("Exito");
//    }
}
