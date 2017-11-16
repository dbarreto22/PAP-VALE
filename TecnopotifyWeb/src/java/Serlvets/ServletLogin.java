/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import Webservices.ControladorWeb;
import static Webservices.ControladorWeb.getUsuario;
import static Webservices.ControladorWeb.seleccionarCliente;
import edu.tecnopotify.interfaces.Artista;
import edu.tecnopotify.interfaces.Cliente;
import edu.tecnopotify.interfaces.SeleccionarArtista;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//*********WEB***********************
import edu.tecnopotify.interfaces.Usuario;

/**
 *
 * @author menan
 */
public class ServletLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     *
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String comando = request.getParameter("comando");
        if ((comando != null) && (comando.equals("login"))) {
            //obtener parametros y autenticar
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            Usuario usr = getUsuario(user);
            //consultar a la logica
            if (pass.equals(usr.getContrasenia())) {
                
                if(user.getClass().getName().contains("Cliente")){
                    Cliente cli = seleccionarCliente(usr.getNickname());
                    request.getSession().setAttribute("user", cli);
                    request.getSession().setAttribute("tipo", "Cliente");
                }else{
                    Artista art = SeleccionarArtista(usr.getNickname());
                    request.getSession().setAttribute("user", art);
                    request.getSession().setAttribute("tipo", "Artista");
            }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrecto.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else if ((comando != null) && (comando.equals("logout"))) {
            request.getSession().invalidate();
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
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

    private Artista SeleccionarArtista(String nickname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
