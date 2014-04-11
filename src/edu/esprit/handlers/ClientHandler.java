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
 * @author Elyes
 */
public class ClientHandler extends DefaultHandler{
    private Vector offres;
    String idTag = "close";
    String Libelle_offreTag = "close";
    String DispoTag = "close";
    String date_postTag="close"   ;
    String ID_prestTag= "close";
    String noteTag= "close";
    String prixTag = "close";
    String urlimgTag= "close";
    String telTag="close";
    public ClientHandler() {
        offres = new Vector();
    }

    public Offre[] getOffres() {
        Offre[] offress = new Offre[offres.size()];
        offres.copyInto(offress);
        return offress;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Offre currentOffre;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("offre")) {
            if (currentOffre != null) {
                throw new IllegalStateException("already processing a offre");
            }
            currentOffre = new Offre();
        } else if (qName.equals("ID_offre")) {
            idTag = "open";
        } else if (qName.equals("Libelle_offre")) {
            Libelle_offreTag = "open";
        } else if (qName.equals("Dispo")) {
            DispoTag = "open";
        }else if (qName.equals("date_Post")) {
            date_postTag = "open";
        } else if (qName.equals("ID_prest")) {
            ID_prestTag = "open";
        } else if (qName.equals("note")) {
            noteTag = "open";      
        } else if (qName.equals("prix")) {
            prixTag = "open";
        }  else if (qName.equals("urlimg")) {
            urlimgTag = "open";
        }   else if (qName.equals("num_tel")) {
            telTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("offre")) {
            // we are no longer processing a <reg.../> tag
            offres.addElement(currentOffre);
            currentOffre = null;
        } else if (qName.equals("ID_offre")) {
            idTag = "close";
        } else if (qName.equals("Libelle_offre")) {
            Libelle_offreTag = "close";
        } else if (qName.equals("Dispo")) {
            DispoTag = "close";
        }else if (qName.equals("date_Post")) {
            date_postTag = "close";
        } else if (qName.equals("ID_prest")) {
            ID_prestTag = "close";
        } else if (qName.equals("note")) {
            noteTag = "close";      
        } else if (qName.equals("prix")) {
            prixTag = "close";
        }  else if (qName.equals("urlimg")) {
            urlimgTag = "close";
        }else if (qName.equals("num_tel")) {
            telTag = "close";
        }
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentOffre != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentOffre.setId_Offre(id);
            } else
                if (Libelle_offreTag.equals("open")) {
                String libelle = new String(ch, start, length).trim();
                currentOffre.setLibelle_off(libelle);
            } else
                    if (DispoTag.equals("open")) {
                String stat = new String(ch, start, length).trim();
                currentOffre.setStat_offre(stat);
            }else
                    if (date_postTag.equals("open")) {
                String datep = new String(ch, start, length-9).trim();
                        
                currentOffre.setDate_post(datep);
            }else
                    if (prixTag.equals("open")) {
                String prix = new String(ch, start, length).trim();
                currentOffre.setPrix(prix);
            }else
                    if (ID_prestTag.equals("open")) {
                String prest = new String(ch, start, length).trim();
                currentOffre.setNomPrest(prest);
            }else
                    if (noteTag.equals("open")) {
                String note = new String(ch, start, length).trim();
                currentOffre.setNoteOffre(note);
            }else
                    if (urlimgTag.equals("open")) {
                String url = new String(ch, start, length).trim();
                currentOffre.setUrlimg(url);
            }else
                    if (telTag.equals("open")) {
                String tel = new String(ch, start, length).trim();
                currentOffre.setTelephone(tel);
            }
        }
    }
    
}
