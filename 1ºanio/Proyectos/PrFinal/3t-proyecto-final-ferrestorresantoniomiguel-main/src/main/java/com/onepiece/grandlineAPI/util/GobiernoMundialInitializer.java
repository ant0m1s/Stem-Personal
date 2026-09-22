package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.GobiernoMundial;
import com.onepiece.grandlineAPI.service.GobiernoMundialService;

@Component
public class GobiernoMundialInitializer {

    @Autowired
    private GobiernoMundialService gobiernoMundialService;

    public void inicializarGobiernoMundial() {
        gobiernoMundialService.save(new GobiernoMundial("Sengoku", "El Buda", 2.78, 79, "Antiguo Almirante de Flota de la Marina", true, "Ex-Almirante de Flota", "/img/gobierno/sengoku.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("Borsalino", "Kizaru", 3.02, 58, "Almirante de Flota de la Marina", true, "Almirante", "/img/gobierno/kizaru.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("Sakazuki", "Akainu", 3.06, 55, "Almirante de Flota de la Marina", true, "Almirante de Flota", "/img/gobierno/akainu.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("Monkey D. Garp", "El Héroe", 2.87, 78, "El Héroe de la Marina", true, "Vicealmirante", "/img/gobierno/monkeyGarp.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("Kuzan (pre-timeskip)", "Aokiji", 2.98, 47, "Ex-Almirante de la Marina", true, "Ex-Almirante", "/img/gobierno/kuzanGob.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("Rob Lucci", "El Asesino", 1.91, 30, "Agente Élite de los Cipher Pol", true, "Agente CP0", "/img/gobierno/lucci.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Jaygarcia Saturn", "Saturn", 2.49, 200, "Antes de morir, fue uno de los Cinco Anciones y líderes del Gobierno Mundial", false, "Ex-Cinco Ancianos", "img/gobierno/saturn.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Marcus Mars", "Mars", 3.67, 200, "Uno de los Cinco Anciones, se le llama Dios Guerrero del Medioambiente", true, "Cinco Ancianos", "img/gobierno/mars.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Topman Warcury", "Warcury", 2.67, 200, "Uno de los Cinco Anciones, se le llama Dios Guerrero de la Ley", true, "Cinco Ancianos", "img/gobierno/warcury.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Ethanbaron V. Nusjuro", "", 2.59, 200, "Uno de los Cinco Anciones, se le llama Dios Guerrero de las Finanzas", true, "Cinco Ancianos", "img/gobierno/nusjuro.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Shepherd Ju Peter", "", 3.00, 200, "Uno de los Cinco Anciones, se le llama Dios Guerrero de la Agricultura", true, "Cinco Ancianos", "img/gobierno/peter.jpg"));
        gobiernoMundialService.save(new GobiernoMundial("San Figarland Garling", "Garling", 2.00, 0, "Último miembro de los Cinco Anciones, se le dice Dios Guerrero de la Ciencia y Defensa", true, "Cinco Ancianos", "img/gobierno/garling.jpg"));
    }
}
