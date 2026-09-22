import java.util.ArrayList;
import java.util.Arrays;

public class Faker {
    // Arrays con datos de ejemplo
    private static final String[] nombres = {
            "Juan", "Pedro", "María", "Ana", "Luis", "Sofía", "Carlos", "Laura", "Fernando", "Elena",
            "Miguel", "Isabel", "Javier", "Beatriz", "Andrés", "Carmen", "Hugo", "Valeria", "Ricardo", "Paula",
            "Gabriel", "Alejandra", "Roberto", "Manuela", "Diego", "Victoria", "Daniel", "Natalia", "Álvaro", "Marta"
    };

    private static final String[] apellidos = {
            "García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Fernández", "Sánchez", "Ramírez",
            "Torres",
            "Díaz", "Jiménez", "Moreno", "Vargas", "Rojas", "Castro", "Mendoza", "Romero", "Herrera", "Ortega",
            "Navarro", "Delgado", "Cabrera", "Reyes", "Acosta", "Campos", "Peña", "Vega", "Guerrero", "Cruz"
    };

    private static final String[] ciudades = {
            "Madrid", "Barcelona", "Buenos Aires", "Ciudad de México", "Lima", "Bogotá", "Santiago", "Caracas",
            "Montevideo", "Quito", "La Paz", "San Salvador", "San José", "Managua", "Asunción", "Brasilia",
            "Lisboa", "Roma", "París", "Berlín", "Londres", "Nueva York", "Los Ángeles", "Chicago", "Toronto",
            "Tokio", "Seúl", "Pekín", "Bangkok", "Sídney", "Ámsterdam", "Dublín", "Moscú", "Atenas", "Dubái"
    };

    private static final String[] titulosLibros = {
            "El señor de los anillos", "Cien años de soledad", "Don Quijote de la Mancha", "1984", "El nombre de la rosa",
            "Crimen y castigo", "Orgullo y prejuicio", "El gran Gatsby", "Matar a un ruiseñor", "Fahrenheit 451",
            "El principito", "La metamorfosis", "Drácula", "Frankenstein", "El alquimista",
            "Harry Potter y la piedra filosofal", "El código Da Vinci", "La sombra del viento", "Los pilares de la tierra", "El hobbit",
            "Guerra y paz", "Anna Karénina", "Madame Bovary", "Los miserables", "El retrato de Dorian Gray",
            "Ulises", "En busca del tiempo perdido", "La divina comedia", "Hamlet", "Romeo y Julieta",
            "Moby Dick", "La isla del tesoro", "Las aventuras de Tom Sawyer", "Veinte mil leguas de viaje submarino", "El conde de Montecristo",
            "Sherlock Holmes", "El proceso", "La náusea", "El extranjero", "El lobo estepario",
            "Siddhartha", "El poder del ahora", "Sapiens", "Una breve historia del tiempo", "El universo en una cáscara de nuez",
            "El gen egoísta", "El origen de las especies", "Cosmos", "Breve historia del tiempo", "Los juegos del hambre",
            "Divergente", "Maze Runner", "Ender's Game", "Dune", "Fundación",
            "Neuromancer", "Blade Runner", "Solaris", "El fin de la eternidad", "Yo, Robot",
            "La guerra de los mundos", "El hombre invisible", "La máquina del tiempo", "Viaje al centro de la Tierra", "De la Tierra a la Luna",
            "El perfume", "El club Dumas", "El péndulo de Foucault", "Baudolino", "El nombre de la rosa",
            "Rebelión en la granja", "Un mundo feliz", "La naranja mecánica", "El cuento de la criada", "Los desposeídos",
            "La ladrona de libros", "El diario de Ana Frank", "El pianista", "Sophie's Choice", "La lista de Schindler",
            "El viejo y el mar", "Por quién doblan las campanas", "Adiós a las armas", "El sol también sale", "Fiesta",
            "La hojarasca", "El coronel no tiene quien le escriba", "El amor en los tiempos del cólera", "El otoño del patriarca", "La increíble y triste historia",
            "Rayuela", "Sobre héroes y tumbas", "El túnel", "Ficciones", "El Aleph",
            "La casa de los espíritus", "Eva Luna", "Cuentos de Eva Luna", "Paula", "Retrato en sepia",
            "Como agua para chocolate", "La amortajada", "El obsceno pájaro de la noche", "Pedro Páramo", "El llano en llamas"
    };

