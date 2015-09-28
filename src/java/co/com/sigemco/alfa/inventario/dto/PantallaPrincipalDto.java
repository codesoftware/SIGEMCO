/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.dto;

import java.io.File;

/**
 *
 * @author nicolas
 */
public class PantallaPrincipalDto {

    private String ppfa_ppfa;
    private String ppfa_codigo;
    private String ppfa_tipo;
    private String ppfa_nombre;
    private String ppfa_posicion;
    private String ppfa_ruta_img;
    private String ppfa_extencion;
    private File imagen;
    private String imagenFileName;
    private String imagenContentType;

    public String getPpfa_ppfa() {
        return ppfa_ppfa;
    }

    public void setPpfa_ppfa(String ppfa_ppfa) {
        this.ppfa_ppfa = ppfa_ppfa;
    }

    public String getPpfa_codigo() {
        return ppfa_codigo;
    }

    public void setPpfa_codigo(String ppfa_codigo) {
        this.ppfa_codigo = ppfa_codigo;
    }

    public String getPpfa_tipo() {
        return ppfa_tipo;
    }

    public void setPpfa_tipo(String ppfa_tipo) {
        this.ppfa_tipo = ppfa_tipo;
    }

    public String getPpfa_nombre() {
        return ppfa_nombre;
    }

    public void setPpfa_nombre(String ppfa_nombre) {
        this.ppfa_nombre = ppfa_nombre;
    }

    public String getPpfa_posicion() {
        return ppfa_posicion;
    }

    public void setPpfa_posicion(String ppfa_posicion) {
        this.ppfa_posicion = ppfa_posicion;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public String getPpfa_ruta_img() {
        return ppfa_ruta_img;
    }

    public void setPpfa_ruta_img(String ppfa_ruta_img) {
        this.ppfa_ruta_img = ppfa_ruta_img;
    }

    public String getPpfa_extencion() {
        return ppfa_extencion;
    }

    public void setPpfa_extencion(String ppfa_extencion) {
        this.ppfa_extencion = ppfa_extencion;
    }

}
