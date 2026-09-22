import java.util.ArrayList;

public class ejercicio11 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Pedro");
        System.out.println("Nombres actuales: " + nombres);

        nombres.add("Laura");
        nombres.add(0, "Carlos");
        System.out.println("Nombres finales: " + nombres);
    }
}
