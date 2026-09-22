public class Programador extends Empleado {
    private final double BONIFICADOR = 1.5;
    private double sueldoFinal;

    public Programador(String nombre, double sueldoBase) {
        super(nombre, sueldoBase);
        this.sueldoFinal = calcularSueldo(sueldoBase, BONIFICADOR);

    }

    public double calcularSueldo(double sueldoBase, double BONIFICADOR) {
        double sueldoFinal = sueldoBase * BONIFICADOR; 
        return sueldoFinal;
    }

    @Override
    public String toString() {
        return "Nombre del empleado: " + getNombre() + " | Sueldo base: " + getSueldoBase() + " | Labor: Programador | Sueldo final: " + sueldoFinal;
    }
    
    
}
