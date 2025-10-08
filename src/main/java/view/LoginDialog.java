package view;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public LoginDialog(Frame owner) {
        super(owner, "Inicio de Sesión", true);
        setLayout(new BorderLayout(100,100));

        JPanel form = new JPanel(new GridLayout(2,2,8,8));
        form.add(new JLabel("Usuario:"));
        form.add(txtUser);
        form.add(new JLabel("Contraseña:"));
        form.add(txtPass);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnCancelar);
        actions.add(btnIngresar);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnIngresar);
        pack();
        setLocationRelativeTo(owner);
    }

    public JTextField getTxtUsuario() { return txtUser; }
    public JPasswordField getTxtPassword() { return txtPass; }
    public JButton getBtnLogin() { return btnIngresar; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}