# Glosario para programación en JAVA
# Imports
### Principales "imports" que utilizaremos
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

### Crear Scanner
Scanner sc = new Scanner(System.in);

### Crear ARRAYS
ArrayList<String> A1 = new ArrayList<>();
ArrayList<Integer> A2 = new ArrayList<>();
ArrayList<Boolean> A3 = new ArrayList<>();
ArrayList<Boolean> A4 = new ArrayList<>();

### Crear MENU con su opciones
String menu = "-------Menu Opcion---------\n"
                + "0. Salir\n"
                + "1. Añadir tractor\n"
                + "2. Listar tractores\n"
                + "3. Evaluar tractor (por índice)\n"
                + "4. Eliminar tractor\n";

## Funciones importantes
## 1. Ingresar entero
### Comprueba prompt del usuario, para que solamente pueda introducir un entero
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

## 2. Muestra menu y pide opción
### Muestra un menu y pide una opción válida
static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

## 2.1 Ingresar entero > 0
### Comprobación para que el entero sea superior a 0
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

## 3. Añade elementos a Arrays
### Se encarga de control de errores a través de funciones auxiliares y lo añade a cada array
static void anadirElemento(Scanner sc,
            ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        imprimirPrompt("Marca del tractor: ");
        String marca = sc.nextLine().trim();

        int potencia = leerEnteroMayorQueCero(sc, "Potencia (CV): ");
        String trabajo = leerPalabraValida(sc);

        A1.add(marca);
        A2.add(potencia);
        A3.add(trabajo);

        imprimirMensaje("Tractor añadido correctamente.");
    }

## 4. Lista Array Completo
### Comprueba si hay elementos en el ARRAY y muestra el ARRAY completo

static void listarElementos(ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        if (A1.isEmpty()) {
            imprimirMensaje("No hay tractores registrados.");
        } else {
            int indice = 0;
            while (indice < A1.size()) {
                imprimirMensaje(indice + " -> " + A1.get(indice) +
                        " | " + A2.get(indice) +
                        " CV | trabajo: " + A3.get(indice));
                indice++;
            }
        }
    }

## 5. Comprueba indice correcto para ARRAY
### Se encarga en comprobar si el número es válido para el tamaño del array

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


## 6. Elimina elemento ARRAY
### Basado en comprobar tamaño del array, y si es posible, lista el array y pide indice para eliminar
static void eliminarElemento(Scanner sc,
            ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        if (A1.isEmpty()) {
            imprimirMensaje("No hay tractores para eliminar.");
        } else {
            listarElementos(A1, A2, A3);
            int indice = pedirIndiceValido(sc, A1.size(), "Índice a eliminar: ");
            A1.remove(indice);
            A2.remove(indice);
            A3.remove(indice);
            imprimirMensaje("Tractor eliminado correctamente.");
        }
    }
---


## Todas las Funciones
# Imprime mensajes

## FUNCION IMPRIMIRMENSAJE()
## No recibe parámetros
## 1. Imprime mensaje en consola
static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

## FUNCION IMPRIMIRPROMPT()
## No recibe parámetros
## 2. Imprime prompt para usuario
static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

---

# Opción del menú

## (Necesita función imprimirPrompt())
## FUNCION LEERENTERO()
## Recibe como parámetro "Scanner"
## 1. Comprueba prompt del usuario, para que solamente pueda introducir un entero
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

## (Necesita funciones imprimirMensaje(), imprimirPrompt() y leerEntero())
## FUNCION MOSTRARMENUYLEEROPCION()
## Recibe como parámetros "Scanner" y "Menú para mostrar"
## 2. Muestra un menu y pide una opción válida
static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

---
# Añadir elementos a un array

## (Necesita funciones imprimirMensaje(), imprimirPrompt() y leerEntero())
## FUNCION LEERENTEROMAYORQUECERO()
## Recibe como parámetros "Scanner" y "Mensaje para pedir al usuario"
## 1. Comprobación para que el entero sea superior a 0
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

## (No necesita funciones auxiliares)
## FUNCION ESPalabraVALIDa()
## Recibe un "String· para compararlo con una lista
## 2. Guarda strings para comprobar y devuelve un bool
static boolean esPalabraValida(String trabajo) {
        boolean resultado = trabajo.equals("arado") ||
                trabajo.equals("transporte") ||
                trabajo.equals("siembra");
        return resultado;
    }


## (Necesita funciones imprimirMensaje(), imprimirPrompt() y esPalabraValida())
## FUNCION LEERPalabraVALIDa()
## Recibe como parámetro "Scanner" 
## 3. Pide opción y comprueba "string" a través de otra función
static String leerPalabraValida(Scanner sc) {
        String trabajo = "";
        boolean valido = false;

        while (!valido) {
            imprimirPrompt("Trabajo (arado / transporte / siembra): ");
            trabajo = sc.nextLine().trim().toLowerCase();
            valido = esPalabraValida(trabajo);
            if (!valido) {
                imprimirMensaje("Trabajo no válido.");
            }
        }
        return trabajo;
    }

