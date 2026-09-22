import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ejercicio6 {
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> tareas = new ArrayList<>(
                Arrays.asList("tarea1", "tarea2", "tarea3", "tarea4"));
        ArrayList<Boolean> estadoTareas = new ArrayList<>(Arrays.asList(false, true, false, true));

        int opcion = 0;
        final String MENSAJEOPCIONES = "1. Añadir tarea.\r\n" +
                "2. Marcar completada.\r\n" +
                "3. Ver pendientes.\r\n" +
                "4. Ver completadas.\r\n" +
                "5. Salir.\"";
        final String MENSAJETAREANUEVA = "Introduce una nueva tarea";
        final String MENSAJEBUSCARTAREA = "Dime que tarea quieres completar";
        final String MENSAJENOENCONTRADA = "Tarea no encontrada";
        final String MENSAJEERROR = "Debes elegir una opción entre 1-5";

        boolean funcionar = true;

        while (funcionar) {
            mostrarMensaje(MENSAJEOPCIONES);
            opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                mostrarMensaje(MENSAJETAREANUEVA);
                String nuevaTarea = teclado.nextLine();
                tareas.add(nuevaTarea);
                estadoTareas.add(false);
            } else if (opcion == 2) {
                mostrarMensaje(MENSAJEBUSCARTAREA);
                String tareaBuscar = teclado.nextLine();
                boolean encontrado = false;
                for (int i = 0; i < tareas.size(); i++) {
                    if (tareas.get(i).equalsIgnoreCase(tareaBuscar)) {
                        encontrado = true;
                        estadoTareas.set(i, encontrado);
                    } else {
                        mostrarMensaje(MENSAJENOENCONTRADA);
                    }
                }
            } else if (opcion == 3) {
                ArrayList<String> tareasMostrarFalse = new ArrayList<>(
                        Arrays.asList());
                for (int i = 0; i < estadoTareas.size(); i++) {
                    if (estadoTareas.get(i) == false) {
                        tareasMostrarFalse.add(tareas.get(i));
                    }
                }
                System.out.println(tareasMostrarFalse);
            } else if (opcion == 4) {
                ArrayList<String> tareasMostrarTrue = new ArrayList<>(
                        Arrays.asList());
                for (int i = 0; i < estadoTareas.size(); i++) {
                    if (estadoTareas.get(i) == true) {
                        tareasMostrarTrue.add(tareas.get(i));
                    }
                }
                System.out.println(tareasMostrarTrue);
            } else if (opcion == 5) {
                funcionar = false;
            }
            teclado.close();

        }

    }
}
