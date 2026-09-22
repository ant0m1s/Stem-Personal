package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Pirata;
import com.onepiece.grandlineAPI.entity.Tripulacion;
import com.onepiece.grandlineAPI.service.PirataService;

@Component
public class PirataInitializer {

    @Autowired
    private PirataService pirataService;

    @Autowired
    private TripulacionInitializer tripulacionInitializer;

    public void incializarPiratas() {
        Tripulacion sombrerosPaja = tripulacionInitializer.getTripulacionPorNombre("Piratas del Sombrero de Paja");
        Tripulacion corazon = tripulacionInitializer.getTripulacionPorNombre("Piratas del Corazón");
        Tripulacion barbaBlanca = tripulacionInitializer.getTripulacionPorNombre("Piratas de Barba Blanca");
        Tripulacion bigMom = tripulacionInitializer.getTripulacionPorNombre("Piratas de Big Mom");
        Tripulacion barbaNegra = tripulacionInitializer.getTripulacionPorNombre("Piratas de Barba Negra");
        Tripulacion pelirrojo = tripulacionInitializer.getTripulacionPorNombre("Piratas del Pelirrojo");

        pirataService.save(new Pirata("Monkey D. Luffy", "Sombrero de Paja", 1.74, 19, "Futuro Rey de los Piratas e Hijo de Dragon", true, 3000000000.0, "Capitán", "Peor Generación", sombrerosPaja, "/img/piratas/luffy.png"));
        pirataService.save(new Pirata("Roronoa Zoro", "El Cazador de Piratas", 1.81, 21, "El espadachín más poderoso", true, 1111000000.0, "Espadachín", "Peor Generación", sombrerosPaja, "/img/piratas/zoro.png"));
        pirataService.save(new Pirata("Nami", "La Gata Ladrona", 1.70, 20, "Navegante y ladrona de mapas", true, 366000000.0, "Navegante", "Peor Generación", sombrerosPaja, "/img/piratas/nami.jpg"));
        pirataService.save(new Pirata("Vinsmoke Sanji", "Pierna Negra", 1.70, 21, "Cocinero del Futuro Rey de los Piratas", true, 1032000000.0, "Cocinero", "Peor Generación", sombrerosPaja, "/img/piratas/sanji.jpg"));
        pirataService.save(new Pirata("Nico Robin", "La Niña Demonio", 1.88, 30, "Única superviviente de Ohara", true, 930000000.0, "Arqueóloga", "Peor Generación", sombrerosPaja, "/img/piratas/robin.jpg"));
        pirataService.save(new Pirata("Tony Tony Chopper", "El amante del Algodón de Azucar", 0.90, 17, "Médico de los Mugiwaras", true, 1000.0, "Médico", "Peor Generación", sombrerosPaja, "/img/piratas/chopper.jpg"));
        pirataService.save(new Pirata("Usopp", "Dios Ussop", 1.76, 19, "Tirador de los Mugiwaras", true, 500000000.0, "Tirador", "Peor Generación", sombrerosPaja, "/img/piratas/usopp.jpg"));
        pirataService.save(new Pirata("Franky", "El Cyborg", 2.40, 36, "Carpintero de los Mugiwaras", true, 394000000.0, "Carpintero", "Peor Generación", sombrerosPaja, "/img/piratas/franky.jpg"));
        pirataService.save(new Pirata("Brook", "El Rey del Soul", 2.40, 36, "Músico de los Mugiwaras", true, 394000000.0, "Músico", "Peor Generación", sombrerosPaja, "/img/piratas/brook.jpg"));
        pirataService.save(new Pirata("Jinbe", "El Caballero del Mar", 3.01, 46, "Timonel de los Mugiwaras", true, 1100000000.0, "Timonel", "Peor Generación", sombrerosPaja, "/img/piratas/jinbei.jpg"));

        pirataService.save(new Pirata("Trafalgar D. Water Law", "El Cirujano de la Muerte", 1.91, 26, "Capitán de los Piratas del Corazón", true, 3000000000.0, "Capitán", "Peor Generación", corazon, "/img/piratas/law.jpg"));
        pirataService.save(new Pirata("Bepo", "Sin alias", 2.40, 22, "Miembro de los Piratas del Corazón", true, 500000000.0, "Navegante", "Peor Generación", corazon, "/img/piratas/bepo.jpg"));
        pirataService.save(new Pirata("Jean Bart", "Bart", 6.39, 00, "Anteriormente era Prisionero en San Roswald", true, 0.0, "Miembro", "Peor Generación", corazon, "/img/piratas/jeanBart.jpg"));
        pirataService.save(new Pirata("Penguin", "Penguin", 1.79, 28, "Miembros Fundadores de los Piratas Heart y Hermano de Shachi", true, 0.0, "Miembro", "Peor Generación", corazon, "/img/piratas/penguin.jpg"));
        pirataService.save(new Pirata("Shachi", "Shachi", 1.80, 27, "Miembros Fundadores de los Piratas Heart y Hermano de Penguin", true, 0.0, "Miembro", "Peor Generación", corazon, "/img/piratas/shachi.jpg"));

        pirataService.save(new Pirata("Edward Newgate", "Barba Blanca", 3.66, 72, "El hombre más poderoso del mundo", false, 5046000000.0, "Capitán", "Generación Antigua", barbaBlanca, "/img/piratas/barbablanca.jpg"));
        pirataService.save(new Pirata("Portgas D. Ace", "El Puño de Fuego", 1.85, 20, "Hijo de Gol D. Roger", false, 550000000.0, "Capitán 2º División", "Generación Antigua", barbaBlanca, "/img/piratas/ace.jpg"));
        pirataService.save(new Pirata("Marco", "Marco El Fenix", 2.03, 45, "Mano Derecha de Barba Blanca", true, 1374000000.0, "Capitán 1º División", "Generación Antigua", barbaBlanca, "/img/piratas/marco.jpg"));
        pirataService.save(new Pirata("Kozuki Oden", "El Samurái más Legendario", 3.82, 39, "Samurai de Wano y Miembro de Confianza para Barbablanca", false, 2800000000.0, "Capitán 2º División", "Generación Antigua", barbaBlanca, "/img/piratas/oden.jpg"));
        pirataService.save(new Pirata("Jozu", "Jozu El Diamante", 5.03, 42, "", false, 0.0, "Capitán 3º División", "Generación Antigua", barbaBlanca, "/img/piratas/jozu.jpg"));

        pirataService.save(new Pirata("Charlotte Linlin", "Big Mom", 8.80, 68, "Emperador del Mar", true, 4388000000.0, "Capitán", "Generación de Oro", bigMom, "/img/piratas/bigMom.jpg"));
        pirataService.save(new Pirata("Charlotte Katakuri", "Katakuri", 5.09, 48, "El hombre que su espalda no toca el suelo", true, 1057000000.0, "Segundo al Mando", "Generación de Oro", bigMom, "/img/piratas/katakuri.jpg"));
        pirataService.save(new Pirata("Charlotte Smoothie", "Smoothie", 4.64, 35, "Será fiel a su Madre hasta su Muerte", true, 932000000.0, "Comandante", "Generación de Oro", bigMom, "/img/piratas/smoothie.jpg"));
        pirataService.save(new Pirata("Charlotte Cracker", "Cracker el Mil Brazos", 3.07, 45, "", true, 860000000.0, "Comandante", "Generación de Oro", bigMom, "/img/piratas/cracker.jpg"));
        pirataService.save(new Pirata("Charlotte Perospero", "Perospero", 3.33, 50, "Primer Hijo de Big Mom", true, 700000000.0, "Oficial", "Generación de Oro", bigMom, "/img/piratas/perospero.jpg"));

        pirataService.save(new Pirata("Marshall D. Teach", "Barba Negra", 3.44, 40, "Único portador de dos Frutas del Diablo", true, 3996000000.0, "Capitán", "Peor Generación", barbaNegra, "/img/piratas/barbanegra.jpg"));
        pirataService.save(new Pirata("Jesus Burgess", "El Campeón", 3.55, 29, "Capitán de la 1º División de Barbanegra", true, 0.0, "Capitán 1º división", "Peor Generación", barbaNegra, "/img/piratas/burgess.jpg"));
        pirataService.save(new Pirata("Kuzan", "Aokiji", 2.98, 49, "Ex-miembro de la Marina", true, 0.0, "Nuevo Miembro", "Peor Generación", barbaNegra, "/img/piratas/kuzanPirat.jpg"));
        pirataService.save(new Pirata("Shiryu", "Shiryu de la Lluvia", 3.40, 44, "Ex-Carcelero Jefe de Impel Down", true, 0.0, "Capitán 2º división", "Peor Generación", barbaNegra, "/img/piratas/shiryu.jpg"));
        pirataService.save(new Pirata("Van Augur", "El Supersónico", 3.40, 27, "Tirador de los Piratas de Barbanegra", true, 64000000.0, "Capitán 3º división", "Peor Generación", barbaNegra, "/img/piratas/vanAugur.jpg"));
        pirataService.save(new Pirata("Avalo Pizarro", "El Rey Corrupto", 5.05, 42, "Fue liberado de Impel Down por su Capitán", true, 0.0, "Capitán 4º división", "Peor Generación", barbaNegra, "/img/piratas/pizarro.jpg"));

        pirataService.save(new Pirata("Shanks", "El Pelirrojo", 1.99, 39, "Miembro de la tripulación de Gol D. Roger y persona que inspiro a Luffy para ser Pirata", true, 4048900000.0, "Capitán", "Generación de Oro", pelirrojo, "/img/piratas/shanks.jpg"));
        pirataService.save(new Pirata("Benn Beckman", "", 2.06, 50, "Personaje con mas coeficiente de la Historia y Mano derecha de Shanks", true, 0.0, "1º Oficial", "Generación de Oro", pelirrojo, "/img/piratas/benn.jpg"));

        Tripulacion donquijote = tripulacionInitializer.getTripulacionPorNombre("Piratas de Donquijote");

        pirataService.save(new Pirata("Donquijote Doflamingo", "Joker", 3.05, 41, "Antiguo Rey Celestial y Shichibukai que controlaba Dressrosa desde las sombras", false, 340000000.0, "Capitán", "Generación de Oro", donquijote, "/img/piratas/doflamingo.jpg"));
        pirataService.save(new Pirata("Trebol", "Trebol", 2.97, 46, "Oficial del clan Donquijote y uno de los tres grandes pilares del grupo", false, 2300000000.0, "Oficial Trébol", "Generación de Oro", donquijote, "/img/piratas/trebol.jpg"));
        pirataService.save(new Pirata("Pica", "Pica", 2.31, 42, "Oficial del clan Donquijote con una voz aguda que contrasta con su enorme tamaño", false, 3000000000.0, "Oficial Pica", "Generación de Oro", donquijote, "/img/piratas/pica.jpg"));
        pirataService.save(new Pirata("Diamante", "Diamante", 2.25, 39, "Oficial del clan Donquijote y encargado de los Juegos de la Flama en Dressrosa", false, 3600000000.0, "Oficial Diamante", "Generación de Oro", donquijote, "/img/piratas/diamante.jpg"));
        pirataService.save(new Pirata("Vergo", "Vergo el Soldado de Acero", 2.10, 41, "Infiltrado durante años en la Marina como vicemarino y espía de Doflamingo", false, 0.0, "Oficial Corazón", "Generación de Oro", donquijote, "/img/piratas/vergo.jpg"));
        pirataService.save(new Pirata("Monet", "Monet la Nieve", 1.60, 25, "Observadora de Doflamingo en la isla Punk Hazard transformada en arpía", false, 0.0, "Agente", "Generación de Oro", donquijote, "/img/piratas/monet.jpg"));
        pirataService.save(new Pirata("Sugar", "Sugar", 1.58, 22, "Aparenta ser una niña pero su fruta detuvo su envejecimiento a los diez años", false, 0.0, "Oficial", "Generación de Oro", donquijote, "/img/piratas/sugar.jpg"));
    }
}
