import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class instituto {
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

    static void anadirElemento(Scanner sc,
            ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3) {

        imprimirPrompt("Nombre: ");
        String elemento1 = sc.nextLine().trim();

        imprimirPrompt("DNI: ");
        String elemento4 = sc.nextLine().trim();

        int elemento2 = leerEnteroMayorQueCero(sc, "Edad: ");
        String elemento3 = leerTrabajoValido(sc);

        ArrayString1.add(elemento1);
        ArrayInteger1.add(elemento2);
        ArrayString2.add(elemento3);
        ArrayString3.add(elemento4);

        imprimirMensaje("Persona añadida correctamente.");
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

    static String leerTrabajoValido(Scanner sc) {
        String trabajo = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Función (alumno / profesor / conserje): ");
            trabajo = sc.nextLine().trim().toLowerCase();
            valido = esPalabraValida(trabajo);
            if (!valido) {
                imprimirMensaje("Función no válido.");
            }
        }
        return trabajo;
    }

    static boolean esPalabraValida(String trabajo) {
        boolean resultado = trabajo.equals("alumno") ||
                trabajo.equals("profesor") ||
                trabajo.equals("conserje");
        return resultado;
    }

    static void listarElementos(ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3) {

        if (ArrayString1.isEmpty()) {
            imprimirMensaje("No hay personas registradas.");
        } else {
            int indice = 0;
            while (indice < ArrayString1.size()) {
                imprimirMensaje(indice + " Alumno-> " + ArrayString1.get(indice) +
                        " | DNI: " + ArrayString3.get(indice) +
                        " | Función: " + ArrayString2.get(indice) +
                        " | Edad: " + ArrayInteger1.get(indice));
                indice++;
            }
        }
    }

    static void eliminarTractor(Scanner sc,
            ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3) {

        if (ArrayString1.isEmpty()) {
            imprimirMensaje("No hay personas para eliminar.");
        } else {
            listarElementos(ArrayString1, ArrayInteger1, ArrayString2, ArrayString3);
            int indice = pedirIndiceValido(sc, ArrayString1.size(), "Índice a eliminar: ");
            ArrayString1.remove(indice);
            ArrayInteger1.remove(indice);
            ArrayString2.remove(indice);
            ArrayString3.remove(indice);
            imprimirMensaje("Persona eliminado correctamente.");
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
        return (indice);
    }

    static void calcularResultadoEDAD(ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3,
            int edadFILTRAR) {
                int indice = 0;
                while (indice < ArrayString1.size()) {
                    if (ArrayInteger1.get(indice) <= edadFILTRAR) {
                        imprimirMensaje(indice + " Alumno-> " + ArrayString1.get(indice) +
                        " | DNI: " + ArrayString3.get(indice) +
                        " | Función: " + ArrayString2.get(indice) +
                        " | Edad: " + ArrayInteger1.get(indice));
                    }
                    indice++;
                }
    }
    static void calcularResultadoFUN(ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3,
            String funcion) {
                int indice = 0;
                while (indice < ArrayString1.size()) {
                    if (ArrayString2.get(indice).equals(funcion)) {
                        imprimirMensaje(indice + " Alumno-> " + ArrayString1.get(indice) +
                        " | DNI: " + ArrayString3.get(indice) +
                        " | Función: " + ArrayString2.get(indice) +
                        " | Edad: " + ArrayInteger1.get(indice));
                    }
                    indice++;
                }
    }

    static String mensajeResultado(String resultado) {
        ArrayList<String> mensajesApto = new ArrayList<>();
        mensajesApto.add("Este tractor es adecuado para el trabajo.");
        mensajesApto.add("Buena elección para este tipo de tarea.");

        ArrayList<String> mensajesNoApto = new ArrayList<>();
        mensajesNoApto.add("La potencia es insuficiente.");
        mensajesNoApto.add("Sería mejor usar un tractor más potente.");

        // Se puede usar también Math.random() en lugar de Random
        Random generadorAleatorio = new Random();
        String mensaje = "";

        if (resultado.equals("APTO")) {
            mensaje = mensajesApto.get(generadorAleatorio.nextInt(mensajesApto.size()));
        } else {
            mensaje = mensajesNoApto.get(generadorAleatorio.nextInt(mensajesNoApto.size()));
        }

        return mensaje;
    }

    static void clasificarEdad(Scanner sc,
            ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3) {

        if (ArrayString1.isEmpty()) {
            imprimirMensaje("No hay personas disponibles.");
        } else {
            listarElementos(ArrayString1, ArrayInteger1, ArrayString2, ArrayString3);
            int edad = leerEnteroMayorQueCero(sc, "Dime la edad por la que quieras filtrar (sera esa edad o menos): ");

            calcularResultadoEDAD(ArrayString1, ArrayInteger1, ArrayString2, ArrayString3, edad);
        }
    }
    static void clasificarFuncion(Scanner sc,
            ArrayList<String> ArrayString1,
            ArrayList<Integer> ArrayInteger1,
            ArrayList<String> ArrayString2,
            ArrayList<String> ArrayString3) {

        if (ArrayString1.isEmpty()) {
            imprimirMensaje("No hay personas disponibles.");
        } else {
            listarElementos(ArrayString1, ArrayInteger1, ArrayString2, ArrayString3);
            String funcionFiltrar = leerTrabajoValido(sc);

            calcularResultadoFUN(ArrayString1, ArrayInteger1, ArrayString2, ArrayString3, funcionFiltrar);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> personas = new ArrayList<>();
        ArrayList<Integer> edades = new ArrayList<>();
        ArrayList<String> funciones = new ArrayList<>();
        ArrayList<String> dni = new ArrayList<>();

        String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir elemento\n"
                + "2. Listar elementos\n"
                + "3. Filtrar por edad\n"
                + "4. Eliminar elemento\n"
                + "5. Filtrar por función\n";
        int eleccion = 0;
        boolean funcionar = true;

        while (funcionar) {
            eleccion = mostrarMenuYLeerOpcion(sc, menu);
            if (eleccion == 0) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {
                anadirElemento(sc, personas, edades, funciones, dni);
            } else if (eleccion == 2) {
                listarElementos(personas, edades, funciones, dni);
            } else if (eleccion == 3) {
                clasificarEdad(sc, personas, edades, funciones, dni);
            } else if (eleccion == 4) {
                eliminarTractor(sc, personas, edades, funciones, dni);
            } else if (eleccion == 5) {
                clasificarFuncion(sc, personas, edades, funciones, dni);
            }
            else {
                imprimirMensaje("Opción no válida");
            }
        }
    }
}
