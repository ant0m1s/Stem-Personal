import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class alumnos {
    public static void main(String[] args) {
        Path archivo = Path.of("C:\\Users\\anton\\OneDrive\\Documentos\\GitHub\\Stem-Personal\\2ºanio\\ACD\\tema1\\ejercicios\\R2\\02BuscadorAlumnos\\alumnos.csv");
        int idFiltrar = 2;

        try {
            List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) {
                String [] campos = lineas.get(i).split(";", -1);

                if (campos.length != 3) {
                    System.out.println("Línea " + (i) + ": Nº de campos incorrecto");
                } else {
                    try {
                        int id = Integer.parseInt(campos[0].trim());
                        String nombre = campos[1].trim();
                        String curso = campos[2].trim();
                        if (id == idFiltrar) {
                            System.out.println("[" + id + "] | Alumno: " + nombre + " Curso: " + curso);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("La ID debe ser un número");
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
