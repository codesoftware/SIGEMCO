/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hotel.action.reenvio;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.dto.Empresa;
import co.com.hotel.dto.Producto;
import co.com.hotel.dto.Sede;
import co.com.hotel.logica.categoria.Inv_CategoriaLogica;
import co.com.hotel.logica.empresa.Emp_EmpresaLogica;
import co.com.hotel.logica.perfil.Adm_PerfilLogica;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.logica.usuarios.Adm_UsuarioLogica;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.logica.ClaseLogica;
import co.com.sigemco.alfa.inventario.dto.PantallaPrincipalDto;
import co.com.sigemco.alfa.inventario.dto.RemisionDto;
import co.com.sigemco.alfa.inventario.logica.MarcaLogica;
import co.com.sigemco.alfa.inventario.logica.PantallaPrincipalLogica;
import co.com.sigemco.alfa.inventario.logica.ProveedorLogica;
import co.com.sigemco.alfa.inventario.logica.ReferenciaLogica;
import co.com.sigemco.alfa.parametros.dto.ParametrosAdminDto;
import co.com.sigemco.alfa.parametros.logica.ParametrosAdminLogica;
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
public class reenvioGeneral extends ActionSupport implements UsuarioHabilitado, SessionAware {

    private int accion;
    private Usuario usuario;
    private Map session;
    private ArrayList<Sede> resultSede = null;
    //El primer numero el modulo, el segundo submodulo, el tercero accion(insert'1', update'2', inactivar'3', consulta'4', operativo'5'): 
    //ejemplo
    // 121  (1)Modulo de administracion (2)perfiles (1)Insert
    //MODULO ADMINISTRACION (Primer digito 1)
    public static final int ADM_CON_USUARIO = 110;
    public static final int ADM_INS_USUARIO = 111;
    public static final int ADM_UPD_USUARIO = 112;
    public static final int ADM_INS_PERFIL = 121;
    public static final int ADM_UPD_PERFIL = 122;
    public static final int ADM_CON_PERFIL = 124;
    public static final int ADM_INS_EMPRESA = 131;   //Informacion general de la empresa
    public static final int ADM_INS_PAREMPRE = 132;   //Informacion general de la empresa

    public static final int ADM_INS_SEDE = 141;   //
    public static final int ADM_UPD_SEDE = 142;   //
    public static final int ADM_CON_SEDE = 144;   //
    //CIERRES
    public static final int ADM_CIERRE_DIARIO = 151;
    //REPORTES
    public static final int ADM_REPORTE_GRAL = 152;
    //MODULO INVENTARIOS (Primer digito 2)
    public static final int INV_INS_PRODUCTO = 211;
    public static final int INV_INS_PRODEXIS = 219; //El ultimo digito es nueve ya que  ya existe la insercion de productos
    public static final int INV_UPD_PRODUCTO = 212;
    public static final int INV_CON_PRODUCTO = 214;
    public static final int INV_ACI_PRODUCTO = 215; //Activa o inactiva productos 
    public static final int INV_PAR_PRECIOSPR = 216; //Parametrizacion de precios de productos
    public static final int INV_CAM_SEDEPRODU = 218; //Cambio de una cantidad de productos
    public static final int INV_COR_INGPRODUC = 213; //Correccion de Ingresos por error humano

