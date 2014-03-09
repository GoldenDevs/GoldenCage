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
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elyes
 */
public class PrestDAO {
     
    public static Prestataire findPrestByLogin(String login){
     
     Prestataire prest = new Prestataire();
     String requete = "select * from Prestataire where login=?";
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
     
    public static boolean addPrest(Prestataire p){
        String requete = "Insert into Prestataire(login,debutAb,finAb)values(?,?,?)";
        Date sqlDateDeb=p.getDebut_Abonnement();
        Date sqlDateFin=p.getFin_Abonnement();
        try {           
            CRUD.addUser(p);
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, p.getLogin());
            ps.setDate(2,sqlDateDeb);
            ps.setDate(3,sqlDateFin);
               
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout Prestataire !");
            return false;
        }
    }
    
    public static boolean deletePrest(String login){
        String requete="DELETE from Prestataire where login=?";
        
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
    
    public static List<Prestataire>listPrest(){
        String requete="Select * from goldencage.user u,goldencage.prestataire p where u.login=p.login";
        ResultSet rs=null;
        Prestataire prest=new Prestataire();
        List<Prestataire>list=new ArrayList<Prestataire>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            rs=ps.executeQuery();
            while(rs.next()){
                prest=findPrestByLogin(rs.getString(1));
                list.add(prest);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void upateTablePrest(javax.swing.JTable table_prest){
        
        String requete="SELECT u.login Login,u.nom Nom,u.prenom Prenom,u.email Email FROM goldencage.prestataire a,goldencage.user u where a.login=u.login and a.etat=1";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
        table_prest.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
            
          
      

    public static void updateEtatPrestBAN(String login) {
 String requete="Update prestataire set etat=? where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 0);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }    }

    public static void updateEtatPrestDEBAN(String login) {
 String requete="Update prestataire set etat=? where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }
    }   
public static void upateTablePrestBanni(javax.swing.JTable table_client_banni){
        
        String requete="Select login 'prestataire Banni' from prestataire where etat=0";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
        table_client_banni.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}