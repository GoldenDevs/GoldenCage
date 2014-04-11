/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.handlers;

import edu.esprit.entities.*;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aymen
 */
public class ClientHandlerStat extends DefaultHandler{
    private Vector clientss;
    String clientTag="close";

    public ClientHandlerStat() {
        clientss = new Vector();
    }

    public ClientStat[] getClient() {
       ClientStat[] clients = new ClientStat[clientss.size()];
         
       clientss.copyInto(clients);
        return clients;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private ClientStat currentClient;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("client")) {

            if (currentClient != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentClient = new ClientStat();
        } else if (qName.equals("kk")) {
           clientTag = "open";
        } 
    }

    public void endElement(String url, String localName, String qName) throws SAXException {

        if (qName.equals("client")) {
            // we are no longer processing a <reg.../> tag
            clientss.addElement(currentClient);
            System.out.println(clientss.size()+"client");
            currentClient = null;
        } else if (qName.equals("kk")) {
           clientTag = "close";
        } 
        
     
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentClient != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (clientTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                
                currentClient.setNbClients((Integer.parseInt(id)));
            } 
        }
    }
    
}
