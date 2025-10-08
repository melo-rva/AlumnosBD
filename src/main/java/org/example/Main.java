package org.example;

import Controller.AlumnoController;
import Controller.LoginController;
import model.AlumnoDAO;
import model.AppModel;
import model.AuthService;
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
            AuthService auth = new AuthService();

            // Crea el controlador principal
            AlumnoController controller = new AlumnoController(dao, view);

            // Crear el diálogo y el controlador de login
            LoginDialog loginDialog = new LoginDialog(view);
            LoginController loginController = new LoginController(model, auth, loginDialog, view);

            // Mostrar el login
            boolean ok = loginController.showLogin(); // modal

            if (!ok) {
                // Si el usuario cancela el login, cerramos la aplicación
                System.exit(0);
            }

            // Si el login fue correcto, mostramos la ventana principal
            view.setVisible(true);
        });
    }
}
