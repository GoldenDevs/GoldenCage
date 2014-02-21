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
public class Reservation {
    private String offre;
    private Date date_debut;
    private Date date_fin;
    private String nomClient;
    private String nomPrest;

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getNomPrest() {
        return nomPrest;
    }

    public void setNomPrest(String nomPrest) {
        this.nomPrest = nomPrest;
    }

    public Reservation(String offre, Date date_debut, Date date_fin, String nomClient, String nomPrest) {
        this.offre = offre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomClient = nomClient;
        this.nomPrest = nomPrest;
    }

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Info Reservation \n\nDate Reservation : "+date_debut+"Fin de Reservation : "+date_fin
                +"\nClient : "+nomClient+"\nPrestataire :"+nomPrest+"\n";
    }
    
    
}
