package edu.tecnopotify.interfaces;

import edu.tecnopotify.datatypes.dataAlbum;
import edu.tecnopotify.datatypes.dataArtista;
import edu.tecnopotify.datatypes.dataGenero;
import edu.tecnopotify.datatypes.dataListaReproduccion;
import edu.tecnopotify.datatypes.dataTemas;
import edu.tecnopotify.datatypes.dataUsuario;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.ListaParticular;
import edu.tecnopotify.entidades.Artista;
import edu.tecnopotify.entidades.Genero;
import edu.tecnopotify.entidades.ListaReproduccion;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.ListaDefecto;
import edu.tecnopotify.entidades.Temas;
import edu.tecnopotify.entidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManagerFactory;


public interface IControlador {

    public EntityManagerFactory getEntityManagerFactory();

    public void crearCliente(dataUsuario usuario);

    public void crearArtista(String biografia, String link, dataUsuario usuario);

    public void altaGenero(dataGenero genero);

    public String obtenerEstadoSuscripcion(String nickCliente);

    public String obtenerPagoSuscripcion(String nickCliente);

    public void modificarSuscripcion(String nickname, String estadoSuscripcion, String pago);

    public void crearAlbum(String nickNameArtista, dataAlbum dtAlbum);
    
    public void crearAlbum2(String nickNameArtista, Album oAlbum);

    public void altaTema(dataTemas tema, String album);
    // public void seleccionarLista(String a);

    public ArrayList<ListaReproduccion> consultarListaRep(boolean cliente, String id);

    public ArrayList<Album> consultarAlbumPorArtista(dataArtista artista);

    public ArrayList<Album> consultarAlbumPorGenero(dataGenero genero);

    public Album seleccionarAlbum(String id);

    public Artista seleccionarArtista(String nombreArtista);//

    public Artista seleccionarArtistaPorNombre(String name);

    public Cliente seleccionarCliente(String nickname);

    public void eliminarFavorito(boolean tema, boolean lista, boolean album, String nickCliente, String idElemento);

    public void agregarFavorito(boolean tema, boolean lista, boolean album, String nickCliente, String idElemento);

    public void dejarDeSeguirUsuario(String nickCliente, String nickUsr);

    public void seguirUsuario(String nickCliente, String nickUsr);

    public void publicarLista(String idUsr, String nombreLista);

    public ListaParticular seleccionarLista(String id);

    public void quitarTemaLista(String idTema, dataListaReproduccion listaR);

    public void agregarTemaLista(String idTema, dataListaReproduccion listaR);

    public void crearListaDefecto(dataListaReproduccion listaD, String nombreGenero);

    public void crearListaParticular(boolean privado, String nickCliente, dataListaReproduccion listaP);

    public ArrayList<Genero> mostrarListaGenero();

    //public Cliente devolverCliente(String nickname);
    public ArrayList<Cliente> listarClientes();

    ArrayList<Artista> listarArtistas();

    public ArrayList<Genero> getListGenero(String nombre);

    public ArrayList<Genero> listarGeneros();

    public Album buscarAlbum(String nombre);

    public ArrayList<Album> listarAlbum();

    public ArrayList<Usuario> listarUsuarios();

    public Cliente getCli(String nickname);

    public ArrayList<Temas> listarTemas();
    
    public Temas getTema(String name);
    
    public ListaReproduccion getlr(String name);

    public Genero buscarGenero(String nombre);
    
    public ArrayList<ListaDefecto> listarDefecto();
    
    public ArrayList<ListaReproduccion> listarListaRepr();
    
    public void cargarDatos();
    
    public Usuario getUsuario(String nickname);
    
    public Usuario buscarUsrMail(String mail);
    
    public void setImageCli(Cliente cli);
    
    public void setImageArt(Artista art);

    public void setImage(Album aux);
    
    public void setTema(Temas aux);

    public void publicar();    
    
    public ArrayList<Usuario> getSeguidos(String nick);
    
    public ArrayList<Genero> getGenerosHijos(String nombre);
    
    public ArrayList<Album> getAlbumsdeGeneros(String nombre);
    
    public void crearListaParticularClase(boolean privado, String nickCliente, ListaParticular listaP);
    
    public void RegistroUsuariosCount(String ip, String url, String browser);
}

