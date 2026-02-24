package com.tt1.test;

import java.util.List;
import java.util.Optional;

public class Repositorio implements IRepositorio {
    private final IDB db;

    public Repositorio(IDB db) {
        this.db = db;
    }

    @Override
    public List<ToDo> getTareas() {
        return db.readTareas();
    }

    @Override
    public Optional<ToDo> findPorNombre(String nombre) {
        return db.readTareaPorNombre(nombre);
    }

    @Override
    public boolean marcarCompletado(String nombre) {
        Optional<ToDo> tareaBD = findPorNombre(nombre);
        if (tareaBD.isEmpty()) {
            return false;
        }

        ToDo tarea = tareaBD.get();
        tarea.setCompletado(true);
        return true;
    }

    @Override
    public void guardar(ToDo tarea) {
        db.createTarea(tarea);
    }

    @Override
    public List<String> getEmails() {
        return db.readEmails();
    }

    @Override
    public void guardar(String email) {
        db.addEmail(email);
    }
}
