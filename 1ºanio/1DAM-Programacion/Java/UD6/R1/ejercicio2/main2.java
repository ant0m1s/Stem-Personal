public class main2 {
    public static void main(String[] args) {
        Programador programador1 = new Programador("Marino", 1500.3);
        Gerente gerente1 = new Gerente("Juan de dios", 1500.1);
        Disenador diseniador = new Disenador("Antonio", 1200.0);
        Empleado limpiador = new Empleado("Manolo", 721.9);

        System.out.println(programador1);
        System.out.println(gerente1);
        System.out.println(diseniador);
        System.out.println(limpiador);
    }
}
