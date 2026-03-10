package com.tt1.test;

/**
 * Interfaz que define la funcionalidad de envío de correos electrónicos.
 * <p>
 * Permite enviar un mensaje a una dirección de correo específica y
 * retorna si el envío fue exitoso o no.
 * </p>
 * 
 * <p>Implementaciones concretas pueden enviar correos reales o simular
 * el envío para pruebas.</p>
 * 
 * @author lucasaf04
 * @version 1.0.0
 */
public interface IMailer {

    /**
     * Envía un mensaje a un correo electrónico especificado.
     * 
     * @param email la dirección de correo electrónico del destinatario.
     * @param mensaje el contenido del mensaje a enviar.
     * @return {@code true} si el mensaje se envió correctamente; {@code false} en caso contrario.
     */
    boolean enviar(String email, String mensaje);
}
