import java.util.ArrayList;

public class ejercicio10 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> pila = new ArrayList<>();
        pila.add("10");
        pila.add("20");
        pila.add("30");
        System.out.println("Cola actual: " + pila);

        String clienteAtentido = pila.remove(pila.size()-1);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + pila);
        clienteAtentido = pila.remove(pila.size()-1);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + pila);
        clienteAtentido = pila.remove(pila.size()-1);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + pila.size());
    }
}
