/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.46
 * Generated at: 2017-10-25 22:43:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.templates;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import edu.tecnopotify.entidades.Usuario;
import edu.tecnopotify.fabrica.Fabrica;
import edu.tecnopotify.interfaces.IControlador;

public final class scripts_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("edu.tecnopotify.entidades.Usuario");
    _jspx_imports_classes.add("edu.tecnopotify.fabrica.Fabrica");
    _jspx_imports_classes.add("edu.tecnopotify.interfaces.IControlador");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"");
      out.print( request.getContextPath());
      out.write("/bower_components/jquery/dist/jquery.js\" type=\"text/javascript\" ></script>\n");
      out.write("<script src=\"");
      out.print( request.getContextPath());
      out.write("/bower_components/bootstrap/dist/js/bootstrap.js\" type=\"text/javascript\" ></script>\n");
      out.write("<script>\n");
      out.write("    function buscarAjax() {\n");
      out.write("        var queryStr = $(\"#buscar-query\").val();\n");
      out.write("        $.get(\"");
      out.print( request.getContextPath());
      out.write("/buscar?query=\" + queryStr, function (data) {\n");
      out.write("            console.info(\"El texto buscado fue... \" + data);\n");
      out.write("        });\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    function validarDatos() {\n");
      out.write("        var esCorrecto = true;\n");
      out.write("        var nickname = $('#nickname').val();\n");
      out.write("        if (nickname === '') {\n");
      out.write("            alert(\"Ingrese Nickname\");\n");
      out.write("            esCorrecto = false;\n");
      out.write("        }\n");
      out.write("        var valcontrasenia = $('#validarContrasenia').val();\n");
      out.write("        if (valcontrasenia === '') {\n");
      out.write("            alert(\"Ingrese Contraseña\");\n");
      out.write("            esCorrecto = false;\n");
      out.write("        }\n");
      out.write("        var contrasenia = $('#contrasenia').val();\n");
      out.write("        if (contrasenia === '') {\n");
      out.write("            alert(\"Ingrese Contraseña\");\n");
      out.write("            esCorrecto = false;\n");
      out.write("        }\n");
      out.write("        if (contrasenia != valcontrasenia) {\n");
      out.write("            alert(\"Contraseña incorrecta, Ingrese nuevamente\");\n");
      out.write("            esCorrecto = false;\n");
      out.write("        }\n");
      out.write("        var nombreL = $('#nombreL').val();\n");
      out.write("        if (nombreL === '') {\n");
      out.write("            alert(\"Ingrese nombre\");\n");
      out.write("            esCorrecto = false;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        return esCorrecto;\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    function Combo(tipo) {\n");
      out.write("        tipo = \"Genero\";\n");
      out.write("        var index = document.forms[0].elements[0].value;\n");
      out.write("        if (index === \"\" || index === null)\n");
      out.write("        {\n");
      out.write("            var index = document.forms[0].elements[1].value;\n");
      out.write("            if (index === \"\" || index === null)\n");
      out.write("            {\n");
      out.write("                alert(\"Debe seleccionar un album o un genero\");\n");
      out.write("            } else\n");
      out.write("                tipo = \"Artista\";\n");
      out.write("        }\n");
      out.write("        return index;\n");
      out.write("\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}