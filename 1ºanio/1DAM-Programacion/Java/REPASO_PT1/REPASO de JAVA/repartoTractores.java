import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class repartoTractores {
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

    static String leerTrabajoValido(Scanner sc) {
        String trabajo = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Trabajo (llano / mixto / montaña): ");
            trabajo = sc.nextLine().trim().toLowerCase();
            valido = esTrabajoValido(trabajo);
            if (!valido) {
                imprimirMensaje("Trabajo no válido.");
            }
        }
        return trabajo;
    }

    static double leerDouble(Scanner sc) {
        double numero = 0.0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String texto = sc.nextLine().trim();
            try {
                // Cambiamos Integer por Double
                numero = Double.parseDouble(texto);
                numeroValido = true;
            } catch (NumberFormatException e) {
                imprimirPrompt("Debes introducir un número (puedes usar decimales): ");
            }
        }
        return numero;
    }

    static double leerDoubleMayorQueCero(Scanner sc, String prompt) {
        double numero = 0.0;

        while (numero <= 0) {
            imprimirPrompt(prompt);
            numero = leerDouble(sc); // Llamamos a nuestra nueva función leerDouble
            if (numero <= 0) {
                imprimirMensaje("El valor debe ser mayor que 0.");
            }
        }
        return numero;
    }

    static void anadirRuta(Scanner sc,
            ArrayList<String> tractor,
            ArrayList<Double> kilometros,
            ArrayList<Double> litros_disponibles,
            ArrayList<String> terreno) {

        imprimirPrompt("Nombre del tractor: ");
        String tractor_act = sc.nextLine().trim();

        double km = leerDoubleMayorQueCero(sc, "Kilómetros: ");
        double litros_gaso = leerDoubleMayorQueCero(sc, "Gasolina: ");
        String terrenoActual = leerTrabajoValido(sc);

        tractor.add(tractor_act);
        kilometros.add(km);
        litros_disponibles.add(litros_gaso);
        terreno.add(terrenoActual);

        imprimirMensaje("Ruta añadida correctamente.");
    }

    static boolean esTrabajoValido(String trabajo) {
        boolean resultado = trabajo.equals("llano") ||
                trabajo.equals("mixto") ||
                trabajo.equals("montaña");
        return resultado;
    }

    static void listarRutas(ArrayList<String> tractor,
            ArrayList<Double> kilometros,
            ArrayList<Double> litros_disponibles,
            ArrayList<String> terreno) {

        if (tractor.isEmpty()) {
            imprimirMensaje("No hay rutas registrados.");
        } else {
            int indice = 0;
            while (indice < tractor.size()) {
                imprimirMensaje(indice + " -> " + tractor.get(indice) +
                        " | " + kilometros.get(indice) + " km" +
                        " | " + litros_disponibles.get(indice) +
                        " | terreno: " + terreno.get(indice));
                indice++;
            }
        }
    }

    static void eliminarRuta(Scanner sc,
            ArrayList<String> tractor,
            ArrayList<Double> kilometros,
            ArrayList<Double> litros_disponibles,
            ArrayList<String> terreno) {

        if (tractor.isEmpty()) {
            imprimirMensaje("No hay rutas para eliminar.");
        } else {
            listarRutas(tractor, kilometros, litros_disponibles, terreno);
            int indice = pedirIndiceValido(sc, tractor.size(), "Índice a eliminar: ");
            tractor.remove(indice);
            kilometros.remove(indice);
            litros_disponibles.remove(indice);
            terreno.remove(indice);
            imprimirMensaje("Ruta eliminada correctamente.");
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

    static void consumoRuta(Scanner sc, ArrayList<String> tractor,
            ArrayList<Double> kilometros,
            ArrayList<Double> litros_disponibles,
            ArrayList<String> terreno) {

        if (tractor.isEmpty()) {
            imprimirMensaje("No hay rutas registrados.");
        } else {
            listarRutas(tractor, kilometros, litros_disponibles, terreno);
            boolean viable = false;
            int indice = pedirIndiceValido(sc, tractor.size(), "Introduce el índice de la ruta: ");
            double litrosNecesarios = 0.0;
            double kmActuales = kilometros.get(indice);
            double litrosFaltantes = 0.0;
            if (terreno.get(indice).equals("llano")) {
                litrosNecesarios = kmActuales * 0.12;
            } else if (terreno.get(indice).equals("mixto")) {
                litrosNecesarios = kmActuales * 0.18;
            } else {
                litrosNecesarios = kmActuales * 0.25;
            }
            if (litrosNecesarios <= litros_disponibles.get(indice)) {
                viable = true;
            } else {
                litrosFaltantes = litrosNecesarios - litros_disponibles.get(indice);
            }
            String mensajeMostrar = mensajesRutas(viable);
            if (viable) {
                imprimirMensaje(mensajeMostrar);
                
            } else {
                imprimirMensaje(mensajeMostrar + " Para lograr hacer el viaje necesitas " + litrosFaltantes);
            }
        }
    }
    static String mensajesRutas(boolean viable) {
        Random generadorAleatorio = new Random();
        String mensaje = "";
        ArrayList<String> mensajesViable = new ArrayList<>();
        mensajesViable.add("Podrás hacer la ruta");
        mensajesViable.add("Haces sobrado el viaje");
        ArrayList<String> mensajesNoViable = new ArrayList<>();
        mensajesNoViable.add("No podrás hacer la ruta");
        mensajesNoViable.add("En la vida podrás hacer el viaje");
        if (viable) {
           mensaje = mensajesViable.get(generadorAleatorio.nextInt(mensajesViable.size()));
        } else { 
            mensaje = mensajesNoViable.get(generadorAleatorio.nextInt(mensajesNoViable.size()));
        }
        return mensaje;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tractor = new ArrayList<>();
        ArrayList<Double> kilometros = new ArrayList<>();
        ArrayList<Double> litros_disponibles = new ArrayList<>();
        ArrayList<String> terreno = new ArrayList<>();

        String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir ruta\n"
                + "2. Listar rutas\n"
                + "3. Calcular consumo\n"
                + "4. Eliminar ruta\n";
        int eleccion = 0;
        boolean funcionar = true;

        while (funcionar) {
            eleccion = mostrarMenuYLeerOpcion(sc, menu);
            if (eleccion == 0) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {
                anadirRuta(sc, tractor, kilometros, litros_disponibles, terreno);
            } else if (eleccion == 2) {
                listarRutas(tractor, kilometros, litros_disponibles, terreno);
            } else if (eleccion == 3) {
                consumoRuta(sc, tractor, kilometros, litros_disponibles, terreno);
            } else if (eleccion == 4) {
                eliminarRuta(sc, tractor, kilometros, litros_disponibles, terreno);
            } else {
                imprimirMensaje("Opción no válida");
            }
        }
    }
}
