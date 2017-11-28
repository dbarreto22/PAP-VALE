<%-- 
    Document   : agregarTemaLista
    Created on : 27/11/2017, 11:40:22 PM
    Author     : Carlox
--%>

<%@page import="edu.tecnopotify.interfaces.ListaParticular"%>
<%@page import="edu.tecnopotify.interfaces.Cliente"%>
<%@page import="edu.tecnopotify.interfaces.Album"%>
<%@page import="edu.tecnopotify.interfaces.Temas"%>
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
        <title>Agregar Tema a Lista</title>
        <form name="FormCombos" method="post" 
              action="<%=request.getContextPath()%>/Temas">
            <div>
                <% List<Temas> lstTemas=(List<Temas>)request.getAttribute("lstTema");
                Cliente user=(Cliente) request.getSession().getAttribute("user");
                List<ListaParticular> lstRep=user.getListasReprParticular();%>
                    Listas de Reproduccion:
                    <select id="listRep" name="listRep">
                       <option value=""> </option>
                       <% if (lstRep != null)
                            for (ListaParticular aux : lstRep) {%>
                                <option value="<%=aux.getNombre()%>"><%=aux.getNombre()%></option>
                            <%}%>>
                </select>
                Album:
                <select id="temaSelect" name="temaSelect">
                       <option value=""> </option>
                       <% if (lstTemas != null)
                            for (Temas aux2 : lstTemas) {%>
                                <option value="<%=aux2.getNombre()%>"><%=aux2.getNombre()%></option>
                            <%}%>
                </select>
                <input type="hidden" name="comando" value="AgregarTema">
                <input type="submit" value="Enviar" />
            </div>
        </form>
    </body>
</html>
