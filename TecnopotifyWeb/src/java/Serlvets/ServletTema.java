/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import edu.tecnopotify.datatypes.dataAlbum;
import edu.tecnopotify.datatypes.dataTemas;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Artista;
import edu.tecnopotify.fabrica.Fabrica;
import edu.tecnopotify.interfaces.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlox
 */
public class ServletTema extends HttpServlet {

    

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
        String comando = request.getParameter("comando");
        String destino="/ppal.jsp";
        if (comando != null && comando.equals("altaAlbum")) {
        }else{
        }    
        request.getRequestDispatcher(destino).forward(request, response);

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
        String comando= request.getParameter("comando");
        String path = "";
        if (comando != null && comando.equals("altaTema")) {
            String idAlbum = request.getParameter("id");
            int pos = Integer.parseInt(request.getParameter("pos"));
            String tema = request.getParameter("nombreTema");
            String duracion = request.getParameter("duracion");
            dataTemas oDtTema = new dataTemas(tema, duracion, pos, path);
            altaTema(oDtTema, idAlbum);
            request.setAttribute("comando", comando);
            request.setAttribute("id", tema);
            request.setAttribute("idAlbum",idAlbum);
        }
        request.getRequestDispatcher("/Temas/subirTema.jsp").forward(request, response);

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

    private void altaTema(dataTemas oDtTema, String idAlbum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
