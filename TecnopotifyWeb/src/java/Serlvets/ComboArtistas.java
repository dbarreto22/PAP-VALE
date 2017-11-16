/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*******************web***********************
import edu.tecnopotify.interfaces.Artista;
import static Webservices.ControladorWeb.listarArtistas;
import static Webservices.ControladorWeb.listarUsuarios;
import edu.tecnopotify.interfaces.Usuario;

/**
 *
 * @author Carlox
 */
@WebServlet(name = "ComboArtistas", urlPatterns = {"/ComboArtistas"})
public class ComboArtistas extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
          RequestDispatcher despachador;
          if(request.getParameter("comando").equals("CargarUsuarios"))
          {
              List<Usuario> lstUsuarios = listarUsuarios();
              request.setAttribute("usuarios", lstUsuarios);
              despachador = request.getRequestDispatcher("Guest.jsp");//
              despachador.forward(request, response);              
          }
          else{
//********************************************************************************************************
          List<Artista> lstArtista = listarArtistas();
//********************************************************************************************************
          request.setAttribute("lstArtista", lstArtista);
          despachador = request.getRequestDispatcher("Album/AltaAlbum.jsp");//
          despachador.forward(request, response);
          }
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

}
