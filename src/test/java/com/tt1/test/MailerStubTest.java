package com.tt1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MailerStubTest {
    private MailerStub mailer;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        mailer = new MailerStub();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        mailer = null;
        System.setOut(System.out);
    }

    @Test
    void enviar() {
        String email = "test@example.com";
        String mensaje = "Hola, este es un mensaje de prueba.";

        boolean resultado = mailer.enviar(email, mensaje);

        assertTrue(resultado, "El método enviar debe retornar true.");

        String lineSeparator = System.lineSeparator();

        String salidaEsperada = "Email para: " + email + lineSeparator + "Contenido: " + mensaje + lineSeparator;
        assertEquals(salidaEsperada, outputStreamCaptor.toString(), "La salida estándar no es la esperada.");
    }
}