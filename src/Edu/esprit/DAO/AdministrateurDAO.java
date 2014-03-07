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
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;




/**
 *
 * @author Elyes
 */
public class AdministrateurDAO {   
    
public static Administrateur findAdminByLogin(String login){
     
     Administrateur admin=null;
     String requete = "SELECT * FROM goldencage.administrateur a,goldencage.user u where a.login=u.login and a.login=?";
     ResultSet rs;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            rs=ps.executeQuery();
            while(rs.next()){
                    if(rs.getString(1).equals(login)){
                        User user=new User(); 
                            if((user=CRUD.findUserByLogin(login))!=null){
                                admin=new Administrateur();
                                admin.setLogin(login);
                                admin.setPassword(user.getPassword());
                                admin.setNom(user.getNom());
                                admin.setPrenom(user.getPrenom());
                                admin.setEmail(user.getEmail());
                                
                            }
                            else{
                                return null;
                            }
                    }
                    
            }  
            return admin;
        } 
        catch (SQLException ex) {
            return null;
        }
}
     
    public static boolean addAdmin(Administrateur a){
        
        
        String requete = "Insert into Administrateur(login)values(?)";
        try {           
            CRUD.addUser(a);
            PreparedStatement ps2=MyConnection.getInstance().prepareStatement(requete);
            ps2.setString(1, a.getLogin());
            ps2.executeUpdate();
            updateDateLoginAdmin(a.getLogin());
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout administrateur !");
            return false;
        }
    }
    
    public static boolean deleteAdmin(String login){
        String requete="DELETE from Administrateur where login=?";
        
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ps.executeUpdate();
            CRUD.deleteUser(login);
                System.out.println("Suppression avec Succés !");
                return true;
            
        } catch (SQLException ex) {
            System.out.println("Erreur de Suppression !"+ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateAdminByLogin(Administrateur a,String login){
            String requete="Update administrateur set login=? where login=?";           
            try {
                CRUD.updateUserByLogin(a);
                PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);  
                ps.setString(1, a.getLogin());
                ps.setString(2, login); 
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée avec succès !");
                return true;
            } catch (SQLException ex) {
                System.out.println("Erreur de Mise a jour !\n"+ex.getMessage());
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
    
    public static void setSuperAdminPassword(String Password){
        Administrateur admin=new Administrateur();
        admin=findAdminByLogin("superadmin");
        admin.setPassword(Password);
        CRUD.updateUserByLogin(admin);
       
    }
    
    public static String getSuperAdminPassword(){
        String pwd=null;
        Administrateur admin=new Administrateur();
        admin=findAdminByLogin("superadmin");
        pwd=admin.getPassword();
        return pwd;
}
    
    public List<User>listAdministrateur(){
        String requete="Select * from goldencage.user u,goldencage.administrateur a where u.login=a.login";
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
    public static void upateTableAdmins(javax.swing.JTable table_admins){
        
        String requete="SELECT u.login Login,u.nom Nom,u.prenom Prenom,u.email Email FROM goldencage.administrateur a,goldencage.user u where a.login=u.login";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
        table_admins.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
