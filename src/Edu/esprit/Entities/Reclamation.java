/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

import java.util.Date;

/**
 *
 * @author Touch media
 */
public class Reclamation {
    private int id_rec;
    private String rec_text;
    private String rec_sujet;
    private Date date_rec;
    private String idClient;
    private int offre_rec;
       private String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
     public int getId_rec() {
        return id_rec;
    }

    public Reclamation() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getRec_sujet() {
        return rec_sujet;
    }

    public void setRec_sujet(String rec_sujet) {
        this.rec_sujet = rec_sujet;
    }

    public String getRec_text() {
        return rec_text;
    }

    public void setRec_text(String rec_text) {
        this.rec_text = rec_text;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public String getidClient() {
        return idClient;
    }

    public void setNomClient(String idClient) {
        this.idClient = idClient;
    }

    public int getOffre_rec() {
        return offre_rec;
    }

    public void setOffre_rec(int offre_rec) {
        this.offre_rec = offre_rec;
    }

    public Reclamation(String rec_text, Date date_rec, String nomClient, int offre_rec,String rec_sujet) {
        this.rec_text = rec_text;
        this.date_rec = date_rec;
        this.idClient = nomClient;
        this.offre_rec = offre_rec;
        this.rec_sujet=rec_sujet;
    }

    public void setId_rec(int id_reclamtion) {
        id_rec=id_reclamtion;
        
    }
    
    
}
