
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

public class RepositorioEquipos {

    // TODO 2.1 - Recuperar la lista serializada con ObjectInputStream.
    // Si no existe el .dat, devolver lista vacía. Comprobar tipos, IDs y duplicados.
    // CARGAR BINARIO
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
                if (equipo.getId() <= 0 || equipo.getNombre() == null
                        || equipo.getNombre().isBlank() || equipo.getUnidades() < 0) {
                    throw new IOException("Equipo no válido con ID " + equipo.getId());
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
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {
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

    public static boolean anadir(List<Equipo> lista, Equipo nuevo) {
        if (nuevo.getId() <= 0 || nuevo.getNombre().isBlank() || nuevo.getUnidades() < 0) {
            return false;
        } if (buscar(lista, nuevo.getId()) != null) {
            return false;
        }
        lista.add(nuevo);
        return true;
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
    //Cargar CSV cuando te pidan no detenerse ante una fila errónea
    public static List<Equipo> cargarCsvTolerante(Path archivo, List<String> errores) throws IOException {
    List<Equipo> validos = new ArrayList<>();
    if (Files.notExists(archivo)) {
        return validos;
    }
    List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
    for (int i = 1; i < lineas.size(); i++) {          // i = 1: se salta la cabecera
        int numLinea = i + 1;
        String linea = lineas.get(i);
        if (linea.isBlank()) {
            continue;
        }
        String[] campos = linea.split(";", -1);
        if (campos.length != 3) {
            errores.add("Línea " + numLinea + ": se esperaban 3 campos");
            continue;
        }
        int id;
        int unidades;
        try {
            id = Integer.parseInt(campos[0].trim());
            unidades = Integer.parseInt(campos[2].trim());
        } catch (NumberFormatException e) {
            errores.add("Línea " + numLinea + ": ID o unidades no son números");
            continue;
        }
        String nombre = campos[1].trim();
        if (id <= 0 || nombre.isEmpty() || unidades < 0) {
            errores.add("Línea " + numLinea + ": datos no válidos");
            continue;
        }
        if (buscar(validos, id) != null) {
            errores.add("Línea " + numLinea + ": ID repetido (" + id + ")");
            continue;
        }
        validos.add(new Equipo(id, nombre, unidades));
    }
    return validos;
}

    //Cuando pidan combinar datos en vez de sustituirlos
    public static int fusionar(List<Equipo> actuales, List<Equipo> importados) {
    int anadidos = 0;
    for (Equipo equipo : importados) {
        if (buscar(actuales, equipo.getId()) == null) {
            actuales.add(equipo);
            anadidos++;
        }
    }
    return anadidos;
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

    public static List<Equipo> importarXml(Path archivo) throws IOException {
        if (Files.notExists(archivo)) {
            throw new IOException("No existe el fichero " + archivo);
        }
        try {
            // 1) Abrir el XML de forma segura y convertirlo en un Document
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document documento = fabrica.newDocumentBuilder().parse(archivo.toFile());

            // 2) Comprobar la raíz <equipos>
            Element raiz = documento.getDocumentElement();
            if (!raiz.getTagName().equals("equipos")) {
                throw new IOException("La raíz del XML debe ser <equipos>");
            }

            // 3) Un Equipo por cada <equipo>, en una lista temporal
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

                if (id <= 0 || nombre.isEmpty() || uds < 0) {
                    throw new IOException("Equipo " + (i + 1) + ": datos no válidos");
                }
                if (buscar(importados, id) != null) {
                    throw new IOException("ID duplicado en el XML: " + id);
                }
                importados.add(new Equipo(id, nombre, uds));
            }
            return importados;
        } catch (ParserConfigurationException | SAXException | NumberFormatException ex) {
            throw new IOException("No se pudo importar el XML: " + ex.getMessage(), ex);
        }
    }
}
