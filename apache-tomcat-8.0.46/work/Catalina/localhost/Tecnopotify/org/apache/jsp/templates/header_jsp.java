/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.46
 * Generated at: 2017-11-28 03:51:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.templates;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import Webservices.ControladorWeb;
import edu.tecnopotify.interfaces.Cliente;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("java.util.Calendar");
    _jspx_imports_classes.add("java.util.GregorianCalendar");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("Webservices.ControladorWeb");
    _jspx_imports_classes.add("edu.tecnopotify.interfaces.Cliente");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Fixed navbar -->\n");
      out.write("<nav class=\"navbar navbar-default navbar-fixed-top\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"navbar-header\">\n");
      out.write("            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">\n");
      out.write("                <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("            </button>\n");
      out.write("            <a class=\"navbar-brand\" href=\"#\">Tecnopotify!</a>\n");
      out.write("            ");

                String ip, browser, url, SO;
                
                ControladorWeb ctr = new ControladorWeb();
                ip = request.getRemoteAddr();
                url = request.getRequestURL().toString();
                browser = request.getHeader("user-agent");             
                ctr.registroUsuariosCount(ip, url, browser);    
            
      out.write("\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        <div id=\"navbar\" class=\"navbar-collapse collapse\">\n");
      out.write(" \n");
      out.write("            <ul class=\"nav navbar-nav\">\n");
      out.write("                <li><a href=\"");
      out.print( request.getContextPath());
      out.write("\">Home</a></li>\n");
      out.write("                <li class=\"dropdown\">\n");
      out.write("                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Usuarios <span class=\"caret\"></span></a>\n");
      out.write("                    <ul class=\"dropdown-menu\">\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/AltaCliente.jsp\">Crear Cliente</a></li>\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/altaArtista.jsp\">Crear Artista</a></li>\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/cboArtistas?comando=");
      out.print("CargarUsuarios");
      out.write("\">Mostrar datos de Usuarios</a></li>\n");
      out.write("                            ");
 String comando = "mostrarCliente"; 
                                String tipo = (String) session.getAttribute("tipoUser");
                                    
                                    if(tipo != null && tipo.contains("Cliente")){
                                        Cliente cli = (Cliente) session.getAttribute("user");
                                    
                                            if (cli.getSuscripcion().getStatus().equals("VIGENTE")) {
      out.write("\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/crearListaReproduccion.jsp\"  >Crear Lista Particular</a></li>\n");
      out.write("                            ");
}
      out.write("                           \n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/usuarios?comando=");
      out.print(comando);
      out.write("\"  >Datos Usuario Logueado</a></li>                \n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/seguirUsuario.jsp\">Seguir/Dejar de seguir Usuario</a></li>\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/contratarSuscripcion.jsp\">Contratar Suscripción</a></li>\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/actualizarSuscripcion.jsp\">Actualizar Suscripción</a></li>\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/crearListaReproduccion.jsp\">Crear Lista Particular</a></li>\n");
      out.write("                        ");
      }
                        
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"Album\">\n");
      out.write("                    <a href=\"#\" class=\"Album\" data-toggle=\"dropdown\"\n");
      out.write("                       role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Album <span class=\"caret\"></span></a>\n");
      out.write("                    <ul class=\"dropdown-menu\">\n");
      out.write("                        ");
 
      out.write("\n");
      out.write("                        ");
 if (tipo != null && tipo.contains("Artista")) {
      out.write("\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/Album/AltaAlbum.jsp\">Crear album</a></li>\n");
      out.write("                            ");
}
                             
      out.write("   \n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/Album\">album</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"Temas\">\n");
      out.write("                    <a href=\"#\" class=\"Temas\" data-toggle=\"dropdown\"\n");
      out.write("                       role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Temas <span class=\"caret\"></span></a>\n");
      out.write("                        ");
 
      out.write("\n");
      out.write("                    <ul class=\"dropdown-menu\">\n");
      out.write("                        ");
 if (tipo != null && tipo.contains("Cliente")) {
      out.write("\n");
      out.write("                        <li><a href=\"");
      out.print( request.getContextPath());
      out.write("/Tema\">Agregar tema a lista</a></li>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                            ");

      out.write("                                                    \n");
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/templates/loginfo.jsp", out, false);
      out.write("      \n");
      out.write("\n");
      out.write("            </ul>\n");
      out.write("        </div><!--/.nav-collapse -->\n");
      out.write("    </div>\n");
      out.write("</nav>");
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
