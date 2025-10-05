package org.example;

import Controller.AlumnoController;
import model.AlumnoDAO;
import view.AlumnoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear la vista principal
            AlumnoView view = new AlumnoView();

            // Crear el DAO (con conexión a la base de datos)
            AlumnoDAO dao = new AlumnoDAO();

            // Conectar el controlador con la vista y el DAO
            AlumnoController controller = new AlumnoController(dao, view);

            // Mostrar la ventana
            view.setVisible(true);
        });
    }
}