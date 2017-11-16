<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
        <jsp:include page="/templates/csss.jsp" />
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #eee;
            }

            .form-signin {
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
            }
            .form-signin .form-signin-heading,
            .form-signin .checkbox {
                margin-bottom: 10px;
            }
            .form-signin .checkbox {
                font-weight: normal;
            }
            .form-signin .form-control {
                position: relative;
                height: auto;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                padding: 10px;
                font-size: 16px;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        </style>
    </head>
    <body>

        <jsp:include page="/templates/scripts.jsp" />
        <jsp:include page="/templates/header.jsp" />
        <div class="container">
            <form class="form-signin" method="POST"
                  action="<%= request.getContextPath()%>/autenticar">
                <h2 class="form-signin-heading">Iniciar sesión</h2>
                <%
                    String error = (String) request.getAttribute("error");
                    if ((error != null) && !error.isEmpty()) {
                %>
                <p class="bg-warning"><%= error%></p>
                <%
                    }
                %>

                <label for="fuser" class="sr-only">NickName</label>
                <input type="text" id="fuser" id="nickname" class="form-control" 
                       placeholder="NickName o Email" required autofocus name="user">

                <label for="fpass" class="sr-only" id="contrasenia" >Contraseña</label>
                <input type="password" id="fpass" class="form-control" 
                       placeholder="Contraseña" required name="pass">

                <input type="hidden"  name="comando" value="login">

                <button class="btn btn-lg btn-primary btn-block" 
                        type="submit">Sign in</button>
            </form>

        </div> 




    </body>
</html>
