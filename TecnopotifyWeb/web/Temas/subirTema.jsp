<%-- 
    Document   : subirTema
    Created on : 18/10/2017, 07:35:20 PM
    Author     : Carlox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../templates/csss.jsp" />        
    </head>
    <body>

        <jsp:include page="../templates/header.jsp" />

        <div class="container" style="margin-top: 60px">

            <form class="form-horizontal" 
                  action="<%= request.getContextPath() %>/imagenes?id=<%= request.getAttribute("id") %>&comando=<%=request.getAttribute("comando")%>&idAlbum=<%= request.getAttribute("idAlbum") %>"  
                  method = "post" enctype = "multipart/form-data">  
                <div class="form-group">
                    <label for="audioFile" class="col-sm-2 control-label">Audio</label>
                    <div class="col-sm-10">
                        <input type="file" 
                           id="audioFile" name="faudio1" 
                            accept="audio/*" />
                        <p class="help-block">Subir cualquier tipo de audio.</p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Enviar</button>
                    </div>
                </div>
            </form>

        </div> <!-- /container -->
        <jsp:include page="../templates/scripts.jsp" />
    </body>
</html>
