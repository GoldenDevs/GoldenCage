/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.Reclamation;
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

/**
 *
 * @author Elyes
 */
public class ReclamationDAO {
    public void insertReclamation(Reclamation r){
        String requete = "insert into reclamation (id_reclamation,rec_sujet,rec_text,id_client,id_offre,date_post) values (default,?,?,?,?,?)";
         try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, r.getRec_sujet());
            
            ps.setString(2, r.getRec_text());
            ps.setInt(4, r.getOffre_rec());
            ps.setString(3, r.getidClient());
            ps.setDate(5, (Date) r.getDate_rec());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }    
    }
    public static void updateReclamation(Reclamation r){
        String requete = "update reclamation set rec_sujet=?,rec_text=?,id_client=?,id_offre=?,date=? where id_rec=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
          
            ps.setString(1, r.getRec_sujet()); 
            ps.setString(2, r.getRec_text());
            ps.setString(3, r.getidClient());
            ps.setInt(4, r.getOffre_rec());
            ps.setDate(5, (Date) r.getDate_rec());
           
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
   
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }
    }
     public static void deleteReclamation(int id){
        String requete = "delete from reclamation where id_user=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("reclamation supprimer");
        } catch (SQLException ex) {
          
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
      }
     public static Reclamation findReclamationByIdRec(int id){
        Reclamation reclamation = new Reclamation();
        String requete = "select * from reclamation where id_reclamation=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
               reclamation.setId_rec(resultat.getInt(1));
               reclamation.setRec_sujet(resultat.getString(2));
               reclamation.setRec_text(resultat.getString(3));
               reclamation.setNomClient(resultat.getString(4));
               reclamation.setOffre_rec(resultat.getInt(5));
               reclamation.setDate_rec(resultat.getDate(6));
               
            }
            return reclamation;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }
      public List<Reclamation> findReclamationByClient(int id){
//        Reclamation reclamation = new Reclamation();
//     String requete = "select * from reclamation where id_client=?";
//        try {
//            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
//            ps.setInt(1, id);
//            ResultSet resultat = ps.executeQuery();
//            while (resultat.next())
//            {
//               reclamation.setId_rec(resultat.getInt(1));
//               reclamation.setRec_sujet(resultat.getString(2));
//               reclamation.setRec_text(resultat.getString(3));
//               reclamation.setNomClient(resultat.getString(4));
//               reclamation.setOffre_rec(resultat.getInt(5));
//               reclamation.setDate_rec(resultat.getDate(6));
//               
//            }
//            return reclamation;
//
//        } catch (SQLException ex) {
//            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
//            return null;
//        }
        List<Reclamation> listereclamation = new ArrayList<Reclamation>();

        String requete = "select * from reclamation where id_client=?";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
           ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
              Reclamation reclamation =new  Reclamation();
               reclamation.setId_rec(resultat.getInt(1));
               reclamation.setRec_sujet(resultat.getString(2));
               reclamation.setRec_text(resultat.getString(3));
               reclamation.setNomClient(resultat.getString(4));
               reclamation.setOffre_rec(resultat.getInt(5));
               reclamation.setDate_rec(resultat.getDate(6));
                

                listereclamation.add(reclamation);
            }
            return listereclamation;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des reclamations "+ex.getMessage());
            return null;
        }
     }
      
      
      
     /* public List<Reclamation>listAdministrateur(){
        String requete="Select * from reclamation";
        ResultSet rs=null;
        Reclamation rec=new Reclamation();
        List<Reclamation>list=new ArrayList<Reclamation>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            rs=ps.executeQuery();
            while(rs.next()){
                rec=CRUD.findUserByLogin(rs.getString(1));
                list.add(rec);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }*/
      
      public List<Reclamation> DisplayAllReclamation (){


        List<Reclamation> listereclamation = new ArrayList<>();

        String requete = "select * from reclamation";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
           ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
              Reclamation reclamation =new  Reclamation();
               reclamation.setId_rec(resultat.getInt(1));
               reclamation.setRec_sujet(resultat.getString(2));
               reclamation.setRec_text(resultat.getString(3));
               reclamation.setNomClient(resultat.getString(4));
               reclamation.setOffre_rec(resultat.getInt(5));
               reclamation.setDate_rec(resultat.getDate(6));
                

                listereclamation.add(reclamation);
            }
            return listereclamation;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des reclamations "+ex.getMessage());
            return null;
        }
    }
      
      
      public static void updateListReclamations(javax.swing.JList list_reclamations){
       
          
      }
}
