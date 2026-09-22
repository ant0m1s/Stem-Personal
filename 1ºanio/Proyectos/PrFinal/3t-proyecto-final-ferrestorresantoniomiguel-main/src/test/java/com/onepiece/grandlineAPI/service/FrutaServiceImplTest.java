package com.onepiece.grandlineAPI.service;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.repository.FrutaRepository;
import com.onepiece.grandlineAPI.repository.PersonajeRepository;
import com.onepiece.grandlineAPI.service.impl.FrutaServiceImpl;

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
class FrutaServiceImplTest {

    @Mock
    private FrutaRepository frutaRepository;

    @Mock
    private PersonajeRepository personajeRepository;

    @InjectMocks
    private FrutaServiceImpl frutaService;

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
    void findAll_retornaListaConFrutas_siExistenFrutas() {

        when(frutaRepository.findAll()).thenReturn(List.of(fruta));

        List<Fruta> resultado = frutaService.findAll();

        assertFalse(resultado.isEmpty());
        verify(frutaRepository).findAll();
    }

    @Test
    void findAll_retornaListaVacia_siNoHayFrutas() {

        when(frutaRepository.findAll()).thenReturn(List.of());

        List<Fruta> resultado = frutaService.findAll();

        assertTrue(resultado.isEmpty());
    }

    // findById ---
    @Test
    void findById_retornaFruta_siIdExiste() {

        when(frutaRepository.findById(1L)).thenReturn(Optional.of(fruta));

        Optional<Fruta> resultado = frutaService.findById(1L);

        assertTrue(resultado.isPresent());
        verify(frutaRepository).findById(1L);
    }

    @Test
    void findById_retornaVacio_siIdNoExiste() {

        when(frutaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Fruta> resultado = frutaService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    // save ---
    @Test
    void save_retornaFrutaGuardada_conNombreCorrecto() {

        when(frutaRepository.save(fruta)).thenReturn(fruta);

        Fruta resultado = frutaService.save(fruta);

        assertNotNull(resultado);
        assertEquals("Gomu Gomu no Mi", resultado.getNombre());
        verify(frutaRepository).save(fruta);
    }

    // deleteById ---
    @Test
    void deleteById_eliminaFruta_siNoTienePortadores() {

        fruta.setPersonajes(new ArrayList<>());
        when(frutaRepository.findById(1L)).thenReturn(Optional.of(fruta));

        frutaService.deleteById(1L);

        verify(frutaRepository).deleteById(1L);
    }

    @Test
    void deleteById_desvinculaPortadores_antesDeEliminar() {

        Personaje portador = mock(Personaje.class);
        List<Fruta> frutasDelPortador = new ArrayList<>();
        frutasDelPortador.add(fruta);
        when(portador.getFrutas()).thenReturn(frutasDelPortador);

        List<Personaje> portadores = new ArrayList<>();
        portadores.add(portador);
        fruta.setPersonajes(portadores);

        when(frutaRepository.findById(1L)).thenReturn(Optional.of(fruta));

        frutaService.deleteById(1L);

        verify(personajeRepository).save(portador);
        verify(frutaRepository).deleteById(1L);
    }

    @Test
    void deleteById_lanzaExcepcion_siFrutaNoExiste() {
        when(frutaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> frutaService.deleteById(99L));
    }

    // findByNombre ---
    @Test
    void findByNombre_retornaFruta_siNombreExiste() {

        when(frutaRepository.findByNombre("Gomu Gomu no Mi")).thenReturn(fruta);

        Fruta resultado = frutaService.findByNombre("Gomu Gomu no Mi");

        assertNotNull(resultado);
        assertEquals("Gomu Gomu no Mi", resultado.getNombre());
    }

    @Test
    void findByNombre_retornaNull_siNombreNoExiste() {
        when(frutaRepository.findByNombre("Fruta Inexistente")).thenReturn(null);

        Fruta resultado = frutaService.findByNombre("Fruta Inexistente");

        assertNull(resultado);
    }
}