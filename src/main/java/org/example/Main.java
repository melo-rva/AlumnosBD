package org.example;

import Controller.AlumnoController;
import Controller.LoginController;
import model.AlumnoDAO;
import model.AppModel;
import view.AlumnoView;
import view.LoginDialog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Crea el modelo, la base de datos, el view del menú principal y el servicio del login
            AppModel model = new AppModel();
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoView view = new AlumnoView();


            // Crea el controlador principal
            AlumnoController controller = new AlumnoController(dao, view);

           //Login y su controlador
            LoginDialog vista = new LoginDialog(null);
            LoginController controlador = new LoginController(vista); // 🔹 Importante
            vista.setVisible(true);
        });
    }
}
