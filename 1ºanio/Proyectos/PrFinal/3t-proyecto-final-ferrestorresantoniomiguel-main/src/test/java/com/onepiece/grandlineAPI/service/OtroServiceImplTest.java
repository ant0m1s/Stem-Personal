package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Otro;
import com.onepiece.grandlineAPI.repository.OtroRepository;
import com.onepiece.grandlineAPI.service.impl.OtroServiceImpl;

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
class OtroServiceImplTest {

    @Mock
    private OtroRepository otroRepository;

    @InjectMocks
    private OtroServiceImpl otroService;

    private Otro otro;

    @BeforeEach
    void inicializar() {
        otro = new Otro();
    }

    @AfterEach
    void limpiar() {
        otro = null;
    }

    @Test
    void findAll_retornaListaConPersonajes_siExisten() {

        when(otroRepository.findAll()).thenReturn(List.of(otro));

        List<Otro> resultado = otroService.findAll();

        assertFalse(resultado.isEmpty());
        verify(otroRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayPersonajes() {

        when(otroRepository.findAll()).thenReturn(List.of());

        List<Otro> resultado = otroService.findAll();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_retornaPersonaje_siIdExiste() {

        when(otroRepository.findById(1L)).thenReturn(Optional.of(otro));

        Optional<Otro> resultado = otroService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(otroRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {

        when(otroRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Otro> resultado = otroService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void save_retornaPersonajeGuardado() {

        when(otroRepository.save(otro)).thenReturn(otro);

        Otro resultado = otroService.save(otro);

        assertNotNull(resultado);
        verify(otroRepository).save(otro);
    }

    @Test
    void deleteById_invocaElRepositorio() {

        otroService.deleteById(1L);

        verify(otroRepository).deleteById(1L);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Cientifico", "Princesa", "Ejercito Revolucionario" })
    void findByLugar_retornaPersonajes_paraDistintosLugares(String lugar) {

        when(otroRepository.findByLugar(lugar)).thenReturn(List.of(otro));

        List<Otro> resultado = otroService.findByLugar(lugar);

        assertFalse(resultado.isEmpty());
        verify(otroRepository).findByLugar(lugar);
    }

    @Test
    void findByLugar_retornaListaVacia_siNoHayPersonajesConEseLugar() {

        when(otroRepository.findByLugar("Lugar Inexistente")).thenReturn(List.of());

        List<Otro> resultado = otroService.findByLugar("Lugar Inexistente");

        assertTrue(resultado.isEmpty());
    }
}