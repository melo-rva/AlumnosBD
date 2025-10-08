package Controller;

import model.AppModel;
import model.AuthService;
import model.User;
import view.LoginDialog;
import view.AlumnoView;

import javax.swing.*;
import java.util.Optional;
import java.util.logging.Logger;

public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private final AppModel model;
    private final AuthService auth;
    private final LoginDialog dialog;
    private final AlumnoView mainView;

    public LoginController(AppModel model, AuthService auth, LoginDialog dialog, AlumnoView mainView) {
        this.model = model;
        this.auth = auth;
        this.dialog = dialog;
        this.mainView = mainView;
        wireEvents();
    }

    private void wireEvents() {
        dialog.getBtnOk().addActionListener(e -> onLogin());
        dialog.getBtnCancel().addActionListener(e -> onCancel());
    }

    public boolean showLogin() {

        dialog.clear();
        dialog.setVisible(true);
        return model.isLoggedIn();
    }

    private void onLogin() {
        String u = dialog.getUsername();
        String p = dialog.getPassword();
        Optional<User> user = auth.login(u, p);
        if (user.isPresent()) {
            model.setUsuarioActual(user.get().getUsername());
            model.setRolActual(user.get().getRole());
            LOG.info("Login OK: " + model.getUsuarioActual() + " (" + model.getRolActual() + ")");
            dialog.setVisible(false);

        } else {
            LOG.warning("Login FALLÓ para usuario: " + u);
            JOptionPane.showMessageDialog(dialog, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        LOG.info("Login cancelado");
        dialog.setVisible(false);
        // Opcional: cerrar app si se cancela
        int opt = JOptionPane.showConfirmDialog(mainView, "¿Deseas salir?", "Cancelar login", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) System.exit(0);
    }
}
