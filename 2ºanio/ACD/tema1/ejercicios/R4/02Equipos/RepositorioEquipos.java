
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
