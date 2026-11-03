import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

//DATOS con CSV
public class GestorInscripciones {
    private static final String CABECERA = "id;persona;taller;plazas";

    // TODO 1.1 - Leer el CSV en UTF-8, ignorar cabecera y validar TODAS las filas.
    // Si el archivo no existe, devolver una lista vacía.
    // Si una fila es incorrecta o hay IDs duplicados, lanzar IOException.
    public static List<Inscripcion> cargar(Path archivo) throws IOException {
        // PASO 1: prepara la lista, recorre las filas del CSV y valida campos.
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

    // TODO 1.3 - Si el ID existe y plazas >= 0, cambiar SOLO ese registro.
    // Crear copia antes de reescribir. Si no existe o plazas < 0, devolver false.
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
        guardar(archivo, lista);
        return false;
    }

    // TODO 1.4 - Eliminar el ID solicitado y conservar todos los demás.
    // Si no existe, NO reescribir el archivo ni su copia.
    public static boolean eliminar(Path archivo, int id) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        Inscripcion item = buscar(lista, id);
        if (item == null) {
            return false;
        }
        lista.remove(item); 
        guardar(archivo, lista);
        return true;
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

    private static void hacerCopia(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Files.copy(archivo, rutaCopia(archivo), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Copia de seguridad: datos/inscripciones.csv -> datos/inscripciones.csv.bak
    private static Path rutaCopia(Path archivo) {
        return archivo.resolveSibling(archivo.getFileName() + ".bak");
    }

    // TODO 1.5 - Restaurar archivo.csv.bak sobre archivo.csv.
    // La confirmación se pide en Main, NO aquí.
    public static boolean restaurar(Path archivo) throws IOException {
        Path copia = rutaCopia(archivo);
        if (Files.notExists(copia)) {
            return false;
        }
        cargar(copia); // comprueba que la copia es válida antes de sustituir
        Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);
        return true;
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

// DATOS XML Y .DAT
public class RepositorioEquipos {

    // TODO 2.1 - Recuperar la lista serializada con ObjectInputStream.
    // Si no existe el .dat, devolver lista vacía. Comprobar tipos, IDs y duplicados.
    public static List<Equipo> cargar(Path archivo) throws IOException {
        List<Equipo> equipos = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return equipos;                                     // sin fichero -> lista vacía
        }
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {
            Object dato = entrada.readObject();                 // se lee como Object
            if (!(dato instanceof List<?> lista)) {
                throw new IOException("El archivo no contiene una lista");
            }
            for (Object elemento : lista) {
                if (!(elemento instanceof Equipo equipo)) {
                    throw new IOException("La lista contiene un elemento que no es un Equipo");
                }
                if (buscar(equipos, equipo.getId()) != null) {
                    throw new IOException("ID duplicado en el archivo: " + equipo.getId());
                }
                equipos.add(equipo);
            }
        } catch (ClassNotFoundException ex) {
            throw new IOException("No se encuentra la clase del objeto guardado", ex);
        }
        return equipos;
    }

    // TODO 2.2 - Crear carpeta y guardar la LISTA COMPLETA con ObjectOutputStream.
    // No usar APPEND en ficheros serializados.
    public static void guardar(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 2: crea la carpeta y usa ObjectOutputStream.
        // El método es void: no necesita devolver nada.
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        try (ObjectOutputStream salida = new  ObjectOutputStream(Files.newOutputStream(archivo)) {
        }) {
            salida.writeObject(new ArrayList<>(lista));
        }
    }

    // MÉTODO DE APOYO COMPLETO: busca por ID sin leer archivos.
    public static Equipo buscar(List<Equipo> lista, int id) {
        for (Equipo equipo : lista) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // TODO 2.3 - Modificar SOLO en memoria. Devolver false si ID desconocido
    // o unidades < 0. NO guardar aquí: lo hará Main en opción 4.
    public static boolean cambiarUnidades(List<Equipo> lista, int id, int unidades) {
        if (unidades < 0) {
            return false;                                       // unidades no válidas
        }
        Equipo equipo = buscar(lista, id);
        if (equipo == null) {
            return false;                                       // ID inexistente
        }
        equipo.setUnidades(unidades);                     // NO se guarda aquí
        return true;
    }

    // TODO 2.4 - Exportar DOM XML. Crear raíz <equipos> y cada <equipo id="...">.
    // Dentro, <nombre> y <unidades>. No concatenar cadenas XML manualmente.
    // Los errores de XML se convierten en IOException para simplificar Main.
    public static void exportarXml(Path archivo, List<Equipo> lista) throws IOException {
        try {
            // 1) Documento vacío y raíz <equipos>
            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element raiz = documento.createElement("equipos");
            documento.appendChild(raiz);

            // 2) Un <equipo id="..."> por objeto, con hijos <nombre> y <unidades>
            for (Equipo equipo : lista) {
                Element elemento = documento.createElement("equipo");
                elemento.setAttribute("id", String.valueOf(equipo.getId()));

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(equipo.getNombre());      // DOM escapa & como &amp;
                Element unidades = documento.createElement("unidades");
                unidades.setTextContent(String.valueOf(equipo.getUnidades()));

                elemento.appendChild(nombre);
                elemento.appendChild(unidades);
                raiz.appendChild(elemento);
            }

            // 3) Escribir el fichero con Transformer (UTF-8 + indentación)
            if (archivo.getParent() != null) {
                Files.createDirectories(archivo.getParent());
            }
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.transform(new DOMSource(documento), new StreamResult(archivo.toFile()));
        } catch (ParserConfigurationException | TransformerException ex) {
            throw new IOException("No se pudo exportar el XML: " + ex.getMessage(), ex);
        }
    }
}
