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
public class PrestHandlerStat extends DefaultHandler{
    private Vector prestatairess;
    String prestataireTag="close";

    public PrestHandlerStat() {
        prestatairess = new Vector();
    }

    public PrestataireStat[] getPrestataire() {
        PrestataireStat[] prestataires = new PrestataireStat[prestatairess.size()];      
       prestatairess.copyInto(prestataires);
        return prestataires;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private PrestataireStat currentPrestataire;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("prestataire")) {

            if (currentPrestataire != null) {
                throw new IllegalStateException("already processing a prestataire");
            }
            currentPrestataire = new PrestataireStat();
        } else if (qName.equals("kk")) {
           prestataireTag = "open";
           
        } 
    }

    public void endElement(String url, String localName, String qName) throws SAXException {

        if (qName.equals("prestataire")) {
            // we are no longer processing a <reg.../> tag
           prestatairess.addElement(currentPrestataire);
            
            currentPrestataire = null;
        } else if (qName.equals("kk")) {
            prestataireTag = "close";
            System.out.println(prestatairess.size()+"bgcbgcnbf");
        } 
        
     
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPrestataire != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (prestataireTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                
                currentPrestataire.setNbPrestataires((Integer.parseInt(id)));
            } 
        }
    }
    
}
