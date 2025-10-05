package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/tap_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            //Intenta conectarse usando DriverManager
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conexion;
    }

}
