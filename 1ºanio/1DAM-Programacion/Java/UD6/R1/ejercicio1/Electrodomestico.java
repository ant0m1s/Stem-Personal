public class Electrodomestico {
    private String nombre;
    private String modelo;
    
    public Electrodomestico(String nombre, String modelo) {
        this.nombre = nombre;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Electrodomestico: " + nombre + " | Modelo: " + modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    

    
}
