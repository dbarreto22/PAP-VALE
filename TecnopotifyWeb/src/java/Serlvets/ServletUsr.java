/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


//**************************WEB**************************
import edu.tecnopotify.interfaces.DataArtista;
import edu.tecnopotify.interfaces.DataFecha;
import edu.tecnopotify.interfaces.DataListaReproduccion;
import edu.tecnopotify.interfaces.DataUsuario;
import edu.tecnopotify.interfaces.Album;
import edu.tecnopotify.interfaces.Artista;
import edu.tecnopotify.interfaces.Cliente;
import edu.tecnopotify.interfaces.Favoritos;
import edu.tecnopotify.interfaces.ListaParticular;
import edu.tecnopotify.interfaces.ListaReproduccion;
import edu.tecnopotify.interfaces.Usuario;
import static Webservices.ControladorWeb.crearArtista;
import static Webservices.ControladorWeb.crearCliente;
import static Webservices.ControladorWeb.crearListaParticular;
import static Webservices.ControladorWeb.getSeguidos;
import static Webservices.ControladorWeb.getUsuario;
import static Webservices.ControladorWeb.seleccionarArtista;

public class ServletUsr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    private String fileDirStr = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, org.apache.tomcat.util.http.fileupload.FileUploadException {

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

        response.setContentType("text/html");

