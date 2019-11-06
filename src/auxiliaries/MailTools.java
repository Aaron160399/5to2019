/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliaries;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Aaron
 */
public class MailTools {
    public Session logIn(final String mail, final String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.live.com");
//        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");
        //aaronlr160399@hotmail.com
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });
        return session;
    }
    
    public boolean send(Session session, String recipient, 
            String subject, String content) {
        try {
            BodyPart text = new MimeBodyPart();
            text.setContent(content, "text/html; charset=utf-8");
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(text);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SU CORREO"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(multiPart);

            Transport.send(message);
            System.out.println("Enviado con éxito a:" + recipient);
            return true;

        } catch (MessagingException e) {
            System.out.println("Error en el envío " + e);
            return false;
        }
    }
}
