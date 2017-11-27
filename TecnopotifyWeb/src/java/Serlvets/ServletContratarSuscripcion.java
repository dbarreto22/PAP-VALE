/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//******************WEB****************************
import Webservices.ControladorWeb;
import edu.tecnopotify.interfaces.Cliente;
/**
 *
 * @author menan
 */
public class ServletContratarSuscripcion extends HttpServlet {
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
        webCtr = new ControladorWeb();
        response.setContentType("text/html;charset=UTF-8");
        //Solo se puede entrar con suscripcion pendiente o vencida, son los únicos estados que permiten pasar a una suscripción vigente
        //FALTA CONTROL PREVIO DE SUSCRIPCIÓN PERO NO ME TOMA LA OPCIÓN DEL COMBO
        String comando = request.getParameter("comando");
        Cliente cliente = (Cliente) request.getSession().getAttribute("user");
        String nickUsr = cliente.getNickname();
        
        String suscrContratar = "VIGENTE";
        String pago = "";
        if (request.getParameter("suscripcionSemanal")!=null && request.getParameter("suscripcionMensual")==null && request.getParameter("suscripcionAnual")==null){
            pago = "SEMANAL";
        }
        if (request.getParameter("suscripcionSemanal")==null && request.getParameter("suscripcionMensual")!=null && request.getParameter("suscripcionAnual")==null){
            pago = "MENSUAL";
        }
        if (request.getParameter("suscripcionSemanal")==null && request.getParameter("suscripcionMensual")==null && request.getParameter("suscripcionAnual")!=null){
            pago = "ANUAL";
        }
        if (comando != null && comando.equals("contratarSuscripcion")){
            webCtr.modificarSuscripcion(nickUsr, suscrContratar, pago); 
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
