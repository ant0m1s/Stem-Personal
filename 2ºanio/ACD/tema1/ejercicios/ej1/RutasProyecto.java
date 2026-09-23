import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.sound.midi.Patch;

public class RutasProyecto {
    public static void main(String[] args) {
        Path ruta = Path.of("datos");
        Path archivo = ruta.resolve("clubes.txt");
        Path copias = ruta.resolve("copias");

        try {
            Files.createDirectory(ruta);
            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
            }
            if (Files.notExists(copias)) {
                Files.createDirectory(copias);
            }

            System.out.println("Ruta absoluta Datos: " + ruta.toAbsolutePath());
            System.out.println("Ruta absoluta Archivo: " + archivo.toAbsolutePath());
            System.out.println("Ruta absoluta Copias: " + copias.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("No se pudo acceder al archivo: " + e.getMessage());
        }
    }
}
