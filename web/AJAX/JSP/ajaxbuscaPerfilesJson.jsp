<%@page import="co.com.hotel.logica.perfil.Adm_PerfilLogica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.com.hotel.dto.Producto"%>
<%@page import="co.com.hotel.logica.productos.ConsultaProductos"%>
<%
    String filtro = "" + request.getParameter("filtro");
    String opcion = "" + request.getParameter("opcion");

    if (opcion.equalsIgnoreCase("1")) {
        Adm_PerfilLogica perfil = new Adm_PerfilLogica();
        ArrayList<String> rta = perfil.buscaPerfilesXFiltro(filtro);
        String objetoJson = "";
        objetoJson = "[";
        for (String aux : rta) {
            objetoJson += ",{\"perfil\":\"";
            objetoJson += aux;
            objetoJson += "\" }";
        }
        objetoJson += "]";
        objetoJson = objetoJson.replaceFirst(",", "");
        out.println(objetoJson);
    }
%>