<%@page import="co.com.sigemco.alfa.inventario.logica.ProductoLogica"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="co.com.hotel.logica.movInventario.Inv_MovInLogica"%>
<%
    String dska_dska = "" + request.getParameter("dska_dska");
    String mvin_mvin = "" + request.getParameter("mvin_mvin");
    Inv_MovInLogica logica = null;
    Map rta = null;
    Gson gson = null;
    String rtaJson ="";
    try{
        rta = new HashMap<String,String>();
        gson = new Gson();
        rta = new HashMap<String, String>();
        logica = new  Inv_MovInLogica();
        String naturaleza = logica.consultaNaturalezaMvinXId(mvin_mvin);
        if(naturaleza != null){
            rta.put("naturaleza", naturaleza);
        }else{
            rta.put("naturaleza", "Error al obtener la naturaleza del movimiento");
        }
        logica = null;
        ProductoLogica logicaProd = new ProductoLogica();
        String promPonderado = logicaProd.obtieneValorPonderadoProducto(dska_dska);
        if(promPonderado != null){
            rta.put("promedio", promPonderado);
        }else{
            rta.put("promedio", "Error al obtener valor promedio ponderado");
        }
        rtaJson = gson.toJson(rta);
    }catch(Exception e ){
        e.printStackTrace();
    }
    out.print(rtaJson);
%>