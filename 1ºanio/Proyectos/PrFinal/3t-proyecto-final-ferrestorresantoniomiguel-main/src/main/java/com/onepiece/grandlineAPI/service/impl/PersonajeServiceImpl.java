package com.onepiece.grandlineAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.repository.PersonajeRepository;
import com.onepiece.grandlineAPI.repository.FrutaRepository;
import com.onepiece.grandlineAPI.service.PersonajeService;

/**
 * implementacion del servicio de personajes
 * ademas de las operaciones basicas tiene logica para gestionar las frutas del diablo
 * anadir y quitar frutas requiere cargar el personaje desde la base de datos primero
 * para no perder los datos que ya tenia guardados
 */
@Service
public class PersonajeServiceImpl implements PersonajeService {

    /** repositorio que accede a la tabla de personajes */
    @Autowired
    private PersonajeRepository personajeRepository;

    /** repositorio de frutas necesario para buscar la fruta por id antes de asociarla */
    @Autowired
    private FrutaRepository frutaRepository;

    /**
     * devuelve todos los personajes de la base de datos sin importar su tipo
     *
     * @return lista con todos los personajes
     */
    @Override
    public List<Personaje> findAll() {
        return personajeRepository.findAll();
    }

    /**
     * busca un personaje por su id
     * si no existe el optional estara vacio
     *
     * @param id el id del personaje que buscamos
     * @return optional con el personaje o vacio si no existe
     */
    @Override
    public Optional<Personaje> findById(Long id) {
        return personajeRepository.findById(id);
    }

    /**
     * elimina un personaje de la base de datos por su id
     *
     * @param id el id del personaje a eliminar
     */
    @Override
    public void deleteById(Long id) {
        personajeRepository.deleteById(id);
    }

    /**
     * anade una fruta del diablo a un personaje
     * carga el personaje y la fruta desde la base de datos para no perder datos
     * el transactional asegura que todo se guarda o nada si hay algun error
     *
     * @param personajeId el id del personaje al que queremos anadir la fruta
     * @param frutaId el id de la fruta que queremos anadir
     * @return el personaje actualizado con la nueva fruta
     */
    @Override
    @Transactional
    public Personaje anadirFruta(Long personajeId, Long frutaId) {
        Personaje personaje = personajeRepository.findById(personajeId)
                .orElseThrow(() -> new RuntimeException("No se encontro personaje"));
        Fruta fruta = frutaRepository.findById(frutaId).orElseThrow(() -> new RuntimeException("No se encontro fruta"));
        personaje.getFrutas().add(fruta);
        return personajeRepository.save(personaje);
    }

    /**
     * elimina una fruta del diablo de un personaje
     * carga ambos desde la base de datos y luego quita la fruta de la lista
     *
     * @param personajeId el id del personaje al que queremos quitar la fruta
     * @param frutaId el id de la fruta que queremos quitar
     * @return el personaje actualizado sin esa fruta
     */
    @Override
    public Personaje borrarFruta(Long personajeId, Long frutaId) {
        Personaje personaje = personajeRepository.findById(personajeId)
                .orElseThrow(() -> new RuntimeException("No se encontro personaje"));
        Fruta fruta = frutaRepository.findById(frutaId).orElseThrow(() -> new RuntimeException("No se encontro fruta"));
        personaje.getFrutas().remove(fruta);
        return personajeRepository.save(personaje);
    }

    /**
     * devuelve la lista de frutas que tiene un personaje concreto
     *
     * @param personajeId el id del personaje
     * @return lista de frutas del personaje puede estar vacia
     */
    @Override
    public List<Fruta> getFrutasByPersonajeId(Long personajeId) {
        Personaje personaje = personajeRepository.findById(personajeId)
                .orElseThrow(() -> new RuntimeException("No se encontro personaje"));
        return personaje.getFrutas();
    }

    /**
     * busca un personaje por su nombre completo exacto
     *
     * @param nombreCompleto el nombre completo del personaje
     * @return el personaje encontrado o null si no existe
     */
    @Override
    public Personaje findByNombreCompleto(String nombreCompleto) {
        return personajeRepository.findByNombreCompleto(nombreCompleto);
    }

}
