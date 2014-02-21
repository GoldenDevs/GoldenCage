/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Touch media
 */
public class Prestataire extends User{
    private Date debut_Abonnement;
    private Date fin_Abonnement;
    private List<Offre>listOffres;

    public Date getDebut_Abonnement() {
        return debut_Abonnement;
    }

    public void setDebut_Abonnement(Date debut_Abonnement) {
        this.debut_Abonnement = debut_Abonnement;
    }

    public Date getFin_Abonnement() {
        return fin_Abonnement;
    }

    public void setFin_Abonnement(Date fin_Abonnement) {
        this.fin_Abonnement = fin_Abonnement;
    }

    public List<Offre> getListOffres() {
        return listOffres;
    }

    public void setListOffres(List<Offre> listOffres) {
        this.listOffres = listOffres;
    }

    public Prestataire(Date debut_Abonnement, Date fin_Abonnement, String login, String password, String nom, String prenom, String adresse, String email) {
        super(login, password, nom, prenom, adresse, email);
        this.debut_Abonnement = debut_Abonnement;
        this.fin_Abonnement = fin_Abonnement;
    }

    public Prestataire(Date debut_Abonnement, Date fin_Abonnement, String login, String password, String email) {
        super(login, password, email);
        this.debut_Abonnement = debut_Abonnement;
        this.fin_Abonnement = fin_Abonnement;
    }

    public Prestataire(Date debut_Abonnement, Date fin_Abonnement) {
        this.debut_Abonnement = debut_Abonnement;
        this.fin_Abonnement = fin_Abonnement;
    }
    
    
}
