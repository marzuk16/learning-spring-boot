package com.learning.Learningspringboot.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class MailSender {

    private static Properties properties;

    private static Session session;

    private static MimeMessage mimeMessage;

    public static void setText(String message) {
        try {
            mimeMessage.setText(message);
        } catch (MessagingException e) {
            System.out.println("Error in setText method: " + e.getMessage());
        }
    }

    public static void setAttachment(String message, String path) {
        try {
            // adding text with attachments to message
//            String path = ""; // attachments path
            MimeMultipart mimeMultipart = new MimeMultipart();

            MimeBodyPart textMime = new MimeBodyPart();
            textMime.setText(message);

            File file = new File(path);
            MimeBodyPart fileMime = new MimeBodyPart();
            fileMime.attachFile(file);

            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(fileMime);

            mimeMessage.setContent(mimeMultipart);
        } catch (Exception e) {
            System.out.println("Error in setAttachment method: " + e.getMessage());
        }
    }

    // set up config
    public static void send(String message, String subject, String[] to, String from, String password, String path) {

        // get the system properties
        properties = System.getProperties();
        System.out.println("Properties: " + properties);

        // setting important information to properties object

        // host set
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "365");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // step 1: to get the session object
        session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password); // returns session object when successfully logged in gmail
            }
        });
        session.setDebug(true); // to debug session


        // step 2: compose the message [text, attachments]
        mimeMessage = new MimeMessage(session);

        // configure sender
        try {
            mimeMessage.setFrom(from);

            // configure to addresses
            Address[] addresses = new Address[to.length];
            for (int i = 0; i < to.length; i++) addresses[i] = new InternetAddress(to[i]);
            mimeMessage.addRecipients(Message.RecipientType.TO, addresses);

            // adding subject to message
            mimeMessage.setSubject(subject);

            // adding text to message
            if (path.equals("")) setText(message);
            else setAttachment(message, path);

            // step 3: send the message using Transport class
            Transport.send(mimeMessage);
            System.out.println("Send message success.");

        } catch (MessagingException e) {
            System.out.println("Error from mimeMessage.setFrom(): " + e.getMessage());
        }
    }
}
