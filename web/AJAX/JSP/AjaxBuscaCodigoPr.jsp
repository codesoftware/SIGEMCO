<%@page import="java.util.ArrayList"%>
<%@page import="co.com.hotel.dto.Producto"%>
<%@page import="co.com.hotel.logica.productos.ConsultaProductos"%>
<%
    String filtro = ""+request.getParameter("codigo");
    ConsultaProductos consulta = new ConsultaProductos();
    boolean valida = consulta.buscaCodigoPr("%"+filtro+"%");
    if(valida == true){
        ArrayList<Producto> productos =  consulta.getCodResult();
    }else{
        System.out.println("Error al buscar productos");
    }   
%>