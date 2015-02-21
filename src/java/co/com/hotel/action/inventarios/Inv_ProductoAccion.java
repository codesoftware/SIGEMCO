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
    private Map<String, String> yesNo;

    /**
     * Funcion encargada de realizar el llamado de la funcion que insertara
     * productos a la base de datos
     *
     * @return
     */
    public String insertar() {
        IngresaProductoNuevo ingreso = null;
        try {
            ingreso = new IngresaProductoNuevo();
            String rta = ingreso.IngresaProducto(producto, usuario.getUsuario());
            if (rta.equalsIgnoreCase("Ok")) {
                limpiarProducto();
                addActionMessage("Producto ingresado correctamente");
            } else {
                addActionError("Lamentablemente el producto no pudo ser ingresado por el siguiente error" + rta);
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
        ValidaCampos valida = new ValidaCampos();
        ValidaDuplicadodosProd duplicados = new ValidaDuplicadodosProd();
        Emp_EmpresaLogica logicaEmp = new Emp_EmpresaLogica();
        Empresa empresa = logicaEmp.obtieneDatosEmpresa();
        producto.setPorcIva(empresa.getIva());
        empresa = null;
        boolean rtaValida = false;
        rtaValida = valida.validaNulo(producto.getNombre());
        if (rtaValida == false) {
            addActionError("El campo nombre no puede ser nulo");
        }
        rtaValida = valida.validaNulo(producto.getDescripcion());
        if (rtaValida == false) {
            addActionError("El campo descripcion no puede ser nulo");
        }
        rtaValida = valida.validaFloat("" + producto.getPorcIva());
        if (rtaValida == false) {
            addActionError("El campo Porcentaje Iva debe ser numerico o los decimales deben ser con .");
        }
        rtaValida = valida.validaFloat(producto.getCosto());
        if (rtaValida == false) {
            addActionError("El campo costo debe ser numerico o los decimales deben ser con .");
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
}
