import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        double resultado = 0.0; 

        System.out.print("Introduzca un número: ");
        double numero1 = teclado.nextDouble();
        teclado.nextLine();

        System.out.print("Introduzca otro número: ");
        double numero2 = teclado.nextDouble();
        teclado.nextLine();

        System.out.print("Introduzca el operador (+, -, *, /): ");
        String operador = teclado.next();
        teclado.nextLine();
        teclado.close();

        if (operador.equals("+")) {
            resultado = numero1 + numero2;
        } else if (operador.equals("-")) {
            resultado = numero1 - numero2;
        } else if (operador.equals("*")) {
            resultado = numero1 * numero2;
        } else if (operador.equals("/")) {
            if (numero2 != 0) {
                resultado = numero1 / numero2;
            } else {
                System.out.println("Error: No se puede dividir por cero.");
            }
        } else {// Finaliza el programa si el operador es incorrecto
            System.out.println("Operador incorrecto");
        }

        System.out.println("El resultado de " + numero1 + " " + operador + " " + numero2 + " es " + resultado);
        
    }

}
