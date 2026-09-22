package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Barco;
import com.onepiece.grandlineAPI.repository.BarcoRepository;
import com.onepiece.grandlineAPI.service.impl.BarcoServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarcoServiceImplTest {

    @Mock
    private BarcoRepository barcoRepository;

    @InjectMocks
    private BarcoServiceImpl barcoService;

    private Barco barco;

    @BeforeEach
    void inicializar() {
        barco = new Barco();
    }

    @AfterEach
    void limpiar() {
        barco = null;
    }

    @Test
    void findAll_retornaListaConBarcos_siExisten() {

        when(barcoRepository.findAll()).thenReturn(List.of(barco));

        List<Barco> resultado = barcoService.findAll();

        assertFalse(resultado.isEmpty());
        verify(barcoRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayBarcos() {

        when(barcoRepository.findAll()).thenReturn(List.of());

        List<Barco> resultado = barcoService.findAll();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_retornaBarco_siIdExiste() {

        when(barcoRepository.findById(1L)).thenReturn(Optional.of(barco));

        Optional<Barco> resultado = barcoService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(barcoRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {

        when(barcoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Barco> resultado = barcoService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void save_retornaBarcoGuardado() {

        when(barcoRepository.save(barco)).thenReturn(barco);

        Barco resultado = barcoService.save(barco);

        assertNotNull(resultado);
        verify(barcoRepository).save(barco);
    }

    @Test
    void deleteById_invocaElRepositorio() {

        barcoService.deleteById(1L);

        verify(barcoRepository).deleteById(1L);
    }

    @Test
    void findByTripulacionId_retornaBarcos_deLaTripulacion() {

        when(barcoRepository.findByTripulacion_Id(1L)).thenReturn(List.of(barco));

        List<Barco> resultado = barcoService.findByTripulacionId(1L);

        assertEquals(1, resultado.size());
        verify(barcoRepository).findByTripulacion_Id(1L);
    }

    @Test
    void findByTripulacionId_retornaListaVacia_siTripulacionNoTieneBarcos() {

        when(barcoRepository.findByTripulacion_Id(99L)).thenReturn(List.of());

        List<Barco> resultado = barcoService.findByTripulacionId(99L);

        assertTrue(resultado.isEmpty());
    }
}