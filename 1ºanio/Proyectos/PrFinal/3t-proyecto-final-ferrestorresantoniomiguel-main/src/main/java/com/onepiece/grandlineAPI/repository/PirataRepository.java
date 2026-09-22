package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Pirata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PirataRepository extends JpaRepository<Pirata, Long> {
    List<Pirata> findByTripulacion_Id(Long tripulacionId);
    List<Pirata> findByGeneracion(String generacion);
}