package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.Barco;
import com.onepiece.grandlineAPI.repository.BarcoRepository;
import com.onepiece.grandlineAPI.service.BarcoService;

/**
 * implementacion del servicio de barcos
 * aqui es donde de verdad se ejecuta la logica aunque en este caso
 * es bastante simple porque solo delega en el repositorio
 */
@Service
public class BarcoServiceImpl implements BarcoService {

    /** repositorio que se encarga de hablar con la base de datos */
    @Autowired
    private BarcoRepository barcoRepository;

    /**
     * devuelve todos los barcos de la base de datos
     *
     * @return lista con todos los barcos
     */
    @Override
    public List<Barco> findAll() {
        return barcoRepository.findAll();
    }

    /**
     * busca un barco por su id y lo devuelve dentro de un optional
     * si no existe el optional estara vacio
     *
     * @param id el id del barco que buscamos
     * @return optional con el barco o vacio si no existe
     */
    @Override
    public Optional<Barco> findById(Long id) {
        return barcoRepository.findById(id);
    }

    /**
     * guarda un barco nuevo o actualiza uno existente
     *
     * @param barco el barco que queremos guardar
     * @return el barco guardado con su id
     */
    @Override
    public Barco save(Barco barco) {
        return barcoRepository.save(barco);
    }

    /**
     * elimina un barco de la base de datos por su id
     *
     * @param id el id del barco a eliminar
     */
    @Override
    public void deleteById(Long id) {
        barcoRepository.deleteById(id);
    }

    /**
     * devuelve todos los barcos que pertenecen a una tripulacion concreta
     *
     * @param tripulacionId el id de la tripulacion cuyos barcos queremos obtener
     * @return lista de barcos de esa tripulacion
     */
    @Override
    public List<Barco> findByTripulacionId(Long tripulacionId) {
        return barcoRepository.findByTripulacion_Id(tripulacionId);
    }

}
