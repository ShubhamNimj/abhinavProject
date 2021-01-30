package org.abhinav.controller.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailServices {

    private static String fromEmail;

    public void sendMail(String receiver, String text, String subject) {
        String to = receiver;//change accordingly

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return getPasswordAuthonticate();
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gnimje111@gmail.com"));//change accordingly  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email Sent Successfully");
        } catch (MessagingException e) {
        	System.out.println("Can not send backup file on email");
            throw new RuntimeException(e);
        }
    }

    private PasswordAuthentication getPasswordAuthonticate() {
        return new PasswordAuthentication("gnimje111@gmail.com","Ganesh@0664");//change accordingly  
    }
}
