/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.*;
import Edu.esprit.utils.CRUD;
import Edu.esprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.logging.Logger;




/**
 *
 * @author Elyes
 */
public class AdministrateurDAO {   
    
    public static Administrateur findAdminByLogin(String login){
     User user=new User();
     if((user=CRUD.findUserByLogin(login))!=null){
         
     Administrateur admin = new Administrateur();
     String requete = "select * from Administrateur where login=?";
     ResultSet rs;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(login)){
                    admin.setLogin(login);
                    admin.setPassword(user.getPassword());
                    admin.setNom(user.getNom());
                    admin.setPrenom(user.getPrenom());
                    admin.setEmail(user.getEmail());
                    
                }
                
            }
            return admin;
        } catch (SQLException ex) {
            
            return null;
        }
        
    }
     
    return null;
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
    
    public static boolean updateDateLoginAdmin(String login){
            
           String requete="Update Administrateur set last_login=? where login=?";
           Date date=new Date();
           java.sql.Date sqlDate= new java.sql.Date(date.getTime());
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setDate(1, sqlDate);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
            return false;
        }
    }
   
    public static String getAdminLastLogin(String login){
        String requete="Select last_login from Administrateur where login=?";
        java.sql.Date sqlDate=null;
         ResultSet rs;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            rs=ps.executeQuery();
            if(rs.next()){
                sqlDate=rs.getDate(1);
            }
            return sqlDate.toString();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
            return null;
        }
    }
    
    public static void setAdminPassword(String login,String Password){
        Administrateur admin=new Administrateur();
        admin=findAdminByLogin(login);
        admin.setPassword(Password);
        CRUD.updateUserByLogin(admin);
        System.out.println(admin);
    }
    
    
}
