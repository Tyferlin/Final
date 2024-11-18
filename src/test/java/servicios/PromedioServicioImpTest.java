package servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PromedioServicioImpTest {

    private PromedioServiciosImp promedioServicioImp;
    private Alumno alumno;

    @BeforeEach
    public void setUp() {
        promedioServicioImp = new PromedioServiciosImp();
        alumno = new Alumno("12345678-9", "Tyferlin", "Rojas", "Lo ovalle 330", 20);
        List<Materia> materias = new ArrayList<>();
        
        Materia matematicas = new Materia(MateriaEnum.MATEMATICAS);
        matematicas.addNota(8.5);
        materias.add(matematicas);
        
        Materia fisica = new Materia(MateriaEnum.FISICA);
        fisica.addNota(9.0);
        materias.add(fisica);
        
        alumno.setMaterias(materias);
    }

    @Test
    public void testCalcularPromedio() {
        double promedio = promedioServicioImp.calcularPromedio(alumno);
        assertEquals(8.75, promedio, 0.01);
    }
    
    @Test
    public void testCalcularPromedioSinMaterias() {
        Alumno alumnoSinMaterias = new Alumno("98765432-1", "Alex", "Smith", "Calle Falsa 123", 22);
        double promedio = promedioServicioImp.calcularPromedio(alumnoSinMaterias);
        assertEquals(0.0, promedio, 0.01);
    }
}
