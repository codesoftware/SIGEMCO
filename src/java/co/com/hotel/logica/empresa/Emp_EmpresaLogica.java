/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.empresa;

import co.com.hotel.dto.Empresa;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;

/**
 *
 * @author nicolas
 */
public class Emp_EmpresaLogica {

    /**
     * Funcion encargada de realizar la logica para parametrizar los parametros
     * generales de la empresa
     *
     * @param empresa
     * @return
     */
    public String ingresarParametrosPrincempresa(Empresa empresa) {
        String rta = "";
        String inserts = "";
        try {
            inserts = this.ingresaNombreEmpresa(empresa.getNombre());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaNitEmpresa(empresa.getNit());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaDireccionEmpresa(empresa.getDireccion());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaTelefonoEmpresa(empresa.getTelefono());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaCiudadEmpresa(empresa.getCiudad());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            rta = "Ok";
        } catch (Exception e) {
            System.out.println("Error Emp_EmpresaLogica.ingresarParametrosPrincempresa " + e);
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para ingresar los parametros
     * Generales de la empresa
     *
     * @param empresa
     * @return
     */
    public String ingresarParametrosGeneEmpresa(Empresa empresa) {
        String rta = "";
        String inserts = "";
        try {
            inserts = this.ingresaIvaEmpresa(empresa.getIva());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaDiasVencimiento(empresa.getDiasVen());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }
            inserts = this.ingresaComision(empresa.getComision());
            if (!inserts.equalsIgnoreCase("Ok")) {
                return inserts;
            }

            rta = "Ok";
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion la cual se encarga de ingresa el nombre de la empresa en la base
     * de datos
     *
     * @param nomEmpresa
     * @return
     */
    private String ingresaNombreEmpresa(String nomEmpresa) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador                \n";
            select += "from em_tpara                            \n";
            select += "where upper(para_clave) = 'NOMBREEMPRESA' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('NOMBREEMPRESA', '" + nomEmpresa + "')   \n";

            } else {
                sql = "UPDATE em_tpara                          \n";
                sql += "SET para_valor = '" + nomEmpresa + "'         \n";
                sql += "WHERE upper(para_clave) = 'NOMBREEMPRESA'\n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    private String ingresaNitEmpresa(String nitEmpresa) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador       \n";
            select += "from em_tpara                   \n";
            select += "where upper(para_clave) = 'NIT' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('NIT', '" + nitEmpresa + "')   \n";

            } else {
                sql = "UPDATE em_tpara                          \n";
                sql += "SET para_valor = '" + nitEmpresa + "'         \n";
                sql += "WHERE upper(para_clave) = 'NIT'\n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    private String ingresaDireccionEmpresa(String dirEmpresa) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador              \n";
            select += "from em_tpara                          \n";
            select += "where upper(para_clave) = 'DIRECCION' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('DIRECCION', '" + dirEmpresa + "')   \n";

            } else {
                sql = "UPDATE em_tpara                          \n";
                sql += "SET para_valor = '" + dirEmpresa + "'         \n";
                sql += "WHERE upper(para_clave) = 'DIRECCION'\n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    private String ingresaTelefonoEmpresa(String telEmpresa) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador                \n";
            select += "from em_tpara                            \n";
            select += "where upper(para_clave) = 'TELEFONOS'    \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('TELEFONOS', '" + telEmpresa + "')   \n";

            } else {
                sql = "UPDATE em_tpara                          \n";
                sql += "SET para_valor = '" + telEmpresa + "'         \n";
                sql += "WHERE upper(para_clave) = 'TELEFONOS'\n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    private String ingresaCiudadEmpresa(String ciuEmpresa) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador              \n";
            select += "from em_tpara                          \n";
            select += "where upper(para_clave) = 'CIUDAD' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('CIUDAD', '" + ciuEmpresa + "')   \n";

            } else {
                sql = "UPDATE em_tpara                     \n";
                sql += "SET para_valor = '" + ciuEmpresa + "'  \n";
                sql += "WHERE upper(para_clave) = 'CIUDAD' \n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    /**
     * Funcion encargada de ingresar el iva que cobrara la empresa
     *
     * @param iva
     * @return
     */
    private String ingresaIvaEmpresa(String iva) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador              \n";
            select += "from em_tpara                          \n";
            select += "where upper(para_clave) = 'IVAPR' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('IVAPR', '" + iva + "')   \n";

            } else {
                sql = "UPDATE em_tpara                     \n";
                sql += "SET para_valor = '" + iva + "'  \n";
                sql += "WHERE upper(para_clave) = 'IVAPR' \n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    /**
     * Funcion encargada de ingresar a la base de datos los dias de Vencimiento
     * para iniciar a enviar notificaciones de vencimiento
     *
     * @param diasVen
     * @return
     */
    private String ingresaDiasVencimiento(String diasVen) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador              \n";
            select += "from em_tpara                          \n";
            select += "where upper(para_clave) = 'DIASVEN' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('DIASVEN', '" + diasVen + "')   \n";

            } else {
                sql = "UPDATE em_tpara                     \n";
                sql += "SET para_valor = '" + diasVen + "'  \n";
                sql += "WHERE upper(para_clave) = 'DIASVEN' \n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    /**
     * Funcion encargada de insertar o modificar la comision que se cobrara por
     * los equipos celulares
     *
     * @param comision
     * @return
     */
    private String ingresaComision(String comision) {
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String select = "";
        String sql = "";
        int cont = 0;
        try {
            select += "select COUNT(*)  contador            \n";
            select += "from em_tpara                        \n";
            select += "where upper(para_clave) = 'COMISION' \n";
            rs = function.enviarSelect(select);
            while (rs.next()) {
                cont = rs.getInt("contador");
            }
            if (cont == 0) {
                sql = "insert into em_tpara(para_clave, para_valor) \n";
                sql += "values('COMISION', '" + comision + "')   \n";

            } else {
                sql = "UPDATE em_tpara                     \n";
                sql += "SET para_valor = '" + comision + "'  \n";
                sql += "WHERE upper(para_clave) = 'COMISION' \n";
            }
            function.enviarUpdate(sql);
        } catch (Exception e) {
            System.err.println("Error Emp_EmpresaLogica.ingresaNombreEmpresa");
            e.printStackTrace();
            return "Error al insertar el nombre de la empresa: " + e;
        } finally {
            function.cerrarConexion();
        }
        return "Ok";
    }

    /**
     * Funcion que obtiene todos los datos empresariales parametrizados
     * previamente en la aplicacion
     *
     * @return
     */
    public Empresa obtieneDatosEmpresa() {
        Empresa obj = null;
        EnvioFunction function = new EnvioFunction();
        ResultSet rs = null;
        String sql = "";
        int contador = 0;
        try {
            sql = "SELECT (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'NIT') NIT,               ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'NOMBREEMPRESA') NOMBRE, ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'TELEFONOS') TELEFONOS,  ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'DIRECCION') DIRECCION,  ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'CIUDAD') CIUDAD      ,  ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'DIASVEN') DIASVEN    ,  ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'IVAPR') IVAPR        ,  ";
            sql += "       (SELECT para_valor FROM em_tpara where UPPER(para_clave) = 'COMISION') COMISION     ";

            rs = function.enviarSelect(sql);
            while (rs.next()) {
                if (contador == 0) {
                    obj = new Empresa();
                    contador++;
                }
                obj.setNit(rs.getString("nit"));
                obj.setNombre(rs.getString("nombre"));
                obj.setTelefono(rs.getString("telefonos"));
                obj.setDireccion(rs.getString("direccion"));
                obj.setCiudad(rs.getString("ciudad"));
                obj.setDiasVen(rs.getString("diasven"));
                obj.setIva(rs.getString("ivapr"));
                obj.setComision(rs.getString("comision"));
            }
        } catch (Exception e) {
            System.out.println("Error Emp_EmpresaLogica.obtieneDatosEmpresa " + e);
            e.printStackTrace();
        } finally {
            function.cerrarConexion();
            rs = null;
        }
        return obj;
    }

}
