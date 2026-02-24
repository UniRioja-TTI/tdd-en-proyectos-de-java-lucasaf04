package com.tt1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    private ToDo todo;

    @BeforeEach
    void setUp() {
        todo = new ToDo();
    }

    @AfterEach
    void tearDown() {
        todo = null;
    }

    @Test
    void getNombre() {
        todo.setNombre("Estudiar");
        assertEquals("Estudiar", todo.getNombre(), "El nombre debería ser 'Estudiar'");
    }

    @Test
    void setNombre() {
        todo.setNombre("Comprar pan");
        assertEquals("Comprar pan", todo.getNombre(), "El nombre debería ser 'Comprar pan'");
    }

    @Test
    void getDescripcion() {
        todo.setDescripcion("Ir al supermercado a comprar comida.");
        assertEquals("Ir al supermercado a comprar comida.", todo.getDescripcion(), "La descripción debería ser la correcta");
    }

    @Test
    void setDescripcion() {
        todo.setDescripcion("Llamar al dentista para pedir cita.");
        assertEquals("Llamar al dentista para pedir cita.", todo.getDescripcion(), "La descripción debería ser correcta.");
    }

    @Test
    void getFechaLimite() {
        todo.setFechaLimite("2023-10-31");
        assertEquals("2023-10-31", todo.getFechaLimite(), "La fecha límite debería ser '2023-10-31'");
    }

    @Test
    void setFechaLimite() {
        todo.setFechaLimite("2023-11-30");
        assertEquals("2023-11-30", todo.getFechaLimite(), "La fecha límite debería ser '2023-11-30'");
    }

    @Test
    void isCompletado() {
        todo.setCompletado(true);
        assertTrue(todo.isCompletado(), "El estado completado debería ser verdadero");

        todo.setCompletado(false);
        assertFalse(todo.isCompletado(), "El estado completado debería ser falso");
    }

    @Test
    void setCompletado() {
        todo.setCompletado(true);
        assertTrue(todo.isCompletado(), "El estado completado debería ser verdadero");

        todo.setCompletado(false);
        assertFalse(todo.isCompletado(), "El estado completado debería ser falso");
    }
}