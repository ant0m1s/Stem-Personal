import java.util.Scanner;

public class pt1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca el número de encargos: ");
        int numeroEncargos = teclado.nextInt();
        teclado.nextLine();

        while (numeroEncargos <= 0) {
            System.out.print("Debes introducir un nº mayor que 0: ");
            numeroEncargos = teclado.nextInt();
            teclado.nextLine();
        }

        System.out.print("Stock de madera (en varas): ");
        int stockMadera = teclado.nextInt();
        teclado.nextLine();

        while (stockMadera <= 0) {
            System.out.print("Debes introducir un nº mayor que 0: ");
            stockMadera = teclado.nextInt();
            teclado.nextLine();
        }

        System.out.print("Stock de acero (en kg): ");
        double stockAcero = teclado.nextInt();
        teclado.nextLine();

        while (stockAcero <= 0.0) {
            System.out.print("Debes introducir un nº mayor que 0: ");
            stockAcero = teclado.nextInt();
            teclado.nextLine();
        }

        int maderaGastada = 0, maderaGastadaAcumulada = 0;
        double aceroGastado = 0, aceroGastadoAcumulado = 0;
        int importe = 0;
        int importeTotal = 0;

        int flechasVendidas = 0, escudosVendidos = 0, espadasVendidas = 0;
        int stockMaderaRestante = 0;
        double stockAceroRestante = 0;
        int encargosRegistrados = 0;

        final int MADERAFLECHA = 1, MADERAESCUDO = 3, MADERAESPADA = 1;
        final double ACEROFLECHA = 0.05, ACEROESCUDO = 1.5, ACEROESPADA = 1.2;
        final int PRECIOFLECHA = 2, PRECIOESCUDO = 30, PRECIOESPADA = 25;

        boolean stockSuperado = false;

        for (int i = 1; i <= numeroEncargos && !stockSuperado; i++) {
            System.out.println("---- Encargo " + i + " ----");

            System.out.print("Tipo de articulo (flecha, escudo o espada): ");
            String tipoArticulo = teclado.next().toLowerCase();
            teclado.nextLine();

            while (!tipoArticulo.equals("flecha") && !tipoArticulo.equals("escudo") && !tipoArticulo.equals("espada")) {
                System.out.print("Debes introducir uno de estos articulos (flecha, escudo o espada): ");
                tipoArticulo = teclado.next().toLowerCase();
                teclado.nextLine();
            }

            System.out.print("Número de unidades (> 0): ");
            int unidades = teclado.nextInt();
            teclado.nextLine();

            while (unidades <= 0) {
                System.out.print("Número de unidades (> 0): ");
                unidades = teclado.nextInt();
                teclado.nextLine();
            }
            
            if (tipoArticulo.equals("flecha")) {
                maderaGastada = unidades * MADERAFLECHA;
                aceroGastado = unidades * ACEROFLECHA;
                
                if (maderaGastada + maderaGastadaAcumulada > stockMadera) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de madera");
                } else if (aceroGastado + aceroGastadoAcumulado > stockAcero) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de acero");
                } else {
                    maderaGastadaAcumulada += maderaGastada;
                    aceroGastadoAcumulado += aceroGastado;
                    
                    importe = unidades * PRECIOFLECHA;
                    flechasVendidas += unidades;
                }
                
            } else if (tipoArticulo.equals("escudo")) {
                maderaGastada = unidades * MADERAESCUDO;
                aceroGastado = unidades * ACEROESCUDO;
                
                if (maderaGastada + maderaGastadaAcumulada > stockMadera) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de madera");
                } else if (aceroGastado + aceroGastadoAcumulado > stockAcero) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de acero");
                } else {
                    maderaGastadaAcumulada += maderaGastada;
                    aceroGastadoAcumulado += aceroGastado;
                    
                    importe = unidades * PRECIOESCUDO;
                    escudosVendidos += unidades;
                }
            } else if (tipoArticulo.equals("espada")) {
                maderaGastada = unidades * MADERAESPADA;
                aceroGastado = unidades * ACEROESPADA;
                
                if (maderaGastada + maderaGastadaAcumulada > stockMadera) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de madera");
                } else if (aceroGastado + aceroGastadoAcumulado > stockAcero) {
                    stockSuperado = true;
                    System.out.println("Alertaaa!! Has superado el stock de acero");
                } else {
                    maderaGastadaAcumulada += maderaGastada;
                    aceroGastadoAcumulado += aceroGastado;
                    
                    importe = unidades * PRECIOESPADA;
                    espadasVendidas += unidades;
                }
            }
            
            if (!stockSuperado) {
                encargosRegistrados++;
                importeTotal += importe;
                
                System.out.println("Artículo: " + tipoArticulo + " | Unidades: " + unidades + " | Importe cobrado: " + importe + " monedas");
                System.out.println("Madera gastada: " + maderaGastada + " varas | Acumulada: " + maderaGastadaAcumulada);
                System.out.println("Acero gastado: " + aceroGastado + " kg | Acumulado: " + aceroGastadoAcumulado);
            }
        }
        teclado.close();
        
        stockMaderaRestante = stockMadera - maderaGastadaAcumulada;
        stockAceroRestante = stockAcero - aceroGastadoAcumulado;
        
        System.out.println("==== RESUMEN FINAL ====");
        System.out.println("Encargos registrados: " + encargosRegistrados + " de " + numeroEncargos);
        System.out.println("Unidades -> Flecha: " + flechasVendidas + " | Escudo: " + escudosVendidos + " | Espada: " + espadasVendidas);
        System.out.println("Consumo total -> Madera: " + maderaGastadaAcumulada + " varas | Acero: " + aceroGastadoAcumulado + " kg");
        System.out.println("Stock restante -> Madera: " + stockMaderaRestante + " varas | Acero: " + stockAceroRestante + " kg");
        System.out.println("Importe total: " + importeTotal + " monedas");

        if(!stockSuperado){
            System.out.println("Registro completado con éxito");
        } else{
            System.out.println("Registro interrumpido por superación de stock");
        }

    }
}
