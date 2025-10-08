package view;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JButton btnOk = new JButton("Ingresar");
    private final JButton btnCancel = new JButton("Cancelar");

    public LoginDialog(Frame owner) {
        super(owner, "Inicio de Sesión", true);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(2,2,8,8));
        form.add(new JLabel("Usuario:"));
        form.add(txtUser);
        form.add(new JLabel("Contraseña:"));
        form.add(txtPass);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnCancel);
        actions.add(btnOk);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnOk);
        pack();
        setLocationRelativeTo(owner);
    }

    public JTextField getTxtUser() { return txtUser; }
    public JPasswordField getTxtPass() { return txtPass; }
    public JButton getBtnOk() { return btnOk; }
    public JButton getBtnCancel() { return btnCancel; }

    public String getUsername() { return txtUser.getText().trim(); }
    public String getPassword() { return new String(txtPass.getPassword()); }

    public void clear() {
        txtUser.setText(""); txtPass.setText("");
        txtUser.requestFocusInWindow();
    }
}

