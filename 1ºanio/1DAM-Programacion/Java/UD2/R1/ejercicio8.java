import java.util.ArrayList;

public class ejercicio8 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(100);
        numeros.add(200);
        numeros.add(300);

        System.out.println("Numero iniciales: " + numeros);
        
        int variable_temporal = numeros.get(0);
        numeros.set(0, numeros.get(numeros.size()-1) );
        numeros.set(numeros.size()-1, variable_temporal);
        System.out.println("Números finales: " + numeros);
    }
}
