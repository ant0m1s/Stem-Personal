package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Pirata;
import com.onepiece.grandlineAPI.service.PirataService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * controlador que maneja las peticiones http relacionadas con los piratas
 * expone los endpoints bajo la ruta /api/v1/piratas
 * permite las operaciones basicas y filtrar por generacion
 */
@RestController
@RequestMapping("/api/v1/piratas")
public class PirataController {

    /** servicio con la logica de negocio de los piratas */
    @Autowired
    private PirataService pirataService;

    /**
     * devuelve todos los piratas que hay en la base de datos
     *
     * @return lista con todos los piratas
     */
    @GetMapping
    public List<Pirata> getAll() {
        return pirataService.findAll();
    }

    /**
     * busca un pirata por su id
     * si no existe devuelve null
     *
     * @param id el id del pirata que buscamos
     * @return el pirata encontrado o null si no existe
     */
    @GetMapping("/{id}")
    public Pirata getById(@PathVariable Long id) {
        return pirataService.findById(id).orElse(null);
    }

    /**
     * busca todos los piratas que pertenecen a una generacion concreta
     *
     * @param generacion la generacion por la que queremos filtrar
     * @return lista de piratas de esa generacion
     */
    @GetMapping("/generacion/{generacion}")
    public List<Pirata> getByGeneracion(@PathVariable String generacion) {
        return pirataService.findByGeneracion(generacion);
    }

    /**
     * crea un nuevo pirata con los datos del body
     *
     * @param pirata los datos del pirata a crear
     * @return el pirata creado con su id generado
     */
    @PostMapping
    public Pirata create(@RequestBody Pirata pirata) {
        return pirataService.save(pirata);
    }

    /**
     * actualiza un pirata existente preservando sus frutas del diablo
     * si no existe no hace nada y devuelve null
     *
     * @param id el id del pirata a actualizar
     * @param pirata los nuevos datos del pirata
     * @return el pirata actualizado o null si no existia
     */
    @PutMapping("/{id}")
    public Pirata update(@PathVariable Long id, @RequestBody Pirata pirata) {
        return pirataService.findById(id).map(existing -> {
            pirata.setId(id);
            pirata.setFrutas(existing.getFrutas());
            return pirataService.save(pirata);
        }).orElse(null);
    }

    /**
     * elimina un pirata de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id del pirata a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (pirataService.findById(id).isPresent()) {
            pirataService.deleteById(id);
        }
    }

}
