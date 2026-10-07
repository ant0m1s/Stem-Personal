import java.nio.file.Files;
import java.nio.file.Path;

public class InspectorFichero {
    public static void main(String[] args) {
        Path directorio = Path.of("datos");
        Path archivo = directorio.resolve("clubes.txt");
        
        try {
            if (Files.notExists(directorio)) {
                Files.createDirectories(directorio);
            }
            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
                System.out.println("Archivo creado correctamente");
            } else {
                System.out.println("clubes.txt existía anteriormente\n Mostrando datos: ");
                System.out.println("Ruta del archivo: " + archivo.toAbsolutePath());
                System.out.println("Tamaño: " + Files.size(archivo));
            }
        } catch (Exception e) {
            System.err.println("Error encontrado");
        }
    }
}
