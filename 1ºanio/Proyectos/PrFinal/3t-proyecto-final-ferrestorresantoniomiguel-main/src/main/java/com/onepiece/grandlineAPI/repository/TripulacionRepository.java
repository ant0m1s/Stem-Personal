package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Tripulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TripulacionRepository extends JpaRepository<Tripulacion, Long> {
    Tripulacion findByNombre(String nombre);
    List<Tripulacion> findByGeneracion(String generacion);
}