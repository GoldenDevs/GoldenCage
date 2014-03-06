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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elyes
 */
public class OffreDAO {
   public static Offre findOffreByID(int id){
     
     Offre off = new Offre();
     String requete = "select * from Offre where ID_offre=?";
     ResultSet rs;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            if(rs.next()){ 
                off.setLibelle_off(rs.getString(1));
                off.setDate_Post(rs.getDate(4));
                off.setNomPrest(rs.getString(5));
                off.setNoteOffre(rs.getFloat(6));
                off.setEtat_offre(rs.getBoolean(3));
                off.setPrix(rs.getFloat(7));
            }
            return off;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
}
     
    public static boolean addOffre(Offre o){
        String requete = "Insert into Offre(Libelle_offre,dispo,date_Post,id_prest,prix)values(?,?,?,?,?)";
        
        try {           
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, o.getLibelle_off());
            ps.setBoolean(2, o.getEtatoffre());
            ps.setDate(3, o.getDate_Post());
            ps.setString(4, o.getNomPrest());
            ps.setFloat(5, o.getPrix());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout d'Offre !");
            return false;
        }
    }
    
    public static boolean deleteOffre(int id){
        String requete="DELETE from Offre where ID_offre=?";
        
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Erreur de Suppression !"+ex.getMessage());
            return false;
        }
    }
    public List<Offre>listOffresPrest(String login){
        String requete="Select * from offre where id_prest=?";
        ResultSet rs=null;
        Offre off=new Offre();
        List<Offre>list=new ArrayList<Offre>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            rs=ps.executeQuery();
            while(rs.next()){
                off=findOffreByID(rs.getInt(0));
                list.add(off);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void updateOffreByID(int id){
        
    }
    public static void updateEtatOffreDispo(String login){
            
           String requete="Update Offre set Dispo=? where ID_offre=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }
    }
    
    public static void updateEtatOffreNonDispo(int id){
            
           String requete="Update Offre set dispo=? where ID_offre=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 0);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur de mise a jour\n"+ex.getMessage());
        }
    }
    
    public static void upateTableOffre(javax.swing.JTable table_Offres){
        
        String requete="SELECT u.login Login,u.nom Nom,u.prenom Prenom,u.email Email FROM goldencage.client a,goldencage.user u where a.login=u.login";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
        table_Offres.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
}
