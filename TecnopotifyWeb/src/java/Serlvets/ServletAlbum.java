/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//************************WEB********************************
import edu.tecnopotify.interfaces.DataAlbum;
import edu.tecnopotify.interfaces.Album;
import edu.tecnopotify.interfaces.Artista;
import edu.tecnopotify.interfaces.Genero;
import static Webservices.ControladorWeb.listarGeneros;
import static Webservices.ControladorWeb.seleccionarArtista;
import static Webservices.ControladorWeb.buscarAlbum;
import static Webservices.ControladorWeb.buscarGenero;
import static Webservices.ControladorWeb.crearAlbum;
import static Webservices.ControladorWeb.getAlbumsdeGeneros;
import static Webservices.ControladorWeb.listarArtistas;

/**
 *
 * @author Carlox
 */
@WebServlet(name = "ServletAlbum", urlPatterns = {"/ServletAlbum"})
public class ServletAlbum extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    private String fileDirStr = null;

    @Override
    public void init() throws ServletException {
        String rootPath = System.getProperty("user.home");
        String relativePath = "imagenes-servlet";
        fileDirStr = rootPath + File.separator + relativePath;
        File filesDir = new File(fileDirStr);
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
       
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        response.setContentType("text/html;charset=UTF-8");
        String comando = request.getParameter("comando");
        String path = "";
        String destino="/ppal.jsp";
        if (comando != null && comando.equals("altaAlbum")) {
        }else if (comando != null && comando.equals("mostrarAlbum")) {
            String album = (String)request.getParameter("idAlbum");
            edu.tecnopotify.interfaces.Album oAlbum = buscarAlbum(album);
            List<edu.tecnopotify.interfaces.Temas> lstTemas = oAlbum.getListTemas();
            destino="/Album/MostrarAlbum.jsp";
            request.setAttribute("lstTemas", lstTemas);
        } else {// Es mostrar album
            List<Genero> lstGenero = listarGeneros();
            List<Artista> lstArtista = listarArtistas();
            request.setAttribute("lstGenero", lstGenero);
            request.setAttribute("lstArtista", lstArtista);
            destino="/Album/ConsultarAlbum.jsp";
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
        response.setContentType("text/html");
        String comando = request.getParameter("comando");
        String path = "";
        String destino="/ppal.jsp";
        if (comando != null && comando.equals("altaAlbum")) {
            Artista artista;
            String idAlbum = request.getParameter("nombreAlbum");
            int anio = Integer.parseInt(request.getParameter("anio"));
            String usr = request.getParameter("usr");
            DataAlbum oDtAlbum = new DataAlbum();
            oDtAlbum.setImagenAlbum(idAlbum);
            oDtAlbum.setAnioCreado(anio);
            oDtAlbum.setImagenAlbum(path);
            artista = seleccionarArtista(usr);
            crearAlbum(artista.getNickname(), oDtAlbum);
            request.setAttribute("comando", comando);
            request.setAttribute("id", idAlbum);
            destino="/subirImg.jsp";
        } else if (comando != null && comando.equals("listarAlbum")) {
            String genero = (String)request.getParameter("GeneroSelect");
            String artista = (String)request.getParameter("ArtistaSelect");
            if (genero == "" && artista=="") {
                destino="/ppal.jsp";
            }else{  
                List<Album> lstAlbum=null;
                if (genero != null && genero != "") {
                    Genero oGenero=buscarGenero(genero);
                    lstAlbum=getAlbumsdeGeneros(oGenero.getNombre());
                } else{
                    Artista oArtista = seleccionarArtista(artista);
                    lstAlbum=oArtista.getListAlbum();
                }
                request.setAttribute("lstAlbum", lstAlbum);
                destino="/Album/listarAlbum.jsp";
            }
        }
        request.getRequestDispatcher(destino).forward(request, response);
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
