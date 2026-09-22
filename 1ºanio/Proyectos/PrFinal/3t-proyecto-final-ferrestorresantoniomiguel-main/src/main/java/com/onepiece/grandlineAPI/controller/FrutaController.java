package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.service.FrutaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * controlador que maneja las peticiones http relacionadas con las frutas del diablo
 * expone los endpoints de la api bajo la ruta /api/v1/frutas
 * permite hacer las operaciones basicas sobre las frutas del diablo
 */
@RestController
@RequestMapping("/api/v1/frutas")
public class FrutaController {

    /** servicio con la logica de negocio de las frutas */
    @Autowired
    private FrutaService frutaService;

    /**
     * devuelve todas las frutas del diablo que hay en la base de datos
     *
     * @return lista con todas las frutas
     */
    @GetMapping
    public List<Fruta> getall() {
        return frutaService.findAll();
    }

    /**
     * busca una fruta por su id
     * si no existe devuelve null
     *
     * @param id el id de la fruta que buscamos
     * @return la fruta encontrada o null si no existe
     */
    @GetMapping("/{id}")
    public Fruta getById(@PathVariable Long id) {
        return frutaService.findById(id).orElse(null);
    }

    /**
     * busca una fruta por su nombre exacto
     *
     * @param nombre el nombre de la fruta que buscamos
     * @return la fruta encontrada o null si no existe
     */
    @GetMapping("/nombre/{nombre}")
    public Fruta getByNombre(@PathVariable String nombre) {
        return frutaService.findByNombre(nombre);
    }

    /**
     * crea una nueva fruta del diablo con los datos del body
     *
     * @param fruta los datos de la fruta a crear
     * @return la fruta creada con su id generado
     */
    @PostMapping
    public Fruta create(@RequestBody Fruta fruta) {
        return frutaService.save(fruta);
    }

    /**
     * actualiza una fruta existente con los nuevos datos
     * si la fruta no existe no hace nada y devuelve null
     *
     * @param id el id de la fruta a actualizar
     * @param fruta los nuevos datos de la fruta
     * @return la fruta actualizada o null si no existia
     */
    @PutMapping("/{id}")
    public Fruta update(@PathVariable Long id, @RequestBody Fruta fruta) {
        Fruta resultado = null;
        if (frutaService.findById(id).isPresent()) {
            fruta.setId(id);
            resultado = frutaService.save(fruta);
        }
        return resultado;
    }

    /**
     * elimina una fruta de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id de la fruta a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (frutaService.findById(id).isPresent()) {
            frutaService.deleteById(id);
        }
    }

}
