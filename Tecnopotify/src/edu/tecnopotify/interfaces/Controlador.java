package edu.tecnopotify.interfaces;

import edu.tecnopotify.controladores.AlbumJpaController;
import edu.tecnopotify.controladores.ArtistaJpaController;
import edu.tecnopotify.controladores.ClienteJpaController;
import edu.tecnopotify.controladores.ExtJpaAlbum;
import edu.tecnopotify.controladores.ExtJpaFavoritos;
import edu.tecnopotify.controladores.ExtJpaGenero;
import edu.tecnopotify.controladores.ExtJpaSrtista;
import edu.tecnopotify.controladores.ExtUsuario;
import edu.tecnopotify.controladores.GeneroJpaController;
import edu.tecnopotify.controladores.ListaDefectoJpaController;
import edu.tecnopotify.controladores.ListaParticularJpaController;
import edu.tecnopotify.controladores.ListaReproduccionJpaController;
import edu.tecnopotify.controladores.SuscripcionJpaController;
import edu.tecnopotify.controladores.TemasJpaController;
import edu.tecnopotify.entidades.Genero;
import edu.tecnopotify.entidades.ListaReproduccion;
import edu.tecnopotify.datatypes.dataUsuario;
import edu.tecnopotify.entidades.Usuario;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import edu.tecnopotify.controladores.UsuarioJpaController;
import edu.tecnopotify.controladores.exceptions.PreexistingEntityException;
import edu.tecnopotify.controladores.extJpaCliente;
import edu.tecnopotify.datatypes.dataAlbum;
import edu.tecnopotify.datatypes.dataArtista;
import edu.tecnopotify.datatypes.dataFecha;
import edu.tecnopotify.datatypes.dataGenero;
import edu.tecnopotify.datatypes.dataListaReproduccion;
import edu.tecnopotify.datatypes.dataTemas;
import edu.tecnopotify.entidades.Album;
import edu.tecnopotify.entidades.Artista;
import edu.tecnopotify.entidades.Cliente;
import edu.tecnopotify.entidades.ListaDefecto;
import edu.tecnopotify.entidades.ListaParticular;
import edu.tecnopotify.entidades.Suscripcion;
import static edu.tecnopotify.entidades.Suscripcion.estado.CANCELADA;
import static edu.tecnopotify.entidades.Suscripcion.estado.PENDIENTE;
import static edu.tecnopotify.entidades.Suscripcion.estado.VENCIDA;
import static edu.tecnopotify.entidades.Suscripcion.estado.VIGENTE;
import static edu.tecnopotify.entidades.Suscripcion.pago.ANUAL;
import static edu.tecnopotify.entidades.Suscripcion.pago.MENSUAL;
import static edu.tecnopotify.entidades.Suscripcion.pago.SEMANAL;
import edu.tecnopotify.entidades.Temas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class Controlador implements IControlador {

    EntityManagerFactory fact = Persistence.createEntityManagerFactory("EjemploJPAPU");

    public Controlador() {

    }

    @WebMethod(exclude = true)
    public EntityManagerFactory getEntityManagerFactory() {
        return this.fact;
    }

    @WebMethod
    public void crearCliente(dataUsuario usuario) {
        Cliente cli = new Cliente(usuario);
        Suscripcion sus = new Suscripcion();
        cli.setSuscripcion(sus);
        //sus.setSuscripto(cli);
        SuscripcionJpaController suscrl = new SuscripcionJpaController(fact);
        suscrl.create(sus);
        ClienteJpaController ctrCl = new ClienteJpaController(fact);
        try {
            ctrCl.create(cli);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void crearArtista(String biografia, String link,
            dataUsuario usuario) {
        Usuario U = new Artista(biografia, link, usuario);
        ArtistaJpaController ctrA = new ArtistaJpaController(fact);
        try {
            ctrA.create((Artista) U);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public String obtenerEstadoSuscripcion(String nickCliente) {
        Cliente cliS = this.seleccionarCliente(nickCliente);
        String retorno = null;
        if (cliS.getSuscripcion().status == PENDIENTE) {
            retorno = "PENDIENTE";
        }
        if (cliS.getSuscripcion().status == VIGENTE) {
            retorno = "VIGENTE";
        }
        if (cliS.getSuscripcion().status == VENCIDA) {
            retorno = "VENCIDA";
        }
        if (cliS.getSuscripcion().status == CANCELADA) {
            retorno = "CANCELADA";
        }

        return retorno;
    }

    @WebMethod
    public String obtenerPagoSuscripcion(String nickCliente) {
        Cliente cliS = this.seleccionarCliente(nickCliente);
        String retorno = null;
        if (cliS.getSuscripcion().cuota == SEMANAL) {
            retorno = "SEMANAL";
        }
        if (cliS.getSuscripcion().cuota == MENSUAL) {
            retorno = "MENSUAL";
        }
        if (cliS.getSuscripcion().cuota == ANUAL) {
            retorno = "ANUAL";
        }
        return retorno;
    }

    @Override
    @WebMethod
    public void modificarSuscripcion(String nickname, String estadoSuscripcion, String pago) {
        //Cliente cli= seleccionarCliente(nickname);
        SuscripcionJpaController suscrl = new SuscripcionJpaController(fact);
        ClienteJpaController ctrCl = new ClienteJpaController(fact);
        Cliente c = ctrCl.findCliente(nickname);
        Suscripcion sus = c.getSuscripcion();
        boolean modificacionValida = false;
        /*desde el estado “Pendiente” a "Cancelada" y desde el estado "Vencida" a "Cancelada" o "Vigente" para el caso actualizarSuscripcion*/

        if (sus.status == PENDIENTE && (estadoSuscripcion.equals("VIGENTE") || estadoSuscripcion.equals("CANCELADA"))) {
            modificacionValida = true;
        }
        if (sus.status == VIGENTE && (estadoSuscripcion.equals("VENCIDA"))) {
            modificacionValida = true;
        }
        if (sus.status == VENCIDA && (estadoSuscripcion.equals("VIGENTE") || estadoSuscripcion.equals("CANCELADA"))) {
            modificacionValida = true;
        }
        if (sus.status == CANCELADA && (estadoSuscripcion.equals("PENDIENTE"))) {
            modificacionValida = true;
        }
        if (modificacionValida) {
            sus.setStatus(estadoSuscripcion);
        }

        //Si la suscripción pasa a vigente se setea el modo de pago
        if (estadoSuscripcion.equals("VIGENTE")) {
            if (pago.equals("SEMANAL")) {
                sus.cuota = SEMANAL;
            } else if (pago.equals("MENSUAL")) {
                sus.cuota = MENSUAL;
            } else if (pago.equals("ANUAL")) {
                sus.cuota = ANUAL;
            }
        }

        //persistiendo gente
        try {
            suscrl.edit(sus);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ctrCl.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void altaTema(dataTemas tema, String album) {
        ExtJpaAlbum crlAlbum = new ExtJpaAlbum(fact);
        Temas oTema = new Temas(tema);
        Album oAlbum = crlAlbum.findAlbum(album);
        oAlbum.getListTemas().add(oTema);
        try {
            crlAlbum.agregarTema(oAlbum, oTema);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void crearListaDefecto(dataListaReproduccion listaD, String nombreGenero) {
        ListaDefectoJpaController crlListaD = new ListaDefectoJpaController(fact);
        GeneroJpaController genctrl = new GeneroJpaController(fact);
        try {
            Genero g = genctrl.findGenero(nombreGenero);
            ListaReproduccion lista = new ListaDefecto(g, listaD);
            g.getListasReprGenero().put(lista.getNombre(), (ListaDefecto) lista);
            crlListaD.create((ListaDefecto) lista);
            genctrl.edit(g);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void crearListaParticular(boolean privado, String nickCliente, dataListaReproduccion listaP) {

        Cliente cli = seleccionarCliente(nickCliente);
        ListaReproduccion lista = new ListaParticular(privado, cli, listaP);
        ListaParticularJpaController crlListaP = new ListaParticularJpaController(fact);
        ClienteJpaController cliCtr = new ClienteJpaController(fact);
        cli.getListasReprParticular().add((ListaParticular) lista);
        try {
            crlListaP.create((ListaParticular) lista);
            cliCtr.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void altaGenero(dataGenero oDtGenero) {
        Genero oGeneroPadre;
        Genero G = new Genero(oDtGenero);
        ExtJpaGenero crlG = new ExtJpaGenero(fact);
        System.out.println("padre: " + oDtGenero.getPadre() + " nombre: " + oDtGenero.getNombre());
        try {
            if (!"".equals(oDtGenero.getPadre())) {//Si no es el genero raiz
                //Busco el genero padre y agrego el hijo
                oGeneroPadre = crlG.findGenero(oDtGenero.getPadre());
                crlG.agregarHijo(oGeneroPadre, G);
            } else {//Si es el genero raiz lo crea en la raiz
                crlG.create(G);
            }

        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public Artista seleccionarArtista(String nickname) {
        ArtistaJpaController ctrArtista = new ArtistaJpaController(fact);
        Artista aux = ctrArtista.findArtista(nickname);
        return aux;
    }

    @WebMethod
    public Cliente seleccionarCliente(String nickname) {
        ClienteJpaController ctrCliente = new ClienteJpaController(fact);
        Cliente aux = ctrCliente.findCliente(nickname);
        return aux;
    }

    @WebMethod
    public Album seleccionarAlbum(String id) {
        AlbumJpaController ctrAlbum = new AlbumJpaController(fact);
        Album aux = ctrAlbum.findAlbum(id);
        return aux;
    }

    @WebMethod
    public ListaParticular seleccionarLista(String id) {
        ListaParticularJpaController ctrListaParticular = new ListaParticularJpaController(fact);
        ListaParticular aux = ctrListaParticular.findListaParticular(id);
        return aux;
    }

    @WebMethod
    public void agregarTemaLista(String idTema, dataListaReproduccion listaR) {

        TemasJpaController ctrTema = new TemasJpaController(fact);
        ListaReproduccionJpaController ctrListaReproduccion = new ListaReproduccionJpaController(fact);
        Temas aux = ctrTema.findTemas(idTema);
        ListaReproduccion Laux = ctrListaReproduccion.findListaReproduccion(listaR.getNombre());
        Laux.getListaTemas().add(aux);
        try {
            ctrListaReproduccion.edit(Laux);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @WebMethod
    public void quitarTemaLista(String idTema, dataListaReproduccion listaR) {
        TemasJpaController ctrTema = new TemasJpaController(fact);
        ListaReproduccionJpaController ctrListaReproduccion = new ListaReproduccionJpaController(fact);
        Temas aux = ctrTema.findTemas(idTema);
        ListaReproduccion Laux = ctrListaReproduccion.findListaReproduccion(listaR.getNombre());
        Laux.getListaTemas().remove(aux);
        try {
            ctrListaReproduccion.edit(Laux);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void crearAlbum(String nickNameArtista, dataAlbum dtAlbum) {
        //Crea un album y lo agrega a su artista
        ExtJpaSrtista ctrArtista = new ExtJpaSrtista(fact);
        //Busca al artista
        Artista oArtista = ctrArtista.findArtista(nickNameArtista);
        if (oArtista != null) {
            System.out.println("Hay artista");
        }
        AlbumJpaController ctrAlbum = new AlbumJpaController(fact);
        //Crea el album
        Album oAlbum = new Album(dtAlbum);
        //Agrega el album a la lista del artista
//        oArtista.getListAlbum().add(oAlbum);
        try {
            //Persiste el album y modifica el artista 
            ctrAlbum.create(oAlbum);
            ctrArtista.agregarAlbum(oArtista, oAlbum);

        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
        @WebMethod
    public void crearAlbum2(String nickNameArtista, Album oAlbum) {
        //Crea un album y lo agrega a su artista
        ExtJpaSrtista ctrArtista = new ExtJpaSrtista(fact);
        //Busca al artista
        Artista oArtista = ctrArtista.findArtista(nickNameArtista);
        if (oArtista != null) {
        }
        AlbumJpaController ctrAlbum = new AlbumJpaController(fact);
        try {
            //Persiste el album y modifica el artista 
            ctrAlbum.create(oAlbum);
            ctrArtista.agregarAlbum(oArtista, oAlbum);

        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //debe devolver Dts?
    @WebMethod
    public ArrayList<Genero> mostrarListaGenero() {
        GeneroJpaController ctrGenero = new GeneroJpaController(fact);
        return new ArrayList(ctrGenero.findGeneroEntities());
    }
//    @WebMethod

    @WebMethod
    //sirven para consultar Album, se le pregunta al usuario por que quiere consultar
    public ArrayList<Album> consultarAlbumPorArtista(dataArtista artista) {
        ArtistaJpaController ctrArtista = new ArtistaJpaController(fact);
        Artista oArtista = ctrArtista.findArtista(artista.getNickname());
        return new ArrayList(oArtista.getListAlbum()); //TODO 
//Fixme
//        return listAlbumArtista;
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Album> consultarAlbumPorGenero(dataGenero genero) {
        //Funcion que obtiene los albums que pertenecen a un genero
        GeneroJpaController ctrGenero = new GeneroJpaController(fact);
        //Obtiene el genero deseado
        Genero oGeneros = ctrGenero.findGenero(genero.getNombre());
        //Devuelve la lista de albums que pertenecen a dicho genero
        return new ArrayList(oGeneros.getListAlbum());
    }

    /*
    public void seleccionarLista(String a) {

    }*/
//    @WebMethod
    @WebMethod
    public ArrayList<ListaReproduccion> consultarListaRep(boolean cliente, String id) {
        //el bool cliente se toma de la entrada, cuando el admin dice si la lista a consultar es de genero o artista
        //retorna en la variable lista la colección de listas de reproduccion a mostrar en pantalla

        ArrayList<ListaReproduccion> lista = null;
        if (cliente) { //string id de cliente (nickname)
            //retornar listas de reproduccion del cliente seleccionado
            ClienteJpaController cli = new ClienteJpaController(fact);
            Cliente c = cli.findCliente(id);
            lista.addAll(c.getListasReprParticular());
        } else { //string nombreGenero
            //retornar listas de reproduccion del genero seleccionado
            GeneroJpaController gen = new GeneroJpaController(fact);
            Genero g = gen.findGenero(id);
            // lista.addAll(0, (Collection<? extends ListaReproduccion>) g.getListasReprGenero());
        }
        return lista;
    }

    @WebMethod
    public void eliminarFavorito(boolean tema, boolean lista, boolean album, String nickCliente, String idElemento) {
        //eliminar tema/lista/album de los favoritos de un cliente
        //selecciono un favorito y saco el elemento de la lista que corresponda
        ExtJpaFavoritos fav = new ExtJpaFavoritos(fact);
        ClienteJpaController clictrl = new ClienteJpaController(fact);
        Cliente oCliente = clictrl.findCliente(nickCliente);
        if (tema) {//Si voy a agregar un tema
            TemasJpaController temactrl = new TemasJpaController(fact);
            Temas oTema = temactrl.findTemas(idElemento);//Busco el tema
            try {
                fav.quitarTemaFav(oTema, oCliente);    //Agrego el tema
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (lista) {
            ListaReproduccionJpaController listactrl = new ListaReproduccionJpaController(fact);
            ListaReproduccion oLista = listactrl.findListaReproduccion(idElemento);
            try {
                fav.quitarListaFav(oLista, oCliente);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (album) {
            AlbumJpaController albctrl = new AlbumJpaController(fact);
            Album oAlbum = albctrl.findAlbum(idElemento);
            try {
                fav.quitarAlbumFav(oAlbum, oCliente);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @WebMethod
    public void agregarFavorito(boolean tema, boolean lista, boolean album, String idCliente, String idElemento) {
        ExtJpaFavoritos fav = new ExtJpaFavoritos(fact);
        extJpaCliente clictrl = new extJpaCliente(fact);
        Cliente oCliente = clictrl.findCliente(idCliente);
        if (tema) {//Si voy a agregar un tema
            TemasJpaController temactrl = new TemasJpaController(fact);
            Temas oTema = temactrl.findTemas(idElemento);//Busco el tema
            try {
                /*oFavorito.getListTemas().add(oTema);
                oCliente.setFav(oFavorito);
                clictrl.edit(oCliente);//*/
                clictrl.agregarTemaFav(oTema, oCliente);    //Agrego el tema
            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (lista) {
            ListaReproduccionJpaController listactrl = new ListaReproduccionJpaController(fact);
            ListaReproduccion oLista = listactrl.findListaReproduccion(idElemento);
            try {
                fav.agregarListaFav(oLista, oCliente);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (album) {
            AlbumJpaController albctrl = new AlbumJpaController(fact);
            Album oAlbum = albctrl.findAlbum(idElemento);
            try {
                /*                oFavorito.getListAlbum().add(oAlbum);
                oCliente.setFav(oFavorito);
                clictrl.edit(oCliente);*/
                clictrl.agregarAlbumFav(oAlbum, oCliente);
            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @WebMethod
    public void dejarDeSeguirUsuario(String nickCliente, String nickUsr) {
        try {
            UsuarioJpaController usrCtrl = new UsuarioJpaController(fact);
            ExtUsuario usr = new ExtUsuario(fact);
            Cliente c = (Cliente) usrCtrl.findUsuario(nickCliente);
            Usuario u = usrCtrl.findUsuario(nickUsr);
            //c.removeFromSeguidos(u);
            usr.quitarSeguidor(u, c);
            usrCtrl.edit(c);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @WebMethod
    public void seguirUsuario(String nickCliente, String nickUsr) {

        try {
            UsuarioJpaController usrCtrl = new UsuarioJpaController(fact);
            ExtUsuario usr = new ExtUsuario(fact);
            Cliente c = (Cliente) usrCtrl.findUsuario(nickCliente);
            Usuario u = usrCtrl.findUsuario(nickUsr);
            //c.addToSeguidos(u);            
            usr.agregarSeguidor(u, c);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @WebMethod
    public void publicarLista(String idUsr, String nombreLista) {
        ListaParticularJpaController listCtrl = new ListaParticularJpaController(fact);
        ListaParticular lParticular = listCtrl.findListaParticular(nombreLista);
        lParticular.setEsPrivada(lParticular.isEsPrivada() == false);
        try {
            listCtrl.edit(lParticular);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Cliente> listarClientes() {
        ClienteJpaController cliCtrl = new ClienteJpaController(fact);
        return new ArrayList(cliCtrl.findClienteEntities());
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Artista> listarArtistas() {
        ArtistaJpaController cliCtrl = new ArtistaJpaController(fact);
        return new ArrayList(cliCtrl.findArtistaEntities());
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Genero> listarGeneros() {
        GeneroJpaController gCtrl = new GeneroJpaController(fact);
        return new ArrayList(gCtrl.findGeneroEntities());
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Genero> getListGenero(String nombre) {
        Genero g = new Genero();
        GeneroJpaController genCtrl = new GeneroJpaController(fact);
        g = genCtrl.findGenero(nombre);
        return new ArrayList(g.getListHijos());
    }

    @WebMethod
    public Album buscarAlbum(String nombre) {
        Album a = null;
        AlbumJpaController crlA = new AlbumJpaController(fact);
        a = crlA.findAlbum(nombre);
        return a;
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Album> listarAlbum() {
        AlbumJpaController crlA = new AlbumJpaController(fact);
        return new ArrayList(crlA.findAlbumEntities());
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Usuario> listarUsuarios() {
        UsuarioJpaController crlU = new UsuarioJpaController(fact);
        return new ArrayList(crlU.findUsuarioEntities());
    }

    @WebMethod
    public Cliente getCli(String nickname) {
        Cliente c = null;
        ClienteJpaController crlU = new ClienteJpaController(fact);
        c = crlU.findCliente(nickname);
        return c;
    }

    @WebMethod
    public Usuario getUsuario(String nickname) {
        Usuario c = null;
        UsuarioJpaController crlU = new UsuarioJpaController(fact);
        c = crlU.findUsuario(nickname);
        return c;
    }
//    @WebMethod

    @WebMethod
    public ArrayList<Temas> listarTemas() {
        TemasJpaController crlT = new TemasJpaController(fact);
        return new ArrayList(crlT.findTemasEntities());
    }

    @WebMethod
    public Temas getTema(String name) {
        Temas t = null;
        TemasJpaController crlT = new TemasJpaController(fact);
        t = crlT.findTemas(name);
        return t;
    }

    @WebMethod
    public ListaReproduccion getlr(String name) {
        ListaReproduccion lr = null;
        ListaReproduccionJpaController crllr = new ListaReproduccionJpaController(fact);
        lr = crllr.findListaReproduccion(name);
        return lr;
    }

    @WebMethod
    public Genero buscarGenero(String nombre) {
        ExtJpaGenero crl = new ExtJpaGenero(fact);
        return crl.findGenero(nombre);
    }
//    @WebMethod

    @WebMethod
    public ArrayList<ListaDefecto> listarDefecto() {
        ListaDefectoJpaController crlld = new ListaDefectoJpaController(fact);
        return new ArrayList(crlld.findListaDefectoEntities());
    }
//    @WebMethod

    @WebMethod
    public ArrayList<ListaReproduccion> listarListaRepr() {
        ListaReproduccionJpaController ctrl = new ListaReproduccionJpaController(fact);
        return new ArrayList(ctrl.findListaReproduccionEntities());

    }

    @WebMethod
    public Usuario buscarUsrMail(String mail) {
        String nick = null;
        Usuario c = null;
        Usuario aux;
        List<Usuario> usuarios = listarUsuarios();
        Iterator<Usuario> it = usuarios.iterator();
        while (it.hasNext()) {
            c = it.next();
            if (c.getMail().equals(mail)) {
                nick = c.getNickname();
            }
        }

        return aux = this.getUsuario(nick);
    }

    @WebMethod
    public Artista seleccionarArtistaPorNombre(String name) {
        ArtistaJpaController ctrArtista = new ArtistaJpaController(fact);
        List<Artista> aux = ctrArtista.findArtistaEntities();
        Artista retorno = null;
        for (Artista artista : aux) {
            if (artista.getNombre().equals(name)) {
                retorno = artista;
            }
        }
        return retorno;

    }

    @WebMethod
    public void setImageCli(Cliente cli) {
        ClienteJpaController ctrCli = new ClienteJpaController(fact);
        try {
            ctrCli.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void setImageArt(Artista art) {
        ArtistaJpaController ctrCli = new ArtistaJpaController(fact);
        try {
            ctrCli.edit(art);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void setImage(Album aux) {
        AlbumJpaController ctr = new AlbumJpaController(fact);
        try {
            ctr.edit(aux);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public void setTema(Temas aux) {
        TemasJpaController ctr = new TemasJpaController(fact);
        try {
            ctr.edit(aux);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    public ArrayList<Usuario> getSeguidos(String nick) {
        UsuarioJpaController ctr = new UsuarioJpaController(fact);
        Usuario u = ctr.findUsuario(nick);
        return new ArrayList<Usuario>(u.getLstSeguidos());
    }

    @WebMethod
    public ArrayList<Genero> getGenerosHijos(String nombre) {
        GeneroJpaController ctr = new GeneroJpaController(fact);
        Genero g = ctr.findGenero(nombre);
        return new ArrayList<Genero>(g.getListHijos());
    }

    @WebMethod
    public ArrayList<Album> getAlbumsdeGeneros(String nombre) {
        GeneroJpaController ctr = new GeneroJpaController(fact);
        Genero g = ctr.findGenero(nombre);
        return new ArrayList<Album>(g.getListAlbum());
    }

    @WebMethod(exclude = true)
    public void cargarDatos() {

        dataFecha fecha = new dataFecha(1, 1, 1980);
        dataUsuario u1 = new dataUsuario("ji", "Julio", "Iglesias", "ji@tecnopotify.java", fecha, "", "ji");
        dataUsuario u2 = new dataUsuario("ei", "Enrique", "Iglesias", "ei@tecnopotify.java", fecha, "", "ei");
        dataUsuario u3 = new dataUsuario("rm", "Ricky", "Martin", "rm@tecnopotify.java", fecha, "", "rm");
        dataUsuario u4 = new dataUsuario("er", "El", "Reja", "er@tecnopotify.java", fecha, "", "er");
        crearArtista("axaxaxa", "www.ji.com", u1);
        crearArtista("sxsxsxx", "www.ei.com", u2);
        crearArtista("dxdxdxx", "www.rm.com", u3);
        crearArtista("ffxfxfx", "www.er.com", u4);
        dataUsuario u5 = new dataUsuario("discoteishon", "Carlos", "Nuñez", "cn@tecnopotify.java", fecha, "", "discoteishon");
        dataUsuario u6 = new dataUsuario("md", "Melany", "Dolgay", "md@tecnopotify.java", fecha, "", "md");
        dataUsuario u7 = new dataUsuario("db", "Diego", "Barreto", "db@tecnopotify.java", fecha, "home/diego-lucia/Imágenes/Modelo.png", "db");
        crearCliente(u5);
        crearCliente(u6);
        crearCliente(u7);
        dataGenero g1 = new dataGenero("Genero", "");
        dataGenero g2 = new dataGenero("Genero2", "Genero");
        dataGenero g3 = new dataGenero("Genero3", "Genero");
        Genero G1 = new Genero(g1);
        Genero G2 = new Genero(g2);
        Genero G3 = new Genero(g3);

        Cliente cliPrueba = new Cliente(u7);
        Artista uPrueba = new Artista("axaxaxa", "www.ji.com", u1);
        cliPrueba.addToSeguidos(uPrueba);

        altaGenero(g1);
        altaGenero(g2);
        altaGenero(g3);
        dataAlbum a1 = new dataAlbum("album1", 1990, "");
        dataAlbum a2 = new dataAlbum("album2", 1991, "");
        dataAlbum a3 = new dataAlbum("album3", 1992, "");
        dataAlbum a4 = new dataAlbum("album4", 1993, "");

        crearAlbum(u1.getNickname(), a1);
        crearAlbum(u2.getNickname(), a2);
        crearAlbum(u3.getNickname(), a3);
        crearAlbum(u4.getNickname(), a4);
        dataTemas t1 = new dataTemas("tema1", "2:30", 1, "www.youtube.com");
        dataTemas t2 = new dataTemas("tema2", "2:31", 2, "www.youtube.com");
        dataTemas t3 = new dataTemas("tema3", "2:32", 3, "www.youtube.com");
        dataTemas t4 = new dataTemas("tema4", "2:33", 4, "www.youtube.com");
        dataTemas t5 = new dataTemas("tema5", "2:35", 1, "www.youtube.com");
        dataTemas t6 = new dataTemas("tema6", "2:36", 2, "www.youtube.com");
        dataTemas t7 = new dataTemas("tema7", "2:38", 3, "www.youtube.com");
        dataTemas t8 = new dataTemas("tema8", "2:39", 4, "www.youtube.com");
        Album A1 = new Album(a1);
        A1.getListGenero().add(G1);
        Album A2 = new Album(a2);
        A2.getListGenero().add(G2);
        Album A3 = new Album(a3);
        A3.getListGenero().add(G3);
        Album A4 = new Album(a4);
        A4.getListGenero().add(G3);

        altaTema(t1, A1.getNombre());
        altaTema(t2, A1.getNombre());
        altaTema(t3, A1.getNombre());
        altaTema(t4, A1.getNombre());
        altaTema(t5, A2.getNombre());
        altaTema(t6, A2.getNombre());
        altaTema(t7, A3.getNombre());
        altaTema(t8, A4.getNombre());

        agregarFavorito(true, false, false, "discoteishon", "tema2");
        agregarFavorito(true, false, false, "discoteishon", "tema3");
        agregarFavorito(true, false, false, "discoteishon", "tema4");
        agregarFavorito(false, false, true, "discoteishon", "album1");
        agregarFavorito(false, false, true, "db", "album3");

        agregarFavorito(true, false, false, "md", "tema3");

        dataListaReproduccion listaRep = new dataListaReproduccion("lista1", "");
        ListaParticular listapar1 = new ListaParticular(true, cliPrueba, listaRep);
        dataListaReproduccion listaRep2 = new dataListaReproduccion("lista2", "");
        ListaParticular listapar2 = new ListaParticular(true, cliPrueba, listaRep2);

        crearListaParticular(true, cliPrueba.getNickname(), listaRep);
        crearListaParticular(true, cliPrueba.getNickname(), listaRep2);

    }

//    @WebMethod(exclude=true)
    public void publicar() {
        Endpoint.publish("http://localhost:2425/Tecnopotify", this);
    }

}
