package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CrashReportSender {
    // Recipient
    public static String to = "vadym.personal@gmail.com";
    // Sender
    public static String from = "karpenkobrown@gmail.com";

    public static void main(String[] args) {

        Session session = getSession();
        // If we need debug
        session.setDebug(false);

        try {
            // Create default Mime msg obj
            MimeMessage msg = new MimeMessage(session);

            // Set From
            msg.setFrom(new InternetAddress(from));

            // Set To:
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject
            msg.setSubject("This is CRASH Report");

            // set Actual text
            msg.setText("Test, Hellow wolrd!\nI'm Vadym. Nice to meet u.");

            System.out.println("Sending...");

            // Send msg
            Transport.send(msg);
            System.out.println("Message sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {

        // sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "************");
            }
        });
        return session;
    }

    public static void sendReport(String report) {
        Session session = getSession();
        try {
            // Create default Mime msg obj
            MimeMessage msg = new MimeMessage(session);

            // Set From
            msg.setFrom(new InternetAddress(from));

            // Set To:
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime now = LocalDateTime.now();
            msg.setSubject("Crash report - " + dtf.format(now));

            // set Actual text
            msg.setText(report);

            System.out.println("Sending...");

            // Send msg
            Transport.send(msg);
            System.out.println("Message sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
