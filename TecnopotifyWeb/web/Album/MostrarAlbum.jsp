<%-- 
    Document   : MostrarAlbum
    Created on : 23/10/2017, 02:13:37 AM
    Author     : Carlox
--%>

<%@page import="edu.tecnopotify.entidades.Temas"%>
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
            <% List<Temas> lstTemas=(List<Temas>)request.getAttribute("lstTemas");
            if(lstTemas!=null){
                for(Temas oTema:lstTemas){%>
                    <li><%= oTema.toString()%></a> 
                    </li>
                <%}
            }else{%>
                 <label for="male">No existen temas para mostrar</label>
             <%}%>    
        </ol>   
        </div>
    </body>
</html>
