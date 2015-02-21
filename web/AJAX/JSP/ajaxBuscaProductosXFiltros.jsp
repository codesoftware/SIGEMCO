<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="co.com.hotel.logica.productos.Inv_ProductoLogica"%>
<%@page import="co.com.hotel.dto.Producto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String codigo = ""+request.getParameter("codigo");
    String referencia = ""+request.getParameter("referencia");
    String nombre = ""+request.getParameter("nombre");
    //System.out.println("Codigo: " + codigo + " referencia: " + referencia + " nombre: " + nombre);
    Producto obj = null;
    Inv_ProductoLogica logica = null;
    Gson gson = new Gson();
    try{
        obj= new Producto();
        logica = new Inv_ProductoLogica();
        obj.setCodigo(codigo);
        obj.setReferencia(referencia);
        obj.setNombre(nombre);
        ArrayList<Producto> rta = logica.buscaProductosXFiltro(obj);
        String objJson = gson.toJson(rta);
        out.print(objJson);       
    }catch(Exception e){
        System.out.println("Error ajaxBuscaProductosXFiltros.jsp " + e);
    }finally{
        gson = null;
        obj = null;
        logica = null;
    }
%>