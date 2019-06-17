package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PuntajeObtenido {

    private final File archivo;
    private String nickname;
    private String puntaje;
    /**
     * Constructor de la clase PuntajeObtenido.
     */
    public PuntajeObtenido() {
        archivo = new File("datos/puntajeObtenido.uno");
        nickname = "";
        puntaje = "";
    }
    /**
     * Guarda los datos nickname y puntaje de tipo String a un archivo plano llamado puntajeObtenido.uno.
     */
    public void guardarDatos() {
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(nickname + ": " + puntaje);
            salida.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado");
        }

    }
    /**
     * Muestra los datos de un archivo plano llamado puntajeObtenido.uno.
     */
    public void mostrarDatos() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String datos = lector.readLine();
            while (datos != null) {
                System.out.println(datos);
                datos = lector.readLine();
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.err.println("Error de I/O");
        }
    }
    /**
     * Asigna un nickname de tipo String a la variable nickname.
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**
     * Asigna un puntaje de tipo String a la variable puntaje.
     * @param puntaje
     */
    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

}
