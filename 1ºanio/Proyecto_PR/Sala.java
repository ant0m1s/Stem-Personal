import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Sala {
    private static int numeroSalas = 0;
    private int capacidadMaxima;

    private Pelicula peliculaActual;
    private String codigo;
    private boolean disponible;
    private List<Proyeccion> proyecciones;
    
    public Sala() {
        this.capacidadMaxima = 200;
        this.proyecciones = new ArrayList<>();
        asignarCodigoIdentificativo();
    }
    //Crea una nueva proyección y comprueba que no exista solapamiento con otras proyecciones
    public String programarProyeccion(Pelicula pelicula, LocalDateTime inicio){
        String mensaje = "";
        Proyeccion nuevaProyeccion = new Proyeccion(pelicula, this, inicio);
        int hora = inicio.getHour();
        int minutosTotales = (hora * 60) + inicio.getMinute();
        boolean bandera = true;
        if (proyecciones.size() > 5) {
            mensaje = "No puedes añadir mas de 5 proyecciones para una sala";
            bandera = false;
        }
        if (minutosTotales <= 180 && bandera|| minutosTotales > 360 && bandera) {
            for (Proyeccion existe : proyecciones) {
                if (nuevaProyeccion.getFechaActual().isBefore(existe.getFechaFin().plusMinutes(30)) && nuevaProyeccion.getFechaFin().isAfter(existe.getFechaActual().plusMinutes(30))) {
                    System.out.println("Sala ocupada, por la pelicula " + existe.getPelicula().getNombre());
                    mensaje = "Sala ocupada, por la pelicula " + existe.getPelicula().getNombre();
                }
            }
            proyecciones.add(nuevaProyeccion);
            mensaje = "ok";
        } else {
            mensaje = "No puedes iniciar una pelicula entre el tramo horario de las 3 y 6 de la mañana";
        }
        return mensaje;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
    public Pelicula getPeliculaActual() {
        return peliculaActual;
    }
    public void setPeliculaActual(Pelicula peliculaActual) {
        this.peliculaActual = peliculaActual;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    //Crea codigo identificador para sala y suma 1 al contador de salas
    public void asignarCodigoIdentificativo() {
        setCodigo("Sala-" + numeroSalas);
        numeroSalas++;
    }

    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    //Comprueba que la sala no tenga proyección en curso
    public boolean estaDisponibleAhora(){
        boolean resultado = true;
        LocalDateTime ahora = LocalDateTime.now();
        for (Proyeccion proyeccion : proyecciones) {
            if (ahora.isAfter(proyeccion.getFechaActual()) && ahora.isBefore(proyeccion.getFechaFin())) {
                resultado = false;
            }
        }
        return resultado;
    }
    //Cancela proyección
    public boolean cancelarProyeccion(String codigoProyeccion){
        boolean devolver = false;
        for (int i = 0; i < proyecciones.size(); i++) {
            if (proyecciones.get(i).getCodigoIdentificativo().equals(codigoProyeccion)) {
                proyecciones.remove(i);
                devolver = true;
            }
        }
        return devolver;
    }

    public List<Proyeccion> getProyecciones() {
        return proyecciones;
    }

    @Override
    public String toString() {
        Boolean estado = estaDisponibleAhora();
        String mensajeEstado;
        if (estado) {
            mensajeEstado = "Disponible";
        } else {
            mensajeEstado = "Ocupada actualmente";
        }
        return codigo + " | Capacidad maxima: " + capacidadMaxima + " | Estado: " + mensajeEstado;
    }

    
}
