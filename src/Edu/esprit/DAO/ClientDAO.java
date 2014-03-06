/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.Client;
import Edu.esprit.Entities.Prestataire;
import Edu.esprit.Entities.User;
import Edu.esprit.utils.CRUD;
import Edu.esprit.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elyes
 */
public class ClientDAO {
     public static Prestataire findClientByLogin(String login){
     
     Prestataire prest = new Prestataire();
     String requete = "select * from Client where login=?";
     ResultSet rs;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            rs=ps.executeQuery();
            if(rs.next()){ 
                    User user=new User(); 
                    user=CRUD.findUserByLogin(login);
                    prest.setLogin(login);
                    prest.setPassword(user.getPassword());
                    prest.setNom(user.getNom());
                    prest.setPrenom(user.getPrenom());
                    prest.setEmail(user.getEmail());  
            }
            return prest;
        } catch (SQLException ex) {
            return null;
        }
}
     
    public static boolean addClient(Client c){
        String requete = "Insert into Client(login,date_naiss)values(?,?)";
        
        try {           
            CRUD.addUser(c);
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, c.getLogin());
            ps.setDate(2, c.getDateNaiss());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout Client !");
            return false;
        }
    }
    
    public static boolean deleteClient(String login){
        String requete="DELETE from Client where login=?";
        
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
    public List<User>listClient(){
        String requete="Select * from goldencage.user u,goldencage.client a where u.login=a.login";
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
    
    public static void updateEtatClientDEBAN(String login){
            
           String requete="Update Client set etat=? where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }
    }
    
    public static void updateEtatClientBAN(String login){
            
           String requete="Update Client set etat=? where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 0);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }
    }
    
    public static void upateTableClient(javax.swing.JTable table_client){
        
        String requete="SELECT u.login Login,u.nom Nom,u.prenom Prenom,u.email Email FROM goldencage.client a,goldencage.user u where a.login=u.login";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
        table_client.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
