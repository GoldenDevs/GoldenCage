package edu.esprit.midlet;

import edu.esprit.entities.*;
import edu.esprit.handlers.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Elyes
 */
public class MobileV2 extends MIDlet implements CommandListener,Runnable
{
    Display disp = Display.getDisplay(this);
    Command cmdExit = new Command("Exit", Command.EXIT, 1);
    Command cmdBack = new Command("Retour", Command.BACK, 1);
    Command cmdSMS = new Command("Envoyer SMS", Command.OK, 1);
    Form loadingDialog = new Form("Please Wait");
    //Acceuil
    Form f = new Form("Golden Cage v0.1 J2ME");
    Command cmdInscritClient = new Command("Inscription Client", Command.SCREEN, 1);
    Command cmdInscritPrest = new Command("Inscription Prestataire", Command.SCREEN, 1);
    Command cmdAuth = new Command("Authentification", Command.SCREEN, 1);
    Command cmdAbout = new Command("Guide GoldenCage", Command.SCREEN, 1);
    TextField tfLogin=new TextField("Login : ", "", 500, TextField.ANY);;
    TextField tfPassword=new TextField("Password : ", "", 500, TextField.PASSWORD);;
    
    //Acceuil Client
    Form f1 = new Form("Welcome ");
    Command cmdStat = new Command("Statistique", Command.SCREEN, 1);
    Command cmdParse = new Command("List des Offres", Command.SCREEN, 1);
    
    
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
    Command cmdRechOff = new Command("Rechercher Offre", Command.SCREEN, 1);
    Command cmdRec = new Command("Signaler", Command.SCREEN, 1);
    
    //Valider Reservation
    Form frez = new Form("Reservation Offre");   
    Alert altERez = new Alert("Erreur lors de reservation");
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
    
    //Add Commenataire
    TextBox tb1=new TextBox("Ajout Commentaire", "", 255, TextField.ANY);
    Command cmdVCom = new Command("Ajouter", Command.OK, 1);
    StringBuffer sbCom=new StringBuffer();
    Alert altCom = new Alert("Commentaire ajouter avec Succes", "Commentaire ajouter avec Succes !", null, AlertType.INFO); 
    Alert altECom = new Alert("Erreur", "Ajout non effectuer!", null, AlertType.INFO); 
    //Recherche Offre
    Form fRech = new Form("Recherche Offre");
    Command cmdRech = new Command("Rechercher", Command.OK, 1);
    TextField tfRech =new TextField("Libelle Offre : ", "", 500, TextField.ANY);
    Alert altERech = new Alert("Erreur", "Offre n'existe pas!", null, AlertType.INFO); 
    
    
    
    //Other
    StringBuffer sb = new StringBuffer();
    Image img;
    Image imgAcceuil;
    Image imgrez;  
    Image imgTemp;
    Image imgTempRe;
    ImageItem im;
    int found;
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
      
    
    
    ///Statistique 
    Form statForm = new Form("acceuil");
    Command cmdB = new Command("statBatton", Command.OK, 0);
    Command cmdC = new Command("statCylindres", Command.OK, 0);
    Command cmdBackStat = new Command("Back Stat", Command.OK, 1);
    Command cmdExitStat = new Command("Exit Stat", Command.EXIT, 1);
    Command cmdP = new Command("statPie", Command.OK, 0);
    global gl = new global();
    OffreStat[] listoffre;
    ClientStat[] client;
    PrestataireStat[] prestataire;
    Image m;
    ImageItem m2;
    int typeStat = 0;
    
    
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
        f.append(tfLogin);
        f.append(tfPassword);
        f.addCommand(cmdInscritClient);
        f.addCommand(cmdInscritPrest);
        f.addCommand(cmdAuth);
        f.addCommand(cmdAbout);
        f.addCommand(cmdExit);
        f.setCommandListener(this);
        
        //Initialisation Form Acceuil Client 
        f1.addCommand(cmdExit);
        f1.addCommand(cmdParse);
        f1.addCommand(cmdStat);
        f1.setCommandListener(this);
        
        //Liste des offres
        lst.setCommandListener(this);
        
        //Initialisation Form  Detail Offre
        f2.addCommand(cmdBack);
        f2.addCommand(cmdRez);
        f2.addCommand(cmdRechOff);
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
        lstCom.addCommand(cmdAddComm);
        lstCom.setCommandListener(this);
        //Ajouter Commentaire
        tb1.addCommand(cmdVCom);
        tb1.addCommand(cmdBack);
        tb1.setCommandListener(this);
        
