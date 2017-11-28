package edu.tecnopotify.swing;

import edu.tecnopotify.fabrica.Fabrica;
import edu.tecnopotify.interfaces.IControlador;
import static edu.tecnopotify.swing.AltaGeneroJInternalFrame.xOffset;
import java.awt.Component;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlox
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Interfaz ctrl;
    private AltaClienteJInternalFrame altaUsuario;
    private AltaGeneroJInternalFrame alatGenero;
    private Fabrica fab = Fabrica.getInstance();
    private IControlador ctr = fab.getInstancia();

    // private JFrame frame = new JFrame();
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        ctr.cargarDatos();
        ctr.publicar();
    }

    protected void createFrameAltaCliente() {
        AltaClienteJInternalFrame frame = new AltaClienteJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void createFrameAltaArtista() {
        AltaPerfilJInternalFrame frame = new AltaPerfilJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void createFrameAltaGenero() {
        AltaGeneroJInternalFrame frame = new AltaGeneroJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void createFrameAltaAlbum() {
        AltaAlbumJInternalFrame frame = new AltaAlbumJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFrameAgregarFavorito() {
        AgregarFavoritoJInternalFrame frame = new AgregarFavoritoJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFrameCrearListaReproduccion() {
        CrearListaReproduccionJInternalFrame frame = new CrearListaReproduccionJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultarArtistaJInternalFrame() {
        consultarArtistaJInternalFrame frame = new consultarArtistaJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultarClienteJInternalFrame() {
        consultarClienteJInternalFrame frame = new consultarClienteJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultarListaDeReproduccionJInternalFrame() {
        ConsultaListaReproduccionJInternalFrame frame = new ConsultaListaReproduccionJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pruebaJInternalFrame() {
        pruebaJInternalFrame frame = new pruebaJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarSuscripcionJInternalFrame() {
        SuscripcionJInternalFrame frame = new SuscripcionJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void publicarListaReproduccionJInternalFrame() {
        PublicarListaJInternalFrame frame = new PublicarListaJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void consultarAlbumJInternalFrame() {
        ConsultarAlbumJInternalFrame frame = new ConsultarAlbumJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarTemaJInternalFrame() {
        agregarTemaJInternalFrame frame = new agregarTemaJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearTemaJInternalFrame() {
        crearTemaJInternalFrame frame = new crearTemaJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void createFrameSeguirUsuario() {
        SeguirUsuarioJInternalFrame frame = new SeguirUsuarioJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void cratedejarSeguirUsuario() {
        dejarUsuarioJInternalFrame frame = new dejarUsuarioJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void crateControlNavegacion() {
        ControlNavegacionJInternalFrame frame = new ControlNavegacionJInternalFrame();
        frame.setVisible(true);
        this.Desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu12 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        Desktop = new javax.swing.JDesktopPane();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menuUuarios = new javax.swing.JMenu();
        jMenuAltaPerfil = new javax.swing.JMenuItem();
        jMenuConsultarCliente = new javax.swing.JMenuItem();
        jMenuConsultarArtista = new javax.swing.JMenuItem();
        SeguirUsuario2 = new javax.swing.JMenuItem();
        DejarSeguir2 = new javax.swing.JMenuItem();
        jMenuModificarSuscripcion = new javax.swing.JMenuItem();
        menuGenero = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuFavs = new javax.swing.JMenu();
        menuGuardarFavorito = new javax.swing.JMenuItem();
        QuitarTema = new javax.swing.JMenuItem();
        menuLista = new javax.swing.JMenu();
        CrearTema = new javax.swing.JMenuItem();
        JMenuCrearListaRep = new javax.swing.JMenuItem();
        agregarTema = new javax.swing.JMenuItem();
        jMenuPublicarLista = new javax.swing.JMenuItem();
        jMenuConsutlarListaRep = new javax.swing.JMenuItem();
        controlNavegacion = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        jMenu12.setText("jMenu12");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frPrincipal"); // NOI18N

        jMenu3.setText("Menu");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        menuUuarios.setText("Usuarios");

        jMenuAltaPerfil.setText("Alta Perfil");
        jMenuAltaPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAltaPerfilActionPerformed(evt);
            }
        });
        menuUuarios.add(jMenuAltaPerfil);

        jMenuConsultarCliente.setText("Consultar cliente");
        jMenuConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConsultarClienteActionPerformed(evt);
            }
        });
        menuUuarios.add(jMenuConsultarCliente);

        jMenuConsultarArtista.setText("Consultar artista");
        jMenuConsultarArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConsultarArtistaActionPerformed(evt);
            }
        });
        menuUuarios.add(jMenuConsultarArtista);

        SeguirUsuario2.setText("Seguir Usuario");
        SeguirUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeguirUsuario2ActionPerformed(evt);
            }
        });
        menuUuarios.add(SeguirUsuario2);

        DejarSeguir2.setText("Dejar de Seguir Usuario");
        DejarSeguir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DejarSeguir2ActionPerformed(evt);
            }
        });
        menuUuarios.add(DejarSeguir2);

        jMenuModificarSuscripcion.setText("Modificar suscripción");
        jMenuModificarSuscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuModificarSuscripcionActionPerformed(evt);
            }
        });
        menuUuarios.add(jMenuModificarSuscripcion);

        jMenu3.add(menuUuarios);

        menuGenero.setText("Generos");

        jMenuItem4.setText("Crear genero");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCrearGeneroActionPerformed(evt);
            }
        });
        menuGenero.add(jMenuItem4);

        jMenu3.add(menuGenero);

        menuFavs.setText("Favoritos");
        menuFavs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFavsActionPerformed(evt);
            }
        });

        menuGuardarFavorito.setText("Guardar tema/lista/album");
        menuGuardarFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarFavoritoActionPerformed(evt);
            }
        });
        menuFavs.add(menuGuardarFavorito);

        QuitarTema.setText("Quitar  tema/lista/album");
        menuFavs.add(QuitarTema);

        jMenu3.add(menuFavs);

        menuLista.setText("Lista de reproduccion");

        CrearTema.setText("Crear tema");
        CrearTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearTemaActionPerformed(evt);
            }
        });
        menuLista.add(CrearTema);

        JMenuCrearListaRep.setText("Crear lista rep.");
        JMenuCrearListaRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuCrearListaRepActionPerformed(evt);
            }
        });
        menuLista.add(JMenuCrearListaRep);

        agregarTema.setText("Agregar/Quitar tema de Lista de Rep.");
        agregarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarTemaActionPerformed(evt);
            }
        });
        menuLista.add(agregarTema);

        jMenuPublicarLista.setText("Publicar lista");
        jMenuPublicarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPublicarListaActionPerformed(evt);
            }
        });
        menuLista.add(jMenuPublicarLista);

        jMenuConsutlarListaRep.setText("Consultar Lista");
        jMenuConsutlarListaRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConsutlarListaRepActionPerformed(evt);
            }
        });
        menuLista.add(jMenuConsutlarListaRep);

        jMenu3.add(menuLista);

        controlNavegacion.setText("Control Navegaciòn");
        controlNavegacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlNavegacionActionPerformed(evt);
            }
        });
        jMenu3.add(controlNavegacion);

        jMenuBar3.add(jMenu3);

        jMenu5.setText("Edit");
        jMenuBar3.add(jMenu5);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("ventanaPrincipal");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConsultarClienteActionPerformed
        // TODO add your handling code here:
        consultarClienteJInternalFrame();

    }//GEN-LAST:event_jMenuConsultarClienteActionPerformed

    private void jCrearGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCrearGeneroActionPerformed
        // TODO add your handling code here:
        createFrameAltaGenero();
    }//GEN-LAST:event_jCrearGeneroActionPerformed

    private void jMenuAltaPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAltaPerfilActionPerformed
        // TODO add your handling code here:
        createFrameAltaArtista();
    }//GEN-LAST:event_jMenuAltaPerfilActionPerformed

    private void SeguirUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeguirUsuario2ActionPerformed
        // TODO add your handling code here:
        createFrameSeguirUsuario();

    }//GEN-LAST:event_SeguirUsuario2ActionPerformed


    private void JMenuCrearListaRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuCrearListaRepActionPerformed
        // TODO add your handling code here:
        createFrameCrearListaReproduccion();
    }//GEN-LAST:event_JMenuCrearListaRepActionPerformed

    private void jMenuConsultarArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConsultarArtistaActionPerformed
        // TODO add your handling code here:
        consultarArtistaJInternalFrame();
    }//GEN-LAST:event_jMenuConsultarArtistaActionPerformed

    private void jMenuConsultarListaReproduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConsultarListaReproduccionActionPerformed
        // TODO add your handling code here:
        consultarListaDeReproduccionJInternalFrame();
    }//GEN-LAST:event_jMenuConsultarListaReproduccionActionPerformed


    private void agregarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarTemaActionPerformed
        // TODO add your handling code here:
        agregarTemaJInternalFrame();
    }//GEN-LAST:event_agregarTemaActionPerformed

    private void jMenuConsutlarListaRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConsutlarListaRepActionPerformed
        // TODO add your handling code here:
        consultarListaDeReproduccionJInternalFrame();
    }//GEN-LAST:event_jMenuConsutlarListaRepActionPerformed

    private void jMenuPublicarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPublicarListaActionPerformed
        // TODO add your handling code here:
        publicarListaReproduccionJInternalFrame();
    }//GEN-LAST:event_jMenuPublicarListaActionPerformed

    private void CrearTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearTemaActionPerformed
        // TODO add your handling code here:
        crearTemaJInternalFrame();
    }//GEN-LAST:event_CrearTemaActionPerformed


    private void menuGuardarFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarFavoritoActionPerformed
        // TODO add your handling code here:
        createFrameAgregarFavorito();
    }//GEN-LAST:event_menuGuardarFavoritoActionPerformed

    private void menuFavsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFavsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuFavsActionPerformed

    private void DejarSeguir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DejarSeguir2ActionPerformed
        // TODO add your handling code here:
        cratedejarSeguirUsuario();
    }//GEN-LAST:event_DejarSeguir2ActionPerformed

    private void jMenuModificarSuscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuModificarSuscripcionActionPerformed
        // TODO add your handling code here:
        modificarSuscripcionJInternalFrame();
    }//GEN-LAST:event_jMenuModificarSuscripcionActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void controlNavegacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlNavegacionActionPerformed
        // TODO add your handling code here:
        crateControlNavegacion();
    }//GEN-LAST:event_controlNavegacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CrearTema;
    private javax.swing.JMenuItem DejarSeguir2;
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuItem JMenuCrearListaRep;
    private javax.swing.JMenuItem QuitarTema;
    private javax.swing.JMenuItem SeguirUsuario2;
    private javax.swing.JMenuItem agregarTema;
    private javax.swing.JMenuItem controlNavegacion;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuAltaPerfil;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuConsultarArtista;
    private javax.swing.JMenuItem jMenuConsultarCliente;
    private javax.swing.JMenuItem jMenuConsutlarListaRep;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuModificarSuscripcion;
    private javax.swing.JMenuItem jMenuPublicarLista;
    private javax.swing.JMenu menuFavs;
    private javax.swing.JMenu menuGenero;
    private javax.swing.JMenuItem menuGuardarFavorito;
    private javax.swing.JMenu menuLista;
    private javax.swing.JMenu menuUuarios;
    // End of variables declaration//GEN-END:variables

    private static class Interfaz {

        public Interfaz() {
        }
    }
}
