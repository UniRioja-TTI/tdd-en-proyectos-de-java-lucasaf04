package com.tt1.test;

import java.util.List;
import java.util.Optional;

public interface IRepositorio {
    void guardar(ToDo tarea);
    List<ToDo> getTareas();
    Optional<ToDo> findPorNombre(String nombre);
    boolean marcarCompletado(String nombre);
    void guardar(String email);
    List<String> getEmails();
}
