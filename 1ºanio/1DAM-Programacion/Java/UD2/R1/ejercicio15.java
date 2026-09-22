import java.util.ArrayList;

public class ejercicio15 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);
        System.out.println("Numero iniciales: " + numeros);

        int tamanioNumeros = numeros.size();
        for(int i = 0; i< tamanioNumeros; i++){
            numeros.remove(numeros.remove(0));
            System.out.println("Borrando números: " + numeros);
        }

    }
}
