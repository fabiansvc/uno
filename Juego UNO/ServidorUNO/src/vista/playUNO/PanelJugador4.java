package vista.playUNO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelJugador4 extends JPanel {

    private GridBagConstraints gbc;
    private JLabel cartas[];

    /**
     * Constructor de la clase PanelJugador4
     */
    public PanelJugador4() {
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 140, 0));

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
            cartas[i] = new JLabel(new ImageIcon("Imagenes/cartaUNOIzquierda.png"));
            cartas[i].setBackground(new Color(255, 140, 0));
            cartas[i].setBorder(null);
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            add(cartas[i], gbc);
        }
    }
}
