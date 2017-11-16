/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;


import static Webservices.ControladorWeb.dejarDeSeguirUsuario;
import static Webservices.ControladorWeb.seguirUsuario;
import static Webservices.ControladorWeb.seleccionarCliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//*************************WEB***********************
import edu.tecnopotify.interfaces.Cliente;
import static edu.tecnopotify.interfaces.Estado.VIGENTE;
//import edu.tecnopotify.interfaces.Suscripcion.estado.VIGENTE;

/**
 *
 * @author menan
 */
public class ServletSeguirUsuario extends HttpServlet {

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
        String comando = request.getParameter("comando");
        String nickUsr = (String) request.getSession().getAttribute("user");

        Cliente cliente = seleccionarCliente(nickUsr);
        if (comando != null && comando.equals("seguirUsuario") && (cliente.getSuscripcion().getStatus() == VIGENTE)) {
            String usrSeguido = request.getParameter("usrASeguir");
            if (request.getParameter("Dejar de seguir") != null) {
                dejarDeSeguirUsuario(nickUsr, usrSeguido);
            } else {
                seguirUsuario(nickUsr, usrSeguido);
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

}
