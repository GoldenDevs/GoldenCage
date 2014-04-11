package edu.esprit.midlet;

import edu.esprit.entities.*;
import edu.esprit.handlers.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.TimeZone;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Elyes
 */
public class Mobile extends MIDlet implements CommandListener
{
    Display disp = Display.getDisplay(this);
    Command cmdExit = new Command("Exit", Command.EXIT, 1);
    Command cmdBack = new Command("Retour", Command.BACK, 1);
    Command cmdSMS = new Command("Envoyer SMS", Command.OK, 1);
    Form loadingDialog = new Form("Please Wait");
    //Acceuil
    Form f = new Form("Golden Cage v0.1 J2ME");
    Command cmdInscrit = new Command("S'inscrire", Command.SCREEN, 1);
    Command cmdAuth = new Command("Authentification", Command.SCREEN, 1);
    Command cmdAbout = new Command("Guide GoldenCage", Command.SCREEN, 1);
    
    //Acceuil Client
    Form f1 = new Form("Welcome ");
    Command cmdStat = new Command("Statistique", Command.SCREEN, 1);
    Command cmdParse = new Command("List des Offres", Command.SCREEN, 1);
    Command cmdRechOff = new Command("Rechercher Offfre", Command.SCREEN, 1);
    
    //Liste des Offres
    Offre[] offres;
    List lst = new List("Offres", List.IMPLICIT);
    
    //list des Commentaires
    Commentaire[] commentaires;
    //Detail Offre
    Form f2 = new Form("Infos Offre");
    Command cmdRez = new Command("Reservation", Command.SCREEN, 1);
    Command cmdCom = new Command("Commentaires", Command.SCREEN, 1);
    Command cmdEval = new Command("Evaluer", Command.SCREEN, 1);
    Command cmdRec = new Command("Signaler", Command.SCREEN, 1);
    
    //Valider Reservation
    Form frez = new Form("Reservation Offre");   
    Alert altERez = new Alert("altERez:Erreur lors de reservation");
    Alert altRez = new Alert("Reservation Effectuer avec Succes", "Merci pour votre confiance !", null, AlertType.INFO);
    Command cmdConf = new Command("Confirmer", Command.OK, 1);
    DateField dfd=new DateField("Date Debut : ", DateField.DATE_TIME,TimeZone.getTimeZone("GMT"));
    DateField dff=new DateField("Date Fin : ", DateField.DATE_TIME,TimeZone.getTimeZone("GMT"));
    
    //Reclamation
    Form fRec = new Form("Reclamation Offre");
    TextField tfRecSujet =new TextField("Sujet : ", "", 50, TextField.ANY);
    TextField tfRecText =new TextField("Text : ", "", 500, TextField.ANY);
    Command cmdVRec = new Command("Valider", Command.OK, 1);
    
    //Commentaire
    Form fCom=new Form("Liste des Commetaires");
    Command cmdAddComm = new Command("Ajouter Commenatire", Command.OK, 1);
    List lstCom=new List("Commentaires", List.IMPLICIT);
    
    //Recherche Offre
    Form fRech = new Form("Recherche Offre");
    Command cmdRech = new Command("Rechercher", Command.OK, 1);
    TextField tfRech =new TextField("Libelle Offre : ", "", 500, TextField.ANY);
    //Other
    StringBuffer sb = new StringBuffer();
    Image img;
    Image imgAcceuil;
    Image imgrez;  
    Image imgTemp;
    Image imgTempRe;
    ImageItem im;
    
    String urlDOfImage="http://localhost/goldencage/images/defaul.jpg";
    Alert alert;
    Alert splashAlert =new Alert("GoldenCage v0.1");
     //connexion 
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    MessageConnection clientConn;

    int size;
    int ch;
    byte[]data;
    
    

