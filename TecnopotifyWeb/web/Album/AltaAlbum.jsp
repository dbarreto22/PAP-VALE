<%-- 
    Document   : AltaAlbum
    Created on : 08/10/2017, 09:29:12 PM
    Author     : Carlox
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.tecnopotify.interfaces.Artista"%>
<%@page import="edu.tecnopotify.interfaces.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta album</title>
    </head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <jsp:include page="/templates/csss.jsp" />
</head>
<body>
    <h1> Alta album </h1>
    <jsp:include page="/templates/header.jsp" />
    <form action="<%= request.getContextPath()%>/Album"
          method="post">
        <div class ="container">
            Artista:
            <label for="male"><%=request.getSession().getAttribute("user")%></label> <br>
            <%
                Usuario usr = (Usuario) request.getSession().getAttribute("user");
            %>
            <input type="hidden"  name="usr" 
                   value="<%=usr.getNickname()%>" />
            <input type="hidden"  name="comando" value="altaAlbum" />
            Nombre album: <br>
            <input type="text" name="nombreAlbum" /><br>
            Año: <br>
            <input type="number" name="anio" /> <br>
            <input type="submit" value="Enviar" />
            <input>
        </div>
    </form> 
</html>
