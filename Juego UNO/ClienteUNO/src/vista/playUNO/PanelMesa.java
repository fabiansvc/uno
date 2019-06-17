package vista.playUNO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMesa extends JPanel {

    private JLabel labelCarta;
    private VentanaJuegoUno vju;
    private boolean primeraVez;
    private int cartaEnMesa;
    /**
     * Constructor de la clase PanelMesa.
     * @param vju 
     */
    public PanelMesa(VentanaJuegoUno vju) {

        this.cartaEnMesa = 0;
        this.primeraVez = false;
        this.vju = vju;

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.ORANGE);

        ImageIcon mesa = new ImageIcon("Imagenes/ImgUno.png");

        labelCarta = new JLabel(mesa);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(labelCarta, gbc);

    }
    /**
     * Asigna una carta a la JLabel de la mesa, cambia el numero de la posicion de la baraja.
     * @param carta
     * @param cartaEnJuego
     * @param posicionCarta 
     */
    public void setMesa(Icon carta, int cartaEnJuego, int posicionCarta) {

        if (primeraVez) {
            primeraVez = false;
            labelCarta.setIcon(carta);
            cartaEnMesa = cartaEnJuego;
            vju.compararCarta(cartaEnJuego, cartaEnMesa, posicionCarta);
            vju.darCartaAServidor(cartaEnMesa);
        } else {
            vju.compararCarta(cartaEnJuego, cartaEnMesa, posicionCarta);
        }
    }
    /**
     * Cambia la carta que esta en el JLabel de la mesa y envia la carta por cliente-servidor.
     * @param carta
     * @param cartaEnJuego 
     */
    public void setCambiarCarta(Icon carta, int cartaEnJuego) {
        cartaEnMesa = cartaEnJuego;
        labelCarta.setIcon(carta);
        vju.darCartaAServidor(cartaEnMesa);
    }
    /**
     * Cambia el estado de la mesa, si es primera vez puede colocar cualquier carta.
     * @param estado 
     */
    public void setCambiarEstado(boolean estado) {
        this.primeraVez = estado;
    }
    /**
     * Cambia la carta de la mesa
     * @param carta
     * @param cartaACambiar 
     */
    public void cambiarCartaMesa(Icon carta, int cartaACambiar) {
        labelCarta.setIcon(carta);
        cartaEnMesa = cartaACambiar;
    }
}
