package vista.playUNO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.concurrent.ThreadLocalRandom;

public class PanelBaraja extends JPanel {
    
    private VentanaJuegoUno vju;
    private int numCarta;
    private boolean comerCarta;
    /**
     * Constructor de la clase PanelBaraja.
     * @param vju 
     */
    public PanelBaraja(VentanaJuegoUno vju) {
        this.comerCarta = true;
        numCarta = 0;
        this.vju = vju;
        setBackground(Color.ORANGE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton botonBaraja = new JButton(new ImageIcon("Imagenes/cartaUNOArriba.png"));
        botonBaraja.setMargin(new Insets(-2, -2, -2, -2));
        botonBaraja.setBackground(Color.ORANGE);
        botonBaraja.setBorder(null);
        botonBaraja.setRolloverIcon(new ImageIcon("Imagenes/cartaUNOArriba2.png"));
        botonBaraja.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(botonBaraja, gbc);
        botonBaraja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (comerCarta) {
                    try {
                        numCarta = ThreadLocalRandom.current().nextInt(0, 60);
                        vju.darCartaJugador(numCarta);
                        comerCarta = false;
                    } catch (Exception e) {
                        System.err.println("Error, no se pudo dar carta al jugador");
                    }
                }

            }

        }
        );

    }
    /**
     * Come una carta de la baraja.
     * @param comerCarta 
     */
    public void setTomarCarta(boolean comerCarta) {
        this.comerCarta = comerCarta;
    }
}
