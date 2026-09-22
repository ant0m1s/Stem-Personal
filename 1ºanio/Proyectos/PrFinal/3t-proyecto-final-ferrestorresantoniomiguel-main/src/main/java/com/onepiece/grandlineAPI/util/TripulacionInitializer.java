package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Tripulacion;
import com.onepiece.grandlineAPI.service.TripulacionService;

@Component
public class TripulacionInitializer {

    @Autowired
    private TripulacionService tripulacionService;

    public void inicializarTripulaciones() {
        tripulacionService.save(new Tripulacion("Piratas del Sombrero de Paja", 10, "Protagonista del Anime y Futura Tripulación que conquistará el Grand Line", 3.131, "Peor Generación", "img/tripulaciones/sombreroPaja.jpg"));
        tripulacionService.save(new Tripulacion("Piratas del Corazón", 20, "Notoria Banda de Piratas del North Blue", 500.0, "Peor Generación", "img/tripulaciones/heart.jpg"));
        tripulacionService.save(new Tripulacion("Piratas de Barba Blanca", 1617, "Tripulación Disuelta del Fallecido Emperador Barbablanca", 5.046, "Generación Antigua", "img/tripulaciones/barbablanca.jpg"));
        tripulacionService.save(new Tripulacion("Piratas de Big Mom", 43, "Poderosa e Infame Banda de Piratas ubicada en la Nación Totto Land", 4.388, "Generación de Oro", "img/tripulaciones/bigMom.jpg"));
        tripulacionService.save(new Tripulacion("Piratas de Barba Negra", 10, "Infame Banda liderada por el Emperador Barbanegra", 3.996, "Peor Generación", "img/tripulaciones/barbanegra.jpg"));
        tripulacionService.save(new Tripulacion("Piratas del Pelirrojo", 10, "Poderosa banda piratas liderada por Shanks el Pelirrojo", 4.142, "Generación de Oro", "img/tripulaciones/pelirrojo.jpg"));
        tripulacionService.save(new Tripulacion("Piratas de Donquijote", 7, "Infame banda pirata que controló el reino de Dressrosa desde las sombras", 2.200, "Generación de Oro", "img/tripulaciones/donquijote.jpg"));
    }
    public Tripulacion getTripulacionPorNombre(String nombre) {
        return tripulacionService.findByNombre(nombre);
    }
}
