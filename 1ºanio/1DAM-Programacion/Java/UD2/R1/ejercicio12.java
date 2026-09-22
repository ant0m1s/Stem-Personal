import java.util.ArrayList;

public class ejercicio12 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Pedro");
        nombres.add("Laura");
        nombres.add(0, "Carlos");
        System.out.println("Nombres actuales: " + nombres);

        String nombreEliminadoPrinc = nombres.remove(0);
        String nombreEliminadoFin = nombres.remove(nombres.size()-1);
        System.out.println("Nombre eliminado (1ºposi): " + nombreEliminadoPrinc);
        System.out.println("Nombre eliminado (ult. posi): " + nombreEliminadoFin);
        System.out.println("Nombres Finales: " + nombres);
    }
}
