package com.onepiece.grandlineAPI.service;

import java.util.List;
import java.util.Optional;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;

public interface PersonajeService {
    List<Personaje> findAll();
    Optional<Personaje> findById(Long id);
    void deleteById(Long id);
    Personaje anadirFruta(Long personajeId, Long frutaId);
    Personaje borrarFruta(Long personajeId, Long frutaId);
    List<Fruta> getFrutasByPersonajeId(Long personajeId);
    Personaje findByNombreCompleto(String nombreCompleto);

}
