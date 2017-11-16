<%-- 
    Document   : clienteguest
    Created on : 22/10/2017, 08:20:09 PM
    Author     : diego-lucia
--%>

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
            <h1>Invitado</h1>
        </div>

        <%
            List<String> listp = (ArrayList) request.getAttribute("lpS");
            Iterator<String> itlp = listp.iterator();

        %>


        <form   method = "post" >
            <input type="hidden"  name="comando" value="mostrarCliente" />
            <div class="container">
                <h6>NickName: </h6>
                <input type="text"  value =<%= request.getAttribute("nickName")%> /> <br/>
                <h6>Imagen: </h6>
                <img 
                    src="<%= request.getContextPath() + "/imagenes/" + request.getAttribute("imagen")%>" />

                <h6>Lista de Reproduccion: </h6>
                <% while (itlp.hasNext()) {%> 
                 <ol>
                    <li> <a href="<%= request.getContextPath()%>/mostrarListaReproduccion.jsp"> <%out.print(itlp.next()); %></a></li>
                </ol>
                <%}%>

                <body>

                    <jsp:include page="/templates/scripts.jsp" />
                </body>
                </html>
