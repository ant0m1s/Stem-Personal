package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Tripulacion;
import com.onepiece.grandlineAPI.repository.TripulacionRepository;
import com.onepiece.grandlineAPI.service.impl.TripulacionServiceImpl;

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
class TripulacionServiceImplTest {

    @Mock
    private TripulacionRepository tripulacionRepository;

    @InjectMocks
    private TripulacionServiceImpl tripulacionService;

    private Tripulacion tripulacion;

    @BeforeEach
    void inicializar() {
        tripulacion = new Tripulacion();
    }

    @AfterEach
    void limpiar() {
        tripulacion = null;
    }

    @Test
    void findAll_retornaListaConTripulaciones_siExisten() {

        when(tripulacionRepository.findAll()).thenReturn(List.of(tripulacion));

        List<Tripulacion> resultado = tripulacionService.findAll();

        assertFalse(resultado.isEmpty());
        verify(tripulacionRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayTripulaciones() {

        when(tripulacionRepository.findAll()).thenReturn(List.of());

        List<Tripulacion> resultado = tripulacionService.findAll();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_retornaTripulacion_siIdExiste() {

        when(tripulacionRepository.findById(1L)).thenReturn(Optional.of(tripulacion));

        Optional<Tripulacion> resultado = tripulacionService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(tripulacionRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {

        when(tripulacionRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Tripulacion> resultado = tripulacionService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void save_retornaTripulacionGuardada() {

        when(tripulacionRepository.save(tripulacion)).thenReturn(tripulacion);

        Tripulacion resultado = tripulacionService.save(tripulacion);

        assertNotNull(resultado);
        verify(tripulacionRepository).save(tripulacion);
    }

    @Test
    void deleteById_invocaElRepositorio() {

        tripulacionService.deleteById(1L);

        verify(tripulacionRepository).deleteById(1L);
    }

    @Test
    void findByNombre_retornaTripulacion_siNombreExiste() {

        when(tripulacionRepository.findByNombre("Sombrero de Paja")).thenReturn(tripulacion);

        Tripulacion resultado = tripulacionService.findByNombre("Sombrero de Paja");

        assertNotNull(resultado);
        verify(tripulacionRepository).findByNombre("Sombrero de Paja");
    }

    @Test
    void findByNombre_retornaNull_siNombreNoExiste() {

        when(tripulacionRepository.findByNombre("Tripulacion Inexistente")).thenReturn(null);

        Tripulacion resultado = tripulacionService.findByNombre("Tripulacion Inexistente");

        assertNull(resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Generacion Dorada", "Nueva Generacion" })
    void findByGeneracion_retornaTripulaciones_paraDistintasGeneraciones(String generacion) {
        when(tripulacionRepository.findByGeneracion(generacion)).thenReturn(List.of(tripulacion));

        List<Tripulacion> resultado = tripulacionService.findByGeneracion(generacion);

        assertFalse(resultado.isEmpty());
        verify(tripulacionRepository).findByGeneracion(generacion);
    }
}