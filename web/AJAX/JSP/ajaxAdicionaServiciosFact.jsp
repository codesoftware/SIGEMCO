<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page import="com.google.gson.Gson"%>
<%
    String numdias = ""+request.getParameter("num_dias");
    String numPers = ""+request.getParameter("num_pers");
    String factura = ""+request.getParameter("fact_fact");
    String habitacion = ""+request.getParameter("idHabi");
    String fecha = ""+request.getParameter("fecha_resv");
    fecha = fecha.replaceAll("%2F", "/");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    Gson  gson = new Gson();
    String rtaJson = "";
    Fac_FacturacionLogica obj = null;
    Map map = new HashMap<String, String>();
    try{
        if(usuario != null){
            obj = new Fac_FacturacionLogica();
            String rta = obj.creaDetallesServFactura(usuario.getIdTius(),fecha, numdias, habitacion, factura);
            map.put("respuesta", rta);
            System.out.println("Esta es la respuesta: " + rta);
        }        
    }catch(Exception e){    
        System.out.println("Error ajaxAdicionaServiciosFact.jsp " + e);
    }finally{
        
    }
    rtaJson = gson.toJson(map);
    out.write(rtaJson);
    
%>