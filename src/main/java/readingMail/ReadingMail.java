package readingMail;

import javax.mail.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadingMail {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.store.protocol", "pop3s");
        props.put("mail.pop3s.host", "imap.gmail.com");
        props.put("mail.pop3s.port", "995");
        props.put("mail.pop3s.starttls.enable", "true");
        props.put("mail.pop3s.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3s.ssl.trust", "*");
        props.put("mail.pop3s.ssl.enable","true");
        try {
            Session session = Session.getInstance(props);
            session.setDebug(true);
            Store store = session.getStore("pop3s");
            store.connect("imap.gmail.com", "cuongcoca2001@gmail.com", "zskxrnwmvbqqvobn");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            System.out.println("Total Message" + messages.length);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                Address[] toAddress =
                        message.getRecipients(Message.RecipientType.TO);
                System.out.println("---------------------------------");
                System.out.println("Details of Email Message "
                        + (i + 1) + " :");
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                //Iterate recipients
                System.out.println("To: ");
                for(int j = 0; j < toAddress.length; j++){
                    System.out.println(toAddress[j].toString());
                }

                //Iterate multiparts
                Multipart multipart = (Multipart) message.getContent();
                for(int k = 0; k < multipart.getCount(); k++){
                    BodyPart bodyPart = multipart.getBodyPart(k);
                    InputStream stream =
                            (InputStream) bodyPart.getInputStream();
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(stream));

                    while (bufferedReader.ready()) {
                        System.out.println(bufferedReader.readLine());
                    }
                    System.out.println();
                }
            }
            inbox.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e){
            e.printStackTrace();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

}
