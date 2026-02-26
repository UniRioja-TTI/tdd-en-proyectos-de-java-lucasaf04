package com.tt1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServicioTest {
    private Servicio servicio;
    private IRepositorio repositorioMock;
    private IMailer mailerMock;

    @BeforeEach
    void setUp() {
        repositorioMock = mock(IRepositorio.class);
        mailerMock = mock(IMailer.class);

        servicio = new Servicio(repositorioMock, mailerMock);
    }

    @AfterEach
    void tearDown() {
        repositorioMock = null;
        mailerMock = null;
        servicio = null;
    }

    @Test
    void crearTarea() {
        String nombre = "Estudiar Java";
        String fechaLimite = "25-10-2023";

        servicio.crearTarea(nombre, fechaLimite);

        verify(repositorioMock).guardar(any(ToDo.class));
    }

    @Test
    void agregarEmail() {
        String email = "test@example.com";

        servicio.agregarEmail(email);

        verify(repositorioMock).guardar(email);
    }

    @Test
    void marcarCompletada() {
        String nombre = "Estudiar Java";
        when(repositorioMock.marcarCompletado(nombre)).thenReturn(true);

        servicio.marcarCompletada(nombre);

        verify(repositorioMock).marcarCompletado(nombre);
    }

    @Test
    void tareasSinCompletar() {
        ToDo tarea = new ToDo();
        tarea.setNombre("Estudiar Java");
        tarea.setCompletado(false);
        ToDo tarea2 = new ToDo();
        tarea2.setNombre("Comprar Pan");
        tarea2.setCompletado(true);
        when(repositorioMock.getTareas()).thenReturn(List.of(tarea, tarea2));

        servicio.tareasSinCompletar();

        verify(repositorioMock, atLeastOnce()).getTareas();
    }
}
