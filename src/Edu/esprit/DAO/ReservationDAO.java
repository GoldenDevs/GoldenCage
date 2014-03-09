/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edu.esprit.DAO;

import Edu.esprit.Entities.Reclamation;
import Edu.esprit.Entities.Reservation;
import Edu.esprit.Entities.User;
import Edu.esprit.utils.CRUD;
import java.sql.PreparedStatement;
import Edu.esprit.utils.MyConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elyes
 */
public class ReservationDAO {

    public void insertReservation(Reservation r) throws SQLException {
        String requete = "insert into reservation (id_offre,id_client,date_debut,date_fin) values (?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        
            //ps.setInt(0, r.getId());
            ps.setInt(1,r.getId_offre());
            ps.setString(2,r.getId_client());
            ps.setDate(3, (Date) r.getDate_debut());
            ps.setDate(4, (Date) r.getDate_fin());
         

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void updateReservation(Reservation r) {
        String requete = "update reclamation set id=?,id_offre=?,id_client=?,date_debut=?,date_fin=? where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);

           ps.setInt(0, r.getId());
            ps.setInt(1,r.getId_offre());
            ps.setString(2,r.getId_client());
            ps.setDate(3, (Date) r.getDate_debut());
            ps.setDate(4, (Date) r.getDate_fin());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {

            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
     public static void upateTableReservation(javax.swing.JTable jTable1_liste_reservation){
        
        String requete="SELECT r.id_reservation Identifient,r.id_offre Offre,r.id_client Client,r.date_debut 'Date Debut',r.date_fin 'Date Fin' FROM goldencage.reservation r;";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        rs=ps.executeQuery();
            jTable1_liste_reservation.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de mise a jour Liste de reservations \n "+ex.getMessage());
        }
     }

     public static void upateTableReservationByClient(javax.swing.JTable jTable1_liste_reservation,String login){
        
        String requete="SELECT r.id_reservation Identifient,r.id_offre Offre,r.id_client Client,r.date_debut 'Date Debut',r.date_fin 'Date Fin' FROM goldencage.reservation r where id_client=?";
        ResultSet rs=null;
        try{
        PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
        ps.setString(1, login);
        rs=ps.executeQuery();
            jTable1_liste_reservation.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de mise a jour Liste de reservations \n "+ex.getMessage());
        }
     }
     
     
//    public void deleteReclamation(int id) {
//        String requete = "delete from reclamation where id=?";
//        try {
//            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            System.out.println("reclamation supprimer");
//        } catch (SQLException ex) {
//
//            System.out.println("erreur lors de la suppression " + ex.getMessage());
//        }
//    }

    public Reservation findReservationById(int id) {
        Reservation reserv = new Reservation();
        String requete = "select * from reservation where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                reserv.setId(resultat.getInt(0));
                reserv.setId_offre(resultat.getInt(1));
                reserv.setId_client(resultat.getString(2));
                reserv.setDate_debut(resultat.getDate(3));
                reserv.setDate_fin(resultat.getDate(4));
               

            }
            return reserv;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    public List<Reservation> findReservationByClient(int id) {
     
        List<Reservation> listereservation = new ArrayList<Reservation>();

        String requete = "select * from reservation where id=?";
        try {
            Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId_rec(resultat.getInt(1));
                reclamation.setRec_sujet(resultat.getString(2));
                reclamation.setRec_text(resultat.getString(3));
                reclamation.setNomClient(resultat.getString(4));
                reclamation.setOffre_rec(resultat.getInt(5));
                reclamation.setDate_rec(resultat.getDate(6));

                listereservation.add(null);
            }
            return listereservation;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des reservations " + ex.getMessage());
            return null;
        }
    }

    
    public List<Reservation> DisplayAllRservation() {

        List<Reservation> listereservation= new ArrayList<>();

        String requete = "select * from reservation";
        try {
            Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId_rec(resultat.getInt(1));
                reclamation.setRec_sujet(resultat.getString(2));
                reclamation.setRec_text(resultat.getString(3));
                reclamation.setNomClient(resultat.getString(4));
                reclamation.setOffre_rec(resultat.getInt(5));
                reclamation.setDate_rec(resultat.getDate(6));

                listereservation.add(null);
            }
            return listereservation;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des reclamations " + ex.getMessage());
            return null;
        }
    }

    public static void updateListReservation(javax.swing.JList list_reclamations) {

    }
}
