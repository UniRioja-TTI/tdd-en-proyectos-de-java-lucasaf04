package com.tt1.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Servicio {
    private final IRepositorio repositorio;
    private final IMailer mailer;

    public Servicio(IRepositorio repositorio, IMailer mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    public void crearTarea(String nombre, String fechaLimite) {
        ToDo nuevaTarea = new ToDo();
        nuevaTarea.setNombre(nombre);
        nuevaTarea.setFechaLimite(fechaLimite);
        nuevaTarea.setCompletado(false);

        repositorio.guardar(nuevaTarea);

        verificarTareasVencidas();
    }

    public void agregarEmail(String email) {
        repositorio.guardar(email);

        verificarTareasVencidas();
    }

    public void marcarCompletada(String nombre) {
        boolean tareaCompletada = repositorio.marcarCompletado(nombre);
        if (tareaCompletada) {
            System.out.println("La tarea '" + nombre + "' ha sido marcada como completada.");
        } else {
            System.out.println("No se encontró la tarea con el nombre '" + nombre + "'.");
        }

        verificarTareasVencidas();
    }

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
