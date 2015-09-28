<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.com.hotel.datos.session.Usuario"%>
<%@page import="co.com.hotel.json.creacion.JSONObject"%>
<%@page import="co.com.hotel.logica.usuarios.ConsultaUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String filtro = request.getParameter("cadena");
    try {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        ConsultaUsuarios consulta = new ConsultaUsuarios();
        boolean exito = consulta.buscaUsuariosXUsua(filtro);
        Map<String, String> listaMap = new HashMap<String, String>();
        ArrayList<Usuario> usuariosArrayList = new ArrayList<Usuario>();
        usuariosArrayList = consulta.getUsuaResult();
        String objetoJson = "";
        objetoJson = "[";
        int valida = 1;
        if (opcion == valida) {
            for (Usuario datoUsuario : usuariosArrayList) {
                objetoJson += ",{\"nombre\":\"";
                objetoJson += datoUsuario.getNombre();
                objetoJson += "\" , ";
                objetoJson += "\"apellido\":\"";
                objetoJson += datoUsuario.getApellido();
                objetoJson += "\" , ";
                objetoJson += "\"usuario\":\"";
                objetoJson += datoUsuario.getUsuario();
                objetoJson += "\" }";
            }
        } else if (opcion == 2) {
            for (Usuario datoUsuario : usuariosArrayList) {
                objetoJson += ",{\"nombre\":\"";
                objetoJson += datoUsuario.getNombre();
                objetoJson += " " + datoUsuario.getApellido();
                objetoJson += "\" , ";
                objetoJson += "\"usuario\":\"";
                objetoJson += datoUsuario.getUsuario();
                objetoJson += "\" , ";
                objetoJson += "\"cedula\":\"";
                objetoJson += datoUsuario.getCedula();
                objetoJson += "\" , ";
                objetoJson += "\"correo\":\"";
                objetoJson += datoUsuario.getCorreo();
                objetoJson += "\" , ";
                objetoJson += "\"telefono\":\"";
                objetoJson += datoUsuario.getTelefono();
                objetoJson += "\" , ";
                objetoJson += "\"tipo_usuario\":\"";
                objetoJson += datoUsuario.getTipoUsuario();
                objetoJson += "\" }";
            }
        }
        objetoJson += "]";
        objetoJson = objetoJson.replaceFirst(",", "");
        out.println(objetoJson);
    } catch (Exception e) {
        System.out.println("Error AjaxBuscaUsuarios.jsp " + e);
    }
%>
