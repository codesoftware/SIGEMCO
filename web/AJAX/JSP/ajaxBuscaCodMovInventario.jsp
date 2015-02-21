<%@page import="co.com.hotel.persistencia.general.EnvioFunction"%>
<%@page import="java.sql.ResultSet"%>
<%
    String codigo = request.getParameter("codigo");
    String sql = "";
    ResultSet rs = null;
    sql = "select case\n";
    sql += "when mvin_natu = 'I' then 'Ingreso'\n";
    sql += "else 'Egreso'\n";
    sql += "end naturaleza\n";
    sql += "from in_tmvin\n";
    sql += "where mvin_mvin ="+ codigo;
    EnvioFunction function = new EnvioFunction();
    try {
        rs = function.enviarSelect(sql);
        if(rs.next()){
            out.write(rs.getString("naturaleza"));                        
        }
    } catch (Exception e) {
        System.out.println("Error ajaxBuscaCodMovInventario " + e);
    } finally {
    }
%>