/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.DAO;

import Edu.esprit.Entities.Administrateur;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elyes
 */
public class AdministrateurDAO {
    
    public boolean findAdminByLogin(String login){
    
     Administrateur admin = new Administrateur();
     String requete = "select * from Administrateur where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                if(login.equals(resultat.getString(1)))
                return true;
            }
            return false;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Login Administrateur "+ex.getMessage());
            return false;
        }
    }
}
