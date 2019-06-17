package Modelo;

import vista.datos.VentanaDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionBD {

    private VentanaDatos vd;
    private static Connection db;
    private boolean connected;

    /**
     * Constructor de la clase ConexionBD
     */
    public ConexionBD() {
        db = null;
        connected = false;

        boolean error_loading_driver = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            error_loading_driver = true;
            System.err.println("No se ha podido cargar el driver JDBC para MySql.");
        }
        if (!error_loading_driver) {
            try {
                System.out.println("Conectando a la base de datos!");
                db = DriverManager.getConnection("jdbc:mysql://localhost/usuarios_uno", "root", "");
                connected = true;
            } catch (SQLException se) {
                System.err.println("No se ha podido conectar a la base de datos.");
            }
        }
    }

    /**
     * Consulta en la base de datos MySql los datos de usuario y password para
     * inciar sesion, retornando un Boolean
     *
     * @param usuario
     * @param password
     * @return
     */
    public Boolean iniciarSesion(String usuario, String password) {

        String resultado[] = new String[5];
        ResultSet rs = null;
        boolean consultar = false;
        boolean estado = false;

        if (connected) {
            try {
                Statement stmt = db.createStatement();
                String consultaNickname = "SELECT nickname FROM datos_usuarios_uno WHERE nickname = '" + usuario + "'";
                rs = stmt.executeQuery(consultaNickname);
                if (rs.next()) {
                    consultar = true;
                    estado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                if (consultar) {
                    String consulta = "SELECT nickname, password, edad, sexo, puntaje FROM datos_usuarios_uno WHERE nickname = '" + usuario + "'" + " AND password = '" + password + "'";
                    rs = stmt.executeQuery(consulta);
                    if (rs.next()) {
                        estado = true;
                        for (int i = 0; i < 5; i++) {
                            resultado[i] = rs.getString(i + 1);
                            if (i == 5) {
                                resultado[i] = String.valueOf(rs.getInt(i + 1));
                            }

                        }
                        mostrarDatos(resultado);
                        guardarLogueado(resultado);
                    } else {
                        estado = false;
                        JOptionPane.showMessageDialog(null, "la contraseña es incorrecta", "Error en inicio de sesion", JOptionPane.ERROR_MESSAGE);
                    }
                }
                rs.close();
                db.close();
                System.out.println("Base de datos cerrada");
            } catch (SQLException se) {
                System.err.println("No se pudo consultar en la base de datos");
            }
        }
        return estado;

    }

    /**
     * Registra en la base de datos MySql los datos nickname, contrasena, edad,
     * sexo, puntaje.
     *
     * @param nickname
     * @param contrasena
     * @param edad
     * @param sexo
     * @param puntaje
     */
    public void registrar(String nickname, String contrasena, String edad, String sexo, int puntaje) {

        PreparedStatement pstmt = null;
        if (connected) {
            try {
                pstmt = db.prepareStatement("INSERT INTO datos_usuarios_uno (nickname, password, edad, sexo, puntaje) VALUES(?,?,?,?,?)");
                pstmt.setString(1, nickname);
                pstmt.setString(2, contrasena);
                pstmt.setString(3, edad);
                pstmt.setString(4, sexo);
                pstmt.setInt(5, puntaje);
                int n = pstmt.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "El usuario se ha registrado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    guardarRegistrado(nickname);
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no se ha registrado satisfactoriamente", "Error", JOptionPane.ERROR_MESSAGE);
                }
                pstmt.close();
                db.close();
                System.out.println("Base de datos cerrada");
            } catch (SQLException ex) {
                System.out.println("No se ha podido cerrar la base.");
            }
        }
    }

    /**
     * Cambia la contrasena de un usuario en la base de datos MySql, comprobando
     * si la contrasena antigua corresponde al usuario a consultar.
     *
     * @param nickname
     * @param passwordAntigua
     * @param passwordNueva
     */
    public void cambiarPassword(String nickname, String passwordAntigua, String passwordNueva) {
        boolean cambiar = false;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        if (connected) {
            try {
                Statement stmt = db.createStatement();
                String consulta = "SELECT nickname FROM datos_usuarios_uno WHERE nickname = '" + nickname + "'" + " AND password = '" + passwordAntigua + "'";
                rs = stmt.executeQuery(consulta);
                if (rs.next()) {
                    cambiar = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Información", JOptionPane.ERROR_MESSAGE);
                }
                if (cambiar) {
                    try {
                        pstmt = db.prepareStatement("UPDATE datos_usuarios_uno SET password = '" + passwordNueva + "' WHERE nickname = '" + nickname + "'");
                        int n = pstmt.executeUpdate();
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "la contraseña se ha cambiado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "la contraseña no se ha cambiado satisfactoriamente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        pstmt.close();
                        db.close();
                        System.out.println("Base de datos cerrada");
                    } catch (SQLException ex) {
                        System.out.println("No se ha podido cerrar la base.");
                    }
                }
            } catch (SQLException se) {
                System.err.println("No se pudo consultar en la base de datos");
            }
        }
    }

    /**
     * Cambia el puntaje de un usuario en la base de datos MySql.
     *
     * @param nickname
     * @param puntaje
     */
    public void cambiarPuntaje(String nickname, int puntaje) {
        boolean cambiar = false;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int resultado = 0;
        int puntajeNuevo = puntaje;
        if (connected) {
            try {
                Statement stmt = db.createStatement();
                String consulta = "SELECT puntaje FROM datos_usuarios_uno WHERE nickname = '" + nickname + "'";
                rs = stmt.executeQuery(consulta);
                if (rs.next()) {
                    resultado = rs.getInt(1);
                    puntajeNuevo += resultado;
                    cambiar = true;
                }
                if (cambiar) {
                    try {
                        pstmt = db.prepareStatement("UPDATE datos_usuarios_uno SET puntaje = '" + puntajeNuevo + "' WHERE nickname = '" + nickname + "'");
                        int n = pstmt.executeUpdate();
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "Puntaje guardado", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Puntaje no guardado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        pstmt.close();
                        db.close();
                        System.out.println("Base de datos cerrada");
                    } catch (SQLException ex) {
                        System.out.println("No se ha podido cerrar la base.");
                    }
                }
            } catch (SQLException se) {
                System.err.println("No se pudo consultar en la base de datos");
            }
        }
    }

    /**
     * Consulta en la base de datos MySql los datos de un usuario, retornando un
     * Boolean del paquete visual.VentanaDatos
     *
     * @param usuario
     * @return
     */
    public Boolean mostrarPerfilJugador(String usuario) {

        String resultado[] = new String[5];
        ResultSet rs = null;
        boolean consultar = false;
        boolean estado = false;

        if (connected) {
            try {
                Statement stmt = db.createStatement();
                String consultaNickname = "SELECT nickname FROM datos_usuarios_uno WHERE nickname = '" + usuario + "'";
                rs = stmt.executeQuery(consultaNickname);
                if (rs.next()) {
                    consultar = true;
                    estado = true;
                }
                if (consultar) {
                    String consulta = "SELECT nickname, password, edad, sexo, puntaje FROM datos_usuarios_uno WHERE nickname = '" + usuario + "'";
                    rs = stmt.executeQuery(consulta);
                    if (rs.next()) {
                        estado = true;
                        for (int i = 0; i < 5; i++) {
                            resultado[i] = rs.getString(i + 1);
                            if (i == 5) {
                                resultado[i] = String.valueOf(rs.getInt(i + 1));
                            }

                        }
                        mostrarDatos(resultado);
                    }
                }
                rs.close();
                db.close();
                System.out.println("Base de datos cerrada");
            } catch (SQLException se) {
                System.err.println("No se pudo consultar en la base de datos");
            }
        }
        return estado;

    }

    /**
     * Muestra los datos obtenidos de la base de datos MySql de un jugador en la
     * clase VentanaDatos
     *
     * @param datos
     */
    public void mostrarDatos(String datos[]) {
        vd = new VentanaDatos();
        vd.getPanelDatos().setTextoUsuario(datos[0]);
        vd.getPanelDatos().setTextoEdad(datos[2]);
        vd.getPanelDatos().setTextoSexo(datos[3]);
        vd.getPanelDatos().setTextoPuntaje(datos[4]);
        //Enviar nickname a los dos paneles
        vd.getPanelEditar().setNickname(datos[0]);
        vd.getPanelJugar().setNickname(datos[0]);
    }

    /**
     * Instancia a la clase LogueadosUNO del paquete Modelo enviando un nickname
     * de tipo String, y llamando los metodos guardasDatos y mostrarDatos de la
     * clase LogueadosUNO.
     *
     * @param datos
     */
    public void guardarLogueado(String datos[]) {
        LogueadosUNO lu = new LogueadosUNO();
        lu.setNickname(datos[0]);
        lu.guardarDatos();
        lu.mostrarDatos();
    }

    /**
     * Instancia a la clase RegistradosUNO del paquete Modelo enviando un
     * nickname de tipo String, y llamando los metodos guardasDatos y
     * mostrarDatos de la clase RegistradosUNO.
     *
     * @param nickname
     */
    public void guardarRegistrado(String nickname) {
        RegistradosUNO ru = new RegistradosUNO();
        ru.setNickname(nickname);
        ru.guardarDatos();
        ru.mostrarDatos();
    }
}
