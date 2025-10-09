package org.example;

import Controller.AlumnoController;
import Controller.LoginController;

import view.LoginDialog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Login y su controlador
            LoginDialog vista = new LoginDialog(null);
            LoginController controlador = new LoginController(vista); // 🔹 Importante
            vista.setVisible(true);
        });
    }
}
