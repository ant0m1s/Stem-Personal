import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class tallerTractor {
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

    static void anadirRevision(Scanner sc,
            ArrayList<String> matriculas,
            ArrayList<String> revisiones,
            ArrayList<Integer> extras,
            ArrayList<Integer> coste_finales) {

        imprimirPrompt("Matrícula del tractor: ");
        String matricula = sc.nextLine().trim();

        int extra = leerEnteroMayorQueCero(sc, "Número de extras: ");
        String revision = leerTrabajoValido(sc);

        int coste = calcular_coste(extra, revision);

        matriculas.add(matricula);
        revisiones.add(revision);
        extras.add(extra);
        coste_finales.add(coste);

        imprimirMensaje("Revisión añadida correctamente.");
    }

    static int leerEnteroMayorQueCero(Scanner sc, String prompt) {
        int numero = -1;

        while (numero < 0) {
            imprimirPrompt(prompt);
            numero = leerEntero(sc);
            if (numero < 0) {
                imprimirMensaje("Debe ser mayor que 0.");
            }
        }
        return numero;
    }

    static String leerTrabajoValido(Scanner sc) {
        String trabajo = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Tipo de revisión (basica o completa): ");
            trabajo = sc.nextLine().trim().toLowerCase();
            valido = esTrabajoValido(trabajo);
            if (!valido) {
                imprimirMensaje("Revision no válida.");
            }
        }
        return trabajo;
    }

    static boolean esTrabajoValido(String trabajo) {
        boolean resultado = trabajo.equals("basica") ||
                trabajo.equals("completa");
        return resultado;
    }

    static int calcular_coste(int extra_num, String revision) {
        int coste = 0;
        int extra_Precio = 15;
        if (revision.equals("basica")) {
            coste += 60;
        } else {
            coste += 120;
        }
        coste += (extra_Precio * extra_num);
        return coste;
    }

    static void listarTractores(ArrayList<String> matricula,
            ArrayList<String> revision,
            ArrayList<Integer> coste,
            ArrayList<Integer> extra) {

        if (matricula.isEmpty()) {
            imprimirMensaje("No hay revisiones registradas.");
        } else {
            int indice = 0;
            while (indice < matricula.size()) {
                imprimirMensaje(indice + " -> " + matricula.get(indice) +
                        " | " + revision.get(indice) +
                        " | extras: " + extra.get(indice) +
                        " | coste: " + coste.get(indice));
                indice++;
            }
        }
    }

    static void eliminarTractor(Scanner sc,
            ArrayList<String> matricula,
            ArrayList<String> revision,
            ArrayList<Integer> coste,
            ArrayList<Integer> extra) {

        if (matricula.isEmpty()) {
            imprimirMensaje("No hay tractores para eliminar.");
        } else {
            listarTractores(matricula, revision, coste, extra);
            int indice = pedirIndiceValido(sc, matricula.size(), "Índice a eliminar: ");
            matricula.remove(indice);
            revision.remove(indice);
            coste.remove(indice);
            extra.remove(indice);
            imprimirMensaje("Revisión eliminado correctamente.");
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

    static void MostrarCoste(Scanner sc, ArrayList<Integer> coste) {
        if (coste.isEmpty()) {
            imprimirMensaje("No hay revisiones registradas.");
        } else {
            listarCostes(coste);
            int indice = pedirIndiceValido(sc, coste.size(), "Índice a eliminar: ");
            int numero = 0;
            while (numero < coste.size()) {
                imprimirMensaje(numero + " -> " + "Coste total: " + coste.get(indice));
                numero++;
            }
        }
    }

    static void listarCostes(ArrayList<Integer> coste) {
        int indice = 0;
        while (indice < coste.size()) {
                imprimirMensaje(indice + " -> " + "Coste total: " + coste.get(indice));
                indice++;
            }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> matriculas = new ArrayList<>();
        ArrayList<String> revisiones = new ArrayList<>();
        ArrayList<Integer> extras = new ArrayList<>();
        ArrayList<Integer> coste_finales = new ArrayList<>();

        String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir revisión\n"
                + "2. Listar revisiones\n"
                + "3. Mostrar coste\n"
                + "4. Eliminar revisión\n";
        int eleccion = 0;
        boolean funcionar = true;

        while (funcionar) {
            eleccion = mostrarMenuYLeerOpcion(sc, menu);
            if (eleccion == 0) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {
                anadirRevision(sc, matriculas, revisiones, extras, coste_finales);
            } else if (eleccion == 2) {
                listarTractores(matriculas, revisiones, coste_finales, extras);
            } else if (eleccion == 3) {
                MostrarCoste(sc, coste_finales);
            } else if (eleccion == 4) {
                eliminarTractor(sc, matriculas, revisiones, coste_finales, extras);
            } else {
                imprimirMensaje("Opción no válida");
            }
        }
    }
}
