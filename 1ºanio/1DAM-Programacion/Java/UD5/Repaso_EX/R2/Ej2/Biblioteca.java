import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> libros;

    Biblioteca() {
        this.libros = new ArrayList<>();
    }

    public String mostrarLibros() {
        String lista = "";
        for (Libro libro : libros) {
            lista += libro.getTitulo() + " | Estado: " + libro.getDisponible() + "\n";
        }
        return lista;
    }

    public void agregarLibro(Libro libroIntroducir) {
        this.libros.add(libroIntroducir);
    }

    public Libro buscarPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Libro devolverLibro(String titulo) {
        Libro libroEncontrado = null;
        Libro libro = buscarPorTitulo(titulo);
        if (libro.getDisponible().equals(false) && libro != null) {
            libro.setDisponible(true);
            libroEncontrado = libro;
        }
        return libroEncontrado;
    }

    public Libro prestarLibro(String titulo) {
        Libro libroEncontrado = null;
        Libro libro = buscarPorTitulo(titulo);
        if (libro.getDisponible().equals(true) && libro != null) {
            libro.setDisponible(false);
            libroEncontrado = libro;
        }
        return libroEncontrado;
    }
}
