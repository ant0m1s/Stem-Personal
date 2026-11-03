import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class GestorInscripciones {

    private static final String CABECERA = "id;persona;taller;plazas";

    public static List<Inscripcion> cargar(Path archivo) throws IOException {
        List<Inscripcion> lista = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return lista;
        }
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        if (lineas.isEmpty() || !lineas.get(0).trim().equals(CABECERA)) {
            throw new IOException("Cabecera incorrecta en " + archivo);
        }
        for (int i = 1; i < lineas.size(); i++) {
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
                throw new IOException("Línea " + (i + 1) + ": datos no válidos");
            }
            if (buscar(lista, id) != null) {
                throw new IOException("Línea " + (i + 1) + ": ID repetido (" + id + ")");
            }
            lista.add(item);
        }
        return lista;
    }

    public static void guardar(Path archivo, List<Inscripcion> lista) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Inscripcion i : lista) {
            lineas.add(i.getId() + ";" + i.getPersona() + ";" + i.getTaller() + ";" + i.getPlazas());
        }
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    public static boolean esValida(Inscripcion i) {
        return i.getId() > 0
                && textoValido(i.getPersona())
                && textoValido(i.getTaller())
                && i.getPlazas() >= 0;
    }

    private static boolean textoValido(String texto) {
        return texto != null && !texto.isBlank() && !texto.contains(";");
    }

    public static Inscripcion buscar(List<Inscripcion> lista, int id) {
        for (Inscripcion i : lista) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static boolean anadir(Path archivo, Inscripcion nueva) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        if (!esValida(nueva) || buscar(lista, nueva.getId()) != null) {
            return false;
        }
        lista.add(nueva);
        hacerCopia(archivo);
        guardar(archivo, lista);
        return true;
    }

    public static boolean actualizarPlazas(Path archivo, int id, int plazas) throws IOException {
        if (plazas < 0) {
            return false;
        }
        List<Inscripcion> lista = cargar(archivo);
        Inscripcion item = buscar(lista, id);
        if (item == null) {
            return false;
        }
        item.setPlazas(plazas);
        hacerCopia(archivo);
        guardar(archivo, lista);
        return true;
    }

    public static boolean eliminar(Path archivo, int id) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        Inscripcion item = buscar(lista, id);
        if (item == null) {
            return false;
        }
        lista.remove(item);
        hacerCopia(archivo);
        guardar(archivo, lista);
        return true;
    }

    // Copia de seguridad: datos/inscripciones.csv -> datos/inscripciones.csv.bak
    private static Path rutaCopia(Path archivo) {
        return archivo.resolveSibling(archivo.getFileName() + ".bak");
    }

    private static void hacerCopia(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Files.copy(archivo, rutaCopia(archivo), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static boolean restaurar(Path archivo) throws IOException {
        Path copia = rutaCopia(archivo);
        if (Files.notExists(copia)) {
            return false;
        }
        cargar(copia); // comprueba que la copia es válida antes de sustituir
        Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }
}
