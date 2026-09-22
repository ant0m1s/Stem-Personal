package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Pirata;
import com.onepiece.grandlineAPI.repository.PirataRepository;
import com.onepiece.grandlineAPI.service.impl.PirataServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PirataServiceImplTest {

    @Mock
    private PirataRepository pirataRepository;

    @InjectMocks
    private PirataServiceImpl pirataService;

    private Pirata pirata;

    @BeforeEach
    void inicializar() {
        pirata = new Pirata();
    }

    @AfterEach
    void limpiar() {
        pirata = null;
    }

    // findAll---
    @Test
    void findAll_retornaListaConPiratas_siExistenPiratas() {
        when(pirataRepository.findAll()).thenReturn(List.of(pirata));

        List<Pirata> resultado = pirataService.findAll();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(pirataRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayPiratas() {
        when(pirataRepository.findAll()).thenReturn(List.of());

        List<Pirata> resultado = pirataService.findAll();

        assertTrue(resultado.isEmpty());
    }

    // findById---
    @Test
    void findById_retornaPirata_siIdExiste() {
        when(pirataRepository.findById(1L)).thenReturn(Optional.of(pirata));

        Optional<Pirata> resultado = pirataService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(pirataRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {
        when(pirataRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Pirata> resultado = pirataService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    // save---
    @Test
    void save_retornaPirataGuardado() {
        when(pirataRepository.save(pirata)).thenReturn(pirata);

        Pirata resultado = pirataService.save(pirata);

        assertNotNull(resultado);
        verify(pirataRepository).save(pirata);
    }

    // deleteById---
    @Test
    void deleteById_invocaElRepositorio() {
        pirataService.deleteById(1L);

        verify(pirataRepository).deleteById(1L);
    }

    // findByGeneracion---
    @ParameterizedTest
    @ValueSource(strings = { "Generacion Dorada", "Nueva Generacion", "Supernovas" })
    void findByGeneracion_retornaPiratas_paraDistintasGeneraciones(String generacion) {
        when(pirataRepository.findByGeneracion(generacion)).thenReturn(List.of(pirata));

        List<Pirata> resultado = pirataService.findByGeneracion(generacion);

        assertFalse(resultado.isEmpty());
        verify(pirataRepository).findByGeneracion(generacion);
    }

    @Test
    void findByGeneracion_retornaListaVacia_siNoHayPiratasEnEsaGeneracion() {
        when(pirataRepository.findByGeneracion("Generacion Inexistente")).thenReturn(List.of());

        List<Pirata> resultado = pirataService.findByGeneracion("Generacion Inexistente");

        assertTrue(resultado.isEmpty());
    }

    // findByTripulacion_Id---
    @Test
    void findByTripulacion_Id_retornaPiratasDeLaTripulacion() {
        when(pirataRepository.findByTripulacion_Id(1L)).thenReturn(List.of(pirata));

        List<Pirata> resultado = pirataService.findByTripulacion_Id(1L);

        assertEquals(1, resultado.size());
        verify(pirataRepository).findByTripulacion_Id(1L);
    }
}
