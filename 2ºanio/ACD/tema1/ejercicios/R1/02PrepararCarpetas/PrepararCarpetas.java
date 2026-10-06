import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {
    public static void main(String[] args) {
        Path rutaPadre = Path.of("datos");
        Path copias = rutaPadre.resolve("copias");
        Path archivo = rutaPadre.resolve("clubes.txt");
        Path respaldo = copias.resolve("respaldo.txt");
        
        try {
            Files.createDirectories(copias);
            if (Files.notExists(archivo)) {
                System.out.println("Archivo clubes.txt creado correctamente");
                Files.createFile(archivo);
            }
            if (Files.notExists(respaldo)) {
                System.out.println("Archivo respaldo.txt creado correctamente");
                Files.createFile(respaldo);
            }

            System.out.println("Clubes.txt");
            System.out.println("Existe: " + Files.exists(archivo));
            System.out.println("Su tamaño es: " + Files.size(archivo) + " bytes");
            System.out.println("Respaldo.txt");
            System.out.println("Existe: " + Files.exists(respaldo));
            System.out.println("Su tamaño es: " + Files.size(respaldo) + " bytes");


        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
