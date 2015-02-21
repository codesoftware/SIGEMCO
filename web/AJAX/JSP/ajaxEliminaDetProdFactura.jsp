<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
    String fact_fact = ""+request.getParameter("fact_fact");
    String dtpr_dtpr = ""+request.getParameter("dtpr_dtpr");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    Gson gson = new Gson();
    Fac_FacturacionLogica obj = null;
    String rtaJson = "";
    Map rta = null;
    try{
        obj = new Fac_FacturacionLogica();
        rta = new HashMap<String,String>();
        String msn = obj.inactivarProductosPorId(fact_fact, dtpr_dtpr, usuario.getIdTius());
        rta.put("respuesta", msn);
    }catch(Exception e){
        System.out.println("Error ajaxEliminaDetProdFactura");
    }finally{
        usuario = null;
    }
    rtaJson = gson.toJson(rta);
    out.write(rtaJson);
%>