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
public class Administrateur extends User{
    
    private Date last_login;

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
    
    public Administrateur(String login, String password, String nom, String prenom, String adresse, String email) {
        super(login, password, nom, prenom, adresse, email);
    }

    public Administrateur(String login, String password, String email) {
        super(login, password, email);
    }

    public Administrateur() {
    }

    @Override
    public String toString() {
        return "Info Administrateur :\nLogin : "+getLogin()+"\nPassword : "+getPassword()+"\nE-mail : "+getEmail()
                +"\n\nInfo Personnel :\nNom & Prenom : "+getNom()+" "+getPrenom()+"\nAdresse : "+getAdresse();
    }
    
}
