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
        
        
        //Administrateur admin=new Administrateur("test2", "123", "test 2", "tester2", "tester in esprit 2", "test2@PIDev.tn");
        //Administrateur admin=new Administrateur("test3", "123", "test 3", "tester3", "tester in esprit 3", "test3@PIDev.tn");
        //AdministrateurDAO.addAdmin(admin);
//        System.out.println(CRUD.findUserByLogin("Elyes"));
//        System.out.println(adminDAO.findAdminByLogin("Elyes"));
       // User user=new User("Test", "Test", "Test@PIDev.tn");
//        CRUD.addUser(user);
//        System.out.println(CRUD.findUserByLogin(user.getLogin()));
//        user.setEmail("AAAAn");
//        user.setPrenom("AAAA");
//        user.setNom("NAAA");
//        CRUD.updateUserByLogin(user);
        //System.out.println(AdministrateurDAO.findAdminByLogin("Microsft")==null);
//        adminDAO.deleteAdmin(a);
        AdministrateurDAO.setSuperAdminPassword("Super23");
        System.out.println(AdministrateurDAO.getSuperAdminPassword());

        


    }
}
