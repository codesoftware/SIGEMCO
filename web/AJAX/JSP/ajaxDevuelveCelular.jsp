<%@page import="java.util.HashMap"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.RemisionLogica"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%
    String rmce_rmce = request.getParameter("rmce_rmce");
    String rmce_comdev = request.getParameter("rmce_comdev");
    Gson gson = null;
    String objetoJson = "";
    RemisionLogica logica = new RemisionLogica();
    String aux = "";
    Map respuesta = null;
    try {

        gson = new Gson();
        aux = logica.devolverCelular(rmce_rmce, rmce_comdev);
            respuesta = new HashMap<String, String>();
            respuesta.put("rta", aux);
     
        objetoJson = gson.toJson(respuesta);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        gson = null;

    }
    out.print(objetoJson);

%>