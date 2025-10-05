package model;

import java.util.ArrayList;
import java.util.List;

public class AlumnoRepositorioArreglo {
    private final List<Alumno> data = new ArrayList<>();

    public List<Alumno> findAll() {
        return new ArrayList<>(data);
    }

    public Alumno findByClave(int NumeroControl) {
        for (Alumno p : data) {
            if (p.getNumeroControl() == (NumeroControl)) return p; // usa equals()
        }
        return null;
    }

    // Crea si no existe; si existe, lanza excepción
    public void create(Alumno p) {
        if (findByClave(p.getNumeroControl()) != null) {
            throw new IllegalArgumentException("La clave ya existe: " + p.getNumeroControl());
        }
        data.add(p);
    }

    // Actualiza si existe; si no existe, lanza excepción
    public void update(Alumno p) {
        Alumno existing = findByClave(p.getNumeroControl());
        if (existing == null) throw new IllegalArgumentException("No existe clave: " + p.getNumeroControl());
        existing.setNombre(p.getNombre());
        existing.setMateria(p.getMateria());
        existing.setCalificacion(p.getCalificacion());
        existing.setEspecialidad(p.getEspecialidad());
    }

    public void deleteByClave(int numeroControl) {
        data.removeIf(p -> p.getNumeroControl() == (numeroControl));
    }


}
