package com.tt1.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase que proporciona servicios de gestión de tareas ({@link ToDo}) y
 * notificaciones por correo electrónico.
 * <p>
 * Permite crear tareas, agregar correos electrónicos de alerta, marcar
 * tareas como completadas, listar tareas pendientes y verificar tareas
 * vencidas enviando alertas a los correos registrados.
 * </p>
 * 
 * <p>Esta clase actúa como intermediaria entre la aplicación y un
 * {@link IRepositorio} para el almacenamiento, y un {@link IMailer}
 * para el envío de notificaciones.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public class Servicio {

    private final IRepositorio repositorio;
    private final IMailer mailer;

    /**
     * Constructor que inicializa el servicio con un repositorio y un sistema de correo.
     * 
     * @param repositorio implementación de {@link IRepositorio} usada para
     *                     almacenamiento de tareas y correos.
     * @param mailer      implementación de {@link IMailer} usada para enviar
     *                     alertas de tareas vencidas.
     */
    public Servicio(IRepositorio repositorio, IMailer mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    /**
     * Crea una nueva tarea con nombre y fecha límite, la guarda en el repositorio
     * y verifica si hay tareas vencidas para enviar alertas.
     * 
     * @param nombre      el nombre de la tarea a crear.
     * @param fechaLimite la fecha límite de la tarea en formato "dd-MM-yyyy".
     */
    public void crearTarea(String nombre, String fechaLimite) {
        ToDo nuevaTarea = new ToDo();
        nuevaTarea.setNombre(nombre);
        nuevaTarea.setFechaLimite(fechaLimite);
        nuevaTarea.setCompletado(false);

        repositorio.guardar(nuevaTarea);

        verificarTareasVencidas();
    }

    /**
     * Agrega una dirección de correo al repositorio para recibir alertas
     * de tareas vencidas y verifica tareas vencidas al registrarlo.
     * 
     * @param email la dirección de correo electrónico a agregar.
     */
    public void agregarEmail(String email) {
        repositorio.guardar(email);

        verificarTareasVencidas();
    }

    /**
     * Marca una tarea como completada según su nombre.
     * Muestra en consola el resultado y verifica tareas vencidas después.
     * 
     * @param nombre el nombre de la tarea a marcar como completada.
     */
    public void marcarCompletada(String nombre) {
        boolean tareaCompletada = repositorio.marcarCompletado(nombre);
        if (tareaCompletada) {
            System.out.println("La tarea '" + nombre + "' ha sido marcada como completada.");
        } else {
            System.out.println("No se encontró la tarea con el nombre '" + nombre + "'.");
        }

        verificarTareasVencidas();
    }

    /**
     * Muestra en consola todas las tareas que no han sido completadas.
     * Después verifica tareas vencidas y envía alertas a los correos registrados.
     */
    public void tareasSinCompletar() {
        List<ToDo> tareasPendientes = repositorio.getTareas();

        System.out.println("Tareas pendientes:");
        for (ToDo tarea : tareasPendientes) {
            if (!tarea.isCompletado()) {
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Fecha límite: " + tarea.getFechaLimite());
            }
        }

        verificarTareasVencidas();
    }

    /**
     * Verifica todas las tareas almacenadas en el repositorio y envía alertas
     * por correo si alguna tarea pendiente ha vencido.
     * <p>
     * Convierte la fecha límite de la tarea usando el formato "dd-MM-yyyy".
     * Si la tarea ha vencido y no está completada, se envía un mensaje a
     * cada correo registrado en el repositorio.
     * </p>
     */
    private void verificarTareasVencidas() {
        List<ToDo> tareas = repositorio.getTareas();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

        for (ToDo tarea : tareas) {
            String fechaLimiteString = tarea.getFechaLimite();
            if (fechaLimiteString == null) {
                continue;
            }
            try {
                Date fechaLimite = formatoFecha.parse(fechaLimiteString);

                if (!tarea.isCompletado() && fechaLimite.before(fechaActual)) {
                    System.out.println("La tarea '" + tarea.getNombre() + "' ha vencido. Enviando alerta.");

                    List<String> emails = repositorio.getEmails();
                    for (String email : emails) {
                        mailer.enviar(email, "Alerta: la tarea '" + tarea.getNombre() + "' ha vencido.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al convertir la fecha de la tarea '" + tarea.getNombre() + "': " + e.getMessage());
            }
        }
    }
}
