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
    private Date dateNaiss;
    private int age;
    private char sexe;
    
    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    private List<Offre>panier;
    
    public Date getDateInscrit() {
        return dateInscrit;
    }
    public int getAge(){
        return age;
    }
    public int setAge(){
        return (new Date(new java.util.Date().getTime()).getYear()-dateNaiss.getYear());
    }
    public void setDateInscrit(Date dateInscrit) {
        this.dateInscrit = dateInscrit;
    }

    public Client(Date dateInscrit, String login, String password, String nom, String prenom, String adresse, String email,Date dateNaiss) {
        super(login, password, nom, prenom, adresse, email);
        this.dateInscrit = dateInscrit;
        this.dateNaiss=dateNaiss;
    }

    public Client(Date dateInscrit, String login, String password, String email,Date dateNaiss) {
        super(login, password, email);
        this.dateInscrit = dateInscrit;
        this.dateNaiss=dateNaiss;

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
