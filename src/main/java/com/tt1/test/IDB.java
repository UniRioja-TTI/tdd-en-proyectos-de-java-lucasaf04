package com.tt1.test;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones básicas de almacenamiento y gestión
 * de tareas ({@link ToDo}) y correos electrónicos asociados.
 * <p>
 * Permite crear, leer, actualizar y eliminar tareas, así como almacenar
 * y recuperar direcciones de correo electrónico.
 * </p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public interface IDB {

    /**
     * Crea una nueva tarea en la base de datos.
     * 
     * @param tarea la instancia de {@link ToDo} que se desea agregar.
     */
    void createTarea(ToDo tarea);

    /**
     * Obtiene la lista de todas las tareas almacenadas.
     * 
     * @return una {@link List} de {@link ToDo} que contiene todas las tareas.
     */
    List<ToDo> readTareas();

    /**
     * Busca una tarea por su nombre.
     * 
     * @param nombre el nombre de la tarea que se desea buscar.
     * @return un {@link Optional} que contiene la tarea si se encuentra, o vacío si no.
     */
    Optional<ToDo> readTareaPorNombre(String nombre);

    /**
     * Actualiza una tarea existente con nueva información.
     * 
     * @param nombre el nombre de la tarea que se desea actualizar.
     * @param nuevaTarea la nueva información de la tarea como {@link ToDo}.
     * @return {@code true} si la tarea fue encontrada y actualizada; {@code false} si no se encontró.
     */
    boolean updateTarea(String nombre, ToDo nuevaTarea);

    /**
     * Elimina una tarea por su nombre.
     * 
     * @param nombre el nombre de la tarea que se desea eliminar.
     * @return {@code true} si la tarea fue encontrada y eliminada; {@code false} si no se encontró.
     */
    boolean deleteTarea(String nombre);

    /**
     * Agrega una dirección de correo electrónico a la base de datos.
     * 
     * @param email la dirección de correo electrónico a agregar.
     */
    void addEmail(String email);

    /**
     * Obtiene la lista de correos electrónicos almacenados.
     * 
     * @return una {@link List} de cadenas con todas las direcciones de correo.
     */
    List<String> readEmails();
}
