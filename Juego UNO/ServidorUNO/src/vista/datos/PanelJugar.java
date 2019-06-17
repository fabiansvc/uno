package vista.datos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelJugar extends JPanel {

    private String nickname;
    private VentanaDatos vd;
    private GridBagConstraints gbc;

    /**
     * Constructor de la clase Jugar
     *
     * @param vd
     */
    public PanelJugar(VentanaDatos vd) {
        this.vd = vd;
        nickname = "";
        gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        JButton botonJugar = new JButton(new ImageIcon("Imagenes/play.png"));
        botonJugar.setRolloverIcon(new ImageIcon("Imagenes/playRollover.png"));
        crearBoton(botonJugar);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        add(botonJugar, gbc);
        botonJugar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vd.jugar(nickname);
            }
        }
        );
    }

    /**
     * Diseña un boton
     *
     * @param boton
     */
    public void crearBoton(JButton boton) { //Recibe un boton y una posicion de gridx de gbc
        boton.setOpaque(false);
        boton.setFocusPainted(false);
        boton.setBorder(null);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
    }

    /**
     * Asigna un nickname de tipo String a la variable nickname;
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
