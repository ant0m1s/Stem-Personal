public class Libro {
    private static int contador = 1;
    private int id;
    private String titulo;
    private String autor;
    private String sinopsis;
    private boolean disponible;

    Libro(String titulo, String autor, String sinopsis, boolean disponible) {
        this.id = contador;
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.disponible = disponible;
        contador++;
    }

    Libro(String titulo, String autor, boolean disponible) {
        this.id = contador;
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = "Sinopsis no disponible";
        this.disponible = disponible;
        contador++;
    }

    @Override
    public String toString() {
        return id + "º- Nombre: " + titulo + " | Autor: " + autor + " | Su sinopsis: " + sinopsis + " | Esta disponible: "
                + disponible;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
}
