package co.com.hotel.action.general;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.com.hotel.action.general.*;
import co.com.hotel.logica.general.RecuperaContraLogica;
import co.com.hotel.logica.general.RecuperaContraLogica;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action el cual se encarga de recuperar la contraseña de un usuario basandose
 * en un correo
 *
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class RecuperarContrasena extends ActionSupport {

    private String correo;

    /**
     * Función por default la cual se encarga de enviar a la pagina
     * correspondiente dependiendo si se pudo o no enviar el correo de
     * recuperacion de contraseña
     *
     * @return Mensaje de redireccionamiento de la pagina
     */
    public String execute() {
        if (correo != null) {
            String[] split = correo.split("@");
            int numArroba = split.length;
            if (numArroba == 2) {
                RecuperaContraLogica rec = new RecuperaContraLogica();
                boolean recuperaContra = rec.recuperaContra(this.correo);
                if (recuperaContra == false){
                    return ERROR;
                }
                return SUCCESS;
            } else {
                return ERROR;
            }

        } else {
            return ERROR;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
