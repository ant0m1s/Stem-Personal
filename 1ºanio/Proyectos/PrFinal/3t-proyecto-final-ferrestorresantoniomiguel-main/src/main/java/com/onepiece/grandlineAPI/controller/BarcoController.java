package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Barco;
import com.onepiece.grandlineAPI.service.BarcoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * controlador que maneja las peticiones http relacionadas con los barcos
 * expone los endpoints de la api bajo la ruta /api/v1/barcos
 * permite hacer las operaciones basicas de crear leer actualizar y borrar
 */
@RestController
@RequestMapping("/api/v1/barcos")
public class BarcoController {

    /** servicio que contiene la logica de negocio de los barcos */
    @Autowired
    private BarcoService barcoService;

    /**
     * devuelve todos los barcos que hay en la base de datos
     *
     * @return lista con todos los barcos
     */
    @GetMapping
    public List<Barco> getAll() {
        return barcoService.findAll();
    }

    /**
     * busca un barco por su id y lo devuelve
     * si no existe devuelve null
     *
     * @param id el id del barco que queremos buscar
     * @return el barco encontrado o null si no existe
     */
    @GetMapping("/{id}")
    public Barco getById(@PathVariable Long id) {
        return barcoService.findById(id).orElse(null);
    }

    /**
     * crea un nuevo barco con los datos que llegan en el body de la peticion
     *
     * @param barco los datos del barco a crear
     * @return el barco creado con su id generado
     */
    @PostMapping
    public Barco create(@RequestBody Barco barco) {
        return barcoService.save(barco);
    }

    /**
     * actualiza un barco existente con los nuevos datos
     * si el barco no existe no hace nada y devuelve null
     *
     * @param id el id del barco que queremos actualizar
     * @param barco los nuevos datos del barco
     * @return el barco actualizado o null si no existia
     */
    @PutMapping("/{id}")
    public Barco update(@PathVariable Long id, @RequestBody Barco barco) {
        Barco resultado = null;
        if (barcoService.findById(id).isPresent()) {
            barco.setId(id);
            resultado = barcoService.save(barco);
        }
        return resultado;
    }

    /**
     * elimina un barco de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id del barco a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (barcoService.findById(id).isPresent()) {
            barcoService.deleteById(id);
        }
    }

}
