package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public static String senderEmailID = "daovanphi25051994@gmail.com";
    public static String senderPassword = "nguoidienyeu";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";

    public EmailService() {
    }

    public EmailService(String from, String password) {
        this.senderEmailID = from;
        this.senderPassword = password;
    }

    public boolean send(String to, String subject, String content, String from) {
        Properties props = new Properties();
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(content);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return false;
    }

    public class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }
}
