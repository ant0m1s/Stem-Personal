import java.util.ArrayList;

public class Supermercado {
    ArrayList<Producto> productos;

    Supermercado() {
        this.productos = new ArrayList<>();
    }

    public String mostrarProductos() {
        String lista = "";
        for (Producto producto : productos) {
            lista += producto + "\n";
        }
        if (productos.isEmpty()) {
            return "No hay productos disponibles";
        }
        return lista;
    }

    public Producto buscarPorNombre(String nombre){
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public void agregarProducto(Producto productoIntroducir){
        this.productos.add(productoIntroducir);
    }

    public Producto venderProducto(String nombre, int cantidadStock){
        Producto productoActual = buscarPorNombre(nombre);
        if (productoActual != null && cantidadStock <= productoActual.getCantidadStock()) {
            productoActual.setCantidadStock(productoActual.getCantidadStock() - cantidadStock);
            return productoActual;
        }
        return null;
    }
}
