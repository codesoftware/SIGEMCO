<%@page import="co.com.hotel.dto.facturacion.Producto"%>
<%@page import="java.util.List"%>
<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String fact_fact = ""+request.getParameter("fact_fact");
    Gson gson = new Gson();
    Fac_FacturacionLogica obj = null;
    String rtaJson = "";
    List<Producto> r = null;
    try{
        obj = new Fac_FacturacionLogica();
        gson = new Gson();
        r = obj.recuperaDetalleFacturaProducto(fact_fact);        
    }catch(Exception e){
        System.out.println("Error ajaxBuscaDetallesProductosFactura.jsp " + e);
    }finally{
        obj = null;
    }
    rtaJson = gson.toJson(r);
    out.write(rtaJson);
%>