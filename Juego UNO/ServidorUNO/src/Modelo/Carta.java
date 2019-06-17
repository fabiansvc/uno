package Modelo;

import javax.swing.Icon;

public class Carta {

    private Icon carta;
    private final String valor;
    private final String color;
    /**
     * Constructor de la clase Carta
     * @param carta
     * @param valor
     * @param color 
     */
    public Carta(Icon carta, String valor, String color) {
        this.carta = carta;
        this.valor = valor;
        this.color = color;

    }
    /**
     * Retorna una carta de tipo Icon.
     * @return 
     */
    public Icon getCarta() {
        return carta;
    }
    /**
     * Retorna un valor de la carta de tipo String.
     * @return 
     */
    public String getValor() {
        return valor;
    }
    /**
     * Retorna un color de la carta de tipo String.
     * @return 
     */
    public String getColor() {
        return color;
    }
}
