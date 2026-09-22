package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Barco;
import com.onepiece.grandlineAPI.service.BarcoService;

@Component
public class BarcoInitializazer {

    @Autowired
    private BarcoService barcoService;

    @Autowired
    private TripulacionInitializer tripulacionInitializer;

    public void inicializarBarcos() {
        barcoService.save(new Barco("Thousand Sunny", true, tripulacionInitializer.getTripulacionPorNombre("Piratas del Sombrero de Paja"), "/img/barcos/sunny.jpg"));
        barcoService.save(new Barco("Going Merry", false, tripulacionInitializer.getTripulacionPorNombre("Piratas del Sombrero de Paja"), "/img/barcos/merry.jpg"));
        barcoService.save(new Barco("Polar Tang", true, tripulacionInitializer.getTripulacionPorNombre("Piratas del Corazón"), "/img/barcos/polar.jpg"));
        barcoService.save(new Barco("Moby Dick", false, tripulacionInitializer.getTripulacionPorNombre("Piratas de Barba Blanca"), "/img/barcos/moby.jpg"));
        barcoService.save(new Barco("Queen Mama Chanter", true, tripulacionInitializer.getTripulacionPorNombre("Piratas de Big Mom"), "/img/barcos/mama.jpg"));
        barcoService.save(new Barco("Saber of Xebec", true, tripulacionInitializer.getTripulacionPorNombre("Piratas de Barba Negra"), "/img/barcos/saber.jpg"));
        barcoService.save(new Barco("Numancia Dragon", false, tripulacionInitializer.getTripulacionPorNombre("Piratas de Donquijote"), "/img/barcos/numancia.png"));

    }
}
