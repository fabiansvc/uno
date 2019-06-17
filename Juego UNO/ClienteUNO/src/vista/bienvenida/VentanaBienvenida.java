package vista.bienvenida;

import Modelo.ConexionBD;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaBienvenida extends JFrame {
    
    private PanelInicioSesion panelInicioSesion;
    private PanelRegistro panelRegistro;

    /**
     * Constructor de la clase VentanaBienvenida
     */
    public VentanaBienvenida() {
        super("Uno");

        setIconImage(new ImageIcon("Imagenes/Icono.png").getImage());
        setLayout(new GridLayout(1, 1));
        JPanel panelFondo = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(new ImageIcon("Imagenes/imgFondo.png").getImage(), 0, 0, 1366, 768, null);
            }
        };
        panelFondo.setLayout(new GridLayout(1, 2));
        panelInicioSesion = new PanelInicioSesion(this);
        panelFondo.add(panelInicioSesion);

        panelRegistro = new PanelRegistro(this);
        panelFondo.add(panelRegistro);

        add(panelFondo);
        abrirVentana();
    }
    /**
     * Abre la ventana principal de bienvenida.
     */
    public void abrirVentana() {
        setSize(1366, 768);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Inicia sesion a un usuario buscandolo en la base datos.
     * @param nickname 
     * @param password 
     */
    public void inicioSesion(String nickname, String password) {
        ConexionBD conexionBD = new ConexionBD();
        if (conexionBD.iniciarSesion(nickname, password)) {
            this.dispose();
        } else {
            panelInicioSesion.limpiarCampoTexto();
        }
    }
    /**
     * Registra un usuario nuevo a la base de datos.
     * @param nickname
     * @param password
     * @param edad
     * @param sexo 
     */
    public void registro(String nickname, String password, String edad, String sexo) {
        ConexionBD conexionBD = new ConexionBD();
        conexionBD.registrar(nickname, password, edad, sexo, 0);

    }
    
}
