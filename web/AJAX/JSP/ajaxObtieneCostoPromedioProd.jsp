<%@page import="java.util.HashMap"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.ProductoLogica"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String dska_dska = request.getParameter("dska_dska");
    Gson gson = null;
    String objJson = "";
    Map<String,String> mapRta = null;
    try{
        gson = new Gson();
        ProductoLogica logica = new ProductoLogica();
        String aux = logica.obtieneValorPonderadoProducto(dska_dska);
        mapRta = new HashMap<String, String>();
        if(aux== null){
            mapRta.put("rta", "Error al obtener el valor");
        }else{
            mapRta.put("rta", aux);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    objJson = gson.toJson(mapRta);
    out.print(objJson);
%>