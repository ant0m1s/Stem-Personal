package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.GobiernoMundial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GobiernoMundialRepository extends JpaRepository<GobiernoMundial, Long> {
    List<GobiernoMundial> findByRango(String rango);
}