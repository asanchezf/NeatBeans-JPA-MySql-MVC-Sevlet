<%-- 
    Document   : casa_form
    Created on : 11-ene-2016, 19:03:58
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
        <h1>Registro de casas</h1>
<!--        enctype="multipart/form-data" sube por partes pero  en bb.dd solo el id-->
        <form name="form" method="post" action="<%= request.getContextPath() %>/RegistroCasaServlet" enctype="multipart/form-data">
            
<!--            Para pasar el id en las modificaciones-->
            <input type="hidden" name="id" value="${casa.id}"/>
            
            <div>
                <label for="nombre">
                    Nombre o título que aparecerá en el anuncio
                </label>
                <input type="text"  id="nombre"  value="${casa.nombre}" name="nombre" />
            </div>
            
            <div>
                <label for="ubicacion">
                    Ubicación del inmueble
                </label>
                <input type="text"  id="ubicacion"  value="${casa.ubicacion}"  name="ubicacion"  />
            </div>
            
            <div>
                <label for="descripcion">
                    Pequeña descripción del inmueble
                </label>
                <input type="text"  id="descripcion"  value="${casa.descripcion}"  name="descripcion"  />
            </div>
            
            <div>
                <label for="precio">
                    Precio
                </label>
                <input type="text"  id="precio"  value="${casa.precio}" name="precio" />
            </div>
            
             <div>
                <label for="imagen">
                    Imagen
                </label>
                <label for="foto">Foto</label>
                    <input type="file" name="foto" id="foto"/><br/>
            </div>
            
            <div>
                <input type="submit" value="Registrar"/>
            </div>    
            
        </form>
        
    </body>
</html>
