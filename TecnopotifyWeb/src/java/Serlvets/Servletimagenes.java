/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serlvets;

import static Webservices.ControladorWeb.getCli;
import static Webservices.ControladorWeb.getTema;
import static Webservices.ControladorWeb.seleccionarAlbum;
import static Webservices.ControladorWeb.seleccionarArtista;
import static Webservices.ControladorWeb.setImage;
import static Webservices.ControladorWeb.setImageArt;
import static Webservices.ControladorWeb.setImageCli;
import static Webservices.ControladorWeb.setTema;
import edu.tecnopotify.interfaces.Album;
import edu.tecnopotify.interfaces.Artista;
import edu.tecnopotify.interfaces.Cliente;
import edu.tecnopotify.interfaces.Temas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Servletimagenes extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName
                = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        System.out.println("filename:" + fileName);

        if (fileName == null || fileName.equals("")) {
            throw new ServletException("Debe cargar una Imagen");
        }
        File file = new File(fileDirStr + File.separator + fileName);
        if (!file.exists()) {
            throw new ServletException("La imagen no existe.");
        }
        System.out.println("Ubicacion::" + file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());

        response.setContentType(mimeType != null ? mimeType
                : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"");

        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while ((read = fis.read(bufferData)) != -1) {
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("Archivo descargado correctamente");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        response.setContentType("text/html");

        try {
            List<FileItem> fileItemsList;
            fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                request.setAttribute("imagen", fileItem.getName());
                File file = new File(fileDirStr + File.separator + fileItem.getName());
                fileItem.write(file);
                String comando = request.getParameter("comando");
                RequestDispatcher despachador=null;
                Album album=null;
                switch (comando) {
                    case "altaCli":
                        Cliente cli = getCli((String) request.getParameter("id"));
                        cli.setImagen(file.getAbsolutePath());
                        setImageCli(cli);
                        break;
                    case "altaArt":
                        Artista art = seleccionarArtista((String) request.getParameter("id"));
                        art.setImagen(file.getAbsolutePath());
                        setImageArt(art);
                        break;
                    case "altaAlbum":
                        album = seleccionarAlbum((String) request.getParameter("id"));
                        album.setImagenAlbum(file.getAbsolutePath());
                        setImage(album);
                        request.setAttribute("id", album.getNombre());
                        request.setAttribute("comando", "altaTema");
                        despachador = request.getRequestDispatcher("/Temas/altaTema.jsp");
                        despachador.forward(request, response);
                        break;
                    case "altaTema":
                        Temas tema = getTema(request.getParameter("id"));
                        String idAlbum = request.getParameter("idAlbum");
                        tema.setArchivo(file.getAbsolutePath());
                        setTema(tema);
                        request.setAttribute("id", idAlbum);
                        request.setAttribute("comando", "altaTema");
                        despachador = request.getRequestDispatcher("/Temas/altaTema.jsp");
                        despachador.forward(request, response);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
        RequestDispatcher despachador = request.getRequestDispatcher("/ppal.jsp");
        despachador.forward(request, response);
    }

}
