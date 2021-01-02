package Controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {

    private static String url = "colegio.db";
    private static Connection connect;

    public static void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cerrarConexion() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Conexion.url = url;
    }

    public static Connection getConnect() {
        return connect;
    }

    public static void setConnect(Connection connect) {
        Conexion.connect = connect;
    }
    
    

}

