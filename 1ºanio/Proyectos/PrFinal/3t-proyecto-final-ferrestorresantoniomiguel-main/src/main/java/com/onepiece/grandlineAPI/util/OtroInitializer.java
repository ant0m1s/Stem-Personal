package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Otro;
import com.onepiece.grandlineAPI.service.OtroService;

@Component
public class OtroInitializer {

    @Autowired
    private OtroService otroService;

    public void inicializarOtros() {
        otroService.save(new Otro("Dr. Vegapunk", "El Mejor Cientifíco", 2.00, 65, "El Científico más brillante del mundo", false, "Egghead", "img/otros/vegapunk.jpg"));
        otroService.save(new Otro("Nefeltari D. Vivi", "La Princesa", 1.69, 18, "Princesa del Reino de Alabasta", true, "Alabasta", "img/otros/vivi.jpg"));
        otroService.save(new Otro("Gol D. Roger", "El Rey Pirata", 1.88, 53, "La única persona que fue Rey de los Piratas", false, "Wano", "img/piratas/rogerJov.jpg"));
        otroService.save(new Otro("Silvers Rayleigh", "El Rey Oscuro", 1.88, 78, "Primer oficial del Rey de los Piratas", true, "Punk Hazard", "img/otros/rayleighMay.jpg"));
        otroService.save(new Otro("Sabo", "El Emperador de las Llamas", 1.87, 22, "Jefe de personal del Ejército Revolucionario", true, "Dressrosa", "img/otros/sabo.jpg"));
        otroService.save(new Otro("Monkey D. Dragon", "El Peor Criminal del Mundo", 2.56, 55, "Comandante Supremo del Ejército Revolucionario y Padre de Luffy", true, "Ejército Revolucionario", "img/otros/dragon.jpg"));
        otroService.save(new Otro("Emporio Ivankov", "Persona Milagrosa", 4.49, 53, "Reina del Reino de Kamabakka y Miembro Fundador del Ejército Revolucionario", true, "Ejército Revolucionario", "img/otros/ivankov.jpg"));
        otroService.save(new Otro("Belo Betty", "Betty", 1.96, 34, "Lleva siendo subordinada de Dragon, desde la existencia de la Armada Libertadora", true, "Ejército Revolucionario", "img/otros/betty.jpg"));
        otroService.save(new Otro("Koala", "Koala", 1.60, 53, "Maestra sustituta de Karate Gyojín y una Oficial del Ejército Revolucionario", true, "Ejército Revolucionario", "img/otros/koala.jpg"));

        otroService.save(new Otro("Nefeltari Cobra", "El Rey de Alabasta", 1.73, 50, "Rey del Reino de Alabasta y padre de la Princesa Vivi", false, "Alabasta", "img/otros/cobra.jpg"));
        otroService.save(new Otro("Igaram", "El Coronel", 2.05, 52, "Comandante de la Guardia Real de Alabasta y fiel protector de la Princesa Vivi", true, "Alabasta", "img/otros/igaram.jpg"));

        otroService.save(new Otro("Kozuki Momonosuke", "El Shogun de Wano", 1.80, 28, "Heredero del clan Kozuki y nuevo Shogun del País de Wano tras derrotar a Kaido", true, "Wano", "img/otros/momonosuke.jpg"));
        otroService.save(new Otro("Yamato", "El Kozuki Oden de Kaido", 2.63, 28, "Hijo de Kaido que se enamoró del espíritu de Kozuki Oden y luchó contra su propio padre", true, "Wano", "img/otros/yamato.jpg"));
        otroService.save(new Otro("Kinemon", "Foxfire Kinemon", 2.78, 43, "Retainer del clan Kozuki que viajó 20 años en el tiempo para cumplir su promesa", true, "Wano", "img/otros/kinemon.jpg"));

        otroService.save(new Otro("Caesar Clown", "Lord Caesar", 2.30, 40, "Científico desquiciado especialista en armas de destrucción masiva y gases venenosos", true, "Punk Hazard", "img/otros/caesar.jpg"));

        otroService.save(new Otro("Shaka", "El Bueno", 1.75, 0, "Satélite de Vegapunk encargado de la investigación y considerado el más inteligente de todos", false, "Egghead", "img/otros/shaka.jpg"));
        otroService.save(new Otro("York", "La Ambiciosa", 1.73, 0, "Satélite de Vegapunk que traicionó al resto de sus compañeros para convertirse en Dragón Celestial", true, "Egghead", "img/otros/york.jpg"));

        otroService.save(new Otro("Enel", "El Dios", 2.66, 37, "Autoproclamado Dios de Skypie y uno de los personajes más poderosos del East Blue", true, "Skypie", "img/otros/enel.jpg"));
        otroService.save(new Otro("Gan Fall", "El Caballero del Cielo", 1.60, 71, "Antiguo Dios de Skypie que protegía a los habitantes de las nubes con su corazón bondadoso", true, "Skypie", "img/otros/ganFall.jpg"));
        otroService.save(new Otro("Wyper", "Wyper el Feroz", 2.07, 28, "Guerrero shandiano que llevó toda su vida luchando por recuperar la tierra natal de su pueblo", true, "Skypie", "img/otros/wyper.jpg"));

        otroService.save(new Otro("Rebecca", "La Bailarina Soldado", 1.69, 16, "Gladiadora de Dressrosa e hija del legendario soldado Kyros convertido en juguete", true, "Dressrosa", "img/otros/rebecca.jpg"));
        otroService.save(new Otro("Kyros", "Thunder Soldier", 2.61, 34, "El mejor gladiador de la historia de Dressrosa que fue convertido en juguete por Sugar durante diez años", true, "Dressrosa", "img/otros/kyros.jpg"));
    }
}
