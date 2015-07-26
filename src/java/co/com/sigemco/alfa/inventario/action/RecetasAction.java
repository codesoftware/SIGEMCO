/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;
import co.com.sigemco.alfa.inventario.dto.PrecioSedeRecetaDto;
import co.com.sigemco.alfa.inventario.dto.ProductoRecetaDto;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import co.com.sigemco.alfa.inventario.logica.PantallaPrincipalLogica;
import co.com.sigemco.alfa.inventario.logica.ProductoRecetaLogica;
import co.com.sigemco.alfa.inventario.logica.RecetaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class RecetasAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private RecetaDto receta;
    private List recetas;
    private String permisoAct;
    private String permisoPrecio;
    private String permisoAddProd;
    private Map<String, String> estadoMap;
    private Map<String, String> sedes;
    private ArrayList<PrecioSedeRecetaDto> histPrRecetas;
    private ArrayList<ProductoRecetaDto> productosReceta;
    private String tipoProducto;
    private String posicion;
    private ArrayList<PantallaPrincipalDto> productosPantalla;
    private ArrayList<PantallaPrincipalDto> recetasPantalla;

    /**
     * Funcion encargada de realizar la accion de insertar una receta en el
     * sistema
     *
     * @return
     */
    public String insertarReceta() {
        RecetaLogica logica = new RecetaLogica();
        try {
            String valida = logica.insertaReceta(receta);
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("La receta " + receta.getRece_nombre().toUpperCase() + " fue insertada correctamente");
                receta = null;
            } else {
                addActionError(valida);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de buscar las recetas teniendo en
     * cuenta filtros
     *
     * @return
     */
    public String consultaRecetasXFiltro() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            this.recetas = objLogica.consultaGeneralXFiltros(receta);
            if (recetas == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                if (usuario.getPermisos().indexOf(".InRec3.") > 0) {
                    permisoAct = "S";
                } else {
                    permisoAct = "N";
                }
                if (usuario.getPermisos().indexOf(".InRec4.") > 0) {
                    permisoPrecio = "S";
                } else {
                    permisoPrecio = "N";

                }
                if (usuario.getPermisos().indexOf(".InRec5.") > 0) {
                    permisoAddProd = "S";
                } else {
                    permisoAddProd = "N";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de consultar la receta por su Id
     *
     * @return
     */
    public String consultaRecetasXId() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            receta = objLogica.consultaRecetaXId(receta);
            if (receta == null) {
                addActionError("No se encontro ninguna receta con ese id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de actualizar una receta
     *
     * @return
     */
    public String actualizaReceta() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            String valida = objLogica.actualizaReceta(receta);
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("La receta se ha actualizado correctamente");
                receta = null;
            } else {
                addActionError(valida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de buscar una receta por su
     * codigo
     *
     * @return
     */
    public String buscaRecetaXCod() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
            receta = objLogica.consultaGeneralXCodigo(receta);
            if (receta == null) {
                addActionError("No existe ninguna receta con ese codigo");
                return ERROR;
            } else {
                RecetaLogica objLogicaRece = new RecetaLogica();
                this.histPrRecetas = objLogicaRece.buscaPreciosSedeRecetas(receta.getRece_rece());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la busqueda de una receta por id
     *
     * @return
     */
    public String buscaRecetaXId() {
        RecetaLogica objLogica = new RecetaLogica();
        try {
            this.receta = objLogica.consultaRecetaXId(receta);
            if (receta == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                ProductoRecetaLogica objLoRece = new ProductoRecetaLogica();
                this.productosReceta = objLoRece.obtienePrdoctosReceta(receta.getRece_rece());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de parametrizar el precio de una
     * receta
     *
     * @return
     */
    public String parametrizaPrecioReceta() {
        RecetaLogica logica = new RecetaLogica();
        try {
            String valida = logica.actualizaPrecioReceta(receta, usuario.getIdTius());
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("El precio de " + receta.getRece_nombre() + " fue parametrizada correctamente");
            } else {
                addActionError(valida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la adicion de productos o recetas a la
     * pantalla principal de facturacion
     *
     * @return
     */
    public String adicionaPantallaPrincipal() {
        try {
            PantallaPrincipalLogica objLogica = new PantallaPrincipalLogica();
            String valida = objLogica.guardaProducto(receta.getRece_codigo(), tipoProducto, Integer.parseInt(posicion));
            if ("Ok".equalsIgnoreCase(valida)) {
                addActionMessage("Producto adicionado correctamente");
                productosPantalla = objLogica.buscaProductos();
                recetasPantalla = objLogica.buscaRecetas();
            } else {
                productosPantalla = objLogica.buscaProductos();
                recetasPantalla = objLogica.buscaRecetas();
                addActionError(valida);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error " + e);
        }
        return SUCCESS;
    }

    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if ("insertReceta".equalsIgnoreCase(accion)) {
            if (!valida.validaNulo(receta.getRece_nombre())) {
                addActionError("El campo nombre no puede ser nulo");
            } else if (!valida.validaNulo(receta.getRece_desc())) {
                addActionError("El campo descripcion no puede ser nulo");
            }
        } else if ("consultaActuliza".equalsIgnoreCase(accion)) {
            this.estadoMap = new HashMap<String, String>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            if (!valida.validaNulo(receta.getRece_rece())) {
                addActionError("El Id de la receta no puede ser nulo");
            }
        } else if ("actualizaReceta".equalsIgnoreCase(accion)) {
            this.estadoMap = new HashMap<String, String>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            if (!valida.validaNulo(receta.getRece_nombre())) {
                addActionError("El campo nombre no puede ser nulo");
            } else if (!valida.validaNulo(receta.getRece_desc())) {
                addActionError("El campo descripcion no puede ser nulo");
            } else if ("-1".equalsIgnoreCase(receta.getRece_rece())) {
                addActionError("Por Favor seleccione un estado para la receta");
            }
        } else if ("parametrizaPrecioReceta".equalsIgnoreCase(accion)) {
            String auxPrecio = receta.getRece_precio();
            String auxSede = receta.getRece_sede();
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
            RecetaLogica objLogica = new RecetaLogica();
            receta = objLogica.consultaGeneralXCodigo(receta);
            receta.setRece_sede(auxSede);
            receta.setRece_precio(auxPrecio);
            if (!valida.validaNulo(receta.getRece_precio())) {
                addActionError("El precio no puede ser nulo");
            } else if ("-1".equalsIgnoreCase(receta.getRece_sede())) {
                addActionError("Por Favor seleccione la sede en la cual va ha parametrizar el precio");
            }
        } else if ("addPantallaPrincipal".equalsIgnoreCase(accion)) {
            if (!valida.validaNulo(receta.getRece_codigo())) {
                addActionError("El codigo no puede ser nulo");
            } else if (!valida.validaNulo(posicion)) {
                addActionError("La posicion no puede ser nulo");
            }

        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public RecetaDto getReceta() {
        return receta;
    }

    public void setReceta(RecetaDto receta) {
        this.receta = receta;
    }

    public List getRecetas() {
        return recetas;
    }

    public void setRecetas(List recetas) {
        this.recetas = recetas;
    }

    public String getPermisoAct() {
        return permisoAct;
    }

    public void setPermisoAct(String permisoAct) {
        this.permisoAct = permisoAct;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public ArrayList<PrecioSedeRecetaDto> getHistPrRecetas() {
        return histPrRecetas;
    }

    public void setHistPrRecetas(ArrayList<PrecioSedeRecetaDto> histPrRecetas) {
        this.histPrRecetas = histPrRecetas;
    }

    public String getPermisoPrecio() {
        return permisoPrecio;
    }

    public void setPermisoPrecio(String permisoPrecio) {
        this.permisoPrecio = permisoPrecio;
    }

    public String getPermisoAddProd() {
        return permisoAddProd;
    }

    public void setPermisoAddProd(String permisoAddProd) {
        this.permisoAddProd = permisoAddProd;
    }

    public ArrayList<ProductoRecetaDto> getProductosReceta() {
        return productosReceta;
    }

    public void setProductosReceta(ArrayList<ProductoRecetaDto> productosReceta) {
        this.productosReceta = productosReceta;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public ArrayList<PantallaPrincipalDto> getProductosPantalla() {
        return productosPantalla;
    }

    public void setProductosPantalla(ArrayList<PantallaPrincipalDto> productosPantalla) {
        this.productosPantalla = productosPantalla;
    }

    public ArrayList<PantallaPrincipalDto> getRecetasPantalla() {
        return recetasPantalla;
    }

    public void setRecetasPantalla(ArrayList<PantallaPrincipalDto> recetasPantalla) {
        this.recetasPantalla = recetasPantalla;
    }

}
