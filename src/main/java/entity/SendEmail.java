/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import domainmodel.CPU;
import domainmodel.CardMH;
import domainmodel.ChiTietSP;
import domainmodel.Hang;
import domainmodel.HoaDon;
import domainmodel.HoaDonChiTiet;
import domainmodel.KhachHang;
import domainmodel.KhuyenMai;
import domainmodel.NhanVien;
import domainmodel.OCung;
import domainmodel.Ram;
import domainmodel.SanPham;
import domainmodel.SanPhamKM;
import domainmodel.SerialDaBan;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Quang Huy
 */
public class SendEmail extends Thread {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DuAn1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(SerialDaBan.class);
        conf.addAnnotatedClass(SanPhamKM.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(CPU.class);
        conf.addAnnotatedClass(Ram.class);
        conf.addAnnotatedClass(OCung.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(CardMH.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void send(String email) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                final String username = "laptopgroup3@gmail.com";
                final String password = "qxkktgzvbqffvbfb";

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
//                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }

}
