/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import edu.tecnopotify.interfaces.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author menan
 */
public class ServletActualizarSuscripcion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cliente cliente = (Cliente) request.getSession().getAttribute("user");
        String nickUsr = cliente.getNickname();
        String suscripcion = obtenerEstadoSuscripcion(nickUsr);
        String pago = obtenerPagoSuscripcion(nickUsr);
        request.setAttribute("susc", suscripcion);
        request.setAttribute("pago", pago);
        request.getRequestDispatcher("actualizarSuscripcion.jsp").forward(request, response);
                        
        if (request.getParameter("suscripcionCancelada") != null) {
            modificarSuscripcion((String) request.getSession().getAttribute("user"), "CANCELADA", "");
        }

        if (request.getParameter("suscripcionCancelada") != null || request.getParameter("suscripcionVigente") != null) {
            if (request.getParameter("suscripcionCancelada") != null) {
                modificarSuscripcion((String) request.getSession().getAttribute("user"), "CANCELADA", "");
            }
            if (request.getParameter("suscripcionVigente") != null) {
                modificarSuscripcion((String) request.getSession().getAttribute("user"), "VIGENTE", "SEMANAL");
            }
        }
        
        request.getRequestDispatcher("/ppal.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String obtenerEstadoSuscripcion(java.lang.String arg0) {
        edu.tecnopotify.interfaces.ControladorService service = new edu.tecnopotify.interfaces.ControladorService();
        edu.tecnopotify.interfaces.Controlador port = service.getControladorPort();
        return port.obtenerEstadoSuscripcion(arg0);
    }

    private static String obtenerPagoSuscripcion(java.lang.String arg0) {
        edu.tecnopotify.interfaces.ControladorService service = new edu.tecnopotify.interfaces.ControladorService();
        edu.tecnopotify.interfaces.Controlador port = service.getControladorPort();
        return port.obtenerPagoSuscripcion(arg0);
    }

    private static void modificarSuscripcion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        edu.tecnopotify.interfaces.ControladorService service = new edu.tecnopotify.interfaces.ControladorService();
        edu.tecnopotify.interfaces.Controlador port = service.getControladorPort();
        port.modificarSuscripcion(arg0, arg1, arg2);
    }

}
