/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.utils;

import Edu.esprit.Entities.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Touch media
 */
public class CRUD {
    
    
    public static boolean addUser(User a){
        
        String requete1 = "Insert into user(login,password,nom,prenom,email)values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps1=edu.esprit.util.MyConnection.getInstance().prepareStatement(requete1);
            ps1.setString(1, a.getLogin());
            ps1.setString(2, a.getPassword());
            ps1.setString(3, a.getNom());
            ps1.setString(4, a.getPrenom());
            ps1.setString(5, a.getEmail());
            ps1.executeUpdate();
            
            System.out.println("Ajout avec succes !");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout ");
            return false;
        }
    }
    
    public static boolean deleteUser(User u){
        
        
        return false; 
    }
    
    public static boolean updateByLogin(User u){
        
        return false;
    }
}
