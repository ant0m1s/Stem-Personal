import java.util.ArrayList;

public class ejercicio3 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> colores = new ArrayList<>();
        colores.add("rojo");
        colores.add("verde");
        colores.add("azul");

        System.out.println("Estoy son mis colores: " + colores);
        String colorBorrado = colores.remove(0);

        System.out.println("Color eliminado: " + colorBorrado);
        System.out.println("Colores restantes: " + colores);
    }
}
