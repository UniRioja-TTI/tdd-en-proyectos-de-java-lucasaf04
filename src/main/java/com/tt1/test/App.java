package com.tt1.test;

import java.util.Scanner;

class App {
    public static void main(String[] args) {
        DBStub dbStub = new DBStub();
        Repositorio repositorio = new Repositorio(dbStub);
        MailerStub mailerStub = new MailerStub();
        Servicio servicio = new Servicio(repositorio, mailerStub);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear tarea");
            System.out.println("2. Agregar email a la agenda");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Consultar tareas sin completar");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introduce el nombre de la tarea: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Introduce la fecha límite (en formato YYYY-MM-DD): ");
                    String fechaLimite = scanner.nextLine();

                    servicio.crearTarea(nombre, fechaLimite);
                }
                case 2 -> {
                    System.out.print("Introduce la dirección de correo electrónico: ");
                    String email = scanner.nextLine();

                    servicio.agregarEmail(email);
                }
                case 3 -> {
                    System.out.print("Introduce el nombre de la tarea a completar: ");
                    String nombre = scanner.nextLine();

                    servicio.marcarCompletada(nombre);
                }
                case 4 -> {
                    servicio.tareasSinCompletar();
                }
                case 5 -> {
                    return;
                }
                default -> {
                    System.out.println("Opción inválida.");
                }
            }
        }
    }
}