    private static final String[] autoresLibros = {
            "J.R.R. Tolkien", "Gabriel García Márquez", "Miguel de Cervantes", "George Orwell", "Umberto Eco",
            "Fiódor Dostoyevski", "Jane Austen", "F. Scott Fitzgerald", "Harper Lee", "Ray Bradbury",
            "Antoine de Saint-Exupéry", "Franz Kafka", "Bram Stoker", "Mary Shelley", "Paulo Coelho",
            "J.K. Rowling", "Dan Brown", "Carlos Ruiz Zafón", "Ken Follett", "J.R.R. Tolkien",
            "León Tolstói", "León Tolstói", "Gustave Flaubert", "Victor Hugo", "Oscar Wilde",
            "James Joyce", "Marcel Proust", "Dante Alighieri", "William Shakespeare", "William Shakespeare",
            "Herman Melville", "Robert Louis Stevenson", "Mark Twain", "Julio Verne", "Alexandre Dumas",
            "Arthur Conan Doyle", "Franz Kafka", "Jean-Paul Sartre", "Albert Camus", "Hermann Hesse",
            "Hermann Hesse", "Eckhart Tolle", "Yuval Noah Harari", "Stephen Hawking", "Stephen Hawking",
            "Richard Dawkins", "Charles Darwin", "Carl Sagan", "Stephen Hawking", "Suzanne Collins",
            "Veronica Roth", "James Dashner", "Orson Scott Card", "Frank Herbert", "Isaac Asimov",
            "William Gibson", "Philip K. Dick", "Stanisław Lem", "Isaac Asimov", "Isaac Asimov",
            "H.G. Wells", "H.G. Wells", "H.G. Wells", "Julio Verne", "Julio Verne",
            "Patrick Süskind", "Arturo Pérez-Reverte", "Umberto Eco", "Umberto Eco", "Umberto Eco",
            "George Orwell", "Aldous Huxley", "Anthony Burgess", "Margaret Atwood", "Ursula K. Le Guin",
            "Markus Zusak", "Ana Frank", "Władysław Szpilman", "William Styron", "Thomas Keneally",
            "Ernest Hemingway", "Ernest Hemingway", "Ernest Hemingway", "Ernest Hemingway", "Ernest Hemingway",
            "Gabriel García Márquez", "Gabriel García Márquez", "Gabriel García Márquez", "Gabriel García Márquez", "Gabriel García Márquez",
            "Julio Cortázar", "Ernesto Sábato", "Ernesto Sábato", "Jorge Luis Borges", "Jorge Luis Borges",
            "Isabel Allende", "Isabel Allende", "Isabel Allende", "Isabel Allende", "Isabel Allende",
            "Laura Esquivel", "María Luisa Bombal", "José Donoso", "Juan Rulfo", "Juan Rulfo"
    };

