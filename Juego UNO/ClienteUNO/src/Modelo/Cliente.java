package Modelo;

import vista.playUNO.VentanaJuegoUno;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {

    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket cliente;
    private VentanaJuegoUno vju;

    /**
     * Constructor de la clase Cliente.
     *
     * @param vju
     */
    public Cliente(VentanaJuegoUno vju) {
        this.vju = vju;
    }

    /**
     * Ejecuta el cliente con submetodos que conectan el cliente al servidor.
     */
    public void ejecutarCliente() {
        conectarAServidor();
        obtenerFlujos();
        procesarConexion();
        cerrarConexion();
    }

    /**
     * Conecta el cliente al servidor.
     */
    public void conectarAServidor() {
        try {
            cliente = new Socket("localhost", 12345);
        } catch (IOException ex) {
            System.err.println("No se establecio conexión con el servidor.");
        }
    }

    /**
     * Se establecen los flujos de entrada y salida.
     */
    public void obtenerFlujos() {
        try {
            salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            System.err.println("No se establecio flujos de entradas y salidas.");
        }

    }

    /**
     * Se procesa la conexion donde se leen los datos enviados del servidor.
     */
    public void procesarConexion() {
        String mensaje = "";
        do {
            try {
                mensaje = (String) entrada.readObject();
                mostrarMensaje(mensaje);

            } catch (ClassNotFoundException ex) {
                System.err.println("No se pudo leer el mensaje\n");
            } catch (IOException ex) {
                System.err.println("Error de I/O");
            }
        } while (!mensaje.equals("exit"));
    }

    /**
     * Se cierra los flujos de entrada, salida y el socket del cliente.
     */
    public void cerrarConexion() {
        try {
            salida.close();
            entrada.close();
            cliente.close();
            vju.mostrarMensaje("\n¡Conexión cerrada!");
        } catch (IOException excepcionES) {
            System.err.println("No se cerro la conexión");
        }
    }

    /**
     * Se envia datos como flujo de dato salida.
     *
     * @param mensaje
     */
    public void enviarDatos(String mensaje) {
        try {
            salida.writeObject(mensaje);
            salida.flush();
            mostrarMensaje(mensaje);
        } catch (IOException e) {
            System.err.println("No se pudo enviar datos");
        }
    }

    /**
     * Se muestra el mensaje en la pantalla del chat, asi mismo controlando el
     * cliente servidor de los movimientos del juego.
     *
     * @param mensaje
     */
    public void mostrarMensaje(String mensaje) {
        boolean estado = true;
        if (mensaje.equalsIgnoreCase("masDos1")) {
            estado = false;
        } else if ((mensaje.equalsIgnoreCase("masCuatro1"))) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("masDos")) {
            estado = false;
            vju.dar2Cartas();
        } else if (mensaje.equalsIgnoreCase("masCuatro")) {
            estado = false;
            vju.dar4Cartas();
        } else if (mensaje.equalsIgnoreCase("turnoCliente")) {
            estado = false;
            vju.setTurno();
        } else if (mensaje.equalsIgnoreCase("turnoServidor")) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("unoServidor")) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("unoCliente")) {
            estado = false;
            vju.seCantoUNO();
        } else if (mensaje.equalsIgnoreCase("perdioCliente")) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Game over");
            vju.puntajeObtenido("50");
            vju.guardarCartasTomadas();
            vju.guardarPuntaje(50);
        } else if (mensaje.equalsIgnoreCase("perdioServidor")) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("trueServidor")) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("trueCliente")) {
            estado = false;
            vju.cambiarEstadoMesa(true);
        }
        for (int i = 0; i < 50; i++) {
            if (mensaje.equalsIgnoreCase("player1 " + i)) {
                estado = false;
                vju.cuantasCartas(i);
            } else if (mensaje.equalsIgnoreCase("player2 " + i)) {
                estado = false;
            }
        }

        for (int i = 0; i < 60; i++) {
            if (mensaje.equalsIgnoreCase(String.valueOf(i))) {
                estado = false;
                vju.cambiarCartaMesa(i);
            }
        }
        if (estado) {
            estado = false;
            vju.mostrarMensaje("\n" + mensaje);
        }
    }
}
