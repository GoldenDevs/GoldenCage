/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.midlet;

import java.io.DataInputStream;
import java.io.IOException;
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
public class ShowOffres extends MIDlet implements CommandListener,Runnable
{
    Display disp = Display.getDisplay(this);
    Command cmdParse = new Command("Offres", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdAuth = new Command("Authentification", Command.OK, 1);
    Command cmdExit = new Command("Exit", Command.EXIT, 1);
    Command cmdSMS = new Command("Envoyer SMS", Command.OK, 1);
    Offre[] offres;
    List lst = new List("Offres", List.IMPLICIT);
    Form f1 = new Form("Acceuil");
    Form f2 = new Form("Infos Offre");
    Form f = new Form("Golden Cage v0.1 J2ME");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    Image img;
    Image imgAcceuil;
    Image imgrez;    
    ImageItem im;
    
    String urlOfImage="http://localhost/goldencage/images/defaul.jpg";
    Alert alert;
     //connexion 
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    MessageConnection clientConn;

    int size;
    byte[]data;
    
    
    int screenWidth=232;
    int screenHeight=140;
    
    //blablabla
    String msgPrest="Vous avez un Client dépeche toi :D ";
    String numPrest="5550000";
    public void init() {
        try {
            imgAcceuil=Image.createImage("/LogoV2.jpg");
        } catch (IOException ex) {
              System.out.println("Errreeeuuur"+ex.getMessage());
        }
        f.append(imgAcceuil);
        f.addCommand(cmdAuth);
        f.addCommand(cmdExit);
        f.setCommandListener(this);
        
        f1.addCommand(cmdSMS);
        f1.addCommand(cmdParse);
        f1.setCommandListener(this);
        lst.setCommandListener(this);
        
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        disp.setCurrent(f);
    }
    
    public void startApp() 
    {       
        init();
    }

    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        
    }

    public void run() {        
        listOffres();
        disp.setCurrent(lst);
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
    
    public void commandAction(Command c, Displayable d) {
        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        if(c==cmdAuth && d==f){
            disp.setCurrent(f1);
        }
        if(c==cmdSMS){
          
            Thread thsms=new Thread(new Runnable() {

                public void run() {
                    sendSMS(numPrest, msgPrest);
                }
            });
            thsms.start();
            
        }
        if (c == List.SELECT_COMMAND) {
            f2.append("Informations Offre :"+offres[lst.getSelectedIndex()].getLibelle_off()+" \n");
            
            Thread th=new Thread(new Runnable() {

                public void run() {
                    try {
                        if(offres[lst.getSelectedIndex()].getUrlimg()!=null){
                            urlOfImage=offres[lst.getSelectedIndex()].getUrlimg();
                        }
                        httpConnection=(HttpConnection)Connector.open(urlOfImage);//connexion
                        dataInputStream=httpConnection.openDataInputStream();//recuperation
                        size=(int)httpConnection.getLength();
                        data=new byte[size];
                        dataInputStream.readFully(data);
                        img=Image.createImage(data, 0,size);
                        imgrez=resizeImage(img);
                        
                        f2.append(imgrez);
                        f2.append(showOffre(lst.getSelectedIndex()));            
                        disp.setCurrent(f2);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }    
                }
            });
            th.start();
            
        }

        if (c == cmdBack) {
            f2.deleteAll();
            disp.setCurrent(lst);
        }
    }
    
    public Image resizeImage(Image src) {
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
                    lst.append(offres[i].getLibelle_off(), null);  
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
