<%-- 
    Document   : casa_reg_respuesta
    Created on : 11-ene-2016, 20:32:04
    Author     : Susana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro creado correctamente</h1>
        
        <p>Se ha creado el registro con el nuevo inmueble</p>
<!--Casa.id: se debe poner el setAttibute con mayúsculas pq se definió así en el Servlet RegistroCasaServlet: request.setAttribute("Casa", casa)-->
        <p>Para editar sus datos, haga clic <a href='<%= request.getContextPath()+"/CargarCasa?id=" %>${Casa.id}'>aquí</a></p>
    </body>
</html>
