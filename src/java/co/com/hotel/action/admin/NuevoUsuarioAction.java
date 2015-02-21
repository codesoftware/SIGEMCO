package co.com.hotel.action.admin;

import co.com.hotel.datos.session.Usuario;
import co.com.hotel.logica.perfil.Adm_PerfilLogica;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.logica.usuarios.ConsultaUsuarios;
import co.com.hotel.logica.usuarios.IngresaUsuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import co.com.hotel.validacion.ValidaCampos;
import co.com.hotel.validacion.ValidaDuplicados;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Action el cual se encargara de ingresar los datos del nuevo usuario al
 * sistema
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class NuevoUsuarioAction extends ActionSupport implements SessionAware, UsuarioHabilitado {

    private Usuario usuario;
    private Map session;
    private Usuario usuaNuevo;
    private String aliasUsuarioNuevo;
    private List<String> perfiles = new ArrayList<String>();
    private Map<String, String> perfilesMap;
    private Map<String, String> estadoMap;
    private Map<String, String> sedes;
    private String modifica;

    @SkipValidation
    public String execute() {
        return SUCCESS;
    }

    /**
     * Funcion la cual se encargara de ingresar el usuario al sistema
     *
     * @since 1.0.0
     * @return Redireccionara la pagina si fue exitoso o no el ingreso del
     * usuario
     */
    public String agregarUsuario() {
        IngresaUsuario nuevoUsuario = new IngresaUsuario();
        Adm_PerfilLogica logicaPerfil = new Adm_PerfilLogica();
        String auxId = logicaPerfil.obtieneIdPerfil(usuaNuevo.getTipoUsuario());
        usuaNuevo.setIdPerfil(auxId);
        usuaNuevo.setUsuario(aliasUsuarioNuevo);
        String rta = nuevoUsuario.ingresarUsuario(this.usuaNuevo, this.usuaNuevo.getFechaNacimiento());
        if (!rta.equalsIgnoreCase("Error")) {
            addActionMessage("Usuario insertado correctamente\n Su contraseña Auxiliar para el sistema es: "+ rta + "\n y su Usuario e: " + aliasUsuarioNuevo );
            usuaNuevo = null;
            aliasUsuarioNuevo = "";
        } else {
            addActionError("Error al crear el usuario");
        }
        return SUCCESS;
    }

    @SkipValidation
    public String consultaUsuario() {
        Adm_PerfilLogica periflObj = null;
        try {
            periflObj = new Adm_PerfilLogica();
            this.perfilesMap = periflObj.obitnePerfilIdNombre(); 
            this.estadoMap = new HashMap<String, String>();
            this.estadoMap.put("A", "Activo");
            this.estadoMap.put("I", "Inactivo");
            ConsultaUsuarios logica = new ConsultaUsuarios();
            this.usuaNuevo.setUsuario(aliasUsuarioNuevo);
            usuaNuevo = logica.buscaUsuarioXFiltros(this.usuaNuevo);
            modifica = logica.getTrajoDatos();
            Adm_SedeLogica sedeLogica  = new Adm_SedeLogica();
            this.sedes = sedeLogica.obtieneSedes();
        } catch (Exception e) {
            System.out.println("Error NuevoUsuarioAction.consultaUsuario " + e);
        } finally {
            periflObj = null;
        }

        return SUCCESS;
    }

    /**
     * Funcion la cual se ejecutara antes de cualquier accion y que validara que
     * los campos ingresados por los usuarios tengan el formato correcto, y sean
     * los esperados por el sistema
     *
     */
    public void validate() {
        ValidaCampos valida = new ValidaCampos();
        ValidaDuplicados duplicados = new ValidaDuplicados();
        boolean validacion = false;
        boolean valdup = false;
        //Validacion del campo nombre 
        validacion = valida.validaNulo(this.usuaNuevo.getNombre());
        if (validacion == false) {
            addActionError("El campo NOMBRE no puede ser nulo");
        } else {
            validacion = false;
            validacion = valida.validaLetras(this.usuaNuevo.getNombre());
            if (validacion == false) {
                addActionError("El campo NOMBRE solo debe tener letras");
            }
        }
        validacion = false;
        //Validaciones del campo Apellidos
        validacion = valida.validaNulo(this.usuaNuevo.getApellido());
        if (validacion == false) {
            addActionError("El campo APELLIDO no puede ser nulo");
        } else {
            validacion = false;
            validacion = valida.validaLetras(this.usuaNuevo.getApellido());
            if (validacion == false) {
                addActionError("El campo APELLIDO solo debe tener letras");
            }
        }
        validacion = false;
        //Validacion del campo cedula
        validacion = valida.validaNulo(this.usuaNuevo.getCedula());
        if (validacion == false) {
            addActionError("El campo CEDULA no puede ser nulo");
        } else {
            validacion = false;
            validacion = valida.validaNumerico(this.usuaNuevo.getCedula());
            if (validacion == false) {
                addActionError("El campo CEDULA solo debe tener NUMEROS");
            } else {
                valdup = duplicados.verificaCedula(this.usuaNuevo.getCedula());
                if (valdup == false) {
                    addActionError("El campo CEDULA ya se encuentra en la base de datos");
                }
            }
        }
        validacion = false;
        //Validacion del campo Correo
        validacion = valida.validaNulo(this.usuaNuevo.getCorreo());
        if (validacion == false) {
            addActionError("El campo CORREO no puede ser nulo");
        } else {
            validacion = false;
            validacion = valida.validaCorreo(this.usuaNuevo.getCorreo());
            if (validacion == false) {
                addActionError("El campo CORREO no tiene el formato deseado");
            } else {
                valdup = false;
                valdup = duplicados.verificaCorreo(this.usuaNuevo.getCorreo());
                if (valdup == false) {
                    addActionError("El campo CORREO ya se encuentra en la base de datos");
                }
            }
        }
        validacion = false;
        //Validacion del campo usuario
        validacion = valida.validaNulo(aliasUsuarioNuevo);
        if (validacion == false) {
            addActionError("El campo USUARIO no puede ser nulo");
        } else {
            valdup = false;
            valdup = duplicados.verificaUsuario(aliasUsuarioNuevo);
            if (valdup == false) {
                addActionError("El campo USUARIO ya se encuentra en la base de datos");
            }
        }
        //Validacion de perfiles
        if (usuaNuevo.getTipoUsuario().equalsIgnoreCase("-1")) {
            addActionError("Por favor eliga un perfil para el nuevo usuario");
        }
        Adm_PerfilLogica periflObj = new Adm_PerfilLogica();
        setPerfiles(periflObj.obtieneNomPerfil());
        Adm_SedeLogica sedeLogica  = new Adm_SedeLogica();
        setSedes(sedeLogica.obtieneSedes());
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

    public Usuario getUsuaNuevo() {
        return usuaNuevo;
    }

    public void setUsuaNuevo(Usuario usuaNuevo) {
        this.usuaNuevo = usuaNuevo;
    }

    public String getAliasUsuarioNuevo() {
        return aliasUsuarioNuevo;
    }

    public void setAliasUsuarioNuevo(String aliasUsuarioNuevo) {
        this.aliasUsuarioNuevo = aliasUsuarioNuevo;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
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

    public Map<String, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, String> sedes) {
        this.sedes = sedes;
    }

    public Map getSession() {
        return session;
    }
}
