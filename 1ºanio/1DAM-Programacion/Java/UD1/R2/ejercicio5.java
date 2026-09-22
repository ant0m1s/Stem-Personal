import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {
    Scanner scanner1 = new Scanner(System.in);
    
    System.out.print("Introduzca su peso: ");
    double pesoEnLaTierra = scanner1.nextDouble();
    double conversion = 0.165;

    double pesoEnLaLuna = pesoEnLaTierra * conversion;

    System.out.println("Tu peso en la Tierra: " + pesoEnLaTierra + "kg");
    System.out.println("En la Luna pesarías: " + pesoEnLaLuna + "kg");
    
    scanner1.close();


    }
}
