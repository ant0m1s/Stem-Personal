class CuentaBancaria {

    String titular;
    double saldo;

    CuentaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    void depositar(double cantidad) {
        saldo += cantidad;
    }

    void retirar(double cantidad) {
        if (saldo < cantidad) {
            System.out.println("No tienes tanto dinero");
        } else {
            cantidad -= saldo;
        }

    }

    @Override
    public String toString() {
        return "Cuenta Bancaria: " + titular + " | " + saldo;
    }
}

public class ej2 {
    public static void main(String[] args) {
        CuentaBancaria cuentaBancaria1 = new CuentaBancaria("Luis Fernandez", 100.1);
        CuentaBancaria cuentaBancaria2 = new CuentaBancaria("Guti Gonzalez", 200.92);
        System.out.println(cuentaBancaria1);
        System.out.println(cuentaBancaria2);
        cuentaBancaria1.depositar(5.5);
        System.out.println(cuentaBancaria1);
    }
}
