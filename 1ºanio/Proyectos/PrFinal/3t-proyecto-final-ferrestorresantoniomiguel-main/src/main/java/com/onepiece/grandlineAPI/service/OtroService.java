package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Otro;

public interface OtroService {
    List<Otro> findAll();
    Optional<Otro> findById(Long id);
    Otro save(Otro otro);
    void deleteById(Long id);
    List<Otro> findByLugar(String lugar);
}
