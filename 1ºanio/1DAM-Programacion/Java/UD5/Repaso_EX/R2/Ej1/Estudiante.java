public class Estudiante {
    private String nombre;
    private int edad;
    private String numeroMatricula;

    Estudiante(String nombre, int edad, String numeroMatricula) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public String toString() {
        return nombre + " tiene " + edad + " años. Su nº de matrícula es: " + numeroMatricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEdad(int edad) {
        if (edad >= 17) {
            this.edad = edad;
        }
    }

    public int getEdad() {
        int edadEstudiante;
        if (edad < 17) {
            edadEstudiante = -1;
        } else {
            edadEstudiante = edad;
        }
        return edadEstudiante;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }
}
