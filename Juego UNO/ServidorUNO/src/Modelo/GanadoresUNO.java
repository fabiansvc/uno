package Modelo;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class GanadoresUNO implements Serializable {

    private final File archivo;
    private String nickname;
    private String puntaje;
    /**
     * Constructor de la clase GanadoresUNO.
     */
    public GanadoresUNO() {
        archivo = new File("datos/ganadores.uno");
        nickname = "";
    }
    /**
     * guarda los datos nickname y puntaje de tipo String a un archivo serializable ganadores.uno.
     */
    public void guardarDatos() {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Archivo no encontrado");
        } catch (IOException e) {
            System.err.println("Error de entrada y salida");
        }
    }
    /**
     * muestra los datos nickname y puntaje de tipo String de un archivo serializable ganadores.uno.
     */
    public void mostrarDatos() {
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GanadoresUNO gu = (GanadoresUNO) ois.readObject();
            System.out.println("El ganador es " + gu.nickname.toString() + " con un puntaje de: " + gu.puntaje.toString());
        } catch (FileNotFoundException fnfe) {
            System.err.println("Archivo no encontrado");
        } catch (IOException e) {
            System.err.println("Error de entrada y salida");
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
    /**
     * Asigna un puntaje de tipo String a la variable puntaje.
     * @param puntaje
     */
    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

}
