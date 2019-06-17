package vista.datos;

import Modelo.ConexionBD;
import vista.bienvenida.VentanaBienvenida;
import vista.playUNO.VentanaJuegoUno;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDatos extends JFrame {

    private PanelDatos panelDatos;
    private PanelEditar panelEditar;
    private PanelJugar panelJugar;
    private PanelRegresar panelRegresar;
    private String nickname;
    private GridBagConstraints gbc;

    /**
     * Constructor de la clase VentanaDatos.
     */
    public VentanaDatos() {
        super("Perfil");

        setLayout(new GridLayout(1, 1));
        setIconImage(new ImageIcon("Imagenes/Icono.png").getImage());

        JPanel panelFondo = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(new ImageIcon("Imagenes/imgFondo.png").getImage(), 0, 0, 1366, 768, null);
            }
        };
        panelFondo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        JLabel labelDato = new JLabel(new ImageIcon("Imagenes/datosJugador.png"));
        gbc(0, 0, 1, 1);
        panelFondo.add(labelDato, gbc);

        panelDatos = new PanelDatos();
        gbc(0, 1, 1, 1);
        panelFondo.add(panelDatos, gbc);

        panelJugar = new PanelJugar(this);
        gbc(0, 2, 2, 1);
        panelFondo.add(panelJugar, gbc);

        panelEditar = new PanelEditar(this);
        gbc(1, 1, 1, 1);
        panelFondo.add(panelEditar, gbc);

        panelRegresar = new PanelRegresar(this);
        gbc(1, 0, 1, 1);
        panelFondo.add(panelRegresar, gbc);

        add(panelFondo);
        ventanaDatos();
    }

    public void gbc(int panelDatos, int gridy, int gridwidth, int gridheight) {
        gbc.gridx = panelDatos;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
    }

    /**
     * Muestra los datos en el panelDatos de la clase PanelDatos del paquete
     * vista.datos
     *
     * @param n
     * @param e
     * @param s
     * @param p
     */
    public void mostrarDatos(String n, String e, String s, int p) {
        nickname = n;
        panelDatos.setTextoUsuario(n);
        panelDatos.setTextoEdad(e);
        panelDatos.setTextoSexo(s);
        panelDatos.setTextoPuntaje(String.valueOf(p));
    }

    /**
     * Regresa a la ventana de bienvenida instanciando VentanaBienvenida() y
     * cierra la ventana de datos.
     */
    public void regresarVentanaBienvenida() {
        VentanaBienvenida vb = new VentanaBienvenida();
        this.dispose();
    }

    /**
     * Cambia la contraseña de un usuario conectandose a la base de datos de
     * MySql.
     *
     * @param nickname
     * @param passwordAntigua
     * @param passwordNueva
     */
    public void cambiarPassword(String nickname, String passwordAntigua, String passwordNueva) {
        ConexionBD conexionBD = new ConexionBD();
        conexionBD.cambiarPassword(nickname, passwordAntigua, passwordNueva);
    }

    /**
     * Retorna panelDatos de la clase PanelDatos.
     *
     * @return
     */
    public PanelDatos getPanelDatos() {
        return panelDatos;
    }

    /**
     * Retorna panelEditar de la clase PanelEditar.
     *
     * @return
     */
    public PanelEditar getPanelEditar() {
        return panelEditar;
    }

    /**
     * Retorna panelDJugar de la clase PanelJugar.
     *
     * @return
     */
    public PanelJugar getPanelJugar() {
        return panelJugar;
    }

    /**
     * Crea en un hilo miRunnable una istancia de VentanaJuegoUno y abre la
     * ventana principal.
     *
     * @param nickname
     */
    public void jugar(String nickname) {
        Runnable miRunnable = new Runnable() {
            @Override
            public void run() {
                VentanaJuegoUno vju = new VentanaJuegoUno(nickname);
            }
        };
        Thread server = new Thread(miRunnable);
        server.start();
        this.dispose();
    }

    /**
     * Contiene los parametros para abrir la ventana principal VentanaDatos.
     */
    public void ventanaDatos() {
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
