import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Videojuegos {
    public static void main(String[] args) {
        Path archivo = Path.of("C:\\Users\\anton\\OneDrive\\Documentos\\GitHub\\Stem-Personal\\2ºanio\\ACD\\tema1\\ejercicios\\R2\\01CatalogoVideojuegos\\videojuegos.csv");

        try {
            List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) {
                String[] campos = lineas.get(i).split(";", -1);

                if (campos.length != 3) {
                    System.out.println("Linea" + (i) + ": Número de campos incorrecto." );
                } else {
                    try {
                        int id = Integer.parseInt(campos[0].trim());
                        String titulo = campos[1].trim();
                        String plataforma = campos[2].trim();

                        System.out.println("[" + id + "] | Nombre: " + titulo + " Consola: " + plataforma);
                    } catch (NumberFormatException e) {
                        System.out.println("Línea " + (i + 1) + ": ID debe ser númerico");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
