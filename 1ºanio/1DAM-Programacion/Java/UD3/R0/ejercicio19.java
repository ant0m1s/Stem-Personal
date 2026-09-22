public class Principal {

    public static void main(String[] args) {
        // Equivale al alert(capitalizarPrimeraLetra("jaime"))
        System.out.println(capitalizarPrimeraLetra("jaime"));
    }

    public static String capitalizarPrimeraLetra(String palabra) {
        // Manejo de errores (si la palabra viene vacía o nula)
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }

        String palabraCapitalizada = "";

        // Bucle igual al de tu captura
        for (int i = 0; i < palabra.length(); i++) {
            if (i == 0) {
                // Java usa Character.toUpperCase y charAt(index)
                palabraCapitalizada += Character.toUpperCase(palabra.charAt(i));
            } else {
                palabraCapitalizada += palabra.charAt(i);
            }
        }

        return palabraCapitalizada;
    }
}