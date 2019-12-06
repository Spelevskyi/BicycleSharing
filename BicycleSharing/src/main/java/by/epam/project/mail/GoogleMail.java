package by.epam.project.mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public enum GoogleMail{
    INSTANCE;
    
    public void sendMessage(String mailContext, String mailTarget) {
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth", "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS Port
        props.put("mail.smtp.port", "587");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("ilyabelevich@gmail.com", "8385641w");
            }
        });

        try {

            // compose the message
            // javax.mail.internet.MimeMessage class is
            // mostly used for abstraction.
            Message message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress("ilyabelevich@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTarget));
            message.setSubject("hello");
            message.setText(mailContext);

            Transport.send(message); // send Message

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

    
    
    
