package com.tt1.test;

import java.util.Scanner;

/**
 * Clase principal de la aplicación de gestión de tareas.
 * <p>
 * Proporciona un menú interactivo en consola que permite:
 * </p>
 * <ul>
 *   <li>Crear nuevas tareas con nombre y fecha límite.</li>
 *   <li>Agregar direcciones de correo a la agenda para recibir alertas.</li>
 *   <li>Marcar tareas como completadas.</li>
 *   <li>Consultar tareas pendientes.</li>
 *   <li>Salir de la aplicación.</li>
 * </ul>
 * <p>
 * Esta clase utiliza {@link DBStub} como almacenamiento simulado,
 * {@link Repositorio} como intermediario de persistencia,
 * {@link MailerStub} como simulador de envío de correos, y
 * {@link Servicio} para la lógica de negocio de tareas y notificaciones.
 * </p>
 * 
 * <p>Nota: se trata de una aplicación de consola que interactúa directamente
 * con el usuario mediante {@link Scanner} y muestra información por consola.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
class App {

    /**
     * Método principal que ejecuta la aplicación.
     * <p>
     * Inicializa los componentes necesarios (base de datos simulada, repositorio,
     * servicio y sistema de correo) y muestra un menú en bucle para que el usuario
     * interactúe con las tareas y correos.
     * </p>
     * 
     * <p>Opciones del menú:</p>
     * <ol>
     *   <li>Crear tarea: solicita nombre y fecha límite, y crea una nueva tarea.</li>
     *   <li>Agregar email a la agenda: solicita un correo electrónico y lo agrega al repositorio.</li>
     *   <li>Marcar tarea como completada: solicita el nombre de la tarea y la marca como completada.</li>
     *   <li>Consultar tareas sin completar: muestra todas las tareas pendientes.</li>
     *   <li>Salir: termina la ejecución de la aplicación.</li>
     * </ol>
     * 
     * @param args argumentos de línea de comandos (no se usan en esta aplicación).
     */
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
                case 1:
                    System.out.print("Introduce el nombre de la tarea: ");
                    String nombre1 = scanner.nextLine();

                    System.out.print("Introduce la fecha límite (en formato YYYY-MM-DD): ");
                    String fechaLimite = scanner.nextLine();

                    servicio.crearTarea(nombre1, fechaLimite);
                    break;
                case 2:
                    System.out.print("Introduce la dirección de correo electrónico: ");
                    String email = scanner.nextLine();

                    servicio.agregarEmail(email);
                    break;
                case 3:
                    System.out.print("Introduce el nombre de la tarea a completar: ");
                    String nombre2 = scanner.nextLine();

                    servicio.marcarCompletada(nombre2);
                    break;
                case 4:
                    servicio.tareasSinCompletar();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
