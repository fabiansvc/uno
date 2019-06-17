package vista.playUNO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelJugador3 extends JPanel {

    private VentanaJuegoUno vju;
    private GridBagConstraints gbc;
    private JLabel cartas[];
    /**
     * Constructor de la clase PanelJugador3.
     */
    public PanelJugador3() {

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 25, 25));

        cartas = new JLabel[5];
        crearLabelsCartas(cartas);

    }
    /**
     * Crea 5 JLabel en diferentes posiciones con una imagen de una carta.
     *
     * @param cartas
     */
    public void crearLabelsCartas(JLabel cartas[]) {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new JLabel(new ImageIcon("Imagenes/cartaUNOAbajo.png"));
            cartas[i].setBackground(new Color(255, 25, 25));
            cartas[i].setBorder(null);
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            add(cartas[i], gbc);
        }
    }
    /**
     * Actualiza las cartas que tiene el jugador servidor.
     * @param numero 
     */
    public void actualizarCartas(int numero) {
        try {
            for (int i = 0; i < numero; i++) {
                cartas[i].setIcon(new ImageIcon("Imagenes/cartaUNOAbajo.png"));
            }
            for (int i = numero; i < cartas.length; i++) {
                cartas[i].setIcon(new ImageIcon("Imagenes/cartaRoja.png"));
            }
        } catch (Exception ae) {

        }
    }
}