    private static final String[] sinopsisLibros = {
            "Un hobbit emprende una épica aventura para destruir un anillo de poder en las tierras de la Tierra Media.",
            "La historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo.",
            "Las aventuras del ingenioso hidalgo Don Quijote y su fiel escudero Sancho Panza por la España del siglo XVII.",
            "Una distopía sobre un régimen totalitario que controla todos los aspectos de la vida de sus ciudadanos.",
            "Un monje investiga una serie de misteriosos crímenes en una abadía medieval italiana.",
            "La historia de Raskólnikov, un estudiante que comete un crimen y lucha con su conciencia.",
            "La historia de amor entre Elizabeth Bennet y el Sr. Darcy en la Inglaterra del siglo XIX.",
            "La decadencia del sueño americano a través de la figura de Jay Gatsby en los años veinte.",
            "Una niña en Alabama presencia la injusticia racial a través del juicio de un hombre negro inocente.",
            "En un futuro distópico, los bomberos queman libros para suprimir el pensamiento crítico.",
            "Un piloto encuentra a un pequeño príncipe en el desierto que le enseña sobre la vida y el amor.",
            "Gregor Samsa se despierta una mañana convertido en un enorme insecto.",
            "El conde Drácula, un vampiro de Transilvania, llega a Inglaterra en busca de nueva sangre.",
            "Un científico crea una criatura a partir de partes de cadáveres con terribles consecuencias.",
            "Un joven pastor andaluz sigue su leyenda personal hasta las pirámides de Egipto.",
            "Un niño huérfano descubre que es un mago y es admitido en una escuela de magia.",
            "Un criptólogo descifra pistas escondidas en obras de arte para resolver un misterio religioso.",
            "Un joven descubre en una librería de Barcelona un libro que lo arrastra a un misterio del pasado.",
            "La construcción de una catedral en la Inglaterra medieval como eje de intrigas y pasiones.",
            "Un hobbit es reclutado por un mago y trece enanos para recuperar un tesoro custodiado por un dragón.",
            "Una épica descripción de la sociedad rusa durante las guerras napoleónicas.",
            "El drama de una mujer de la aristocracia rusa atrapada entre el deber y la pasión.",
            "La historia de Emma Bovary, una mujer que busca escapar de la mediocridad a través de sus fantasías románticas.",
            "La historia de Jean Valjean, un expresidiario que lucha por redimirse en la Francia del siglo XIX.",
            "Un joven lord vende su alma a cambio de la eterna juventud y la belleza.",
            "Un día en la vida de Leopold Bloom en Dublín, narrado con técnicas narrativas revolucionarias.",
            "Un hombre recuerda su infancia y juventud a través de sensaciones involuntarias.",
            "El viaje de Dante por el Infierno, el Purgatorio y el Paraíso guiado por Virgilio.",
            "El príncipe de Dinamarca se venga de su tío asesino mientras lucha con sus propias dudas.",
            "Dos jóvenes de familias enemigas se enamoran con trágicas consecuencias en Verona.",
            "El capitán Ahab obsesionado con cazar a la ballena blanca que le arrancó la pierna.",
            "Un joven pirata, un mapa del tesoro y una aventura en el mar del siglo XVIII.",
            "Las travesuras del joven Tom Sawyer a orillas del río Mississippi en el siglo XIX.",
            "El capitán Nemo lleva a sus prisioneros por los fondos oceánicos en el submarino Nautilus.",
            "Edmond Dantès, falsamente encarcelado, escapa y se venga de sus enemigos como el Conde de Montecristo.",
            "Las investigaciones del detective más famoso de la literatura junto a su inseparable Watson.",
            "Josef K. es arrestado y procesado por una causa que jamás llega a conocer.",
            "Antoine Roquentin siente una profunda angustia existencial mientras escribe una biografía.",
            "Meursault mata a un árabe en la playa y afronta su juicio con total indiferencia.",
            "Harry Haller, el lobo estepario, lucha entre su naturaleza humana y su instinto solitario.",
            "El joven Siddhartha abandona su vida privilegiada en busca de la iluminación espiritual.",
            "Una guía para vivir plenamente en el momento presente y alcanzar la paz interior.",
            "Un recorrido por la historia de la humanidad desde el Homo sapiens hasta la actualidad.",
            "Una explicación accesible del universo, el tiempo y los agujeros negros.",
            "Hawking explora las últimas teorías sobre el universo desde las cuerdas hasta los agujeros negros.",
            "La teoría de que los genes, y no los individuos, son las verdaderas unidades de la evolución.",
            "La obra que cambió para siempre nuestra comprensión de la vida y la naturaleza.",
            "Un viaje por el cosmos que explora la historia del universo y el lugar de la humanidad en él.",
            "Una accesible explicación del espacio, el tiempo y el universo para el público general.",
            "Katniss Everdeen lucha por sobrevivir en un brutal reality show televisado en una nación distópica.",
            "Tris Prior descubre que su sociedad divide a las personas en facciones según sus virtudes.",
            "Un grupo de adolescentes despiertan en un laberinto sin recuerdos y deben encontrar la salida.",
            "Ender Wiggin es entrenado desde niño para liderar la lucha de la humanidad contra una raza alienígena.",
            "En el desértico planeta Arrakis, Paul Atreides se convierte en el mesías de un pueblo oprimido.",
            "La historia de la Fundación, un plan para preservar el conocimiento humano ante la caída del Imperio Galáctico.",
            "Un hacker es contratado para infiltrarse en el ciberespacio y robar datos para una misteriosa IA.",
            "Un detective investiga el asesinato de una replicante mientras cuestiona su propia humanidad.",
            "El planeta Solaris, con su océano pensante, desafía la comprensión humana de la vida.",
            "Un detective temporal viaja al pasado para evitar que el tiempo sea alterado para siempre.",
            "Un robot narra las Tres Leyes de la Robótica y sus consecuencias imprevistas.",
            "Los marcianos invaden la Tierra con devastadoras máquinas de guerra que destruyen todo a su paso.",
            "Un científico descubre la fórmula para volverse invisible con consecuencias fatales.",
            "Un científico viaja al futuro y descubre una humanidad dividida en dos razas degeneradas.",
            "El profesor Lidenbrock desciende a las entrañas de la Tierra a través de un volcán islandés.",
            "Tres viajeros son disparados desde un cañón gigante hacia la Luna en una cápsula de aluminio.",
            "Un asesino en serie crea el perfume más extraordinario del mundo usando víctimas humanas.",
            "Dos bibliófilos buscan a los tres mosqueteros en un juego de pistas literarias mortales.",
            "El símbolo del péndulo de Foucault se convierte en el centro de una conspiración global.",
            "Baudolino, un embustero medieval, acompaña al emperador Barbarroja en sus conquistas.",
            "El fraile Guillermo de Baskerville investiga crímenes en una abadía benedictina medieval.",
            "Los animales de una granja se rebelan contra sus dueños humanos en una alegoría del totalitarismo.",
            "En un futuro donde la felicidad es obligatoria, Bernard Marx cuestiona el orden establecido.",
            "En la Inglaterra futura, un delincuente es sometido a un brutal proceso de condicionamiento.",
            "En Gilead, las mujeres son reducidas a reproductoras al servicio del estado teocrático.",
            "En Anarres, una sociedad anarquista contrasta con el mundo capitalista del planeta vecino.",
            "Una niña narra su vida junto a una familia alemana durante la Segunda Guerra Mundial.",
            "El testimonio de Anne Frank sobre su vida escondida de los nazis en Ámsterdam.",
            "Las memorias de Władysław Szpilman, pianista judío que sobrevivió en la Varsovia ocupada.",
            "Stingo narra su amistad con Sophie y Nathan en el Brooklyn de la posguerra.",
            "La historia real de Oskar Schindler, quien salvó a más de mil judíos durante el Holocausto.",
            "Un viejo pescador cubano lucha durante tres días con un enorme marlín en alta mar.",
            "La historia de Robert Jordan, un americano que lucha junto a guerrilleros en la Guerra Civil española.",
            "El teniente Frederic Henry se enamora de una enfermera durante la Primera Guerra Mundial.",
            "Jake Barnes y sus amigos viajan de París a Pamplona para ver los Sanfermines en los años veinte.",
            "Un grupo de expatriados americanos viven en el París bohemio de los años veinte.",
            "El primer libro del ciclo de Macondo, que prefigura Cien años de soledad.",
            "El coronel espera durante quince años una carta del gobierno que nunca llega.",
            "Fermina Daza y Florentino Ariza se reencuentran en la vejez tras décadas de amor no correspondido.",
            "La historia del dictador latinoamericano más longevo del mundo contada en una sola frase.",
            "Eréndira es vendida por su abuela para pagar una deuda y sufre una vida de esclavitud.",
            "Oliveira busca a la Maga por París y Buenos Aires en una novela que puede leerse de múltiples formas.",
            "Martín García busca a su hija Alejandra mientras una secta anarquista planea un atentado.",
            "Juan Pablo Castel, un pintor obsesivo, narra cómo asesinó a la mujer que amaba.",
            "Laberintos de tiempo y sueños en cuentos que exploran los límites de la realidad y la ficción.",
            "Historias fantásticas que llevan la realidad al límite en el Buenos Aires de mediados del siglo XX.",
            "Clara del Valle narra la historia de su familia desde principios del siglo XX en Chile.",
            "Eva Luna cuenta la historia de su vida desde su nacimiento hasta convertirse en narradora.",
            "Una colección de cuentos narrados por Eva Luna a su amante Rolf Carlé.",
            "Isabel Allende narra en segunda persona la historia de su hija Paula, en coma durante un año.",
            "Aurora del Valle busca sus raíces y reconstruye la historia de su familia en el siglo XIX.",
            "Tita de la Garza expresa sus emociones a través de la cocina en el México revolucionario.",
            "Una mujer recuerda su vida después de morir, tendida en su ataúd.",
            "Humberto Peñaloza narra la historia de una familia aristocrática chilena en decadencia.",
            "Juan Preciado llega al pueblo de Comala buscando a su padre, pero solo encuentra muertos.",
            "Cuentos que retratan la vida rural mexicana con una prosa seca y poética."
    };

