package com.tt1.test;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define operaciones para gestionar tareas ({@link ToDo})
 * y correos electrónicos en un repositorio.
 * <p>
 * Permite guardar y recuperar tareas, buscar por nombre, marcar como completadas,
 * así como almacenar y obtener direcciones de correo.
 * </p>
 * 
 * <p>Esta interfaz abstrae la persistencia de datos y puede ser implementada
 * usando bases de datos reales, stubs en memoria u otros mecanismos.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public interface IRepositorio {

    /**
     * Guarda una tarea en el repositorio.
     * 
     * @param tarea la instancia de {@link ToDo} que se desea guardar.
     */
    void guardar(ToDo tarea);

    /**
     * Obtiene todas las tareas almacenadas en el repositorio.
     * 
     * @return una {@link List} de {@link ToDo} con todas las tareas.
     */
    List<ToDo> getTareas();

    /**
     * Busca una tarea por su nombre.
     * 
     * @param nombre el nombre de la tarea a buscar.
     * @return un {@link Optional} que contiene la tarea si se encuentra, o vacío si no.
     */
    Optional<ToDo> findPorNombre(String nombre);

    /**
     * Marca una tarea como completada según su nombre.
     * 
     * @param nombre el nombre de la tarea a marcar como completada.
     * @return {@code true} si la tarea fue encontrada y marcada; {@code false} si no se encontró.
     */
    boolean marcarCompletado(String nombre);

    /**
     * Guarda una dirección de correo en el repositorio.
     * 
     * @param email la dirección de correo electrónico a guardar.
     */
    void guardar(String email);

    /**
     * Obtiene todas las direcciones de correo almacenadas en el repositorio.
     * 
     * @return una {@link List} de cadenas con todas las direcciones de correo.
     */
    List<String> getEmails();
}
