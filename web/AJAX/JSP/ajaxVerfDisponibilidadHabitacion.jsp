<%@page import="co.com.hotel.logica.facturacion.Fac_FacturacionLogica"%>
<%
    // Este ajax se encarga de verificar si la habitacion que quiere reservar tiene disponibilidad
    String habitacion = ""+request.getParameter("habitacion");
    String numDias = ""+request.getParameter("numDias");
    String fechaInicial = ""+ request.getParameter("fecha");
    
    Fac_FacturacionLogica logica = null;
    try{
        logica = new Fac_FacturacionLogica();
        boolean rta = logica.verificaDisHabitacionRangoFechas(fechaInicial, habitacion, numDias);
        if(rta){
            out.println("1");
        }else{
            out.println("0");
        }
        
    }catch(Exception e){
        System.out.println("Error ajaxVerfDisponibilidadHabitacion.jsp " + e);
    }finally{
        logica = null;        
    }
%>