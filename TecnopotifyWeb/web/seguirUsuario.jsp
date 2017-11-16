<%-- 
    Document   : seguirUsuario
    Created on : Oct 14, 2017, 10:20:14 PM
    Author     : menan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguir Usuario</title>
    </head>
    <body>
        <jsp:include page="/templates/csss.jsp" />
        <jsp:include page="/templates/scripts.jsp" />
        <jsp:include page="/templates/header.jsp" />
        <div class ="container">
            <input type="hidden"  name="usr" 
                       value="<%=request.getSession().getAttribute("user")%>" />
            <!-- Para seguir usuarios el cliente debe tener una suscripción vigente. -->
            <h2>Seguir o dejar de seguir usuario</h2>
            <%
                if (request.getSession().getAttribute("user") != null) {
            %>
            <form action="<%= request.getContextPath()%>/seguirUsuario?comando=seguirUsuario"
                  method="post">
                <label for="male">Usuario en línea: <%=request.getSession().getAttribute("user")%></label> <br>

                <input type="checkbox" name="Dejar de seguir" value="Si"/>Dejar de seguir usuario?<br/>
                <input type="hidden"  name="comando" value="seguirUsuario" />
                <input type="text" name="usrASeguir"/><br/>
                <input type="submit" value="Enviar" />
            </form>
            <%
                } else {
            %>
            <h3>No hay usuario con sesión inciada!</h3>
            <a href="login.jsp">Iniciar sesión</a>
            <%
                }
            %>
        </div>
    </body>
</html>
