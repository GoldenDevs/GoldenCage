/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.Administrateur;
import Edu.esprit.utils.CRUD;
import edu.esprit.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elyes
 */
public class AdministrateurDAO {
    
    public Administrateur findAdminByLogin(String login){
    
     Administrateur admin = new Administrateur();
     String requete = "select * from user where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                if(resultat.getString(1).equals(login)){
                admin.setLogin(login);
                admin.setPassword(resultat.getString(2));
                admin.setNom(resultat.getString(3));
                admin.setPrenom(resultat.getString(4));
                admin.setEmail(resultat.getString(5));
                admin.setAdresse(resultat.getString(7));}
                return admin;
            }
            return null;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Login Administrateur "+ex.getMessage());
            return null;
        }
    }
    
    public boolean addAdmin(Administrateur a){
        
        
        String requete = "Insert into Administrateur(login)values(?)";
        try {           
            CRUD.addUser(a);
            PreparedStatement ps2=MyConnection.getInstance().prepareStatement(requete);
            ps2.setString(1, a.getLogin());
            ps2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout administrateur !");
            return false;
        }
    }
    
    
    public boolean deleteAdmin(Administrateur a){
        String requete="DELETE from Administrateur where login=?";
        
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, a.getLogin());
            ps.executeUpdate();
            CRUD.deleteUser(a);
                System.out.println("Suppression avec Succés !");
                return true;
            
        } catch (SQLException ex) {
            System.out.println("Erreur de Suppression !"+ex.getMessage());
            return false;
        }
    }
    
    public boolean updateAdmin(Administrateur a){
        
        return false;
    }
   
}
