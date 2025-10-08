package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    // Obtener todos los alumnos si quieres
    public List<Alumno> listar() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";



        try (Connection conn = BaseDatos.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("numero_control"),
                        rs.getString("nombre"),
                        rs.getString("materia"),
                        rs.getDouble("calificacion"),
                        rs.getString("especialidad")
                );
                alumnos.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    // Insertar nuevo alumno
    public boolean insertar(Alumno alumno) {
        String sql = "INSERT INTO alumnos (numero_control, nombre, materia, calificacion, especialidad) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = BaseDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alumno.getNumeroControl());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getMateria());
            ps.setDouble(4, alumno.getCalificacion());
            ps.setString(5, alumno.getEspecialidad());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Actualizar alumno existente
    public boolean actualizar(Alumno alumno) {
        String sql = "UPDATE alumnos SET nombre = ?, materia = ?, calificacion = ?, especialidad = ? WHERE numero_control = ?";

        try (Connection conn = BaseDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getMateria());
            ps.setDouble(3, alumno.getCalificacion());
            ps.setString(4, alumno.getEspecialidad());
            ps.setInt(5, alumno.getNumeroControl());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Eliminar alumno por número de control
    public boolean eliminar(int numeroControl) {
        String sql = "DELETE FROM alumnos WHERE numero_control = ?";

        try (Connection conn = BaseDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numeroControl);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
