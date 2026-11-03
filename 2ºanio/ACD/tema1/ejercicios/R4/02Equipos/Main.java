
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Path binario = Path.of("datos", "equipos.dat");
        Path xml = Path.of("datos", "equipos_exportados.xml");
        try (Scanner teclado = new Scanner(System.in)) {
            try {
                List<Equipo> equipos = RepositorioEquipos.cargar(binario);
                int opcion = -1;
                while (opcion != 0) {
                    System.out.println("\nARCHIVO DE EQUIPOS");
                    System.out.println("1. Listar | 2. Buscar | 3. Cambiar unidades");
                    System.out.println("4. Guardar binario | 5. Recargar binario | 6. Exportar XML | 7. Importar XML | 8. Añadir Club | 0. Salir");
                    System.out.print("Opción: ");
                    try {
                        opcion = Integer.parseInt(teclado.nextLine().trim());
                        switch (opcion) {
                            //Listar
                            case 1 -> {
                                // EJEMPLO COMPLETO: Main muestra resultados.
                                if (equipos.isEmpty()) {
                                    System.out.println("No hay equipos.");
                                } else {
                                    equipos.forEach(System.out::println);
                                }
                            }
                            //Filtrar por Id
                            case 2 -> {
                                // EJEMPLO COMPLETO: consulta en memoria.
                                for (Equipo equipo : equipos) {
                                    System.out.println(equipo.getId());
                                }
                                System.out.print("ID que buscas: ");
                                int id = Integer.parseInt(teclado.nextLine().trim());
                                Equipo encontrado = RepositorioEquipos.buscar(equipos, id);
                                System.out.println(encontrado == null ? "ID no encontrado." : encontrado);
                            }
                            //Cambiar unidades, filtrando por Id
                            case 3 -> {
                                // TODO 2.5-A: pedir ID y unidades, llamar a cambiarUnidades,
                                // e informar al usuario desde aquí (nunca desde el repositorio).
                                // Pide ID, unidades y llama al repositorio.
                                for (Equipo equipo : equipos) {
                                    System.out.println(equipo.getId());
                                }
                                System.out.print("ID del equipo: ");
                                int id = Integer.parseInt(teclado.nextLine().trim());
                                Equipo equipo = RepositorioEquipos.buscar(equipos, id);
                                if (equipo == null) {
                                    System.out.println("ID no encontrado.");
                                    break;
                                }
                                System.out.println("Unidades actuales: " + equipo.getUnidades());
                                System.out.println("Nuevas unidades: ");
                                int unidades = Integer.parseInt(teclado.nextLine().trim());
                                boolean cambiado = RepositorioEquipos.cambiarUnidades(equipos, id, unidades);
                                System.out.println(cambiado ? "Cambiado en memoria. Usa la opción 4 para guardar." : "No se modificó nada");

                            }
                            //Guardar binario
                            case 4 -> {
                                // EJEMPLO COMPLETO: guardado explícito.
                                RepositorioEquipos.guardar(binario, equipos);
                                System.out.println("Lista guardada en binario.");
                            }
                            case 5 -> {
                                // TODO 2.5-B: reemplazar equipos por cargar(binario).
                                // Recarga la lista desde el fichero.
                                equipos = RepositorioEquipos.cargar(binario);
                                System.out.println("Lista recargada desde el binario");
                            }
                            case 6 -> {
                                // TODO 2.5-C: llamar a exportarXml(xml, equipos).
                                // Exporta la lista a XML.
                                RepositorioEquipos.exportarXml(xml, equipos);
                                System.out.println("XML exportado en " + xml);
                            }
                            case 7 -> {
                                equipos = RepositorioEquipos.importarXml(xml);
                                System.out.println("Importados " + equipos.size() + " equipos. Usa la opción 4 para guardar.");
                            }
                            case 8 -> {
                                System.out.print("ID: ");
                                int id = Integer.parseInt(teclado.nextLine().trim());
                                System.out.print("Nombre: ");
                                String nombre = teclado.nextLine().trim();
                                System.out.print("Unidades: ");
                                int unidades = Integer.parseInt(teclado.nextLine().trim());
                                boolean anadido = RepositorioEquipos.anadir(equipos, new Equipo(id, nombre, unidades));
                                System.out.println(anadido ? "Añadido en memoria. Usa la opción 4 para guardar."
                                        : "No se añadió: datos no válidos o ID repetido.");
                            }
                            case 0 ->
                                System.out.println("Hasta luego.");
                            default ->
                                System.out.println("Opción desconocida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Introduce un número entero válido.");
                    } catch (IOException e) {
                        System.out.println("Error de archivo: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("No se pudo iniciar el programa: " + e.getMessage());
            }
        }
    }
}
