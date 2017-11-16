<%-- 
    Document   : MostrarPersonas
    Created on : 19/09/2016, 07:15:42 PM
    Author     : emi
--%>

<%@page import="edu.tecnopotify.interfaces.Usuario"%>
<%@page import="java.util.List"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Cliente</title>
        <jsp:include page="/templates/csss.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />
        <div class="container">
            <h1>Alta Cliente</h1>
        </div>

        <form action="/Tecnopotify/usuarios?comando=altaCliente" method = "post" 
              onsubmit="return validarDatos()" >
            <input type="hidden"  name="comando" value="AltaCliente" />
            <div class="container">
                <label>Nickname: </label>
                <input type="text" name="nickname" id="nickname" /><br />
                Contraseña:
                <input type="password" name="contrasenia" id ="contrasenia"/> <br />
                Ingrese Contraseña nuevamente:
                <input type="password" name="validarContrasenia" id = "validarContrasenia"/> <br />
                Ingrese Nombre:
                <input type="text" name="nombre" /> <br />
                Ingrese Apellido:
                <input type="text" name="apellido" /> <br />
                Ingrese Fecha de Nacimiento:<br />
                Dia 
                <input type="number" name="dia" min="0" max ="30" />
                Mes
                <input type="month" name="mes" />
                Año
                <input type="number" name="anio"  min="1920" max ="2007"/> <br />  
                Mail
                <input type ="email" name ="mail"  id="mail"/> <br/>
                
                <input type="submit" value="Enviar" />
            </div>
        </form> 


        <jsp:include page="/templates/scripts.jsp" />
    </body>
</html>

