package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Pirata;

public interface PirataService {
    List<Pirata> findAll();
    Optional<Pirata> findById(Long id);
    Pirata save(Pirata pirata);
    void deleteById(Long id);
    List<Pirata> findByTripulacion_Id(Long tripulacionId);
    List<Pirata> findByGeneracion(String generacion);
}
