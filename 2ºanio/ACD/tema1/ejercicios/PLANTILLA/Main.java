import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Path archivo = Path.of("datos", "inscripciones.csv");
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            System.out.println("=== GESTOR DE INSCRIPCIONES ===");
            System.out.println("1. Listar inscripciones");
            System.out.println("2. Añadir inscripción");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar plazas");
            System.out.println("5. Eliminar inscripción");
            System.out.println("6. Restaurar copia de seguridad");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
                switch (opcion) {
                    case 1 -> {
                        List<Inscripcion> lista = GestorInscripciones.cargar(archivo);
                        if (lista.isEmpty()) {
                            System.out.println("No hay inscripciones.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(teclado.nextLine().trim());
                        System.out.print("Persona: ");
                        String persona = teclado.nextLine().trim();
                        System.out.print("Taller: ");
                        String taller = teclado.nextLine().trim();
                        System.out.print("Plazas: ");
                        int plazas = Integer.parseInt(teclado.nextLine().trim());
                        boolean anadido = GestorInscripciones.anadir(archivo,
                                new Inscripcion(id, persona, taller, plazas));
                        System.out.println(anadido ? "Inscripción añadida."
                                : "No se añadió: datos no válidos o ID repetido.");
                    }
                    case 3 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(teclado.nextLine().trim());
                        Inscripcion item = GestorInscripciones.buscar(GestorInscripciones.cargar(archivo), id);
                        System.out.println(item != null ? item : "ID no encontrado.");
                    }
                    case 4 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(teclado.nextLine().trim());
                        System.out.print("Nuevas plazas: ");
                        int plazas = Integer.parseInt(teclado.nextLine().trim());
                        boolean cambiado = GestorInscripciones.actualizarPlazas(archivo, id, plazas);
                        System.out.println(cambiado ? "Plazas actualizadas."
                                : "No se modificó nada: ID inexistente o plazas negativas.");
                    }
                    case 5 -> {
                        System.out.print("ID a eliminar: ");
                        int id = Integer.parseInt(teclado.nextLine().trim());
                        boolean eliminado = GestorInscripciones.eliminar(archivo, id);
                        System.out.println(eliminado ? "Inscripción eliminada." : "ID no encontrado.");
                    }
                    case 6 -> {
                        System.out.print("Se sustituirán los datos actuales por la copia. ¿Continuar? (S/N): ");
                        String respuesta = teclado.nextLine().trim();
                        if (respuesta.equalsIgnoreCase("S")) {
                            boolean restaurado = GestorInscripciones.restaurar(archivo);
                            System.out.println(restaurado ? "Copia restaurada." : "No hay copia que restaurar.");
                        } else {
                            System.out.println("Restauración cancelada.");
                        }
                    }
                    case 0 -> System.out.println("Hasta luego.");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número.");
            } catch (IOException e) {
                System.out.println("Error con el archivo: " + e.getMessage());
            }
        }
        teclado.close();
    }
}
