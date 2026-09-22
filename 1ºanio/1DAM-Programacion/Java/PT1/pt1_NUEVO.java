import java.util.Scanner;

public class pt1_NUEVO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroEncargos = 0;
        int numeroUnidades;
        int encargoActual = 0;
        String articuloUsuario = "";
        boolean funcionamiento = true;
        int stockMadera = 0;
        double stockAcero = 0.0;
        final int STOCKMADERAINICIAL;
        final double STOCKACEROINICIAL;
        final int MADERAPORFLECHA = 1;
        final int MADERAPORESCUDO = 3;
        final int MADERAPORESPADA = 1;
        final double ACEROPORFLECHA = 0.05;
        final double ACEROPORESCUDO = 1.5;
        final double ACEROPORESPADA = 1.2;
        final int MONEDASPORFLECHA = 2;
        final int MONEDASPORESCUDO = 30;
        final int MONEDASPORESPADA = 25;

        do {
            System.out.print("Número de encargos: ");
            numeroEncargos = scanner.nextInt();
        } while (numeroEncargos <= 0);

        System.out.print("Stock de madera (varas): ");
        stockMadera = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Stock de acero (kg): ");
        stockAcero = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        while (encargoActual < numeroEncargos && !funcionamiento) {
            encargoActual++;
            System.out.println("--- Encargo 1 ---");
            
            do {
                System.out.print("Articulo (\n1- Flecha \n2- Escudo \n3- Espada): ");
                articuloUsuario = scanner.nextLine();
            } while (!articuloUsuario.equals("1") && !articuloUsuario.equals("2") && !articuloUsuario.equals("3"));
            
            do {
                try {
                    System.out.print("Cuando unidades quieres: ");
                    numeroUnidades = scanner.nextInt();
                    
                } catch (Exception e) {
                    numeroUnidades = -1;
                    System.out.println("ERROR, debes poner un valor positivo");
                    scanner.nextLine();
                }
            } while (numeroUnidades <= 0);
            
            if (articuloUsuario.equals("1")) {
                System.out.print("Cuando unidades quieres: ");
                System.out.print("Articulo: flecha | Unidades: ");
            }
        }

    }
}
