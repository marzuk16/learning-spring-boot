package self.learning.learningspringboot.utils;

public interface MailSender {
    void send(String message, String subject, String[] to, String from, String password);

    void sendWithAttachments(String message, String subject, String[] to, String from, String password, String path);
}
