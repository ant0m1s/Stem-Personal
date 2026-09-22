import java.util.ArrayList;
import java.util.List;

public class Cine {
    private static int numeroCines;

    private String nombre;
    private String ciudad;
    private String pais;
    private String codigoIdentificativo;
    private List<Sala> salas;
    private List<Pelicula> peliculas;
    private List<Cliente> clientes;

    public Cine(String nombre, String ciudad, String pais) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        asignarCodigoIdentificativo();
        this.salas = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }
    //Añade cliente al cine
    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    //Busca cliente filtrando por DNI (identificador)
    public Cliente buscarClientePorDni(String dni) {
        Cliente devolverC = null;
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                devolverC = c;
            }
        }
        return devolverC;
    }
    //Añade sala al cine
    public void agregarSala(Sala sala) {
        this.salas.add(sala);
    }
    //Añade película al cine
    public void agregarPelicula(Pelicula peli) {
        this.peliculas.add(peli);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoIdentificativo() {
        return codigoIdentificativo;
    }
    public void setCodigoIdentificativo(String codigoIdentificativo) {
        this.codigoIdentificativo = codigoIdentificativo;
    }
    
    //Establece ID para cada cine que se añada y suma 1 al contador de cines
    public void asignarCodigoIdentificativo() {
        setCodigoIdentificativo("C-" + numeroCines);
        numeroCines++;
    }
    //Busca película filtrando por su nombre
    public Pelicula buscarPorNombre(String nombre) {
        Pelicula peliculadevolver = null;
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getNombre().equalsIgnoreCase(nombre)) {
                peliculadevolver = pelicula;
            }
        }
        return peliculadevolver;
    }
    //Busca Sala por su codigo (identificador único)
    public Sala buscarSalaPorCodigo(String codigo) {
        Sala salaDevolver = null;
        for (Sala s : salas) {
            if (s.getCodigo().equals(codigo))
                salaDevolver = s;
        }
        return salaDevolver;
    }

    public Pelicula devolverPelicula(String nombrePelicula) {
        return buscarPorNombre(nombrePelicula);
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @Override
    public String toString() {
        return codigoIdentificativo + " | Cine: " + nombre + " en la ciudad " + ciudad + " de " + pais + "Número de salas: " + salas.size();
    }

}
