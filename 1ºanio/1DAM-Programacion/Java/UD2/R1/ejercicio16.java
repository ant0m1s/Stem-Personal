import java.util.ArrayList;

public class ejercicio16 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<Integer> secuencia = new ArrayList<>();
        int numerosIntroducir = 5;
        System.out.println("Secuencia inicial: " + secuencia);
        for (int i = 1; i <= numerosIntroducir; i++) {
            secuencia.add(i);
            System.out.println("Secuencia en progreso: " + secuencia);
            
        }
        
        System.out.println("Secuencia final: " + secuencia);


    }
}
