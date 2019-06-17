package vista.bienvenida;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelInicioSesion extends JPanel {

    private JTextField textoNickname;
    private JPasswordField textoContraseña;
    private GridBagConstraints gbc;
    private VentanaBienvenida vb;

    /**
     * Constructor de la clase VentanaBievenida.
     *
     * @param vb
     */
    public PanelInicioSesion(VentanaBienvenida vb) {
        this.vb = vb;

        setLayout(new GridBagLayout());
        setOpaque(false);
        gbc = new GridBagConstraints();

        JLabel labelIniciarSesion = new JLabel(new ImageIcon("Imagenes/IniciarSesion.png"));
        gbc(0, 0, 2, 1, 0, 0);
        add(labelIniciarSesion, gbc);

        JLabel labelNickname = new JLabel(new ImageIcon("Imagenes/Nickname.png"));
        gbc(0, 1, 1, 1, 0, 0);
        add(labelNickname, gbc);

        textoNickname = new JTextField();
        crearJTextField(textoNickname);
        gbc(1, 1, 1, 1, 0, 10);
        add(textoNickname, gbc);

        JLabel labelContraseña = new JLabel(new ImageIcon("Imagenes/Password.png"));
        gbc(0, 2, 1, 1, 0, 0);
        add(labelContraseña, gbc);

        textoContraseña = new JPasswordField();
        crearJPasswordField(textoContraseña);
        gbc(1, 2, 1, 1, 0, 10);

        add(textoContraseña, gbc);

        JButton botonIniciarSesion = new JButton(new ImageIcon("Imagenes/botonLogin.png"));
        botonIniciarSesion.setRolloverIcon(new ImageIcon("Imagenes/botonLoginRollover.png"));
        crearBoton(botonIniciarSesion);
        gbc(0, 3, 2, 1, 0, 0);
        botonIniciarSesion.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vb.inicioSesion(textoNickname.getText(), new String(textoContraseña.getPassword()));
            }
        }
        );
        add(botonIniciarSesion, gbc);

        JButton botonManual = new JButton(new ImageIcon("Imagenes/comojugar.png"));
        botonManual.setRolloverIcon(new ImageIcon("Imagenes/comojugarRollover.png"));
        crearBoton(botonManual);
        gbc(0, 4, 2, 1, 0, 0);
        botonManual.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }
        );
        add(botonManual, gbc);

    }

    /**
     * Crea los parametros del GridBagConstraints.
     *
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight
     * @param ipadx
     * @param ipady
     */
    public void gbc(int gridx, int gridy, int gridwidth, int gridheight, int ipadx, int ipady) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
    }

    /**
     * Disña un boton.
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
     * Diseña un JTextField.
     *
     * @param texto
     */
    public void crearJTextField(JTextField texto) {
        texto.setFont(new Font("Rockwell", Font.PLAIN, 24));
        texto.setForeground(Color.red);
        texto.setHorizontalAlignment(JTextField.CENTER);
        texto.setColumns(10);
    }

    /**
     * Diseña un JPasswordField.
     *
     * @param texto
     */
    public void crearJPasswordField(JPasswordField texto) {
        texto.setFont(new Font("Rockwell", Font.PLAIN, 24));
        texto.setForeground(Color.red);
        texto.setHorizontalAlignment(JTextField.CENTER);
        texto.setColumns(10);
    }

    /**
     * Limpia los JTextField textoNickname, y textoContraseña.
     */
    public void limpiarCampoTexto() {
        textoNickname.setText("");
        textoContraseña.setText("");
    }

}
