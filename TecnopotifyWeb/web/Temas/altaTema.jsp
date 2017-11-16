<%-- 
    Document   : altaTema
    Created on : 12/10/2017, 11:48:14 PM
    Author     : Carlox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="capa">
            <div class="container">
            <form action="<%= request.getContextPath()%>/Temas"  METHOD = "post" onsubmit="return validarDatos()">
                <div class ="container">
             <label for="male"><%=request.getAttribute("id")%></label> <br>
                    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
                    <input type="hidden" name="comando" value="<%=request.getAttribute("comando")%>">
                   Posicion:
                    <input type="number" name="pos" />
                    Nombre Tema: 
                    <input type="text" name="nombreTema" />
                    Duración:
                    <input type="number" name="duracion" /> <br>
                   <input type="submit" value="enviar" />
                </div> 
            </form>
            <form action="<%= request.getContextPath()%>/ppal.jsp"  METHOD = "get" >
                   <input type="submit" value="Cancelar" />
            </form>
            </div>
            <jsp:include page="/templates/scripts.jsp" />
        </div>
    </body>
</html>
