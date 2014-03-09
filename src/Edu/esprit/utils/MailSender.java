/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edu.esprit.utils;
import Edu.esprit.DAO.ReclamationDAO;
import Edu.esprit.Entities.Reclamation;
import Edu.esprit.GUI.InterfaceReclamation;
import Edu.esprit.GUI.InterfaceRepMail;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import static Edu.esprit.GUI.Acceuil.idReclamation;
/**
 *
 * @author Aymen
 */
public class MailSender {

    public MailSender() {
    }
    ReclamationDAO rd=new ReclamationDAO();
    Reclamation r =rd.findReclamationByIdRec(idReclamation);
    public static void sendMail(String dest,String sujet,String texte,String attachement_path,String txtf_attach_name) {
        
        ReclamationDAO rd=new ReclamationDAO();
    Reclamation r =rd.findReclamationByIdRec(idReclamation);Properties props=new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.port", "465");
       
       Session session=Session.getDefaultInstance(props,
               new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication("aymen.berrima@esprit.tn", "A07189885");
               }
               }
               );
        
       try{
        Message message= new MimeMessage(session) ;
       message.setFrom(new InternetAddress("aymen.berrima@esprit.tn"));
       message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(dest));
       message.setSubject(sujet);
       message.setText(texte);
       if (InterfaceRepMail.verif==0) 
       {  //Message message= new MimeMessage(session) ;
       
       Transport.send(message);
       JOptionPane.showMessageDialog(null,"message sent");
       r.setEtat("traitee");
       rd.updateReclamation(r);
       
       }
       
       
       else //if(InterfaceRepMail.verif==1)
       {
           MimeBodyPart messageBodyPart=new MimeBodyPart();
        messageBodyPart.setText(texte);
        Multipart multipart=new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
           
           
           messageBodyPart=new MimeBodyPart();
           javax.activation.DataSource source=new FileDataSource(attachement_path);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(txtf_attach_name);
           multipart.addBodyPart(messageBodyPart);
           message.setContent(multipart);
           
           
           
       Transport.send(message);
       JOptionPane.showMessageDialog(null,"file sent");
       r.setEtat("traitee");
       rd.updateReclamation(r);

       }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, "erreur lors de l envoi !");
       }
       
        
    }
    
    
}
