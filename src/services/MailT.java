/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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
 * @author me
 */
public  class MailT {

    
private final String username = "mounabenkouks@gmail.com";
private final String password = "welcometohell@@@";
private final String to ;
private final String sujet ;
private final String msg ;

    public MailT(String to, String sujet, String msg) {
        this.to = to;
        this.sujet = sujet;
        this.msg = msg;
    }

public void envoyer() {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
props.put("mail.smtp.ssl.trust", "*");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("entraideesprit@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(to));
message.setSubject(sujet);
message.setText(msg);
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
    System.out.println(e);
} 
}
}