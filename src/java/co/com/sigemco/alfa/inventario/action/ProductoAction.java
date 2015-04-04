/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.action;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.AddProdExistentes;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.sigemco.alfa.contabilidad.logica.MoviContablesLogica;
import co.com.sigemco.alfa.inventario.dto.MoviInventarioDto;
import co.com.sigemco.alfa.inventario.dto.ProductoDto;
import co.com.sigemco.alfa.inventario.logica.MoviInventarioLogica;
import co.com.sigemco.alfa.inventario.logica.ProductoLogica;
import co.com.sigemco.alfa.inventario.logica.ReferenciaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones de los productos en el sistema
 *
 * @author Nicolas
 */
public class ProductoAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private List<ProductoDto> listProductos;
    private Map<String, String> sedes;
    private ProductoDto producto;
    private String perActualizar;   //Indica si el usuario que realiza determinada accion tiene permiso de actualizar el producto
    private String perParamPrecio;  //Indica si el usuario que realiza determinada accion tiene permiso de parametrizar el precio
    private String accion;
    private String bandera;
    private AddProdExistentes addicionProd;
    private MoviInventarioDto moviInventario;
    private ArrayList<String> ArrayAddSubCuentas;
    private String idTrans = null;
    private Map<String, String> referencias;
    private String sedeOrigen;
    private String sedeDestino;

    /**
     * Funcion encargada de realizar la accion de la consulta general por fitros
     * de productos
     *
     * @return
     */
    public String consultaGenXFiltros() {
        ProductoLogica logica = null;
        try {
            logica = new ProductoLogica();
            listProductos = logica.buscaProductosXFiltro(producto);
            if (listProductos == null) {
                addActionError("La consulta no arrojo ningun resultado");
            } else {
                int acceso = usuario.getPermisos().indexOf(".InPr5.");
                if (acceso >= 0) {
                    perActualizar = "S";
                } else {
                    perActualizar = "N";
                }
                acceso = usuario.getPermisos().indexOf(".InPr6.");
                if (acceso >= 0) {
                    perParamPrecio = "S";
                } else {
                    perParamPrecio = "N";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logica = null;
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de la accion de buscar un producto para adicionarlo al
     * inventario
     *
     * @return
     */
    public String buscaProductoAdicion() {
        ProductoLogica logica = null;
        try {
            logica = new ProductoLogica();
            producto = logica.buscaProductoXCodigo(producto.getDska_cod());
            if (producto == null) {
                Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
                this.sedes = sedeLogica.obtieneSedes();
                sedeLogica = null;
                bandera = "S";
                addActionError("Producto Inexistente por favor intente con otro codigo");
            } else {
                MoviInventarioLogica moviLogica = new MoviInventarioLogica();
                Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
                this.sedes = sedeLogica.obtieneSedes();
                this.moviInventario = moviLogica.buscaMoviInventarioCompra();
                bandera = "N";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //bandera = "N";
        return SUCCESS;
    }

    /**
     * Funcion encargade de adicionar productos al inventario
     *
     * @return
     */
    public String adicionaProductosInventario() {
        MoviContablesLogica logicaCont = null;
        try {
            logicaCont = new MoviContablesLogica();
            String idTaransc = logicaCont.insertaSbcuTablaTempo(ArrayAddSubCuentas);
            if (!idTaransc.matches("(.*)Error(.*)")) {
                producto.setTransMvcon(idTaransc);
                ProductoLogica logica = new ProductoLogica();
                String rtaFuncion = logica.adicionaProdInventario(producto, addicionProd, usuario.getIdTius());
                if ("Ok".equalsIgnoreCase(rtaFuncion)) {
                    this.idTrans = producto.getTransMvcon();
                    addActionMessage("Producto Adicionado correctamente");
                    bandera = "S";
                    producto.setDska_cod("");
                } else {
                    Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    sedeLogica = null;
                    bandera = "S";
                    addActionError("Error al insertar el producto msg DB: " + rtaFuncion);
                }
            } else {
                Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
                this.sedes = sedeLogica.obtieneSedes();
                sedeLogica = null;
                bandera = "S";
                addActionError("Error al insertar el producto msg DB: " + idTaransc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de buscar un producto por medio
     * de su codigo para luego cambiar de sedes los productos
     *
     * @return
     */
    public String buscaProductoCambioSede() {
        ProductoLogica logica = null;
        try {
            logica = new ProductoLogica();
            producto = logica.buscaProductoXCodigo(producto.getDska_cod());
            if(producto == null ){
                addActionError("No existe ningun producto con este codigo");
            }else{
                Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
                this.sedes = sedeLogica.obtieneSedes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de validar los cmpos de cada uno de las acciones que
     * realice el usuario
     */
    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        if (accion.equalsIgnoreCase("consultaGen")) {
            ReferenciaLogica refeLogica = new ReferenciaLogica();
            this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
        }
        //Validaciones para cuando se va ha realizar la busqueda para la adicion de productos existentes
        if ("consultaForAddEx".equalsIgnoreCase(accion)) {
            if (!valida.validaNulo(producto.getDska_cod())) {
                bandera = "S";
                addActionError("El campo codigo no puede ser Nulo");

            }
        }
        //Validaciones para cuando se va ha realizar la insercion de productos al inventario
        if ("addProdInventario".equalsIgnoreCase(accion)) {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
            sedeLogica = null;
        }
        //Validacion para la busqueda de producto cuando se va ha cambiar de sede
        if("buscaProdCambioSede".equalsIgnoreCase(accion)){
            if(!valida.validaNulo(producto.getDska_cod())){
                addActionError("El campo codigo no puede ser nulo");
            }
        }
        valida = null;
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

    public List<ProductoDto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<ProductoDto> listProductos) {
        this.listProductos = listProductos;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    public String getPerActualizar() {
        return perActualizar;
    }

    public void setPerActualizar(String perActualizar) {
        this.perActualizar = perActualizar;
    }

    public String getPerParamPrecio() {
        return perParamPrecio;
    }

    public void setPerParamPrecio(String perParamPrecio) {
        this.perParamPrecio = perParamPrecio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public AddProdExistentes getAddicionProd() {
        return addicionProd;
    }

    public void setAddicionProd(AddProdExistentes addicionProd) {
        this.addicionProd = addicionProd;
    }

    public MoviInventarioDto getMoviInventario() {
        return moviInventario;
    }

    public void setMoviInventario(MoviInventarioDto moviInventario) {
        this.moviInventario = moviInventario;
    }

    public ArrayList<String> getArrayAddSubCuentas() {
        return ArrayAddSubCuentas;
    }

    public void setArrayAddSubCuentas(ArrayList<String> ArrayAddSubCuentas) {
        this.ArrayAddSubCuentas = ArrayAddSubCuentas;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public Map<String, String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Map<String, String> referencias) {
        this.referencias = referencias;
    }

    public String getSedeOrigen() {
        return sedeOrigen;
    }

    public void setSedeOrigen(String sedeOrigen) {
        this.sedeOrigen = sedeOrigen;
    }

    public String getSedeDestino() {
        return sedeDestino;
    }

    public void setSedeDestino(String sedeDestino) {
        this.sedeDestino = sedeDestino;
    }    
    
}
