package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Barco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcoRepository extends JpaRepository<Barco, Long> {
    java.util.List<Barco> findByTripulacion_Id(Long tripulacionId);
}