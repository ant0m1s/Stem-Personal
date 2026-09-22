package com.onepiece.grandlineAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onepiece.grandlineAPI.entity.Barco;
import com.onepiece.grandlineAPI.entity.Pirata;
import com.onepiece.grandlineAPI.entity.Tripulacion;
import com.onepiece.grandlineAPI.service.BarcoService;
import com.onepiece.grandlineAPI.service.PirataService;
import com.onepiece.grandlineAPI.service.TripulacionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * controlador que maneja las peticiones http relacionadas con las tripulaciones
 * expone los endpoints bajo la ruta /api/v1/tripulaciones
 * ademas de las operaciones basicas permite obtener los piratas y barcos de cada tripulacion
 * usa tres servicios distintos porque necesita acceder a piratas y barcos tambien
 */
@RestController
@RequestMapping("/api/v1/tripulaciones")
public class TripulacionController {

    /** servicio con la logica de negocio de las tripulaciones */
    @Autowired
    private TripulacionService tripulacionService;

    /** servicio de barcos para obtener los barcos de una tripulacion */
    @Autowired
    private BarcoService barcoService;

    /** servicio de piratas para obtener los piratas de una tripulacion */
    @Autowired
    private PirataService pirataService;

    /**
     * devuelve todas las tripulaciones de la base de datos
     *
     * @return lista con todas las tripulaciones
     */
    @GetMapping
    public List<Tripulacion> getAll() {
        return tripulacionService.findAll();
    }

    /**
     * busca una tripulacion por su id
     * si no existe devuelve null
     *
     * @param id el id de la tripulacion que buscamos
     * @return la tripulacion encontrada o null si no existe
     */
    @GetMapping("/{id}")
    public Tripulacion getById(@PathVariable Long id) {
        return tripulacionService.findById(id).orElse(null);
    }

    /**
     * devuelve todos los barcos que pertenecen a una tripulacion
     *
     * @param id el id de la tripulacion
     * @return lista de barcos de esa tripulacion
     */
    @GetMapping("/{id}/barcos")
    public List<Barco> getBarcos(@PathVariable Long id) {
        return barcoService.findByTripulacionId(id);
    }

    /**
     * devuelve todos los piratas que pertenecen a una tripulacion
     *
     * @param id el id de la tripulacion
     * @return lista de piratas de esa tripulacion
     */
    @GetMapping("/{id}/piratas")
    public List<Pirata> getPiratas(@PathVariable Long id) {
        return pirataService.findByTripulacion_Id(id);
    }

    /**
     * busca tripulaciones que pertenezcan a una generacion concreta
     *
     * @param generacion la generacion por la que queremos filtrar
     * @return lista de tripulaciones de esa generacion
     */
    @GetMapping("/generacion/{generacion}")
    public List<Tripulacion> getMByGeneracion(@PathVariable String generacion) {
        return tripulacionService.findByGeneracion(generacion);
    }

    /**
     * crea una nueva tripulacion con los datos del body
     *
     * @param tripulacion los datos de la tripulacion a crear
     * @return la tripulacion creada con su id generado
     */
    @PostMapping
    public Tripulacion create(@RequestBody Tripulacion tripulacion) {
        return tripulacionService.save(tripulacion);
    }

    /**
     * actualiza una tripulacion existente con los nuevos datos
     * si no existe no hace nada y devuelve null
     *
     * @param id el id de la tripulacion a actualizar
     * @param tripulacion los nuevos datos de la tripulacion
     * @return la tripulacion actualizada o null si no existia
     */
    @PutMapping("/{id}")
    public Tripulacion update(@PathVariable Long id, @RequestBody Tripulacion tripulacion) {
        Tripulacion resultado = null;
        if (tripulacionService.findById(id).isPresent()) {
            tripulacion.setId(id);
            resultado = tripulacionService.save(tripulacion);
        }
        return resultado;
    }

    /**
     * elimina una tripulacion de la base de datos por su id
     * si no existe simplemente no hace nada
     *
     * @param id el id de la tripulacion a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (tripulacionService.findById(id).isPresent()) {
            tripulacionService.deleteById(id);
        }
    }

}
