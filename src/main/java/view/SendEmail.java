/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Quang Huy
 */
public class SendEmail extends Thread {

    public void send(String email) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                final String username = "laptopgroup3@gmail.com";
                final String password = "lveekscgavporrkq";

                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true"); //TLS

                Session session = Session.getInstance(prop,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("from@gmail.com"));
                    message.setRecipients(
                            Message.RecipientType.TO,
                            InternetAddress.parse(email)
                    );
                    message.setSubject("Welcome to Laptop Group 3 Store");
                    message.setText("Dear " + email + ", "
                            + "\nCongratulations on being recruited as an employee of Laptop Group 3 Store "
                            + "\nYour default password is: 123456 \nPlease change your password the first time you log in to the system. "
                            + "\nSincerely thank you."
                            + "\nWish you will have a lot of fun when working at our store in the near future.");

                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }

}
