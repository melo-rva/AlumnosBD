package Controller;

import model.Alumno;
import model.AlumnoDAO;

import view.FormularioAgregarView;
import view.AlumnoTableModel;
import view.AlumnoView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;
import java.util.logging.Logger;

public class AlumnoController {
    private final AlumnoDAO dao;
    private final AlumnoView view;

    private static final Logger LOG = Logger.getLogger(AlumnoController.class.getName());



    public AlumnoController(AlumnoDAO dao, AlumnoView view) {
        this.dao = dao;
        this.view = view;

        // Inicializar tabla
        view.tableModel = new AlumnoTableModel(dao.listar());
        view.tabla.setModel(view.tableModel);

        // Centrar columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < view.tabla.getColumnCount(); i++) {
            view.tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        registrarEventos();
        refrescarTabla();
    }

    private void registrarEventos() {
        view.act_agregar.addActionListener(e -> abrirFormularioAgregar());
        view.act_eliminar.addActionListener(e -> eliminarSeleccionado());
        view.act_modificar.addActionListener(e -> modificarSeleccionado());
        view.btnLimpiar.addActionListener(e -> view.limpiarFormulario());
    }

    private void abrirFormularioAgregar() {
        FormularioAgregarView form = new FormularioAgregarView(view);
        new FormularioAgregarController(form); // Usa el controlador del formulario
        form.setVisible(true);
        refrescarTabla();
    }

    private void eliminarSeleccionado() {
        int fila = view.tabla.getSelectedRow();

        if (fila >= 0) {
            Alumno seleccionado = view.tableModel.getAt(fila);

            int opcion = JOptionPane.showConfirmDialog(view,
                    "¿Eliminar al alumno con nombre: " + seleccionado.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                boolean eliminado = dao.eliminar(seleccionado.getNumeroControl());
                if (eliminado) {
                    refrescarTabla();
                    JOptionPane.showMessageDialog(view, "Alumno eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(view, "Error al eliminar el alumno");
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Seleccione un alumno primero");
        }
    }

    private void modificarSeleccionado() {
        int fila = view.tabla.getSelectedRow();

        if (fila >= 0) {
            Alumno seleccionado = view.tableModel.getAt(fila);

            FormularioAgregarView form = new FormularioAgregarView(view);

            form.txtNumeroControl.setText(String.valueOf(seleccionado.getNumeroControl()));
            form.txtNumeroControl.setEditable(false);
            form.txtNombre.setText(seleccionado.getNombre());
            form.txtMateria.setText(seleccionado.getMateria());
            form.txtCalificacion.setText(String.valueOf(seleccionado.getCalificacion()));
            form.txtEspecialidad.setText(seleccionado.getEspecialidad());

            form.btnAgregar.setText("Guardar cambios");

            form.btnAgregar.addActionListener(ev -> {
                try {
                    String nombre = form.txtNombre.getText().trim();
                    String materia = form.txtMateria.getText().trim();
                    double calificacion = Double.parseDouble(form.txtCalificacion.getText().trim());
                    String especialidad = form.txtEspecialidad.getText().trim();

                    validar(seleccionado.getNumeroControl(), nombre, materia, calificacion);

                    Alumno alumnoActualizado = new Alumno(
                            seleccionado.getNumeroControl(),
                            nombre,
                            materia,
                            calificacion,
                            especialidad
                    );

                    boolean actualizado = dao.actualizar(alumnoActualizado);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(view, "Alumno modificado correctamente");
                        form.dispose();
                        refrescarTabla();
                    } else {
                        JOptionPane.showMessageDialog(view, "Error al modificar el alumno");
                    }

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(form, "Número inválido en los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            form.btnCancelar.addActionListener(ev -> form.dispose());
            form.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Seleccione un alumno primero");
        }
    }

    private void refrescarTabla() {
        List<Alumno> alumnos = dao.listar();
        view.tableModel.setData(alumnos);
    }

    private void validar(int numeroControl, String nombre, String materia, double calificacion) {
        if (numeroControl <= 0)
            throw new IllegalArgumentException("El número de control es requerido.");
        if (nombre.isBlank()) throw new IllegalArgumentException("El nombre es requerido.");
        if (materia.isBlank()) throw new IllegalArgumentException("La materia es requerida.");
        if (calificacion < 0) throw new IllegalArgumentException("La calificación no puede ser negativa.");
    }
}

