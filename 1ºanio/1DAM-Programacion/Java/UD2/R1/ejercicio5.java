import java.util.ArrayList;

public class ejercicio5 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> animales = new ArrayList<>();
        animales.add("perro");
        animales.add("gato");
        
        animales.add(0, "loro");
        animales.add(0, "tortuga");
        System.out.println("Mis animales: " + animales);

        String animalBorrado = animales.remove(0);
        System.out.println("Animal eliminado: " + animalBorrado);
        System.out.println("Animales restantes: " + animales);

    }
}
