import java.util.ArrayList;

public class ejercicio6 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("manzana");
        frutas.add("banana");
        frutas.add("naranja");
        System.out.println("Frutas originales: " + frutas);

        String frutaBorrada = frutas.remove(0);
        System.out.println("Fruta eliminada: " + frutaBorrada);

        frutas.add("fresa");

        System.out.println("Estas son mis frutas: " + frutas);
    }
}
