public class Televisor extends Electrodomestico {
    private int pulgadas;

    public Televisor(String nombre, String modelo, int pulgadas) {
        super(nombre, modelo);
        this.pulgadas = pulgadas;
        System.out.println("Constructor de Televisor");
    }

    @Override
    public String toString() {
        return super.toString() + " | Pulgadas: " + pulgadas;
    }

    

}
