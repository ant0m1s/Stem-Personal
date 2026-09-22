public class ejercicio1 {
    public static void main(String[] args) {
        Embarcacion barco1 = new Embarcacion("Barco1", 10, "Velero");
        Embarcacion barco2 = new Embarcacion("Manolo", 5, "Velero");

        Amarre amarre1 = new Amarre(15, "normal");
        System.out.println(amarre1);
        Amarre amarre2 = new Amarre(10, "normal");
        System.out.println(amarre2);
    }
}
