<%@page import="edu.tecnopotify.entidades.Album"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/templates/csss.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar album</title>
    </head>
    <body>
       <jsp:include page="/templates/header.jsp" />
       <div>
        <ol>    
            <% List<Album> lstAlbum=(List<Album>)request.getAttribute("lstAlbum");
            if(lstAlbum!=null){
                for(Album oAlbum:lstAlbum){%>
                    <li><a href="<%= request.getContextPath()%>/Album?comando=mostrarAlbum&idAlbum=<%= oAlbum.getNombre()%>"><%= oAlbum.toString()%></a> 
                    </li>
                <%}
            }else{%>
                 <label for="male">No existen albums para mostrar</label>
             <%}%>    
        </ol>   
        </div>
    </body>
</html>