        String comando = request.getParameter("comando");
        if (comando != null && comando.equals("mostrarCliente")) {

         //   String nick = (String) request.getSession().getAttribute("user").;
            Usuario usr = (Usuario) request.getSession().getAttribute("user");
            if (usr.getClass().getName().contains("Cliente")) {
                Cliente cli = getCli(usr.getNickname());

                request.setAttribute("nickName", cli.getNickname());
                request.setAttribute("nombre", cli.getNombre());
                request.setAttribute("apellido", cli.getApellido());
                request.setAttribute("mail", cli.getMail());
                request.setAttribute("imagen", cli.getImagen());
                request.setAttribute("dia", cli.getFNac().getDia());
                request.setAttribute("mes", cli.getFNac().getMes());
                request.setAttribute("anio", cli.getFNac().getAnio());

                if (cli.getSuscripcion().getStatus().equals("VIGENTE")) {
                    List<Usuario> seguidores = getSeguidos(cli.getNickname());
                    Iterator<Usuario> itS = seguidores.iterator();
                    List<String> nickSeg = new ArrayList<>();
                    while (itS.hasNext()) {
                        nickSeg.add(itS.next().getNickname());
                    }
                    request.setAttribute("litseg", nickSeg);
                }

                List<ListaParticular> repPropia = cli.getListasReprParticular();
                Iterator<ListaParticular> itR = repPropia.iterator();
                List<String> nickRepPropia = new ArrayList<>();
                while (itR.hasNext()) {
                    nickRepPropia.add(itR.next().getNombre());
                }
                request.setAttribute("repPropia", nickRepPropia);
                Favoritos fav = cli.getFav();
                if (fav != null) {
                    List<Album> album = fav.getListAlbum();
                    Iterator<Album> itAlb = album.iterator();
                    List<String> nickAlbum = new ArrayList<>();

                    while (itAlb.hasNext()) {
                        nickAlbum.add(itAlb.next().getNombre());
                    }
                    request.setAttribute("album", nickAlbum);

                    List<ListaReproduccion> listRep = fav.getListRep();
                    Iterator<ListaReproduccion> itListRep = listRep.iterator();
                    List<String> nickLrep = new ArrayList<>();
                    while (itListRep.hasNext()) {
                        nickLrep.add(itAlb.next().getNombre());
                    }

                    request.setAttribute("listRep", nickLrep);
                }

                RequestDispatcher despachador = request.getRequestDispatcher("/mostrarC.jsp");
                despachador.forward(request, response);

            } else if (usr.getClass().getName().contains("Artista")) {
                Artista art = seleccionarArtista(usr.getNickname());

                request.setAttribute("nickName", art.getNickname());
                request.setAttribute("nombre", art.getNombre());
                request.setAttribute("apellido", art.getApellido());
                request.setAttribute("mail", art.getMail());
                request.setAttribute("imagen", art.getImagen());
                request.setAttribute("biografia", art.getBiografia());
                request.setAttribute("link", art.getLink());
                request.setAttribute("dia", art.getFNac().getDia());
                request.setAttribute("mes", art.getFNac().getMes());
                request.setAttribute("anio", art.getFNac().getAnio());

                List<Album> albumArt = art.getListAlbum();
                Iterator<Album> itA = albumArt.iterator();
                List<String> nomAl = new ArrayList<>();
                while (itA.hasNext()) {
                    nomAl.add(itA.next().getNombre());
                }

                request.setAttribute("albumArt", nomAl);

                RequestDispatcher despachador = request.getRequestDispatcher("/mostrarArtista.jsp");
                despachador.forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String comando = request.getParameter("comando");

        if (comando != null && comando.equals("altaCliente")) {
            //Procesar el formulario  
            String nickName = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String mail = request.getParameter("mail");
            int dia = request.getIntHeader("dia");
            int mes = request.getIntHeader("mes");
            int anio = request.getIntHeader("anio");
            DataFecha fecha = new DataFecha();
            fecha.setAnio(anio);
            fecha.setDia(dia);
            fecha.setMes(mes);
            String contrasenia = request.getParameter("contrasenia");
            DataUsuario cli = new DataUsuario();
            
            cli.setNickname(nickName);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setMail(mail);
            cli.setFNac(fecha);
            cli.setImagen("");
            cli.setContrasenia(contrasenia);

            crearCliente(cli);

            request.setAttribute("id", nickName);
            request.setAttribute("comando", "altaCli");

            RequestDispatcher despachador = request.getRequestDispatcher("/subirImg.jsp");
            despachador.forward(request, response);

        } else if (comando != null && comando.equals("altaArtista")) {
            //Procesar el formulario  
            String nickName = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String mail = request.getParameter("mail");
            int dia = request.getIntHeader("dia");
            int mes = request.getIntHeader("mes");
            int anio = request.getIntHeader("anio");
            DataFecha fecha = new DataFecha();
            fecha.setAnio(anio);
            fecha.setDia(dia);
            fecha.setMes(mes);
            String contrasenia = request.getParameter("contrasenia");
            String imagen = "";
            String biografia = request.getParameter("biografia");
            String link = request.getParameter("link");
            DataUsuario cli = new DataArtista();
            
            cli.setNickname(nickName);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setMail(mail);
            cli.setFNac(fecha);
            cli.setImagen("");
            cli.setContrasenia(contrasenia);
            
            crearArtista(biografia, link, cli);
            String altaArt = "altaArt";

            request.setAttribute("id", nickName);
            request.setAttribute("comando", altaArt);

            RequestDispatcher despachador = request.getRequestDispatcher("/subirImg.jsp");
            despachador.forward(request, response);
        }

        if (comando != null && comando.equals("mostrarClienteGuest")) {

            String nick = request.getParameter("ArtistaSelect");
            Usuario usr = getUsuario(nick);
            if (usr.getClass().getName().contains("Artista")) {
                Artista art = seleccionarArtista(nick);

                request.setAttribute("nickName", art.getNickname());
                request.setAttribute("nombre", art.getNombre());
                request.setAttribute("apellido", art.getApellido());
                request.setAttribute("mail", art.getMail());
                request.setAttribute("imagen", art.getImagen());
                request.setAttribute("biografia", art.getBiografia());
                request.setAttribute("link", art.getLink());
                request.setAttribute("dia", art.getFNac().getDia());
                request.setAttribute("mes", art.getFNac().getMes());
                request.setAttribute("anio", art.getFNac().getAnio());

                List<Album> albumArt = art.getListAlbum();
                Iterator<Album> itA = albumArt.iterator();
                List<String> nomAl = new ArrayList<>();
                while (itA.hasNext()) {
                    nomAl.add(itA.next().getNombre());
                }

                request.setAttribute("albumArt", nomAl);

                RequestDispatcher despachador = request.getRequestDispatcher("/mostrarArtista.jsp");
                despachador.forward(request, response);

            } else if (usr.getClass().getName().contains("Cliente")) {

                Cliente c = getCli(nick);
                request.setAttribute("nickName", c.getNickname());
                request.setAttribute("imagen", c.getImagen());

                List<ListaParticular> lp = c.getListasReprParticular();
                Iterator<ListaParticular> it = lp.iterator();
                List<String> lpS = new ArrayList<>();
                while (it.hasNext()) {
                    if (!it.next().isEsPrivada()) {
                        lpS.add(it.next().getNombre());
                    }
                }
                request.setAttribute("lpS", lpS);
                RequestDispatcher despachador = request.getRequestDispatcher("/clienteguest.jsp");
                despachador.forward(request, response);
            }
        }

        if (comando != null && comando.equals("listaRep")) {

            String nombreL = request.getParameter("nombre");
            String nick = (String) request.getSession().getAttribute("user");
            Cliente cli = getCli(nick);
            DataListaReproduccion listaP = new DataListaReproduccion();
            listaP.setNombre(nombreL);
            listaP.setImagen("");
            crearListaParticular(true, nick, listaP);

            request.setAttribute("id", nombreL);

            RequestDispatcher despachador = request.getRequestDispatcher("/subirImg.jsp");
            despachador.forward(request, response);

        }

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

    private static Cliente getCli(java.lang.String arg0) {
        edu.tecnopotify.interfaces.ControladorService service = new edu.tecnopotify.interfaces.ControladorService();
        edu.tecnopotify.interfaces.Controlador port = service.getControladorPort();
        return port.getCli(arg0);
    }

   

}
