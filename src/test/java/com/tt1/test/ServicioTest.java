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
        // Creamos mocks de IRepositorio e IMailer
        repositorioMock = mock(IRepositorio.class);
        mailerMock = mock(IMailer.class);

        // Creamos el servicio pasando los mocks
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
        // Arrange
        String nombre = "Estudiar Java";
        String fechaLimite = "25-10-2023";

        // Act
        servicio.crearTarea(nombre, fechaLimite);

        // Assert: Verificamos que se llamó al método guardar del repositorio
        verify(repositorioMock).guardar(any(ToDo.class));
    }

    @Test
    void agregarEmail() {
        // Arrange
        String email = "test@example.com";

        // Act
        servicio.agregarEmail(email);

        // Assert: Verificamos que el email fue guardado
        verify(repositorioMock).guardar(email);
    }

    @Test
    void marcarCompletada() {
        // Arrange
        String nombre = "Estudiar Java";
        when(repositorioMock.marcarCompletado(nombre)).thenReturn(true);

        // Act
        servicio.marcarCompletada(nombre);

        // Assert: Verificamos que se llamó al método marcarCompletado
        verify(repositorioMock).marcarCompletado(nombre);
    }

    @Test
    void tareasSinCompletar() {
        // Arrange
        ToDo tarea = new ToDo();
        tarea.setNombre("Estudiar Java");
        tarea.setCompletado(false);
        when(repositorioMock.getTareas()).thenReturn(List.of(tarea));

        // Act
        servicio.tareasSinCompletar();

        // Assert: Verificamos que se imprimieron las tareas pendientes
        verify(repositorioMock).getTareas();
    }
}