        //Statistique
        try {
               m= Image.createImage("/statImage.png");
         } catch (IOException ex) {
             ex.printStackTrace();
         }
         m2=new ImageItem("", m, ImageItem.LAYOUT_CENTER, null);

        statForm.append("Choisissez le type de statistiques:");
        statForm.append(m2);
        statForm.addCommand(cmdB);
        statForm.addCommand(cmdP);
        statForm.addCommand(cmdC);
        statForm.addCommand(cmdExitStat);
        statForm.setCommandListener(this);

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
        if (c == lst.SELECT_COMMAND && d==lst) {  
            f2.append("Informations Offre :"+offres[indexOff].getLibelle_off()+" \n");  
            Thread th=new Thread(new Runnable() {
                public void run() {
                    detailOffre(indexOff);   
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
                    sendSMS(offres[indexOff].getTelephone(), "Vous Avez un nouveau Client dans l'offre"+offres[indexOff].getLibelle_off()+"\nBy GoldenCage Mobile Application!");
                    reservationOffre(userLogin,offres[indexOff].getId_Offre(), dfd.getDate().toString(), dff.getDate().toString());
                }
            });
            threz.start();
             
        }
       if(c==cmdRechOff && d==f2){
           
           disp.setCurrent(fRech);
       }
       
       if(c==cmdCom && d==f2){
           Thread thCom=new Thread(new Runnable() {
               public void run() {
                   
                   fCom.deleteAll();
                   listCommenatires(offres[lst.getSelectedIndex()].getId_Offre());
               }
           });
           thCom.start();           
           disp.setCurrent(lstCom);
       }
       
       if(c==cmdAddComm){
           
           disp.setCurrent(tb1);
       }
       if(c== cmdVCom){
           Thread th=new Thread(new Runnable() {

               public void run() {
                   System.out.println("aegaegaeg : "+tb1.getString());
                   addCommentaire(userLogin, offres[lst.getSelectedIndex()].getId_Offre(), tb1.getString());
               }
           });
           th.start();
           disp.setCurrent(fCom);
       }
       if(c==cmdRech){
           
           found=rechercherOffre(tfRech.getString());
           if(found!=-1){
               f2.append("Informations Offre :"+offres[indexOff].getLibelle_off()+" \n");  
                Thread th=new Thread(new Runnable() {
                    public void run() {
                        f2.deleteAll();
                        detailOffre(found-1);   
                    }
                });
                th.start();   
           }else{
               disp.setCurrent(altERech,lst);
           }               
       }
       //Stat
        if (c == cmdB) {
            typeStat = 0;
            Thread th = new Thread(this);
            th.start();
        } else if (c == cmdP) {
            typeStat = 1;
            Thread th = new Thread(this);
            th.start();
        } else if (c == cmdC) {
            typeStat = 2;
            Thread th = new Thread(this);
            th.start();
        }
        
        if(c==cmdStat){
            disp.setCurrent(statForm);
        }
        
