import java.util.Scanner;

public class pt1V2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int STOCKMADERAINICIAL;
        final double STOCKACEROINICIAL;

        int numeroEncargos;
        
        int stockMaderaRestante = 0, consumoMadera = 0, consumoMaderaTotal = 0;
        double stockacerorestante = 0, consumoAcero = 0, consumoAceroTotal = 0;

        int contadorEncargos = 0;
        int encargoActual;
        int ObjetosXEncargo = 0;
        String objetoEncargado = "";

        boolean errorMaterial = false;

        int dineroTotal = 0, precio = 0, costeEncargo = 0;

        int consumoMaderaFlecha = 1, precioFelcha = 2;
        double consumoAceroFlecha = 0.05;

        int consumoMaderaEscudo = 3, precioEscudo = 30;
        double consumoAceroEscudo = 1.5;

        int consumoMaderaEspada = 1, precioEspada = 25;
        double consumoAceroEspada = 1.2;

        int totalFlechas = 0, totalEscudos = 0, totalEspadas = 0;

        // !!
        do {
            try {
                System.out.print("¿Cuantos encargos piensas realizar? (Int): ");
                numeroEncargos = Integer.parseInt(scanner.nextLine()) ;
            } catch (Exception e) {
                numeroEncargos = 0;
                System.out.println("ERROR al leer entero");
            }
        } while(numeroEncargos <= 0);
        

        System.out.print("Cual es el stock inicial de madera? (Int): ");
        STOCKMADERAINICIAL = scanner.nextInt();

        System.out.print("Cual es el stock inicial de acero? (Double): ");
        STOCKACEROINICIAL = scanner.nextInt();

        stockMaderaRestante = STOCKMADERAINICIAL;
        stockacerorestante = STOCKACEROINICIAL;
    
        while (contadorEncargos < numeroEncargos && !errorMaterial) {
            contadorEncargos++;

            encargoActual = 0;
            ObjetosXEncargo = 0;

            System.out.println("----- ENCARGO " + contadorEncargos + " -----");

            while (!(encargoActual == 1 || encargoActual == 2 || encargoActual == 3)){
                System.out.print("Que objeto quiere fabricar en este encargo?: ");
                encargoActual = scanner.nextInt();
            }

            while (ObjetosXEncargo < 1) {
                System.out.print("Cuantos objetos quieres hacer?: ");
                ObjetosXEncargo = scanner.nextInt();
            }

            if (encargoActual == 1) {
                objetoEncargado = "Flecha";
                consumoMadera = consumoMaderaFlecha;
                consumoAcero = consumoAceroFlecha;
                precio = precioFelcha;
                totalFlechas += ObjetosXEncargo;
            }
            
            if (encargoActual == 2) {
                objetoEncargado = "Escudo";
                consumoMadera = consumoMaderaEscudo;
                consumoAcero = consumoAceroEscudo;
                precio = precioEscudo;
                totalEscudos += ObjetosXEncargo;
            }
            
            if (encargoActual == 3) {
                objetoEncargado = "Espada";
                consumoMadera = consumoMaderaEspada;
                consumoAcero = consumoAceroEspada;
                precio = precioEspada;
                totalEspadas += ObjetosXEncargo;
            }
            
            consumoMadera *=  ObjetosXEncargo;
            consumoAcero *= ObjetosXEncargo;

            if (stockMaderaRestante < consumoMadera) {
                System.out.println("No tienes suficiente madera");
                errorMaterial = true;
            } else {
                stockMaderaRestante -= consumoMadera;
            }
    
            if (stockacerorestante < consumoAcero) {
                System.out.println("No tienes suficiente acero");
                errorMaterial = true;
            } else {
                stockacerorestante -= consumoAcero;
            }
            
            consumoMaderaTotal += consumoMadera;
            consumoAceroTotal += consumoAcero;
            
            costeEncargo = ObjetosXEncargo * precio;
            dineroTotal += costeEncargo;

            System.out.println("Artículo: " + objetoEncargado + " | Unidades: " + ObjetosXEncargo + " | Importe cobrado: " + costeEncargo);
            System.out.println("Madera gastada: " + consumoMadera + "| Acumulada: " + consumoMaderaTotal );
            System.out.println("Acero gastado: " + consumoAcero + "| Acumulado: " + consumoAceroTotal + "\n");
        }
        scanner.close();

        System.out.println("==== RESUMEN FINAL ===");
        System.out.println("Encargos registrados: " + contadorEncargos + " de " + numeroEncargos);
        System.out.println("Unidades -> Flechas: " + totalFlechas + " | Escudos: " + totalEscudos + " | Espadas: " + totalEspadas);
        System.out.println("consumo total -> madera: " + consumoMaderaTotal + " | acero: " + consumoAceroTotal);
        System.out.println("Stock restante -> madera: " + stockMaderaRestante + " | acero: " + consumoAceroTotal);
        System.out.println("Importe Total: " + dineroTotal + " Monedas");
        
        if (errorMaterial) {
            System.out.println("Programa interrumpido por falta de recursos");
        }
    }
    
}
