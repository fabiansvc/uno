package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RegistradosUNO implements Serializable {

    private final File archivo;
    private String nickname;
    /**
     * Constructor de la clase RegistradoUNO.
     */
    public RegistradosUNO() {
        nickname = "";
        archivo = new File("datos/registrados.uno");
    }
    /**
     * Guardar el nickname de un jugador en un archivo serializable llamado registrados.uno.
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
     * Muestra el nickname de un jugador de un archivo serializable llamado registrados.uno.
     */
    public void mostrarDatos() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        RegistradosUNO rs = null;
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            rs = (RegistradosUNO) ois.readObject();
            System.out.println("¡El jugador " + rs.nickname.toString() + " se registro al juego uno!");
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
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
