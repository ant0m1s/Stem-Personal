public class ejercicio8 {

    public static int mayor (int a, int b){
        int maximo = b;
        if (a > b) {
            maximo = a;
        }
        return maximo;
    }


    public static void main(String[] args) {
        int resultadoComparacion = mayor(12, 10);
        System.out.println("El número mayor: " + resultadoComparacion);
    }
}