<%@page import="co.com.sigemco.alfa.inventario.dto.RemisionDto"%>
<%@page import="java.util.List"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.RemisionLogica"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    Gson gson = null;
    String objJson = "";
    try{
        gson = new Gson();
        RemisionLogica logica = new RemisionLogica();
        List<RemisionDto> rta = logica.encuentraEquiposProximosAVencer();
        objJson = gson.toJson(rta);
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        gson = null;
    }
    out.print(objJson);
%>