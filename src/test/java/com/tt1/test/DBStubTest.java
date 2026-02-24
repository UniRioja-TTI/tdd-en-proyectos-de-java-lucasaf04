package com.tt1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

class DBStubTest {
    private DBStub db;
    private ToDo tarea1;
    private ToDo tarea2;

    @BeforeEach
    void setUp() {
        db = new DBStub();

        tarea1 = new ToDo();
        tarea1.setNombre("Estudiar Java");
        tarea1.setDescripcion("Repasar conceptos básicos");
        tarea1.setFechaLimite("2023-10-30");
        tarea1.setCompletado(false);

        tarea2 = new ToDo();
        tarea2.setNombre("Comprar pan");
        tarea2.setDescripcion("Ir a la panadería");
        tarea2.setFechaLimite("2023-10-25");
        tarea2.setCompletado(false);
    }

    @AfterEach
    void tearDown() {
        db = null;
        tarea1 = null;
        tarea2 = null;
    }

    @Test
    void createTarea() {
        db.createTarea(tarea1);

        List<ToDo> tareas = db.readTareas();
        assertEquals(1, tareas.size(), "Debe haber una tarea en la base de datos.");
        assertEquals("Estudiar Java", tareas.getFirst().getNombre(), "El nombre de la tarea debería ser 'Estudiar Java'");
    }

    @Test
    void readTareas() {
        db.createTarea(tarea1);
        db.createTarea(tarea2);

        List<ToDo> tareas = db.readTareas();
        assertEquals(2, tareas.size(), "Debe haber dos tareas en la base de datos.");
        assertEquals("Estudiar Java", tareas.getFirst().getNombre(), "El nombre de la tarea debería ser 'Estudiar Java'");
        assertEquals("Comprar pan", tareas.get(1).getNombre(), "El nombre de la tarea debería ser 'Comprar pan'");
    }

    @Test
    void readTareaPorNombre() {
        db.createTarea(tarea1);
        db.createTarea(tarea2);

        Optional<ToDo> tareaRecuperada = db.readTareaPorNombre("Estudiar Java");
        assertTrue(tareaRecuperada.isPresent(), "La tarea 'Estudiar Java' debería ser recuperada.");
        assertEquals("Estudiar Java", tareaRecuperada.get().getNombre(), "El nombre de la tarea debería ser 'Estudiar Java'");

        Optional<ToDo> tareaNoExistente = db.readTareaPorNombre("No Existe");
        assertFalse(tareaNoExistente.isPresent(), "La tarea no existente no debería ser recuperada.");
    }

    @Test
    void updateTarea() {
        db.createTarea(tarea1);
        ToDo nuevaTarea = new ToDo();
        nuevaTarea.setNombre("Estudiar Python");
        nuevaTarea.setDescripcion("Repasar conceptos de Python");
        nuevaTarea.setFechaLimite("2023-11-01");
        nuevaTarea.setCompletado(true);

        boolean actualizado = db.updateTarea("Estudiar Java", nuevaTarea);

        assertTrue(actualizado, "La tarea debería haberse actualizado.");
        Optional<ToDo> tareaActualizada = db.readTareaPorNombre("Estudiar Python");
        assertTrue(tareaActualizada.isPresent(), "La tarea 'Estudiar Python' debería existir.");
        assertEquals("Estudiar Python", tareaActualizada.get().getNombre(), "El nombre debería ser 'Estudiar Python'");
    }

    @Test
    void deleteTarea() {
        db.createTarea(tarea1);

        boolean eliminada = db.deleteTarea("Estudiar Java");

        assertTrue(eliminada, "La tarea debería haberse eliminado.");
        Optional<ToDo> tareaEliminada = db.readTareaPorNombre("Estudiar Java");
        assertFalse(tareaEliminada.isPresent(), "La tarea 'Estudiar Java' no debería existir.");
    }

    @Test
    void addEmail() {
        db.addEmail("test@example.com");

        List<String> emails = db.readEmails();
        assertEquals(1, emails.size(), "Debe haber un email en la base de datos.");
        assertEquals("test@example.com", emails.getFirst(), "El email añadido debería ser 'test@example.com'");
    }

    @Test
    void readEmails() {
        db.addEmail("test1@example.com");
        db.addEmail("test2@example.com");

        List<String> emails = db.readEmails();
        assertEquals(2, emails.size(), "Debe haber dos emails en la base de datos.");
        assertTrue(emails.contains("test1@example.com"), "La lista debería contener el email 'test1@example.com'");
        assertTrue(emails.contains("test2@example.com"), "La lista debería contener el email 'test2@example.com'");
    }
}