    public static final int INV_INS_SERVICIO = 221;
    public static final int INV_CON_SERVICIO = 224;
    public static final int INV_UPD_SERVICIO = 222;
    public static final int INV_ACI_SERVICIO = 225; //Activa o inactiva un servicios
    public static final int INV_PAR_PRECIOSV = 231; //Parametrizacion de precios de servicios
    //Paginas de Movimientos de inventario
    public static final int INV_INS_MOVINVENTARIO = 241;
    public static final int INV_UPD_MOVINVENTARIO = 242;
    public static final int INV_CON_MOVINVENTARIO = 244;
    //Paginas de Celulares
    public static final int INV_INS_CELULAR = 251;
    public static final int INV_CON_CELULAR = 254;
    //Paginas Referencias de celulares
    public static final int INV_INS_REFERENCIA = 261;
    public static final int INV_UPD_REFERENCIA = 262;
    public static final int INV_CON_REFERENCIA = 264;
    //Paginas para Recetas
    public static final int INV_INS_RECETA = 291;
    public static final int INV_CONS_RECETA = 292;
    public static final int INV_PARA_PRECIORECETA = 293;
    //paginas de marcas
    public static final int INV_INS_MARCA = 601;
    public static final int INV_UPD_MARCA = 602;
    public static final int INV_CON_MARCA = 604;
    //Paginas de Proveedores
    public static final int INV_INS_PROVED = 701;
    public static final int INV_UPD_PROVED = 702;
    public static final int INV_CON_PROVED = 704;
    //paginas de arqueos
    public static final int INV_INS_ARQUEO = 801;
    public static final int INV_UPD_ARQUEO = 802;
    public static final int INV_CON_ARQUEO = 804;
    // paginas de categorias
    public static final int INV_INS_CATEGORIA = 271;
    public static final int INV_UPD_CATEGORIA = 272;
    public static final int INV_CON_CATEGORIA = 274;

    public static final int INV_INS_PROVEEDORES = 281;
    public static final int INV_UPD_PROVEEDORES = 282;
    public static final int INV_CON_PROVEEDORES = 284;

    //Movimientos Contables
    public static final int INV_CON_MOVCONTABLE = 294;
    //Submodulo de Conteos
    public static final int INV_INS_CREACONTEO = 232;
    public static final int INV_CON_CONTEOPROD = 233;
    //MODULO FACTURACION (Primer digito 3)
    public static final int FAC_INS_FACTURA = 311;
    public static final int FAC_UPD_FACTURA = 312;
    public static final int FAC_CANC_FACTURA = 313;
    public static final int FAC_UPD_PANTPRINC = 314; //Punto de menu con el cual se modificaran las imagenes que se veran en la pantalla principal de facturacion
    //MODULO DE REPORTES (Primer digito 4)
    // Este modulo tiene una numeracion especial
    // primer digito(3) segundo (inventarios = 1, productos = 2 , usuarios= 3)
    public static final int REP_INV_PONDERADO = 411;
    public static final int REP_INV_USUARIOS = 431;//Reportes de usuarios
    public static final int REP_INV_BASICOS = 412;//Reportes basicos de inventarios

    //MODULO DE CONTABILIDAD (Primer digito 5)
    public static final int CON_CONSGENCLASES = 514;
    public static final int CON_INSERTSUBCUENTAS = 515;

    //Listas iniciales de las paginas
    private List<String> perfiles;
    private List<String> estadoUsuario;
    private List<String> gravamen;
    private Map<String, String> yesNo;
    private Map<String, String> estadoMap;
    private Map<String, String> runico;
    private Map<String, String> perfilesMap;
    private Map<String, String> sedes;
    private Map<String, String> categorias;
    private Map<String, String> referencias;
    //Mapas necesarios para los proveedores
    private Map<String, String> proveedores;

    //Mapas Necesarios para Referencias de Celular
    private Map<String, String> camara;
    private Map<String, String> memoria;
    private Map<String, String> pantalla;

    //Mapas necesarios para los movimientos de inventario
    private Map<String, String> naturalezaMvIn;
    private Map<String, String> usuarioImplicado;
    //Tipo plan
    private Map<String, String> tipoPlan;
    private Map<String, String> estadoEqCeluar; //Estado en el cual se encuentra el equipo celular
    //Mapa necesario para consultar movimientos contables
    private Map<String, String> clasePUC;
    private Map<String, String> grupoPUC;
    private Map<String, String> cuentaPUC;
    private Producto producto;
    //Lista de Clases del puc
    private List<ClaseDto> clase;
    //Mapa para visualizar las marcas
    private Map<String, String> marcas;
    //Mapa por si se requiere parametrizar los reportes
    private Map<String, String> reportes;
    private Map<String, String> reportesBasicos;
    private String modifica;
    private String bandera;
    private String estado;
    private Empresa empresa;
    private RemisionDto remision;
    //
    private ArrayList<PantallaPrincipalDto> productosPantalla;
    private ArrayList<PantallaPrincipalDto> recetasPantalla;
    //Mapa para consultar los parametros detallados de la empresa
    private Map<String, String> parametrosEspeciales;
    private String parametrosComparar;

