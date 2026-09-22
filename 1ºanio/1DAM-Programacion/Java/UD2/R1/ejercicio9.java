import java.util.ArrayList;

public class ejercicio9 {
    public static void main(String[] args) {
        
        //Creamos el array de números enteros
        ArrayList<String> cola = new ArrayList<>();
        cola.add("cliente1");
        cola.add("cliente2");
        cola.add("cliente3");
        System.out.println("Cola actual: " + cola);

        String clienteAtentido = cola.remove(0);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + cola);
        clienteAtentido = cola.remove(0);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + cola);
        clienteAtentido = cola.remove(0);
        System.out.println("He atendido a: " + clienteAtentido);
        System.out.println("Clientes restantes: " + cola.size());
    }
}
