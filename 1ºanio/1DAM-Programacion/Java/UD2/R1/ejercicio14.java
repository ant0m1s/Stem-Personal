import java.util.ArrayList;

public class ejercicio14 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(10);
        numeros.add(15);
        numeros.add(20);
        System.out.println("Numero iniciales: " + numeros);

        int numeroBorrado = numeros.remove(0);
        numeros.add(numeroBorrado);

        System.out.println("Primer número cambiado: " + numeros);
        
    }
}
