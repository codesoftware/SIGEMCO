<%@page import="co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="co.com.sigemco.alfa.contabilidad.logica.SubCuentaLogica"%>
<%@page import="com.google.gson.Gson"%>
<%
    String sbcu_codigo = ""+request.getParameter("sbcu_codigo");
    String objJson = "";
    Gson gson = null;
    Map rtaMap = null;
    try{
        SubCuentaLogica logica = new SubCuentaLogica();
        gson = new Gson();
        String rta = logica.validaExistenciaSubCuenta(sbcu_codigo);
        rtaMap = new HashMap<String,Object>();
        if(rta.equalsIgnoreCase("SI")){
            SubCuentaDto objDto = logica.buscaSubcuentaXCodigo(sbcu_codigo);
            if(objDto == null){
                rtaMap.put("respuesta", "error");
            }else{
                rtaMap.put("objeto", objDto);
                
            }
        }else if(rta.equalsIgnoreCase("NO")){
            rtaMap.put("respuesta", "inexistente");
        }else{
            rtaMap.put("respuesta", "error");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    objJson = gson.toJson(rtaMap);
    out.print(objJson);
%>