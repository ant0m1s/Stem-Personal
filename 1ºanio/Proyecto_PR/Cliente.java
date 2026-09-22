public class Cliente {
    private static int totalEntradas;
    private static int contador = 0;
    private int id;

    private String nombreCompleto;
    private String dni;
    private int edad;
    private String tipoEntrada;
    private int numEntrada;

    public Cliente(String nombreCompleto, String dni, int edad, String tipoEntrada) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.edad = edad;
        this.tipoEntrada = tipoEntrada;
        contador++;
        this.id = contador;
        this.numEntrada = totalEntradas;
        totalEntradas++;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public static int getTotalEntradas() {
        return totalEntradas;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    @Override
    public String toString() {
        return id + "º Cliente: Su nombre completo: " + nombreCompleto + " con DNI: " + dni + " | tiene "
                + edad + " años " + "\nTiene una entrada: "
                + tipoEntrada + " con un número de entrada de " + numEntrada;
    }

}
