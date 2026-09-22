public class ejercicio1 {
    public static void main(String[] args) {
        Universidad uco = new Universidad();
        Estudiante estudianteAleatorio;
        final int NUMEROESTUDIANTES = 100;

        for (int i = 0; i < NUMEROESTUDIANTES; i++) {
            estudianteAleatorio = new Estudiante(FakerPR.nombreCompleto(), FakerPR.edad(), "STEM" + i);
            uco.agregarEstudiante(estudianteAleatorio);
        }
        System.out.println(uco.mostrarEstudiantes());
    }
}
