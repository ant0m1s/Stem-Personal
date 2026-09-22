import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ejercicio2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> libros = new ArrayList<>(
                Arrays.asList("El quijote", "La Biblia", "Manuel de resistencia", "La criada", "Rueda del Tiempo"));
        ArrayList<Integer> cantidades = new ArrayList<>(Arrays.asList(5, 3, 1, 7, 2));

        int opcion = 0;
        String mensajeError = "Debes introducir un numero entre 0 y 4";

        System.out.print("Introduzca un número: ");

        do {
            try {
                System.out.print("\n1. Añadir un nuevo libro.\r\n" + //
                        "2. Actualizar cantidad.\r\n" + //
                        "3. Ver inventario.\r\n" + //
                        "4. Salir.\"" +
                        "Introduzca una opción: ");
                opcion = Integer.parseInt(teclado.nextLine());
                if (opcion == 1) {
                    System.out.print("Nombre del nuevo libro: ");
                    libros.add(teclado.nextLine());
                    System.out.print("Cantidad: ");
                    cantidades.add(Integer.parseInt(teclado.nextLine()));
                } else if (opcion == 2) {
                    System.out.print("¿Qué libro quieres actualizar?\n ");
                    for (int i = 0; i < libros.size(); i++){
                        System.out.print("Libro " + i + " : " + libros.get(i) + ".\n2");
                    }

                    String buscar = teclado.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i< libros.size(); i++){
                        if (libros.get(i).equalsIgnoreCase(buscar)) {
                            System.out.print("Nueva cantidad para '" + libros.get(i) + "': ");
                            int nuevaCant = Integer.parseInt(teclado.nextLine());
                            cantidades.set(i, nuevaCant);
                            System.out.print("Cantidad cambiada");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.print("Libro no encontrado");
                    }
                } else if (opcion == 3) {
                    System.out.println("\nINVENTARIO ACTUAL:");
                    for (int i = 0; i < libros.size(); i++) {
                        System.out.println("- " + libros.get(i) + ": " + cantidades.get(i) + " uds.\n");
                    }
                } else if (opcion == 4) {
                    System.out.println("Saliendo del programa... ¡Hasta luego!");
                }

            } catch (Exception e) {
                System.out.println(mensajeError);
            }

        } while (opcion != 4);
        teclado.close();

    }
}