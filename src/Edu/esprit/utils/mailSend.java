/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.utils;

/**
 *
 * @author Elyes
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class mailSend {
   
    public static void Send(String dest,String sujet,String Text ) {
      String to = dest;

      String from = "bselyes@gmail.com";
      final String username = "bselyes";
      final String password = "o0e1zk2890";

      
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      
      Session session = Session.getInstance(props,new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         
         Message message = new MimeMessage(session);

         
         message.setFrom(new InternetAddress(from));

         
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));
         message.setSubject(sujet);
         message.setText(Text);
         Transport.send(message);
         
          JOptionPane.showMessageDialog(null, "Mail Envoyee avec Succés");

      } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'envoi de mail");
            throw new RuntimeException(e);
      }
   }
}