
<%@page import="edu.tecnopotify.interfaces.Artista"%>
<%@page  import ="edu.tecnopotify.interfaces.Estado"%>
<%@page import="edu.tecnopotify.interfaces.Cliente"%>
<%@page import="edu.tecnopotify.interfaces.Usuario"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    
  String comando = "mostrarCliente";
  String tipo = (String) session.getAttribute("tipoUser");
  if(tipo != null && tipo.contains("Cliente")){
     Cliente cliente = (Cliente) session.getAttribute("user");%>
    <li><a href="<%= request.getContextPath()%>/usuarios?comando=<%=comando%>">Bienvenido <%= cliente.getNickname() %>!</a></li>
    <li><a href="<%= request.getContextPath()%>/autenticar?comando=logout">LogOut</a></li><%
    
        if (cliente.getSuscripcion().getStatus() == Estado.PENDIENTE) {
%>
<li>Solicitud pendiente<br/><ui><a href="/contratarSuscripcion.jsp">Contratar suscripción</a></ui></li>
    <%
        }
        if (cliente.getSuscripcion().getStatus() == Estado.VIGENTE) {
    %>
<li>Solicitud vigente</li>
    <%
        }
        if (cliente.getSuscripcion().getStatus() == Estado.CANCELADA) {
    %>
<li>Solicitud cancelada<br/><ui><a href="/contratarSuscripcion.jsp">Contratar suscripción</a></ui></li>
    <%
        }
        if (cliente.getSuscripcion().getStatus() == Estado.VENCIDA) {
    %>
<li>Solicitud vencida<br/><ui><a href="/contratarSuscripcion.jsp">Contratar suscripción</a></ui></li>
    <%
        }
    %>

<%     
    }else if(tipo != null && tipo.contains("Artista")){
      Artista artista = (Artista) session.getAttribute("user");%>
        <li><a href="<%= request.getContextPath()%>/usuarios?comando=<%=comando%>">Bienvenido <%= artista.getNickname() %>!</a></li>
    <li><a href="<%= request.getContextPath()%>/autenticar?comando=logout">LogOut</a></li>
  
    <%}else{%>
      <li><a href="<%= request.getContextPath()%>/login.jsp">Login</a></li>
  <%}%>

 




