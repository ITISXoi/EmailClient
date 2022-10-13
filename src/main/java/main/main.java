package main;


import sendMail.MessageSend;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

public class main {
    private static Object mailController;

    public static void main(String[] args) {
        MessageSend send = new MessageSend();
        Session sess = send.createSession();
        try {
            Message mess = send.createMessage(sess);
            Transport.send(mess);
        } catch (MessagingException e) {
            System.out.println("Messaging Exception: " + e);
        }
    }

}
