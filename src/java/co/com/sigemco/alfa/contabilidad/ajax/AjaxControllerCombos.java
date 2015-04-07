/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.ajax;

import co.com.sigemco.alfa.contabilidad.logica.GrupoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Personal
 */
public class AjaxControllerCombos extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String grup_clas;
    private String grup_nombre;

    public void consultaComboGrupo() {
        GrupoLogica logica = new GrupoLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.consultaGrupoXClase(grup_clas);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGrup_clas() {
        return grup_clas;
    }

    public void setGrup_clas(String grup_clas) {
        this.grup_clas = grup_clas;
    }

    public String getGrup_nombre() {
        return grup_nombre;
    }

    public void setGrup_nombre(String grup_nombre) {
        this.grup_nombre = grup_nombre;
    }

}
