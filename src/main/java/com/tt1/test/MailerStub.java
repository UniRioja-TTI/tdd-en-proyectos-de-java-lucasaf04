package com.tt1.test;

public class MailerStub implements IMailer {
    @Override
    public boolean enviar(String email, String mensaje) {
        System.out.println("Email para: " + email);
        System.out.println("Contenido: " + mensaje);

        return true;
    }
}
