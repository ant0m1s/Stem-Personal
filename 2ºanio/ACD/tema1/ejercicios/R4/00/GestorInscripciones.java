
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class GestorInscripciones {

    private static final String CABECERA = "id;persona;taller;plazas";

    // TODO 1.1 - Leer el CSV en UTF-8, ignorar cabecera y validar TODAS las filas.
    // Si el archivo no existe, devolver una lista vacía.
    // Si una fila es incorrecta o hay IDs duplicados, lanzar IOException.
    public static List<Inscripcion> cargar(Path archivo) throws IOException {
        List<Inscripcion> lista = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return lista;
        }
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        if (lineas.isEmpty() || !lineas.get(0).trim().equals(CABECERA)) {
            throw new IOException("Cabecera incorrecta en " + archivo);
        }
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] campos = linea.split(";", -1);
            if (campos.length != 4) {
                throw new IOException("Línea " + (i + 1) + ": se esperaban 4 campos");
            }
            int id;
            int plazas;
            try {
                id = Integer.parseInt(campos[0].trim());
                plazas = Integer.parseInt(campos[3].trim());
            } catch (NumberFormatException e) {
                throw new IOException("Línea " + (i + 1) + ": ID o plazas no son números", e);
            }
            Inscripcion item = new Inscripcion(id, campos[1].trim(), campos[2].trim(), plazas);
            if (!esValida(item)) {
                throw new IOException("Línea \" + (i + 1) + \": datos no válidos");
            }
            lista.add(item);
        }
        return lista;
    }

    // TODO 1.2 - Buscar por ID. Devolver la inscripción o null.
    public static Inscripcion buscar(List<Inscripcion> lista, int id) {
        for (Inscripcion i : lista) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    // MÉTODO RESUELTO COMO EJEMPLO: no hay que modificarlo.
    public static boolean registrar(Path archivo, Inscripcion nueva) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        if (!esValida(nueva) || buscar(lista, nueva.getId()) != null) {
            return false;
        }
        lista.add(nueva);
        crearCopiaSiExiste(archivo);
        guardarTodo(archivo, lista);
        return true;
    }

    // TODO 1.3 - Si el ID existe y plazas >= 0, cambiar SOLO ese registro.
    // Crear copia antes de reescribir. Si no existe o plazas < 0, devolver false.
    public static boolean actualizarPlazas(Path archivo, int id, int plazas) throws IOException {
        // PASO 3: carga, busca, valida, crea copia y guarda.
        return false;
    }

    // TODO 1.4 - Eliminar el ID solicitado y conservar todos los demás.
    // Si no existe, NO reescribir el archivo ni su copia.
    public static boolean eliminar(Path archivo, int id) throws IOException {
        // PASO 4: reconstruye la lista omitiendo el ID solicitado.
        return false;
    }

    // TODO 1.5 - Restaurar archivo.csv.bak sobre archivo.csv.
    // La confirmación se pide en Main, NO aquí.
    public static boolean restaurar(Path archivo) throws IOException {
        // PASO 5: localiza .bak y cópialo sobre el CSV.
        return false;
    }

    // MÉTODOS PROPORCIONADOS. No se deben completar.
    private static boolean esValida(Inscripcion item) {
        return item != null && item.getId() > 0 && item.getPlazas() >= 0
                && !item.getPersona().isBlank() && !item.getTaller().isBlank()
                && !item.getPersona().contains(";") && !item.getTaller().contains(";")
                && !item.getPersona().contains("\n") && !item.getTaller().contains("\n");
    }

    private static void guardarTodo(Path archivo, List<Inscripcion> lista) throws IOException {
        Path padre = archivo.getParent();
        if (padre != null) {
            Files.createDirectories(padre);
        }
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Inscripcion item : lista) {
            lineas.add(item.getId() + ";" + item.getPersona() + ";"
                    + item.getTaller() + ";" + item.getPlazas());
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    private static void crearCopiaSiExiste(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Path copia = Path.of(archivo.toString() + ".bak");
            Files.copy(archivo, copia, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
