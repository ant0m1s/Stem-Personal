import java.util.ArrayList;

public class ejercicio13 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> letras = new ArrayList<>();
        letras.add("a");
        letras.add("b");
        letras.add("c");
        letras.add("d");
        System.out.println("Letras actuales: " + letras);

        ArrayList<String> letrasInvertidas = new ArrayList<>();
        int tamanioLetras = letras.size();
        for (int i = 0; i < tamanioLetras; i++ ){
            letrasInvertidas.add(letras.remove(letras.size()-1));
        }
        System.out.println("Letras invertidas: " + letrasInvertidas);

    
    }
}
