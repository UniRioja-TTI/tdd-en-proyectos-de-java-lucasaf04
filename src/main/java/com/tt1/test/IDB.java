package com.tt1.test;

import java.util.List;
import java.util.Optional;

public interface IDB {
    void createTarea(ToDo tarea);
    List<ToDo> readTareas();
    Optional<ToDo> readTareaPorNombre(String nombre);
    boolean updateTarea(String nombre, ToDo nuevaTarea);
    boolean deleteTarea(String nombre);
    void addEmail(String email);
    List<String> readEmails();
}
