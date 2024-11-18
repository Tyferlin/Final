package servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelos.Alumno;
import modelos.Materia;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ArchivoServicio {
    private PromedioServiciosImp promediosServicioImp = new PromedioServiciosImp();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void exportarDatos(Map<String, Alumno> alumnos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Alumno alumno : alumnos.values()) {
                writer.write(alumno.getNombre() + " - " + promediosServicioImp.calcularPromedio(alumno));
                writer.newLine();
            }
        }
    }

    public void exportarDatosJson(Map<String, Alumno> alumnos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String json = objectMapper.writeValueAsString(alumnos);
            writer.write(json);
        }
    }

    public void exportarDatosCsv(Map<String, Alumno> alumnos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados CSV
            writer.write("RUT,Nombre,Apellido,Edad,Direccion,Materia,Notas\n");

            // Escribir datos de alumnos
            for (Alumno alumno : alumnos.values()) {
                for (Materia materia : alumno.getMaterias()) {
                    writer.write(alumno.getRut() + "," + alumno.getNombre() + "," + alumno.getApellido() + "," + alumno.getEdad() + "," + alumno.getDireccion() + "," + materia.getNombre() + ",");
                    for (Double nota : materia.getNotas()) {
                        writer.write(nota + " ");
                    }
                    writer.write("\n");
                }
            }
        }
    }
}
