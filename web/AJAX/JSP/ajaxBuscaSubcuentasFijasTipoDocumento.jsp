<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto"%>
<%@page import="co.com.sigemco.alfa.contabilidad.logica.SubCuentaLogica"%>
<%@page import="com.google.gson.Gson"%>
<%
    String tido_nombre = request.getParameter("tido_nombre");
    
    String objJson = "";
    Gson gson = null;
    Map rtaMap = null;
    try{
        SubCuentaLogica logica = new SubCuentaLogica();
        gson = new Gson();
        List<SubCuentaDto> rta = logica.buscaSubCuentasFijasPorTipoDocumento(tido_nombre);
        rtaMap = new HashMap<String,Object>();
        if(rta != null){
            rtaMap.put("respuesta", "Ok");
            rtaMap.put("obj", rta);
        }else{
            //Indica que no hay ninguna subcuenta parametrizada
            rtaMap.put("respuesta", "noParam");            
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    objJson = gson.toJson(rtaMap);
    out.print(objJson);
%>
