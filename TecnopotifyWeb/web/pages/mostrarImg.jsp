<%-- 
    Document   : SubiendoImagenes
    Created on : 06/10/2017, 04:36:03 PM
    Author     : emi
--%>
<%-- 
    Document   : SubiendoImagenes
    Created on : 06/10/2017, 04:36:03 PM
    Author     : emi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="templates/csss.jsp" />        
    </head>
    <body>

        <jsp:include page="templates/header.jsp" />

        <div class="container" style="margin-top: 60px">

            <img 
                src="<%= request.getContextPath() + "/imagenes/" + request.getAttribute("imagen") %>" />

        </div> <!-- /container -->
        <jsp:include page="templates/script.jsp" />
    </body>
</html>

