/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.tests;

import Edu.esprit.DAO.PrestDAO;
import Edu.esprit.Entities.Prestataire;
import java.sql.Date;

/**
 *
 * @author Elyes
 */
public class TestCRUDPrest {
    public static void main(String[] args) {
        Date deAb=new Date(new java.util.Date().getTime());
        Date finAb=new Date(new java.util.Date().getTime());
        Prestataire prest=new Prestataire(deAb, finAb, "Microsoft2", "123", "Microsoft", "Blabla", "Rue fagaebaebaea", "Bla@esprit.Tn");
        PrestDAO.addPrest(prest);
    }
}
