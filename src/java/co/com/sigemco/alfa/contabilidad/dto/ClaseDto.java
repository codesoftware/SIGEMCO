/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.dto;

import java.util.List;

/**
 * Clase encargada de transferir la informacion de las clases del puc
 *
 * @author ACER
 */
public class ClaseDto {

    private String clas_clas;
    private String clas_estado;
    private String clas_nombre;
    private String clas_codigo;
    private String clas_descripcion;
    private String clas_naturaleza;
    private List<GrupoDto> grupo;

    public String getClas_clas() {
        return clas_clas;
    }

    public void setClas_clas(String clas_clas) {
        this.clas_clas = clas_clas;
    }

    public String getClas_estado() {
        return clas_estado;
    }

    public void setClas_estado(String clas_estado) {
        this.clas_estado = clas_estado;
    }

    public String getClas_nombre() {
        return clas_nombre;
    }

    public void setClas_nombre(String clas_nombre) {
        this.clas_nombre = clas_nombre;
    }

    public String getClas_codigo() {
        return clas_codigo;
    }

    public void setClas_codigo(String clas_codigo) {
        this.clas_codigo = clas_codigo;
    }

    public String getClas_descripcion() {
        return clas_descripcion;
    }

    public void setClas_descripcion(String clas_descripcion) {
        this.clas_descripcion = clas_descripcion;
    }

    public List<GrupoDto> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<GrupoDto> grupo) {
        this.grupo = grupo;
    }

    public String getClas_naturaleza() {
        return clas_naturaleza;
    }

    public void setClas_naturaleza(String clas_naturaleza) {
        this.clas_naturaleza = clas_naturaleza;
    }

}
