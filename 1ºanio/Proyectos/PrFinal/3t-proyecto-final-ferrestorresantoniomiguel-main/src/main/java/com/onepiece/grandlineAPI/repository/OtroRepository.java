package com.onepiece.grandlineAPI.repository;

import com.onepiece.grandlineAPI.entity.Otro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtroRepository extends JpaRepository<Otro, Long> {
    List<Otro> findByLugar(String oficio);
}