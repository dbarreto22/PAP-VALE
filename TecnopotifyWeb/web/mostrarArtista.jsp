<%-- 
    Document   : mostrarArtista
    Created on : 09/10/2017, 07:50:20 PM
    Author     : diego-lucia
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.tecnopotify.interfaces.Artista"%>
<%@page import="edu.tecnopotify.interfaces.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Artista</title>
        <jsp:include page="/templates/csss.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />

        <div class="container">
            <h3>Datos Artista</h3>
        </div>

        <%
            List<String> listAl = (ArrayList) request.getAttribute("albumArt");
            Iterator<String> itAl = listAl.iterator();


        %>

        <form   method = "post" >
            <input type="hidden"  name="comando" value="mostrarArtista" />
            <div class="container">
                <h6>NickName: </h6>
                <input type="text"  value =<%= request.getAttribute("nickName")%> /> <br/>
                <h6>Nombre: </h6>
                <input type="text"  value =<%= request.getAttribute("nombre")%> /> <br/>
                <h6>Apellido </h6>
                <input type="text"  value =<%= request.getAttribute("apellido")%> /> <br/>
                <h6>Mail: </h6>
                <input type="text"  value =<%= request.getAttribute("mail")%> /> <br/>
                <h6>Fecha de Nacimiento: </h6>
                <input type="text"  value =<%= request.getAttribute("dia")%> /> / 
                <input type="text"  value =<%= request.getAttribute("mes")%> />
                <input type="text"  value =<%= request.getAttribute("anio")%> />
                <br/>
                <h6>Biografia: </h6>
                <input type="text"  value =<%= request.getAttribute("biografia")%> /> <br/>
                <h6>Link: </h6>
                <input type="text"  value =<%= request.getAttribute("link")%> /> <br/>


                <h6>Imagen: </h6>
                <img type="text"  src="<%= request.getAttribute("imagen")%>" /> <br/>

                <h6>Lista de Album Favoritos: </h6>
                <%if (!listAl.isEmpty()) {
                        while (itAl.hasNext()) {%> 
                <ol>
                    <li> <a href="<%= request.getContextPath()%>/Album/MostrarAlbum.jsp"> <%out.print(itAl.next()); %></a></li> </li>
                </ol>
                <%}
                } else {%>
                <h6>NO tiene Albums</h6>
                <%}%>

            </div>
        </form> 
    </div>

    <jsp:include page="/templates/scripts.jsp" />
</body>
</html>
