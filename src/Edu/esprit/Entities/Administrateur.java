/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

/**
 *
 * @author Touch media
 */
public class Administrateur extends User{

    public Administrateur(String login, String password, String nom, String prenom, String adresse, String email) {
        super(login, password, nom, prenom, adresse, email);
    }

    public Administrateur(String login, String password, String email) {
        super(login, password, email);
    }

    public Administrateur() {
    }
    
}
