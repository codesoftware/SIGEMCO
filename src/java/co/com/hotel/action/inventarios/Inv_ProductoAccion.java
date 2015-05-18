/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.inventarios;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Empresa;
import co.com.hotel.dto.Producto;
import co.com.hotel.logica.categoria.Inv_CategoriaLogica;
import co.com.hotel.logica.empresa.Emp_EmpresaLogica;
import co.com.hotel.logica.productos.IngresaProductoNuevo;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.hotel.validacion.ValidaDuplicadodosProd;
import co.com.sigemco.alfa.contabilidad.logica.MoviContablesLogica;
import co.com.sigemco.alfa.inventario.logica.MarcaLogica;
import co.com.sigemco.alfa.inventario.logica.ReferenciaLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author SOFIA
 */
public class Inv_ProductoAccion extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Producto producto;
    private List<String> gravamen;
    private Map<String, String> sedes;
    private Map<String, String> categorias;
    private Map<String, String> marcas;
    private Map<String, String> yesNo;
    private ArrayList<String> ArrayAddSubCuentas;
    private String mandaParamePrecio;
    private Map<String, String> referencias;

    /**
     * Funcion encargada de realizar el llamado de la funcion que insertara
     * productos a la base de datos
     *
     * @return
     */
    public String insertar() {
        IngresaProductoNuevo ingreso = null;
        MoviContablesLogica logica = null;
        try {
            logica = new MoviContablesLogica();
            String idTaransc = logica.insertaSbcuTablaTempo(ArrayAddSubCuentas);
            if (!idTaransc.matches("(.*)Error(.*)")) {
                producto.setIdTranMvCo(idTaransc);
                ingreso = new IngresaProductoNuevo();
                String rta = ingreso.IngresaProducto(producto, usuario.getUsuario());
                String[] aux = rta.split("&");
                if (aux[0].equalsIgnoreCase("Ok")) {
                    mandaParamePrecio = "S";
                    limpiarProducto();
                    String codProd = aux[1];
                    producto.setCodigo(codProd);
                    producto.setIdTranMvCo(idTaransc);
                    addActionMessage("Producto ingresado correctamente");
                } else {
                    addActionError("Lamentablemente el producto no pudo ser ingresado por el siguiente error" + rta);
                }
            } else {
                addActionError(idTaransc);

            }
        } catch (Exception e) {
            addActionError("Error al ingresar el producto");
        }
        return SUCCESS;
    }

    public void validate() {
        Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
        this.sedes = sedeLogica.obtieneSedes();
        Inv_CategoriaLogica cateLogica = new Inv_CategoriaLogica();
        this.categorias = cateLogica.obtieneCategorias();
        ReferenciaLogica refeLogica = new ReferenciaLogica();
        this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
        ValidaCampos valida = new ValidaCampos();
        MarcaLogica logicaMarca = new MarcaLogica();
        this.marcas = logicaMarca.obtieneMarcas();
        ValidaDuplicadodosProd duplicados = new ValidaDuplicadodosProd();
        Emp_EmpresaLogica logicaEmp = new Emp_EmpresaLogica();
        Empresa empresa = logicaEmp.obtieneDatosEmpresa();
        producto.setPorcIva(empresa.getIva());
        empresa = null;
        boolean rtaValida = false;
        //rtaValida = valida.validaNulo(producto.getNombre());
        //if (rtaValida == false) {
            //addActionError("El campo nombre no puede ser nulo");
        //}
//        rtaValida = valida.validaNulo(producto.getDescripcion());
//        if (rtaValida == false) {
//            addActionError("El campo descripcion no puede ser nulo");
//        }
        rtaValida = valida.validaFloat("" + producto.getPorcIva());
        if (rtaValida == false) {
            addActionError("El campo Porcentaje Iva debe ser numerico o los decimales deben ser con .");
        }
        rtaValida = valida.validaFloat(producto.getCosto());
        if (rtaValida == false) {
            addActionError("El campo costo debe ser numerico o los decimales deben ser con .");
        }
        if("-1".equalsIgnoreCase(producto.getMarca())){
            addActionError("Debe seleccionar una marca para su producto");
        }
        rtaValida = valida.validaNumerico(producto.getCantidad());
        if (rtaValida == false) {
            addActionError("El campo cantidad debe ser numerico");
        } else if (producto.getIva().equalsIgnoreCase("-1")) {
            addActionError("Seleccione una de las opciones de la lista Gravamen");
        }
//        boolean dup = duplicados.verificaCodigo(producto.getCodigo());
//        if (dup == false) {
//            addActionError("El codigo ingresado ya esta referenciado a otro producto");
//        } else if (producto.getSede().equalsIgnoreCase("-1")) {
//            addActionError("Por Favor seleccione la sede a la cual ira el producto");
//        } else if (producto.getCategoria().equalsIgnoreCase("-1")) {
//            addActionError("Por Favor seleccione la categoria a la cual pertenece el producto");
//        }
        valida = null;
    }

    public void limpiarProducto() {
        producto.setReferencia("");
        producto.setCodigo("");
        producto.setNombre("");
        producto.setDescripcion("");
        producto.setIva("");
        producto.setPorcIva("");
        producto.setMarca("");
        producto.setCantidad("");
        producto.setCosto("");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.gravamen = new ArrayList<String>();
        this.gravamen.add("SI_grava_Iva");
        this.gravamen.add("No_grava_Iva");
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<String> getGravamen() {
        return gravamen;
    }

    public void setGravamen(List<String> gravamen) {
        this.gravamen = gravamen;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public Map<String, String> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<String, String> categorias) {
        this.categorias = categorias;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public ArrayList<String> getArrayAddSubCuentas() {
        return ArrayAddSubCuentas;
    }

    public void setArrayAddSubCuentas(ArrayList<String> ArrayAddSubCuentas) {
        this.ArrayAddSubCuentas = ArrayAddSubCuentas;
    }

    public String getMandaParamePrecio() {
        return mandaParamePrecio;
    }

    public void setMandaParamePrecio(String mandaParamePrecio) {
        this.mandaParamePrecio = mandaParamePrecio;
    }

    public Map<String, String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Map<String, String> referencias) {
        this.referencias = referencias;
    }

    public Map<String, String> getMarcas() {
        return marcas;
    }

    public void setMarcas(Map<String, String> marcas) {
        this.marcas = marcas;
    }

}
