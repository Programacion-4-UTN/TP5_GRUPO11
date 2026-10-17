package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "sistema_clientes";
    private static final String USER = "root";
    private static final String PASS = "root"; // Ajustar según entorno local
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(HOST + DB_NAME + "?useSSL=false", USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL Conexion: " + e.getMessage());
        }
        return cn;
    }

    public static void cerrarConexion(Connection cn) {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexion: " + e.getMessage());
        }
    }
}