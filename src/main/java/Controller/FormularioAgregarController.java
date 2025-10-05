package Controller;

import model.Alumno;
import model.AlumnoDAO;
import view.AlumnoView;
import view.FormularioAgregarView;


import javax.swing.*;
import java.util.List;

public class FormularioAgregarController {
    private AlumnoDAO dao = new AlumnoDAO();
    private FormularioAgregarView formView;
    private AlumnoView mainView;


    public FormularioAgregarController(FormularioAgregarView view) {
        this.formView = view;

        // Acción del botón Agregar
        view.btnAgregar.addActionListener(e -> agregarAlumno());
        // Acción del botón Cancelar
        view.btnCancelar.addActionListener(e -> view.dispose());

    }

    private void refrescarTabla() {
        List<Alumno> alumnos = dao.listar();
    }


    private void agregarAlumno() {
        try {
            int numero_control = Integer.parseInt(formView.txtNumeroControl.getText());
            String nombre = formView.txtNombre.getText();
            String materia = formView.txtMateria.getText();
            double calificacion = Double.parseDouble(formView.txtCalificacion.getText());
            String especialidad = formView.txtEspecialidad.getText();

            Alumno alumno = new Alumno(numero_control,nombre, materia, calificacion, especialidad);
            boolean exito = dao.insertar(alumno);

            if (exito) {
                JOptionPane.showMessageDialog(formView, "Alumno agregado correctamente");
                formView.dispose(); // cierra el formulario
                refrescarTabla();

            } else {
                JOptionPane.showMessageDialog(formView, "Error al agregar alumno");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(formView, "Calificación debe ser un número válido");
        }
    }
}


