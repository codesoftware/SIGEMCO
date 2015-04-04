<%@page import="co.com.sigemco.alfa.inventario.dto.ProductoDto"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.ProductoLogica"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%
    String sede = request.getParameter("sede_sede");
    String dska_dska = request.getParameter("dska_dska");
    String objJson = "";
    Map<String,String> rta = new HashMap<String,String>();
    Gson gson = null;
    ProductoLogica logica = null;
    try{
        gson = new Gson();
        logica = new ProductoLogica();
        ProductoDto  objTo = new ProductoDto();
        objTo.setDska_dska(dska_dska);
        String respuesta = logica.obtenerExistenciasPorSede(objTo, sede);
        if(respuesta == null){
            rta.put("respuesta", "Error ");
        }else{
            rta.put("respuesta", "Ok");
            rta.put("cantidad", respuesta);
        }
    }catch(Exception e){
        e.printStackTrace();
        rta.put("respuesta", "Error " + e);
    }
    objJson = gson.toJson(rta);
    out.print(objJson);
%>