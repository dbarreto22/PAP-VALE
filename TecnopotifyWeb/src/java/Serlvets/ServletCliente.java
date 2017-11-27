/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;


import Webservices.ControladorWeb;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*******************WEB**************************
import edu.tecnopotify.interfaces.DataFecha;
import edu.tecnopotify.interfaces.DataUsuario;


/**
 *
 * @author Carlox
 */
public class ServletCliente extends HttpServlet {
    private ControladorWeb webCtr;
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
            fecha.setMes(mes);
            fecha.setDia(dia);
            String contrasenia = request.getParameter("contrasenia");
            // String img = request.getParameter("fimg");
            DataUsuario cli = new DataUsuario();
            cli.setNickname(nickName);
            cli.setApellido(apellido);
            cli.setNombre(nombre);
            cli.setMail(mail);
            cli.setContrasenia(contrasenia);
            cli.setFNac(fecha);
            cli.setImagen("");
            webCtr.crearCliente(cli);
            request.setAttribute("nickName",nickName);
        }
        
            RequestDispatcher despachador = request.getRequestDispatcher("/subirImg.jsp");
            despachador.forward(request, response);

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
