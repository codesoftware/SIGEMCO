<%@page import="com.google.gson.Gson"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.RemisionLogica"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
    String tppl = "" + request.getParameter("tppl");
    String objJson = "";
    Map rta = new HashMap<String, String>();
    RemisionLogica logica = null;
    Gson gson = new Gson();
    try{
        logica = new RemisionLogica();
        String rtaAux = logica.consultaValorComisionXtppl(tppl);
        if(rtaAux == null){
            rta.put("respuesta", "Error");
            rta.put("mensaje", "Error la comision no esta parametrizada para este tipo de plan");
        }else{
            rta.put("respuesta", "Ok");
            rta.put("valor", rtaAux);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    objJson = gson.toJson(rta);
    out.print(objJson);
%>