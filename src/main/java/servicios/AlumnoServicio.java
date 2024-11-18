package servicios;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;

public class AlumnoServicio {
    private Map<String, Alumno> listaAlumnos = new HashMap<>();

    public void crearAlumno(Alumno alumno) {
        listaAlumnos.put(alumno.getRut(), alumno);
    }

    public void agregarMateria(String rutAlumno, Materia currentMate) {
        Alumno alumno = listaAlumnos.get(rutAlumno);
        if (alumno != null) {
            alumno.getMaterias().add(currentMate);
        }
    }

    public List<Materia> materiasPorAlumnos(String rutAlumno) {
        Alumno alumno = listaAlumnos.get(rutAlumno);
        if (alumno != null) {
            return alumno.getMaterias();
        }
        return new ArrayList<>();
    }

    public Map<String, Alumno> listarAlumnos() {
        return listaAlumnos;
    }


    public void eliminarAlumno(Alumno alumno) {
        listaAlumnos.remove(alumno.getRut());
    }

    public double calcularPromedio(String rutAlumno) {
        Alumno alumno = listaAlumnos.get(rutAlumno);
        if (alumno != null) {
            return new PromedioServiciosImp().calcularPromedio(alumno);
        }
        return 0.0;
    }
}
