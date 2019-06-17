package vista.datos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PanelEditar extends JPanel {

    private String nickname;
    private JTextField textoContrasenaAntigua, textoContrasenaNueva;
    private VentanaDatos vd;
    private GridBagConstraints gbc;

    /**
     * Constructor de la clase PanelEditar.
     *
     * @param vd
     */
    public PanelEditar(VentanaDatos vd) {
        this.vd = vd;
        nickname = "";
        gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel labelCambiarContraseña = new JLabel(new ImageIcon("Imagenes/cambiarcontrasena.png"));
        gbc(0, 0, 2, 1, 0, 0);
        add(labelCambiarContraseña, gbc);

        JLabel labelContrasenaAntigua = new JLabel(new ImageIcon("Imagenes/cambiarcontrasenaAntigua.png"));
        gbc(0, 1, 1, 1, 0, 0);
        add(labelContrasenaAntigua, gbc);

        textoContrasenaAntigua = new JTextField();
        crearJTextField(textoContrasenaAntigua);
        gbc(1, 1, 1, 1, 0, 10);
        add(textoContrasenaAntigua, gbc);

        JLabel labelContrasenaNueva = new JLabel(new ImageIcon("Imagenes/cambiarcontrasenaNueva.png"));
        gbc(0, 2, 1, 1, 0, 0);
        add(labelContrasenaNueva, gbc);

        textoContrasenaNueva = new JTextField();
        crearJTextField(textoContrasenaNueva);
        gbc(1, 2, 1, 1, 0, 10);
        add(textoContrasenaNueva, gbc);

        JButton botonCambiarContraseña = new JButton(new ImageIcon("Imagenes/cambiar.png"));
        botonCambiarContraseña.setRolloverIcon(new ImageIcon("Imagenes/cambiarRollover.png"));
        crearBoton(botonCambiarContraseña);
        gbc(0, 3, 2, 1, 0, 0);
        add(botonCambiarContraseña, gbc);
        botonCambiarContraseña.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordSegura(new String(textoContrasenaNueva.getText()))) {
                    vd.cambiarPassword(nickname, textoContrasenaAntigua.getText(), textoContrasenaNueva.getText());
                    textoContrasenaAntigua.setText("");
                    textoContrasenaNueva.setText("");
                } else {
                    textoContrasenaAntigua.setText("");
                    textoContrasenaNueva.setText("");
                }

            }
        }
        );

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
     * Diseña un boton.
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
        gbc.fill = GridBagConstraints.NONE;
    }

    /**
     * Asigna un usuario de tipo String a la variable nickname.
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Comprueba si la contraseña escrita es segura, si es alfanumerica y si es
     * mayor a 8 caracteres o menor a 15, retornando un Boolean.
     *
     * @param password
     * @return
     */
    public Boolean passwordSegura(String password) {
        boolean estado = true;
        String secuenciaNumeros = "1";
        int numero = 2;

        for (int i = 1; i < 10; i++) {
            if (password.equalsIgnoreCase(secuenciaNumeros)) {
                estado = false;
                JOptionPane.showMessageDialog(null, "Digite una contraseña alfanumerica", "Contraseña insegura",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            secuenciaNumeros += String.valueOf(numero++);
        }

        if (!(password.length() >= 8 && password.length() <= 15)) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Digite una contraseña de almenos 8 caracteres\no maximo 15 caracteres", "Contraseña insegura",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return estado;

    }

}
