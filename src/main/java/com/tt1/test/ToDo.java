package com.tt1.test;

/**
 * Representa una tarea o ítem de una lista de tareas (To-Do).
 * <p>
 * Esta clase encapsula la información principal de una tarea:
 * su nombre, descripción, fecha límite y estado de completado.
 * Permite crear instancias de tareas y acceder o modificar sus atributos.
 * </p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private String fechaLimite;
    private boolean completado;

    /**
     * Constructor por defecto.
     * <p>
     * Inicializa una nueva instancia de {@code ToDo} sin valores asignados.
     * Los atributos pueden establecerse posteriormente mediante los métodos setters.
     * </p>
     */
    public ToDo() {
    }

    /**
     * Obtiene el nombre de la tarea.
     * 
     * @return el nombre de la tarea como una cadena.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la tarea.
     * 
     * @param nombre la cadena que representa el nombre de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la tarea.
     * 
     * @return la descripción de la tarea como una cadena.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la tarea.
     * 
     * @param descripcion la cadena que representa la descripción de la tarea.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha límite de la tarea.
     * 
     * @return la fecha límite de la tarea en formato de cadena (por ejemplo, "YYYY-MM-DD").
     */
    public String getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Establece la fecha límite de la tarea.
     * 
     * @param fechaLimite la fecha límite de la tarea como una cadena (por ejemplo, "YYYY-MM-DD").
     */
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * Indica si la tarea ha sido completada.
     * 
     * @return {@code true} si la tarea está completada; {@code false} en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Establece el estado de completado de la tarea.
     * 
     * @param completado {@code true} si la tarea ha sido completada; {@code false} si no.
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
