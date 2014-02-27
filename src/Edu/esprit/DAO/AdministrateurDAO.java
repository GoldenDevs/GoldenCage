/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.*;
import Edu.esprit.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Elyes
 */
public class AdministrateurDAO {   
    
    public Administrateur findAdminByLogin(String login){
    
     Administrateur admin = null;
     String requete = "select * from Administrateur where login=?";
        try {
            PreparedStatement ps =MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next())
            {
                admin=(Administrateur)CRUD.findUserByLogin(login);
            }
            return admin;
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
