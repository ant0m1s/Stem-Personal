public class ejercicio1 {
    public static void main(String[] args) {
        Electrodomestico cafetera = new Electrodomestico("Nespresso", "Whats else");
        Televisor chaomi = new Televisor("Chaomi", "LX458", 55);
        Lavadora boch = new Lavadora("Boch", "Lavamucho 3000", 5.5);
        Lavadora boch1 = new Lavadora( "No Lavamucho 3000", 5.5);

        System.out.println(cafetera);
        System.out.println(chaomi);
        System.out.println(boch);
        System.out.println(boch1);
    }
}
