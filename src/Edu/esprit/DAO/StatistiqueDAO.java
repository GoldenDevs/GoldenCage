/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edu.esprit.DAO;

import Edu.esprit.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author manel
 */
public class StatistiqueDAO {

    public StatistiqueDAO() {
        //constructeur
    }

    public int findNumberOf(String critaire) {
        /*fonction permettant de  recupéré size d'une  table depuis  la  base
         */
        int nb = 0;
        String requete = " SELECT COUNT(*)  AS count FROM `" + critaire + "`";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                nb = resultat.getInt("count");
                System.out.println(nb);
            }

            return nb;
        } catch (SQLException ex) {
            System.out.println("erreur de chargement" + ex.getMessage());
            return -1;
        }
    }

    public int findNombreoffre(String table, String critere, String valeur) {
        /*fonction permettant de  recupéré size d'une  table   selon un critaire
         et une  valeur depuis  la  base  */
        int nb = 0;

        String requete = " SELECT COUNT(*) AS count FROM `" + table + "` WHERE `" + critere + "`=" + valeur;

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                nb = resultat.getInt("count");
                
                System.out.println("Nb"+nb);
            }
            return nb;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return -1;
        }
    }

    public int findNombrePrestatair(int idPrestataire) {
        /*fonction permettant de  recupéré size d'une  table   selon un critaire
         et une  valeur depuis  la  base  */
        int nb = 0;

        String requete = " SELECT id  FROM `produit` WHERE `prestataire`='"+idPrestataire+"'";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                nb = resultat.getInt("id");
                System.out.println(nb);
            }
            return nb;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return -1;
        }

       
    }

  

    public int UserSexe(String sexe) {
        int result = 0;
        String requete = " SELECT count(*) AS count FROM `user` WHERE `sexe`='" + sexe + "'";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                result = resultat.getInt("count");
                System.out.println(result);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return -1;
        }
    }
  public int UserDateInscrit(java.sql.Date date) {
        int result = 0;
        String requete = " select count(*) from user where goldencage.user.date_inscrit =?";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            ps.setDate(1, date);
            while (resultat.next()) {
                result = resultat.getInt("count");
                System.out.println(result);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return -1;
        }
    }
public List<Integer> listesjour (String datedebut,String datefin){

List<Integer> list = new ArrayList<>();
DateTime d1 = DateTime.parse(datedebut);
DateTime d2 = DateTime.parse(datefin);
while (d1.isBefore(d2)){
DateTime newDate = d1.plusDays(1);
  String requete = " select count(*) as mm from user where goldencage.user.date_inscrit between ? and ?";
   PreparedStatement ps;
        try {
            ps = MyConnection.getInstance().prepareStatement(requete);
            java.sql.Date ddd1 = new java.sql.Date(d1.getMillis());
             java.sql.Date ddd2 = new java.sql.Date(newDate.getMillis());
             
            ps.setDate(1, ddd1);
             ps.setDate(2, ddd2);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()){
            int a= resultat.getInt("mm");
            list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
  d1 = d1.plusDays(1);
  

}
return list;
}
}
