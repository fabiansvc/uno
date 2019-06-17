package vista.bienvenida;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelRegistro extends JPanel {

    private JTextField textoNickname, textoEdad;
    private JPasswordField textoContraseña;
    private JComboBox comboSexo;
    private final String sexo[];
    private GridBagConstraints gbc;
    private VentanaBienvenida vb;

    /**
     * Constructor de la clase PanelRegistro.
     *
     * @param vb
     */
    public PanelRegistro(VentanaBienvenida vb) {
        this.vb = vb;
        sexo = new String[]{"Masculino", "Femenino", "Otro"};

        setLayout(new GridBagLayout());
        setOpaque(false);
        gbc = new GridBagConstraints();

        JLabel registrarse = new JLabel(new ImageIcon("Imagenes/Registro.png"));
        gbc(0, 0, 4, 1, 0, 0);
        add(registrarse, gbc);

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

        JLabel labelSexo = new JLabel(new ImageIcon("Imagenes/Sexo.png"));
        gbc(0, 3, 1, 1, 0, 0);
        add(labelSexo, gbc);

        comboSexo = new JComboBox(sexo);
        comboSexo.setFont(new Font("Rockwell", Font.PLAIN, 24));
        comboSexo.setForeground(Color.red);
        comboSexo.setBackground(Color.WHITE);
        ((JLabel) comboSexo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        gbc(1, 3, 1, 1, 65, 6);
        add(comboSexo, gbc);

        JLabel labelEdad = new JLabel(new ImageIcon("Imagenes/Edad.png"));
        gbc(0, 4, 1, 1, 0, 0);
        add(labelEdad, gbc);

        textoEdad = new JTextField();
        crearJTextField(textoEdad);
        gbc(1, 4, 1, 1, 0, 10);
        add(textoEdad, gbc);

        JButton botonRegistrarse = new JButton(new ImageIcon("Imagenes/botonSignUp.png"));
        botonRegistrarse.setRolloverIcon(new ImageIcon("Imagenes/botonSignUpRollover.png"));
        crearBoton(botonRegistrarse);
        gbc(0, 5, 3, 1, 0, 0);
        botonRegistrarse.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordSegura(new String(textoContraseña.getPassword()))) {
                    vb.registro(textoNickname.getText(), new String(textoContraseña.getPassword()),
                            textoEdad.getText(), comboSexo.getSelectedItem().toString());
                    textoNickname.setText("");
                    textoContraseña.setText("");
                    textoEdad.setText("");
                } else {
                    textoNickname.setText("");
                    textoContraseña.setText("");
                    textoEdad.setText("");
                }

            }
        }
        );
        add(botonRegistrarse, gbc);

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
     * Disña un boton.
     *
     * @param boton
     */
    public void crearBoton(JButton boton) { //Recibe un boton y una posicion de gridx de gbc
        boton.setOpaque(false);
        boton.setBackground(Color.ORANGE);
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
