package com.aps.wicc.ejb.email;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {

    @Resource(lookup = "java:/mail/smtp-gmail")
    private Session session;

    @Asynchronous
    public void send(String to, String subject, String messageBody) {

        MimeMessage message = new MimeMessage(session);

        try {

            InternetAddress[] address = { new InternetAddress(to) };
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setContent(messageBody, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException ex) {

            throw new EmailFailedException(ex);

        }
    }
}
