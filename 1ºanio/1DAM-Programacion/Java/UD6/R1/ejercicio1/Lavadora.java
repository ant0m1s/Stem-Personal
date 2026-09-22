public class Lavadora extends Electrodomestico {
    private double capacidadCarga;

    public Lavadora(String nombre, String modelo, double capacidadCarga) {
        super(nombre, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    public Lavadora(String modelo, double capacidadCarga) {
        this("Sin nombre", modelo, capacidadCarga);
    }

    @Override
    public String toString() {
        return super.toString()+ " | Capacidad de Carga: " + capacidadCarga;
    }

    

}
