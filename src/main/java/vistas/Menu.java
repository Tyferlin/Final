package vistas;

import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import servicios.ArchivoServicio;

public class Menu extends MenuTemplate {
    private AlumnoServicio alumnoServicio = new AlumnoServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();

    @Override
    public void mostrarMenu() {
        imprimirLinea("1. Crear Alumno");
        imprimirLinea("2. Agregar Materia");
        imprimirLinea("3. Listar Alumnos");
        imprimirLinea("4. Exportar Datos a JSON");
        imprimirLinea("5. Exportar Datos a CSV");
        imprimirLinea("0. Salir");
    }

    @Override
    public void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    crearAlumno(scanner);
                    break;
                case 2:
                    agregarMateria(scanner);
                    break;
                case 3:
                    listarAlumnos();
                    break;
                case 4:
                    exportarDatosJson();
                    break;
                case 5:
                    exportarDatosCsv();
                    break;
                case 0:
                    salir = true;
                    imprimirLinea("Saliendo del menú...");
                    break;
                default:
                    imprimirLinea("Opción no válida, intente nuevamente.");
            }
        }
        scanner.close();
    }


    public void crearAlumno(Scanner scanner) {
        System.out.print("Ingrese el RUT del alumno: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del alumno: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la dirección del alumno: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese la edad del alumno: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); 
        Alumno alumno = new Alumno(rut, nombre, apellido, direccion, edad);
        alumnoServicio.crearAlumno(alumno);
        imprimirLinea("Alumno creado correctamente.");
    }

    public void agregarMateria(Scanner scanner) {
        System.out.print("Ingrese el RUT del alumno: ");
        String rutAlumno = scanner.nextLine();
        System.out.print("Ingrese el nombre de la materia (MATEMATICAS, FISICA, QUIMICA, LENGUAJE, HISTORIA): ");
        String nombreMateriaStr = scanner.nextLine();
        MateriaEnum nombreMateria = MateriaEnum.valueOf(nombreMateriaStr.toUpperCase());
        Materia materia = new Materia(nombreMateria);
        System.out.print("Ingrese la nota de la materia: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); 
        materia.addNota(nota);
        alumnoServicio.agregarMateria(rutAlumno, materia);
        imprimirLinea("Materia agregada correctamente.");
    }

    public void listarAlumnos() {
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        if (alumnos.isEmpty()) {
            imprimirLinea("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : alumnos.values()) {
                imprimirLinea("RUT: " + alumno.getRut() + ", Nombre: " + alumno.getNombre() + ", Apellido: " + alumno.getApellido() + ", Dirección: " + alumno.getDireccion() + ", Edad: " + alumno.getEdad());
            }
        }
    }

    public void exportarDatosJson() {
        try {
            archivoServicio.exportarDatosJson(alumnoServicio.listarAlumnos(), "datos.json");
            imprimirLinea("Datos exportados a JSON correctamente.");
        } catch (IOException e) {
            imprimirLinea("Error al exportar datos a JSON: " + e.getMessage());
        }
    }

    public void exportarDatosCsv() {
        try {
            archivoServicio.exportarDatosCsv(alumnoServicio.listarAlumnos(), "datos.csv");
            imprimirLinea("Datos exportados a CSV correctamente.");
        } catch (IOException e) {
            imprimirLinea("Error al exportar datos a CSV: " + e.getMessage());
        }
    }
}
