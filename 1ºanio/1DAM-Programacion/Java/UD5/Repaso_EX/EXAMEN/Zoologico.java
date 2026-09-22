import java.util.ArrayList;

public class Zoologico {
    ArrayList<Animal> animales;

    Zoologico() {
        this.animales = new ArrayList<>();
    }

    public String mostrarAnimales() {
        if (animales.isEmpty()) {
            return "No hay animales disponibles";
        }
        String lista = "";
        for (Animal animal : animales) {
            lista += animal + "\n";
        }
        return lista;
    }

    public Animal buscarPorNombre(String codigo) {
        Animal mostrarAnimal = null;
        for (Animal animal : animales) {
            if (animal.getCodigo().equals(codigo)) {
                mostrarAnimal = animal;
            }
        }
        return mostrarAnimal;
    }

    public void agregarAnimal(Animal animalIntroducir) {
        this.animales.add(animalIntroducir);
    }

    public Animal eliminarAnimal(String codigo) {
        Animal animalEncontrado = buscarPorNombre(codigo);
        if (animalEncontrado != null) {
            animales.remove(animalEncontrado);
        }
        return animalEncontrado;
    }
}
