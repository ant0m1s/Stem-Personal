public class Producto {
    private static int contador = 1;
    private int id;
    private String nombre;
    private String descripción;
    private int precio;
    private int cantidadStock;

    Producto(String nombre, String descripcion, int precio, int cantidadStock) {
        this.id = contador;
        this.nombre = nombre;
        this.descripción = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        contador++;
    }

    Producto(String nombre, int precio, int cantidadStock) {
        this.id = contador;
        this.nombre = nombre;
        this.descripción = "Sin descripción";
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        contador++;
    }

    @Override
    public String toString() {
        return id + "º- Producto: " + nombre + " | Descripción: " + descripción + " | Precio: " + precio
                + " | Stock?: "
                + cantidadStock;
    }

    public static int getContador() {
        return contador;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    
}
