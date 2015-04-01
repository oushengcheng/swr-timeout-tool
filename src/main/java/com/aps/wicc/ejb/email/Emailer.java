package com.aps.wicc.ejb.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {

	private static final String EMAIL_ADDRESS_SEPARATOR = ";";

    @Resource(lookup = "java:/mail/smtp-gmail")
    private Session session;

    @Asynchronous
    public void send(String to, String subject, String messageBody) {

        MimeMessage message = new MimeMessage(session);

        try {

            message.setRecipients(Message.RecipientType.TO, createAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setContent(messageBody, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException ex) {

            throw new EmailFailedException(ex);

        }
    }

    private InternetAddress[] createAddress(String to) throws AddressException {
    	List<InternetAddress> addresses = new ArrayList<>();
    	for (String address : to.split(EMAIL_ADDRESS_SEPARATOR)) {
    		addresses.add(new InternetAddress(address));
    	}
    	InternetAddress[] ia = new InternetAddress[addresses.size()];
    	return addresses.toArray(ia);
    }
}
