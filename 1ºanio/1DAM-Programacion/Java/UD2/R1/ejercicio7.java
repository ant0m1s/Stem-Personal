import java.util.ArrayList;

public class ejercicio7 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> dias = new ArrayList<>();
        dias.add("L");
        dias.add("M");
        dias.add("X");
        dias.add("J");
        dias.add("V");
        dias.add("S");
        dias.add("D");
        System.out.println("Vº1 Semana: " + dias);

        dias.remove(dias.size()-1);
        dias.add("Domingo (fin de semana)");

        System.out.println("Vº2 Semana: " + dias);
    }
}
