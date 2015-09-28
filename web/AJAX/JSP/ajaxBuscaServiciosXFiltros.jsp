<%@page import="com.google.gson.Gson"%>
<%@page import="co.com.hotel.dto.Habitacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%
    String numPersonas = ""+request.getParameter("numPers");
    String numdias = ""+request.getParameter("numDias");
    String fecha = ""+request.getParameter("fecha");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    fecha = fecha.replaceAll("%2F", "/");
    String fechaNueva = "";
    String []array = fecha.split("/");
    fechaNueva = array[1] + "/" + array[0] +"/" +array[2];
    Fac_FacturacionLogica obj = null;
    Gson gson = new Gson();
    String rta = "";
    ArrayList<Habitacion> r = null;
    try{
        obj= new  Fac_FacturacionLogica();
        r = obj.buscarHabitacionesNumPersNumHab(numPersonas, fechaNueva, ""+usuario.getIdTius(), numdias);
    }catch(Exception e){
        System.out.println("Error ajaxBuscaServiciosXFiltros.jsp " + e);
    }finally{
        obj = null;
    }
    rta = gson.toJson(r);
    out.write(rta);
%>