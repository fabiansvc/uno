package vista.datos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDatos extends JPanel {

    private JTextField textoNickname, textoSexo, textoEdad, textoPuntaje;
    private GridBagConstraints gbc;

    /**
     * Constructor de PanelDatos()
     */
    public PanelDatos() {
        gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel labelNickname = new JLabel(new ImageIcon("Imagenes/Nickname.png"));
        gbc(0, 0, 1, 1, 0, 0);
        add(labelNickname, gbc);

        textoNickname = new JTextField();
        crearJTextField(textoNickname);
        gbc(1, 0, 1, 1, 0, 10);
        add(textoNickname, gbc);

        JLabel labelEdad = new JLabel(new ImageIcon("Imagenes/Edad.png"));
        gbc(0, 1, 1, 1, 0, 0);
        add(labelEdad, gbc);

        textoEdad = new JTextField();
        crearJTextField(textoEdad);
        gbc(1, 1, 1, 1, 0, 10);
        add(textoEdad, gbc);

        JLabel labelSexo = new JLabel(new ImageIcon("Imagenes/Sexo.png"));
        gbc(0, 2, 1, 1, 0, 0);
        add(labelSexo, gbc);

        textoSexo = new JTextField();
        crearJTextField(textoSexo);
        gbc(1, 2, 1, 1, 0, 10);
        add(textoSexo, gbc);

        JLabel labelPuntaje = new JLabel(new ImageIcon("Imagenes/Puntaje.png"));
        gbc(0, 3, 1, 1, 0, 0);
        add(labelPuntaje, gbc);

        textoPuntaje = new JTextField();
        crearJTextField(textoPuntaje);
        gbc(1, 3, 1, 1, 0, 10);
        add(textoPuntaje, gbc);

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
     * @param t
     */
    public void setTextoUsuario(String t) {
        textoNickname.setText(t); //Sobreescribimos a el area de texto
    }

    /**
     * Asigna un sexo de tipo String a la variable sexo.
     *
     * @param t
     */
    public void setTextoSexo(String t) {
        textoSexo.setText(t); //Sobreescribimos a el area de texto
    }

    /**
     * Asigna una edad de tipo String a la variable edad.
     *
     * @param t
     */
    public void setTextoEdad(String t) {
        textoEdad.setText(t); //Sobreescribimos a el area de texto
    }

    /**
     * Asigna un puntaje de tipo String a la variable puntaje.
     * @param t
     */
    public void setTextoPuntaje(String t) {
        textoPuntaje.setText(t); //Sobreescribimos a el area de texto
    }

}
