package self.learning.learningspringboot.utils;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Component
public class MailSenderImpl implements MailSender{
    private MimeMessage setConfig(Properties properties, String from, String password, String[] to, String subject){

        // variable for gmail
        String host = "smtp.gmail.com";
        String port = "465"; // gmail port

        // setting important information to properties object

        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // step 1: to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password); // returns session object when successfully logged in gmail
            }
        });
        session.setDebug(true); // to debug session

        // step 2: compose the message [text, attachments]
        MimeMessage mimeMessage = new MimeMessage(session);

        // configure sender
        try {
            mimeMessage.setFrom(from);

            // configure to addresses
            Address[] addresses = new Address[to.length];
            for (int i = 0; i < to.length; i++) addresses[i] = new InternetAddress(to[i]);
            mimeMessage.addRecipients(Message.RecipientType.TO, addresses);

            // adding subject to message
            mimeMessage.setSubject(subject);

        } catch (MessagingException e) {
            System.out.println("Error from mimeMessage.setFrom(): " + e.getMessage());
        }

        return mimeMessage;
    }

    // set up config
    @Override
    public void send(String message, String subject, String[] to, String from, String password) {

        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        MimeMessage mimeMessage = setConfig(properties, from, password, to, subject);

        try {
            // adding text to message
            mimeMessage.setText(message);

            // step 3: send the message using Transport class
            Transport.send(mimeMessage);
            System.out.println("Send message success.");
        }catch (MessagingException e){
            System.out.println("Error from mimeMessage.setFrom(): " + e.getMessage());
        }
    }

    // set up config
    @Override
    public void sendWithAttachments(String message, String subject, String[] to, String from, String password, String path) {

        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        MimeMessage mimeMessage = setConfig(properties, from, password, to, subject);

        try {
            // adding content to message

            MimeMultipart mimeMultipart = new MimeMultipart();

            MimeBodyPart textMime = new MimeBodyPart();
            textMime.setText(message);

            File file = new File(path);
            MimeBodyPart fileMime = new MimeBodyPart();
            fileMime.attachFile(file);

            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(fileMime);

            mimeMessage.setContent(mimeMultipart);

            // step 3: send the message using Transport class
            Transport.send(mimeMessage);
            System.out.println("Send message success.");

        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
