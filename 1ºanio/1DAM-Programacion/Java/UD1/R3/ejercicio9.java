import java.util.Scanner;
public class ejercicio9 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.print("Introduzca un número de dos dígitos: ");
        int numero = teclado.nextInt();
        
        String mensaje = "";

        // Comprobamos si tiene dos cifras (entre 10 y 99)
        if (numero >= 10 && numero <= 99) {
            
            int digitoDecenas = numero / 10;
            
            int digitoUnidades = numero % 10;
            
            int suma = digitoDecenas + digitoUnidades;
            
            mensaje = "El número tiene dos cifras, la suma de sus cifras es: " + suma;
            
        } else {
            mensaje = "El número no tiene dos cifras";
        }

        System.out.println(mensaje);
        
        teclado.close();
    }
}
