<%-- 
    Document   : mostrarC
    Created on : 18/10/2017, 07:17:14 PM
    Author     : diego-lucia
--%>

<%@page import="edu.tecnopotify.interfaces.IControlador"%>
<%@page import="edu.tecnopotify.fabrica.Fabrica"%>
<%@page import="edu.tecnopotify.entidades.ListaParticular"%>
<%@page import="edu.tecnopotify.entidades.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Cliente</title>
        <jsp:include page="/templates/csss.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />
        <div class="container">
            <h3>Datos Cliente</h3>
        </div>
        <%
            //List<String> seguidores = (ArrayList) request.getAttribute("litseg");
            //Iterator<String> itS = seguidores.iterator();
            List<String> listPart = (ArrayList) request.getAttribute("repPropia");
            Iterator<String> itR = listPart.iterator();
            List<String> album = (ArrayList) request.getAttribute("album");
            Iterator<String> itA = album.iterator();
            List<String> lisRep = (ArrayList) request.getAttribute("listRep");
            Iterator<String> itLR = lisRep.iterator();
        %>


        <form   method = "post" >
            <input type="hidden"  name="comando" value="mostrarCliente" />
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



                <%--          <h6>Lista de Seguidores: </h6>

                <%if (!seguidores.isEmpty()) {
                        while (itS.hasNext()) {%> 
                <ol>
                    <li>  <%out.print(itR.next()); %></li> 
                </ol>
                <%}
                } else {%>
                <h6>NO hay Usuarios que lo sigan</h6>
                <%}%>--%>
                
                <h6>Lista de Reproducciones Particulares: </h6>
                <% if (!listPart.isEmpty()) {
                        while (itR.hasNext()) {%> 
                <ol>
                    <li> <a href="<%= request.getContextPath()%>/mostrarListaReproduccion.jsp"> <%out.print(itR.next()); %></a></li>
                </ol>
                <%}
                } else {%>
                <h6>NO hay listas de reproducciones favoritas</h6>
                <%}%>

                <h6>Lista de Reproduccione Favoritas: </h6>
                <% if (!lisRep.isEmpty()) {
                        while (itLR.hasNext()) {%> 
                <ol>
                    <li> <a href="<%= request.getContextPath()%>/mostrarListaReproduccion.jsp"> <%out.print(itLR.next()); %></a></li>
                </ol>
                <%}
                } else {%>
                <h6>NO hay listas de reproducciones favoritas</h6>
                <%}%>


                <h6>Lista de Album Favoritos: </h6>
                <% if (!album.isEmpty()) {
                    String nom;
                        while (itA.hasNext()) {
                            nom = itA.next();                     %> 
                <ol>
                    <li> <a href="<%= request.getContextPath()%>/Album?idAlbum=<%= nom %>&comando=mostrarAlbum"> <%= nom %></a></li>
                </ol>
                <%}
                } else {%>
                <h6>NO tiene Albums Favoritos</h6>
                <%}%>

            </div>
        </form>   

        <jsp:include page="/templates/scripts.jsp" />
    </body>
</html>
