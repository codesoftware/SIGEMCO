/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.logica.productos;

import co.com.hotel.dto.Producto;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.utilidades.ManejoString;

/**
 * Clase encargada de realizar todas las transacciones con la base de datos
 * relacionados con el inventario de productos
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class IngresaProductoNuevo {
    
    public String IngresaProducto(Producto producto, String usuario){
        try{
            //ManejoString manejo = new ManejoString();
            //String fechaNueva = manejo.convertirFormatoFechas(producto.getFechaVencimiento(), "mm/dd/yyyy", "/");
            //producto.setFechaVencimiento(fechaNueva);
            EnvioFunction function = new EnvioFunction();
            function.adicionarNombre("US_FINSERT_NUEVO_PROD");
            function.adicionarParametro(producto.getReferencia());
            //function.adicionarParametro(producto.getCodigo());
            function.adicionarNull();
            function.adicionarParametro(producto.getNombre());
            function.adicionarParametro(producto.getDescripcion());
            function.adicionarParametro(producto.getIva());
            function.adicionarNumeric(producto.getPorcIva());
            function.adicionarParametro(producto.getMarca());
            function.adicionarNumeric(producto.getCantidad());
            function.adicionarNumeric(producto.getCosto());
            function.adicionarParametro(usuario); 
            function.adicionarNumeric(producto.getSede());
            function.adicionarNumeric(producto.getCategoria()); 
            if(producto.getRegistroUnico() == null){
                function.adicionarNull();
            }else{
                function.adicionarNull();
                //function.adicionarParametro(producto.getRegistroUnico());
            }
            
            if(producto.getFechaVencimiento() == null){
                function.adicionarNull();
            }else{
                function.adicionarNull();
                //function.addicionarParametroDate(producto.getFechaVencimiento()); Se elimina ya que no es necesaria la funcionalidad
            }
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            function.cerrarConexion();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return rtaPg;
                }
            }            
        }catch(Exception e){
            e.printStackTrace();
            return "Error IngresaProductoNuevo.IngresaProducto " +e;
        }
        return "Error al llamar funcion de Base de datos";
    }

}
