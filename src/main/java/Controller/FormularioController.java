package Controller;

import model.Alumno;
import model.AlumnoDAO;
import view.FormularioAgregar;

import javax.swing.*;

public class FormularioController {
    private AlumnoDAO dao = new AlumnoDAO();
    private FormularioAgregar view;

    public FormularioController(FormularioAgregar view) {
        this.view = view;

        // Acción del botón Agregar
        view.btnAgregar.addActionListener(e -> agregarAlumno());
        // Acción del botón Cancelar
        view.btnCancelar.addActionListener(e -> view.dispose());
    }

    private void agregarAlumno() {
        try {
            int numero_control = Integer.parseInt(view.txtNumeroControl.getText());
            String nombre = view.txtNombre.getText();
            String materia = view.txtMateria.getText();
            double calificacion = Double.parseDouble(view.txtCalificacion.getText());
            String especialidad = view.txtEspecialidad.getText();

            Alumno alumno = new Alumno(numero_control,nombre, materia, calificacion, especialidad);
            boolean exito = dao.insertar(alumno);

            if (exito) {
                JOptionPane.showMessageDialog(view, "Alumno agregado correctamente");
                view.dispose(); // cierra el formulario
            } else {
                JOptionPane.showMessageDialog(view, "Error al agregar alumno");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Calificación debe ser un número válido");
        }
    }
}