        if(c==cmdExitStat){
            statForm.deleteAll();
            disp.setCurrent(f1);
                    
        }
       
    }
    
    
    
    public void displayListOffres() {        
        listOffres();
        disp.setCurrent(lst);
    }
    
    public void detailOffre(int i){
        try {
                        if(offres[i].getUrlimg()!=null){
                            urlDOfImage=offres[i].getUrlimg();
                        }
                        httpConnection=(HttpConnection)Connector.open(urlDOfImage);//connexion
                        dataInputStream=httpConnection.openDataInputStream();//recuperation
                        size=(int)httpConnection.getLength();
                        data=new byte[size];
                        dataInputStream.readFully(data);
                        img=Image.createImage(data, 0,size);
                        imgrez=resizeImage(img,232,140);
                        
                        f2.append(imgrez);
                        f2.append(showOffre(i));            
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
                    String tempString=commentaires[i].getDate_com()+" "+commentaires[i].getId_client()+"\ndit : "+
                            commentaires[i].getText();
                    lstCom.append(tempString,null);  
                }
            }
            if(commentaires.length == 0){
                lstCom.append("Aucune Commentaire sur cet offre",null); 
            }
        } catch (Exception e) 
        {
            System.out.println("Exception:" + e.toString());
        }
    }
    
    public void addCommentaire(String id_client,String id_offre,String text){
        String url="http://localhost/parsing/addCom.php?text='"+text.replace(' ', '+')+"'&id_offre="+id_offre+"&id_client='"+id_client+"'";
        
        try {
            
            httpConnection=(HttpConnection)Connector.open(url);//connexion
            dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                while ((ch = dataInputStream.read()) != -1) {
                    sbCom.append((char)ch);
                }
                if ("successfully added".equalsIgnoreCase(sbCom.toString().trim())) {                    
                    disp.setCurrent(altCom,lst);
                }else{
                    disp.setCurrent(altECom);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
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
    
    public int rechercherOffre(String libelle){   
        
        for(int i=0;i<offres.length;i++){
            if(offres[i].getLibelle_off().equals(libelle)){
                return Integer.parseInt(offres[i].getId_Offre());
            }
        }       
        return -1;
    }
    
    public void reservationOffre(String idc,String ido,String dd,String  df){
        String urlRez="http://localhost/parsing/reservationOffre.php?id_client='Said'&id_offre="+ido+"&date_debut='"+dd.replace(' ','+')+"'&date_fin='"+df.replace(' ','+')+"'";
        try {
            
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
   
    
    public void run() {


        //fin client


        try {

            //prestataire ************************
            //offre************************************

            // this will handle our XML
            PrestHandlerStat prestatairesHandler = new PrestHandlerStat();
            // get a parser object
            SAXParser parserP = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hcP = (HttpConnection) Connector.open("http://localhost/parsing/getXmlPrestataires.php");
            DataInputStream disP = new DataInputStream(hcP.openDataInputStream());
            parserP.parse(disP, prestatairesHandler);
            // display the result
            prestataire = prestatairesHandler.getPrestataire();

            if (prestataire.length > 0) {

                for (int i = 0; i < prestataire.length; i++) {
                    gl.nbprest = prestataire[i].getNbPrestataires();
//                    System.out.println(gl.nbprest+"klklklkl");
                }
            }
            //fin Prestataire***************************
            //offre************************************

            // this will handle our XML
            OffreHandlerStat offresHandler = new OffreHandlerStat();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/getXmlOffresStat.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse((InputStream) dis, (DefaultHandler) offresHandler);
            // display the result
            listoffre = offresHandler.getOffres();

            if (listoffre.length > 0) {

                for (int i = 0; i < listoffre.length; i++) {
                    gl.nbroff = listoffre[i].getNbOffres();
                    //System.out.println(gl.nbroff);
                }
            }
            //fin offre**********************************************
            // this will handle our XML

            //Client***********************************************
            // get a parser object
            SAXParser parserC = SAXParserFactory.newInstance().newSAXParser();
            ClientHandlerStat clientHandler = new ClientHandlerStat();

            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hcC = (HttpConnection) Connector.open("http://localhost/parsing/getXmlClients.php");
            DataInputStream disC = new DataInputStream(hcC.openDataInputStream());
            parser.parse((InputStream) disC, (DefaultHandler) clientHandler);

            // display the result
            client = clientHandler.getClient();

            if (client.length > 0) {

                for (int i = 0; i < client.length; i++) {
                    gl.nbclient = client[i].getNbClients();
                    //System.out.println(gl.nbroff);
                }
            }
            //fin Client**************************

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }


        // lst.addCommand(cmdFind);
        if (typeStat == 0) {
            disp.setCurrent(new Draw2());

        } else if (typeStat == 1) {
            disp.setCurrent(new Draw1());
        } else if (typeStat == 2) {
            disp.setCurrent(new Draw3());
        }
        
    }

    /**
     *
     */
    public class Draw1 extends Canvas implements CommandListener {

        int x = gl.nbroff;
        int y = gl.nbclient;
        int z = gl.nbprest;
        int w = getWidth(); // déclaration de la largeur
        int h = getHeight(); // déclaration de la hauteur
        int a = w / 2 - 80;//position initiale
        int b = h / 2 - 30;

        public Draw1() {
            addCommand(cmdBackStat);
            setCommandListener(this);
        }

        protected void paint(Graphics g) {

            g.setColor(220, 220, 220);
            g.fillRect(0, 0, w, h);

           

            for (int i = 0; i < 60; i++) {
                g.setColor(100, 100, 100);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 200, 1);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i - 50, 1, 50);
                g.setColor(0, 0, 0);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 1, 1);
            }


            g.setColor(0, 0, 0);
            g.fillArc(a, b, 181, 101, 0, 360);

            g.setColor(255, 0, 0);
            g.fillArc(a, b, 180, 100, 0, x * 360 / (x + y + z));

            g.setColor(0, 255, 0);
            g.fillArc(a, b, 180, 100, x * 360 / (x + y + z), ((y) * 360 / (x + y + z)));

            g.setColor(0, 0, 255);
            g.fillArc(a, b, 180, 100, ((y + x) * 360 / (x + y + z)), ((z) * 360 / (x + y + z)));



            //3D autrement:
            for (int i = 2; i < 20; i++) {
                g.setColor(0, 0, 0);
                g.fillArc(a - 1, b - i - 1, 182, 101, 0, 181);

                g.setColor(255, 0, 0);
                g.fillArc(a, b - i, 180, 100, 0, x * 360 / (x + y + z));

                g.setColor(0, 255, 0);
                g.fillArc(a, b - i, 180, 100, x * 360 / (x + y + z), ((y) * 360 / (x + y + z)));

                g.setColor(0, 0, 255);
                g.fillArc(a, b - i, 180, 100, ((y + x) * 360 / (x + y + z)), ((z) * 360 / (x + y + z)));

            }

            g.setColor(0, 0, 0);
            g.fillArc(a - 1, b - 21, 182, 102, 0, 360);
            g.setColor(255, 0, 0);
            g.fillArc(a, b - 20, 180, 100, 0, x * 360 / (x + y + z));

            g.setColor(0, 255, 0);
            g.fillArc(a, b - 20, 180, 100, x * 360 / (x + y + z), ((y) * 360 / (x + y + z)));

            g.setColor(0, 0, 255);
            g.fillArc(a, b - 20, 180, 100, ((y + x) * 360 / (x + y + z)), ((z) * 360 / (x + y + z)));





            //legende
            g.setColor(0, 0, 0);
            g.fillRect(9, h - 23, 22, 22);
            g.setColor(255, 0, 0);
            g.fillRect(10, h - 22, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre d'offres ", 82, h - 10, Graphics.HCENTER | Graphics.BASELINE);

            g.setColor(0, 0, 0);
            g.fillRect(9, h - 43, 22, 22);
            g.setColor(0, 0, 255);
            g.fillRect(10, h - 42, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de prestataires ", 95, h - 30, Graphics.HCENTER | Graphics.BASELINE);


            g.setColor(0, 0, 0);
            g.fillRect(9, h - 63, 22, 22);
            g.setColor(0, 255, 0);
            g.fillRect(10, h - 62, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de clients ", 80, h - 50, Graphics.HCENTER | Graphics.BASELINE);
        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdBackStat) {
                disp.setCurrent(statForm);
            }
        }
    }
    
    public class Draw3 extends Canvas implements CommandListener {

        int x = gl.nbroff;
        int y = gl.nbclient;
        int z = gl.nbprest;
        int w = getWidth(); // déclaration de la largeur
        int h = getHeight(); // déclaration de la hauteur
        int a = w / 2 - 90;//position initiale
        int b = h / 2 - 20;
        int lmax = h / 2;

        public Draw3() {
            addCommand(cmdBackStat);
            setCommandListener(this);
        }

        protected void paint(Graphics g) {

            g.setColor(220, 220, 220);
            g.fillRect(0, 0, w, h);




            for (int i = 0; i < 40; i++) {
                g.setColor(100, 100, 100);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 220, 1);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i - 80, 1, 80);
                g.setColor(0, 0, 0);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 1, 1);
            }



            g.setColor(0, 0, 0);
            g.fillArc(a, b + 60, 62, 22, 0, 360);
            g.setColor(255, 0, 0);
            g.fillArc(a + 1, b + 61, 60, 20, 0, 360);


            g.setColor(0, 0, 0);
            g.fillArc(a + 70, b + 60, 62, 22, 0, 360);
            g.setColor(0, 255, 0);
            g.fillArc(a + 71, b + 61, 60, 20, 0, 360);

            g.setColor(0, 0, 0);
            g.fillArc(a + 140, b + 60, 62, 22, 0, 360);
            g.setColor(0, 0, 255);
            g.fillArc(a + 141, b + 61, 60, 20, 0, 360);



            //3D autrement:
            for (int i = 2; i < (x * h / (2 * (x + y + z))); i++) {

                g.setColor(0, 0, 0);
                g.fillArc(a, b + 60 - i, 62, 22, 0, 230);
                g.setColor(255, 0, 0);
                g.fillArc(a + 1, b + 61 - i, 60, 20, 0, 360);
            }
            for (int i = 2; i < (y * h / (2 * (x + y + z))); i++) {

                g.setColor(0, 0, 0);
                g.fillArc(a + 70, b + 60 - i, 62, 22, 0, 230);
                g.setColor(0, 255, 0);
                g.fillArc(a + 71, b + 61 - i, 60, 20, 0, 360);
            }
            for (int i = 2; i < (z * h / (2 * (x + y + z))); i++) {

                g.setColor(0, 0, 0);
                g.fillArc(a + 140, b + 60 - i, 62, 22, 0, 230);
                g.setColor(0, 0, 255);
                g.fillArc(a + 141, b + 61 - i, 60, 20, 0, 360);
            }

            g.setColor(0, 0, 0);
            g.fillArc(a, b + 60 - (x * h / (2 * (x + y + z))), 62, 22, 0, 360);
            g.setColor(255, 0, 0);
            g.fillArc(a + 1, b + 61 - (x * h / (2 * (x + y + z))), 60, 20, 0, 360);


            g.setColor(0, 0, 0);
            g.fillArc(a + 70, b + 60 - (y * h / (2 * (x + y + z))), 62, 22, 0, 360);
            g.setColor(0, 255, 0);
            g.fillArc(a + 71, b + 61 - (y * h / (2 * (x + y + z))), 60, 20, 0, 360);

            g.setColor(0, 0, 0);
            g.fillArc(a + 140, b + 60 - (z * h / (2 * (x + y + z))), 62, 22, 0, 360);
            g.setColor(0, 0, 255);
            g.fillArc(a + 141, b + 61 - (z * h / (2 * (x + y + z))), 60, 20, 0, 360);







            //lgende
             g.setColor(0, 0, 0);
            g.fillRect(9, h - 23, 22, 22);
            g.setColor(255, 0, 0);
            g.fillRect(10, h - 22, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre d'offres ", 82, h - 10, Graphics.HCENTER | Graphics.BASELINE);

            g.setColor(0, 0, 0);
            g.fillRect(9, h - 43, 22, 22);
            g.setColor(0, 0, 255);
            g.fillRect(10, h - 42, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de prestataires ", 95, h - 30, Graphics.HCENTER | Graphics.BASELINE);


            g.setColor(0, 0, 0);
            g.fillRect(9, h - 63, 22, 22);
            g.setColor(0, 255, 0);
            g.fillRect(10, h - 62, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de clients ", 80, h - 50, Graphics.HCENTER | Graphics.BASELINE);
        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdBackStat) {
                disp.setCurrent(statForm);
            }
        }
    }

    /// draw statistiques batton
    public class Draw2 extends Canvas implements CommandListener {

        int x = gl.nbroff;
        int y = gl.nbclient;
        int z = gl.nbprest;

        public Draw2() {
            addCommand(cmdBackStat);
            setCommandListener(this);

        }
        int w = getWidth(); // déclaration de la largeur
        int h = getHeight(); // déclaration de la hauteur
        int a = w / 2 - 90;//position initiale
        int lmax = h / 2;

        protected void paint(Graphics g) {

            g.setColor(220, 220, 220);
            g.fillRect(0, 0, w, h);
            


            for (int i = 0; i < 40; i++) {
                g.setColor(100, 100, 100);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 180, 1);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i - 80, 1, 80);
                g.setColor(0, 0, 0);
                g.fillRect(i + 5, 3 * h / 4 - 2 * i, 1, 1);
            }




            g.setColor(0, 0, 0);
            g.fillRect(a - 1, 49 + lmax - (x * h / (2 * (x + y + z))), 42, 2 + (x * h / (2 * (x + y + z))));
            g.setColor(255, 0, 0);
            g.fillRect(a, 50 + lmax - (x * h / (2 * (x + y + z))), 40, (x * h / (2 * (x + y + z))));

            g.setColor(0, 0, 0);
            g.fillRect(a + 49, 49 + lmax - (y * h / (2 * (x + y + z))), 42, 2 + (y * h / (2 * (x + y + z))));
            g.setColor(0, 255, 0);
            g.fillRect(a + 50, 50 + lmax - (y * h / (2 * (x + y + z))), 40, (y * h / (2 * (x + y + z))));

            g.setColor(0, 0, 0);
            g.fillRect(a + 99, 49 + lmax - (z * h / (2 * (x + y + z))), 42, 2 + (z * h / (2 * (x + y + z))));
            g.setColor(0, 0, 255);
            g.fillRect(a + 100, 50 + lmax - (z * h / (2 * (x + y + z))), 40, (z * h / (2 * (x + y + z))));
//             
         //3D autrement:
            for (int i = 1; i < 9; i++) {
                g.setColor(0, 0, 0);
                g.fillRect(a - 1 - i, i + 50 + lmax - (x * h / (2 * (x + y + z))), 42, (x * h / (2 * (x + y + z))));
                g.setColor(255, 0, 0);
                g.fillRect(a - i, i + 50 + lmax - (x * h / (2 * (x + y + z))), 40, (x * h / (2 * (x + y + z))));


                g.setColor(0, 0, 0);
                g.fillRect(a + 49 - i, i + 50 + lmax - (y * h / (2 * (x + y + z))), 42, (y * h / (2 * (x + y + z))));
                g.setColor(0, 255, 0);
                g.fillRect(a + 50 - i, i + 50 + lmax - (y * h / (2 * (x + y + z))), 40, (y * h / (2 * (x + y + z))));
//             

                g.setColor(0, 0, 0);
                g.fillRect(a + 99 - i, i + 50 + lmax - (z * h / (2 * (x + y + z))), 42, (z * h / (2 * (x + y + z))));
                g.setColor(0, 0, 255);
                g.fillRect(a + 100 - i, i + 50 + lmax - (z * h / (2 * (x + y + z))), 40, (z * h / (2 * (x + y + z))));
//                 
            }
//             
            g.setColor(0, 0, 0);
            g.fillRect(a - 9, 59 + lmax - (x * h / (2 * (x + y + z))), 42, 2 + (x * h / (2 * (x + y + z))));
            g.setColor(255, 0, 0);
            g.fillRect(a - 8, 60 + lmax - (x * h / (2 * (x + y + z))), 40, (x * h / (2 * (x + y + z))));


            g.setColor(0, 0, 0);
            g.fillRect(a + 41, 59 + lmax - (y * h / (2 * (x + y + z))), 42, 2 + (y * h / (2 * (x + y + z))));
            g.setColor(0, 255, 0);
            g.fillRect(a + 42, 60 + lmax - (y * h / (2 * (x + y + z))), 40, (y * h / (2 * (x + y + z))));

            g.setColor(0, 0, 0);
            g.fillRect(a + 91, 59 + lmax - (z * h / (2 * (x + y + z))), 42, 2 + (z * h / (2 * (x + y + z))));
            g.setColor(0, 0, 255);
            g.fillRect(a + 92, 60 + lmax - (z * h / (2 * (x + y + z))), 40, (z * h / (2 * (x + y + z))));
//             


            //legende
             g.setColor(0, 0, 0);
            g.fillRect(9, h - 23, 22, 22);
            g.setColor(255, 0, 0);
            g.fillRect(10, h - 22, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre d'offres ", 82, h - 10, Graphics.HCENTER | Graphics.BASELINE);

            g.setColor(0, 0, 0);
            g.fillRect(9, h - 43, 22, 22);
            g.setColor(0, 0, 255);
            g.fillRect(10, h - 42, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de prestataires ", 95, h - 30, Graphics.HCENTER | Graphics.BASELINE);


            g.setColor(0, 0, 0);
            g.fillRect(9, h - 63, 22, 22);
            g.setColor(0, 255, 0);
            g.fillRect(10, h - 62, 20, 20);
            g.setColor(0, 0, 0);
            g.drawString(":nombre de clients ", 80, h - 50, Graphics.HCENTER | Graphics.BASELINE);
        }
         public void commandAction(Command c, Displayable d) {
            if (c == cmdBackStat) {
                disp.setCurrent(statForm);
            }
        }
    
    
}

}