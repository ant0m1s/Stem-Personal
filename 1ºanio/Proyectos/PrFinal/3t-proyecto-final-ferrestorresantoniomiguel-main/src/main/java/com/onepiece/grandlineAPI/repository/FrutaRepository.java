package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrutaRepository extends JpaRepository<Fruta, Long> {
    Fruta findByNombre(String nombre);
}
