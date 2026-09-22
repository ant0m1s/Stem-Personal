package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Personaje findByNombreCompleto(String nombreCompleto);
}