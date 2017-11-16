<%-- 
    Document   : basico
    Created on : 20/09/2016, 12:41:06 PM
    Author     : emi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tecnopotify</title>
        <jsp:include page="/templates/csss.jsp" />        
    </head>
    <body>

        <jsp:include page="/templates/header.jsp" />

        <div class="container">


            <!-- Jumbotron -->
            <div class="jumbotron">
                <h1>Mascotas sueltas</h1>
                <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet.</p>
            </div>

            <!-- Example row of columns -->
            <div class="row">
                <div class="col-lg-4">
                    <h2>Safari bug warning!</h2>
                    <p class="text-danger">As of v9.1.2, Safari exhibits a bug in which resizing your browser horizontally causes rendering errors in the justified nav that are cleared upon refreshing.</p>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-primary" href="#" role="button">View details &raquo;</a></p>
                </div>
                <div class="col-lg-4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-primary" href="#" role="button">View details &raquo;</a></p>
                </div>
                <div class="col-lg-4">
                    <h2>Heading</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
                    <p><a class="btn btn-primary" href="#" role="button">View details &raquo;</a></p>
                </div>
            </div>

            <!-- Site footer -->
            <footer class="footer">
                <p>&copy; 2016 Company, Inc.</p>
            </footer>

        </div> <!-- /container -->
        <jsp:include page="/templates/scripts.jsp" />
    </body>
</html>
