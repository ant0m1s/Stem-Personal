public class Gerente extends Empleado {
    private final double BONIFICADOR = 2.1;
    private double sueldoFinal;

    public Gerente(String nombre, double sueldoBase) {
        super(nombre, sueldoBase);
        this.sueldoFinal = calcularSueldo(sueldoBase, BONIFICADOR);

    }

    public double calcularSueldo(double sueldoBase, double BONIFICADOR) {
        double sueldoFinal = sueldoBase * BONIFICADOR; 
        return sueldoFinal;
    }

    @Override
    public String toString() {
        return "Nombre del empleado: " + getNombre() + " | Sueldo base: " + getSueldoBase() + " | Labor: Gerente | Sueldo final: " + sueldoFinal;
    }
    
    
}
