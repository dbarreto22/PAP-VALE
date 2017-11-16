<%-- 
    Document   : dejarDeSeguir
    Created on : Oct 16, 2017, 12:56:16 AM
    Author     : menan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dejar de seguir usuario</title>
    </head>
    <body>
        <jsp:include page="/templates/csss.jsp" />
        <jsp:include page="/templates/scripts.jsp" />
        <jsp:include page="/templates/header.jsp" />
        <div class ="container">
            <input type="hidden"  name="usr" 
                   value="<%=request.getSession().getAttribute("user")%>" />
            <!-- El caso de uso comienza cuando el Cliente quiere contratar una
            suscripción. Para ello el Sistema muestra las opciones de suscripción
            (semanal, mensual o anual) y el cliente elige una. El sistema muestra el
            monto total correspondiente a la suscripción, y si el Cliente confirma se
            crea una suscripción con fecha actual del Sistema en estado = “Pendiente”..-->
            <h2>Contratar suscripción</h2>
            <input type="hidden"  name="comando" value="contratarSuscripcion">

            <%
                if (request.getSession().getAttribute("user") != null) {
            %>
            <form action="<%= request.getContextPath()%>/contratarSuscripcion?comando=contratarSuscripcion"
                  method="post">
                <label for="male">Usuario en línea: <%=request.getSession().getAttribute("user")%></label> <br/>
                <!--LISTAR TIPOS DE SUSCRIPCIONES POSIBLES
                public enum estado 
                PENDIENTE, VENCIDA, VIGENTE, CANCELADA
                public enum pago 
                SUSCRIPCION_PENDIENTE, SEMANAL, MENSUAL, ANUAL
                -->
                <input type="checkbox" name="suscripcionSemanal" value="Si"/>SEMANAL<br/>
                <input type="checkbox" name="suscripcionMensual" value="Si"/>MENSUAL<br/>
                <input type="checkbox" name="suscripcionAnual" value="Si"/>ANUAL<br/>

                <input type="hidden"  name="comando" value="contratarSuscripcion">
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
