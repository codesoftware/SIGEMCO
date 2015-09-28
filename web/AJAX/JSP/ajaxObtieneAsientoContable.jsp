<%@page import="co.com.sigemco.alfa.contabilidad.dto.MoviContableDto"%>
<%@page import="java.util.List"%>
<%@page import="co.com.sigemco.alfa.contabilidad.logica.MoviContableLogica"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%
    String mvco_trans = ""+request.getParameter("mvco_trans");
    Gson gson = null;
    String objJson = "";
    Map<String, Object> rta = null;
    MoviContableLogica objLogica = null;
    try{
        gson = new Gson();
        rta = new HashMap<String,Object>();
        objLogica = new MoviContableLogica();
        List<MoviContableDto> lista = objLogica.generaAsientoContable(mvco_trans);
        if(lista != null){
            rta.put("respuesta", "Ok");
            rta.put("list", lista);
        }else{
            rta.put("respuesta", "Error"); 
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    objJson = gson.toJson(rta);
    out.print(objJson);
%>