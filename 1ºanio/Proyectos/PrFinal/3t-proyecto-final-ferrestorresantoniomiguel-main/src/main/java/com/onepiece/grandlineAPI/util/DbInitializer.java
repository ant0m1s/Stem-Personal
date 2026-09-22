package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private FrutaInitializer frutaInitializer;

    @Autowired
    private TripulacionInitializer tripulacionInitializer;

    @Autowired
    private PirataInitializer pirataInitializer;

    @Autowired
    private GobiernoMundialInitializer gobiernoMundialInitializer;

    @Autowired
    private OtroInitializer otroInitializer;

    @Autowired
    private BarcoInitializazer barcoInitializazer;

    @Autowired
    private PersonajeFrutaInitializer personajeFrutaInitializer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Empezamos con la carga de datos");
        frutaInitializer.inicializarFrutas();
        tripulacionInitializer.inicializarTripulaciones();
        pirataInitializer.incializarPiratas();
        gobiernoMundialInitializer.inicializarGobiernoMundial();
        otroInitializer.inicializarOtros();
        barcoInitializazer.inicializarBarcos();
        personajeFrutaInitializer.asignarFrutas();
        System.out.println("Terminada carga de datos");
    }

}
