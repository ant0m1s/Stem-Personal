package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Barco;

public interface BarcoService {
    List<Barco> findAll();
    Optional<Barco> findById(Long id);
    Barco save(Barco barco);
    void deleteById(Long id);
    List<Barco> findByTripulacionId(Long tripulacionId);
}
