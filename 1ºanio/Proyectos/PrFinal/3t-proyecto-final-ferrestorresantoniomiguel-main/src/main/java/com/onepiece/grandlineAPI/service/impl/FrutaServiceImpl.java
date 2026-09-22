package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.repository.FrutaRepository;
import com.onepiece.grandlineAPI.repository.PersonajeRepository;
import com.onepiece.grandlineAPI.service.FrutaService;

/**
 * implementacion del servicio de frutas del diablo
 * la mayoria de metodos son simples pero el de borrar tiene logica extra
 * antes de eliminar una fruta hay que desasociarla de todos los personajes que la tenian
 * si no se haria esto daria error de clave foranea en la base de datos
 */
@Service
public class FrutaServiceImpl implements FrutaService {

    /** repositorio que accede a la tabla de frutas */
    @Autowired
    private FrutaRepository frutaRepository;

    /** repositorio de personajes necesario para limpiar la relacion antes de borrar */
    @Autowired
    private PersonajeRepository personajeRepository;

    /**
     * devuelve todas las frutas del diablo de la base de datos
     *
     * @return lista con todas las frutas
     */
    @Override
    public List<Fruta> findAll() {
        return frutaRepository.findAll();
    }

    /**
     * guarda una fruta nueva o actualiza una existente
     *
     * @param fruta la fruta que queremos guardar
     * @return la fruta guardada con su id
     */
    @Override
    public Fruta save(Fruta fruta) {
        return frutaRepository.save(fruta);
    }

    /**
     * busca una fruta por su id
     * si no existe el optional estara vacio
     *
     * @param id el id de la fruta que buscamos
     * @return optional con la fruta o vacio si no existe
     */
    @Override
    public Optional<Fruta> findById(Long id) {
        return frutaRepository.findById(id);
    }

    /**
     * elimina una fruta de la base de datos
     * antes de borrarla la desasocia de todos los personajes que la tenian
     * esto es necesario para evitar errores por claves foraneas huerfanas
     *
     * @param id el id de la fruta a eliminar
     */
    @Override
    public void deleteById(Long id) {
        Fruta fruta = frutaRepository.findById(id).orElseThrow(() -> new RuntimeException("Fruta no encontrada"));
        for (Personaje personaje : fruta.getPersonajes()) {
            personaje.getFrutas().remove(fruta);
            personajeRepository.save(personaje);
        }
        frutaRepository.deleteById(id);
    }

    /**
     * busca una fruta por su nombre exacto
     *
     * @param nombre el nombre de la fruta que buscamos
     * @return la fruta encontrada o null si no existe
     */
    @Override
    public Fruta findByNombre(String nombre) {
        return frutaRepository.findByNombre(nombre);
    }

}
