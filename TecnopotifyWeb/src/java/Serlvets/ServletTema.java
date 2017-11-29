/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import edu.tecnopotify.interfaces.DataTemas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Webservices.ControladorWeb;
import edu.tecnopotify.interfaces.Cliente;
import edu.tecnopotify.interfaces.ListaParticular;
import edu.tecnopotify.interfaces.Temas;
import java.util.List;

/**
 *
 * @author Carlox
 */
public class ServletTema extends HttpServlet {

    private ControladorWeb webCtr;    

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
        webCtr=new ControladorWeb();
        String comando = request.getParameter("comando");
        String path="/ppal.jsp";
        if(comando != null && comando.equals("agregarTemaLista"))
        {
            List<Temas> lstTema = webCtr.listarTemas();
            request.setAttribute("lstTema", lstTema);
            path="/Temas/agregarTemaLista.jsp";
        }
        else{
        }    
        request.getRequestDispatcher(path).forward(request, response);

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
        webCtr=new ControladorWeb();
        processRequest(request, response);
        String comando= request.getParameter("comando");
        String path = "/ppal.jsp";
        if (comando != null && comando.equals("altaTema")) {
            String idAlbum = request.getParameter("id");
            int pos = Integer.parseInt(request.getParameter("pos"));
            String tema = request.getParameter("nombreTema");
            String duracion = request.getParameter("duracion");
            DataTemas oDtTema = new DataTemas();
            oDtTema.setNombre(tema);
            oDtTema.setDuracion(duracion);
            oDtTema.setPosicion(pos);
            oDtTema.setArchivo(path);
            webCtr.altaTema(oDtTema, idAlbum);
            request.setAttribute("comando", comando);
            request.setAttribute("id", tema);
            request.setAttribute("idAlbum",idAlbum);
            path="/Temas/subirTema.jsp";
        }
        else if(comando != null && comando.equals("agregarTema"))
        {
            String lstRep = (String)request.getParameter("listRep");
            String tema = (String)request.getParameter("temaSelect");
            ListaParticular oLstPart=new ListaParticular();
            boolean encontre=false;
            if (!lstRep.equals("") && !tema.equals("")) {
                Cliente cli=(Cliente)request.getSession().getAttribute("user");
                List<ListaParticular> lstPart=cli.getListasReprParticular();
                for(ListaParticular aux : lstPart){
                    if(aux.getNombre().equals(lstRep))
                    {
                       oLstPart= aux;
                       webCtr.agregarTemaListaClase(tema, oLstPart);
                    }
                }

            }
            path="/ppal.jsp";
            }
        request.getRequestDispatcher(path).forward(request, response);
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
