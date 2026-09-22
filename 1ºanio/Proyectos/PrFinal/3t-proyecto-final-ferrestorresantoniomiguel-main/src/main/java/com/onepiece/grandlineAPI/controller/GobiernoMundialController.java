package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.GobiernoMundial;
import com.onepiece.grandlineAPI.service.GobiernoMundialService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * controlador que maneja las peticiones http de los miembros del gobierno mundial
 * expone los endpoints bajo la ruta /api/v1/gobierno-mundial
 * permite hacer las operaciones basicas y filtrar por rango
 */
@RestController
@RequestMapping("/api/v1/gobierno-mundial")
public class GobiernoMundialController {

    /** servicio con la logica de negocio del gobierno mundial */
    @Autowired
    private GobiernoMundialService gobiernoMundialService;

    /**
     * devuelve todos los miembros del gobierno mundial que hay en la base de datos
     *
     * @return lista con todos los agentes
     */
    @GetMapping
    public List<GobiernoMundial> getAll() {
        return gobiernoMundialService.findAll();
    }

    /**
     * busca un miembro del gobierno mundial por su id
     * si no existe devuelve null
     *
     * @param id el id del agente que buscamos
     * @return el agente encontrado o null si no existe
     */
    @GetMapping("/{id}")
    public GobiernoMundial getById(@PathVariable Long id) {
        return gobiernoMundialService.findById(id).orElse(null);
    }

    /**
     * busca todos los agentes que tienen un rango concreto
     *
     * @param rango el rango por el que queremos filtrar por ejemplo almirante
     * @return lista de agentes con ese rango
     */
    @GetMapping("/rango/{rango}")
    public List<GobiernoMundial> getByrango(@PathVariable String rango) {
        return gobiernoMundialService.findByRango(rango);
    }

    /**
     * crea un nuevo miembro del gobierno mundial con los datos del body
     *
     * @param gobiernoMundial los datos del agente a crear
     * @return el agente creado con su id generado
     */
    @PostMapping
    public GobiernoMundial create(@RequestBody GobiernoMundial gobiernoMundial) {
        return gobiernoMundialService.save(gobiernoMundial);
    }

    /**
     * actualiza un agente existente preservando sus frutas del diablo
     * si no existe no hace nada y devuelve null
     *
     * @param id el id del agente a actualizar
     * @param gobiernoMundial los nuevos datos del agente
     * @return el agente actualizado o null si no existia
     */
    @PutMapping("/{id}")
    public GobiernoMundial update(@PathVariable Long id, @RequestBody GobiernoMundial gobiernoMundial) {
        return gobiernoMundialService.findById(id).map(existing -> {
            gobiernoMundial.setId(id);
            gobiernoMundial.setFrutas(existing.getFrutas());
            return gobiernoMundialService.save(gobiernoMundial);
        }).orElse(null);
    }

    /**
     * elimina un agente de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id del agente a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (gobiernoMundialService.findById(id).isPresent()) {
            gobiernoMundialService.deleteById(id);
        }
    }

}
