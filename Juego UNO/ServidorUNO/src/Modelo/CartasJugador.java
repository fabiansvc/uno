package Modelo;

public class CartasJugador {

    private String nickname;
    private String valor;
    private String color;
    /**
     * Constructor de la clase CartaJugador.
     * @param nickname
     * @param valor
     * @param color 
     */
    public CartasJugador(String nickname, String valor, String color) {
        this.nickname = nickname;
        this.valor = valor;
        this.color = color;
    }
    /**
     * Retorna un nickname de tipo String.
     * @return 
     */
    public String getNickname() {
        return nickname;
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
    /**
     * Asigna un nickname a la variable nickname de tipo String.
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**
     * Asigna un valor a la variable valor de tipo String.
     * @param valor 
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Asigna un color a la variable color de tipo String.
     * @param color 
     */
    public void setColor(String color) {
        this.color = color;
    }

}
