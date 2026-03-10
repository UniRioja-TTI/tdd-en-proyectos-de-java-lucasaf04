package com.tt1.test;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de {@link IRepositorio} que utiliza un {@link IDB} como
 * almacenamiento subyacente.
 * <p>
 * Esta clase actúa como un intermediario entre la aplicación y la base de datos,
 * delegando operaciones de persistencia y recuperación de tareas y correos
 * al {@link IDB} proporcionado.
 * </p>
 * 
 * <p>Permite operaciones CRUD básicas sobre tareas y manejo de correos electrónicos.</p>
 * 
 * Ejemplo de uso:
 * <pre>{@code
 * IDB db = new DBStub();
 * IRepositorio repo = new Repositorio(db);
 * repo.guardar(new ToDo());
 * List<ToDo> tareas = repo.getTareas();
 * }</pre>
 * 
 * <p>Permite marcar tareas como completadas y buscar por nombre.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public class Repositorio implements IRepositorio {

    private final IDB db;

    /**
     * Constructor que inicializa el repositorio con un almacenamiento {@link IDB}.
     * 
     * @param db la implementación de {@link IDB} que se usará para persistencia.
     */
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
