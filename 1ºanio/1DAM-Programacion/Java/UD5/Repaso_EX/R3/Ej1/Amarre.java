import java.util.ArrayList;
import java.util.Arrays;

public class Amarre {
    private static int numeroAmarres;
    private final static ArrayList<String> TIPOSVALIDOS = new ArrayList<>(
            Array.asList("NORMAL", "PREMIUM", "MEGAYATE"));

    private int numero;
    private double longitudMaxima;
    private double precioDia;
    private boolean ocupado;
    private String tipoAmarre;

    Amarre(double longitudMaxima, String tipoAmarre) {
        setNumeroAmarres(numeroAmarres);
        asignarNumero();
        this.longitudMaxima = longitudMaxima;
        asignarPrecioDia();
        this.ocupado = false;
        setTipoAmarre(tipoAmarre);
    }

    private void asignarNumero() {
        setNumero(numero);
        numero++;
    }

    private void asignarPrecioDia() {
        if (precioDia > 0.0) {
            setPrecioDia(precioDia);
        } else {
            setPrecioDia(0.0);
        }
    }

    private void calcularPrecioDia() {
        if (tipoAmarre != null && longitudMaxima != -1.0) {
            if (tipoAmarre.equals(TIPOSVALIDOS.get(0))) {
                precioDia = 25 + (1.5 * longitudMaxima);
            } else if (tipoAmarre.equals(TIPOSVALIDOS.get(1))) {
                precioDia = 60 + (2.2 * longitudMaxima);
            } else if (tipoAmarre.equals(TIPOSVALIDOS.get(2))) {
                precioDia = 120 + (3.5 * longitudMaxima);
            }
        }
    }

    public static int getNumeroAmarres() {
        return numeroAmarres;
    }

    public static void setNumeroAmarres(int numeroAmarres) {
        Amarre.numeroAmarres = numeroAmarres;
        numeroAmarres++;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(double longitudMaxima) {
        if (longitudMaxima >= 4.0) {
            this.longitudMaxima = longitudMaxima;
        } else {
            this.longitudMaxima = -1.0;
            precioDia = -1;
        }
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getTipoAmarre() {
        return tipoAmarre;
    }

    public void setTipoAmarre(String tipoAmarre) {
        if (TIPOSVALIDOS.include(tipoAmarre.toUpperCase())) {
            this.tipoAmarre = tipoAmarre;
        }
    }

    public String consultarDisponibilidad() {
        if (ocupado) {
            return "Está ocupado";
        } else {
            return "Está libre";
        }
    }

    public String consultarPrecioDia() {
        if (precioDia == -1) {
            return "PRECIO DIARIO ERRONEO: Actualice longitud o tipo de amarre";
        } else {
            return precioDia + "€/día.";
        }
    }

    public String consultarLongitudMaxima() {
        if (longitudMaxima == -1.0) {
            return "LONGITUD MAXIMA ERRONEA: Actualice longitud";
        } else {
            return longitudMaxima + "m";
        }
    }

    @Override
    public String toString() {
        return "Amarre " + numero + " del tipo " + tipoAmarre + ". Longitud maxima de " + consultarLongitudMaxima()
                + " con precio diario de " + consultarPrecioDia() + consultarDisponibilidad();
    }
}
