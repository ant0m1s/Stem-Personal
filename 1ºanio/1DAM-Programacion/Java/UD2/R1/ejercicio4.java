import java.util.ArrayList;

public class ejercicio4 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> animales = new ArrayList<>();
        animales.add("perro");
        animales.add("gato");
        System.out.println("Hace dos años tenía: " + animales);

        animales.add(0, "loro");
        animales.add(0, "tortuga");
        System.out.println("Ahora tengo: " + animales);
    }
}