    // Fragmentos de texto Lorem Ipsum ampliado
    private static final String[] palabrasLorem = {
            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do",
            "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua", "ut",
            "enim", "ad", "minim", "veniam", "quis", "nostrud", "exercitation", "ullamco", "laboris",
            "nisi", "ut", "aliquip", "ex", "ea", "commodo", "consequat", "duis", "aute", "irure",
            "dolor", "in", "reprehenderit", "voluptate", "velit", "esse", "cillum", "fugiat", "pariatur"
    };

    /**
     * Genera un número entero aleatorio entre min y max (inclusive).
     * 
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Número entero aleatorio.
     */
    public static int entero(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Genera un booleano aleatorio.
     * 
     * @return booleano aleatorio.
     */
    public static boolean booleano() {
        return (entero(0, 1) == 1);
    }

    /**
     * Genera un precio aleatorio en un rango dado.
     * 
     * @param min Valor mínimo del precio.
     * @param max Valor máximo del precio.
     * @return Precio aleatorio con dos decimales.
     */
    public static double precio(double min, double max) {
        return entero((int) min * 100, (int) max * 100) / 100.0;
    }

    /**
     * Genera un nombre aleatorio.
     * 
     * @return Nombre aleatorio.
     */
    public static String nombre() {
        int posicionAleatoria = entero(0, nombres.length - 1);
        return nombres[posicionAleatoria];
    }

    /**
     * Genera un apellido aleatorio.
     * 
     * @return Apellido aleatorio.
     */
    public static String apellido() {
        int posicionAleatoria = entero(0, apellidos.length - 1);
        return apellidos[posicionAleatoria];
    }

    /**
     * Genera un nombre completo aleatorio.
     * 
     * @return Nombre y apellido combinados.
     */
    public static String nombreCompleto() {
        return nombre() + " " + apellido() + " " + apellido();
    }

    /**
     * Genera una edad aleatoria entre 18 y 60 años.
     * 
     * @return Edad aleatoria.
     */
    public static int edad() {
        return entero(18, 60);
    }

    /**
     * Genera una ciudad aleatoria.
     * 
     * @return Ciudad aleatoria.
     */
    public static String ciudad() {
        int posicionAleatoria = entero(0, ciudades.length - 1);
        return ciudades[posicionAleatoria];
    }

    /**
     * Genera un número de teléfono aleatorio con formato "+34 XXX XXX XXX".
     * 
     * @return Número de teléfono aleatorio.
     */
    public static String telefono() {
        String telefono = "+34 ";

        telefono += entero(6, 9);

        for (int i = 2; i < 9; i++) {
            telefono += entero(0, 9);
            if (i % 3 == 0) {
                telefono += " ";
            }
        }

        telefono += entero(0, 9);

        return telefono;
    }

    /**
     * Genera un correo electrónico aleatorio basado en un nombre y apellido
     * aleatorio.
     * 
     * @return Correo electrónico aleatorio.
     */
    public static String correo() {
        return tildesCase(nombre() + apellido() + "@example.com").toLowerCase();
    }

    /**
     * Transforma una cadena eliminando las tildes.
     * 
     * @return Cadena de texto sin tildes.
     */
    public static String tildesCase(String cadena) {

        Character[] arrayVocalesConTilde = { 'á', 'é', 'í', 'ó', 'ú', 'Á', 'É', 'Í', 'Ó', 'Ú' };
        ArrayList<Character> vocalesConTilde = new ArrayList<>(Arrays.asList(arrayVocalesConTilde));

        Character[] arrayVocalesSinTilde = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        ArrayList<Character> vocalesSinTilde = new ArrayList<>(Arrays.asList(arrayVocalesSinTilde));

        String resultadoSinTildes = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lectraActual = cadena.charAt(i);

            int posicionVocalConTilde = vocalesConTilde.indexOf(lectraActual);

            if (posicionVocalConTilde == -1) {
                resultadoSinTildes += lectraActual;
            }else{
                resultadoSinTildes += vocalesSinTilde.get(posicionVocalConTilde);
            }

            // resultadoSinTildes += posicionVocalConTilde == -1 ? lectraActual : vocalesSinTilde.get(posicionVocalConTilde);

        }

        return resultadoSinTildes;
    }

