package servicios;

import modelos.Alumno;
import modelos.Materia;

public class PromedioServiciosImp {
    public double calcularPromedio(Alumno alumno) {
        double sumaNotas = 0.0;
        int totalNotas = 0;

        for (Materia materia : alumno.getMaterias()) {
            for (double nota : materia.getNotas()) {
                sumaNotas += nota;
                totalNotas++;
            }
        }

        if (totalNotas == 0) return 0.0;
        return sumaNotas / totalNotas;
    }
}
