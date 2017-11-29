<%-- 
    Document   : PublicarLista
    Created on : 29/11/2017, 05:56:23 PM
    Author     : Carlox
--%>

<%@page import="edu.tecnopotify.interfaces.ListaParticular"%>
<%@page import="Webservices.ControladorWeb"%>
<%@page import="edu.tecnopotify.interfaces.Cliente"%>
<%@page import="edu.tecnopotify.interfaces.Temas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.tecnopotify.interfaces.ListaReproduccion"%>
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
        <title>Agregar Tema a Lista</title>
        <form name="FormCombos" method="post" 
              action="<%=request.getContextPath()%>/consultarListas">
            <div>
                <% Cliente user=(Cliente) request.getSession().getAttribute("user");
                ControladorWeb webCtr = new ControladorWeb();
                List <ListaParticular> lAux = user.getListasReprParticular();
                %>
                    Listas de Reproduccion:
                    <select id="listRep" name="listRep">
                       <option value=""> </option>
                       <% if (lAux != null)
                            for (ListaReproduccion aux : lAux) {%>
                                <option value="<%=aux.getNombre()%>"><%=aux.getNombre()%></option>
                            <%}%>>
                </select>
                <input type="hidden" name="comando" value="PublicarLista">
                <input type="submit" value="Enviar" />
            </div>
        </form>
    </body>
</html>

