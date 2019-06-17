package vista.playUNO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PanelSonido extends JPanel {

    private VentanaJuegoUno vju;
    private Player[] music;
    private boolean estado;
    private boolean pausa;
    private boolean contador;
    private boolean contador2;
    private int numCancion;
    private int numCancion1;
    private JButton botonRepetir, botonSiguiente, botonPausa;
    private Thread hilo;
    private Sonido sonido;
    /**
     * Constructor de la clase PanelSonido
     * @param vju 
     */
    public PanelSonido(VentanaJuegoUno vju) {
        this.vju = vju;
        
        music = new Player[11];
        estado = true;
        pausa = false;
        contador = true;
        contador2 = true;
        numCancion = ThreadLocalRandom.current().nextInt(11);
        numCancion1 = ThreadLocalRandom.current().nextInt(11);
        hilo = new Thread();
        sonido = new Sonido();

        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        ManejaSonido manejasonido = new ManejaSonido();

        JLabel imgSonido = new JLabel(new ImageIcon("Imagenes/sonido.png"));
        add(imgSonido, BorderLayout.CENTER);

        JPanel panelEventos = new JPanel();
        panelEventos.setLayout(new FlowLayout());
        panelEventos.setBackground(Color.ORANGE);

        botonRepetir = new JButton(new ImageIcon("Imagenes/botonAnterior.png"));
        crearBoton(botonRepetir);
        botonRepetir.addActionListener(manejasonido);
        panelEventos.add(botonRepetir);

        botonPausa = new JButton(new ImageIcon("Imagenes/botonPausa.png"));
        crearBoton(botonPausa);
        botonPausa.addActionListener(manejasonido);
        panelEventos.add(botonPausa);

        botonSiguiente = new JButton(new ImageIcon("Imagenes/botonSiguiente.png"));
        crearBoton(botonSiguiente);
        botonSiguiente.addActionListener(manejasonido);
        panelEventos.add(botonSiguiente);

        add(panelEventos, BorderLayout.SOUTH);

        musica();
    }
    /**
     * Reproduce la musica
     */
    public void musica() {
        numCancion = ThreadLocalRandom.current().nextInt(0, 11);
        sonido.start();
    }
    /**
     * Diseña un boton.
     * @param boton 
     */
    public void crearBoton(JButton boton) {
        boton.setMargin(new Insets(-2, -2, -2, -2));
        boton.setBorder(null);
        boton.setBackground(Color.ORANGE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
    }
    /**
     * Retorna la clase Sonido.
     * @return 
     */
    public Sonido getSonido(){
        return sonido;
    }

    public class Sonido extends Thread {

        private boolean pausa = false;
        /**
         * Constructor del sonido.
         */
        public Sonido() {
            try {
                music[0] = new Player(new FileInputStream("musica/Avicii.mp3"));
                music[1] = new Player(new FileInputStream("musica/Fade.mp3"));
                music[2] = new Player(new FileInputStream("musica/Dubstep.mp3"));
                music[3] = new Player(new FileInputStream("musica/Stressed Out.mp3"));
                music[4] = new Player(new FileInputStream("musica/titanium.mp3"));
                music[5] = new Player(new FileInputStream("musica/uncover.mp3"));
                music[6] = new Player(new FileInputStream("musica/Be The One.mp3"));
                music[7] = new Player(new FileInputStream("musica/Despacito.mp3"));
                music[8] = new Player(new FileInputStream("musica/Hey Brother.mp3"));
                music[9] = new Player(new FileInputStream("musica/In The Name Of Love.mp3"));
                music[10] = new Player(new FileInputStream("musica/La Player.mp3"));

            } catch (FileNotFoundException ex) {
                System.err.println("Archivo no encontrado");
            } catch (JavaLayerException ex) {
                Logger.getLogger(PanelSonido.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public void run() {
            while (true) {
                if (!pausa) {
                    try {
                        if (!music[numCancion1].play(1)) {
                            break;
                        }
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(PanelSonido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    break;
                }
            }
        }
        /**
         * Cambia el estado boolean de la variable pausa.
         * @param estado 
         */
        public void setPausa(boolean estado) {
            this.pausa = estado;
        }

    }
    /**
     * Es la escucha de los botones repetir, pausar, siguiente
     */
    public class ManejaSonido implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (botonRepetir == e.getSource()) {
                sonido.setPausa(true);
                sonido = new Sonido();
                sonido.start();
                botonPausa.setIcon(new ImageIcon("Imagenes/botonPausa.png"));
                estado = true;

            } else if (botonPausa == e.getSource()) {

                if (estado) {
                    sonido.setPausa(true);
                    botonPausa.setIcon(new ImageIcon("Imagenes/botonReproducir.png"));
                    estado = false;
                } else {
                    botonPausa.setIcon(new ImageIcon("Imagenes/botonPausa.png"));
                }

            } else if (botonSiguiente == e.getSource()) {
                sonido.stop();
                sonido = new Sonido();
                sonido.start();
                numCancion1 = ThreadLocalRandom.current().nextInt(0, 11);
                botonPausa.setIcon(new ImageIcon("Imagenes/botonPausa.png"));
            }

        }

    }

}
