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
    
    /**
     * Method for sending message to Gmail account
     * 
     * @param mailContext - message for forwarding
     * @param mailTarget  - message reciever
     */
    public void sendMessage(String mailContext, String mailTarget) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ilyabelevich@gmail.com", "8385641w");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ilyabelevich@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTarget));
            message.setSubject("Hello");
            message.setText(mailContext);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

    
    
    
