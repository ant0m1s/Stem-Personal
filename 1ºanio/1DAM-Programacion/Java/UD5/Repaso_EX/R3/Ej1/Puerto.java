import java.util.ArrayList;
import java.util.Arrays;

public class Puerto {
    private static int numeroPuertos;
    private final static ArrayList<String> UBICACIONESVALIDAS = new ArrayList<>(
            Array.asList("CADIZ", "BARCELONA", "MALAGA", "GRANADA", "VALENCIA"));

    private String nombre;
    private String ubicacion;
    private int capacidadMaxima;
    private String telefonoContacto;
    private ArrayList<Amarre> amarres;
    private ArrayList<Embarcacion> embarcaciones;
    private ArrayList<String> matriculasAmarradas;

    public Puerto(String ubicacion, int capacidadMaxima, String telefonoContacto,
            ArrayList<Amarre> amarres, ArrayList<Embarcacion> embarcaciones, ArrayList<String> matriculasAmarradas) {
        setNombre(nombre);
        setUbicacion(ubicacion);
        setCapacidadMaxima(capacidadMaxima);
        this.telefonoContacto = telefonoContacto;
        setAmarres(amarres);
        this.embarcaciones = embarcaciones;
        if (!matriculasAmarradas.isEmpty()) {
            setMatriculasAmarradas(matriculasAmarradas);
        }
    }

    public boolean registrarEmbarcacion(Embarcacion e) {
        boolean barcoRegistrado = false;

        if (!embarcaciones.constains(e)) {
            embarcaciones.add(e);
            barcoRegistrado = true;
        }
        return barcoRegistrado;
    }

    public static int getNumeroPuertos() {
        return numeroPuertos;
    }

    public static void setNumeroPuertos(int numeroPuertos) {
        Puerto.numeroPuertos = numeroPuertos;
    }

    public static ArrayList<String> getUbicacionesvalidas() {
        return UBICACIONESVALIDAS;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = "Puerto-" + nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if (UBICACIONESVALIDAS.include(ubicacion.toUpperCase())) {
            this.ubicacion = ubicacion;
        }
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        if (amarres != null) {
            if (capacidadMaxima >= 0 && amarres.size() <= capacidadMaxima) {
                this.capacidadMaxima = capacidadMaxima;
            }
        }
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public ArrayList<Amarre> getAmarres() {
        return amarres;
    }

    public void setAmarres(ArrayList<Amarre> amarres) {
        if (amarres.size() <= capacidadMaxima) {
            this.amarres = amarres;
            for (int i = 0; i < amarres.size(); i++) {
                matriculasAmarradas.set(i, "VACIO");
            }
        }
    }

    public ArrayList<Embarcacion> getEmbarcaciones() {
        return embarcaciones;
    }

    public void setEmbarcaciones(ArrayList<Embarcacion> embarcaciones) {
        this.embarcaciones = embarcaciones;
    }

    public ArrayList<String> getMatriculasAmarradas() {
        return matriculasAmarradas;
    }

    public void setMatriculasAmarradas(ArrayList<String> matriculasAmarradas) {
        this.matriculasAmarradas = matriculasAmarradas;
    }

}
