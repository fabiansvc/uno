package vista.datos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PanelRegresar extends JPanel {

    private VentanaDatos vd;
    private GridBagConstraints gbc;

    /**
     * Constructor de la clase PanelRegresar.
     *
     * @param vd
     */
    public PanelRegresar(VentanaDatos vd) {
        this.vd = vd;
        gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        JButton regresar = new JButton(new ImageIcon("Imagenes/regresar.png"));
        regresar.setRolloverIcon(new ImageIcon("Imagenes/regresarRollover.png"));
        crearBoton(regresar);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        add(regresar, gbc);
        regresar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                vd.regresarVentanaBienvenida();
            }
        }
        );
    }
        
    /**
     * Diseña un boton.
     * @param boton 
     */
    public void crearBoton(JButton boton) { //Recibe un boton y una posicion de gridx de gbc
        boton.setOpaque(false);
        boton.setFocusPainted(false);
        boton.setBorder(null);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
    }
}
