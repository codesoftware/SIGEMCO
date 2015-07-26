/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.PantallaPrincipalDao;
import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author nicolas
 */
public class PantallaPrincipalLogica {
    private String extencion;

    /**
     * Funcion encargada de realizar la logica para guardar un producto en la
     * tabla de lista
     *
     * @param codigo
     * @param tipo
     * @param posicion
     * @return
     */
    public String guardaProducto(String codigo, String tipo, int posicion, String ruta) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            PantallaPrincipalDto objDto = new PantallaPrincipalDto();
            objDto.setPpfa_codigo(codigo);
            objDto.setPpfa_posicion("" + posicion);
            objDto.setPpfa_ruta_img(ruta);
            objDto.setPpfa_extencion(this.extencion);
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
                aux.setPpfa_ruta_img(rs.getString("ppga_ruta_img"));
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
                aux.setPpfa_ruta_img(rs.getString("ppga_ruta_img"));
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

    public String guardaImagen(PantallaPrincipalDto objDto, String tipoProducto) {
        String rta = "";
        try {
            String ruta = this.obtieneRutaBase();
            this.extencion = this.buscaExtencionImagen(objDto.getImagenFileName());
            if (ruta != null) {
                ruta = this.verificaCarpetaImagen(ruta);
                rta = ruta +"/"+ objDto.getPpfa_codigo()+"_"+tipoProducto + ".jpeg";
                InputStream fis = new FileInputStream(objDto.getImagen());
                OutputStream fos = new FileOutputStream(new File(rta));
                int c ;
                while((c = fis.read()) != -1){
                    fos.write(c);
                }
                fis.close();
                fos.close();
            } else {
                rta = "Error al buscar la ruta base de las imagenes";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    public String obtieneRutaBase() {
        ResourceBundle rb = ResourceBundle.getBundle("co.com.sigemco.alfa.archivos.BASECONFIG");
        String ruta;
        ruta = rb.getString("IMAGENES");
        if ("".equalsIgnoreCase(ruta) && ruta == null) {
            return null;
        } else {
            return ruta;
        }
    }
    
    public String verificaCarpetaImagen(String ruta){
        String rutaFinal = ruta+"/pantallaPrincipal";
        File file = new File(rutaFinal);
        if(!file.exists()){
            file.mkdirs();
        }
        return rutaFinal;
    }
    
    public String buscaExtencionImagen(String imagen){
        String extension;
        String []ext = imagen.split("\\.");
        extension = ext[1];
        return extension;
    }
   
}
