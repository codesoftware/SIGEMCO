<%@page import="co.com.hotel.logica.usuarios.CambiaEstadoUsua"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario = request.getParameter("usuario");
    String opcion  = request.getParameter("opcion");
    int intOpcion = Integer.parseInt(opcion);
    if (intOpcion == 3){
        CambiaEstadoUsua cambia = new CambiaEstadoUsua();
        boolean confirma = cambia.cambiaEstado(usuario);
        if (confirma){
            %>OK<%
        }else{
            %>ERR<%
        }
    }    
%>