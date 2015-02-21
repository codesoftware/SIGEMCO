<%@page import="co.com.hotel.dto.Perfil"%>
<%@page import="co.com.hotel.logica.perfil.Adm_PerfilLogica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.com.hotel.dto.Producto"%>
<%@page import="co.com.hotel.logica.productos.ConsultaProductos"%>
<%
    String nombre = "" + request.getParameter("nombre");
    String desc = "" + request.getParameter("desc");
    String estado = "" + request.getParameter("estado");
    String opcion = "" + request.getParameter("opcion");

    if (opcion.equalsIgnoreCase("2")) {
        Adm_PerfilLogica perfil = new Adm_PerfilLogica();
        Perfil objPerfil = perfil.buscaPerfil(nombre.trim(), desc.trim(), estado.trim());
        String objetoJson = "";
        objetoJson = "[";
        if (objPerfil != null){
            objetoJson += ",{\"nombre\":\"";
            objetoJson += objPerfil.getNombre();
            objetoJson += "\" , ";
            objetoJson += "\"descripcion\":\"";
            objetoJson += objPerfil.getDescripcion();
            objetoJson += "\" , ";
            objetoJson += "\"estado\":\"";
            objetoJson += objPerfil.getEstado();
            objetoJson += "\" , ";
            objetoJson += "\"id\":\"";
            objetoJson += objPerfil.getId();
            objetoJson += "\" }";
            
        }
        objetoJson += "]";
        objetoJson = objetoJson.replaceFirst(",", "");
        out.println(objetoJson);        
        
    }
%>