## (Necesita funciones imprimirPrompt(), leerEnteroMayorQueCero() y leerPalabraValida())
## FUNCION AÑADIRTRACTOR()
## Recibe como parámetros "Scanner" y varios ARRAYS
## 4. Se encarga de control de errores a través de funciones auxiliares y lo añade a cada array
static void anadirElemento(Scanner sc,
            ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        imprimirPrompt("Marca del tractor: ");
        String marca = sc.nextLine().trim();

        int potencia = leerEnteroMayorQueCero(sc, "Potencia (CV): ");
        String trabajo = leerPalabraValida(sc);

        A1.add(marca);
        A2.add(potencia);
        A3.add(trabajo);

        imprimirMensaje("Tractor añadido correctamente.");
    }

---
# Listar elementos de ARRAY

## (Necesita función imprimirMensaje())
## FUNCION LISTARElementos()
## Recibe como parámetros varios ARRAYS
## 1. Comprueba si hay elementos en el array y muestra el array completo

static void listarElementos(ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        if (A1.isEmpty()) {
            imprimirMensaje("No hay tractores registrados.");
        } else {
            int indice = 0;
            while (indice < A1.size()) {
                imprimirMensaje(indice + " -> " + A1.get(indice) +
                        " | " + A2.get(indice) +
                        " CV | trabajo: " + A3.get(indice));
                indice++;
            }
        }
    }

---
# Borrar elementos de ARRAY

## (Necesita funciones imprimirMensaje(), imprimirPrompt() y leerEntero())
## FUNCION PEDIRINDICEVALIDO()
## Recibe como parámetros "Scanner", tamañodelArray y prompt para usuario
## 1. Se encarga en comprobar si el número es válido para el tamaño del array

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

## (Necesita funciones imprimirMensaje(), listarElementos() y pedirIndiceValido())
## FUNCION ELIMINARElemento()
## Recibe como parámetros "Scanner" y varios ARRAYS
## 2. Basado en comprobar tamaño del array, y si es posible, lista el array y pide indice para eliminar
static void eliminarElemento(Scanner sc,
            ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        if (A1.isEmpty()) {
            imprimirMensaje("No hay tractores para eliminar.");
        } else {
            listarElementos(A1, A2, A3);
            int indice = pedirIndiceValido(sc, A1.size(), "Índice a eliminar: ");
            A1.remove(indice);
            A2.remove(indice);
            A3.remove(indice);
            imprimirMensaje("Tractor eliminado correctamente.");
        }
    }

# Evaluar elementos de ARRAY

## (No necesita funciones auxiliares)
## FUNCION CALCULARRESULTADO()
## Recibe como parámetros un elemento del array "string" y otro elemento de array "int".
## 1. Compara los elementos recibidos y devuelve un mensaje según el caso


static String calcularResultado(String trabajo, int potencia) {
        String resultado = "NO APTO";

        if (trabajo.equals("arado") && potencia >= 120) {
            resultado = "APTO";
        } else if (trabajo.equals("transporte") && potencia >= 90) {
            resultado = "APTO";
        } else if (trabajo.equals("siembra") && potencia >= 70) {
            resultado = "APTO";
        }

        return resultado;
    }

## (No necesita funciones auxiliares)
## FUNCION MENSAJERESULTADO()
## Recibe como parámetro un "mensaje"
## 2. Crea dos ARRAYS con dos mensajes (en cada uno) y según lo que reciba eligirá aleatoriamente entre los arrays

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

## (Necesita funciones imprimirMensaje(), listarElementos(), pedirIndiceValido(), calcularResultado() y mensajeResultado())
## FUNCION EVALUARElemento()
## Recibe como parámetros "Scanner" y varios ARRAYS
## 3. Comprueba el tamaño del array y a través de funciones auxiliares recoge un índice, comprueba sus valores y da un mensaje

static void evaluarElemento(Scanner sc,
            ArrayList<String> A1,
            ArrayList<Integer> A2,
            ArrayList<String> A3,
            ArrayList<Integer> A4) {

        if (A1.isEmpty()) {
            imprimirMensaje("No hay tractores para evaluar.");
        } else {
            listarElementos(A1, A2, A3);
            int indice = pedirIndiceValido(sc, A1.size(), "Índice del tractor: ");
            String resultado = calcularResultado(A3.get(indice), A2.get(indice));
            imprimirMensaje("Resultado: " + resultado);
            imprimirMensaje(mensajeResultado(resultado));
        }
    }
### Doubles
### 1. Lee cualquier número (permite decimales como 10.5)
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

### 2. Se asegura de que el número sea positivo (ej. para precios o A2)
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