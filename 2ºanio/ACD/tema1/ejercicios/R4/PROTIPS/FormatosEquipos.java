import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Importar y exportar equipos en CSV, binario y XML. Solo necesita el JDK.
public class FormatosEquipos {
    private static final String CABECERA = "id;nombre;unidades";

    public static Equipo buscar(List<Equipo> lista, int id) {
        for (Equipo equipo : lista) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // Reglas comunes: ID positivo, nombre con texto y unidades no negativas.
    private static boolean datosValidos(int id, String nombre, int unidades) {
        return id > 0 && nombre != null && !nombre.isBlank() && unidades >= 0;
    }

    // Crea la carpeta del fichero si hace falta.
    private static void crearCarpeta(Path archivo) throws IOException {
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
    }

    // ==================== CSV ====================

    public static void exportarCsv(Path archivo, List<Equipo> lista) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Equipo equipo : lista) {
            String nombre = equipo.getNombre();
            if (nombre.contains(";") || nombre.contains("\n") || nombre.contains("\r")) {
                throw new IOException("El nombre del equipo " + equipo.getId() + " no cabe en un CSV");
            }
            lineas.add(equipo.getId() + ";" + nombre + ";" + equipo.getUnidades());
        }
        crearCarpeta(archivo);
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    public static List<Equipo> importarCsv(Path archivo) throws IOException {
        List<Equipo> equipos = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return equipos;
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
            if (campos.length != 3) {
                throw new IOException("Línea " + (i + 1) + ": se esperaban 3 campos");
            }
            int id;
            int unidades;
            try {
                id = Integer.parseInt(campos[0].trim());
                unidades = Integer.parseInt(campos[2].trim());
            } catch (NumberFormatException e) {
                throw new IOException("Línea " + (i + 1) + ": ID o unidades no son números", e);
            }
            String nombre = campos[1].trim();
            if (!datosValidos(id, nombre, unidades)) {
                throw new IOException("Línea " + (i + 1) + ": datos no válidos");
            }
            if (buscar(equipos, id) != null) {
                throw new IOException("Línea " + (i + 1) + ": ID repetido (" + id + ")");
            }
            equipos.add(new Equipo(id, nombre, unidades));
        }
        return equipos;
    }

    // ==================== BINARIO ====================

    public static void exportarBinario(Path archivo, List<Equipo> lista) throws IOException {
        crearCarpeta(archivo);
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {
            salida.writeObject(new ArrayList<>(lista));
        }
    }

    public static List<Equipo> importarBinario(Path archivo) throws IOException {
        List<Equipo> equipos = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return equipos;
        }
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {
            Object dato = entrada.readObject();
            if (!(dato instanceof List<?> lista)) {
                throw new IOException("El archivo no contiene una lista");
            }
            for (Object elemento : lista) {
                if (!(elemento instanceof Equipo equipo)) {
                    throw new IOException("La lista contiene un elemento que no es un Equipo");
                }
                if (!datosValidos(equipo.getId(), equipo.getNombre(), equipo.getUnidades())) {
                    throw new IOException("Equipo no válido con ID " + equipo.getId());
                }
                if (buscar(equipos, equipo.getId()) != null) {
                    throw new IOException("ID duplicado en el archivo: " + equipo.getId());
                }
                equipos.add(equipo);
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("No se encuentra la clase del objeto guardado", e);
        }
        return equipos;
    }

    // ==================== XML ====================

    public static void exportarXml(Path archivo, List<Equipo> lista) throws IOException {
        try {
            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element raiz = documento.createElement("equipos");
            documento.appendChild(raiz);

            for (Equipo equipo : lista) {
                Element elemento = documento.createElement("equipo");
                elemento.setAttribute("id", String.valueOf(equipo.getId()));

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(equipo.getNombre());
                Element unidades = documento.createElement("unidades");
                unidades.setTextContent(String.valueOf(equipo.getUnidades()));

                elemento.appendChild(nombre);
                elemento.appendChild(unidades);
                raiz.appendChild(elemento);
            }

            crearCarpeta(archivo);
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.transform(new DOMSource(documento), new StreamResult(archivo.toFile()));
        } catch (ParserConfigurationException | TransformerException e) {
            throw new IOException("No se pudo exportar el XML: " + e.getMessage(), e);
        }
    }

    public static List<Equipo> importarXml(Path archivo) throws IOException {
        if (Files.notExists(archivo)) {
            throw new IOException("No existe el fichero " + archivo);
        }
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            fabrica.setFeature("http://xml.org/sax/features/external-general-entities", false);
            fabrica.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            fabrica.setXIncludeAware(false);
            fabrica.setExpandEntityReferences(false);
            Document documento = fabrica.newDocumentBuilder().parse(archivo.toFile());

            Element raiz = documento.getDocumentElement();
            if (!raiz.getTagName().equals("equipos")) {
                throw new IOException("La raíz del XML debe ser <equipos>");
            }

            List<Equipo> importados = new ArrayList<>();
            NodeList elementos = raiz.getElementsByTagName("equipo");
            for (int i = 0; i < elementos.getLength(); i++) {
                Element elemento = (Element) elementos.item(i);
                NodeList nombres = elemento.getElementsByTagName("nombre");
                NodeList unidades = elemento.getElementsByTagName("unidades");
                if (nombres.getLength() != 1 || unidades.getLength() != 1) {
                    throw new IOException("Equipo " + (i + 1) + ": faltan campos o están repetidos");
                }
                int id = Integer.parseInt(elemento.getAttribute("id").trim());
                String nombre = nombres.item(0).getTextContent().trim();
                int uds = Integer.parseInt(unidades.item(0).getTextContent().trim());
                if (!datosValidos(id, nombre, uds)) {
                    throw new IOException("Equipo " + (i + 1) + ": datos no válidos");
                }
                if (buscar(importados, id) != null) {
                    throw new IOException("ID duplicado en el XML: " + id);
                }
                importados.add(new Equipo(id, nombre, uds));
            }
            return importados;
        } catch (ParserConfigurationException | SAXException | NumberFormatException e) {
            throw new IOException("No se pudo importar el XML: " + e.getMessage(), e);
        }
    }
}
