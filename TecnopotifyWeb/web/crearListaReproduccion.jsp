<%-- 
    Document   : crearListaReproduccion
    Created on : 23/10/2017, 05:55:10 PM
    Author     : diego-lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Reproduccion</title>
        <jsp:include page="/templates/csss.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />
        <div class="container">
            <div class="container">
                <h4>Crear Lista Reproduccion</h4>
            </div>

            <form action="/Tecnopotify/usuarios?comando=listaRep" method = "post" 
                  onsubmit="return validarDatos()" >
                <input type="hidden"  name="comando" value="listaRep" />
                <div class="container">
                    <label>Nombre: </label>
                    <input type="text" name="nombre" id="nombreL" /><br /> 

                    <input type="submit" name="aceptar" value="Aceptar" id="nombreL" />

                </div>
                </body>
                </html>
