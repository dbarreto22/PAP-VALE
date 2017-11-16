<%-- 
    Document   : ConsultarAlbum
    Created on : 21/10/2017, 10:13:32 PM
    Author     : Carlox
--%>


<%@page import="edu.tecnopotify.entidades.Artista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.tecnopotify.entidades.Genero"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/csss.jsp" />
        <jsp:include page="/templates/scripts.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />
        <h1>Consultar Album</h1>
        <form name="FormCombos" method="post" 
              action="<%=request.getContextPath()%>/Album"
              onsubmit="id=Combo(tipo)">
            <div>
                <% List<Genero> lstGenero = (List<Genero>)request.getAttribute("lstGenero");
                    List<Artista> lstArtista = (List<Artista>)request.getAttribute("lstArtista");
                    String id="";
                    String tipo="";%>
                    Genero:
                <select id="GeneroSelect" name="GeneroSelect">
                       <option value=""> </option>
                       <% if (lstGenero != null)
                            for (Genero genero : lstGenero) {%>
                                <option value="<%=genero.getNombre()%>"><%=genero.getNombre()%></option>
                            <%}%>>
                </select>
                Artista:
                <select id="ArtistaSelect" name="ArtistaSelect">
                       <option value=""> </option>
                       <% if (lstGenero != null)
                            for (Artista artista : lstArtista) {%>
                                <option value="<%=artista.getNickname()%>"><%=artista.getNombre()%></option>
                            <%}%>
                </select>
                <input type="hidden" name="comando" value="listarAlbum">
                <input type="submit" value="Enviar" />
            </div>
        </form>
    </body>
</html>
