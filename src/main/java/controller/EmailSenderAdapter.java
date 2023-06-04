package controller;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderAdapter implements  EmailSender{

    private static final String FROM_EMAIL = "cristideac1120@gmail.com";
    private static final String PASSWORD = "rhncuvsrkjshirwe";

    @Override
    public void sendEmail(String function, String field, String newValue, String toAddress) throws IOException {
        final String toEmail = toAddress;

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
        props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); // SMTP Port

        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        };

        Session session = Session.getInstance(props, auth);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Update: Name, Username and Password Successfully Changed");
            message.setText("We wanted to inform you that we have successfully updated your name and password in our system. These changes have been implemented to ensure the security and accuracy of your account."
                    + "\nUpdated information \nName: " + function + "\nUsername: " + field + "\nPassword: " + newValue);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
