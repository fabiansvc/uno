package vista.playUNO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelUNO extends JPanel {

    private VentanaJuegoUno vju;
    private int estadoUNO;
    private boolean unaCarta;

    /**
     *Constructor de la clase PanelUNO
     * @param vju
     */
    public PanelUNO(VentanaJuegoUno vju) {
        this.vju = vju;
        unaCarta = false;
        estadoUNO = 2; // Valor defecto para que no se cante UNO, mientras tiene mas de 1 carta.

        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        JButton botonUno = new JButton(new ImageIcon("Imagenes/UNO.png"));
        botonUno.setMargin(new Insets(-2, -2, -2, -2));
        botonUno.setBackground(Color.ORANGE);
        botonUno.setBorder(null);
        botonUno.setFocusPainted(false);
        botonUno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unaCarta) {
                    estadoUNO = 1; // Esta contra el uno
                } else {
                    vju.enviarMensaje("unoServidor");
                }

            }

        }
        );

        add(botonUno, BorderLayout.CENTER);

    }
    /**
     * Retorna un int de la variable estadoUNO, si se puede cantar uno o no.
     * @return 
     */
    public int getEstadoUNO() {
        return estadoUNO;
    }
    /**
     * Asigna un int a la variable estadoUNO.
     * @param estadoUNO 
     */
    public void setEstadoUNO(int estadoUNO) {
        this.estadoUNO = estadoUNO;
    }
    /**
     * Asigna un dato boolean para saber si hay una carta o no.
     * @param unaCarta 
     */
    public void setUnaCarta(boolean unaCarta) {
        this.unaCarta = unaCarta;
    }

}
