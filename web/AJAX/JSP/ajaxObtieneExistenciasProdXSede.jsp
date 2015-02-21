<%@page import="java.util.HashMap"%>
<%@page import="co.com.sigemco.alfa.inventario.dto.ProductoDto"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="co.com.sigemco.alfa.inventario.logica.ProductoLogica"%>
<%
    String dska_dska = "" + request.getParameter("dska_dska");
    String sede_sede = "" + request.getParameter("sede_sede");
    ProductoLogica logica = null;
    String objJson = "";
    Gson gson = null; 
    Map rtaMap = null;
    try {
        logica = new ProductoLogica();
        gson = new Gson(); 
        ProductoDto producto = new ProductoDto();
        producto.setDska_dska(dska_dska);
        String rta = logica.obtenerExistenciasPorSede(producto, sede_sede);
        rtaMap = new HashMap<String, String>();
        if(rta == null){
            rtaMap.put("respuesta", "Error al consultar cantidades existentes");
        }else{
            rtaMap.put("respuesta", "Ok");
            rtaMap.put("cantidades", rta);
        }
        objJson = gson.toJson(rtaMap);
    } catch (Exception e) {
        e.printStackTrace();
    }
    out.print(objJson);
%>