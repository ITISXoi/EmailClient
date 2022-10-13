package sendMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MessageSend {
    public Session createSession() {
        final String username = "cuongcoca2001@gmail.com";
        final String password = "zskxrnwmvbqqvobn";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }
    public Message createMessage(Session sess) throws MessagingException {
        Message mess = new MimeMessage(sess);
        mess.setFrom(new InternetAddress("cuongcoca01@gmail.com"));
        mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cuongcoca01@gmail.com ", false));
        mess.setSubject("Test");
        mess.setText("This is a test of JavaMail's functionality.");
        mess.setSentDate(new Date());
        return mess;
    }
}
