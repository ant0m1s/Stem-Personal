public class Empleado {
    private String nombre;
    private double sueldoBase;
    public Empleado(String nombre, double sueldoBase) {
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getSueldoBase() {
        return sueldoBase;
    }
    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    @Override
    public String toString() {
        return "Nombre del empleado: " + nombre + " | Sueldo base: " + sueldoBase;
    }

    
}
