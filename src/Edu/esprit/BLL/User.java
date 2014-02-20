/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.BLL;

/**
 *
 * @author Elyes
 */
public class User {
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String login, String password, String nom, String prenom, String adresse, String email) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
    }
    
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
    public User() {
    }

    @Override
    public String toString() {
        return "Info User :\nLogin : "+getLogin()+"\nPassword : "+getPassword()+"\nE-mail : "+getEmail()
                +"\n\nInfo Personnel :\nNom & Prenom "+getNom()+" "+getPrenom()+"\nAdresse : "+getAdresse();
    }
    
    
}
