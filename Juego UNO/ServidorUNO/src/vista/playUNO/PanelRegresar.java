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

public class PanelRegresar extends JPanel {

    private VentanaJuegoUno vju;
    /**
     * Constructor de la clase PanelRegresar.
     * @param vju 
     */
    public PanelRegresar(VentanaJuegoUno vju) {
        this.vju = vju;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.ORANGE);

        ImageIcon salir = new ImageIcon("Imagenes/Salir.png");

        JButton botonSalir = new JButton(salir);
        botonSalir.setBackground(Color.ORANGE);
        botonSalir.setMargin(new Insets(-2, -2, -2, -2));
        botonSalir.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(botonSalir, gbc);
        botonSalir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vju.regresarVentanaDatos();
            }

        });
    }
}
