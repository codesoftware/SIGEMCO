<%@page import="co.com.hotel.logica.productos.Inv_ProductoLogica"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="co.com.hotel.validacion.ValidaCampos"%>
<%@page import="com.google.gson.Gson"%>
<%
    String identificador = ""+request.getParameter("identificador").trim(); //Identificador del producto que va ha insertar
    String cantidad = ""+request.getParameter("cantidad").trim();
    String factura = ""+request.getParameter("factura");
    String rtaJson = "";
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    Gson gson = new Gson();
    Map rta = null;
    ValidaCampos valida = null;
    String idUsuario = usuario.getIdTius();
    Inv_ProductoLogica logica = null;
    try{
        valida = new ValidaCampos();
        rta = new HashMap<String,String>();
        if(valida.validaNulo(identificador) || valida.validaNulo(cantidad) || valida.validaNulo(factura) || valida.validaNulo(idUsuario)){
            logica = new Inv_ProductoLogica();
            String adicion = logica.adicionProductosFactura(factura,identificador , cantidad, idUsuario);
            rta.put("respuesta", adicion);            
        }else{            
            if(valida.validaNulo(idUsuario)){
                rta.put("respuesta", "No se encuentra ningun usuario autenticado");
            }else{
                rta.put("respuesta", "Existen valores Nulos en la adicion de Productos");
            }
        }
        rtaJson = gson.toJson(rta);
    }catch(Exception e){
        System.out.println("Error ajaxAdicionaProductosFact.jsp " + e);
    }finally{
        valida = null;
        logica = null;
    }
    out.write( rtaJson);
%>