
<%@page import="edu.tecnopotify.entidades.Usuario"%>
<%@page import="edu.tecnopotify.fabrica.Fabrica"%>
<%@page import="edu.tecnopotify.interfaces.IControlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath()%>/bower_components/jquery/dist/jquery.js" type="text/javascript" ></script>
<script src="<%= request.getContextPath()%>/bower_components/bootstrap/dist/js/bootstrap.js" type="text/javascript" ></script>
<script>
    function buscarAjax() {
        var queryStr = $("#buscar-query").val();
        $.get("<%= request.getContextPath()%>/buscar?query=" + queryStr, function (data) {
            console.info("El texto buscado fue... " + data);
        });
    }
</script>

<script>
    function validarDatos() {
        var esCorrecto = true;
        var nickname = $('#nickname').val();
        if (nickname === '') {
            alert("Ingrese Nickname");
            esCorrecto = false;
        }
        var valcontrasenia = $('#validarContrasenia').val();
        if (valcontrasenia === '') {
            alert("Ingrese Contraseña");
            esCorrecto = false;
        }
        var contrasenia = $('#contrasenia').val();
        if (contrasenia === '') {
            alert("Ingrese Contraseña");
            esCorrecto = false;
        }
        if (contrasenia !== valcontrasenia) {
            alert("Contraseña incorrecta, Ingrese nuevamente");
            esCorrecto = false;
        }
        var nombreL = $('#nombreL').val();
        if (nombreL === '') {
            alert("Ingrese nombre");
            esCorrecto = false;
        }

        var mail = $('#mail').val();
        if (mail === '') {
            alert("Ingrese mail");
            esCorrecto = false;
        }

        return esCorrecto;
    }
</script>

<script type="text/javascript">
    function Combo(tipo) {
        tipo = "Genero";
        var index = document.forms[0].elements[0].value;
        if (index === "" || index === null)
        {
            var index = document.forms[0].elements[1].value;
            if (index === "" || index === null)
            {
                alert("Debe seleccionar un album o un genero");
            } else
                tipo = "Artista";
        }
        return index;

    }
</script>

