package vista.playUNO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Modelo.Baraja;
import Modelo.CartasTomadas;
import Modelo.ConexionBD;
import Modelo.GanadoresUNO;
import Modelo.PuntajeObtenido;
import Modelo.Servidor;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VentanaJuegoUno extends JFrame {

    private PanelBaraja panelBaraja;
    private PanelJugador1 panelJugador1;
    private PanelJugador2 panelJugador2;
    private PanelJugador3 panelJugador3;
    private PanelJugador4 panelJugador4;
    private PanelChat panelChat;
    private PanelRegresar panelRegresar;
    private PanelSonido panelSonido;
    private PanelUNO panelUno;
    private PanelMesa panelMesa;
    private Baraja baraja;
    private GridBagConstraints gbc;
    private Servidor servidor;
    private String nickname;
    private CartasTomadas ct;
    /**
     * Constructor de la clase VentanaJuegoUno.
     * @param nickname 
     */
    public VentanaJuegoUno(String nickname) {
        super("Player 1");
        gbc = new GridBagConstraints();
        baraja = new Baraja(this);
        this.nickname = nickname;
        ct = new CartasTomadas();

        setLayout(new GridBagLayout());
        setIconImage(new ImageIcon("Imagenes/Icono.png").getImage());

        panelJugador4 = new PanelJugador4();
        gbc(0, 0, 1, 3);
        add(panelJugador4, gbc);

        panelSonido = new PanelSonido(this);
        gbc(1, 0, 1, 1);
        add(panelSonido, gbc);

        JLabel espacio1 = new JLabel();
        espacio1.setOpaque(true);
        espacio1.setBackground(Color.ORANGE);
        gbc(1, 1, 1, 1);
        add(espacio1, gbc);

        JLabel espacio2 = new JLabel();
        espacio2.setOpaque(true);
        espacio2.setBackground(Color.ORANGE);
        gbc(4, 1, 1, 1);
        add(espacio2, gbc);

        panelChat = new PanelChat(this);
        gbc(1, 2, 1, 1);
        add(panelChat, gbc);

        panelJugador3 = new PanelJugador3();
        gbc(2, 0, 2, 1);
        add(panelJugador3, gbc);

        panelBaraja = new PanelBaraja(this);
        gbc(2, 1, 1, 1);
        add(panelBaraja, gbc);

        panelMesa = new PanelMesa(this);
        gbc(3, 1, 1, 1);
        add(panelMesa, gbc);

        panelJugador1 = new PanelJugador1(this);
        gbc(2, 2, 2, 1);
        add(panelJugador1, gbc);

        panelRegresar = new PanelRegresar(this);
        gbc(4, 0, 1, 1);
        add(panelRegresar, gbc);

        panelJugador2 = new PanelJugador2();
        gbc(5, 0, 1, 3);
        add(panelJugador2, gbc);

        panelUno = new PanelUNO(this);
        gbc(4, 2, 1, 1);
        add(panelUno, gbc);

        ventanaJuegoUno();

        servidor = new Servidor(this);
        servidor.ejecutarServidor();

    }
    /**
     * Asigna los parametros al GridBagConstraints.
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight 
     */
    public void gbc(int gridx, int gridy, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
    }
    /**
     * Retorna el nickname del jugador.
     * @return 
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * Retorna una carta de tipo Icon
     * @param numCarta 
     * @return 
     */
    public Icon getCarta(int numCarta) {
        return baraja.getCarta(numCarta);
    }
    /**
     * Da una carta de tipo Icon al panelJugador1.
     * @param numCarta 
     */
    public void darCartaJugador(int numCarta) {
        panelJugador1.agregarCartas(baraja.getCarta(numCarta), numCarta);
    }
    /**
     * Da una carta a la mesa de tipo Icon.
     * @param cartaEnJuego
     * @param posicionCarta 
     */
    public void darCartaMesa(int cartaEnJuego, int posicionCarta) {
        setTitle(getNickname());
        panelMesa.setMesa(baraja.getCarta(cartaEnJuego), cartaEnJuego, posicionCarta);
    }
    /**
     * Compara si se puede colocar una carta en panelMesa.
     * @param cartaEnJuego
     * @param cartaEnMesa
     * @param posicionCarta 
     */
    public void compararCarta(int cartaEnJuego, int cartaEnMesa, int posicionCarta) {
        if (baraja.compararCartas(cartaEnJuego, cartaEnMesa)) {
            panelMesa.setCambiarCarta(baraja.getCarta(cartaEnJuego), cartaEnJuego);
            panelJugador1.eliminarCarta(posicionCarta);

        } else {
            JOptionPane.showMessageDialog(null, "Coloca una carta del mismo color o valor\n Si no toma una carta",
                    "Movimiento Invalido", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Elimina una carta de panelJugador1.
     * @param posicionCarta 
     */
    public void eliminarCarta(int posicionCarta) {
        panelJugador1.eliminarCarta(posicionCarta);
    }
    /**
     * Envia un mensaje al servidor como flujo de salida.
     * @param mensaje 
     */
    public void enviarMensaje(final String mensaje) {

        servidor.enviarDatos(mensaje);
    }
    /**
     * Muestra un mensaje en la pantalla del panelChat.
     * @param mensaje 
     */
    public void mostrarMensaje(final String mensaje) {
        panelChat.setAreaPantalla(mensaje);
    }
    /**
     * Cambia el estado de la mesa, para saber si hay restriccion de colocar una carta sin 
     * compararse o no.
     * @param estado 
     */
    public void cambiarEstadoMesa(boolean estado) {
        panelMesa.setCambiarEstado(estado);
    }
    /**
     * Da una carta al cliente.
     * @param cartaEnMesa 
     */
    public void darCartaACliente(int cartaEnMesa) {
        servidor.enviarDatos(String.valueOf(cartaEnMesa));

    }
    /**
     * Cambia la carta de panelMesa.
     * @param cartaAMesa 
     */
    public void cambiarCartaMesa(int cartaAMesa) {
        panelMesa.cambiarCartaMesa(baraja.getCarta(cartaAMesa), cartaAMesa);
    }
    /**
     * Cambia el turno del jugador y la posibilidad de tomar carta.
     */
    public void setTurno() {
        panelJugador1.setTurno(true);
        panelBaraja.setTomarCarta(true);
    }
    /**
     * Manda el turno al cliente por flujo de salida.
     */
    public void mandarTurno() {
        servidor.enviarDatos("turnoCliente");
        panelJugador1.setTurno(false);
        panelBaraja.setTomarCarta(false);
    }
    /**
     * Da 2 cartas de tipo Icon al panelJugador1.
     */
    public void dar2Cartas() {
        JOptionPane.showMessageDialog(null, "come 2 cartas");
        int numCarta = 0;
        for (int i = 0; i < 2; i++) {
            numCarta = ThreadLocalRandom.current().nextInt(0, 60);
            try {
                darCartaJugador(numCarta);
            } catch (Exception e) {
                System.err.println("No se pudo dar las 2 cartas");
            }
        }
    }
    /**
     * Da 4 cartas al panelJugador1.
     */
    public void dar4Cartas() {
        JOptionPane.showMessageDialog(null, "come 4 cartas");
        int numCarta = 0;
        for (int i = 0; i < 4; i++) {
            numCarta = ThreadLocalRandom.current().nextInt(0, 60);
            try {
                darCartaJugador(numCarta);
            } catch (Exception e) {
                System.err.println("No se pudo dar las 4 cartas");
            }
        }
    }
    /**
     * Actualiza las cartas del cliente.
     * @param cuantasCartas 
     */
    public void cuantasCartas(int cuantasCartas) {
        panelJugador3.actualizarCartas(cuantasCartas);
    }
    /**
     * Manda un entero al estado del boton UNO
     * @param estadoUNO 
     */
    public void estadoUNO(int estadoUNO) {
        panelUno.setEstadoUNO(estadoUNO);
    }
    /**
     * Envia un dato de tipo boolean al panelUno.
     * @param unaCarta 
     */
    public void unaCarta(boolean unaCarta) {
        panelUno.setUnaCarta(unaCarta);
    }
    /**
     * Si el cliente le canto "UNO", le da 2 cartas al panelJugador1.
     */
    public void seCantoUNO() {
        if (panelUno.getEstadoUNO() == 0) {
            dar2Cartas();
            estadoUNO(2);
            unaCarta(false);
        } else {
            JOptionPane.showMessageDialog(null, "Jugador protegido contra el UNO");
        }
    }
    /**
     * Si el ganador es el servidor, guarda en un Archivo serializable de la clase GanadoresUNO.
     */
    public void ganadoresUno() {
        GanadoresUNO gu = new GanadoresUNO();
        gu.setNickname(getNickname());
        gu.setPuntaje("100");
        gu.guardarDatos();
        gu.mostrarDatos();
    }
    /**
     * Guarda el puntajeObtenido por el jugador en un archivo plano de la Clase PuntajeObtenido.
     * @param puntaje 
     */
    public void puntajeObtenido(String puntaje) {
        PuntajeObtenido po = new PuntajeObtenido();
        po.setNickname(getNickname());
        po.setPuntaje(puntaje);
        po.guardarDatos();
        po.mostrarDatos();
    }
    /**
     * Agregar la carta tomada por el jugador un arraylist de la clase CartaTomada.
     * @param numCarta 
     */
    public void cartaTomada(int numCarta) {
        ct.agregarDatos(getNickname(), baraja.getValor(numCarta), baraja.getColor(numCarta));
    }
    /**
     * Guarda la carta tomada por el jugador en un archivo plano de la clase CartaTomada.
     */
    public void guardarCartasTomadas() {
        ct.guardarDatos();
        ct.mostrarDatos();
    }
    /**
     * Guarda el puntaje obtenido en la base de datos MySql.
     * @param puntaje 
     */
    public void guardarPuntaje(int puntaje) {
        ConexionBD cb = new ConexionBD();
        cb.cambiarPuntaje(getNickname(), puntaje);
    }
    /**
     * Regresa a la ventana de Datos.
     */
    public void regresarVentanaDatos() {
        ConexionBD cb = new ConexionBD();
        if (cb.mostrarPerfilJugador(getNickname())) {            
            panelSonido.getSonido().setPausa(true);
            this.dispose();

        } else {
            System.out.println("No se pudo regresar");
        }

    }
    /**
     * Contiene los parametros para abrir la ventana  VentanaJuegoUno.
     */
    public void ventanaJuegoUno() {
        setSize(1366, 768);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
