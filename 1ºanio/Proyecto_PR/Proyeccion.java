import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Proyeccion {
    private static int numeroProyecciones;

    private String codigoIdentificativo;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime fechaActual;
    private LocalDateTime fechaFin;
    private List<Cliente> asistentes;
    private Butaca[][] butacas;

    public Proyeccion(Pelicula pelicula, Sala sala, LocalDateTime fechaHora) {
        asignarCodigoIdentificativo();
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaActual = fechaHora;
        this.fechaFin = fechaHora.plusMinutes(pelicula.getDuracionMinutos());
        this.asistentes = new ArrayList<>();
        this.butacas = new Butaca[10][20];
        char[] columnas = "ABCDEFGHIJKLMNOPQRST".toCharArray();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                butacas[i][j] = new Butaca(i + 1, columnas[j]);
            }
        }
    }

    public List<Cliente> getAsistentes() {
        return asistentes;
    }
    //Añade cliente si no esta registrado anteriormente
    public boolean agregarAsistente(Cliente cliente) {
        boolean devolverResultado = true;
        for (Cliente c : asistentes) {
            if (c.getDni().equalsIgnoreCase(cliente.getDni())) {
                devolverResultado = false;
            }
        }
        if (devolverResultado) {
            asistentes.add(cliente);
        }
        return devolverResultado;
    }
    //Elimina cliente y libera su butaca
    public boolean eliminarAsistente(String dni) {
        boolean devolverResultado = false;
        for (int i = 0; i < asistentes.size(); i++) {
            if (asistentes.get(i).getDni().equalsIgnoreCase(dni)) {
                liberarButaca(dni);
                asistentes.remove(i);
                devolverResultado = true;
            }
        }
        return devolverResultado;
    }
    //Recorre matriz de butacas y suma las butacas que esten ocupadas
    public int contarButacasOcup() {
        int sumatorio = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (butacas[i][j].isOcupada()) {
                    sumatorio++;
                }
            }
        }
        return sumatorio;
    }

    public String contarButacasOcupPorFila() {
        int sumatorio = 0;
        String mensaje = "";
        for (int i = 0; i < 10; i++) {
            sumatorio = 0;
            for (int j = 0; j < 20; j++) {
                if (butacas[i][j].isOcupada()) {
                    sumatorio++;
                }
            }
            mensaje += "Fila " + (i + 1) + ": " + sumatorio + " butacas ocupadas.\n";
        }
        return mensaje;
    }
    //Intenta asignar una butaca a un cliente, según el resultado devuelve un valor
    public int asignarButaca(int fila, char columna, Cliente cliente) {
        int col = columna - 'A';
        int valorResultado = 1;

        if (fila < 1 || fila > 10 || col < 0 || col > 19) {
            valorResultado = -1;
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    if (butacas[i][j].getCliente() != null) {
                        if (butacas[i][j].getCliente().getDni().equalsIgnoreCase(cliente.getDni())) {
                            valorResultado = -2;
                        }
                    }
                }
            }
            if (butacas[fila - 1][col].isOcupada()) {
                valorResultado = -3;
            }
            if (valorResultado == 1) {
                butacas[fila - 1][col].ocupar(cliente);
            }
        }
        return valorResultado;
    }
    //Genera un texto que funciona como representación visual del plano de butacas
    public String mostrarMatriz() {
        String matriz = "";
        char[] cols = "ABCDEFGHIJKLMNOPQRST".toCharArray();
        matriz = "   ";
        for (char c : cols) {
            matriz += " " + c + " ";
        }
        matriz += "\n";

        for (int i = 0; i < 10; i++) {
            int filaN = (i + 1);
            if (filaN < 10) {
                matriz += " " + (i + 1) + " ";
            } else {
                matriz += (i + 1) + " ";
            }
            for (Butaca b : butacas[i]) {
                if (b.isOcupada()) {
                    matriz += "[X]";
                } else {
                    matriz += "[0]";
                }
            }
            matriz += "\n";
        }
        return matriz;
    }
    //Recibe un dni (identificador de cliente) y recorre matriz de butacas
    //Cuando lo encuentra, libera dicha butaca (con metodo auxiliar)
    public void liberarButaca(String dni) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (butacas[i][j].getCliente() != null) {
                    if (butacas[i][j].getCliente().getDni().equalsIgnoreCase(dni)) {
                        butacas[i][j].liberar();
                    }
                }
            }
        }
    }

    public String getPorcentajeOcupacion() {
        int ocupadas = contarButacasOcup();
        int capacidad = sala.getCapacidadMaxima();
        double porcentaje = (ocupadas * 100) / capacidad;
        String mensaje = ocupadas + "/" + capacidad + " butacas ocupadas ( " + porcentaje + " %)";
        return mensaje;
    }

    public String getRecudacionProyeccion() {
        double total = 0;
        for (Cliente c : asistentes) {
            if (c.getTipoEntrada().equalsIgnoreCase("normal")) {
                total += 19.99;
            } else if (c.getTipoEntrada().equalsIgnoreCase("reducida")) {
                total += 14.99;
            } else if (c.getTipoEntrada().equalsIgnoreCase("3D")) {
                total += 22.99;
            } else{
                total += 24.99;
            }
        }
        String mensaje = "Recaudación de la proyección: " + total + "€";
        return mensaje;
    }

    public boolean estaLlena() {
        return contarButacasOcup() >= 200;
    }
    //Crea identificador para cada proyección y suma 1 al contador de proyecciones
    public void asignarCodigoIdentificativo() {
        this.codigoIdentificativo = "PROY-" + numeroProyecciones;
        numeroProyecciones++;
    }

    public String getCodigoIdentificativo() {
        return codigoIdentificativo;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDateTime fechaHora) {
        this.fechaActual = fechaHora;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaHora) {
        this.fechaFin = fechaHora;
    }

    @Override
    public String toString() {
        return "ID Proyección: " + codigoIdentificativo + "\nFecha inicio: " + fechaActual + " | Fecha fin: " + fechaFin + " \nPelicula: " + pelicula.getNombre()
                + "\nProyectada en la sala: " + sala.getCodigo();
    }

}
