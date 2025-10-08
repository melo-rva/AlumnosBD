package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean validarUsuario(String user, String pass) {
        boolean valido = false;

        String sql = "SELECT * FROM users WHERE user = ? AND password = ?";

        try (Connection conn = BaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                valido = true; // Encontró un registro válido
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valido;
    }
}
