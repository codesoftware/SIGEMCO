<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String fact_fact = ""+request.getParameter("fact_fact");
    String dtsv_fact = ""+request.getParameter("dtsv_fact");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "-1");
    Gson gson = null;
    Fac_FacturacionLogica obj = null;
    try{
        Map rta = new HashMap<String,String>();
        gson = new Gson();
        obj = new Fac_FacturacionLogica();
        String rtas = obj.inactivarServiciosPorId(fact_fact, dtsv_fact);
        rta.put("Respuesta", rtas);
        String rpta = gson.toJson(rta);
        out.print(rpta);
    }catch(Exception e){
        System.out.println("Error ajaxEliminaDetServFactura.jsp " + e);
    }finally{
        gson = null;        
    }
%>
<%-- 
ajaxEliminaDetServFactura: Jsp encargado de eliminar los detalles de los servicios
de las facturas
--%>