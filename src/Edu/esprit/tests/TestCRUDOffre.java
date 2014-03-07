/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.tests;

import Edu.esprit.DAO.OffreDAO;
import Edu.esprit.Entities.Offre;

/**
 *
 * @author Elyes
 */
public class TestCRUDOffre {
    public static void main(String[] args) {
        Offre off=new Offre("Sourie444", true,(float) 100.5, "Microsoft2");
        //System.out.println(OffreDAO.verifExistOffre("aegaeg"));
        OffreDAO.addOffre(off);
    }
}
