package Modelo;

import vista.playUNO.VentanaJuegoUno;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Baraja {

    private Carta cartas[];
    private int numCarta = 0;
    private VentanaJuegoUno vju;

    /**
     * Constructor de la clase Baraja.
     *
     * @param vju
     */
    public Baraja(VentanaJuegoUno vju) {
        this.vju = vju;
        cartas = new Carta[60];
        agregarCartas(cartas);
    }

    /**
     * Retorna una carta de tipo Icon.
     *
     * @param i
     * @return
     */
    public Icon getCarta(int i) {
        return cartas[i].getCarta();
    }

    /**
     * Retorna un valor de la carta de tipo String.
     *
     * @param i
     * @return
     */
    public String getValor(int i) {
        return cartas[i].getValor();
    }

    /**
     * Retona un color de la carta de tipo String.
     *
     * @param i
     * @return
     */
    public String getColor(int i) {
        return cartas[i].getColor();
    }

    /**
     * Agrega todas las cartas a un arreglo de tipo Carta.
     *
     * @param cartas
     */
    public void agregarCartas(Carta cartas[]) {

        cartas[0] = new Carta(new ImageIcon("Imagenes/Cartas/ceroAzul.png"), "cero", "azul");
        cartas[1] = new Carta(new ImageIcon("Imagenes/Cartas/unoAzul.png"), "uno", "azul");
        cartas[2] = new Carta(new ImageIcon("Imagenes/Cartas/dosAzul.png"), "dos", "azul");
        cartas[3] = new Carta(new ImageIcon("Imagenes/Cartas/tresAzul.png"), "tres", "azul");
        cartas[4] = new Carta(new ImageIcon("Imagenes/Cartas/cuatroAzul.png"), "cuatro", "azul");
        cartas[5] = new Carta(new ImageIcon("Imagenes/Cartas/cincoAzul.png"), "cinco", "azul");
        cartas[6] = new Carta(new ImageIcon("Imagenes/Cartas/seisAzul.png"), "seis", "azul");
        cartas[7] = new Carta(new ImageIcon("Imagenes/Cartas/sieteAzul.png"), "siete", "azul");
        cartas[8] = new Carta(new ImageIcon("Imagenes/Cartas/ochoAzul.png"), "ocho", "azul");
        cartas[9] = new Carta(new ImageIcon("Imagenes/Cartas/nueveAzul.png"), "nueve", "azul");
        cartas[10] = new Carta(new ImageIcon("Imagenes/Cartas/cederAzul.png"), "ceder", "azul");
        cartas[11] = new Carta(new ImageIcon("Imagenes/Cartas/reservaAzul.png"), "reserva", "azul");
        cartas[12] = new Carta(new ImageIcon("Imagenes/Cartas/masDosAzul.png"), "masDos", null);
        cartas[13] = new Carta(new ImageIcon("Imagenes/Cartas/masCuatro.png"), "masCuatro", null);
        cartas[14] = new Carta(new ImageIcon("Imagenes/Cartas/CambioColores.png"), "colores", null);

        cartas[15] = new Carta(new ImageIcon("Imagenes/Cartas/ceroRojo.png"), "cero", "rojo");
        cartas[16] = new Carta(new ImageIcon("Imagenes/Cartas/unoRojo.png"), "uno", "rojo");
        cartas[17] = new Carta(new ImageIcon("Imagenes/Cartas/dosRojo.png"), "dos", "rojo");
        cartas[18] = new Carta(new ImageIcon("Imagenes/Cartas/tresRojo.png"), "tres", "rojo");
        cartas[19] = new Carta(new ImageIcon("Imagenes/Cartas/cuatroRojo.png"), "cuatro", "rojo");
        cartas[20] = new Carta(new ImageIcon("Imagenes/Cartas/cincoRojo.png"), "cinco", "rojo");
        cartas[21] = new Carta(new ImageIcon("Imagenes/Cartas/seisRojo.png"), "seis", "rojo");
        cartas[22] = new Carta(new ImageIcon("Imagenes/Cartas/sieteRojo.png"), "siete", "rojo");
        cartas[23] = new Carta(new ImageIcon("Imagenes/Cartas/ochoRojo.png"), "ocho", "rojo");
        cartas[24] = new Carta(new ImageIcon("Imagenes/Cartas/nueveRojo.png"), "nueve", "rojo");
        cartas[25] = new Carta(new ImageIcon("Imagenes/Cartas/cederRojo.png"), "ceder", "rojo");
        cartas[26] = new Carta(new ImageIcon("Imagenes/Cartas/reservaRojo.png"), "reserva", "rojo");
        cartas[27] = new Carta(new ImageIcon("Imagenes/Cartas/masDosRojo.png"), "masDos", null);
        cartas[28] = new Carta(new ImageIcon("Imagenes/Cartas/masCuatro.png"), "masCuatro", null);
        cartas[29] = new Carta(new ImageIcon("Imagenes/Cartas/CambioColores.png"), "colores", null);

        cartas[30] = new Carta(new ImageIcon("Imagenes/Cartas/ceroAmarillo.png"), "cero", "amarillo");
        cartas[31] = new Carta(new ImageIcon("Imagenes/Cartas/unoAmarillo.png"), "uno", "amarillo");
        cartas[32] = new Carta(new ImageIcon("Imagenes/Cartas/dosAmarillo.png"), "dos", "amarillo");
        cartas[33] = new Carta(new ImageIcon("Imagenes/Cartas/tresAmarillo.png"), "tres", "amarillo");
        cartas[34] = new Carta(new ImageIcon("Imagenes/Cartas/cuatroAmarillo.png"), "cuatro", "amarillo");
        cartas[35] = new Carta(new ImageIcon("Imagenes/Cartas/cincoAmarillo.png"), "cinco", "amarillo");
        cartas[36] = new Carta(new ImageIcon("Imagenes/Cartas/seisAmarillo.png"), "seis", "amarillo");
        cartas[37] = new Carta(new ImageIcon("Imagenes/Cartas/sieteAmarillo.png"), "siete", "amarillo");
        cartas[38] = new Carta(new ImageIcon("Imagenes/Cartas/ochoAmarillo.png"), "ocho", "amarillo");
        cartas[39] = new Carta(new ImageIcon("Imagenes/Cartas/nueveAmarillo.png"), "nueve", "amarillo");
        cartas[40] = new Carta(new ImageIcon("Imagenes/Cartas/cederAmarillo.png"), "ceder", "amarillo");
        cartas[41] = new Carta(new ImageIcon("Imagenes/Cartas/reservaAmarillo.png"), "reserva", "amarillo");
        cartas[42] = new Carta(new ImageIcon("Imagenes/Cartas/masDosAmarillo.png"), "masDos", null);
        cartas[43] = new Carta(new ImageIcon("Imagenes/Cartas/masCuatro.png"), "masCuatro", null);
        cartas[44] = new Carta(new ImageIcon("Imagenes/Cartas/CambioColores.png"), "colores", null);

        cartas[45] = new Carta(new ImageIcon("Imagenes/Cartas/ceroVerde.png"), "cero", "verde");
        cartas[46] = new Carta(new ImageIcon("Imagenes/Cartas/unoVerde.png"), "uno", "verde");
        cartas[47] = new Carta(new ImageIcon("Imagenes/Cartas/dosVerde.png"), "dos", "verde");
        cartas[48] = new Carta(new ImageIcon("Imagenes/Cartas/tresVerde.png"), "tres", "verde");
        cartas[49] = new Carta(new ImageIcon("Imagenes/Cartas/cuatroVerde.png"), "cuatro", "verde");
        cartas[50] = new Carta(new ImageIcon("Imagenes/Cartas/cincoVerde.png"), "cinco", "verde");
        cartas[51] = new Carta(new ImageIcon("Imagenes/Cartas/seisVerde.png"), "seis", "verde");
        cartas[52] = new Carta(new ImageIcon("Imagenes/Cartas/sieteVerde.png"), "siete", "verde");
        cartas[53] = new Carta(new ImageIcon("Imagenes/Cartas/ochoVerde.png"), "ocho", "verde");
        cartas[54] = new Carta(new ImageIcon("Imagenes/Cartas/nueveVerde.png"), "nueve", "verde");
        cartas[55] = new Carta(new ImageIcon("Imagenes/Cartas/cederVerde.png"), "ceder", "verde");
        cartas[56] = new Carta(new ImageIcon("Imagenes/Cartas/reservaVerde.png"), "reserva", "verde");
        cartas[57] = new Carta(new ImageIcon("Imagenes/Cartas/masDosVerde.png"), "masDos", null);
        cartas[58] = new Carta(new ImageIcon("Imagenes/Cartas/masCuatro.png"), "masCuatro", null);
        cartas[59] = new Carta(new ImageIcon("Imagenes/Cartas/CambioColores.png"), "colores", null);
    }

    /**
     * Compara las cartas con una posicion de una carta que esta en juego y otra
     * carta que esta en la mesa del juego, retornando un boolean.
     *
     * @param cartaEnJuego
     * @param cartaEnMesa
     * @return
     */
    public boolean compararCartas(int cartaEnJuego, int cartaEnMesa) {
        boolean estado = false;
        if ("colores".equalsIgnoreCase(cartas[cartaEnJuego].getValor())
                || "masDos".equalsIgnoreCase(cartas[cartaEnJuego].getValor())
                || "masCuatro".equalsIgnoreCase(cartas[cartaEnJuego].getValor())) {
            estado = true;
            if ("masDos".equals(cartas[cartaEnJuego].getValor())) {
                masDos();
            } else if ("masCuatro".equals(cartas[cartaEnJuego].getValor())) {
                masCuatro();
            } else if ("colores".equals(cartas[cartaEnJuego].getValor())) {
                colores();
            }
        } else if (cartas[cartaEnJuego].getColor() == cartas[cartaEnMesa].getColor()
                || cartas[cartaEnJuego].getValor() == cartas[cartaEnMesa].getValor()) {
            estado = true;
            if ("ceder".equals(cartas[cartaEnJuego].getValor())) {
                ceder();
            } else if ("reserva".equals(cartas[cartaEnJuego].getValor())) {
                reserva();
            } else {
                vju.mandarTurno();
            }
        }
        return estado;

    }

    /**
     * Movimiento reserva del juego UNO.
     */
    public void reserva() {
        JOptionPane.showMessageDialog(null, "reserva");
        vju.enviarMensaje("trueServidor");
    }

    /**
     * Movimiento carta mas 2 cartas del juego UNO.
     */
    public void masDos() {
        JOptionPane.showMessageDialog(null, "mas 2");
        vju.enviarMensaje("masDos1");
        vju.mandarTurno();
        vju.enviarMensaje("trueServidor");
    }

    /**
     * Movimiento carta mas 4 cartas del juego UNO.
     */
    public void masCuatro() {
        JOptionPane.showMessageDialog(null, "mas 4");
        vju.enviarMensaje("masCuatro1");
        vju.mandarTurno();
        vju.enviarMensaje("trueServidor");

    }

    /**
     * Movimiento carta colores del juego UNO.
     */
    public void colores() {
        vju.mandarTurno();
        vju.enviarMensaje("trueServidor");
    }

    /**
     * Movimiento carta ceder del juego UNO.
     */
    public void ceder() {
        JOptionPane.showMessageDialog(null, "Ceder");
        vju.enviarMensaje("trueServidor");

    }

}