    //blablabla
    String msgPrest="Vous avez un Client dépeche toi :D ";
    String numPrest="5550000";    
    String urlLImg="";
    String userLogin="Said";
    int indexOff=1;
//    int screenWidth=232;
//    int screenHeight=140;
        
    
    public void init() {
        //Initialisation Form d'Acceuil + Splash
        try {
            imgAcceuil=Image.createImage("/LogoV2.jpg");
        } catch (IOException ex) {
              System.out.println("Errreeeuuur"+ex.getMessage());
        }
        splashAlert.setImage(imgAcceuil);
        //splashAlert.setTimeout(5000);
        f.append(imgAcceuil);
        f.addCommand(cmdInscrit);
        f.addCommand(cmdAuth);
        f.addCommand(cmdAbout);
        f.addCommand(cmdExit);
        f.setCommandListener(this);
        
        //Initialisation Form Acceuil Client 
        f1.addCommand(cmdExit);
        f1.addCommand(cmdParse);
        f1.addCommand(cmdRechOff);
        f1.addCommand(cmdStat);
        f1.setCommandListener(this);
        
        //Liste des offres
        lst.setCommandListener(this);
        
        //Initialisation Form  Detail Offre
        f2.addCommand(cmdBack);
        f2.addCommand(cmdRez);
        f2.addCommand(cmdRec);
        f2.addCommand(cmdCom);
        f2.setCommandListener(this);
        
        //Initialisation Form Reservation Offre
        frez.addCommand(cmdBack);
        frez.addCommand(cmdConf);
        frez.append(dfd);
        frez.append(dff);
        frez.setCommandListener(this);
        
        //Initialisation Form Commentaires
        fCom.addCommand(cmdAddComm);
        fCom.addCommand(cmdBack);
        fCom.setCommandListener(this);
        
        
        //Initialisation Form Reclamation
        fRec.addCommand(cmdVRec);
        fRec.append(tfRecSujet);
        fRec.append(tfRecText);
        fRec.setCommandListener(this);
        try {
            //Some Blablabla
            imgTemp=Image.createImage("/LogoV2.jpg");
            imgTempRe=resizeImage(imgTemp, 40, 40);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //Initialisation Form Recherche offre
        fRech.addCommand(cmdRech);
        fRech.append(tfRech);
        fRech.addCommand(cmdBack);
        fRech.setCommandListener(this);
        
        //List des Commentaires
        lstCom.addCommand(cmdBack);
        lstCom.setCommandListener(this);
    }
    
    public void startApp() 
    {       
        init();
        disp.setCurrent(splashAlert,f);
    }

    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        
    }
   
    public void commandAction(Command c, Displayable d) {
        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(new Runnable() {
                public void run() {
                    displayListOffres();
                }
            });
            th.start();
        }
        //Authentification
        if(c==cmdAuth && d==f){
            disp.setCurrent(f1);
        }
        //SMS
        
        //Detail Offre
        if (c == List.SELECT_COMMAND) {            
            f2.append("Informations Offre :"+offres[lst.getSelectedIndex()].getLibelle_off()+" \n");            
            Thread th=new Thread(new Runnable() {
                public void run() {
                    detailOffre();   
                }
            });
            th.start();   
            f2.deleteAll();
        }
        
        if (c == cmdBack) {
            f2.deleteAll();
            disp.setCurrent(lst);
        }
        
        if( c==cmdRez){
            frez.append(showOffre(indexOff));
            disp.setCurrent(frez);   
        }
        
       if(c==cmdConf && d==frez){
            Thread threz=new Thread(new Runnable() {
                public void run() {
                    reservationOffre(userLogin,offres[lst.getSelectedIndex()].getId_Offre(), dfd.getDate().toString(), dff.getDate().toString());
                }
            });
            threz.start();
             disp.setCurrent(altRez,lst);
        }
       if(c==cmdRechOff && d==f1){
           disp.setCurrent(fRech);
       }
       
