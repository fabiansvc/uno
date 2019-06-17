package vista;

import vista.bienvenida.VentanaBienvenida;
import javax.swing.JFrame;
import vista.playUNO.VentanaJuegoUno;

public class Uno extends JFrame {
    /**
     * Istancia la clase VentanaBienvenida del paquete vista.bienvenida.
     * @param args 
     */
    public static void main(String args[]) {
        new VentanaJuegoUno("juliana");
    }
}
