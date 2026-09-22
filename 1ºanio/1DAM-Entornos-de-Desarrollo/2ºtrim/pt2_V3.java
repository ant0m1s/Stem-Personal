import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal para el juego de Mario vs. Luigi: "Topo-Bomba".
 * El juego consiste en un tablero de tuberías donde los jugadores deben evitar 
 * las bombas ocultas para ganar.
 * * @author TuNombre
 * @version 3.0
 */
public class pt2_V3 {

    /**
     * 
     * Gestiona la configuración del tablero. 
     * la colocación aleatoria de bombas y el bucle principal de juego.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // --- Configuración y Declaración de variables ---
        final int MINIMO_BOMBAS = 1, MAXIMO_BOMBAS = 5;
        final int TAMANIO_TABLERO = 10;

        String nombreJugador = "";
        int pocicioAleatoriaDeBomba = 0, intentos = 0;
        int pocicioElegida = -1;

        boolean bombaExplotado = false, resultadoEmpate = false, entradaValida = false;

        // Cálculo del número de bombas de forma aleatoria entre el rango definido
        int numeroBombas = (int)(Math.random() * (MAXIMO_BOMBAS - MINIMO_BOMBAS + 1)) + MINIMO_BOMBAS;
        int numeroBombasPorColocar = numeroBombas;
        
        // --- Creación del tablero externo (Visible para los jugadores) ---
        ArrayList<String> tablero = new ArrayList<String>();
        for (int i = 0; i < TAMANIO_TABLERO; i++) {
            tablero.add("🟩");
        }

        // --- Creación del tablero interno (Donde se ocultan las bombas) ---
        ArrayList<Integer> tableroInterno = new ArrayList<Integer>();
        for (int i = 0; i < TAMANIO_TABLERO; i++) {
            tableroInterno.add(0); // 0 indica tubería segura
        }

        /**
         * 
         * Bucle para colocar las bombas aleatoriamente en el tablero interno.
         * Se asegura de no colocar dos bombas en la misma posición.
         */
        while (numeroBombasPorColocar > 0) {
            pocicioAleatoriaDeBomba = (int)(Math.random() * (tableroInterno.size() - 1));
            if (tableroInterno.get(pocicioAleatoriaDeBomba) != 1) { 
                tableroInterno.set(pocicioAleatoriaDeBomba, 1); // 1 indica bomba
                numeroBombasPorColocar--;
            }
        }

        // --- Inicio de la interfaz de consola ---
        System.out.print("\nTablero interno: ");
        for (int i = 0; i < tableroInterno.size(); i++) {
            System.out.print(tableroInterno.get(i));
        }
        System.out.println();

        System.out.print("Tablero visible: ");
        for (int i = 0; i < tablero.size(); i++) {
            System.out.print(tablero.get(i));
        }

        System.out.println("\n\n------------ Juego -------------");
        Scanner inputUsuario = new Scanner(System.in);

        /**
         * 
         * Bucle principal del juego.
         * Se ejecuta mientras no explote una bomba y/o no haya un empate.
         */
        while (bombaExplotado == false && resultadoEmpate == false) {

            // Alternancia de turnos entre Mario y Luigi
            if (intentos == 0 || intentos % 2 == 0) {
                nombreJugador = "Mario";
            } else {
                nombreJugador = "Luigi";
            }

            /**
             * 
             * Bucle para validación de entrada.
             * Utiliza try-catch para evitar que el programa se cierre si se introduce texto.
             */
            entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("\n" + nombreJugador + ", elige una posición del 0 al " + (tablero.size() - 1) + ": ");
                    pocicioElegida = inputUsuario.nextInt();

                    // Validación de rango
                    if (pocicioElegida < 0 || pocicioElegida >= tablero.size()) {
                        System.out.println("Error: número fuera de rango.");
                        throw new Exception();
                    }

                    // Validación de posición repetida
                    if (!(tablero.get(pocicioElegida).equals("🟩"))) {
                        System.out.println("Error: esa posición ya fue usada.");
                        throw new Exception();
                    }

                    entradaValida = true;

                } catch (Exception e) {
                    System.out.println("Entrada no válida. Inténtalo de nuevo.");
                    inputUsuario.nextLine(); // Limpieza del búfer del Scanner
                }
            }
            
            // --- Comprobación del resultado del movimiento ---
            if (tableroInterno.get(pocicioElegida) == 1) {
                // Caso: El jugador golpea una bomba
                tablero.set(pocicioElegida, "💣");
                System.out.println("Tuberia con topo-bomba ");
                imprimirTablero(tablero);
                System.out.println("\nTOPO BOMBA, " + nombreJugador.toUpperCase() + " PIERDE!");
                bombaExplotado = true;

            } else {
                // Caso: El jugador elige una posición segura
                tablero.set(pocicioElegida, "⚪");
                System.out.println("Tuberia segura ");
                imprimirTablero(tablero);
                System.out.println();
            }
            intentos++;

            // --- Comprobación de condición de empate/victoria conjunta ---
            if ((intentos + numeroBombas) == tablero.size() && bombaExplotado == false) {
                resultadoEmpate = true;
                System.out.println("Mario y Luigi ganan juntos. ¡No quedan tuberías seguras!");
            }
        }
        inputUsuario.close();
    }

    /**
     * 
     * Método auxiliar para imprimir el estado actual del tablero.
     * * @param tablero El ArrayList que contiene los iconos del tablero.
     */
    private static void imprimirTablero(ArrayList<String> tablero) {
        System.out.print("Tablero: ");
        for (String casilla : tablero) {
            System.out.print(casilla);
        }
    }
}