package servicios;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlumnoServicioTest {

    private AlumnoServicio alumnoServicio;
    private AlumnoServicio alumnoServicioMock;
    private Materia matematicas;
    private Materia lenguaje;
    private Alumno mapu;

    @BeforeEach
    public void setup() {
        alumnoServicio = new AlumnoServicio();
        alumnoServicioMock = Mockito.mock(AlumnoServicio.class);
        matematicas = new Materia(MateriaEnum.MATEMATICAS);
        lenguaje = new Materia(MateriaEnum.LENGUAJE);
        mapu = new Alumno("12345678-9", "Mariangel", "Leon", "Calle tarata 258", 20);
    }

    @Test
    public void crearAlumnoTest() {
        alumnoServicio.crearAlumno(mapu);
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertEquals(1, alumnos.size());
        assertEquals(mapu, alumnos.get("12345678-9"));
    }

    @Test
    public void agregarMateriaTest() {
        alumnoServicio.crearAlumno(mapu);
        alumnoServicio.agregarMateria("12345678-9", matematicas);
        List<Materia> materias = alumnoServicio.materiasPorAlumnos("12345678-9");
        assertEquals(1, materias.size());
        assertEquals(matematicas, materias.get(0));
    }

    @Test
    public void materiasPorAlumnosTest() {
        when(alumnoServicioMock.materiasPorAlumnos("12345678-9")).thenReturn(List.of(matematicas, lenguaje));
        List<Materia> materias = alumnoServicioMock.materiasPorAlumnos("12345678-9");
        assertEquals(2, materias.size());
        assertEquals(matematicas, materias.get(0));
        assertEquals(lenguaje, materias.get(1));
    }

    @Test
    public void listarAlumnosTest() {
        alumnoServicio.crearAlumno(mapu);
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertEquals(1, alumnos.size());
        assertEquals(mapu, alumnos.get("12345678-9"));
    }
}
