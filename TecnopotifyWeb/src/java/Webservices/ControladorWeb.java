/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webservices;

import edu.tecnopotify.interfaces.Album;
import edu.tecnopotify.interfaces.Artista;
import edu.tecnopotify.interfaces.Cliente;
import edu.tecnopotify.interfaces.Genero;
import edu.tecnopotify.interfaces.ListaParticular;
import edu.tecnopotify.interfaces.ListaReproduccion;
import edu.tecnopotify.interfaces.Temas;
import edu.tecnopotify.interfaces.Usuario;
import edu.tecnopotify.interfaces.ControladorService;
import edu.tecnopotify.interfaces.Controlador;
/**
 *
 * @author Carlox
 */
public class ControladorWeb {
        private static ControladorService service;
        private static Controlador port;
    ControladorWeb()
    {
        service = new edu.tecnopotify.interfaces.ControladorService();
        port = service.getControladorPort();
    }

    public static void agregarFavorito(boolean arg0, boolean arg1, boolean arg2, java.lang.String arg3, java.lang.String arg4) {
        port.agregarFavorito(arg0, arg1, arg2, arg3, arg4);
    }

    public static void agregarTemaLista(java.lang.String arg0, edu.tecnopotify.interfaces.DataListaReproduccion arg1) {
        port.agregarTemaLista(arg0, arg1);
    }

    public static void altaGenero(edu.tecnopotify.interfaces.DataGenero arg0) {
        port.altaGenero(arg0);
    }

    public static void altaTema(edu.tecnopotify.interfaces.DataTemas arg0, java.lang.String arg1) {
        port.altaTema(arg0, arg1);
    }

    public static Album buscarAlbum(java.lang.String arg0) {
        return port.buscarAlbum(arg0);
    }

    public static Genero buscarGenero(java.lang.String arg0) {
        return port.buscarGenero(arg0);
    }

    public static Usuario buscarUsrMail(java.lang.String arg0) {
        return port.buscarUsrMail(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Album> consultarAlbumPorArtista(edu.tecnopotify.interfaces.DataArtista arg0) {
        return port.consultarAlbumPorArtista(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Album> consultarAlbumPorGenero(edu.tecnopotify.interfaces.DataGenero arg0) {
        return port.consultarAlbumPorGenero(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.ListaReproduccion> consultarListaRep(boolean arg0, java.lang.String arg1) {
        return port.consultarListaRep(arg0, arg1);
    }

    public static void crearAlbum(java.lang.String arg0, edu.tecnopotify.interfaces.DataAlbum arg1) {
        port.crearAlbum(arg0, arg1);
    }

    public static void crearArtista(java.lang.String arg0, java.lang.String arg1, edu.tecnopotify.interfaces.DataUsuario arg2) {
        port.crearArtista(arg0, arg1, arg2);
    }

    public static void crearCliente(edu.tecnopotify.interfaces.DataUsuario arg0) {
        port.crearCliente(arg0);
    }

    public static void crearListaDefecto(edu.tecnopotify.interfaces.DataListaReproduccion arg0, java.lang.String arg1) {
        port.crearListaDefecto(arg0, arg1);
    }

    public static void crearListaParticular(boolean arg0, java.lang.String arg1, edu.tecnopotify.interfaces.DataListaReproduccion arg2) {
        port.crearListaParticular(arg0, arg1, arg2);
    }

    public static void dejarDeSeguirUsuario(java.lang.String arg0, java.lang.String arg1) {
        port.dejarDeSeguirUsuario(arg0, arg1);
    }

    public static void eliminarFavorito(boolean arg0, boolean arg1, boolean arg2, java.lang.String arg3, java.lang.String arg4) {
        port.eliminarFavorito(arg0, arg1, arg2, arg3, arg4);
    }

    public static Cliente getCli(java.lang.String arg0) {
        return port.getCli(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Genero> getListGenero(java.lang.String arg0) {
        return port.getListGenero(arg0);
    }

    public static Temas getTema(java.lang.String arg0) {
        return port.getTema(arg0);
    }

    public static Usuario getUsuario(java.lang.String arg0) {
        return port.getUsuario(arg0);
    }

    public static ListaReproduccion getlr(java.lang.String arg0) {
        return port.getlr(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Album> listarAlbum() {
        return port.listarAlbum();
    }

    public static java.util.List<edu.tecnopotify.interfaces.Artista> listarArtistas() {
        return port.listarArtistas();
    }

    public static java.util.List<edu.tecnopotify.interfaces.Cliente> listarClientes() {
        return port.listarClientes();
    }

    public static java.util.List<edu.tecnopotify.interfaces.ListaDefecto> listarDefecto() {
        return port.listarDefecto();
    }

    public static java.util.List<edu.tecnopotify.interfaces.Genero> listarGeneros() {
        return port.listarGeneros();
    }

    public static java.util.List<edu.tecnopotify.interfaces.ListaReproduccion> listarListaRepr() {
        return port.listarListaRepr();
    }

    public static java.util.List<edu.tecnopotify.interfaces.Temas> listarTemas() {
        return port.listarTemas();
    }

    public static java.util.List<edu.tecnopotify.interfaces.Usuario> listarUsuarios() {
        return port.listarUsuarios();
    }

    public static void modificarSuscripcion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        port.modificarSuscripcion(arg0, arg1, arg2);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Genero> mostrarListaGenero() {
        return port.mostrarListaGenero();
    }

    public static String obtenerEstadoSuscripcion(java.lang.String arg0) {
        return port.obtenerEstadoSuscripcion(arg0);
    }

    public static String obtenerPagoSuscripcion(java.lang.String arg0) {
        return port.obtenerPagoSuscripcion(arg0);
    }


    public static void publicarLista(java.lang.String arg0, java.lang.String arg1) {
        port.publicarLista(arg0, arg1);
    }

    public static void quitarTemaLista(java.lang.String arg0, edu.tecnopotify.interfaces.DataListaReproduccion arg1) {
        port.quitarTemaLista(arg0, arg1);
    }

    public static void seguirUsuario(java.lang.String arg0, java.lang.String arg1) {
        port.seguirUsuario(arg0, arg1);
    }

    public static Album seleccionarAlbum(java.lang.String arg0) {
        return port.seleccionarAlbum(arg0);
    }

    public static Artista seleccionarArtista(java.lang.String arg0) {
        return port.seleccionarArtista(arg0);
    }

    public static Artista seleccionarArtistaPorNombre(java.lang.String arg0) {
        return port.seleccionarArtistaPorNombre(arg0);
    }

    public static Cliente seleccionarCliente(java.lang.String arg0) {
        return port.seleccionarCliente(arg0);
    }

    public static ListaParticular seleccionarLista(java.lang.String arg0) {
        return port.seleccionarLista(arg0);
    }

    public static void setImage(edu.tecnopotify.interfaces.Album arg0) {
        port.setImage(arg0);
    }

    public static void setImageArt(edu.tecnopotify.interfaces.Artista arg0) {
        port.setImageArt(arg0);
    }

    public static void setImageCli(edu.tecnopotify.interfaces.Cliente arg0) {
        port.setImageCli(arg0);
    }

    public static void setTema(edu.tecnopotify.interfaces.Temas arg0) {
        port.setTema(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Genero> getGenerosHijos(java.lang.String arg0) {
        return port.getGenerosHijos(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Album> getAlbumsdeGeneros(java.lang.String arg0) {
        return port.getAlbumsdeGeneros(arg0);
    }

    public static java.util.List<edu.tecnopotify.interfaces.Usuario> getSeguidos(java.lang.String arg0) {
        return port.getSeguidos(arg0);
    }

    
    
    
}
