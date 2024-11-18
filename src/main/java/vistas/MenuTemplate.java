package vistas;

import java.util.Scanner;

public abstract class MenuTemplate {
    public abstract void mostrarMenu();

    public void imprimirLinea(String linea) {
        System.out.println(linea);
    }

    public void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir nueva línea

            switch (opcion) {
                case 1:
                    crearAlumno(scanner);
                    break;
                case 2:
                    agregarMateria(scanner);
                    break;
                case 3:
                    exportarDatos();
                    break;
                case 4:
                    agregarNotaPasoUno(scanner);
                    break;
                case 5:
                    listarAlumnos();
                    break;
                case 0:
                    terminarPrograma();
                    salir = true;
                    break;
                default:
                    imprimirLinea("Opción no válida, intente nuevamente.");
            }
        }
        scanner.close();
    }

    // Métodos a implementar en las subclases
    public void exportarDatos() {
        // Implementación a definir en la subclase
    }

    public void crearAlumno(Scanner scanner) {
        // Implementación a definir en la subclase
    }

    public void agregarMateria(Scanner scanner) {
        // Implementación a definir en la subclase
    }

    public void agregarNotaPasoUno(Scanner scanner) {
        // Implementación a definir en la subclase
    }

    public void listarAlumnos() {
        // Implementación a definir en la subclase
    }

    public void terminarPrograma() {
        // Implementación a definir en la subclase
        imprimirLinea("Saliendo del programa...");
    }
}
