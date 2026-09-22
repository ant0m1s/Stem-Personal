public class Salvaje extends Animal {
    private String habitat;

    public Salvaje(String nombre, String tipoAlimentacion, String habitat) {
        super(nombre, tipoAlimentacion);
        this.habitat = habitat;
    }
    public String cazar() {
        return "El animal salvaje " + getNombre() + " caza en su hábitat " + habitat + " y se alimenta de " + getTipoAlimentacion();
    }

    
}
