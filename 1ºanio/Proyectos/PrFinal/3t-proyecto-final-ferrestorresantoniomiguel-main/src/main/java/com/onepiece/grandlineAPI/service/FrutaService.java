package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Fruta;

public interface FrutaService {
    List<Fruta> findAll();
    Optional<Fruta> findById(Long id);
    Fruta save(Fruta fruta);
    void deleteById(Long id);
    Fruta findByNombre(String nombre);
}
