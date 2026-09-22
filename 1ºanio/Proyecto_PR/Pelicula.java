public class Pelicula {
    private static int numeroPeliculas;

    private String codigoIdentificativo;
    private String nombre;
    private int duracionMinutos;
    private String clasificacionEdad;
    private String genero;
    
    public Pelicula(String nombre, int duracionMinutos, String clasificacionEdad, String genero) {
        asignarCodigoIdentificativo();
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.clasificacionEdad = clasificacionEdad;
        this.genero = genero;
    }

    public static int getNumeroPelicula() {
        return numeroPeliculas;
    }

    public static void setNumeroPelicula(int numeroPelicula) {
        Pelicula.numeroPeliculas = numeroPelicula;
    }

    public String getCodigoIdentificativo() {
        return codigoIdentificativo;
    }

    public void setCodigoIdentificativo(String codigoIdentificativo) {
        this.codigoIdentificativo = codigoIdentificativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    //Crea un ID para cada película que se añada y aumenta el contador para siguiente película
    public void asignarCodigoIdentificativo() {
        setCodigoIdentificativo("P-" + numeroPeliculas);
        numeroPeliculas++;
    }

    @Override
    public String toString() {
        return codigoIdentificativo + " | Pelicula: " + nombre + " con una duracion de "
                + duracionMinutos + " minutos | Genero: " + genero + " | Clasificación por edad: " + clasificacionEdad + " años";
    }

    


}
