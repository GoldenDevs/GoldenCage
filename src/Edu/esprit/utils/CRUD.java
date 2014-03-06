/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.utils;

import Edu.esprit.DAO.AdministrateurDAO;
import Edu.esprit.Entities.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Edu.esprit.utils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Touch media
 */
public class CRUD {
   
    public static boolean addUser(User a){
        
        String requete1 = "Insert into user(login,password,nom,prenom,email,adresse)values(?,?,?,?,?,?)";
       
        try {
            PreparedStatement ps1=MyConnection.getInstance().prepareStatement(requete1);
            ps1.setString(1, a.getLogin());
            ps1.setString(2, a.getPassword());
            ps1.setString(3, a.getNom());
            ps1.setString(4, a.getPrenom());
            ps1.setString(5, a.getEmail());
            ps1.setString(6, a.getAdresse());
            ps1.executeUpdate();
            
            System.out.println("Ajout avec succes !");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout "+ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteUser(String login){
        String requete="DELETE from user where login=?";
        
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ps.executeUpdate();
                System.out.println("Suppression avec Succés !");
                return true;
            
        } catch (SQLException ex) {
            System.out.println("Erreur de Suppression !"+ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateUserByLogin(User u){
            String requete="Update user set password=?,nom=?,prenom=?,email=? where login=?";           
            try {
                PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
                ps.setString(1, u.getPassword());
                ps.setString(2, u.getNom());
                ps.setString(3, u.getPrenom());
                ps.setString(4, u.getEmail());
                ps.setString(5, u.getLogin());    
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée avec succès !");
                return true;
            } catch (SQLException ex) {
                System.out.println("Erreur de Mise a jour !\n"+ex.getMessage());
                return false;
            }
    }
    
    public static User findUserByLogin(String login){
        String requete="Select * from user where login=?";
        User user=new User();
        ResultSet rs=null;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1,login);
            rs=ps.executeQuery();
            while (rs.next())
            {
                
                if(rs.getString(1).equals(login)){
                user.setLogin(login);
                user.setPassword(rs.getString(2));
                user.setNom(rs.getString(3));
                user.setPrenom(rs.getString(4));
                user.setEmail(rs.getString(5));
                return user;
                }
                
            }
            return null;
            
            } catch (SQLException ex) {
            System.out.println("Login n'existe pas.\n"+ex.getMessage());
            return null;
        }
    }
    
    
    public static List<User>listUsers(){
        String requete="Select * from User";
        ResultSet rs=null;
        User user=new User();
        List<User>list=new ArrayList<User>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            rs=ps.executeQuery();
            while(rs.next()){
                user=CRUD.findUserByLogin(rs.getString(1));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
