package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.Pirata;
import com.onepiece.grandlineAPI.repository.PirataRepository;
import com.onepiece.grandlineAPI.service.PirataService;

/**
 * implementacion del servicio de piratas
 * contiene la logica de negocio para gestionar los piratas
 * permite buscar por tripulacion y por generacion ademas de las operaciones basicas
 */
@Service
public class PirataServiceImpl implements PirataService {

    /** repositorio que se encarga de hablar con la base de datos */
    @Autowired
    private PirataRepository pirataRepository;

    /**
     * devuelve todos los piratas de la base de datos
     *
     * @return lista con todos los piratas
     */
    @Override
    public List<Pirata> findAll() {
        return pirataRepository.findAll();
    }

    /**
     * busca un pirata por su id
     * si no existe el optional estara vacio
     *
     * @param id el id del pirata que buscamos
     * @return optional con el pirata o vacio si no existe
     */
    @Override
    public Optional<Pirata> findById(Long id) {
        return pirataRepository.findById(id);
    }

    /**
     * guarda un pirata nuevo o actualiza uno existente
     *
     * @param pirata el pirata que queremos guardar
     * @return el pirata guardado con su id
     */
    @Override
    public Pirata save(Pirata pirata) {
        return pirataRepository.save(pirata);
    }

    /**
     * elimina un pirata de la base de datos por su id
     *
     * @param id el id del pirata a eliminar
     */
    @Override
    public void deleteById(Long id) {
        pirataRepository.deleteById(id);
    }

    /**
     * devuelve todos los piratas que pertenecen a una tripulacion concreta
     *
     * @param tripulacionId el id de la tripulacion
     * @return lista de piratas de esa tripulacion
     */
    @Override
    public List<Pirata> findByTripulacion_Id(Long tripulacionId) {
        return pirataRepository.findByTripulacion_Id(tripulacionId);
    }

    /**
     * devuelve todos los piratas que pertenecen a una generacion concreta
     *
     * @param generacion la generacion por la que queremos filtrar
     * @return lista de piratas de esa generacion
     */
    @Override
    public List<Pirata> findByGeneracion(String generacion) {
        return pirataRepository.findByGeneracion(generacion);
    }

}
