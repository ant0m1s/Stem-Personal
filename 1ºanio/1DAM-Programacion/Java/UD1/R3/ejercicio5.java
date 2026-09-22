import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Pedir número
        System.out.print("Introduzca el numero: ");
        int numero = teclado.nextInt();
        teclado.close();

        // Booleano para identificar el numero
        boolean estaEntre10y20 = (numero >= 10 && numero <= 20);

        if (estaEntre10y20) {
            System.out.println("El número está entre 10 y 20");
        } else {
            System.out.println("El número NO está entre 10 y 20");
        }

    }

}
