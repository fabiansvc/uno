package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LogueadosUNO implements Serializable {

    private final File archivo;
    private String nickname;

    /**
     * Constructor de la clase LogueadosUNO.
     */
    public LogueadosUNO() {
        nickname = "";
        archivo = new File("datos/logueados.uno");
    }

    /**
     * Guarda el dato de la variable nickname a un archivo serializable
     * logueados.uno.
     */
    public void guardarDatos() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            fos.close();
            oos.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error de archivo");
        } catch (IOException ex) {
            System.err.println("Error de I/O");
        }
    }

    /**
     * Muestra el dato de un archivo serializable logueados.uno.
     */
    public void mostrarDatos() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        LogueadosUNO rs = null;
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            rs = (LogueadosUNO) ois.readObject();
            System.out.println("¡Entro el jugador " + rs.nickname.toString() + " al juego uno!");
            fis.close();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error de archivo");
        } catch (IOException ex) {
            System.err.println("Error de I/O");
        } catch (ClassNotFoundException ex) {
            System.err.println("Clase no encontrada");
        }
    }

    /**
     * Asigna un nickname de tipo String a la variable nickname.
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
