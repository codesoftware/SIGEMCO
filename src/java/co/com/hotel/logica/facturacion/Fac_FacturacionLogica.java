/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.facturacion;

import co.com.hotel.dto.Habitacion;
import co.com.hotel.dto.facturacion.Cliente;
import co.com.hotel.dto.facturacion.Factura;
import co.com.hotel.dto.facturacion.Producto;
import co.com.hotel.dto.facturacion.Servicio;
import co.com.hotel.logica.servicio.Adm_ServicioLogica;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.validacion.ValidaCampos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SOFIA
 */
public class Fac_FacturacionLogica {

    public ArrayList<Habitacion> buscarHabitacionesNumPersNumHab(String numPers, String numHabi) {
        ArrayList<Habitacion> r = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        String sqlWhere = "";
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dsha_dsha llave, dsha_num_hab habi,dsha_num_max_pers num_max,"
                    + "dsha_num_min_pers num_min,dsha_num_camas num_camas\n";
            sql += "FROM in_tdsha\n";
            sqlWhere = "where dsha_num_max_pers >= " + numPers;
            rs = function.enviarSelect(sql + sqlWhere);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<>();
                    cont = 1;
                }
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getString("llave"));
                habitacion.setNumHabi(rs.getString("habi"));
                habitacion.setNumMaxPers(rs.getInt("num_max"));
                habitacion.setNumMinPers(rs.getInt("num_min"));
                habitacion.setNumCamas(rs.getInt("num_camas"));
                r.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscarHabitacionesNumPersNumHab " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    /**
     * Funcion la cual busca las habitaciones las cuales tienen disponibilidad y
     * capacidas necesaria para hospedar a los clientes
     *
     * @param numPers
     * @param fechaInicial
     * @param usuario
     * @param NumDias
     * @return
     */
    public ArrayList<Habitacion> buscarHabitacionesNumPersNumHab(String numPers, String fechaInicial, String usuario, String NumDias) {
        ArrayList<Habitacion> r = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        String sqlWhere = "";
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dsha_dsha llave, dsha_num_hab habi,dsha_num_max_pers num_max,"
                    + "dsha_num_min_pers num_min,dsha_num_camas num_camas\n";
            sql += "FROM in_tdsha\n";
            sqlWhere = "WHERE dsha_num_max_pers >= " + numPers + "\n";
            sqlWhere += "AND 'S' = FA_VERF_RESERVA_HABITACION(" + usuario + ",to_date('" + fechaInicial + "', 'mm/dd/yyyy')," + NumDias + ",dsha_dsha)\n";
            rs = function.enviarSelect(sql + sqlWhere);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<>();
                    cont = 1;
                }
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getString("llave"));
                habitacion.setNumHabi(rs.getString("habi"));
                habitacion.setNumMaxPers(rs.getInt("num_max"));
                habitacion.setNumMinPers(rs.getInt("num_min"));
                habitacion.setNumCamas(rs.getInt("num_camas"));
                r.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscarHabitacionesNumPersNumHab " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    /**
     * Funcion el cual busca todas las habitaciones
     *
     * @return
     */
    public ArrayList<Habitacion> buscarHabitaciones() {
        ArrayList<Habitacion> r = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        String sqlWhere = "";
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dsha_dsha llave, dsha_num_hab habi,dsha_num_max_pers num_max,"
                    + "dsha_num_min_pers num_min,dsha_num_camas num_camas\n";
            sql += "FROM in_tdsha\n";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<>();
                    cont = 1;
                }
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getString("llave"));
                habitacion.setNumHabi(rs.getString("habi"));
                habitacion.setNumMaxPers(rs.getInt("num_max"));
                habitacion.setNumMinPers(rs.getInt("num_min"));
                habitacion.setNumCamas(rs.getInt("num_camas"));
                r.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscarHabitacionesNumPersNumHab " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return r;
    }

    public boolean verificaDisHabitacionRangoFechas(String fechaIni, String dsha_dsha, String numDias) {
        boolean rta = false;
        EnvioFunction function = new EnvioFunction();
        String sql = "SELECT FA_VERF_RESERVA_HABITACION(1,to_date('" + fechaIni + "', 'mm/dd/yyyy'), " + numDias + ", " + dsha_dsha + ") AS reserva;";
        ResultSet rs = null;
        try {
            rs = function.enviarSelect(sql);
            if (rs.next()) {
                String rtaString = rs.getString("reserva");
                if (rtaString.equalsIgnoreCase("S")) {
                    rta = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.verificaDisHabitacionRangoFechas " + e);
        } finally {
            function.cerrarConexion();
            function = null;
        }
        return rta;
    }

    public Map<String, String> realizarFacturacion(String[] habitaciones, String numDias, String fechaInicial, String cliente, String tius) {
        Map<String, String> rta = new HashMap<String, String>();
        rta.put("ErrorDetalles", "");
        rta.put("RtaFact", "");
        String factura = creaFactura(tius, cliente);
        String[] rtaFact = factura.split("-");
        String idFactura = rtaFact[1];
        if (rtaFact[0].trim().equalsIgnoreCase("Ok")) {
            rta.put("RtaFact", "Factura realizada Exitosamente\n");
            rta.put("fact_fact", idFactura);
            for (int i = 0; i < habitaciones.length; i++) {
                String rtaDetalleFact = this.creaDetallesServFactura(tius, fechaInicial, numDias, habitaciones[i], idFactura);
                if (!rtaDetalleFact.equalsIgnoreCase("OK")) {
                    String aux = rta.get("ErrorDetalles");
                    rta.remove("ErrorDetalles");
                    Adm_ServicioLogica logica = new Adm_ServicioLogica();
                    Habitacion hab = logica.buscaHabitacionXid(habitaciones[i]);
                    aux += "\n Error al crear Detalle de factura con la habitación numero: " + hab.getNumHabi() + "\n";
                    aux += "(Error data base: )" + rtaDetalleFact + "\n";
                    rta.put("ErrorDetalles", aux);
                }
            }
        } else {
            rta.put("RtaFact", "Error al crear la factura");
        }
        return rta;
    }

    public String creaFactura(String tius, String cliente) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        synchronized (this) {
            try {
                function.adicionarNombre("FA_CREA_FACTURA");
                function.adicionarNumeric(tius);
                function.adicionarNumeric(cliente);
                rta = function.llamarFunction(function.getSql());
                function.recuperarString();
                String[] rtaVector = rta.split("-");
                int tam = rtaVector.length;
                if (tam == 2) {
                    // Este mensaje lo envia la funcion que envia la funcion de java que
                    // confirma que el llamado de a la funcion fue exitiso.
                    if (rtaVector[1].equalsIgnoreCase("Ok")) {
                        // Aqui verifico si la consulta fue exitosa
                        rta = function.getRespuesta();
                    }
                }
            } catch (Exception e) {
                System.out.println("Error Fac_FacturacionLogica.creaFactura " + e);

            } finally {
                function.cerrarConexion();
            }
        }
        return rta;
    }

    public String creaDetallesServFactura(String tius, String fechaIni, String numDias, String dsha, String fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CREA_DETALLE_SERVFACTURA");
            function.adicionarNumeric(tius);
            function.addicionarParametroDate(fechaIni);
            function.adicionarNumeric(numDias);
            function.adicionarNumeric(dsha);
            function.adicionarNumeric(fact);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    rta = function.getRespuesta();
                }
            }

        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.creaDetallesServFactura " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    public Factura recuperaFacturaXId(String fact_fact) {
        Factura rta = null;
        try {
            rta = recuperaDatosFactura(fact_fact);
            rta.setDetalleServicio(this.recuperaDetalleFacturaServicio(fact_fact));
            rta.setCliente(this.recuperaClienteXFact(fact_fact));
            rta.setFact_fact(fact_fact);
            rta.setValorServicio(this.buscaValorTotalServXFact_Fact(fact_fact));
            rta.setValorIva(this.buscaValorIvaServXFact_Fact(fact_fact));
            rta.setValorTotal(this.buscaValorTotalXFact_Fact(fact_fact));
            rta.setDetalleProducto(this.recuperaDetalleFacturaProducto(fact_fact));
            rta.setValorIvaPro(this.buscaValorIvaProdXFact_Fact(fact_fact));
            rta.setValorProductos(this.buscaValorTotalProdXFact_Fact(fact_fact));
            rta.setValorTotalProd(this.buscaValorTotalProductosXFact_Fact(fact_fact));
            
        } catch (Exception e) {
        } finally {
        }
        return rta;
    }

    /**
     * Funcion la cual se encarga de recuperar los productos asociados a una
     * factura
     *
     * @param fact_fact identificador primario de la tabla in_tfact facturacion
     * @return lista de Productos
     */
    public List<Producto> recuperaDetalleFacturaProducto(String fact_fact) {
        List<Producto> r = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String sql = "";
        int cont = 0;
        try {
            sql += "SELECT  coalesce(dtpr_dtpr, 0)         dtpr_dtpr        ,\n";
            sql += "        coalesce(dtpr_dska, 0)         dtpr_dska        ,\n";
            sql += "        coalesce(dtpr_fact, 0)         dtpr_fact        ,\n";
            sql += " to_char(coalesce(dtpr_fecha, now()),'dd/mm/yyyy')    dtpr_fecha       ,\n";
            sql += "        coalesce(dtpr_num_prod, 0)     dtpr_num_prod    ,\n";
            sql += "        coalesce(dtpr_cant, 0)         dtpr_cant        ,\n";
            sql += " to_char(coalesce(dtpr_valor_pr, 0),'LFM9,999,999.99')     dtpr_valor_pr    ,\n";
            sql += " to_char(coalesce(dtpr_valor_iva, 0),'LFM9,999,999.99')    dtpr_valor_iva   ,\n";
            sql += " to_char(coalesce(dtpr_vl_uni_prod, 0),'LFM9,999,999.99')  dtpr_vl_uni_prod ,\n";
            sql += " to_char(coalesce(dtpr_valor_venta, 0),'LFM9,999,999.99')  dtpr_valor_venta ,\n";
            sql += "        coalesce(dtpr_desc, ' ')       dtpr_desc        ,\n";
            sql += "        coalesce(dtpr_con_desc, ' ')   dtpr_con_desc    ,\n";
            sql += "        coalesce(dtpr_valor_desc, ' ') dtpr_valor_desc  ,\n";
            sql += "        dska_nom_prod                                   \n";
            sql += "FROM fa_tdtpr, in_tdska                                 \n";
            sql += "WHERE dtpr_fact =  " + fact_fact + "                    \n";
            sql += "  AND dtpr_dska = dska_dska                             \n";
            sql += "  AND dtpr_estado = 'A'                                 \n";
            sql += "ORDER BY dtpr_dtpr                                      \n";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<Producto>();
                    cont++;
                }
                Producto aux = new Producto();
                aux.setDtpr_dtpr(rs.getString("DTPR_DTPR"));
                aux.setDtpr_dska(rs.getString("DTPR_DSKA"));
                aux.setDtpr_fact(rs.getString("DTPR_FACT"));
                aux.setDtpr_fecha(rs.getString("DTPR_FECHA"));
                aux.setDtpr_num_prod(rs.getString("DTPR_NUM_PROD"));
                aux.setDtpr_cant(rs.getString("DTPR_CANT"));
                aux.setDtpr_valor_pr(rs.getString("DTPR_VALOR_PR"));
                aux.setDtpr_valor_iva(rs.getString("DTPR_VALOR_IVA"));
                aux.setDtpr_vl_uni_prod(rs.getString("DTPR_VL_UNI_PROD"));
                aux.setDtpr_valor_venta(rs.getString("DTPR_VALOR_VENTA"));
                aux.setDtpr_desc(rs.getString("DTPR_DESC"));
                aux.setDtpr_con_desc(rs.getString("DTPR_CON_DESC"));
                aux.setDtpr_valor_desc(rs.getString("DTPR_VALOR_DESC"));
                aux.setNombProducto(rs.getString("dska_nom_prod"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.recuperaDetalleFacturaProducto " + e);
        } finally {
            function.cerrarConexion();
        }
        return r;
    }

    public List<Servicio> recuperaDetalleFacturaServicio(String fact_fact) {
        List<Servicio> r = null;
        String sql = "";
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        int cont = 0;
        try {
            sql = "SELECT dtsv_dtsv, dtsv_rvha, dtsv_fecha, to_char(dtsv_valor_venta,'LFM9,999,999.99') dtsv_valor_venta, "
                    + "to_char(dtsv_valor_sv,'LFM9,999,999.99') dtsv_valor_sv, dtsv_desc, dtsv_con_desc, to_char(dtsv_valor_desc,'LFM9,999,999.99') dtsv_valor_desc"
                    + " , to_char(dtsv_valor_iva,'LFM9,999,999.99') dtsv_valor_iva\n";
            sql += ",rvha_num_dias,dsha_num_hab,to_char(prha_precio,'LFM9,999,999.99') prha_precio \n";
            sql += "from fa_tdtsv, in_trvha, in_tdsha, in_tprha\n";
            sql += "where dtsv_fact = " + fact_fact;
            sql += "and dtsv_rvha = rvha_rvha\n";
            sql += "and rvha_dsha = dsha_dsha\n";
            sql += "and prha_dsha = dsha_dsha\n";
            sql += "and prha_estado = 'A'\n";
            sql += "and dtsv_estado = 'A' ";
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<Servicio>();
                    cont++;
                }
                Servicio aux = new Servicio();
                aux.setDtsv_dtsv(rs.getString("dtsv_dtsv"));
                aux.setDtsv_fecha(rs.getString("dtsv_fecha"));
                aux.setDtsv_valor_sv(rs.getString("dtsv_valor_sv"));
                aux.setDtsv_valor_venta(rs.getString("dtsv_valor_venta"));
                aux.setDtsv_rvha(rs.getString("dtsv_rvha"));
                aux.setDtsv_valor_iva(rs.getString("dtsv_valor_iva"));
                aux.setNumHabitacion(rs.getString("dsha_num_hab"));
                aux.setDiasReserv(rs.getString("rvha_num_dias"));
                aux.setPrecioHabitacion(rs.getString("prha_precio"));
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.recuperaDetalleFactura " + e);
        } finally {
            function.cerrarConexion();
        }
        return r;
    }

    public Cliente recuperaClienteXFact(String fact_fact) {
        Cliente r = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        ResultSet rs = null;
        try {
            sql += "SELECT pers_pers, pers_apellido, pers_nombre, pers_cedula,\n";
            sql += "       pers_email, pers_fecha_nac, pers_tel, pers_cel,    \n";
            sql += "       pers_dir, pers_dept_resi,pers_ciudad_resi          \n";
            sql += "FROM us_tclien, us_tpers, fa_tfact                        \n";
            sql += "WHERE clien_pers = pers_pers                              \n";
            sql += "  AND fact_clien = clien_clien                            \n";
            sql += "  AND fact_fact = " + fact_fact;

            rs = function.enviarSelect(sql);

            if (rs.next()) {
                r = new Cliente();
                r.setIdCliente(rs.getString("pers_pers"));
                r.setApellidos(rs.getString("pers_apellido"));
                r.setNombres(rs.getString("pers_nombre"));
                r.setCedula(rs.getString("pers_cedula"));
                r.setMail(rs.getString("pers_email"));
                r.setFechaNac(rs.getString("pers_fecha_nac"));
                r.setTel(rs.getString("pers_tel"));
                r.setCel(rs.getString("pers_cel"));
                r.setDireccion(rs.getString("pers_dir"));
                r.setDepartamentoRes(rs.getString("pers_dept_resi"));
                r.setCiudadResi(rs.getString("pers_ciudad_resi"));
            }

        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.recuperaCliente " + e);
        } finally {
            function.cerrarConexion();
        }
        return r;
    }

    public Factura recuperaDatosFactura(String fact_fact) {
        Factura factAux = new Factura();
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        ResultSet rs = null;
        try {
            sql += "select (select PERS_APELLIDO || ' ' ||PERS_NOMBRE from us_ttius, us_tpers where tius_pers = pers_pers AND fact_tius = tius_tius) usuario,\n";
            sql += "fact_fact, \n ";
            sql += "case \n ";
            sql += "when fact_estado = 'P' then 'Por Aprobación' \n ";
            sql += "when fact_estado = 'C' then 'Cancelada'\n ";
            sql += "when fact_estado = 'U' then 'Confirmada' \n ";
            sql += "end estado,\n ";
            sql += "to_char(fact_fec_ini,'dd/mm/yyyy')  fecha,\n ";
            sql += "to_char(fact_vlr_total,'LFM9,999,999.99') fact_vlr_total , \n";            
            sql += "to_char(coalesce(fact_vlr_total+ fact_vlr_iva, 0 ),'LFM9,999,999.99') iva_total,\n";
            sql += "to_char(fact_vlr_iva,'LFM9,999,999.99') fact_vlr_iva\n";
            sql += "from fa_tfact WHERE fact_fact = " + fact_fact + " \n ";

            rs = function.enviarSelect(sql);

            if (rs.next()) {
                factAux.setFact_fact(fact_fact);
                factAux.setFact_fec_ini(rs.getString("fecha"));
                factAux.setFact_estado(rs.getString("estado"));
                factAux.setUsuarioFacturo(rs.getString("usuario"));
                factAux.setFact_vlr_iva(rs.getString("fact_vlr_iva"));
                factAux.setFact_vlr_total(rs.getString("fact_vlr_total"));
                factAux.setFact_vlr_total_iva(rs.getString("iva_total"));
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.recuperaDatosFactura " + e);
        } finally {
            function.cerrarConexion();
        }
        return factAux;
    }

    /**
     * Busca el valor del servicio de las habitaciones sin iva por factura
     *
     * @param fact_fact
     * @return valor del servicio
     */
    public String buscaValorTotalServXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("1");
            function.adicionarNumeric("2");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }
    
    public String buscaValorTotalProdXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("2");
            function.adicionarNumeric("2");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    /**
     * Busca el valor total de la factura
     *
     * @param fact_fact : identificador de la tabla de facturacion
     * @return
     */
    public String buscaValorTotalXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("1");
            function.adicionarNumeric("1");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    } 
    
    public String buscaValorTotalProductosXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("2");
            function.adicionarNumeric("1");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    /**
     * Obtener el valor del iva de la factura solo de los servicios
     *
     * @param fact_fact
     * @return
     */
    public String buscaValorIvaServXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("1");
            function.adicionarNumeric("3");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }
    
    public String buscaValorIvaProdXFact_Fact(String fact_fact) {
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONSLUTA_COSTS_FACT");
            function.adicionarNumeric(fact_fact);
            function.adicionarNumeric("2");
            function.adicionarNumeric("3");
            String rtaPg = function.llamarFunctionFormatoPesos(function.getSql());
            function.recuperarString();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaValorTotalXFact_Fact " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

    public String apruebaFactura(String fact_fact) {
        String r = "";
        String rta = "";
        EnvioFunction function = new EnvioFunction();
        try {
            function.adicionarNombre("FA_CONFIRMA_FACTURA");
            function.adicionarNumeric(fact_fact.trim());
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.apruebaFactura " + e);
        }finally{
            function.cerrarConexion();
        }
        return r;
    }

    /**
     * Funcion la cual se encargara de recuperar una lista de Facturas
     * dependiendo los filtros ingresados
     *
     * @param cedulaCliente
     * @param factura
     * @return
     */
    public List buscaFacturasXFiltro(String cedulaCliente, String factura) {
        List<Factura> r = null;
        String sql = "";
        ValidaCampos valida = new ValidaCampos();
        EnvioFunction function = null;
        ResultSet rs = null;
        int cont = 0;
        try {
            function = new EnvioFunction();
            sql = "SELECT fact_fact\n";
            sql += "FROM fa_tfact\n";
            sql += "where 1=1\n";
            if (valida.validaNulo(cedulaCliente.trim())) {
                sql += "and exists(select 'S' from us_tpers, us_tclien where  fact_clien = clien_clien and clien_pers = pers_pers and pers_cedula like '%" + cedulaCliente + "%')\n";
            }
            if (valida.validaNulo(factura.trim())) {
                sql += "and cast(fact_fact as varchar) like '%" + factura + "%'\n";
            }
            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (cont == 0) {
                    r = new ArrayList<Factura>();
                    cont++;
                }
                Factura aux = recuperaFacturaXId(rs.getString("fact_fact").trim());
                r.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.buscaFacturasXFiltro " + e);
        } finally {
            function.cerrarConexion();
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        return r;
    }

    public String inactivarServiciosPorId(String fact_fact, String dtsv_dtsv) {
        String rta = "";
        EnvioFunction function = null;
        try {
            function = new EnvioFunction();
            function.adicionarNombre("FA_ELIMINA_SERVICIOSXDTSV");
            function.adicionarNumeric(fact_fact.trim());
            function.adicionarNumeric(dtsv_dtsv.trim());
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.inactivarServiciosPorId " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }
    
    public String inactivarProductosPorId(String fact_fact, String dtpr_dtpr,String tius_tius) {
        String rta = "";
        EnvioFunction function = null;
        try {
            function = new EnvioFunction();
            function.adicionarNombre("FA_ELIMINA_PRODUCTOSXDTPR");
            function.adicionarNumeric(fact_fact.trim());
            function.adicionarNumeric(dtpr_dtpr.trim());
            function.adicionarNumeric(tius_tius.trim());
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                return rtaFunc;
            } else {
                return "Error";
            }
        } catch (Exception e) {
            System.out.println("Error Fac_FacturacionLogica.inactivarServiciosPorId " + e);
        } finally {
            function.cerrarConexion();
        }
        return rta;
    }

}
