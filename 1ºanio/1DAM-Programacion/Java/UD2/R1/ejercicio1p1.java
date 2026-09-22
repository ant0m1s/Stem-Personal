import java.util.ArrayList;

public class ejercicio1p1 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(10);
        numeros.add(15);
        numeros.add(20);

        System.out.println("El primer elemento es: " + numeros.get(0));

        System.out.println("El último elemento es: " + numeros.get(numeros.size()-1));
    }
}
