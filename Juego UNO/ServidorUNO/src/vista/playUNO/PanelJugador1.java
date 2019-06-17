package vista.playUNO;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelJugador1 extends JPanel {

    private VentanaJuegoUno vju;
    private JButton botonAnterior;
    private JButton botonSiguiente;
    private GridBagConstraints gbc;
    private ArrayList<Icon> cartasJugador;
    private ArrayList<Integer> numeroCarta;
    private ArrayList<Integer> posicionCarta;
    private ManejadorEventos manejadorEventos;
    private JButton[] botones;
    private int posicionIncialDeCartasMostradas = 0;
    private int posicion = 0;
    private int cartaEnJuego = 0;
    private int posCarta = 0;
    private boolean turno = true;
    /**
     * Constructor de la clase PanelJugador1
     * @param vju 
     */
    public PanelJugador1(VentanaJuegoUno vju) {
        botones = new JButton[5];
        gbc = new GridBagConstraints();
        posicionCarta = new ArrayList();
        manejadorEventos = new ManejadorEventos();
        numeroCarta = new ArrayList();
        cartasJugador = new ArrayList();
        this.vju = vju;

        setLayout(new GridBagLayout());
        setBackground(new Color(255, 25, 25));

        crearBotonesCartas(botones); //Crea 5 botones.
        repartir5Cartas(cartasJugador);//Reparte las primeras 5 cartas al jugador.

        botonAnterior = new JButton("Anterior");
        crearBotones(botonAnterior, 0);
        add(botonAnterior, gbc);
        botonAnterior.addActionListener(manejadorEventos);

        botonSiguiente = new JButton("Siguiente");
        crearBotones(botonSiguiente, 3);
        add(botonSiguiente, gbc);
        botonSiguiente.addActionListener(manejadorEventos);

    }
    /**
     * Crea 5 botones.
     * @param botones 
     */
    public void crearBotonesCartas(JButton botones[]) {

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
            botones[i].setMargin(new Insets(-2, -2, -2, -2));
            botones[i].setBackground(new Color(255, 25, 25));
            botones[i].setBorder(null);
            botones[i].addActionListener(manejadorEventos);
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            add(botones[i], gbc);
        }
    }
    /**
     * Reparte 5 cartas a los botones.
     * @param cartasJugador 
     */
    public void repartir5Cartas(ArrayList<Icon> cartasJugador) {

        for (int i = 0; i < botones.length; i++) {
            int numCarta = ThreadLocalRandom.current().nextInt(0, 60); // Escoje un numero aleatorio de 0 hasta 60
            botones[i].setIcon(vju.getCarta(numCarta));// Pinta un icono en el boton
            cartasJugador.add(vju.getCarta(numCarta));// Agrega ese icono a un arraylist de iconos
            numeroCarta.add(numCarta); // Agrega el numero de la carta a un arraylist de enteros
            posicionCarta.add(i); // Agrega la posicion de la carta creada a un arraylist de enteros
            posCarta++; // se aumenta en 1 la posicion de la carta, que esta a su vez sera utilizada en el metodo agregarCartas.
            vju.cartaTomada(numCarta);
        }

    }
    /**
     * Agrega una nueva carta al jugador.
     * @param carta
     * @param numCarta 
     */
    public void agregarCartas(Icon carta, int numCarta) {
        cartasJugador.add(carta); // Agrega ese icono a un arraylist de iconos
        numeroCarta.add(numCarta); // Agrega el numero de la carta a un arraylist de enteros
        posicionCarta.add(posCarta); // Agrega la posicion de la carta creada a un arraylist de enteros
        posCarta++;  // se aumenta en 1 la posicion de la carta, que esta a su vez sera utilizada en el metodo agregarCartas.
        vju.cartaTomada(numCarta);
        actualizarBotones(botones); // Actualiza los botones con la carta nueva.
        vju.mandarTurno();
        cuantasCartasTiene(cartasJugador);

    }
    /**
     * Diseña los botones anterior y siguiente.
     * @param boton
     * @param gridx 
     */
    public void crearBotones(JButton boton, int gridx) { //Recibe un boton y una posicion de gridx de gbc
        boton.setFont(new Font("Riven Normal", Font.BOLD, 24));
        boton.setBackground(Color.ORANGE);
        boton.setFocusPainted(false);
        gbc.gridx = gridx;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
    }
    /**
     * Elimina una carta al jugador.
     * @param posicionAEliminar 
     */
    public void eliminarCarta(int posicionAEliminar) { // Recibe un entero de la posicion a eliminar 
        cartasJugador.remove(posicionAEliminar); // Elimina el icono del arraylist de iconos
        numeroCarta.remove(posicionAEliminar); // Elimina el numero de carta del arraylist de enteros
        posicionCarta.remove(posicionAEliminar); // Elimina la posicion de la carta del arraylist de enteros
        actualizarBotones(botones); // Actuliza los botones ya con la carta eliminada
        cuantasCartasTiene(cartasJugador); // Hace un conteo de cuantas cartas el jugador tiene         
    }
    /**
     * Actualiza las cartas del jugador
     * @param botones 
     */
    public void actualizarBotones(JButton botones[]) { // Recibe un arreglo de botones
        for (int i = 0; i < botones.length; i++) {
            try {
                botones[i].setIcon(cartasJugador.get(posicionIncialDeCartasMostradas + i)); // Pinta Las cartas dependiendo de la posicion de cartas que se ven al jugador
            } catch (Exception e) {
                botones[i].setIcon(new ImageIcon("Imagenes/cartaRoja.png")); // Si Ocurre que pinta una carta en una posicion null, la carta se pinta de color rojo, es decir que no existe.
            }
        }

    }
    /**
     * Hace el calculo de cuantas cartas tiene el jugador.
     * @param cartasJugador 
     */
    public void cuantasCartasTiene(ArrayList<Icon> cartasJugador) { // Recibe un arraylist de cartasJugador
        int cuantasCartas = 0;
        for (int i = 0; i < cartasJugador.size(); i++) {
            if (cartasJugador.get(i) != null) { // Hace un conteo de las cartas que tiene
                cuantasCartas++;
            }
        }
        vju.enviarMensaje(String.valueOf("player1 " + cuantasCartas));

        if (cuantasCartas == 1) {
            vju.unaCarta(true);
            vju.estadoUNO(0);
        } else if (cuantasCartas == 0) {
            JOptionPane.showMessageDialog(null, "Ganastes");
            vju.enviarMensaje("perdioCliente");
            vju.ganadoresUno();
            vju.puntajeObtenido("100");
            vju.guardarCartasTomadas();
            vju.guardarPuntaje(100);
        }

    }
    /**
     * Cambia el estado boolean del turno del jugador.
     * @param turno 
     */
    public void setTurno(boolean turno) {
        this.turno = turno;
    }
    /**
     * La escucha de los botones anterior, siguiente y las cartas.
     */
    private class ManejadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (botonAnterior == ae.getSource()) { // Si escucha el boton Anterior reduce la p.i.d.m en -5
                posicionIncialDeCartasMostradas -= 5;
                actualizarBotones(botones); // Actualiza los botones
            } else if (botonSiguiente == ae.getSource()) { // Si escucha el boton Anterior reduce la p.i.d.m en -5
                posicionIncialDeCartasMostradas += 5; // Actualiza los botones
                actualizarBotones(botones);
            }
            if (turno) {
                for (int i = 0; i < botones.length; i++) {
                    if (botones[i] == ae.getSource()) {
                        posicion = i;
                        cartaEnJuego = posicion + posicionIncialDeCartasMostradas;
                        vju.darCartaMesa(numeroCarta.get(cartaEnJuego), cartaEnJuego);
                    }
                }

            }

        }

    }

}
