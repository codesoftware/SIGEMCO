<%-- 
    Document   : exception
    Created on : 7/06/2014, 04:35:49 PM
    Author     : SOFIA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function error(){
                document.getElementById('formulario').submit();
            }
        </script>
    </head>
    <body onload="setInterval(function(){error()},3000);">
        <h1>Ha ocurrido un error inseperado</h1>
        <form action="logout" id="formulario">
            <input type="hidden" value="chao" name="chao">
        </form>
    </body>
</html>
