package Modelo;

import vista.playUNO.VentanaJuegoUno;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Servidor {
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ServerSocket servidor;
    private Socket conexion;
    VentanaJuegoUno vju;
    /**
     * Constructor de la clase Servidor
     * @param vju 
     */
    public Servidor(VentanaJuegoUno vju) {
        this.vju = vju;
    }
    /**
     * Ejecuta el servidor con submetodos que conectan el servidor al cliente.
     */
    public void ejecutarServidor() {
        try {
            servidor = new ServerSocket(12345, 4);
            while (true) {
                esperarConexion();
                obtenerFlujos();
                procesarConexion();
                cerrarConexion();
            }
        } 
        catch (IOException excepcionES) {
            excepcionES.printStackTrace();
        }
    } 
    /**
     * Espera la conexion de clientes.
     */
    public void esperarConexion()  {
        try {
            conexion = servidor.accept();
        } catch (IOException ex) {
            System.err.println("no se establecio conexión con el cliente");
           
        }
    }
    /**
     * Se establecen los flujos de entrada y salida.
     */
    public void obtenerFlujos() {
        try {
            salida = new ObjectOutputStream(conexion.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(conexion.getInputStream());
        } catch (IOException ex) {
            System.err.println("No se establecio flujos de entradas y salidas.");
        }
        
    }
    /**
     * Se procesa la conexion donde se leen los datos enviados del cliente.
     */
    public void procesarConexion()  {
        String mensaje = "¡Conexión exitosa!";
        enviarDatos(mensaje);
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
     * Se cierra los flujos de entrada, salida y el socket conexion del servidor.
     */
    public void cerrarConexion() {
        try {
            salida.close();
            entrada.close();
            conexion.close();
            vju.mostrarMensaje("\n¡Conexión cerrada!");
        } catch (IOException excepcionES) {
            System.err.println("No se cerro la conexión");
        }
    }
    /**
     * Se envia datos como flujo de dato salida.
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
     * Se muestra el mensaje en la pantalla del chat, 
     * asi mismo controlando el cliente servidor de los movimientos del juego.
     * @param mensaje 
     */
    public void mostrarMensaje(String mensaje) {
        boolean estado = true;
        if (mensaje.equals("masDos")) {
            estado = false;
        } else if ((mensaje.equalsIgnoreCase("masCuatro"))) {
            estado = false;
        } else if (mensaje.equalsIgnoreCase("masDos1")) {
            estado = false;
            vju.dar2Cartas();
        } else if (mensaje.equalsIgnoreCase("masCuatro1")) {
            estado = false;
            vju.dar4Cartas();
        } else if (mensaje.equalsIgnoreCase("turnoServidor")) {
            estado = false;
            vju.setTurno();
        } else if (mensaje.equalsIgnoreCase("turnoCliente")) {
            estado = false;
        }else if (mensaje.equalsIgnoreCase("unoCliente")) {
            estado = false;
        }else if (mensaje.equalsIgnoreCase("unoServidor")) {
            estado = false;
            vju.seCantoUNO();
        }else if (mensaje.equalsIgnoreCase("perdioServidor")) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Game over");
            vju.puntajeObtenido("50");
            vju.guardarCartasTomadas();
            vju.guardarPuntaje(50);
        }else if (mensaje.equalsIgnoreCase("perdioCliente")) {
           estado = false;
        } else if (mensaje.equalsIgnoreCase("trueServidor")) {
            estado = false;
            vju.cambiarEstadoMesa(true);
        } else if (mensaje.equalsIgnoreCase("trueCliente")) {
            estado = false;
        }
        
        for (int i = 0; i < 50; i++) {
            if (mensaje.equalsIgnoreCase("player1 " + i)) {
                estado = false;     
                break;
            }else if (mensaje.equalsIgnoreCase("player2 " + i)) {
                estado = false;
                vju.cuantasCartas(i); 
                break;
            }
        }
        for (int i = 0; i < 60; i++) {
            if (mensaje.equalsIgnoreCase(String.valueOf(i))) {
                estado = false;
                vju.cambiarCartaMesa(i);
                break;
            }
        }
        if (estado) {
            estado = false;
            vju.mostrarMensaje("\n" + mensaje);
        }
    }
}
