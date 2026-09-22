import java.util.ArrayList;

public class Universidad {
    ArrayList<Estudiante> alumnado;

    Universidad() {
        this.alumnado = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.alumnado.add(estudiante);
    }

    public String mostrarEstudiantes() {
        String lista = "";
        for (Estudiante estudiante : alumnado) {
            lista += estudiante.getNombre() + "\n";
        }
        return lista;
    }

    public String mostrarEstudiantesCompletos(){
        String lista = "";
        for (Estudiante estudiante : alumnado) {
            lista += estudiante + "||\n";
        }
        return lista;
    }

    public Estudiante buscarEstudiante(String numeroMatricula) {
        Estudiante estudianteEncontrado = null;
        for (Estudiante estudiante : alumnado) {
            if (estudiante.getNumeroMatricula().equals(numeroMatricula)) {
                estudianteEncontrado = estudiante;
            }
        }
        return estudianteEncontrado;
    }
}