    // public static String tildesCase(String cadena) {

    //     String resultado = cadena;
    //     resultado = resultado.replace('á', 'a');
    //     resultado = resultado.replace('é', 'e');
    //     resultado = resultado.replace('í', 'i');
    //     resultado = resultado.replace('ó', 'o');
    //     resultado = resultado.replace('ú', 'u');
    //     resultado = resultado.replace('Á', 'A');
    //     resultado = resultado.replace('É', 'E');
    //     resultado = resultado.replace('Í', 'I');
    //     resultado = resultado.replace('Ó', 'O');
    //     resultado = resultado.replace('Ú', 'U');

    //     return resultado;
    // }

    /**
     * Genera un texto Lorem Ipsum con una cantidad específica de palabras.
     * 
     * @param cantidad Cantidad de palabras a generar.
     * @return Texto Lorem Ipsum generado.
     */
    public static String lorem(int cantidad) {
        String texto = "";

        for (int i = 0; i < cantidad; i++) {
            int posicionAleatoria = entero(0, palabrasLorem.length - 1);
            texto += palabrasLorem[posicionAleatoria] + " ";
        }

        return texto;
        
    }

    /**
     * Genera un texto Lorem Ipsum corto (5-10 palabras).
     * 
     * @return Texto Lorem Ipsum corto.
     */
    public static String loremCorto() {
        int cantidadPalabras = entero(5, 10);
        return lorem(cantidadPalabras);
    }

    /**
     * Genera un texto Lorem Ipsum largo (20-50 palabras).
     * 
     * @return Texto Lorem Ipsum largo.
     */
    public static String loremLargo() {
        int cantidadPalabras = entero(20, 50);
        return lorem(cantidadPalabras);
    }

    /**
     * Genera un título de libro aleatorio.
     * 
     * @return Título de libro aleatorio.
     */
    public static String titulo() {
        int posicionAleatoria = entero(0, titulosLibros.length - 1);
        return titulosLibros[posicionAleatoria];
    }

    /**
     * Genera un autor de libro aleatorio.
     * 
     * @return Autor de libro aleatorio.
     */
    public static String autorLibro() {
        int posicionAleatoria = entero(0, autoresLibros.length - 1);
        return autoresLibros[posicionAleatoria];
    }

    /**
     * Genera una sinopsis de libro aleatoria.
     * 
     * @return Sinopsis de libro aleatoria.
     */
    public static String sinopsis() {
        int posicionAleatoria = entero(0, sinopsisLibros.length - 1);
        return sinopsisLibros[posicionAleatoria];
    }

}