package Controller;

import model.AlumnoDAO;
import model.UserDAO;
import view.AlumnoView;
import view.LoginDialog;

public class LoginController {
    private LoginDialog vista;
    private UserDAO dao;

    public LoginController(LoginDialog vista) {
        this.vista = vista;
        this.dao = new UserDAO();

        // Escucha del botón de login
        this.vista.getBtnLogin().addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String user = vista.getTxtUsuario().getText();
        String pass = new String(vista.getTxtPassword().getPassword());

        if (dao.validarUsuario(user, pass)) {
            vista.mostrarMensaje("Inicio de sesión exitoso ");
            // Aquí podrías abrir la ventana principal, por ejemplo:
            // new MenuPrincipalView().setVisible(true);
            AlumnoView view = new AlumnoView();

            // Crear el DAO (con conexión a la base de datos)
            AlumnoDAO dao = new AlumnoDAO();

            // Conectar el controlador con la vista y el DAO
            AlumnoController controller = new AlumnoController(dao, view);

            // Mostrar la ventana
            view.setVisible(true);
            // vista.dispose();
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos ");
        }
    }
}
