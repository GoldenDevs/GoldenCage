/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Elyes
 */
public class Client extends User{
    private Date dateInscrit;
    private List<Offre>panier;
    
    public Date getDateInscrit() {
        return dateInscrit;
    }

    public void setDateInscrit(Date dateInscrit) {
        this.dateInscrit = dateInscrit;
    }

    public Client(Date dateInscrit, String login, String password, String nom, String prenom, String adresse, String email) {
        super(login, password, nom, prenom, adresse, email);
        this.dateInscrit = dateInscrit;
    }

    public Client(Date dateInscrit, String login, String password, String email) {
        super(login, password, email);
        this.dateInscrit = dateInscrit;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Info Client :\nLogin : "+getLogin()+"\nPassword : "+getPassword()+"\nE-mail : "+getEmail()
                +"\n\nInfo Personnel :\nNom & Prenom "+getNom()+" "+getPrenom()+"\nAdresse : "+getAdresse()
                +"\nDate d'Inscription : "+getDateInscrit();
    }
    
    
}
