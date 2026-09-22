package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Otro;
import com.onepiece.grandlineAPI.service.OtroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * controlador que maneja las peticiones http de los personajes de tipo otro
 * expone los endpoints bajo la ruta /api/v1/otros
 * permite las operaciones basicas y filtrar por lugar de origen o trabajo
 */
@RestController
@RequestMapping("/api/v1/otros")
public class OtroController {

    /** servicio con la logica de negocio de los personajes de tipo otro */
    @Autowired
    private OtroService otroService;

    /**
     * devuelve todos los personajes de tipo otro que hay en la base de datos
     *
     * @return lista con todos los personajes
     */
    @GetMapping
    public List<Otro> getAll() {
        return otroService.findAll();
    }

    /**
     * busca un personaje de tipo otro por su id
     * si no existe devuelve null
     *
     * @param id el id del personaje que buscamos
     * @return el personaje encontrado o null si no existe
     */
    @GetMapping("/{id}")
    public Otro getById(@PathVariable Long id) {
        return otroService.findById(id).orElse(null);
    }

    /**
     * busca todos los personajes que tienen un lugar de origen o trabajo concreto
     *
     * @param lugar el lugar por el que queremos filtrar por ejemplo alabasta
     * @return lista de personajes de ese lugar
     */
    @GetMapping("/lugar/{lugar}")
    public List<Otro> getByLugar(@PathVariable String lugar) {
        return otroService.findByLugar(lugar);
    }

    /**
     * crea un nuevo personaje de tipo otro con los datos del body
     *
     * @param otro los datos del personaje a crear
     * @return el personaje creado con su id generado
     */
    @PostMapping
    public Otro create(@RequestBody Otro otro) {
        return otroService.save(otro);
    }

    /**
     * actualiza un personaje existente preservando sus frutas del diablo
     * si no existe no hace nada y devuelve null
     *
     * @param id el id del personaje a actualizar
     * @param otro los nuevos datos del personaje
     * @return el personaje actualizado o null si no existia
     */
    @PutMapping("/{id}")
    public Otro update(@PathVariable Long id, @RequestBody Otro otro) {
        return otroService.findById(id).map(existing -> {
            otro.setId(id);
            otro.setFrutas(existing.getFrutas());
            return otroService.save(otro);
        }).orElse(null);
    }

    /**
     * elimina un personaje de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id del personaje a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (otroService.findById(id).isPresent()) {
            otroService.deleteById(id);
        }
    }

}
