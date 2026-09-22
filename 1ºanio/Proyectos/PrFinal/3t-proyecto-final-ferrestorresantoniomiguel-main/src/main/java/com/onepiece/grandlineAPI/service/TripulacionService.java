package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Tripulacion;

public interface TripulacionService {
    List<Tripulacion> findAll();
    Optional<Tripulacion> findById(Long id);
    Tripulacion save(Tripulacion tripulacion);
    void deleteById(Long id);
    Tripulacion findByNombre(String nombre);
    List<Tripulacion> findByGeneracion(String generacion);
}
