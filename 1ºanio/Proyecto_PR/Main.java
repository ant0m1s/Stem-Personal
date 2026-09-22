import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Formato general para hora y fecha
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Muestra mensaje en consola
    static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra prompt para usuario
    static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    // Muestra menú y pide opción a usuario
    static int mostrarMenuYLeerOpcion(Scanner sc, String menu) {
        imprimirMensaje(menu);
        imprimirPrompt("Opción: ");
        int opcion = leerEntero(sc);
        return opcion;
    }

    // Comprueba que usuario introducio int
    static int leerEntero(Scanner sc) {
        int numero = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            String texto = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                numeroValido = true;
            } catch (NumberFormatException e) {
                imprimirPrompt("Debes introducir un número entero: ");
            }
        }
        return numero;
    }
    //Filtra los diferentes países para el cine
    static boolean esPaisValido(String filtrarPais) {
        boolean resultado = filtrarPais.equals("alemania") ||
                filtrarPais.equals("francia") ||
                filtrarPais.equals("reino unido") ||
                filtrarPais.equals("italia") ||
                filtrarPais.equals("espania");
        return resultado;
    }
    //Clasificaciones disponibles para cada película
    static boolean esClasificacionValida(String filtrarClasificacion) {
        boolean resultado = filtrarClasificacion.equals("+7") ||
                filtrarClasificacion.equals("+12") ||
                filtrarClasificacion.equals("+16") ||
                filtrarClasificacion.equals("+18");
        return resultado;
    }
    //Generos disponibles para cada película
    static boolean esGeneroValido(String filtrarGenero) {
        boolean resultado = filtrarGenero.equalsIgnoreCase("accion") ||
                filtrarGenero.equalsIgnoreCase("comedia") ||
                filtrarGenero.equalsIgnoreCase("drama") ||
                filtrarGenero.equalsIgnoreCase("terror") ||
                filtrarGenero.equalsIgnoreCase("ciencia ficción") ||
                filtrarGenero.equalsIgnoreCase("otro");
        return resultado;
    }
    //Tipos de entradas disponibles
    static boolean esEntradaValida(String filtrarEntrada) {
        boolean resultado = filtrarEntrada.equalsIgnoreCase("normal") ||
                filtrarEntrada.equalsIgnoreCase("3D") ||
                filtrarEntrada.equalsIgnoreCase("Imax") ||
                filtrarEntrada.equalsIgnoreCase("reducida");
        return resultado;
    }

    static Cine buscarCine(List<Cine> cines, String nombre) {
        Cine cineTemp = null;
        for (Cine cine : cines) {
            if (cine.getNombre().equalsIgnoreCase(nombre)) {
                cineTemp = cine;
            }
        }
        return cineTemp;
    }
    //Recibe una fecha una fecha y la comprueba
    static LocalDateTime leerFecha(Scanner sc) {
        while (true) {
            String texto = sc.nextLine().trim();
            try {
                return LocalDateTime.parse(texto, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                imprimirPrompt("Formato incorrecto. Usa dd/MM/yyyy HH:mm: ");
            }
        }
    }
    //Muestra todo tipo de listas
    static <T> void mostrarLista(List<T> lista) {
        if (lista.isEmpty()) {
            imprimirMensaje("No hay elementos registrados");
        } else {
            for (T elemento : lista) {
                System.out.println(elemento);
            }

        }
    }
    //Recibe lista de proyecciones y dentro de ella busca por el codigo que recibe (parámetro)
    static Proyeccion buscarProyeccion(List<Proyeccion> proyecciones, String codigo) {
        Proyeccion resultado = null;
        for (Proyeccion p : proyecciones) {
            if (p.getCodigoIdentificativo().equalsIgnoreCase(codigo)) {
                resultado = p;
            }
        }
        return resultado;
    }
    //Recorre proyecciones (recibe lista de proyecciones) y pide codigo de proyección para buscar
    static Proyeccion pedirYBuscarProyeccion(Scanner sc, List<Proyeccion> proyecciones) {
        for (Proyeccion p : proyecciones) {
            System.out.println(p.getCodigoIdentificativo() + " || " + p.getPelicula().getNombre());
        }
        imprimirPrompt("Codigo de la proyección: ");
        String codigo = sc.nextLine().trim();
        return buscarProyeccion(proyecciones, codigo);
    }
    //Busca pelicula por su codigo
    static Pelicula buscarPeliculaPorCodigo(List<Pelicula> peliculas, String codigo) {
        Pelicula resultado = null;
        for (Pelicula p : peliculas) {
            if (p.getCodigoIdentificativo().equalsIgnoreCase(codigo)) {
                resultado = p;
            }
        }
        return resultado;
    }
    //Pide datos para un nuevo cliente y comprueba que DNI (identificador) no se haya repetido
    static Cliente crearCliente(Scanner sc, List<Cine> cines) {
        imprimirPrompt("Nombre del cliente (Nombre y apellidos): ");
        String nombre = sc.nextLine().trim();
        boolean dniRepetido = true;
        String dni = "";
        while (dniRepetido) {
            dniRepetido = false;
            imprimirPrompt("DNI: ");
            dni = sc.nextLine().trim();
            for (Cine c : cines) {
                if (c.buscarClientePorDni(dni) != null) {
                    dniRepetido = true;
                    imprimirMensaje("DNI ya registrado, vuelve a introducir otro");
                }
            }
        }
        imprimirPrompt("Edad: ");
        int edad = leerEntero(sc);
        String tipoEntrada = "";
        do {
            imprimirPrompt("Tipo de entrada (normal, reducida, 3D y IMAX): ");
            tipoEntrada = sc.nextLine().trim().toLowerCase();
            if (!esEntradaValida(tipoEntrada)) {
                imprimirMensaje("Entrada no valida");
            }
        } while (!esEntradaValida(tipoEntrada));
        return new Cliente(nombre, dni, edad, tipoEntrada);
    }
    //Recibe lista de cines y dentro busca si el cliente existe (filtrando por DNI, recibido en parámetros)
    static Cliente buscarClienteEnCines(List<Cine> cines, String dni) {
        Cliente resultado = null;
        for (Cine cine : cines) {
            Cliente encontrado = cine.buscarClientePorDni(dni);
            if (encontrado != null) {
                resultado = encontrado;
            }
        }
        return resultado;
    }
    // MAIN PROGRAMA
    // Al empezar, pide datos del primer cine y carga datos de prueba (3 películas, 2 salas, 3 clientes, 3 proyecciones).

    // OPCIÓN 1 - Gestionar Cines: Listar cines / Ver detalles de un cine
    // OPCIÓN 2 - Gestionar Salas: Añadir sala / Listar salas / Ver disponibilidad / Programar/Cancelar proyección
    // OPCIÓN 3 - Gestionar Películas: Añadir / Listar / Ver detalles / Ver sus proyecciones
    // OPCIÓN 4 - Gestionar Clientes:  Registrar cliente / Cancelar asistencia / Ver asistentes / Registrar cliente en proyección (elige butaca)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Cine> cines = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Pelicula> peliculas = new ArrayList<>();
        List<Proyeccion> proyecciones = new ArrayList<>();

        final String MENUPRINCIPAL = "-------Menu Principal---------\n"
                + "1. Gestionar Cines\n"
                + "2. Gestionar Salas\n"
                + "3. Gestionar Peliculas\n"
                + "4. Gestionar Clientes\n"
                + "5. Salir";

        final String MENUCINE = "-------Menu Cine---------\n"
                + "1. Listar cines\n"
                + "2. Ver detalles de un cine\n"
                + "3. Volver al menú principal";

        final String MENUSALA = "-------Menu Sala---------\n"
                + "1. Añadir Sala\n"
                + "2. Listar salas de un cine\n"
                + "3. Consultar disponibilidad de una sala\n"
                + "4. Programar película en una sala\n"
                + "5. Cancelar Proyección\n"
                + "6. Volver al menú principal";

        final String MENUPELICULA = "-------Menu Pelicula---------\n"
                + "1. Añadir Pelicula\n"
                + "2. Listar Películas\n"
                + "3. Ver detalles de una película\n"
                + "4. Ver proyecciones de una película\n"
                + "5. Volver al menú principal";

        final String MENUCLIENTES = "-------Menu Clientes---------\n"
                + "1. Registrar un cliente nuevo\n"
                + "2. Cancelar asistencia\n"
                + "3. Ver lista de clientes en una proyección\n"
                + "4. Ver número total de asistentes\n"
                + "5. Registrar cliente en una proyección\n"
                + "6. Volver al menú principal\n"
                + "9. Mostrar porcentaje de ocupación de una proyección";

        final String MENUELEGIRCLIENTE = "¿Quieres usar un cliente existente o crear uno nuevo?\n"
                + "1. Usar cliente existente\n"
                + "2. Crear cliente nuevo";

        int eleccion = 0;
        boolean funcionar = true;
        int numIterraciones = 0;

        while (funcionar) {
            if (numIterraciones == 0) {
                imprimirPrompt("Dime el nombre del cine: ");
                String nombreCine = sc.nextLine();
                String paisCine = "";
                do {
                    imprimirPrompt("¿En que país estamos (Alemania, Francia, Reino Unido, Italia y Espania): ");
                    paisCine = sc.nextLine().trim().toLowerCase();
                    if (!esPaisValido(paisCine)) {
                        imprimirMensaje("País no valido");
                    }
                } while (!esPaisValido(paisCine));
                imprimirPrompt("¿En que ciudad se situa?: ");
                String ciudadCine = sc.nextLine();
                Cine cine1 = new Cine(nombreCine, ciudadCine, paisCine);
                cines.add(cine1);
                imprimirMensaje("Cine añadido.");

                // ------------DATOS DE PRUEBA----------------

                Pelicula p1 = new Pelicula("Origen", 148, "+12", "ciencia ficción");
                Pelicula p2 = new Pelicula("Kill Bill", 111, "+18", "accion");
                Pelicula p3 = new Pelicula("Terrifier", 96, "+18", "terror");

                peliculas.add(p1);
                peliculas.add(p2);
                peliculas.add(p3);
                for (Cine cine : cines) {
                    cine.agregarPelicula(p1);
                    cine.agregarPelicula(p2);
                    cine.agregarPelicula(p3);
                }

                Sala s1 = new Sala();
                Sala s2 = new Sala();
                salas.add(s1);
                salas.add(s2);
                for (Cine cine : cines) {
                    cine.agregarSala(s1);
                    cine.agregarSala(s2);
                }

                // Clientes
                Cliente c1 = new Cliente("Juan de dios Alaminos", "12345678A", 21, "normal");
                Cliente c2 = new Cliente("Marino Fernández", "87654321B", 21, "reducida");
                Cliente c3 = new Cliente("Martina Romero", "91661111C", 17, "normal");
                for (Cine cine : cines) {
                    cine.agregarCliente(c1);
                    cine.agregarCliente(c2);
                    cine.agregarCliente(c3);
                }

                // Proyecciones
                LocalDateTime inicio1 = LocalDateTime.now().plusHours(1);
                LocalDateTime inicio2 = LocalDateTime.now().plusHours(3);
                LocalDateTime inicio3 = LocalDateTime.now().plusDays(1);

                s1.programarProyeccion(p1, inicio1);
                s2.programarProyeccion(p2, inicio2);
                s1.programarProyeccion(p3, inicio3);

                proyecciones.add(s1.getProyecciones().get(0));
                proyecciones.add(s2.getProyecciones().get(0));
                proyecciones.add(s1.getProyecciones().get(1));

                imprimirMensaje("(DATOS DE PRUEBA AÑADIDOS)");
                // -------------------------------------------
            }

            eleccion = mostrarMenuYLeerOpcion(sc, MENUPRINCIPAL);
            if (eleccion == 5) {
                funcionar = false;
                imprimirMensaje("Saliendo del programa...");
            } else if (eleccion == 1) {// Gestión Cines
                boolean funcionarCine = true;
                int eleccionCine = 0;
                while (funcionarCine) {
                    eleccionCine = mostrarMenuYLeerOpcion(sc, MENUCINE);
                    if (eleccionCine == 3) {// Volver al menú anterior
                        funcionarCine = false;
                    } else if (eleccionCine == 1) {// Mostrar todos los cines
                        mostrarLista(cines);
                    } else if (eleccionCine == 2) {// Ver detalles de un cine
                        imprimirPrompt("Nombre del cine: ");
                        Cine cine = buscarCine(cines, sc.nextLine().trim());
                        if (cine == null) {
                            imprimirMensaje("Cine no encontrado.");
                        } else {
                            String mensaje = "- Código: " + cine.getCodigoIdentificativo() + "\n- Ciudad: "
                                    + cine.getCiudad() + "\n- País: " + cine.getPais() + "\n- Número de salas: "
                                    + cine.getSalas().size();
                            imprimirMensaje(mensaje);
                        }
                    } else {
                        imprimirMensaje("Opción no válida");
                    }
                }

            } else if (eleccion == 2) {// Gestión Salas
                boolean funcionarSala = true;
                int eleccionSala = 0;
                while (funcionarSala) {
                    eleccionSala = mostrarMenuYLeerOpcion(sc, MENUSALA);
                    if (eleccionSala == 6) {// Volver al menú anterior
                        funcionarSala = false;
                    } else if (eleccionSala == 1) {// Añadir Sala
                        if (cines.isEmpty()) {
                            imprimirMensaje("No puedes añadir salas si no hay cines registrados");
                        } else {
                            imprimirPrompt("Nombre del cine donde añadir la sala: ");
                            String cineBuscar = sc.nextLine().trim();
                            Cine cineElegido = buscarCine(cines, cineBuscar);
                            if (cineElegido == null) {
                                imprimirMensaje("Cine no encontrado");
                            } else {
                                Sala sala1 = new Sala();
                                cineElegido.agregarSala(sala1);
                                salas.add(sala1);
                                imprimirMensaje("Sala añadida correctamente");
                            }

                        }
                    } else if (eleccionSala == 2) {// Listar salas de un cine
                        imprimirPrompt("Nombre del cine para mostrar sus salas: ");
                        String cineBuscar = sc.nextLine().trim();
                        Cine cineElegido = buscarCine(cines, cineBuscar);
                        if (cineElegido == null) {
                            imprimirMensaje("Cine no encontrado");
                        } else if (cineElegido.getSalas().isEmpty()) {
                            imprimirMensaje("No hay salas registradas");
                        } else {
                            mostrarLista(cineElegido.getSalas());
                        }
                    } else if (eleccionSala == 3) {// Disponibilidad de una sala
                        imprimirPrompt("Nombre del cine: ");
                        String cineB = sc.nextLine().trim();
                        Cine cine = buscarCine(cines, cineB);
                        if (cine == null) {
                            imprimirMensaje("Cine no encontrado");
                        } else if (cine.getSalas().isEmpty()) {
                            imprimirMensaje("Este cine no tiene salas.");
                        } else {
                            mostrarLista(cine.getSalas());
                            imprimirPrompt("Codigo de la sala: ");
                            String codigoSala = sc.nextLine().trim();
                            Sala sala = cine.buscarSalaPorCodigo(codigoSala);
                            if (sala == null) {
                                imprimirMensaje("Sala no encontrada");
                            } else {
                                if (sala.estaDisponibleAhora()) {
                                    imprimirMensaje("Sala " + sala.getCodigo() + " esta *DISPONIBLE* en este momento");
                                } else {
                                    imprimirMensaje("Sala " + sala.getCodigo() + " esta *OCUPADA* en este momento");
                                }
                            }
                        }
                    } else if (eleccionSala == 4) {// Programar proyección de película
                        if (cines.isEmpty()) {
                            imprimirMensaje("No puedes añadir salas si no hay cines registrados");
                        } else if (peliculas.isEmpty()) {
                            imprimirMensaje("No hay peliculas registradas");
                        } else {
                            imprimirPrompt("Nombre del cine: ");
                            Cine cine = buscarCine(cines, sc.nextLine().trim());
                            if (cine == null) {
                                imprimirMensaje("Cine no encontrado");
                            } else if (cine.getSalas().isEmpty()) {
                                imprimirMensaje("Este cine no tiene salas.");
                            } else {
                                imprimirMensaje("Salas del cine: ");
                                mostrarLista(cine.getSalas());
                                imprimirPrompt("Codigo de la sala: ");
                                String codigoSala = sc.nextLine().trim();
                                Sala salaBuscar = cine.buscarSalaPorCodigo(codigoSala);
                                if (salaBuscar == null) {
                                    imprimirMensaje("Sala no encontrada");
                                } else {
                                    imprimirMensaje("Peliculas disponibles: ");
                                    for (Pelicula pelicula : cine.getPeliculas()) {
                                        System.out.println(pelicula);
                                    }
                                    imprimirPrompt("Nombre de la pelicula: ");
                                    String nombrePeli = sc.nextLine().trim();
                                    Pelicula pelicula = cine.buscarPorNombre(nombrePeli);
                                    if (pelicula == null) {
                                        imprimirMensaje("Pelicula no encontrada");
                                    } else {
                                        imprimirPrompt("Fecha y hora de inicio (dd/MM/yyyy HH:mm): ");
                                        LocalDateTime inicio = leerFecha(sc);
                                        String mensaje = salaBuscar.programarProyeccion(pelicula, inicio);
                                        if (mensaje.equalsIgnoreCase("ok")) {
                                            List<Proyeccion> proyecc = salaBuscar.getProyecciones();
                                            proyecciones.add(proyecc.get(proyecc.size() - 1));
                                            imprimirMensaje(("Proyección programada correctamente"));
                                        } else if (mensaje.equalsIgnoreCase("No puedes iniciar una pelicula entre el tramo horario de las 3 y 6 de la mañana")) {
                                            imprimirMensaje("No puedes iniciar una pelicula entre el tramo horario de las 3 y 6 de la mañana");
                                        } else {
                                            imprimirMensaje("Sala esta ocupada");
                                        }
                                    }
                                }
                            }
                        }
                    } else if (eleccionSala == 5) {// Cancelar Proyección
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            imprimirPrompt("Nombre del cine: ");
                            String nombreCine = sc.nextLine().trim();
                            Cine cine = buscarCine(cines, nombreCine);
                            if (cine == null) {
                                imprimirMensaje("Cine no encontrado");
                            } else if (cine.getSalas().isEmpty()) {
                                imprimirMensaje("El cine no tiene salas registradas");
                            } else {
                                imprimirMensaje("Salas del cine: ");
                                mostrarLista(salas);
                                imprimirPrompt("Código de la sala: ");
                                Sala salaB = cine.buscarSalaPorCodigo(sc.nextLine().trim());
                                if (salaB == null) {
                                    imprimirMensaje("Sala no encontrada");
                                } else if (salaB.getProyecciones().isEmpty()) {
                                    imprimirMensaje("Esta sala no tiene proyecciones programadas");
                                } else {
                                    imprimirMensaje("Proyecciones de la sala: ");
                                    for (Proyeccion proyeccion : salaB.getProyecciones()) {
                                        System.out.println(proyeccion);
                                    }
                                    imprimirPrompt("Codigo de la proyeccion: ");
                                    String proyecCod = sc.nextLine().trim();
                                    boolean borrado = false;
                                    for (int i = 0; i < proyecciones.size(); i++) {
                                        if (proyecciones.get(i).getCodigoIdentificativo().equalsIgnoreCase(proyecCod)) {
                                            Proyeccion proyeccionBorrar = proyecciones.get(i);
                                            if (LocalDateTime.now().isAfter(proyeccionBorrar.getFechaFin())) {
                                                imprimirMensaje(
                                                        "No se puede cancelar una proyección que ya ha finalizado");
                                            } else {
                                                salaB.cancelarProyeccion(proyecCod);
                                                imprimirMensaje("Proyección cancelada: " + proyeccionBorrar);
                                                proyecciones.remove(i);
                                            }
                                            borrado = true;
                                        }
                                    }
                                    if (!borrado) {
                                        imprimirMensaje("No se encontró la proyección deseada");
                                    }
                                }
                            }
                        }
                    } else {
                        imprimirMensaje("Opción no válida");
                    }
                }
            } else if (eleccion == 3) {// Gestión Películas
                boolean funcionarPelicula = true;
                int eleccionPelicula = 0;
                while (funcionarPelicula) {
                    eleccionPelicula = mostrarMenuYLeerOpcion(sc, MENUPELICULA);
                    if (eleccionPelicula == 5) {// Volver al menú anterior
                        funcionarPelicula = false;
                    } else if (eleccionPelicula == 1) {// Añadir película
                        imprimirPrompt("Nombre de la pelicula: ");
                        String nombrePelicula = sc.nextLine();
                        imprimirPrompt("Duración (en minutos): ");
                        int duracionPelicula = leerEntero(sc);
                        String clasiPelicula = "";
                        String generoPelicula = "";
                        do {
                            imprimirPrompt("Clasificación por edad (+7, +12, +16 y +18): ");
                            clasiPelicula = sc.nextLine().trim().toLowerCase();
                            if (!esClasificacionValida(clasiPelicula)) {
                                imprimirMensaje("Clasificación no valida");
                            }
                        } while (!esClasificacionValida(clasiPelicula));

                        do {
                            imprimirPrompt("Genero (accion, comedia, drama, terror, ciencia ficción y otro): ");
                            generoPelicula = sc.nextLine().trim().toLowerCase();
                            if (!esGeneroValido(generoPelicula)) {
                                imprimirMensaje("Genero no valido");
                            }
                        } while (!esGeneroValido(generoPelicula));
                        Pelicula pelicula1 = new Pelicula(nombrePelicula, duracionPelicula, clasiPelicula,
                                generoPelicula);
                        peliculas.add(pelicula1);

                        for (Cine cine : cines) {
                            cine.agregarPelicula(pelicula1);
                        }
                        System.out.println("Película añadida correctamente");

                    } else if (eleccionPelicula == 2) {// Listar películas
                        if (peliculas.isEmpty()) {
                            imprimirMensaje("No hay peliculas registradas");
                        } else {
                            mostrarLista(peliculas);
                        }
                    } else if (eleccionPelicula == 3) {// Detalles de una película
                        if (peliculas.isEmpty()) {
                            imprimirMensaje("No hay peliculas registradas");
                        } else {
                            imprimirMensaje("Lista de peliculas");
                            for (Pelicula pelicula : peliculas) {
                                System.out.println(pelicula.getCodigoIdentificativo());
                            }
                            imprimirPrompt("Escribe el codigo de la pelicula que quieres ver los detales: ");
                            String codigoPeli = sc.nextLine().trim();
                            Pelicula peliMostrar = buscarPeliculaPorCodigo(peliculas, codigoPeli);
                            if (peliMostrar == null) {
                                imprimirMensaje("No se ha encontrado la pelicula");
                            } else {
                                System.out.println(peliMostrar);
                            }
                        }
                    } else if (eleccionPelicula == 4) {// Proyecciones para una película
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            imprimirMensaje("Lista de peliculas");
                            for (Pelicula pelicula : peliculas) {
                                System.out.println(pelicula.getCodigoIdentificativo());
                            }
                            imprimirPrompt("Escribe el codigo de la pelicula que quieres ver los detales: ");
                            String codigoPeli = sc.nextLine().trim();
                            Pelicula peliB = buscarPeliculaPorCodigo(peliculas, codigoPeli);
                            if (peliB == null) {
                                imprimirMensaje("No se ha encontrado la pelicula");
                            } else {
                                boolean hayProy = false;
                                imprimirMensaje("Proyecciones de " + peliB.getNombre() + ": ");
                                for (Proyeccion proyeccion : proyecciones) {
                                    if (proyeccion.getPelicula().getCodigoIdentificativo()
                                            .equalsIgnoreCase(peliB.getCodigoIdentificativo())) {
                                        System.out.println(proyeccion);
                                        hayProy = true;
                                    }
                                }
                                if (!hayProy) {
                                    imprimirMensaje(peliB.getNombre() + " no tiene proyecciones programadas.");
                                }
                            }
                        }
                    } else {
                        imprimirMensaje("Opción no válida");
                    }
                }
            } else if (eleccion == 4) {// Gestión de clientes
                boolean funcionarCliente = true;
                int eleccionCliente = 0;
                while (funcionarCliente) {
                    eleccionCliente = mostrarMenuYLeerOpcion(sc, MENUCLIENTES);
                    if (eleccionCliente == 6) {// Volver al menú anterior
                        funcionarCliente = false;
                    } else if (eleccionCliente == 1) {// Añadir cliente
                        imprimirPrompt("Nombre del cine: ");
                        String nombreC = sc.nextLine().trim();
                        Cine cine = buscarCine(cines, nombreC);
                        if (cine == null) {
                            imprimirMensaje("Cine no encontrado");
                        } else {
                            Cliente nuevoCliente = crearCliente(sc, cines);
                            cine.agregarCliente(nuevoCliente);
                            imprimirMensaje("Cliente registrado correctamente");
                        }
                    } else if (eleccionCliente == 2) {// Cancelar asistencia
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else if (LocalDateTime.now().isAfter(proyeccion.getFechaFin())) {
                                imprimirMensaje("La proyección ya ha finalizado, no se puede cancelar");
                            } else if (proyeccion.getAsistentes().isEmpty()) {
                                imprimirMensaje("No hay asistentes registrados");
                            } else {
                                imprimirMensaje("Asistentes registrados: ");
                                for (Cliente c : proyeccion.getAsistentes()) {
                                    System.out.println(c.getDni());
                                }
                                imprimirPrompt("Dime el DNI del cliente para cancelar la asistencia: ");
                                String dni = sc.nextLine().trim();
                                Cliente clienteBorrar = null;
                                for (Cliente c : proyeccion.getAsistentes()) {
                                    if (c.getDni().equalsIgnoreCase(dni)) {
                                        clienteBorrar = c;
                                    }
                                }
                                if (clienteBorrar == null) {
                                    imprimirMensaje("No hay asistentes con este ( " + dni + " )dni.");
                                } else if (proyeccion.eliminarAsistente(dni)) {
                                    imprimirMensaje("Eliminada asistencia de " + clienteBorrar.getNombreCompleto());
                                } else {
                                    imprimirMensaje("No se encontró ningún asistente con el dni " + dni);
                                }
                            }
                        }
                    } else if (eleccionCliente == 3) {// Lista de clientes en una proyección
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else if (proyeccion.getAsistentes().isEmpty()) {
                                imprimirMensaje("La proyección todavía no tiene asistentes registrados");
                            } else {
                                System.out.println(proyeccion.mostrarMatriz());
                                List<Cliente> asistentes = new ArrayList<>(proyeccion.getAsistentes());
                                Collections.sort(asistentes, Comparator.comparing(Cliente::getNombreCompleto));
                                imprimirMensaje("Asistentes ordenados por nombre: ");
                                mostrarLista(asistentes);
                            }
                        }
                    } else if (eleccionCliente == 4) {// Nº total de asistentes en una proyección
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else {
                                imprimirMensaje("Asistentes actuales: " + proyeccion.contarButacasOcup() + " / 200");
                            }
                        }
                    } else if (eleccionCliente == 11) {// Nº total de asistentes en una proyección
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else {
                                String mensaje = proyeccion.contarButacasOcupPorFila();
                                imprimirMensaje(mensaje);
                            }
                        }
                    } else if (eleccionCliente == 9) {
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else {
                                System.out.println(proyeccion.getPorcentajeOcupacion());;
                            }
                        }
                    }  else if (eleccionCliente == 10) {
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else {
                                System.out.println(proyeccion.getRecudacionProyeccion());;
                            }
                        }
                    } else if (eleccionCliente == 5) {// Registrar cliente en una proyección
                        int subEleccionCliente;
                        if (proyecciones.isEmpty()) {
                            imprimirMensaje("No hay proyecciones registradas");
                        } else {
                            Proyeccion proyeccion = pedirYBuscarProyeccion(sc, proyecciones);
                            if (proyeccion == null) {
                                imprimirMensaje("Proyección no encontrada");
                            } else if (LocalDateTime.now().isAfter(proyeccion.getFechaFin())) {
                                imprimirMensaje("La proyección ya ha finalizado, no se puede registrar ");
                            } else if (proyeccion.estaLlena()) {
                                imprimirMensaje("La sala ya esta llena (200/200 butacas ocupadas)");
                            } else {
                                subEleccionCliente = mostrarMenuYLeerOpcion(sc, MENUELEGIRCLIENTE);
                                Cliente clienteRegistrar = null;
                                if (subEleccionCliente == 1) {
                                    boolean clienteExiste = false;
                                    for (Cine cine : cines) {
                                        if (!cine.getClientes().isEmpty()) {
                                            clienteExiste = true;
                                        }
                                    }
                                    if (!clienteExiste) {
                                        imprimirMensaje("No hay clientes registrados");
                                    } else {
                                        imprimirMensaje("Clientes registrados: ");
                                        for (Cine cine : cines) {
                                            mostrarLista(cine.getClientes());
                                        }
                                        imprimirPrompt("Dime el DNI del cliente: ");
                                        String dniC = sc.nextLine().trim();
                                        clienteRegistrar = buscarClienteEnCines(cines, dniC);
                                        if (clienteRegistrar == null) {
                                            imprimirMensaje("No hay ningún cliente con el dni " + dniC);
                                        }
                                    }
                                } else if (subEleccionCliente == 2) {
                                    Cliente nuevoCliente = crearCliente(sc, cines);
                                    for (Cine cine : cines) {
                                        cine.agregarCliente(nuevoCliente);
                                    }
                                    clienteRegistrar = nuevoCliente;
                                    imprimirMensaje("Cliente registrado correctamente");
                                } else {
                                    imprimirMensaje("Opción no válida");
                                }

                                if (clienteRegistrar != null) {
                                    if (proyeccion.agregarAsistente(clienteRegistrar)) {
                                        boolean butacaAsignada = false;
                                        while (!butacaAsignada) {
                                            System.out.println(proyeccion.mostrarMatriz());
                                            imprimirPrompt("Elige fila (1-10): ");
                                            int fila = leerEntero(sc);
                                            while (fila < 1 || fila > 10) {
                                                imprimirPrompt("Fila incorrecta. Introduce un nº entre 1-10: ");
                                                fila = leerEntero(sc);
                                            }
                                            char columna = ' ';
                                            boolean columnaCorrecta = false;
                                            while (!columnaCorrecta) {
                                                imprimirPrompt("Elige columna (A-T): ");
                                                String ColumnaUsu = sc.nextLine().trim().toUpperCase();
                                                if (ColumnaUsu.length() == 1
                                                        && ColumnaUsu.charAt(0) >= 'A'
                                                        && ColumnaUsu.charAt(0) <= 'T') {
                                                    columna = ColumnaUsu.charAt(0);
                                                    columnaCorrecta = true;
                                                } else {
                                                    imprimirMensaje("Error. Introduce una letra entre A y T: ");
                                                }
                                            }

                                            int resultado = proyeccion.asignarButaca(fila, columna, clienteRegistrar);
                                            if (resultado == 1) {
                                                imprimirMensaje("Butaca " + fila + columna + " asignada.");
                                                butacaAsignada = true;
                                            } else if (resultado == -1) {
                                                imprimirMensaje("ERROR: Columna/Fila fuera de rango");
                                            } else if (resultado == -2) {
                                                imprimirMensaje(
                                                        "ERROR: Cliente tiene comprada ya una butaca para proyección");
                                                butacaAsignada = true;
                                            } else if (resultado == -3) {
                                                imprimirMensaje("ERROR: Asiento ya ocupado");
                                            }
                                        }
                                    } else {
                                        imprimirMensaje("Este cliente esta registrado en la proyección");
                                    }
                                }
                            }
                        }
                    } else {
                        imprimirMensaje("Opción no válida");
                    }
                }

            } else {
                imprimirMensaje("Opción no válida");
            }
            numIterraciones++;
        }
        sc.close();
    }
}
