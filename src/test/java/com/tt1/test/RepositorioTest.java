package com.tt1.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class RepositorioTest {
    private Repositorio repositorio;
    private IDB dbMock;

    @BeforeEach
    void setUp() {
        dbMock = mock(IDB.class);
        repositorio = new Repositorio(dbMock);
    }

    @AfterEach
    void tearDown() {
        dbMock = null;
        repositorio = null;
    }

    @Test
    void getTareas() {
        ToDo tarea = new ToDo();
        tarea.setNombre("Estudiar Java");
        when(dbMock.readTareas()).thenReturn(List.of(tarea));

        var tareas = repositorio.getTareas();

        assertEquals(1, tareas.size(), "Debería haber una tarea.");
        assertEquals("Estudiar Java", tareas.getFirst().getNombre(), "El nombre de la tarea debería ser 'Estudiar Java'");
    }

    @Test
    void findPorNombre() {
        ToDo tarea = new ToDo();
        tarea.setNombre("Estudiar Java");
        when(dbMock.readTareaPorNombre("Estudiar Java")).thenReturn(Optional.of(tarea));

        var tareaRecuperada = repositorio.findPorNombre("Estudiar Java");

        assertTrue(tareaRecuperada.isPresent(), "La tarea debería estar presente.");
        assertEquals("Estudiar Java", tareaRecuperada.get().getNombre(), "El nombre de la tarea debería ser 'Estudiar Java'");
    }

    @Test
    void marcarCompletado() {
        ToDo tarea = new ToDo();
        tarea.setNombre("Estudiar Java");
        tarea.setCompletado(false);
        when(dbMock.readTareaPorNombre("Estudiar Java")).thenReturn(Optional.of(tarea));

        boolean resultado = repositorio.marcarCompletado("Estudiar Java");

        assertTrue(resultado, "La tarea debería ser marcada como completada.");
        assertTrue(tarea.isCompletado(), "La tarea debería estar marcada como completada.");
    }
}
