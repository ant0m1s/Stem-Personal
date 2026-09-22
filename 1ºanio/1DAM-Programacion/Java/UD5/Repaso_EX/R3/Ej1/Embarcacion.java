import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class Embarcacion {
    private static int numeroEmbarcaciones;
    private final static ArrayList<String> TIPOSVALIDOS = new ArrayList<>(
            Array.asList("VELERO", "LANCHA", "YATE", "CATAMARAN", "MOTO DE AGUA"));

    private String matricula;
    private String nombre;
    private double eslora;
    private String tipo;
    private String propietario;
    private int anioFabricacion;
    private double valorEstimado;

    Embarcacion(String nombre, double eslora, String tipo, String propietario, int anioFabricacion,
            double valorEstimado) {
        asignarMatricula();
        this.nombre = nombre;
        setEslora(eslora);
        setTipo(tipo);
        this.propietario = propietario;
        setAnioFabricacion(anioFabricacion);
        this.valorEstimado = valorEstimado;
    }

    Embarcacion(String nombre, double eslora, String tipo) {
        asignarMatricula();
        this.nombre = nombre;
        setEslora(eslora);
        setTipo(tipo);
        this.propietario = "SIN PROPIETARIO";
        setAnioFabricacion(2026);
        this.valorEstimado = 0.0;
    }

    private void asignarMatricula() {
        setMatricula("EMB-" + numeroEmbarcaciones);
        numeroEmbarcaciones++;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEslora() {
        return eslora;
    }

    public void setEslora(double eslora) {
        if (eslora > 0) {
            this.eslora = eslora;
        } else {
            this.eslora = -1;
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (TIPOSVALIDOS.include(tipo)) {
            this.tipo = tipo;
        }
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        if (anioFabricacion < 1950 && anioFabricacion <= Year.now().getValue()) {
            this.anioFabricacion = anioFabricacion;
        } else {
            this.anioFabricacion = 0;
        }
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public String mostrarEslora() {
        if (eslora != -1) {
            return "Eslora " + eslora;
        } else {
            return "Valor de eslora incorrecto. Actualicelo por favor";
        }
    }

    public String mostrarTipo() {
        if (tipo != null) {
            return tipo;
        } else {
            return "Tipo incorrecto. Actualicelo por favor";
        }
    }

    public int mostrarAnioFabricacion() {
        if (anioFabricacion != 0) {
            return anioFabricacion;
        } else {
            return "Año de fabricación incorrecto. Actualicelo por favor";
        }
    }

    @Override
    public String toString() {
        return nombre + " " + mostrarTipo() + " con matricula " + matricula + " de " + propietario + ". Mide "
                + mostrarEslora() + "m. Fabricado en el año " + mostrarAnioFabricacion() + ". Su valor estimado es de "
                + valorEstimado + "€.";
    }
}
