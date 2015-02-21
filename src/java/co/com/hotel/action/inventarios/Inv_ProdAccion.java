/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.inventarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.AddProdExistentes;
import co.com.hotel.dto.Producto;
import co.com.hotel.logica.movInventario.Inv_MovInLogica;
import co.com.hotel.logica.productos.ConsultaProductos;
import co.com.hotel.logica.productos.Inv_ProductoLogica;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author SOFIA
 */
public class Inv_ProdAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private String accion;
    private Producto producto;
    private String bandera;
    private String permisoParam;
    private Map<String, String> movInventarios;
    private AddProdExistentes addicionProd;
    private ArrayList<Producto> rtaConsProd;
    private String subAccion;
    private Map<String, String> gravamen;
    private Map<String, String> yesNo;
    private Map<String, String> sedes;

    /**
     * Consulta de producto la cual se realiza con el codigo unico del producto
     * para poder agregar productos al inventario de un producto en especifico
     *
     * @return
     */
    @SkipValidation
    public String consultaIndividual() {
        Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
        this.sedes = sedeLogica.obtieneSedes();
        ConsultaProductos logica = new ConsultaProductos();
        try {
            producto = logica.buscaProductoIndividual(producto.getCodigo());
            if (producto == null) {
                addActionError("No se encontraron productos con el codigo ingresado");
                bandera = "S";
            } else {
                Inv_MovInLogica mov = new Inv_MovInLogica();
                movInventarios = mov.obtineMovimientosInv();
                bandera = "N";
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProdAccion.consultaIndividual " + e);
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    /**
     * Funcion la cual ingresa productos existententes al inventario
     *
     * @return
     */
    public String addProdExisInv() {
        addicionProd.setIdProd(producto.getId());
        Inv_ProductoLogica logica = new Inv_ProductoLogica();
        String rta = logica.ingresaProductoExistente(addicionProd, usuario.getIdTius());
        String[] vectorRta = rta.split("-");
        if (vectorRta[0].equalsIgnoreCase("Ok")) {
            reset();
            bandera = "S";
            addActionMessage("Producto adicionado correctamente");
        } else {
            addActionError("Error al adicionar productos al inventario");

        }
        return SUCCESS;
    }

    /**
     * Funcion la cual busca un unico producto por medio de tres filtros Nom
     *
     * @return
     */
    public String consultaProdXFiltroIndv() {
        Inv_ProductoLogica logica = new Inv_ProductoLogica();
        ArrayList<Producto> r = logica.buscaProductosXFiltro(producto);
        clearErrorsAndMessages();
        try {
            if (r == null) {
                addActionError("La consulta no arrojo ningun resultado");
                bandera = "N";
            } else {
                producto = r.get(0);
                String aux = logica.buscaCanProdExistenXId(producto.getId());
                producto.setCantidad(aux);
                bandera = "S";
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProdAccion.consultaProdXFiltroIndv " + e);
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    @SkipValidation
    public String consultaProdXFiltrosGen() {
        Inv_ProductoLogica logica = new Inv_ProductoLogica();
        rtaConsProd = logica.buscaProductosXFiltro(producto);
        clearErrorsAndMessages();
        try {
            if (rtaConsProd == null) {
                addActionError("La consulta no arrojo ningun resultado o no estan parametrizados los precios de los productos\n");
            } else {
                int acceso = usuario.getPermisos().indexOf(".InPr5.");
                if (acceso >= 0) {
                    bandera = "S";
                } else {
                    bandera = "N";
                }
                acceso = usuario.getPermisos().indexOf(".InPr6.");
                if (acceso >= 0) {
                    permisoParam = "S";
                } else {
                    permisoParam = "N";
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProdAccion.consultaProdXFiltroIndv " + e);
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    public String actualizaProducto() {
        this.yesNo = new HashMap<String, String>();
        this.yesNo.put("S", "Si");
        this.yesNo.put("N", "No");
        Inv_ProductoLogica logica = new Inv_ProductoLogica();
        try {
            if (subAccion.equalsIgnoreCase("consulta")) {
                producto = logica.buscaProductoXId(producto.getId());
                if (producto == null) {
                    bandera = "N";
                } else {
                    bandera = "S";
                }
                logica = null;
            } else if (subAccion.equalsIgnoreCase("update")) {
                String rta = logica.actualizaProducto(producto);
                if (rta.equalsIgnoreCase("Ok")) {
                    addActionMessage("Producto actualizado correctamente");
                } else {
                    addActionError("Error al actualizar el producto");
                }
            } else if (subAccion.equalsIgnoreCase("consultaFiltro")) {
                ArrayList<Producto> r = logica.buscaProductosXFiltro(producto);
                if (r == null) {
                    bandera = "N";
                    addActionError("La consulta no arrojo ningun resultado");
                } else {
                    bandera = "S";
                    producto = r.get(0);
                }
            }
        } catch (Exception e) {
            System.out.println("Error Inv_ProdAccion.actualizaProducto " + e);
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    public void validate() {
        if (accion.equalsIgnoreCase("consIndividual")) {

        }
        if (accion.equalsIgnoreCase("addProductosExistents")) {
            Inv_MovInLogica mov = new Inv_MovInLogica();
            ValidaCampos validaCampos = new ValidaCampos();
            movInventarios = mov.obtineMovimientosInv();
            bandera = "N";
            mov = null;
            if (!validaCampos.validaNulo(addicionProd.getNoProductos())) {
                addFieldError("addicionProd.noProductos", "El campo Numero de productos no puede ser nulo");
            }
            if (addicionProd.getMovInv().equalsIgnoreCase("-1")) {
                addFieldError("addicionProd.movInv", "Por Favor elija un movimiento de inventario");
            }
            if (!validaCampos.validaNulo(addicionProd.getCosto())) {
                addFieldError("addicionProd.costo", "El costo no puede estar vacio");
            }
            if (!validaCampos.validaNumerico(addicionProd.getCosto())) {
                addFieldError("addicionProd.costo", "El campo costo debe ser numerico");
            }
            if (!validaCampos.validaNumerico(addicionProd.getNoProductos())) {
                addFieldError("addicionProd.noProductos", "El campo Numero de productos debe ser numerico");
            }
            validaCampos = null;
        }
        if (accion.equalsIgnoreCase("consultaProdXFiltroIndv")) {
            ValidaCampos validaCampos = new ValidaCampos();
            if (!validaCampos.validaNulo(producto.getCodigo()) && !validaCampos.validaNulo(producto.getNombre()) && !validaCampos.validaNulo(producto.getReferencia())) {
                addActionError("LOS TRES CAMPOS NO PUEDEN SER NULOS");
            }
            validaCampos = null;
        }
        //Accion en la cual se quiere actulizar un producto
        if (accion.equalsIgnoreCase("updProductoIndv")) {
            ValidaCampos validaCampos = new ValidaCampos();
            this.yesNo = new HashMap<String, String>();
            this.yesNo.put("S", "Si");
            this.yesNo.put("N", "No");
            if (subAccion.equalsIgnoreCase("update")) {
                if (!validaCampos.validaNulo(producto.getNombre().trim())) {
                    addFieldError("producto.nombre", "El campo no puede ser nulo");
                }
                if (!validaCampos.validaNulo(producto.getDescripcion().trim())) {
                    addFieldError("producto.descripcion", "El campo no puede ser nulo");
                }
                if (!validaCampos.validaNulo(producto.getReferencia().trim())) {
                    addFieldError("producto.referencia", "El campo no puede ser nulo");
                }
                if (!validaCampos.validaNulo(producto.getMarca())) {
                    addFieldError("producto.marca", "El campo no puede ser nulo");
                }
                if (!validaCampos.validaNumerico(producto.getPorcIva())) {
                    addFieldError("producto.porcIva", "El campo debe ser numerico");
                }
                if (producto.getIva() == "-1") {
                    addFieldError("producto.iva", "Por favor seleccione una de las opciones");
                }
            }
            validaCampos = null;
        }
    }

    public void reset() {
        addicionProd.setCosto("");
        addicionProd.setIdProd("");
        addicionProd.setNatuMov("");
        addicionProd.setNoProductos("");
        producto.setCodigo("");
        producto.setNombre("");
        producto.setMarca("");
        producto.setDescripcion("");
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public Map<String, String> getMovInventarios() {
        return movInventarios;
    }

    public void setMovInventarios(Map<String, String> movInventarios) {
        this.movInventarios = movInventarios;
    }

    public AddProdExistentes getAddicionProd() {
        return addicionProd;
    }

    public void setAddicionProd(AddProdExistentes addicionProd) {
        this.addicionProd = addicionProd;
    }

    public ArrayList<Producto> getRtaConsProd() {
        return rtaConsProd;
    }

    public void setRtaConsProd(ArrayList<Producto> rtaConsProd) {
        this.rtaConsProd = rtaConsProd;
    }

    public String getSubAccion() {
        return subAccion;
    }

    public void setSubAccion(String subAccion) {
        this.subAccion = subAccion;
    }

    public Map<String, String> getGravamen() {
        return gravamen;
    }

    public void setGravamen(Map<String, String> gravamen) {
        this.gravamen = gravamen;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public String getPermisoParam() {
        return permisoParam;
    }

    public void setPermisoParam(String permisoParam) {
        this.permisoParam = permisoParam;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }
}
