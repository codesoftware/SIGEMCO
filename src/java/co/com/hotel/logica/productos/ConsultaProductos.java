/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.productos;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Producto;
import co.com.hotel.persistencia.general.EnvioFunction;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class ConsultaProductos {

    private ArrayList<Producto> codResult = null;

    public boolean buscaCodigoPr(String filtro) {
        try {
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FCONSULTA_CODIGO");
            function.adicionarParametro(filtro);
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarStringVector();
            function.cerrarConexion();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    String productos[] = function.getRespuestaVector();
                    if (productos.length == 0 || productos == null) {
                        return false;
                    } else {
                        boolean valida = cargarCodProductos(productos[0]);
                        if(valida){
                            return true;
                        }else{
                            return false;                           
                        }                        
                    }
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar coincidencias en los codigos: " + e);
            return false;
        }
        return true;
    }
    
    
    public boolean cargarCodProductos(String arrayProd) {
        try {
            String []productos = arrayProd.split(",");
            int tam = productos.length;
            this.codResult = new ArrayList<Producto>();            
            for (int i = 0; i < tam; i++) {
                Producto prod = new Producto();
                prod.setCodigo(productos[i]);
                this.codResult.add(prod);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Producto> getCodResult() {
        return codResult;
    }

    public void setCodResult(ArrayList<Producto> codResult) {
        this.codResult = codResult;
    }
    
    public Producto buscaProductoIndividual(String codigo){
        Producto producto = null;
        EnvioFunction function = new EnvioFunction();
        String sql = "";
        ResultSet rs = null;
        try {
            sql = "SELECT dska_dska id ,dska_nom_prod nombre, dska_desc descripcion, dska_marca marca, dska_cod codigo\n";
            sql += "FROM in_tdska\n";
            sql += "WHERE upper(trim(dska_cod)) like upper(trim('%" + codigo + "%'))";
            rs = function.enviarSelect(sql);
            if(rs.next()){
                producto = new Producto();
                producto.setId(rs.getString("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setMarca(rs.getString("marca"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setNombre(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error ConsultaProductos.buscaProductoIndividual " + e);
        }finally{
            function.cerrarConexion();
            function = null;
            rs= null;
        }
        return producto;
    }
}
