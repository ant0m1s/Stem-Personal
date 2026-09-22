package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.GobiernoMundial;
import com.onepiece.grandlineAPI.repository.GobiernoMundialRepository;
import com.onepiece.grandlineAPI.service.GobiernoMundialService;

/**
 * implementacion del servicio del gobierno mundial
 * contiene la logica de negocio para gestionar los miembros del gobierno
 * en este caso es bastante directa y delega casi todo al repositorio
 */
@Service
public class GobiernoMundialServiceImpl implements GobiernoMundialService {

    /** repositorio que se encarga de hablar con la base de datos */
    @Autowired
    private GobiernoMundialRepository gobiernoMundialRepository;

    /**
     * devuelve todos los miembros del gobierno mundial de la base de datos
     *
     * @return lista con todos los agentes
     */
    @Override
    public List<GobiernoMundial> findAll() {
        return gobiernoMundialRepository.findAll();
    }

    /**
     * busca un agente por su id
     * si no existe el optional estara vacio
     *
     * @param id el id del agente que buscamos
     * @return optional con el agente o vacio si no existe
     */
    @Override
    public Optional<GobiernoMundial> findById(Long id) {
        return gobiernoMundialRepository.findById(id);
    }

    /**
     * guarda un agente nuevo o actualiza uno existente
     *
     * @param gobiernoMundial el agente que queremos guardar
     * @return el agente guardado con su id
     */
    @Override
    public GobiernoMundial save(GobiernoMundial gobiernoMundial) {
        return gobiernoMundialRepository.save(gobiernoMundial);
    }

    /**
     * elimina un agente de la base de datos por su id
     *
     * @param id el id del agente a eliminar
     */
    @Override
    public void deleteById(Long id) {
        gobiernoMundialRepository.deleteById(id);
    }

    /**
     * devuelve todos los agentes que tienen un rango concreto
     *
     * @param rango el rango por el que queremos filtrar
     * @return lista de agentes con ese rango
     */
    @Override
    public List<GobiernoMundial> findByRango(String rango) {
        return gobiernoMundialRepository.findByRango(rango);
    }

}
