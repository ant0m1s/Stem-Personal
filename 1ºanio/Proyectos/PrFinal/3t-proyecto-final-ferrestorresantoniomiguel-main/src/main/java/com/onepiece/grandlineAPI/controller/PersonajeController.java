package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.service.PersonajeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * controlador que maneja las peticiones http relacionadas con los personajes
 * expone endpoints bajo la ruta /api/v1/personajes
 * ademas de las operaciones basicas permite gestionar las frutas del diablo de cada personaje
 * es el controlador que usan todos los subtipos de personaje para manejar sus frutas
 */
@RestController
@RequestMapping("/api/v1/personajes")
public class PersonajeController {

    /** servicio con la logica de negocio de los personajes */
    @Autowired
    private PersonajeService personajeService;

    /**
     * devuelve todos los personajes de la base de datos sin importar su tipo
     *
     * @return lista con todos los personajes
     */
    @GetMapping
    public List<Personaje> getAll() {
        return personajeService.findAll();
    }

    /**
     * busca un personaje por su id
     * si no existe devuelve null
     *
     * @param id el id del personaje que buscamos
     * @return el personaje encontrado o null si no existe
     */
    @GetMapping("/{id}")
    public Personaje getById(@PathVariable Long id) {
        return personajeService.findById(id).orElse(null);
    }

    /**
     * devuelve la lista de frutas del diablo que tiene un personaje
     *
     * @param id el id del personaje
     * @return lista de frutas del personaje puede estar vacia
     */
    @GetMapping("/{id}/frutas")
    public List<Fruta> getFrutas(@PathVariable Long id) {
        return personajeService.getFrutasByPersonajeId(id);
    }

    /**
     * anade una fruta del diablo a un personaje
     * se usa al crear o editar un personaje desde el formulario
     *
     * @param id el id del personaje
     * @param frutaId el id de la fruta que queremos anadir
     * @return el personaje actualizado con la nueva fruta
     */
    @PostMapping("/{id}/frutas/{frutaId}")
    public Personaje addFruta(@PathVariable Long id, @PathVariable Long frutaId) {
        return personajeService.anadirFruta(id, frutaId);
    }

    /**
     * elimina una fruta del diablo de un personaje
     * se usa al editar cuando el usuario desmarca una fruta
     *
     * @param id el id del personaje
     * @param frutaId el id de la fruta que queremos quitar
     * @return el personaje actualizado sin esa fruta
     */
    @DeleteMapping("/{id}/frutas/{frutaId}")
    public Personaje removeFruta(@PathVariable Long id, @PathVariable Long frutaId) {
        return personajeService.borrarFruta(id, frutaId);
    }

    /**
     * elimina un personaje de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id del personaje a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (personajeService.findById(id).isPresent()) {
            personajeService.deleteById(id);
        }
    }

}
