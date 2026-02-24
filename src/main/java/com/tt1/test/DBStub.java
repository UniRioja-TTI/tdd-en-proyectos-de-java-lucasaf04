package com.tt1.test;

import java.util.*;

public class DBStub implements IDB {
    private final List<ToDo> tareas;
    private final List<String> emails;

    public DBStub() {
        this.tareas = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    @Override
    public void createTarea(ToDo tarea) {
        tareas.add(tarea);
    }

    @Override
    public List<ToDo> readTareas() {
        return tareas;
    }

    @Override
    public Optional<ToDo> readTareaPorNombre(String nombre) {
        return tareas.stream().filter(t -> t.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    @Override
    public boolean updateTarea(String nombre, ToDo nuevaTarea) {
        Optional<ToDo> tareaBD = readTareaPorNombre(nombre);
        if (tareaBD.isEmpty()) {
            return false;
        }

        ToDo tarea = tareaBD.get();
        tarea.setNombre(nuevaTarea.getNombre());
        tarea.setDescripcion(nuevaTarea.getDescripcion());
        tarea.setFechaLimite(nuevaTarea.getFechaLimite());
        tarea.setCompletado(nuevaTarea.isCompletado());
        return true;
    }

    @Override
    public boolean deleteTarea(String nombre) {
        Optional<ToDo> tareaBD = readTareaPorNombre(nombre);
        if (tareaBD.isEmpty()) {
            return false;
        }

        tareas.remove(tareaBD.get());
        return true;
    }

    @Override
    public void addEmail(String email) {
        emails.add(email);
    }

    @Override
    public List<String> readEmails() {
        return new ArrayList<>(emails);
    }
}
