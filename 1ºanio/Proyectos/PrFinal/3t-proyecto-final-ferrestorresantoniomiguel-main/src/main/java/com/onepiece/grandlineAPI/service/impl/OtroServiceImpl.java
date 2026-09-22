package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.Otro;
import com.onepiece.grandlineAPI.repository.OtroRepository;
import com.onepiece.grandlineAPI.service.OtroService;

/**
 * implementacion del servicio de personajes de tipo otro
 * contiene la logica de negocio para gestionarlos
 * permite buscar por lugar de origen o trabajo ademas de las operaciones basicas
 */
@Service
public class OtroServiceImpl implements OtroService {

    /** repositorio que se encarga de hablar con la base de datos */
    @Autowired
    private OtroRepository otroRepository;

    /**
     * devuelve todos los personajes de tipo otro de la base de datos
     *
     * @return lista con todos los personajes
     */
    @Override
    public List<Otro> findAll() {
        return otroRepository.findAll();
    }

    /**
     * busca un personaje por su id
     * si no existe el optional estara vacio
     *
     * @param id el id del personaje que buscamos
     * @return optional con el personaje o vacio si no existe
     */
    @Override
    public Optional<Otro> findById(Long id) {
        return otroRepository.findById(id);
    }

    /**
     * guarda un personaje nuevo o actualiza uno existente
     *
     * @param otro el personaje que queremos guardar
     * @return el personaje guardado con su id
     */
    @Override
    public Otro save(Otro otro) {
        return otroRepository.save(otro);
    }

    /**
     * elimina un personaje de la base de datos por su id
     *
     * @param id el id del personaje a eliminar
     */
    @Override
    public void deleteById(Long id) {
        otroRepository.deleteById(id);
    }

    /**
     * devuelve todos los personajes que tienen un lugar de origen o trabajo concreto
     *
     * @param lugar el lugar por el que queremos filtrar
     * @return lista de personajes de ese lugar
     */
    @Override
    public List<Otro> findByLugar(String lugar) {
        return otroRepository.findByLugar(lugar);
    }

}
