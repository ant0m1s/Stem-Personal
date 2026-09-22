public class Domestico extends Animal {
    private String duenio;

    public Domestico(String nombre, String tipoAlimentacion, String duenio) {
        super(nombre, tipoAlimentacion);
        this.duenio = duenio;
    }
    public String convivirConHumanos() {
        return "El animal doméstico " + getNombre() + " convive con su dueño " + duenio + " y se alimenta de " + getTipoAlimentacion();
    }

    
}
