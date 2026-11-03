import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Importar y exportar equipos en JSON. Necesita Maven y jackson-databind.
public class EquiposJson {
    // Equipo no tiene constructor vacío, así que Jackson trabaja con este record.
    // Integer, y no int, para que un campo ausente llegue como null y se detecte.
    public record EquipoJson(Integer id, String nombre, Integer unidades) {}

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void exportarJson(Path archivo, List<Equipo> lista) throws IOException {
        List<EquipoJson> datos = new ArrayList<>();
        for (Equipo equipo : lista) {
            datos.add(new EquipoJson(equipo.getId(), equipo.getNombre(), equipo.getUnidades()));
        }
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(archivo.toFile(), datos);
    }

    public static List<Equipo> importarJson(Path archivo) throws IOException {
        if (Files.notExists(archivo)) {
            throw new IOException("No existe el fichero " + archivo);
        }
        List<EquipoJson> leidos = MAPPER.readValue(archivo.toFile(),
                new TypeReference<List<EquipoJson>>() {});

        List<Equipo> importados = new ArrayList<>();
        for (EquipoJson dato : leidos) {
            if (dato == null || dato.id() == null || dato.nombre() == null || dato.unidades() == null
                    || dato.id() <= 0 || dato.nombre().isBlank() || dato.unidades() < 0) {
                throw new IOException("Datos no válidos en el JSON: " + dato);
            }
            for (Equipo anterior : importados) {
                if (anterior.getId() == dato.id()) {
                    throw new IOException("ID duplicado en el JSON: " + dato.id());
                }
            }
            importados.add(new Equipo(dato.id(), dato.nombre().trim(), dato.unidades()));
        }
        return importados;
    }
}
