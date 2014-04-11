package edu.esprit.handlers;

import edu.esprit.entities.*;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Elyes
 */
public class CommentairesHandler extends DefaultHandler{
    private Vector commentaires;
    String idTag = "close";
    String id_clientTag = "close";
    String id_offreTag = "close";
    String textTag="close"   ;
    String dateTag= "close";
    
    public CommentairesHandler() {
        commentaires = new Vector();
    }

    public Commentaire[] getCommentaires() {
        Commentaire[] commentairess = new Commentaire[commentaires.size()];
        commentaires.copyInto(commentairess);
        return commentairess;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Commentaire currentCommentaire;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("commentaire")) {
            if (currentCommentaire != null) {
                throw new IllegalStateException("already processing a Comment");
            }
            currentCommentaire = new Commentaire();
        } else if (qName.equals("id_comment")) {
            idTag = "open";
        } else if (qName.equals("id_client")) {
            id_clientTag = "open";
        } else if (qName.equals("id_offre")) {
            id_offreTag = "open";
        }else if (qName.equals("text")) {
            textTag = "open";
        } else if (qName.equals("date_comm")) {
            dateTag = "open";
        } 
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("commentaire")) {
            // we are no longer processing a <reg.../> tag
            commentaires.addElement(currentCommentaire);
            currentCommentaire = null;
        } else if (qName.equals("id_comment")) {
            idTag = "close";
        } else if (qName.equals("id_client")) {
            id_clientTag = "close";
        } else if (qName.equals("id_offre")) {
            id_offreTag = "close";
        }else if (qName.equals("text")) {
            textTag = "close";
        } else if (qName.equals("date_comm")) {
            dateTag = "close";
        } 
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentCommentaire != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentCommentaire.setIdCom(id);
            } else
                if (id_clientTag.equals("open")) {
                String idc = new String(ch, start, length).trim();
                currentCommentaire.setId_client(idc);
            } else
                    if (id_offreTag.equals("open")) {
                String ido = new String(ch, start, length).trim();
                currentCommentaire.setId_offre(ido);
            }else
                    if (dateTag.equals("open")) {
                String datep = new String(ch, start, length-9).trim();                        
                currentCommentaire.setDate_com(datep);
            }else
                    if (textTag.equals("open")) {
                String text = new String(ch, start, length).trim();
                currentCommentaire.setText(text);
            }
        }
    }
    
}
