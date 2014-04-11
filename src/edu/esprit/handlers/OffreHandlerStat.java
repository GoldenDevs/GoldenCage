/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author mohamed
 */
public class OffreHandlerStat extends DefaultHandler{
    private Vector offress;
    String offreTag="close";

    public OffreHandlerStat() {
        offress = new Vector();
    }

    public OffreStat[] getOffres() {
        OffreStat[] offres = new OffreStat[offress.size()];
        offress.copyInto(offres);
        return offres;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private OffreStat currentOffre;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("offre")) {

            if (currentOffre != null) {
                throw new IllegalStateException("already processing an offre");
            }
            currentOffre = new OffreStat();
        } else if (qName.equals("kk")) {
           offreTag = "open";
        } 
    }

    public void endElement(String url, String localName, String qName) throws SAXException {

        if (qName.equals("offre")) {
            // we are no longer processing a <reg.../> tag
            offress.addElement(currentOffre);
            currentOffre = null;
        } else if (qName.equals("kk")) {
            offreTag = "close";
        } 
        
     
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentOffre != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (offreTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                
                currentOffre.setNbOffres((Integer.parseInt(id)));
            } 
        }
    }
    
}
