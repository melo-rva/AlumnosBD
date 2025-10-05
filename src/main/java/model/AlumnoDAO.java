package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    public List<Alumno> listar() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno"; //Consulta SQL para los registros

        try (Connection conn = BaseDatos.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("materia"),
                        rs.getDouble("calificacion"),
                        rs.getString("especialidad")
                );
                alumnos.add(a); //
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }
    //Metodo para insertar datos
    public boolean insertar(Alumno alumno) {
        String sql = "INSERT INTO alumnos(numero_control,nombre, materia, calificacion, especialidad) VALUES (?,?, ?, ?, ?)";

        try (Connection conn = BaseDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,alumno.getNumeroControl());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getMateria());
            ps.setDouble(4, alumno.getCalificacion());
            ps.setString(5, alumno.getEspecialidad());

            int filas = ps.executeUpdate(); // devuelve número de filas afectadas
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
