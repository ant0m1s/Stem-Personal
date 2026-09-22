import java.util.Scanner;

public class Main {
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
        imprimirPrompt("Seleccione una opción: ");
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

    static int leerEnteroMayorQueCero(Scanner sc, String prompt) {
        int numero = 0;

        while (numero <= 0) {
            imprimirPrompt(prompt);
            numero = leerEntero(sc);
            if (numero <= 0) {
                imprimirMensaje("Debe ser mayor que 0.");
            }
        }
        return numero;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Zoologico malageta = new Zoologico();
        Animal animalRandom;
        final int NUMEROANIMALES = 100;
        boolean funcionar = true;
        int opcion = 0;

        final String MENU = "===== GESTIÓN DE ZOOLÓGICO =====\r\n" + //
                "1. Mostrar todos los animales\r\n" + //
                "2. Buscar un animal por código\r\n" + //
                "3. Agregar ejemplares a un animal\r\n" + //
                "4. Retirar ejemplares de un animal\r\n" + //
                "5. Eliminar un animal\r\n" + //
                "6. Salir\r\n";

        for (int i = 0; i < NUMEROANIMALES; i++) {
            animalRandom = new Animal(FakerPR.nombreAnimal(), FakerPR.codigoAnimal(), FakerPR.cantidadAnimal(),
                    FakerPR.precioAnimal());
            malageta.agregarAnimal(animalRandom);
        }

        while (funcionar) {
            opcion = mostrarMenuYLeerOpcion(sc, MENU);
            if (opcion == 6) {
                funcionar = false;
                System.out.println("Saliendo...");
            } else if (opcion == 1) {
                System.out.println(malageta.mostrarAnimales());
            } else if (opcion == 2) {
                imprimirPrompt("Busca un animal por su codigo (Ejemplo A1B2C3): ");
                String codigoBuscar = sc.nextLine();
                Animal animalEncontrado = malageta.buscarPorNombre(codigoBuscar);
                if (animalEncontrado != null) {
                    System.out.println(animalEncontrado.toString());
                } else {
                    imprimirMensaje("Animal no encontrado.");
                }
            } else if (opcion == 3) {
                imprimirPrompt("Busca un animal por su codigo (Ejemplo A1B2C3): ");
                String codigoBuscar = sc.nextLine();
                Animal animalEncontrado = malageta.buscarPorNombre(codigoBuscar);
                if (animalEncontrado != null) {
                    animalEncontrado.getCantidad();
                    int ejemplaresSumar = leerEnteroMayorQueCero(sc, "Dime cuantos ejemplares quieres sumar: ");
                    int cantidadActual = animalEncontrado.getCantidad();
                    animalEncontrado.setCantidad(cantidadActual + ejemplaresSumar);
                    System.out.println("Hemos agregado correctamente " + ejemplaresSumar + " ejemplares");
                } else {
                    imprimirMensaje("Animal no encontrado.");
                }
            } else if (opcion == 4) {
                imprimirPrompt("Busca un animal por su codigo (Ejemplo A1B2C3): ");
                String codigoBuscar = sc.nextLine();
                Animal animalEncontrado = malageta.buscarPorNombre(codigoBuscar);
                if (animalEncontrado != null) {
                    animalEncontrado.getCantidad();
                    int ejemplaresRestar = leerEnteroMayorQueCero(sc, "Dime cuantos ejemplares quieres eliminar: ");
                    if (ejemplaresRestar <= animalEncontrado.getCantidad()) {
                        int cantidadActual = animalEncontrado.getCantidad();
                        animalEncontrado.setCantidad(cantidadActual - ejemplaresRestar);
                        System.out.println("Hemos eliminado correctamente " + ejemplaresRestar + " ejemplares");
                    } else {
                        System.out.println("No podemos eliminar " + ejemplaresRestar + " porque solo tenemos " + animalEncontrado.getCantidad());
                    }
                } else {
                    imprimirMensaje("Animal no encontrado.");
                }
            } else if (opcion == 5) {
                imprimirPrompt("Busca un animal por su codigo (Ejemplo A1B2C3): ");
                String codigoBuscar = sc.nextLine();
                Animal animalEncontrado = malageta.buscarPorNombre(codigoBuscar);
                if (animalEncontrado != null) {
                    if (animalEncontrado.getCantidad() == 0) {
                        Animal eliminado = malageta.eliminarAnimal(codigoBuscar);
                        imprimirMensaje("Animal eliminado: " + eliminado);
                    } else {
                        imprimirMensaje("No puedes eliminar un animal si tiene 1 o mas ejemplares. " + animalEncontrado.getCantidad() + " ejemplares actuales.");
                    }
                } else {
                    imprimirMensaje("Animal no encontrado.");
                }
            }
        }

    }
}