       if(c==cmdCom && d==f2){
           Thread thCom=new Thread(new Runnable() {
               public void run() {
                   listCommenatires(offres[lst.getSelectedIndex()].getId_Offre());
               }
           });
           thCom.start();           
           disp.setCurrent(lstCom);
       }
    }
    
    
    
    public void displayListOffres() {        
        listOffres();
        disp.setCurrent(lst);
    }
    
    public void detailOffre(){
        try {
                        if(offres[lst.getSelectedIndex()].getUrlimg()!=null){
                            urlDOfImage=offres[lst.getSelectedIndex()].getUrlimg();
                        }
                        httpConnection=(HttpConnection)Connector.open(urlDOfImage);//connexion
                        dataInputStream=httpConnection.openDataInputStream();//recuperation
                        size=(int)httpConnection.getLength();
                        data=new byte[size];
                        dataInputStream.readFully(data);
                        img=Image.createImage(data, 0,size);
                        imgrez=resizeImage(img,232,140);
                        
                        f2.append(imgrez);
                        f2.append(showOffre(lst.getSelectedIndex()));            
                        disp.setCurrent(f2);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } 
    }
    
    private String showOffre(int i) {
        String res = "";
        if (offres.length > 0) {
            
            sb.append("Date Post : ");
            sb.append(offres[i].getDate_post());
            sb.append("\n");
            sb.append("Prestataire : ");
            sb.append(offres[i].getNomPrest());
            sb.append("\n");
            sb.append("Prix : ");
            sb.append(offres[i].getPrix());
            sb.append("\n");
            sb.append("Note : ");
            sb.append(offres[i].getNoteOffre());
            sb.append("\n");
            sb.append("Etat : ");
            if(Integer.parseInt(offres[i].getStat_offre())==1){
            sb.append("Disponible");
            sb.append("\n");
            sb.append("Contact Responsable : ");
            if(offres[i].getTelephone()==null){
                numPrest="Blablabla";            
            }else{
                numPrest=offres[i].getTelephone();
            }
            sb.append(numPrest);
            sb.append("\n");
            
            }
            else{
            sb.append("Non Disponible");
            sb.append("\n");    
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
  
    public Image resizeImage(Image src,int screenWidth,int screenHeight) {
      int srcWidth = src.getWidth();
      int srcHeight = src.getHeight();
      Image tmp = Image.createImage(screenWidth, srcHeight);
      Graphics g = tmp.getGraphics();
      int ratio = (srcWidth << 16) / screenWidth;
      int pos = ratio/2;


      for (int x = 0; x < screenWidth; x++) {
          g.setClip(x, 0, 1, srcHeight);
          g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }

      Image resizedImage = Image.createImage(screenWidth, screenHeight);
      g = resizedImage.getGraphics();
      ratio = (srcHeight << 16) / screenHeight;
      pos = ratio/2;        

      for (int y = 0; y < screenHeight; y++) {
          g.setClip(0, y, screenWidth, 1);
          g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }
      return resizedImage;

      }
    
    public void listOffres(){
        try 
        {
            OffreHandler offresHandler = new OffreHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/getXmlOffres.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, offresHandler);

            offres = offresHandler.getOffres();
            
            if (offres.length > 0) {
                for (int i = 0; i < offres.length; i++) {
//                        if(offres[i].getUrlimg().equals("")){
//                            urlLImg="/LogoV2.jpg";
//                        }else{
//                            urlLImg=offres[i].getUrlimg();
//                        }
//                        imgTemp=Image.createImage(urlLImg);
                   
                    lst.append(offres[i].getLibelle_off(),imgTempRe);  
                }
            }

        } catch (Exception e) 
        {
            System.out.println("Exception:" + e.toString());
        }
    }
    
    public void listCommenatires(String id){
        try 
        {
            CommentairesHandler commentairesHandler = new CommentairesHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/getXmlCom.php?id="+id);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, commentairesHandler);

            commentaires = commentairesHandler.getCommentaires();
            if (commentaires.length > 0) {
                for (int i = 0; i < commentaires.length; i++) {
//                    String tempString=commentaires[i].getDate_com()+" "+commentaires[i].getId_client()+"\n         "+
//                            commentaires[i].getText();
                    lstCom.append(commentaires[i].getText(),null);  
                }
            }
        } catch (Exception e) 
        {
            System.out.println("Exception:" + e.toString());
        }
    }
    
    public void sendSMS(String toWhom,String message) {
      String mno=toWhom;
      String msg=message;
      if(mno.equals("")) {
               alert = new Alert("Alert");
               alert.setString("Enter Mobile Number!!!");
               alert.setTimeout(2000);
               disp.setCurrent(alert);
                  }
                  else {
                        try {
                              clientConn=(MessageConnection)Connector.open("sms://"+mno);
                        }
                        catch(Exception e) {
                              alert = new Alert("Alert");
                              alert.setString("Unable to connect to Station because of network problem");
                              alert.setTimeout(2000);
                              disp.setCurrent(alert);
                        }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+mno);
                              textmessage.setPayloadText(msg);
                              clientConn.send(textmessage);
                        }
                        
                        catch(Exception e)
                        {
                              Alert alert=new Alert("Alert","",null,AlertType.INFO);
                              alert.setTimeout(Alert.FOREVER);
                              alert.setString("Unable to send");
                              disp.setCurrent(alert);
                        }
                  }
}

    public void reservationOffre(String idc,String ido,String dd,String  df){
        String urlRez="http://localhost/parsing/reservationOffre.php?id_client='Said'&id_offre="+ido+"&date_debut='"+dd.replace(' ','+')+"'&date_fin='"+df.replace(' ','+')+"'";
        try {
            
            System.out.println(dd);
            System.out.println(df);
            httpConnection=(HttpConnection)Connector.open(urlRez);//connexion
            dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                while ((ch = dataInputStream.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {                    
                    disp.setCurrent(altRez,lst);
                }else{
                    disp.setCurrent(altERez);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    public class DrawAcceuil extends Canvas{
        
        int h=getHeight();
        int w=getWidth();
        Image img2;
        int choix ;
        public DrawAcceuil(){}   
        
        public DrawAcceuil(int i){
            this.choix=i;
        }   
        public DrawAcceuil(Image img){
            this.img2=img;
        }   
        protected void paint(Graphics g) {
            
            g.drawImage(img2, w/2, h/2, Graphics.HCENTER|Graphics.VCENTER);

        }
        
    }
}
