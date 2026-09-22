import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class instituto {
    //Muestra mensaje en consola
    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    //Muestra prompt para usuario
    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }
    //Muestra menú y pide opción a usuario
    static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }
    //Comprueba que usuario introducio int
    static int leerEntero(Scanner sc) {
        int numero = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                numeroValido = true;
            } catch (NumberFormatException e) {
                imprimirPrompt("Debes introducir un número entero: ");
            }
        }
        return numero;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arrayString1 = new ArrayList<>();
        ArrayList<String> arrayString2 = new ArrayList<>();
        ArrayList<Integer> arrayInteger1 = new ArrayList<>();
        ArrayList<Integer> arrayInteger2 = new ArrayList<>();

        String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir elemento\n"
                + "2. Listar elementos\n"
                + "3. Mostrar especificación\n"
                + "4. Eliminar revisión\n";
        int eleccion = 0;
        boolean funcionar = true;

        while (funcionar) {
            eleccion = mostrarMenuYLeerOpcion(sc, menu);
            if (eleccion == 0) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {
                
            } else if (eleccion == 2) {
                
            } else if (eleccion == 3) {
                
            } else if (eleccion == 4) {
                
            } else {
                imprimirMensaje("Opción no válida");
            }
        }
    }
}