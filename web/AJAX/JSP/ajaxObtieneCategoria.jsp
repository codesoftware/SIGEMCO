<%@page import="co.com.sigemco.alfa.inventario.dto.CategoriaDto"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.CategoriaLogica"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"   pageEncoding="UTF-8" %>
<%
  String id = "" + request.getParameter("cate_cate");
  Gson gson = null;
  String objJson = "";
  try{
      gson = new Gson();
      CategoriaLogica logica = new CategoriaLogica();
      CategoriaDto objDto = null;
      objDto = logica.obtieneCategoriasXId(id);
      objJson = gson.toJson(objDto);
      out.print(objJson);
  }catch(Exception e){
      e.printStackTrace();
  }finally{
      gson = null;
  }
%>