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
    private String rec_text;
    private Date date_rec;
    private String idClient;
    private String offre_rec;

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

    public String getOffre_rec() {
        return offre_rec;
    }

    public void setOffre_rec(String offre_rec) {
        this.offre_rec = offre_rec;
    }

    public Reclamation(String rec_text, Date date_rec, String nomClient, String offre_rec) {
        this.rec_text = rec_text;
        this.date_rec = date_rec;
        this.idClient = nomClient;
        this.offre_rec = offre_rec;
    }
    
    
}
