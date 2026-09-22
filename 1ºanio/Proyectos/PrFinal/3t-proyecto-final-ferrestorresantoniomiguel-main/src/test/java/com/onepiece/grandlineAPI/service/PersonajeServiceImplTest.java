package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.repository.FrutaRepository;
import com.onepiece.grandlineAPI.repository.PersonajeRepository;
import com.onepiece.grandlineAPI.service.impl.PersonajeServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonajeServiceImplTest {

    @Mock
    private PersonajeRepository personajeRepository;

    @Mock
    private FrutaRepository frutaRepository;

    @InjectMocks
    private PersonajeServiceImpl personajeService;

    private Fruta fruta;

    @BeforeEach
    void inicializar() {
        fruta = new Fruta("Gomu Gomu no Mi", "Fruta del caucho", "Paramecia");
    }

    @AfterEach
    void limpiar() {
        fruta = null;
    }

    // findAll ---
    @Test
    void findAll_retornaListaConPersonajes_siExisten() {

        Personaje personaje = mock(Personaje.class);
        when(personajeRepository.findAll()).thenReturn(List.of(personaje));

        List<Personaje> resultado = personajeService.findAll();

        assertFalse(resultado.isEmpty());
        verify(personajeRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayPersonajes() {

        when(personajeRepository.findAll()).thenReturn(List.of());

        List<Personaje> resultado = personajeService.findAll();

        assertTrue(resultado.isEmpty());
    }

    // findById ---
    @Test
    void findById_retornaPersonaje_siIdExiste() {

        Personaje personaje = mock(Personaje.class);
        when(personajeRepository.findById(1L)).thenReturn(Optional.of(personaje));

        Optional<Personaje> resultado = personajeService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(personajeRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {

        when(personajeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Personaje> resultado = personajeService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    // deleteById ---
    @Test
    void deleteById_invocaElRepositorio() {

        personajeService.deleteById(1L);

        verify(personajeRepository).deleteById(1L);
    }

    // anadirFruta ---
    @Test
    void anadirFruta_asociaFrutaAlPersonaje_correctamente() {

        Personaje personaje = mock(Personaje.class);
        List<Fruta> frutas = new ArrayList<>();
        when(personaje.getFrutas()).thenReturn(frutas);
        when(personajeRepository.findById(1L)).thenReturn(Optional.of(personaje));
        when(frutaRepository.findById(1L)).thenReturn(Optional.of(fruta));
        when(personajeRepository.save(personaje)).thenReturn(personaje);

        Personaje resultado = personajeService.anadirFruta(1L, 1L);

        assertTrue(frutas.contains(fruta));
        assertNotNull(resultado);
        verify(personajeRepository).save(personaje);
    }

    @Test
    void anadirFruta_lanzaExcepcion_siPersonajeNoExiste() {

        when(personajeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personajeService.anadirFruta(99L, 1L));
    }

    @Test
    void anadirFruta_lanzaExcepcion_siFrutaNoExiste() {

        Personaje personaje = mock(Personaje.class);
        when(personajeRepository.findById(1L)).thenReturn(Optional.of(personaje));
        when(frutaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personajeService.anadirFruta(1L, 99L));
    }

    // borrarFruta ---
    @Test
    void borrarFruta_desasociaFrutaDelPersonaje_correctamente() {

        Personaje personaje = mock(Personaje.class);
        List<Fruta> frutas = new ArrayList<>();
        frutas.add(fruta);
        when(personaje.getFrutas()).thenReturn(frutas);
        when(personajeRepository.findById(1L)).thenReturn(Optional.of(personaje));
        when(frutaRepository.findById(1L)).thenReturn(Optional.of(fruta));
        when(personajeRepository.save(personaje)).thenReturn(personaje);

        personajeService.borrarFruta(1L, 1L);

        assertFalse(frutas.contains(fruta));
        verify(personajeRepository).save(personaje);
    }

    // getFrutasByPersonajeId ---
    @Test
    void getFrutasByPersonajeId_retornaFrutas_delPersonaje() {

        Personaje personaje = mock(Personaje.class);
        when(personaje.getFrutas()).thenReturn(List.of(fruta));
        when(personajeRepository.findById(1L)).thenReturn(Optional.of(personaje));

        List<Fruta> resultado = personajeService.getFrutasByPersonajeId(1L);

        assertEquals(1, resultado.size());
        assertEquals("Gomu Gomu no Mi", resultado.get(0).getNombre());
    }

    @Test
    void getFrutasByPersonajeId_lanzaExcepcion_siPersonajeNoExiste() {

        when(personajeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personajeService.getFrutasByPersonajeId(99L));
    }

    // findByNombreCompleto ---
    @Test
    void findByNombreCompleto_retornaPersonaje_siNombreExiste() {

        Personaje personaje = mock(Personaje.class);
        when(personajeRepository.findByNombreCompleto("Monkey D. Luffy")).thenReturn(personaje);

        Personaje resultado = personajeService.findByNombreCompleto("Monkey D. Luffy");

        assertNotNull(resultado);
        verify(personajeRepository).findByNombreCompleto("Monkey D. Luffy");
    }

    @Test
    void findByNombreCompleto_retornaNull_siNombreNoExiste() {

        when(personajeRepository.findByNombreCompleto("Personaje Inexistente")).thenReturn(null);

        Personaje resultado = personajeService.findByNombreCompleto("Personaje Inexistente");

        assertNull(resultado);
    }
}