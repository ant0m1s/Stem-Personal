import java.util.ArrayList;
import java.util.Scanner;

public class subastaTractores {
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

    static void anadirModelo(Scanner sc,
            ArrayList<String> modelo,
            ArrayList<Integer> precio_Inicial,
            ArrayList<Integer> mejor_puja,
            ArrayList<String> mejor_postor) {

        imprimirPrompt("Modelo: ");
        String modeloN = sc.nextLine().trim();

        int precioInicial = leerEnteroMayorQueCero(sc, "Precio Inicial: ");

        modelo.add(modeloN);
        precio_Inicial.add(precioInicial);
        mejor_puja.add(0);
        mejor_postor.add("NADIE");

        imprimirMensaje("Modelo añadido correctamente.");
    }

    static void listarModelos(ArrayList<String> modelo,
            ArrayList<Integer> precio_Inicial,
            ArrayList<Integer> mejor_puja,
            ArrayList<String> mejor_postor) {

        if (modelo.isEmpty()) {
            imprimirMensaje("No hay pujas registradas.");
        } else {
            int indice = 0;
            while (indice < modelo.size()) {
                imprimirMensaje(indice + " -> " + modelo.get(indice) +
                        " | inicial: " + precio_Inicial.get(indice) +
                        " | mejor puja: " + mejor_puja.get(indice) +
                        " | postor: " + mejor_postor.get(indice));
                indice++;
            }
        }
    }

    static int pedirIndiceValido(Scanner sc, int tam, String prompt) {
        int indice = -1;

        while (indice < 0 || indice >= tam) {
            imprimirPrompt(prompt);
            indice = leerEntero(sc);
            if (indice < 0 || indice >= tam) {
                imprimirMensaje("Índice fuera de rango.");
            }
        }
        return indice;
    }

    static void hacerPuja(Scanner sc, ArrayList<String> modelo,
            ArrayList<Integer> precio_Inicial,
            ArrayList<Integer> mejor_puja,
            ArrayList<String> mejor_postor) {

        if (modelo.isEmpty()) {
            imprimirMensaje("No hay pujas registradas.");
        } else {
            listarModelos(modelo, precio_Inicial, mejor_puja, mejor_postor);
            int indice = pedirIndiceValido(sc, modelo.size(), "Introduce nº de puja: ");
            imprimirPrompt("Introduce tu nombre: ");
            String pujador = sc.nextLine().trim();
            int puja_sup = leerEnteroMayorQueCero(sc, "Cantidad de puja: ");
            if (puja_sup >= precio_Inicial.get(indice)) {
                if (puja_sup > mejor_puja.get(indice)) {
                    mejor_puja.set(indice, puja_sup);
                    mejor_postor.set(indice, pujador);
                } else {
                    imprimirMensaje("No supera la puja actual");
                }
            } else {
                imprimirMensaje("No supera el precio incial");
            }

        }
    }

    static void listarGanadores(ArrayList<Integer> mejor_puja,
            ArrayList<String> mejor_postor) {

        if (mejor_puja.isEmpty()) {
            imprimirMensaje("No hay pujas registradas.");
        } else {
            int indice = 0;
            while (indice < mejor_puja.size()) {
                if (mejor_puja.get(indice) == 0) {
                    String textoEnsenar = "sin pujas todavia";
                    imprimirMensaje(indice + " -> Puja Actual " + textoEnsenar +
                            " | Postor: " + mejor_postor.get(indice));
                } else {
                    imprimirMensaje(indice + " -> Puja Actual " + mejor_puja.get(indice) +
                            " | Postor: " + mejor_postor.get(indice));

                }
                indice++;
            }
        }
    }

    static void eliminarPuja(Scanner sc, ArrayList<String> modelo,
            ArrayList<Integer> precio_Inicial,
            ArrayList<Integer> mejor_puja,
            ArrayList<String> mejor_postor) {

        if (modelo.isEmpty()) {
            imprimirMensaje("No hay pujas para eliminar.");
        } else {
            listarModelos(modelo, precio_Inicial, mejor_puja, mejor_postor);
            int indice = pedirIndiceValido(sc, modelo.size(), "Índice a eliminar: ");
            modelo.remove(indice);
            precio_Inicial.remove(indice);
            mejor_puja.remove(indice);
            mejor_postor.remove(indice);
            imprimirMensaje("Puja eliminada correctamente.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> modelo = new ArrayList<>();
        ArrayList<Integer> precio_Inicial = new ArrayList<>();
        ArrayList<Integer> mejor_puja = new ArrayList<>();
        ArrayList<String> mejor_postor = new ArrayList<>();

        String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir lote\n"
                + "2. Listar lotes\n"
                + "3. Hacer puja\n"
                + "4. Ver ganador actual\n"
                + "5. Eliminar lote";
        int eleccion = 0;
        boolean funcionar = true;

        while (funcionar) {
            eleccion = mostrarMenuYLeerOpcion(sc, menu);
            if (eleccion == 0) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {
                anadirModelo(sc, modelo, precio_Inicial, mejor_puja, mejor_postor);
            } else if (eleccion == 2) {
                listarModelos(modelo, precio_Inicial, mejor_puja, mejor_postor);
            } else if (eleccion == 3) {
                hacerPuja(sc, modelo, precio_Inicial, mejor_puja, mejor_postor);
            } else if (eleccion == 4) {
                listarGanadores(mejor_puja, mejor_postor);
            } else if (eleccion == 5) {
                eliminarPuja(sc, modelo, precio_Inicial, mejor_puja, mejor_postor);
            } else {
                imprimirMensaje("Opción no válida");
            }
        }
    }
}
