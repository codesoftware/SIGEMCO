<%@page import="com.google.gson.Gson"%>
<%@page import="co.com.hotel.dto.facturacion.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String fact_fact = ""+request.getParameter("fact_fact");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "-1");
    Fac_FacturacionLogica obj = null;
    try{
        obj = new Fac_FacturacionLogica();
        List<Servicio> servicios = obj.recuperaDetalleFacturaServicio(fact_fact);
        Gson gson = new Gson();
        String rta = gson.toJson(servicios);
        out.print(rta);
    }catch(Exception e)  {
        System.out.println("Error ajaxBusquaDetallesServicioFactura.jsp " + e );
    }finally{
        obj = null;
    }
    
%>