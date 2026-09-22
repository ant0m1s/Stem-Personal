package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.GobiernoMundial;

public interface GobiernoMundialService {
    List<GobiernoMundial> findAll();
    Optional<GobiernoMundial> findById(Long id);
    GobiernoMundial save(GobiernoMundial gobiernoMundial);
    void deleteById(Long id);
    List<GobiernoMundial> findByRango(String rango);
}
