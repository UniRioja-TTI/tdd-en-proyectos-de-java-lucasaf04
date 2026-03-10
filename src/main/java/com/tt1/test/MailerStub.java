package com.tt1.test;

/**
 * Implementación de {@link IMailer} que simula el envío de correos electrónicos.
 * <p>
 * Esta clase imprime en consola el destinatario y el contenido del mensaje,
 * sin enviar realmente un correo electrónico. Es útil para pruebas y desarrollo
 * sin depender de un servidor de correo real.
 * </p>
 * 
 * <p>Siempre retorna {@code true} indicando que el envío fue exitoso en la simulación.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public class MailerStub implements IMailer {

    /**
     * Simula el envío de un mensaje a un correo electrónico.
     * <p>
     * Imprime en consola la dirección y el contenido del mensaje.
     * </p>
     * 
     * @param email la dirección de correo electrónico del destinatario.
     * @param mensaje el contenido del mensaje a enviar.
     * @return {@code true} siempre, ya que esta implementación es un stub.
     */
    @Override
    public boolean enviar(String email, String mensaje) {
        System.out.println("Email para: " + email);
        System.out.println("Contenido: " + mensaje);

        return true;
    }
}
