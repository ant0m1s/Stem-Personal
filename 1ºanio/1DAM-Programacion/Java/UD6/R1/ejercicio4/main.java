public class main {
    public static void main(String[] args) {
        Animal animal1 = new Animal("Pedro", "Pescado");
        Domestico perro = new Domestico("Bolt", "Pollo", "Amadeo");
        Salvaje zorro = new Salvaje("Zorro", "Aves", "Bosque");

        System.out.println(animal1);
        System.out.println(perro.convivirConHumanos());
        System.out.println(zorro.cazar());
    }

}
