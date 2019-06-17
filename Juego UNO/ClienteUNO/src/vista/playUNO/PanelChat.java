package vista.playUNO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelChat extends JPanel {

    private VentanaJuegoUno vju;
    private JTextField textoChat;
    private JTextArea areaPantalla;

    /**
     * Constructor de la clase Panel PanelChat.
     *
     * @param vju
     */
    public PanelChat(VentanaJuegoUno vju) {
        this.vju = vju;
        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        areaPantalla = new JTextArea();
        areaPantalla.setBackground(Color.ORANGE);
        areaPantalla.setEditable(false);
        areaPantalla.setForeground(Color.RED);
        areaPantalla.setFont(new Font("Riven Normal", Font.PLAIN, 20));
        add(new JScrollPane(areaPantalla), BorderLayout.CENTER);

        textoChat = new JTextField();
        textoChat.setForeground(Color.black);
        textoChat.setFont(new Font("Riven Normal", Font.PLAIN, 20));
        add(textoChat, BorderLayout.SOUTH);
        textoChat.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vju.enviarMensaje(e.getActionCommand());
                textoChat.setText("");
            }
        }
        );

    }

    /**
     * Imprime en la pantalla el texto.
     *
     * @param texto
     */
    public void setAreaPantalla(String texto) {
        areaPantalla.append(texto);
    }

}
