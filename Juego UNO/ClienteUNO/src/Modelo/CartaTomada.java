package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CartaTomada {

    private final File archivo;
    private ArrayList<CartaJugador> cartasJugador;

    /**
     * Constructor de la clase CartaTomada.
     */
    public CartaTomada() {
        archivo = new File("datos/cartasTomadas.uno");
        cartasJugador = new ArrayList();
    }

    /**
     * Guarda todas las cartas tomadas por el jugador en un archivo plano
     * cartasTomadas.uno.
     */
    public void guardarDatos() {
        try {
            PrintWriter salida = new PrintWriter(archivo);
            for (int i = 0; i < cartasJugador.size(); i++) {
                salida.println(cartasJugador.get(i).getNickname() + " tomo la carta: " + cartasJugador.get(i).getValor()
                        + " " + cartasJugador.get(i).getColor());
            }

            salida.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Archivo no encontrado");
        }
    }

    /**
     * Muestra los datos guardados del archivo plano cartasTomadas.uno.
     */
    public void mostrarDatos() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String datos = lector.readLine();
            while (datos != null) {
                System.out.println(datos);
                datos = lector.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.err.println("Error de I/O");
        }
    }

    /**
     * agrega los datos del nickname, valor, color a un arraylist de tipo
     * CartaJugador.
     *
     * @param nickname
     * @param valor
     * @param color
     */
    public void agregarDatos(String nickname, String valor, String color) {
        CartaJugador jugador = new CartaJugador(nickname, valor, color);
        cartasJugador.add(jugador);
        System.out.println(nickname + " tomo la carta: " + valor + " " + color);
    }

}
