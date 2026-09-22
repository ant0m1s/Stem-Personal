package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.Tripulacion;
import com.onepiece.grandlineAPI.repository.TripulacionRepository;
import com.onepiece.grandlineAPI.service.TripulacionService;

/**
 * implementacion del servicio de tripulaciones
 * contiene la logica de negocio para gestionar las tripulaciones
 * permite buscar por nombre y por generacion ademas de las operaciones basicas
 */
@Service
public class TripulacionServiceImpl implements TripulacionService {

    /** repositorio que se encarga de hablar con la base de datos */
    @Autowired
    private TripulacionRepository tripulacionRepository;

    /**
     * devuelve todas las tripulaciones de la base de datos
     *
     * @return lista con todas las tripulaciones
     */
    @Override
    public List<Tripulacion> findAll() {
        return tripulacionRepository.findAll();
    }

    /**
     * busca una tripulacion por su id
     * si no existe el optional estara vacio
     *
     * @param id el id de la tripulacion que buscamos
     * @return optional con la tripulacion o vacio si no existe
     */
    @Override
    public Optional<Tripulacion> findById(Long id) {
        return tripulacionRepository.findById(id);
    }

    /**
     * guarda una tripulacion nueva o actualiza una existente
     *
     * @param tripulacion la tripulacion que queremos guardar
     * @return la tripulacion guardada con su id
     */
    @Override
    public Tripulacion save(Tripulacion tripulacion) {
        return tripulacionRepository.save(tripulacion);
    }

    /**
     * elimina una tripulacion de la base de datos por su id
     *
     * @param id el id de la tripulacion a eliminar
     */
    @Override
    public void deleteById(Long id) {
        tripulacionRepository.deleteById(id);
    }

    /**
     * busca una tripulacion por su nombre exacto
     *
     * @param nombre el nombre de la tripulacion que buscamos
     * @return la tripulacion encontrada o null si no existe
     */
    @Override
    public Tripulacion findByNombre(String nombre) {
        return tripulacionRepository.findByNombre(nombre);
    }

    /**
     * devuelve todas las tripulaciones que pertenecen a una generacion concreta
     *
     * @param generacion la generacion por la que queremos filtrar
     * @return lista de tripulaciones de esa generacion
     */
    @Override
    public List<Tripulacion> findByGeneracion(String generacion) {
        return tripulacionRepository.findByGeneracion(generacion);
    }

}
