public class Butaca {
    private int fila;
    private char columna;
    private boolean ocupada;
    private Cliente cliente;
    public Butaca(int fila, char columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupada = false;
        this.cliente = null;
    }

    //Marca butaca como ocupada y guarda quien la ocupa
    public void ocupar(Cliente c){
        this.ocupada = true;
        this.cliente = c;
    }
    //Libera la butaca, elimina cliente que esta ocupandola
    public void liberar(){
        this.ocupada = false;
        this.cliente = null;
    }

    public boolean isOcupada() {
        return ocupada;
    }
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getID() {
        return fila + "" + columna;
    }

    @Override
    public String toString() {
        return "Butaca " + getID() + " \n Esta ocupada? " + ocupada + " \n Asignado al cliente " + cliente ;
    }

    

    
}
