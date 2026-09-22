import java.util.Scanner;

public class ejercicio3 {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Supermercado mercadona = new Supermercado();
        Producto productoRandom;
        final int NUMEROPRODUCTOS = 50;
        boolean funcionar = true;
        int opcion = 0;

        final String MENU = "0. SALIR \n1. AGREGAR PRODUCTO \n2. VENDER PRODUCTO \n3. MOSTRAR INVENTARIO";

        for (int i = 0; i < NUMEROPRODUCTOS; i++) {
            productoRandom = new Producto(FakerPR.nombreProducto(), FakerPR.descripcionProducto(), FakerPR.precioProducto(),
                    FakerPR.cantidadStock());
            mercadona.agregarProducto(productoRandom);
        }
        System.out.println(mercadona.mostrarProductos());

        while (funcionar) {
            opcion = mostrarMenuYLeerOpcion(sc, MENU);
            if (opcion == 0) {
                funcionar = false;
                System.out.println("Saliendo...");
            } else if (opcion == 1) {
                System.out.print("Nombre del producto: ");
                String nombreProducto = sc.nextLine();
                System.out.print("Descripción del producto: ");
                String descripcionProducto = sc.nextLine();
                int precioProducto = leerEnteroMayorQueCero(sc, "Precio: ");
                int cantidadProducto = leerEnteroMayorQueCero(sc, "Cantidad de Stock: ");
                Producto nuevoProducto = new Producto(nombreProducto, descripcionProducto, precioProducto,
                        cantidadProducto);
                mercadona.agregarProducto(nuevoProducto);
                System.out.println(nuevoProducto);
            } else if (opcion == 2) {
                System.out.print("Nombre del producto: ");
                String nombreProducto = sc.nextLine();
                int cantidadProducto = leerEnteroMayorQueCero(sc, "Cuanto stock quieres eliminar?? ");
                Producto vendido = mercadona.venderProducto(nombreProducto, cantidadProducto);
                if (vendido != null) {
                    System.out.println("Venta realizada. Stock restante: " + vendido.getCantidadStock());
                } else {
                    System.out.println("No se pudo vender: producto no encontrado o stock insuficiente.");
                }
            } else if (opcion == 3) {
                System.out.println(mercadona.mostrarProductos());
            } else {
                System.out.println("Opción no válida, selecciona una opción entre 0-3");
            }
        }
    }
}
