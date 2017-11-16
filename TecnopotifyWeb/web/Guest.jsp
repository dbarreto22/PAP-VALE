<%-- 
    Document   : mostrarDatosGuest
    Created on : 22/10/2017, 07:29:11 PM
    Author     : diego-lucia
--%>

<%@page import="edu.tecnopotify.interfaces.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
            <h1>Datos Artista</h1>
        </div>


        <%
            List<Usuario> users = (List<Usuario>) request.getAttribute("usuarios");
            Iterator<Usuario> itU = users.iterator();
            List<String> nickUsr = new ArrayList<>();
            while (itU.hasNext()) {
                nickUsr.add(itU.next().getNickname());
            }
            Iterator<String> itNU = nickUsr.iterator();


        %>

        <form action="/Tecnopotify/usuarios?comando=mostrarClienteGuest" method = "post" >
            <input type="hidden"  name="comando" value="mostrarClienteGuest" />

            <div class="container">
                <select id="ArtistaSelect" name="ArtistaSelect">
                    <option value=""> </option>
                    <% if (nickUsr != null) {
                            for (String nick : nickUsr) {%>
                    <option value="<%=nick%>"><%=nick%></option>
                    <%}
                    } else {%>
                    <h6> No hay Usuarios ingresados </h6>
                    <%}%>
                </select>
                <input type="submit" value="Aceptar"
            </div>

        </form>

        <jsp:include page="/templates/scripts.jsp" />
    </body>
</html>