    /**
     *
     * @return
     */
    public String execute() {
        String nextPage = "";
        Adm_PerfilLogica periflObj = null;
        Adm_UsuarioLogica usuarioObj = null;
        Adm_SedeLogica sedeLogica = null;
        ProveedorLogica provLogica = null;
        Inv_CategoriaLogica cateLogica = null;
        ReferenciaLogica refeLogica = null;
        Emp_EmpresaLogica logicaEmp = null;
        ClaseLogica logicaClase = null;
        MarcaLogica logicaMarca = null;
        ParametrosAdminLogica logicaParametros = null;

        try {
            switch (this.accion) {
                case ADM_CON_USUARIO:
                    periflObj = new Adm_PerfilLogica();
                    this.perfiles = periflObj.obtieneNomPerfil();
                    usuarioObj = new Adm_UsuarioLogica();
                    this.estadoUsuario = usuarioObj.obtieneEstadoUsuario();
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    nextPage = "adm_con_usuario";
                    break;
                case ADM_INS_USUARIO:
                    logicaParametros = new ParametrosAdminLogica();
                    this.parametrosEspeciales = logicaParametros.consultaParametrosAdm();
                    if ("S".equalsIgnoreCase(this.parametrosEspeciales.get("Comision"))) {
                        setParametrosComparar("S");
                    } else {
                        setParametrosComparar("N");
                    }
                    periflObj = new Adm_PerfilLogica();
                    sedeLogica = new Adm_SedeLogica();
                    this.perfiles = periflObj.obtieneNomPerfil();
                    this.sedes = sedeLogica.obtieneSedes();
                    nextPage = "adm_ins_usuario";
                    break;
                case ADM_UPD_USUARIO:
                    periflObj = new Adm_PerfilLogica();
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    this.perfilesMap = periflObj.obitnePerfilIdNombre();
                    modifica = "N";
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    nextPage = "adm_upd_usuario";
                    break;
                case ADM_INS_PERFIL:
                    nextPage = "adm_ins_perfil";
                    break;
                case ADM_UPD_PERFIL:
                    nextPage = "adm_upd_perfil";
                    break;
                case ADM_CON_PERFIL:
                    nextPage = "adm_con_perfil";
                    break;
                case ADM_INS_EMPRESA:
                    nextPage = "adm_ins_empresa";
                    logicaEmp = new Emp_EmpresaLogica();
                    empresa = logicaEmp.obtieneDatosEmpresa();
                    break;
                case ADM_INS_PAREMPRE:
                    logicaEmp = new Emp_EmpresaLogica();
                    empresa = logicaEmp.obtieneDatosEmpresa();
                    nextPage = "adm_ins_parempre";
                    break;
                case INV_INS_PRODUCTO:
                    nextPage = "inv_ins_producto";
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    provLogica = new ProveedorLogica();
                    this.proveedores = provLogica.obtieneProovedores();
                    cateLogica = new Inv_CategoriaLogica();
                    this.categorias = cateLogica.obtieneCategorias();
                    logicaMarca = new MarcaLogica();
                    this.marcas = logicaMarca.obtieneMarcas();
                    logicaEmp = new Emp_EmpresaLogica();
                    empresa = logicaEmp.obtieneDatosEmpresa();
                    producto = new Producto();
                    producto.setPorcIva(empresa.getIva());
                    empresa = null;
                    refeLogica = new ReferenciaLogica();
                    this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
                    break;
                case INV_COR_INGPRODUC:
                    nextPage = "inv_cor_ingproduc";
                    break;
                case INV_INS_PRODEXIS:
                    bandera = "S";
                    nextPage = "inv_ins_prodexis";
                    break;
                case INV_CAM_SEDEPRODU:
                    nextPage = "inv_cam_sedeprodu";
                    break;
                case FAC_INS_FACTURA:
                    nextPage = "fac_ins_factura";
                    break;
                case INV_INS_SERVICIO:
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    nextPage = "inv_ins_servicio";
                    break;
                case INV_CON_SERVICIO:
                    nextPage = "inv_con_servicio";
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    break;
                case INV_UPD_SERVICIO:
                    nextPage = "inv_upd_servicio";
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    break;
                case INV_CON_PRODUCTO:
                    nextPage = "inv_con_producto";
                    refeLogica = new ReferenciaLogica();
                    this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
                    logicaMarca = new MarcaLogica();
                    this.marcas = logicaMarca.obtieneMarcas();
                    cateLogica = new Inv_CategoriaLogica();
                    this.categorias = cateLogica.obtieneCategorias();
                    break;
                case INV_UPD_PRODUCTO:
                    nextPage = "inv_upd_producto";
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
//                    provLogica = new ProveedorLogica();
//                    this.proveedores = provLogica.obtieneProovedores(); 
                    break;
                case REP_INV_PONDERADO:
                    refeLogica = new ReferenciaLogica();
                    this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
                    nextPage = "rep_inv_ponderado";
                    break;
                case REP_INV_BASICOS:
                    this.reportesBasicos = new HashMap<String, String>();
                    if (usuario.getPermisos().contains(".RpIn2.")) {
                        this.reportesBasicos.put("existGene", "EXISTENCIAS GENERALES");
                    }
                    if (usuario.getPermisos().contains(".RpIn3.")) {
                        this.reportesBasicos.put("receProd", "PRODUCTOS RECETAS");
                    }
                    nextPage = "rep_inv_basicos";
                    break;
                case INV_ACI_PRODUCTO:
                    nextPage = "inv_aci_producto";
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_ACI_SERVICIO:
                    nextPage = "inv_aci_servicio";
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_PAR_PRECIOSPR:
                    nextPage = "inv_par_preciospr";
                    break;
                case INV_PAR_PRECIOSV:
                    nextPage = "inv_par_preciosv";
                    break;

                case INV_INS_CELULAR:
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    this.tipoPlan = new HashMap<String, String>();
                    this.tipoPlan.put("ps", "POSTPAGO");
                    this.tipoPlan.put("pr", "PREPAGO");
                    this.tipoPlan.put("rp", "REPOSICION");
                    refeLogica = new ReferenciaLogica();
                    this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
                    logicaEmp = new Emp_EmpresaLogica();
                    empresa = logicaEmp.obtieneDatosEmpresa();
                    remision = new RemisionDto();
                    remision.setRmce_comision("N/A");
                    //remision.setRmce_comision(empresa.getComisionPrepago()); // Se cambia ya que la comision ahora depende de acuerdo el plan
                    nextPage = "inv_ins_celular";
                    break;
                case INV_CON_CELULAR:
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    this.tipoPlan = new HashMap<String, String>();
                    this.tipoPlan.put("ps", "POSTPAGO");
                    this.tipoPlan.put("pr", "PREPAGO");
                    refeLogica = new ReferenciaLogica();
                    this.referencias = refeLogica.obtieneIdDescrReferenciaActivos();
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    this.estadoEqCeluar = new HashMap<String, String>();
                    this.estadoEqCeluar.put("E", "STAND");
                    this.estadoEqCeluar.put("V", "VENDIDO");
                    this.estadoEqCeluar.put("D", "DEVUELTO");
                    nextPage = "inv_con_celular";
                    break;
                case FAC_UPD_FACTURA:
                    nextPage = "fac_upd_factura";
                    this.estado = "inicial";
                    break;
                case FAC_CANC_FACTURA:
                    nextPage = "fac_canc_factura";
                    break;
                case FAC_UPD_PANTPRINC:
                    nextPage = "fac_upd_pantprinc";
                    PantallaPrincipalLogica objLogica = new PantallaPrincipalLogica();
                    productosPantalla = objLogica.buscaProductos();
                    recetasPantalla = objLogica.buscaRecetas();
                    break;
                case REP_INV_USUARIOS:
                    periflObj = new Adm_PerfilLogica();
                    this.perfilesMap = periflObj.obitnePerfilIdNombre();
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    nextPage = "rep_inv_usuarios";
                    break;
                case INV_INS_CREACONTEO:
                    nextPage = "inv_ins_creaconteo";
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    break;
                case INV_CON_CONTEOPROD:
                    nextPage = "inv_con_conteoprod";
                    sedeLogica = new Adm_SedeLogica();
                    this.sedes = sedeLogica.obtieneSedes();
                    break;
                case INV_INS_MOVINVENTARIO:
                    nextPage = "inv_ins_movinventario";
                    naturalezaMvIn = new HashMap<String, String>();
                    this.naturalezaMvIn.put("I", "Ingreso");
                    this.naturalezaMvIn.put("E", "Egreso");
                    this.usuarioImplicado = new HashMap<String, String>();
                    this.usuarioImplicado.put("C", "Cliente");
                    this.usuarioImplicado.put("P", "Proveedor");
                    this.usuarioImplicado.put("N", "Ninguno");
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    break;

                case INV_INS_REFERENCIA:
                    nextPage = "inv_ins_referencia";
                    break;
                case INV_UPD_REFERENCIA:
                    nextPage = "inv_upd_referencia";
                    break;
                case INV_CON_REFERENCIA:
                    nextPage = "Inv_ConReferencia";
                    this.camara = new HashMap<String, String>();
                    this.camara.put("8", "MENOS DE 8");
                    this.camara.put("13", "ENTRE 8 Y 13");
                    this.camara.put("14", "MAS DE 13 ");
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    this.memoria = new HashMap<String, String>();
                    this.pantalla = new HashMap<String, String>();
                    break;
                case INV_INS_MARCA:
                    nextPage = "inv_ins_marca";
                    break;
                case INV_UPD_MARCA:
                    nextPage = "inv_upd_marca";
                    break;
                case INV_CON_MARCA:
                    nextPage = "Inv_Conmarca";
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_INS_PROVED:
                    nextPage = "inv_ins_proved";
                    break;
                case INV_UPD_PROVED:
                    nextPage = "inv_upd_proved";
                    break;
                case INV_CON_PROVED:
                    nextPage = "Inv_ConProved";
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_INS_ARQUEO:
                    Adm_SedeLogica sede = new Adm_SedeLogica();
                    this.sedes = sede.obtieneSedes();
                    nextPage = "inv_ins_arqueo";
                    break;
                case INV_UPD_ARQUEO:
                    nextPage = "inv_upd_arqueo";
                    break;
                case INV_CON_ARQUEO:
                    nextPage = "Inv_ConArqueo";
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("B", "Bueno");
                    this.estadoMap.put("S", "Sobro");
                    this.estadoMap.put("F", "Falto");
                    break;
                case INV_INS_CATEGORIA:
                    nextPage = "inv_ins_categoria";
                    this.runico = new HashMap<String, String>();
                    this.runico.put("A", "Activo");
                    this.runico.put("I", "Inactivo");
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_UPD_CATEGORIA:
                    nextPage = "inv_upd_categoria";
                    this.runico = new HashMap<String, String>();
                    this.runico.put("A", "Activo");
                    this.runico.put("I", "Inactivo");
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_CON_CATEGORIA:
                    nextPage = "Inv_ConCategoria";
                    this.runico = new HashMap<String, String>();
                    this.runico.put("A", "Activo");
                    this.runico.put("I", "Inactivo");
                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");
                    break;
                case INV_INS_PROVEEDORES:
                    nextPage = "inv_ins_proveedores";
                    break;
                case INV_UPD_PROVEEDORES:
                    nextPage = "inv_upd_proveedores";
                    break;
                case INV_CON_PROVEEDORES:
                    nextPage = "Inv_ConProveedores";
                    this.runico = new HashMap<String, String>();
                    this.runico.put("A", "Activo");
                    this.runico.put("I", "Inactivo");

                    this.estadoMap = new HashMap<String, String>();
                    this.estadoMap.put("A", "Activo");
                    this.estadoMap.put("I", "Inactivo");

                    break;

                case INV_UPD_MOVINVENTARIO:
                    nextPage = "inv_upd_movinventario";
                    naturalezaMvIn = new HashMap<String, String>();
                    this.naturalezaMvIn.put("I", "Ingreso");
                    this.naturalezaMvIn.put("E", "Egreso");
                    this.usuarioImplicado = new HashMap<String, String>();
                    this.usuarioImplicado.put("C", "Cliente");
                    this.usuarioImplicado.put("P", "Proveedor");
                    this.usuarioImplicado.put("N", "Ninguno");
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    break;
                case INV_CON_MOVINVENTARIO:
                    nextPage = "inv_con_movinventario";
                    naturalezaMvIn = new HashMap<String, String>();
                    this.naturalezaMvIn.put("I", "Ingreso");
                    this.naturalezaMvIn.put("E", "Egreso");
                    this.usuarioImplicado = new HashMap<String, String>();
                    this.usuarioImplicado.put("C", "Cliente");
                    this.usuarioImplicado.put("P", "Proveedor");
                    this.usuarioImplicado.put("N", "Ninguno");
                    this.yesNo = new HashMap<String, String>();
                    this.yesNo.put("S", "Si");
                    this.yesNo.put("N", "No");
                    this.modifica = "N";
                    break;
                case INV_CON_MOVCONTABLE:
                    ClaseLogica cl = new ClaseLogica();
                    List<ClaseDto> rta = new ArrayList<ClaseDto>();
                    rta = cl.consultaGeneralActivo();
                    clasePUC = new HashMap<String, String>();
                    grupoPUC = new HashMap<String, String>();
                    cuentaPUC = new HashMap<String, String>();
                    for (int i = 0; i < rta.size(); i++) {
                        this.clasePUC.put(rta.get(i).getClas_clas(), rta.get(i).getClas_nombre());
                    }
                    nextPage = "inv_con_movcontable";
                    break;

                case ADM_CIERRE_DIARIO:

                    sede = new Adm_SedeLogica();
                    this.sedes = sede.obtieneSedes();
                    nextPage = "adm_cierre_diario";
                    break;

                case ADM_REPORTE_GRAL:
                    reportes = new HashMap<String, String>();
                    this.reportes.put("1", "Reporte Promedio ponderado todo los productos");
                    nextPage = "adm_reporte_gral";
                    break;
                case ADM_CON_SEDE:
                    nextPage = "adm_con_sede";
                    break;
                case ADM_INS_SEDE:
                    nextPage = "adm_ins_sede";
                    break;
                case CON_CONSGENCLASES:
                    logicaClase = new ClaseLogica();
                    clase = logicaClase.consultaGeneralActivo();
                    nextPage = "con_consgenclases";
                    break;
                case CON_INSERTSUBCUENTAS:
                    nextPage = "con_insertcuentas";
                    break;
                case INV_INS_RECETA:
                    nextPage = "inv_ins_receta";
                    break;
                case INV_CONS_RECETA:
                    nextPage = "inv_cons_receta";
                    break;
                case INV_PARA_PRECIORECETA:
                    nextPage = "inv_para_precioreceta";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        periflObj = null;
        return nextPage;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> tipoUsuario) {
        this.perfiles = tipoUsuario;
    }

    public Map getSession() {
        return session;
    }

    public List<String> getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(List<String> estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * Funcion utilizada para evitar que los usuarios vean datos que no deban.
     *
     * @return retorna si la excepcion fue realizada correctamente
     */
    public boolean limpiar() {
        try {
            perfiles.clear();
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public List<String> getGravamen() {
        return gravamen;
    }

    public void setGravamen(List<String> gravamen) {
        this.gravamen = gravamen;
    }

    public Map<String, String> getYesNo() {
        return yesNo;
    }

    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    public String getModifica() {
        return modifica;
    }

    public void setModifica(String modifica) {
        this.modifica = modifica;
    }

    public Map<String, String> getPerfilesMap() {
        return perfilesMap;
    }

    public void setPerfilesMap(Map<String, String> perfilesMap) {
        this.perfilesMap = perfilesMap;
    }

    public Map<String, String> getEstadoMap() {
        return estadoMap;
    }

    public void setEstadoMap(Map<String, String> estadoMap) {
        this.estadoMap = estadoMap;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Map<String, String> getNaturalezaMvIn() {
        return naturalezaMvIn;
    }

    public void setNaturalezaMvIn(Map<String, String> naturalezaMvIn) {
        this.naturalezaMvIn = naturalezaMvIn;
    }

    public Map<String, String> getUsuarioImplicado() {
        return usuarioImplicado;
    }

    public void setUsuarioImplicado(Map<String, String> usuarioImplicado) {
        this.usuarioImplicado = usuarioImplicado;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public Map<String, String> getProveedores() {
        return proveedores;
    }

    public void setProveedores(Map<String, String> proveedores) {
        this.proveedores = proveedores;
    }

    public Map<String, String> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<String, String> categorias) {
        this.categorias = categorias;
    }

    public Map<String, String> getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(Map<String, String> tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public Map<String, String> getCamara() {
        return camara;
    }

    public void setCamara(Map<String, String> camara) {
        this.camara = camara;
    }

    public Map<String, String> getMemoria() {
        return memoria;
    }

    public void setMemoria(Map<String, String> memoria) {
        this.memoria = memoria;
    }

    public Map<String, String> getPantalla() {
        return pantalla;
    }

    public void setPantalla(Map<String, String> pantalla) {
        this.pantalla = pantalla;
    }

    public Map<String, String> getReferencias() {
        return referencias;
    }

    public void setReferencias(Map<String, String> referencias) {
        this.referencias = referencias;
    }

    public Map<String, String> getEstadoEqCeluar() {
        return estadoEqCeluar;
    }

    public void setEstadoEqCeluar(Map<String, String> estadoEqCeluar) {
        this.estadoEqCeluar = estadoEqCeluar;
    }

    public Map<String, String> getClasePUC() {
        return clasePUC;
    }

    public void setClasePUC(Map<String, String> clasePUC) {
        this.clasePUC = clasePUC;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public RemisionDto getRemision() {
        return remision;
    }

    public void setRemision(RemisionDto remision) {
        this.remision = remision;
    }

    public List<ClaseDto> getClase() {
        return clase;
    }

    public void setClase(List<ClaseDto> clase) {
        this.clase = clase;
    }

    public Map<String, String> getRunico() {
        return runico;
    }

    public void setRunico(Map<String, String> runico) {
        this.runico = runico;
    }

    public Map<String, String> getGrupoPUC() {
        return grupoPUC;
    }

    public void setGrupoPUC(Map<String, String> grupoPUC) {
        this.grupoPUC = grupoPUC;
    }

    public Map<String, String> getCuentaPUC() {
        return cuentaPUC;
    }

    public void setCuentaPUC(Map<String, String> cuentaPUC) {
        this.cuentaPUC = cuentaPUC;
    }

    public ArrayList<Sede> getResultSede() {
        return resultSede;
    }

    public void setResultSede(ArrayList<Sede> resultSede) {
        this.resultSede = resultSede;
    }

    public Map<String, String> getMarcas() {
        return marcas;
    }

    public void setMarcas(Map<String, String> marcas) {
        this.marcas = marcas;
    }

    public Map<String, String> getReportes() {
        return reportes;
    }

    public void setReportes(Map<String, String> reportes) {
        this.reportes = reportes;
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

    public Map<String, String> getReportesBasicos() {
        return reportesBasicos;
    }

    public void setReportesBasicos(Map<String, String> reportesBasicos) {
        this.reportesBasicos = reportesBasicos;
    }

    public Map<String, String> getParametrosEspeciales() {
        return parametrosEspeciales;
    }

    public void setParametrosEspeciales(Map<String, String> parametrosEspeciales) {
        this.parametrosEspeciales = parametrosEspeciales;
    }

    public String getParametrosComparar() {
        return parametrosComparar;
    }

    public void setParametrosComparar(String parametrosComparar) {
        this.parametrosComparar = parametrosComparar;
    }

}
