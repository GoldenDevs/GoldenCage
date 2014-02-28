/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.tests;

import Edu.esprit.DAO.AdministrateurDAO;

import Edu.esprit.Entities.*;
import Edu.esprit.utils.*;
import java.util.Date;

/**
 *
 * @author Touch media
 */
public class TestCRUDAdmin {
    public static void main(String[] args) {
        

        AdministrateurDAO adminDAO= new AdministrateurDAO();
        Administrateur admin=new Administrateur();
//        adminDAO.addAdmin(a);
//        System.out.println(CRUD.findUserByLogin("Elyes"));
//        System.out.println(adminDAO.findAdminByLogin("Elyes"));
//        User user=new User("Test", "Test", "Test@PIDev.tn");
//        CRUD.addUser(user);
//        System.out.println(CRUD.findUserByLogin(user.getLogin()));
//        user.setEmail("AAAAn");
//        user.setPrenom("AAAA");
//        user.setNom("NAAA");
//        CRUD.updateUserByLogin(user);
//        System.out.println(CRUD.findUserByLogin(user.getLogin()));
//        adminDAO.deleteAdmin(a);
            AdministrateurDAO.updateDateLoginAdmin("Test");
            AdministrateurDAO.updateDateLoginAdmin("Test2");

    }
}
