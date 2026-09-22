import java.util.Scanner;

public class ejercicio2 {
    // Muestra mensaje en consola
    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra prompt para usuario
    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    // Muestra menú y pide opción a usuario
    static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

    // Comprueba que usuario introducio int
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
        Biblioteca sanDios = new Biblioteca();
        Libro libroAleatorio;
        final int NUMEROLIBROS = 50;
        boolean funcionar = true;
        int opcion = 0;
        final String MENU = "0. SALIR \n1. AGREGAR LIBRO \n2. PRESTAR LIBRO \n3. DEVOLVER LIBRO \n4. MOSTRAR LIBROS";

        for (int i = 0; i < NUMEROLIBROS; i++) {
            libroAleatorio = new Libro(FakerPR.titulo(), FakerPR.autorLibro(), FakerPR.booleano());
            sanDios.agregarLibro(libroAleatorio);
        }
        System.out.println(sanDios.mostrarLibros());

        while (funcionar) {
            opcion = mostrarMenuYLeerOpcion(sc, MENU);
            if (opcion == 0) {
                funcionar = false;
                System.out.println("Saliendo...");
            } else if (opcion == 1) {
                System.out.print("Dime título del libro: ");
                String tituloLibro = sc.nextLine();
                System.out.print("Dime el autor: ");
                String autorLibro = sc.nextLine();
                System.out.print("Sinopsis: ");
                String sinopsisLibro = sc.nextLine();
                System.out.print("Esta disponible (S/N): ");
                String disponibleLibro = sc.nextLine();
                boolean estadoLibro = true;
                if (disponibleLibro.equals("S")) {
                    estadoLibro = true;
                } else {
                    estadoLibro = false;
                }
                Libro nuevoLibro = new Libro(tituloLibro, autorLibro, sinopsisLibro, estadoLibro);
                sanDios.agregarLibro(nuevoLibro);
                System.out.println(nuevoLibro);
            } else if (opcion == 2) {
                System.out.print("Dime título del libro: ");
                String tituloLibro = sc.nextLine();
                Libro libroEncontrado = sanDios.prestarLibro(tituloLibro);
                if (libroEncontrado != null) {
                    System.out.println("Libro: " + tituloLibro + " ha sido prestado");
                } else {
                    System.out.println("No se ha encontrado el libro");
                }
            } else if (opcion == 3) {
                System.out.print("Dime título del libro: ");
                String tituloLibro = sc.nextLine();
                Libro libroEncontrado = sanDios.devolverLibro(tituloLibro);
                if (libroEncontrado != null) {
                    System.out.println("Libro: " + tituloLibro + " ha sido devuelto");
                } else {
                    System.out.println("No se ha encontrado el libro");
                }
                System.out.println();
            } else if (opcion == 4) {
                System.out.println(sanDios.mostrarLibros());
            } else {
                System.out.println("Opción no válida, selecciona una opción entre 0-4");
            }
        }
    }
}
