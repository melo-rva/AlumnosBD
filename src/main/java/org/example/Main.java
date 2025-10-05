package org.example;

import Controller.AlumnoController;
import Controller.FormularioController;
import model.AlumnoRepositorioArreglo;
import view.AlumnoView;
import view.FormularioAgregar;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ventana principal
            JFrame frame = new JFrame("Registro de alumnos");
            frame.setSize(300,200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            FormularioAgregar formulario = new FormularioAgregar(frame);
            new FormularioController(formulario);
            formulario.setVisible(true);
        });



    }
}